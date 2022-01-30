package Vue;

import java.awt.*;
import java.io.Serial;
import java.util.ArrayList;

import javax.swing.JPanel;

import Controleur.*;
import Modele.*;

/**class définissant la vue (étend JPanel)*/
public class PanelFlappy extends JPanel{
	/**Make Java Happy :) */
	@Serial
	private static final long serialVersionUID = 1L;
	
	/*modèle à afficher :*/
	private final ModeleFlappy modele;
	/*pointeur vers le parcours*/
	private Parcours parcours;
	/*pointeur vers Modele.Avancer*/
	public Avancer avancer;
	/*pointeur vers les oiseaux*/
	public VueOiseau oiseaux;
	/**constructeur*/
	public PanelFlappy(ModeleFlappy m) {
		this.modele = m;
		this.setPreferredSize(new Dimension(this.modele.width, this.modele.height));
		Controleur controleur = new Controleur(m, this);
		addMouseListener(controleur);
	}

	/**méthode pour redimensionner une dimension x en fonction de la fenêtre*/
	public int scaleToX(int x){
		return (int) (x/((double) this.modele.width)*this.modele.fenetre.getWidth());
	}
	/**méthode pour redimensionner une dimension y en fonction de la fenêtre*/
	public int scaleToY(int y){
		return (int) (y/((double) this.modele.height)*this.modele.fenetre.getHeight());
	}
	/**méthode de dessin appelé à chaques m.a.j. de l'Affichage */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		/*dessin des oiseaux*/
		if(this.oiseaux != null)
		this.oiseaux.dessiner(g);
		/*dessin de l'oval*/
		this.drawOvalBird(g);
		/*dessin du parcours*/
		this.drawParcours(g);
		/*dessin des tuyaux*/
		this.drawTuyaux(g);
	}
	/**fonction de dessin de l'oval*/
	private void drawOvalBird(Graphics g){
		g.setColor(new Color(255, 148, 204));
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(3L));
		g2d.drawOval(scaleToX(modele.x_oval),  scaleToY(modele.y_oval), scaleToX(modele.x_size), scaleToY(modele.y_size));
	}
	/**fonction de dessin du parcours*/
	private void drawParcours(Graphics g){
		g.setColor(new Color(244, 0, 44));
		Graphics2D g2d = (Graphics2D) g;
		Point p = this.parcours.points.get(0);
		double deca = this.avancer.decalage_global*this.modele.speed(this.avancer.decalage_global);
		ArrayList<Point> tmp = (ArrayList<Point>) this.parcours.points.clone();
		for(Point e : tmp){
			int x1 = scaleToX((int) (p.x - deca));
			int x2 = scaleToX((int) (e.x - deca));

			int y1 = scaleToY(p.y);
			int y2 = scaleToY(e.y );
			g2d.setStroke(new BasicStroke(3L));
			g2d.drawLine(x1, y1, x2, y2);
			p = e;
		}
	}
	/**fonction de dessin des tuyaux*/
	private void drawTuyaux(Graphics g){
		int rectWidth = 100;
		int rectHeight = 50;
		int rectwidth = 60;
		int delta = (int) (this.modele.y_size/(double)2 + this.modele.fenetre.getHeight()/20);
		double deca = this.avancer.decalage_global*this.modele.speed(this.avancer.decalage_global);
		Graphics2D g2d = (Graphics2D) g;
		ArrayList<Point> tmp = (ArrayList<Point>) this.parcours.points.clone();
		for(Point e : tmp){
			/*déclaration des variables coordonnées*/
			int x1 = (int) (e.x - rectWidth/2 - deca);
			int y11 = e.y + delta;
			int y12 = e.y - delta - rectHeight;
			int x2 = (int) ( e.x - rectwidth/2 - deca);
			int y21 = e.y - delta - rectHeight;
			int y22 = e.y + delta +rectHeight;
			/*dessin de l'intérieur des tuyaux en vert clair*/
			g2d.setColor(new Color(0, 255, 0));
			g2d.fillRect(scaleToX(x1), scaleToY(y11), scaleToX(rectWidth), scaleToY(rectHeight));
			g2d.fillRect(scaleToX(x1), scaleToY(y12), scaleToX(rectWidth), scaleToY(rectHeight));
			int t1 = e.y - delta - rectHeight;
			if(scaleToY(t1) > 0){
				g2d.fillRect(scaleToX(x2), 0, scaleToX(rectwidth), scaleToY(y21));
			}
			if(scaleToY(e.y + delta + rectHeight) < this.modele.fenetre.getHeight() ){
				g2d.fillRect(scaleToX(x2),scaleToY(y22) , scaleToX(rectwidth), this.modele.fenetre.getHeight());
			}
			/*dessin du countour des tuyaux en vert foncé*/
			g2d.setColor(new Color(42, 151, 35));
			g2d.drawRect(scaleToX(x1), scaleToY(y11), scaleToX(rectWidth), scaleToY(rectHeight));
			g2d.drawRect(scaleToX(x1), scaleToY(y12), scaleToX(rectWidth), scaleToY(rectHeight));
			if(scaleToY(e.y - delta - rectHeight) > 0){
				g2d.drawRect(scaleToX(x2), 0, scaleToX(rectwidth), scaleToY(y21));
			}
			if(scaleToY(e.y + delta + rectHeight) < this.modele.fenetre.getHeight() ){
				g2d.drawRect(scaleToX(x2),scaleToY(y22) , scaleToX(rectwidth), this.modele.fenetre.getHeight());
			}
		}
	}
	/**Setteur du parcours*/
	public void setParcours(Parcours p){
		this.parcours = p;
	}
	/**Setteur de avancer*/
	public void setAvancer(Avancer av){this.avancer = av;}
	/**setter de oiseaux*/
	public void setOiseaux(VueOiseau vo) {
		this.oiseaux = vo;
	}

}
