package Modele;
/**cette classe a pour but de repérer les sorties de l'oval du trajet*/
public class GameOver extends Thread{
    /*pointeur vers le modèle*/
    public ModeleFlappy modele;
    /**constructeur*/
    public GameOver(ModeleFlappy m){this.modele = m;}
    @Override
    public void run(){
        while(true){
            /*vérirication des collisions :*/
            if(this.modele.testPerdu()){
                this.modele.theGameIsAlive = false;
            }
            try {
                sleep(ModeleFlappy.tic* 4L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
