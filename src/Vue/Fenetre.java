package Vue;

import javax.swing.JFrame;
import java.awt.*;
import java.io.Serial;

import Controleur.*;
import Modele.*;

/** Cette classe crée une fenêtre avec un "Affichage" dedans */
public class Fenetre extends JFrame {
	
	/**Make Java Happy :)*/
	@Serial
	private static final long serialVersionUID = 1L;
	/*attribus :*/
	public PanelFlappy vue;

	/**constructeur de la classe Fenêtre*/
	public Fenetre(String s){
		this.setTitle(s);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(new Dimension(800, 500));
	}
	public void addPanel(PanelFlappy v){
		/*l'affichage :*/
		this.vue = v;
		/* insertion de l'affichage :*/
		this.add(vue);

		/* assemblage */
		this.pack();
		this.setVisible(true);
	}
}
