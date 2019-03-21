package views;

import javax.swing.*;
import java.awt.*;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.CityController;

import static javax.imageio.ImageIO.read;

public class CityFrame extends JFrame{

    private  CityController cityController;

    public void setCityController(CityController cityController) {

        this.cityController = cityController;
    }

    public ImageIcon addingImageToAbutton(String path){
        // read an image and add it to a button
        BufferedImage img = null;
        try {
            img = read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(29, 29,
                Image.SCALE_SMOOTH);

        return new ImageIcon(dimg);

    }

    Grille g = new Grille(15,15);

    public CityFrame(CityController cityController){

        this.cityController=cityController;
        this.setTitle("Tron");
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);

        //Instanciation d'un objet JPanel
        JPanel pan = new JPanel();

        //DÃ©finition de sa couleur de fond
        pan.setBackground(Color.BLACK);

        //On prÃ©vient notre JFrame que notre JPanel sera son content pane
        this.setContentPane(pan);

        //Trois lignes sur deux colonnes
        this.setLayout(new GridLayout(15,15));

        //Instanciation de la matrice
        g.addEntree(0, 0);
        int matrice[][] = g.getMatrice();
        for(int i = 0; i< matrice.length; i++){
            for(int j = 0; j < matrice.length; j++){
                JButton b = new JButton();
                b.setEnabled(false);
                this.getContentPane().add(b);
                b.setSize(30,30);
                b.setText(matrice[i][j] + "");
            }
        }

        //On affiche la fenetre
        this.setVisible(true);
    }
    public static void main(String []args)
    {
        new CityFrame(new CityController("Manathan.txt"));
    }


}