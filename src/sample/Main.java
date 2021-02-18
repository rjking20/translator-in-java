package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    private double x=0,y=0;


    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        root.setOnMousePressed(event ->{
            x=primaryStage.getX()-event.getScreenX();
            y=primaryStage.getY()-event.getSceneY();
        });
        root.setOnMouseDragged(event->{
            primaryStage.setX(event.getScreenX()+x);
            primaryStage.setY(event.getScreenY()+y);
        });



        primaryStage.setScene(new Scene(root,820,553));
        primaryStage.setTitle("Translator");
        primaryStage.initStyle(StageStyle.TRANSPARENT);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
