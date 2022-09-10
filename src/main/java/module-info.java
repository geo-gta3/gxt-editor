module ge.vakho.gxt.editor {
    requires javafx.controls;
    requires javafx.fxml;
    requires ge.vakho.gxt;

    exports ge.vakho.gxt.editor;
    opens ge.vakho.gxt.editor to javafx.fxml;

    exports ge.vakho.gxt.editor.controller;
    opens ge.vakho.gxt.editor.controller to javafx.fxml;

    exports ge.vakho.gxt.editor.model;
    opens ge.vakho.gxt.editor.model to javafx.fxml;
}