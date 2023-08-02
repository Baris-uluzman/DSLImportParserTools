import model.DSLFile;
import org.junit.Test;

import java.util.HashSet;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class DSLImportParserTest {

    private final DSLImporter dSLImporter = new DSLImporterImpl();
    private final String rootPathExample3 = "src/test/resources/example3";
    private String rootProgName = "root.prog";
    private final HashSet<Object> importMap = new HashSet<>();

    @Test
    public void shouldValidRootPath() {
        assertTrue(dSLImporter.isValid(rootPathExample3));
    }

    @Test
    public void shouldImportListFromRootProg() {
        String rootPathExample4 = "src/test/resources/example4";
        StringBuilder importTree = dSLImporter.prepareImportTree(new DSLFile(
                rootProgName,
                rootPathExample4 + "/" + rootProgName,
                rootPathExample4,
                0
        ), importMap);
        StringBuilder expectedImportTree = prepareImportTreeBuilder();
        assertEquals(expectedImportTree.toString(), importTree.toString().replaceAll("\n", ""));
    }

    @Test
    public void shouldProcessEndToEnd() {
        assertTrue(dSLImporter.isValid(rootPathExample3));
        StringBuilder importTree = dSLImporter.prepareImportTree(new DSLFile(
                rootProgName,
                rootPathExample3 + "/" + rootProgName,
                rootPathExample3,
                0
        ), importMap);
        dSLImporter.displayImportStructure(importTree);
    }

    private StringBuilder prepareImportTreeBuilder() {
        StringBuilder importTree = new StringBuilder();
        importTree.append("root.prog");
        importTree.append("    ex1.lib");
        importTree.append("        ex2.lib");
        importTree.append("            ex3.lib");
        importTree.append("    ex5.lib");
        importTree.append("        ex6.lib");
        importTree.append("            ex3.lib");
        importTree.append("        ex2.lib");
        return importTree;
    }
}