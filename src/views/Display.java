package views;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Display extends JPanel {
        protected JFrame frame;
    protected Canvas canvas;
    //protected JPanel p;
    protected BufferStrategy bs;
    protected Graphics g;
    protected String title;
    protected JPanel principal; //le panel principal
    private JButton btAdd;
    Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    int height = (int)dimension.getHeight();
    int width  = (int)dimension.getWidth();

    public Display(String title)
    {
        this.title=title;
		/*this.width=width;
		this.height=height;*/
        createDisplays();
    }

    public JFrame getJFrame() {
        return frame;
    }
    public void Fermer_fenetre() {
        frame.dispose();
    }

    private void createDisplays() {

        frame=new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        //frame.getContentPane().setBackground(Color.BLACK);

        principal=new JPanel();
        //frame.setContentPane(principal);
        //frame.setVisible(true);les 2 methodes je les mis dans GameView , Pour résoudre le truc de panel GETWIDTH

        //Mettre la frame en plein ecran
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);




    }

}
