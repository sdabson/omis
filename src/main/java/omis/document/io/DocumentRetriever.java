package omis.document.io;

import omis.document.domain.Document;

/** Document retriever.
 * @author Ryan Johns
 * @version 0.1.0 (May 19, 2015)
 * @since OMIS 3.0 */
public interface DocumentRetriever {
	/** Retrieve document file from document.
	 * @param document document.
	 * @return data. */
	byte[] retrieve(Document document);
}
