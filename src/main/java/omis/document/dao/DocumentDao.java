package omis.document.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.document.domain.Document;

/** Data access object for documents.
 * @author Ryan Johns
 * @version 0.1.0 (May 19, 2015)
 * @since OMIS 3.0 */
public interface DocumentDao extends GenericDao<Document> {
	/** Finds documents by date file name.
	 * @param fileName file name.
	 * @param excludedDocuments excluded documents.
	 * @return list of documents. */
	List<Document> findByFileNameExcluding(String fileName, 
					Document...excludedDocuments);
	
	/** Finds next document filename distinguisher.
	 * @return document filename distinguisher. */
	String findNextDocumentFilenameDistinguisher();

	/**
	 * Finds documents by filename
	 * @param fileName 
	 * @return list of documents
	 */
	List<Document> findByFileName(String fileName);
}
