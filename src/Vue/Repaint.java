package Vue;

/*classe mettant à jour l'affichage, rendant pljus fluide ce dernier*/

import Modele.ModeleFlappy;

public class Repaint extends Thread{
    /*booléen controlant les repaint*/
    private boolean repaint = true;
    /*pointeur vers la vue*/
    PanelFlappy vue;
    /*pointeur vers le modèle*/
    ModeleFlappy modele;

    /**constructeur*/
    public Repaint(PanelFlappy vue, ModeleFlappy m){
        this.vue = vue;
        this.modele = m;
    }

    @Override
    public void run(){
        while(true) {
            if (this.repaint) {
                this.vue.revalidate();
                this.vue.repaint();
            }
        }
    }
    /**fonction demandant un refresh de l'affichage*/
    public void ping(){
        this.repaint = true;
    }
}
