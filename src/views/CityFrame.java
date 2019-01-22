package views;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class CityFrame extends Application{

    public static void main(String[] args)
    {
        launch(args); // Will call javaFx constructor which will call start methode

    }


    @Override
    public void start(Stage primaryStage) throws Exception {
       //Adding button named Click on me
        Button button = new Button("Click on me ");
        //Adding  an action on this Button
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("hello world");
            }
        });
     // This is our Layout !
        StackPane root = new StackPane();
        root.getChildren().add(button);
        //Scene with size width and height
        Scene scene=new Scene(root,500,500);

        // JavaFX simple Window
        primaryStage.setScene(scene);
         primaryStage.show();

    }
}
