package omis.media.io.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import omis.io.FileLocator;
import omis.media.domain.Photo;
import omis.media.io.PhotoPersister;

/**
 * Simple implementation of photo persister.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 11, 2013)
 * @since OMIS 3.0
 */
public class SimplePhotoPersisterImpl implements PhotoPersister {

	private final FileLocator fileLocator;

	/**
	 * Instantiates a simple implementation of photo persister.
	 * 
	 * @param fileLocator locator used to find file
	 */
	public SimplePhotoPersisterImpl(final FileLocator fileLocator) {
		this.fileLocator = fileLocator;
	}
	
	/** {@inheritDoc} */
	@Override
	public File persist(final Photo photo, final byte[] data) {
		return this.persist(photo.getFilename(), data);
	}
	
	// Persist by filename
	private File persist(final String filename, final byte[] data) {
		File file = this.fileLocator.locate(filename, false);
		if (file == null) {
			throw new IllegalArgumentException("Cannot write to directory");
		}
		boolean isNew = !file.exists();
		BufferedOutputStream buffer = null;
		try {
			buffer = new BufferedOutputStream(new FileOutputStream(file));
			buffer.write(data);
		} catch (IOException ioe) {
			if (isNew) {
				file.delete();
			}
			throw new RuntimeException(
					"Error writing to file: " + file.getName(), ioe);
		} finally {
			if (buffer != null) {
				try {
					buffer.close();
				} catch (IOException ioe) {
					throw new RuntimeException(
							"Error closing file: " + file.getName(), ioe);
				}
			}
		}
		return file;
	}
}