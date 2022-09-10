package ge.vakho.gxt.editor.model;

public enum SearchType {
    SEARCH_BY_KEY("Search by key"),
    SEARCH_BY_VALUE("Search by value");

    private final String readableName;

    SearchType(String readableName) {
        this.readableName = readableName;
    }

    @Override
    public String toString() {
        return readableName;
    }

}
