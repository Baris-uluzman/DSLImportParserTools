import model.DSLFile;

import java.util.*;

public class DSLImportParser {
    static DSLImporter dSLImporter = new DSLImporterImpl();
    static HashSet<Object> importMap = new HashSet<>();

    public static void main(String[] args) {
        if (args.length != 1) { // Accept only one input parameter
            System.out.println("It's expect path root program directory.");
            return;
        }
        String rootPath = args[0];
        if (!dSLImporter.isValid(rootPath)) return;
        String rootFile = "root.prog";
        StringBuilder importTree = dSLImporter.prepareImportTree(new DSLFile(
                rootFile,
                rootPath + "/" + rootFile,
                rootPath,
                0
        ), importMap);
        dSLImporter.displayImportStructure(importTree);
    }
}
