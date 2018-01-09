package omis.io.impl;

import java.io.File;

import omis.io.FileLocator;
import omis.io.FileRemover;

/**
 * Simple implementation of file remover.
 * 
 * @author Stephen Abson
 * @author Joel Norris
 * @version 0.1.1 (Dec 8, 2016)
 * @since OMIS 3.0
 */
public class SimpleFileRemoverImpl
		implements FileRemover {

	private final FileLocator fileLocator;

	/**
	 * Instantiates a simple implementation of file remover.
	 * 
	 * @param fileLocator file locator
	 */
	public SimpleFileRemoverImpl(final FileLocator fileLocator) {
		this.fileLocator = fileLocator;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean remove(final String filename) {
		File file = this.fileLocator.locate(filename, true);
		if (file != null) {
			return file.delete();
		} else {
			return false;
		}
	}
}