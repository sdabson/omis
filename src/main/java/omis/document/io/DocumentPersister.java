package omis.document.io;

import java.io.File;

import omis.document.domain.Document;

/** Document persister.
 * @author Ryan Johns
 * @version 0.1.0 (May 19, 2015)
 * @since OMIS 3.0 */
public interface DocumentPersister {
	/** Persist document file.
	 * @param document document.
	 * @param file file
	 * @return file. */
	File persist(Document document, byte[] file);
}