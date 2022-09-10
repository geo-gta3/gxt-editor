package ge.vakho.gxt.editor.controller;

import ge.vakho.gxt.GXT;
import ge.vakho.gxt.editor.model.Entry;
import ge.vakho.gxt.editor.model.SearchType;
import ge.vakho.gxt.editor.model.State;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private Stage stage;
    private Alert aboutAlert;

    private State state;
    private FileChooser fileChooser;

    public MenuItem menuItemSave;
    public MenuItem menuItemSaveAs;
    public TableView<Entry> tableView;
    public TableColumn<Entry, String> keyColumn;
    public TableColumn<Entry, String> valueColumn;

    public void initStage(Stage stage) {
        this.stage = stage;
        stage.titleProperty().bindBidirectional(
                state.pathProperty(),
                new State.TitleConverter()
        );
    }

    public void initAboutAlert(Parent aboutNode) {
        aboutAlert = new Alert(Alert.AlertType.NONE);
        aboutAlert.setTitle("About GXT Editor");
        aboutAlert.getDialogPane().setContent(aboutNode);
        aboutAlert.getButtonTypes().addAll(ButtonType.CLOSE);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        state = new State();

        tableView.setItems(state.getEntries());
        keyColumn.setCellValueFactory(new PropertyValueFactory<>("key"));
        keyColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        valueColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        // Property bindings
        menuItemSaveAs.disableProperty().bind(state.pathProperty().isNull());

        // File chooser
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("GXT Files", "*.gxt")
        );
    }

    public void onNew(ActionEvent event) {
        state.clear();
    }

    public void onOpen(ActionEvent event) throws IOException {
        fileChooser.setTitle("Open GXT File");
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null && !selectedFile.isDirectory()) {
            fileChooser.setInitialDirectory(selectedFile.getParentFile());
            state.setPath(selectedFile.toPath());
            state.setEntriesFromMap(GXT.from(state.getPath()).toMap());
        }
    }

    public void onSave(ActionEvent event) throws IOException {
        if (state.getPath() == null) {
            fileChooser.setTitle("Save GXT File");
            File selectedFile = fileChooser.showSaveDialog(stage);
            if (selectedFile != null) {
                fileChooser.setInitialDirectory(selectedFile.getParentFile());
                state.setPath(selectedFile.toPath());
            }
        }
        if (state.getPath() != null) {
            GXT.from(state.getEntriesAsMap()).writeTo(state.getPath());
        }
    }

    public void onSaveAs(ActionEvent event) throws IOException {
        fileChooser.setTitle("Save File");
        File selectedFile = fileChooser.showSaveDialog(stage);
        if (selectedFile != null) {
            fileChooser.setInitialDirectory(selectedFile.getParentFile());
            state.setPath(selectedFile.toPath());
            GXT.from(state.getEntriesAsMap()).writeTo(state.getPath());
        }
    }

    public void onClose(ActionEvent event) {
        Platform.exit();
    }

    public void onAbout(ActionEvent event) {
        aboutAlert.showAndWait();
    }

    public void onSearch(ActionEvent event) {

    }


}