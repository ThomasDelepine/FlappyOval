package Modele;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import Controleur.*;
import Vue.*;

/**classe permettant de générer le parcours*/
public class Parcours {
    /*attribut correspondant à la position de l'oval (et au score du joueur)*/
    public int position;
    /*attribut correspondant au pas*/
    public int evol;
    /*attribut stockant la dernière ordonnée générée*/
    public int last_y;
    /*liste contenant les points du parcours*/
    public ArrayList<Point> points;
    /*modèle*/
    public ModeleFlappy modele;

    /**constructeur de la classe Parcours prenant en paramètre le modèle*/
    public Parcours(ModeleFlappy modele){
        /*construction de la liste de points*/
        this.points = new ArrayList<Point>();
        this.modele = modele;
        this.evol = this.modele.width/3;
        /*le premier point se trouve au niveau de l'oval au départ*/
        Point p = new Point(modele.x_oval, modele.y_oval);
        this.points.add(p);
        /*position actuelle sur l'image*/
        this.position = modele.x_oval + evol;
        /*création d'un second point pour rendre le départ facile*/
        Point p2 = new Point(this.position, this.modele.y_oval + this.modele.y_size);
        this.points.add(p2);
        /*dernière ordonnée générée*/
        this.last_y = p2.y;
        while(this.position < this.modele.width + 3*this.evol){
            this.position += this.evol;
            Random rand = new Random();
            /*génère un point aléatoire autour de la derniere position en ordonnée générée
            et bornée dans les 3/4 centraux de l'image*/
            int randy = this.last_y + -3*this.modele.jump + rand.nextInt(6*this.modele.jump);
            /*si on sort de l'image par le haut : on rerentre dedans*/
            if(randy < this.modele.height/8){
                randy = this.modele.height/4 + randy;
            }
            /*si on sort de l'image por la bas, on rerentre dedans*/
            else if(randy > 7*this.modele.height/8){
                randy = 7*this.modele.height/4 - randy;
            }
            /*ajoute un point (position, randy)*/
            Point po = new Point(this.position, randy);
            this.points.add(po);
            /*met à jour la dernière ordonnée générée*/
            this.last_y = po.y;

        }
    }
    /**getter du parcours*/
    public ArrayList<Point> getParcours(){
        return this.points;
    }
    /**getter de la position*/
    public int getposition(){return this.position;}
    /**setter de la position*/
    public void setposition(int p){this.position = p;}
    /**setter ajoutant un point au parcours*/
    public void newPoint(){
        this.position += this.evol;
        Random rand = new Random();
            /*génère un point aléatoire autour de la derniere position en ordonnée générée
            et bornée dans les 3/4 central de l'image*/
        int randy = this.last_y + -2*this.modele.jump + rand.nextInt(4*this.modele.jump);
        /*si on sort de l'image par le haut : on rerentre dedans*/
        if(randy <= this.modele.height/8){
            randy = this.modele.height/4 + randy;
        }
        /*si on sort de l'image por la bas, on rerentre dedans*/
        else if(randy >= 7*this.modele.height/8){
            randy = 7*this.modele.height/4 - randy;
        }
        /*ajoute un point (position, randy)*/
        Point po = new Point(this.position, randy);
        this.points.add(po);
        /*met à jour la dernière ordonnée générée*/
        this.last_y = po.y;
    }

}
