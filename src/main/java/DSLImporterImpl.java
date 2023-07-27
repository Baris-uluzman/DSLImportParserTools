import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DSLImporterImpl implements DSLImporter {
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
    public Map<String, List<String>> prepareImportList(String filePath) {
        // TreeMap keeps all elements in sorted order
        Map<String, List<String>> importMap = new TreeMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // interested text 'import' and should contain '.lib' text
                if (line.trim().startsWith("import ") && line.contains(".lib")) {
                    String importedFile = line.trim().replace("import ", "");
                    if (importedFile.endsWith(";")) {
                        importedFile = importedFile.substring(0, importedFile.length() - 1);
                    }
                    String[] parts = importedFile.split("/");
                    String parentPath = "";
                    for (int i = 0; i < parts.length - 1; i++) {
                        parentPath += parts[i] + "/";
                    }
                    String fileName = parts[parts.length - 1];
                    if (!importMap.containsKey(parentPath)) {
                        importMap.put(parentPath, new ArrayList<>());
                    }
                    importMap.get(parentPath).add(fileName);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return importMap;
    }

    @Override
    public void displayImportStructure(String progName, int indentSize, Map<String, List<String>> importMap) {
        System.out.println(progName);
        int level = 1;
        for (Map.Entry<String, List<String>> entry : importMap.entrySet()) {
            String indent = " ".repeat(indentSize * level);
            importMap.get(entry.getKey()).forEach((value) -> System.out.println(indent + value));
            level += 1;
        }
    }
}
