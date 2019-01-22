package views;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.awt.*;
import java.util.Random;

public class MatrixDisplay extends Application {

    private GridPane root; // we will use it for displayng the city matrix
    @Override
    public void start(Stage primaryStage) throws Exception {
        int SIZE = 100; // size of the city


        displayTheCity(SIZE); 
        /** **/
        //To have dimensions of the screen then use the dimensions in the scene object
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double widthScreen = screenSize.getWidth();
        double heightScreen = screenSize.getHeight();
        //
        Scene scene = new Scene(root, widthScreen, heightScreen);
        primaryStage.setTitle("Random Binary Matrix (JavaFX)");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void displayTheCity(int sizeCity)
    {
        int length = sizeCity;
        int width = sizeCity;

         root = new GridPane();

        for(int y = 0; y < length; y++){
            for(int x = 0; x < width; x++){

                Random rand = new Random();
                int rand1 = rand.nextInt(2);

                // Create a new TextField in each Iteration

                TextField tf = new TextField();

                tf.setPrefHeight(50);
                tf.setPrefWidth(50);
                tf.setAlignment(Pos.CENTER);
                tf.setEditable(false);
                tf.setText("(" + rand1 + ")");
                if ( rand1 ==0)
                    tf.setStyle("-fx-background-color: green;");

                // Iterate the Index using the loops
                root.setRowIndex(tf,y);
                root.setColumnIndex(tf,x);
                root.getChildren().add(tf);
            }
        }

    }
}
