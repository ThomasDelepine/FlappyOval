package Modele;

import java.awt.*;

import Controleur.*;
import Vue.*;

import java.util.Random;

public class Avancer extends Thread{
    /*pointeur vers le parcours*/
    public Parcours pa;
    /*pointeur vers la vue*/
    public PanelFlappy vue;
    /*pointeur vers la fenetre*/
    public Fenetre fenetre;
    /*pointeur vers repaint*/
    public Repaint rp;
    /*flag de création/supprétion*/
    private boolean flag = true;
    /*décalage global*/
    public int decalage_global = 0;
    public Avancer(Parcours parcours, PanelFlappy v, Fenetre f){this.pa = parcours; this.vue = v; this.fenetre = f;}
    @Override
    public void run() {
        while(this.pa.modele.theGameIsAlive){
            /*évolution du décalge global, cette variable sera récupérée dans PanelFlappy pour décaler l'affichage.*/
            this.decalage_global += 1;
            double deca = this.decalage_global*this.pa.modele.speed(this.decalage_global);
            /*on fait une petite pause pour que le tout ressemble à un mouvement*/
            try {
                sleep(ModeleFlappy.tic/3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            /*quand le second point dépasse le point de départ de l'oval, ont rajoute un nouveau point*/
            if(this.pa.points.get(1).x - deca <= this.pa.modele.Start_x  && this.flag){
                this.pa.newPoint();
                this.flag = false;
            }
            /*quand le second point sort de la fenêtre, alors le premier n'est plus visible et donc on peut le supprimer*/
            if(this.pa.points.get(1).x - deca <= 0 && !this.flag){
                pa.points.remove(0);
                this.flag = true;
            }
            /*on met à jour le nom de la fenetre avec le score*/
            /*Cette fonctionnalité aurait pu mériter une classe à part entière, a voir si on la développe plus. */
            this.fenetre.setTitle("FlappyOval : " + String.valueOf((int)deca));


            /*création aléatoire d'un nouvel oiseau de décors*/
            Random rand = new Random();
            /*dans ce thread, on fait des pauses tous les tic/3 ms
            * on souhaite environ en moyenne 4 oiseaux dans la fenêtre.
            * */
            int tmp = rand.nextInt(this.fenetre.getWidth()/3);
            if(tmp == 1){
                Oiseau o_temp = new Oiseau(this.fenetre, this.vue.oiseaux, this.pa.modele);
                this.vue.oiseaux.addOiseau(o_temp);
                o_temp.start();
            }
            this.rp.ping();
        }
    }
    /**setter de rp*/
    public void setRp(Repaint rp){this.rp = rp;}
}
