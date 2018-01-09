package omis.document.domain;

import java.io.Serializable;

/** Represents a document associable entity.
 * @author Ryan Johns
 * @version 0.1.1 (Nov 9, 2015)
 * @since OMIS 3.0 */
public interface DocumentAssociable	extends Serializable {
	/** Gets document.
	 * @return document. */
	Document getDocument();
	
	/** Sets document.
	 * @param document document. */
	void setDocument(Document document);
}
