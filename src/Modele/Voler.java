package Modele;

import Controleur.*;
import Vue.*;

public class Voler extends Thread{
    /*attribut modele*/
    private final ModeleFlappy modele;
    /**constructeur*/
    public Voler(ModeleFlappy m){
        this.modele = m;
    }
    @Override
    public void run() {
        while(this.modele.theGameIsAlive){
            this.modele.moveDown();
            try {
                sleep(2L *ModeleFlappy.tic);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
