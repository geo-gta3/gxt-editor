package ge.vakho.gxt.editor.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.StringConverter;

import java.nio.file.Path;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class State {

    private ObjectProperty<Path> path = new SimpleObjectProperty<>();
    private ObservableList<Entry> entries = FXCollections.observableArrayList();

    public Path getPath() {
        return path.get();
    }

    public ObjectProperty<Path> pathProperty() {
        return path;
    }

    public void setPath(Path path) {
        this.path.set(path);
    }

    public ObservableList<Entry> getEntries() {
        return entries;
    }

    public void setEntries(ObservableList<Entry> entries) {
        this.entries = entries;
    }

    public void clear() {
        setPath(null);
        this.entries.clear();
    }

    public void setEntriesFromMap(Map<String, String> map) {
        entries.addAll(
                map.entrySet().stream()
                        .map(i -> new Entry(i.getKey(), i.getValue()))
                        .toList()
        );
    }

    public Map<String, String> getEntriesAsMap() {
        return new TreeMap<>(entries.stream().collect(Collectors.toMap(Entry::getKey, Entry::getValue)));
    }

    public static class TitleConverter extends StringConverter<Path> {
        @Override
        public String toString(Path path) {
            if (path == null) {
                return "Untitled - GXT Editor";
            }
            return path.getFileName().toString() + " - GXT Editor";
        }

        @Override
        public Path fromString(String title) {
            throw new RuntimeException("This method should never be called!");
        }
    }

}
