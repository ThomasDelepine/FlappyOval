package Controleur;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Modele.*;
import Vue.*;

/**class controlant le modèle*/

public class Controleur implements MouseListener{
	
	/*modèle :*/
	private final ModeleFlappy modele;
	/*vue (étend JPanel)*/
	private final PanelFlappy vue;
	
	/**constructeur*/
	public Controleur(ModeleFlappy m, PanelFlappy v) {
		this.modele = m;
		this.vue = v;
	}
	/**définition de méthode de MouseListener opérant un saut*/
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
			this.modele.jump();
			this.vue.repaint();
	}
	/**définition de méthode de MouseListener*/
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	/**définition de méthode de MouseListener*/
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	/**définition de méthode de MouseListener*/
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	/**définition de méthode de MouseListener*/
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}
