package ge.vakho.gxt.editor;

import ge.vakho.gxt.editor.controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class GXTEditor extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent aboutNode = loadAboutNode();
        Parent mainNode = loadMainNode(stage, aboutNode);
        Scene mainScene = new Scene(mainNode, 640, 400);

        addIcon(stage);
        stage.setScene(mainScene);
        stage.show();
    }

    private Parent loadAboutNode() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        try (var inputStream = GXTEditor.class.getResourceAsStream("/views/about.fxml")) {
            return fxmlLoader.load(inputStream);
        }
    }

    private Parent loadMainNode(Stage stage, Parent aboutNode) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent parent;
        try (var inputStream = GXTEditor.class.getResourceAsStream("/views/main.fxml")) {
            parent = fxmlLoader.load(inputStream);
        }
        MainController controller = fxmlLoader.getController();
        controller.initStage(stage);
        controller.initAboutAlert(aboutNode);
        return parent;
    }

    private void addIcon(Stage stage) throws IOException {
        try (var inputStream = GXTEditor.class.getResourceAsStream("/images/icon.png")) {
            stage.getIcons().add(new Image(inputStream));
        }
    }

    public static void main(String[] args) {
        launch();
    }

}