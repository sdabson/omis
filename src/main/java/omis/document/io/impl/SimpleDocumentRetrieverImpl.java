package omis.document.io.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import omis.document.domain.Document;
import omis.document.io.DocumentRetriever;
import omis.io.FileLocator;

/** Implementation of document retriever. Based on the implementation of 
 * PhotoRetriever in {@link SimplePhotoRetrieverImpl}.
 * @author Ryan Johns
 * @since OMIS 3.0 (Nov 18, 2015) */
public class SimpleDocumentRetrieverImpl implements DocumentRetriever {
	private final FileLocator fileLocator;
	private final String notFoundFilename;
	
	/** Constructor.
	 * @param fileLocator - file locator. 
	 * @param defaultFilename - default file name. */
	public SimpleDocumentRetrieverImpl(final FileLocator fileLocator,
			final String defaultFilename) {
		this.fileLocator = fileLocator;
		this.notFoundFilename = defaultFilename;
	}
	
	/** {@inheritDoc} */
	@Override
	public byte[] retrieve(final Document document) {
		return this.retrieve(document.getFilename());
	}

	// Retrieve document by filename.
	private byte[] retrieve(final String filename) {
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
				throw new RuntimeException(String.format(
						"Error opening file: %s", file.getName()), ioe);
			} finally {
				if (buffer != null) {
					try {
						buffer.close();
					} catch (IOException ioe) {
						throw new RuntimeException(String.format(
								"Error closing file: %s", file.getName()), ioe);
					}
				}
			}
		} else {
			return new byte[]{};
		}
	}
}
