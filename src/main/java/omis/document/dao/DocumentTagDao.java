package omis.document.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.document.domain.Document;
import omis.document.domain.DocumentTag;

/** Data access object for document tag.
 * @author Ryan Johns
 * @version 0.1.0 (May 19, 2015)
 * @since OMIS 3.0 */
public interface DocumentTagDao extends GenericDao<DocumentTag> {
	/** finds document tag by name and document. 
	 * @param tagName tag name.
	 * @param document document. 
	 * @return document tag. */
	DocumentTag findByTagNameAndDocument(String tagName, Document document);
	
	/** finds document tag by name and document excluding.
	 * @param tagName tag name.
	 * @param document document.
	 * @param documentTags - document tags.
	 * @return list of document tags.  */
	DocumentTag findByTagNameAndDocumentExcluding(String tagName, 
			Document document, DocumentTag...documentTags);
	
	/** Lists document tag by document.
	 * @param document - document.
	 * @return document tags. */
	List<DocumentTag> findByDocument(Document document);
}
