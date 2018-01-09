package omis.media.io.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import omis.io.FileLocator;
import omis.media.domain.Photo;
import omis.media.io.PhotoRetriever;

/**
 * Simple implementation of photo retriever.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 8, 2013)
 * @since OMIS 3.0
 */
public class SimplePhotoRetrieverImpl
		implements PhotoRetriever {

	private final FileLocator fileLocator;
	
	private final String notFoundFilename;

	/**
	 * Instantiates a simple implementation of photo retriever.
	 * 
	 * @param fileLocator locator used to find file
	 */
	public SimplePhotoRetrieverImpl(final FileLocator fileLocator,
			final String defaultFilename) {
		this.fileLocator = fileLocator;
		this.notFoundFilename = defaultFilename;
	}
	
	/** {@inheritDoc} */
	@Override
	public byte[] retrieve(final Photo photo) {
		return this.retrieve(photo.getFilename());
	}

	/** {@inheritDoc} */
	@Override
	public byte[] retrieve(final String filename) {
		File file = this.fileLocator.locate(filename, true);
		if (file == null
				&& (this.notFoundFilename != null
					&& this.notFoundFilename.length() > 0)) {
			file = this.fileLocator.locate(this.notFoundFilename, true);
		}
		if (file != null) {
			BufferedInputStream buffer = null;
			try {
				buffer = new BufferedInputStream(new FileInputStream(file));
				byte[] bytes = new byte[buffer.available()];
				buffer.read(bytes);
				return bytes;
			} catch (IOException ioe) {
				throw new RuntimeException(
						"Error opening file: " + file.getName(), ioe);
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
		} else {
			return new byte[] { };
		}
	}
}