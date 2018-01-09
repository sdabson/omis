package omis.document.io.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import omis.document.domain.Document;
import omis.document.io.DocumentPersister;
import omis.io.FileLocator;

/** Implementation of simple document persister. Based on the implementation
 * of the PhotoPersister in {@link SimplePhotoPersisterImpl}.
 * @author Ryan Johns
 * @version 0.1.0 (Nov 18, 2015)
 * @since OMIS 3.0 */
public class SimpleDocumentPersisterImpl implements DocumentPersister {
	private final FileLocator fileLocator;
	
	/** Constructor.
	 * @param fileLocator - file locator. */
	public SimpleDocumentPersisterImpl(final FileLocator fileLocator) {
		this.fileLocator = fileLocator;
	}
	
	/** {@inheritDoc} */
	@Override
	public File persist(final Document document, final byte[] data) {
		return this.persist(document.getFilename(), data);
	}
	
	
	// Persist by filename.
	private File persist(final String fileName, final byte[] data) {
		File file = this.fileLocator.locate(fileName, false);
		if (file == null) {
			throw new IllegalArgumentException(String.format(
					"Cannot write file: %s  to directory",
					fileName));
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
			throw new RuntimeException(String.format(
					"Error writing to file: %s", file.getName()), ioe);
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
		return file;
	}
	
	/** Converts file to bytes. 
	 * @param file - file. 
	 * @return bytes. */
	public byte[] fileToBytes(final File file) {
		final byte[] bytes = new byte[(int) file.length()];
		try {
			final FileInputStream fileInputStream = new FileInputStream(file);
			fileInputStream.read(bytes);
			fileInputStream.close();
		} catch (IOException ioException) {
			throw new RuntimeException(String.format("Error reading file: %s", 
					file.getName()), ioException);
		}
		return bytes;
	}
}
