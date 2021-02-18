package sample;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Dictionary;
import java.util.Hashtable;

public class Controller {

    @FXML
    private ComboBox<String> source,target;

    @FXML
    private TextField sourceField,targetField;

    @FXML
    private ImageView exit,menu,close;

    @FXML
    private AnchorPane slider,pane;

    @FXML
    private void initialize() {

        try {

            exit.setOnMouseClicked(mouseEvent -> {
                System.exit(0);
            });

            close.setVisible(false);
            slider.setTranslateX(-175);
            pane.setTranslateX(-175);

            menu.setOnMouseClicked(mouseEvent -> {

                TranslateTransition slide = new TranslateTransition();
                TranslateTransition panel = new TranslateTransition();
                slide.setDuration(Duration.seconds(0.4));
                panel.setDuration(Duration.seconds(0.4));
                slide.setNode(slider);
                panel.setNode(pane);

                slide.setToX(0);
                slide.play();
                panel.setToX(0);
                panel.play();
                pane.setBlendMode(BlendMode.RED);

                slider.setTranslateX(-175);
                pane.setTranslateX(-175);
                slide.setOnFinished(event -> {
                    menu.setVisible(false);
                    close.setVisible(true);
                });

            });

            close.setOnMouseClicked(mouseEvent -> {

                TranslateTransition slide = new TranslateTransition();
                TranslateTransition panel = new TranslateTransition();
                slide.setDuration(Duration.seconds(0.4));
                panel.setDuration(Duration.seconds(0.4));
                slide.setNode(slider);
                panel.setNode(pane);

                slide.setToX(-175);
                slide.play();
                panel.setToX(-175);
                panel.play();
                pane.setBlendMode(BlendMode.SRC_OVER);

                slider.setTranslateX(0);
                pane.setTranslateX(20);
                slide.setOnFinished(event -> {
                    menu.setVisible(true);
                    close.setVisible(false);
                });

            });

            ObservableList<String> items= FXCollections.observableArrayList(
                    "English",
                    "Korean",
                    "Hindi",
                    "Japanese",
                    "Spanish",
                    "Chinese",
                    "French");



            source.setItems(items);
            target.setItems(items);

        }
        catch (Exception ex){
            ex.printStackTrace();
        }

    }

    @FXML
    private void translator() {


        Dictionary dic =new Hashtable();

        dic.put("English","en");
        dic.put("Korean","ko");
        dic.put("Hindi","hi");
        dic.put("Japanese","ja");
        dic.put("Spanish","es");
        dic.put("Chinese","zh-CN");
        dic.put("French","fr");




       HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://google-translate1.p.rapidapi.com/language/translate/v2"))
                .header("content-type", "application/x-www-form-urlencoded")
                .header("accept-encoding", "application/gzip")
                .header("x-rapidapi-key", "795c85050cmsha505ac32196eec7p19ce50jsn56b857f3bc19")
                .header("x-rapidapi-host", "google-translate1.p.rapidapi.com")
                .method("POST", HttpRequest.BodyPublishers.ofString("q="+sourceField.getText().toString()+"&source="+dic.get(source.getValue())+"&target="+dic.get(target.getValue())))
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String[] firstHalf = response.body().split(":");
        String[] secondHalf = firstHalf[3].split("}");
        System.out.println(secondHalf[0]);
        targetField.setText(secondHalf[0].replace("\"",""));

    }


        }

