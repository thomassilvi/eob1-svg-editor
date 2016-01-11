package emeriss.org;

import java.io.File;
import java.io.FilenameFilter;

public class EobSavFileNameFilter implements FilenameFilter {

    @Override
    public boolean accept(File dir, String name) {
        return name.toUpperCase().endsWith(".SAV");
    }

}
