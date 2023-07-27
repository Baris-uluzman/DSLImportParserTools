import org.junit.Test;

import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class DSLImportParserTest {

    private DSLImporter dSLImporter = new DSLImporterImpl();
    private String rootPath = "src/test/resources/example3";
    private String rootProgName = "root.prog";
    private String rootProgPath = rootPath + "/" + rootProgName;

    @Test
    public void shouldValidRootPath() {
        assertTrue(dSLImporter.isValid(rootPath));
    }

    @Test
    public void shouldImportListFromRootProg() {
        Map<String, List<String>> importMaps = dSLImporter.prepareImportList(rootProgPath);
        String expectedImportList = "{./=[ex2.lib, ex1.lib], ./subfolder1/=[ex3.lib, ex4.lib], ./subfolder1/subfolder2/=[ex4.lib, ex5.lib], ./subfolder1/subfolder2/subfolder3/=[ex5.lib, ex5.lib]}";
        assertEquals(expectedImportList, importMaps.toString());
    }

    @Test
    public void shouldProcessEndToEnd() {
        assertTrue(dSLImporter.isValid(rootPath));
        Map<String, List<String>> importMaps = dSLImporter.prepareImportList(rootProgPath);
        dSLImporter.displayImportStructure(rootProgName, 4, importMaps);
    }

}