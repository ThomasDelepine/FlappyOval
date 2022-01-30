package Vue;
import Modele.ModeleFlappy;

import java.util.Random;

public class Oiseau extends Thread{
    /*variables de la classe oiseau*/
    private final long delai;
    private int hauteur;
    private int position;
    private int etat;
    public static int minDelai = 40;

    /*booléen représentant la sortie de l'oiseau*/
    private boolean out = false;

    /*constante représentant le nombre d'image animant les oiseaux*/
    public static final int nbFrame = 8;

    /*pointeur vers la fenêtre*/
    public Fenetre fenetre;
    /*pointeur vers le modèle*/
    ModeleFlappy modele;
    /*pointeur vers la liste d'oiseaux*/
    VueOiseau vo;
    /**constructeur*/
    public Oiseau(Fenetre fenetre, VueOiseau vo, ModeleFlappy m){
        /*attribution de la fenêtre*/
        this.fenetre = fenetre;
        /*attribution de la VueOiseau*/
        this.vo = vo;
        this.modele = m;
        /*On place l'oiseau à droite de la fenêtre*/
        this.position = (int)(this.fenetre.getWidth()*1.2);
        Random rand = new Random();
        /*on calcul une heuteur aléatoire*/
        this.hauteur = rand.nextInt((int)(this.fenetre.getHeight()*0.6)) + (int)(this.fenetre.getHeight()*0.2);
        /*et une vitesse aléatoire*/
        this.delai = rand.nextInt(2*minDelai) + minDelai;
        this.etat = 0;

    }
    @Override
    public void run(){
        /*tant que l'oiseau n'a pas traversé l'écran*/
        while (this.position > -(this.fenetre.getWidth()*0.2)){
            try {
                sleep(delai);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(this.modele.theGameIsAlive){
                this.etat = (this.etat + 1) % nbFrame;
                this.position -= 3;
            }
        }
        this.out = true;
    }
    /**getter de la hauteur*/
    public int getHauteur(){return this.hauteur;}
    /**getter de la position*/
    public int getPosition(){return this.position;}
    /**getter de la présence dans la fenêtre de l'oiseau*/
    public boolean getOut(){return this.out;}
    /**getter de l'état*/
    public int getEtat(){return this.etat;}
    /**getter du délai*/
    public int getDelai(){return (int) this.delai;}
}
