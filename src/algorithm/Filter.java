package algorithm;

import java.io.File;
import java.io.FileFilter;
/**
 * This class implements the FileFilter interface to filer files ending with .csv
 * @author David&Evegeny
 *
 */
public final class Filter implements FileFilter {

	@Override
	public boolean accept(File pathname) {
		if(pathname.getPath().endsWith(".csv"))return true;
		return false;
	}

}
