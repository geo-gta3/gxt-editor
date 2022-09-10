package ge.vakho.gxt.editor.controller;

import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AboutController implements Initializable {

    public ImageView aboutPageImageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try (var inputStream = AboutController.class.getResourceAsStream("/images/icon.png")) {
            aboutPageImageView.setImage(new Image(inputStream));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}