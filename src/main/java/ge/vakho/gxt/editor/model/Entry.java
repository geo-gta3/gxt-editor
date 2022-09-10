package ge.vakho.gxt.editor.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Entry {

    private StringProperty key = new SimpleStringProperty(this, "key");
    private StringProperty value = new SimpleStringProperty(this, "value");

    public Entry() {
    }

    public Entry(String firstName, String lastName) {
        setKey(firstName);
        setValue(lastName);
    }

    public void setKey(String value) {
        keyProperty().set(value);
    }

    public String getKey() {
        return keyProperty().get();
    }

    public StringProperty keyProperty() {
        return key;
    }

    public void setValue(String value) {
        valueProperty().set(value);
    }

    public String getValue() {
        return valueProperty().get();
    }

    public StringProperty valueProperty() {
        return value;
    }

}
