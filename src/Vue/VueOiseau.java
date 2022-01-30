package Vue;

import Modele.ModeleFlappy;
import org.w3c.dom.ls.LSOutput;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**classe stockant les oiseaux et les affichant*/

public class VueOiseau {
    /*liste d'oiseaux*/
    public ArrayList<Oiseau> oiseaux;
    /*pointeur vers le modèle*/
    public ModeleFlappy modele;
    /**constructeur*/
    public VueOiseau(ModeleFlappy m){
        this.modele = m;
        this.oiseaux = new ArrayList<>();
    };
    /**fonction ajoutant un oiseau à VueOiseau*/
    public void addOiseau(Oiseau oi){
        this.oiseaux.add(oi);
    }
    /**fonction d'affichage des oiseaux*/
    public void dessiner(Graphics g){
        ArrayList<Oiseau> tmp = (ArrayList<Oiseau>) this.oiseaux.clone();
        for (Oiseau oiseau : tmp) {
            if(oiseau.getOut()){
                this.oiseaux.remove(oiseau);
            }
            BufferedImage im = null;
            try {
                im = ImageIO.read(new File("gif/frame-" + (oiseau.getEtat() + 1) + ".png"));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            assert im != null;
            /*On affiche une image rescale de telle sorte que si un oiseau est rapide, on le voit gros (il est proche), et inversement s'il est lent.
             * C'est juste une approximation arbitraire, il y a sans doutes des équations plus précises
             * Par ailleur on ajuste la taille de l'oiseau en fonction de la taille de la fenêtre
             * La taille de base de 50 pixels est arbitraire.*/

            /*resize en fonction de la vitesse*/
            int x_resize = 50 * Oiseau.minDelai / oiseau.getDelai();
            int y_resize = 50 * Oiseau.minDelai / oiseau.getDelai();
            /*resize en fonction de la fenêtre*/
            int x_Fenetre = (int) ((x_resize) / ((float) this.modele.width) * this.modele.fenetre.getWidth());
            int y_Fenetre = (int) ((y_resize) / ((float) this.modele.height) * this.modele.fenetre.getHeight());
            /*dessin final de l'image*/
            g.drawImage(im.getScaledInstance(x_Fenetre, y_Fenetre, 10), (int) ((oiseau.getPosition()) / ((float) this.modele.width) * this.modele.fenetre.getWidth()), (int) ((oiseau.getHauteur()) / ((float) this.modele.height) * this.modele.fenetre.getHeight()), null);
        }
    }
}
