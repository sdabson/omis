package omis.io.impl;

import java.io.File;

import omis.io.FileLocator;

/**
 * Implementation of file locator that locates a file from a path.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 8, 2013)
 * @since OMIS 3.0
 */
public class FileFromPathLocatorImpl
		implements FileLocator {

	private final String path;
	
	/**
	 * Instantiates an imlementation of default file from path locator.
	 * 
	 * @param path path
	 */
	public FileFromPathLocatorImpl(final String path) {
		this.path = path;
	}
	
	/** {@inheritDoc} */
	@Override
	public File locate(final String filename, final boolean mustExist) {
		String actualFilename;
		if (this.path == null) {
			actualFilename = "";
		} else {
			if (!this.path.endsWith(File.separator)) {
				actualFilename = this.path + File.separator + filename;
			} else {
				actualFilename = this.path + filename;
			}
		}
		File file = new File(actualFilename);
		if ((!file.exists() && mustExist) || file.isDirectory()) {
			return null;
		} else {
			return file;
		}
	}
}