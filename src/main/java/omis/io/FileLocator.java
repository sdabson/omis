package omis.io;

import java.io.File;

/**
 * Locates a file using a file name.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 8, 2013)
 * @since OMIS 3.0
 */
public interface FileLocator {

	/**
	 * Returns a file using the given filename.
	 * @param filename filename of file to locate
	 * @param mustExist enforces that the file exists
	 * @return file located using filename; {@code null} if the file
	 * does not exist and {@code mustExist} is {@code true} or is a directory
	 */
	File locate(String filename, boolean mustExist);
}