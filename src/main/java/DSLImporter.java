import model.DSLFile;

import java.util.Set;

public interface DSLImporter {
    boolean isValid(String rootPath);

    StringBuilder prepareImportTree(DSLFile dslFile, Set<Object> visitedFiles);

    void displayImportStructure(StringBuilder importTree);
}
