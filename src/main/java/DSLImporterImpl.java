import model.DSLFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DSLImporterImpl implements DSLImporter {
    StringBuilder importTree = new StringBuilder();

    @Override
    public boolean isValid(String rootPath) {
        if (!(new File(rootPath).isDirectory())) {
            System.out.println("It's expect root.prog directory path.");
            return false;
        }
        if (!(new File(rootPath + "/root.prog").exists())) {
            System.out.println("it doesn't exist for root.prog file in provided path.");
            return false;
        }
        return true;
    }

    @Override
    public StringBuilder prepareImportTree(DSLFile dslFile, Set<Object> visitedFiles) {
        importTree.append(getIndentation(dslFile.getIndentSize())).append(dslFile.getFileName());
        importTree.append("\n");
        // Check file context already read
        if (!visitedFiles.add(dslFile.getFileName())) {
            return importTree;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(dslFile.getFullPath()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().startsWith("import")) {
                    String importedFile = String.join("/", line.split("/")).replace("import ./", "").replace(";", "");
                    String[] filePathArray = importedFile.split("/");
                    prepareImportTree(new DSLFile(
                            filePathArray[filePathArray.length - 1],// FileName
                            dslFile.getDirectoryPath() + "/" + importedFile,// Full path
                            dslFile.getDirectoryPath(),//Input directory path
                            dslFile.getIndentSize() + 4//Structure indent size
                    ), visitedFiles);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return importTree;
    }

    @Override
    public void displayImportStructure(StringBuilder importTree) {
        System.out.println(importTree);
    }

    private String getIndentation(int indent) {
        return " ".repeat(Math.max(0, indent));
    }
}
