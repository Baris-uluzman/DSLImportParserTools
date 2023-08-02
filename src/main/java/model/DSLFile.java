package model;


public class DSLFile {
    private final String fileName;
    private final String fullPath;
    private final String directoryPath;
    private final int indentSize;

    public String getFileName() {
        return fileName;
    }

    public String getFullPath() {
        return fullPath;
    }

    public String getDirectoryPath() {
        return directoryPath;
    }

    public int getIndentSize() {
        return indentSize;
    }

    public DSLFile(String fileName, String fullPath, String directoryPath, int indentSize) {
        this.fileName = fileName;
        this.fullPath = fullPath;
        this.directoryPath = directoryPath;
        this.indentSize = indentSize;
    }

}
