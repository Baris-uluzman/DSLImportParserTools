import java.util.List;
import java.util.Map;

public interface DSLImporter {
    boolean isValid(String rootPath);

    Map<String, List<String>> prepareImportList(String filePath);

    void displayImportStructure(String progName, int indentSize, Map<String, List<String>> importMap);
}
