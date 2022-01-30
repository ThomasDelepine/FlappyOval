package Main;
import Controleur.*;
import Modele.*;
import Vue.*;

/** Cette classe lance le programme */
public class Main {
	/**fonction main appelée en premier lors de l'exécution du programme*/
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		/*création d'une fenêtre*/
		Fenetre fenetre = new Fenetre("Flappy Oval");
		/*création du modèle :*/
		ModeleFlappy modele = new ModeleFlappy(fenetre);
		/*création de la vue :*/
		PanelFlappy vue = new PanelFlappy(modele);
		fenetre.addPanel(vue);
		/*création du controleur :*/
		Controleur controleur = new Controleur(modele, vue);
		/*setting de la vue dans le modèle*/
		modele.setVue(vue);
		/*création du vol*/
		Voler vole = new Voler(modele);
		/*création du parcours*/
		Parcours parcours = new Parcours(modele);
		modele.setParcours(parcours);
		vue.setParcours(parcours);
		/*création de l'avancement du parcours*/
		Avancer av = new Avancer(parcours, vue, fenetre);
		modele.setAvancer(av);
		vue.setAvancer(av);
		/*création de la vérification du parcours de l'ovalz*/
		GameOver go = new GameOver(modele);

		/*création du décors : oiseaux volants*/
		VueOiseau vo = new VueOiseau(modele);
		vue.setOiseaux(vo);

		/*création de l'objet de repaint*/
		Repaint rp = new Repaint(vue, modele);
		av.setRp(rp);




		/*démarrage des threads :*/
		vole.start();
		av.start();
		rp.start();
		go.start();
	}
}
