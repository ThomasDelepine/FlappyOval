package Modele;

import java.awt.*;

import Controleur.*;
import Vue.*;

/**class contenant les données du jeu*/
public class ModeleFlappy {
	/*booléen vérifiant que le jeu n'est pas perdu*/
	public boolean theGameIsAlive = true;

	/*pointeur vers le Panel*/
	public PanelFlappy vue;
	/*pointeur vers le parcours*/
	public Parcours parcours;
	/*pointeur vers la fenêtre*/
	public Fenetre fenetre;
	/*pointeur vers l'avancement*/
	public Avancer avancer;

	/* Définition des constantes */
	public int height; //hauteur
	public int width; //largeur
	public int jump; //valeur de la distance de saut
	public int fall; //valeur de la distance de chute
	public int Start_x; //valeur de départ en x de l'oval
	
	/* Définition des variables*/
	public int x_oval; //position x de l'oval
	public int y_oval; //position y de l'oval
	public int x_size; //taille de l'oval en x
	public int y_size; //taille de l'oval en y

	/*attribut stockant le temps de tics*/
	public static int tic = 75;


	/**constructeur*/
	public ModeleFlappy(Fenetre fenetre) {
		this.fenetre = fenetre;
		/* Définition des constantes */
		this.height = this.fenetre.getHeight(); //hauteur
		this.width = this.fenetre.getWidth(); //largeur
		this.jump = 3*height/50; //valeur de la distance de saut
		this.fall = 8*height/1000; //valeur de la distance de chute

		/* Définition des variables*/
		this.x_oval = (int) (0.2*width); //position x de l'oval
		this.y_oval = (int) (0.5*height); //position y de l'oval
		this.Start_x = (int) (0.2*width); //point de départ
		this.x_size = (int) (this.width/(double)10);
		this.y_size = (int) (this.height/(double)10);
	}

	/**méthode définissant un saut d'envergure la constante "jump"*/
	public void jump() {
		if(this.y_oval > this.jump && this.theGameIsAlive) {
			this.y_oval -= jump;
		}
	}
	/**méthode définissant la chute d'envergure la constante "fall"*/
	public void moveDown() {
		if(this.y_oval < this.height && this.theGameIsAlive){
			this.y_oval += fall*this.speed(this.avancer.decalage_global);
		}
	}

	/**fonction estimant une défaite ou non : renvoie vrai si perdu*/
	public boolean testPerdu(){
		int x = (int) (this.x_oval + this.vue.avancer.decalage_global*this.speed(this.avancer.decalage_global) + this.x_size/(double)2);
		int y = (int) (this.y_oval + this.y_size/(double)2);
		boolean flag = true;
		int cpt = 0;
		while(flag){
			if(this.parcours.points.get(cpt + 1).x >= x){
				flag = false;
			}
			else{
				cpt++;
			}
		}
		int Y = (int) (this.parcours.points.get(cpt).y + ((this.parcours.points.get(cpt + 1).y - this.parcours.points.get(cpt).y)/(float)(this.parcours.points.get(cpt + 1).x - this.parcours.points.get(cpt).x))*(x - this.parcours.points.get(cpt).x));
		/*on se donne une petite marge d'erreur*/
		int epsilon = this.fenetre.getHeight()/20;
		return !((Y > (y - y_size/(double)2 - epsilon)) && (Y < (y + y_size/(double)2 + epsilon)));
	}
	/**fonction calculant la vitesse qui évolue au cours du temps*/
	public double speed(int n){
		return Math.exp((n*Math.log(2))/1000);
	}

	/**setteur de la vue*/
	public void setVue(PanelFlappy v){
		this.vue = v;
	}
	/**setteur du parcours*/
	public void setParcours(Parcours p){
		this.parcours = p;
	}
	/**setteur de la fenêtre*/
	public void setFenetre(Fenetre fenetre){
		this.fenetre = fenetre;
	}
	/**Setteur de Avancer*/
	public void setAvancer(Avancer av){this.avancer = av;}
}
