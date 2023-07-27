import java.util.*;

public class DSLImportParser {
    static DSLImporter dSLImporter = new DSLImporterImpl();
    public static void main(String[] args) {
        if (args.length != 1) { // Accept only one input parameter
            System.out.println("It's expect path root program directory.");
            return;
        }
        String rootPath = args[0];
        String rootProgName = "root.prog";
        String rootProgPath = rootPath + "/" + rootProgName;
        if (!dSLImporter.isValid(rootPath)) return;
        Map<String, List<String>> importMaps = dSLImporter.prepareImportList(rootProgPath);
        dSLImporter.displayImportStructure(rootProgName, 4, importMaps);
    }
}
