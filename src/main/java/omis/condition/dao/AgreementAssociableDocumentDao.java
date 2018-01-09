package omis.condition.dao;

import java.util.List;

import omis.condition.domain.Agreement;
import omis.condition.domain.AgreementAssociableDocument;
import omis.dao.GenericDao;
import omis.document.domain.Document;

/**
 * Agreement Associable Document Data Access Object.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Nov 27, 2017)
 *@since OMIS 3.0
 *
 */
public interface AgreementAssociableDocumentDao
		extends GenericDao<AgreementAssociableDocument> {
	
	/**
	 * Finds an Agreement Associable Document by the specified properties.
	 * 
	 * @param agreement - Agreement
	 * @param document - Document
	 * @return AgreementAssociableDocument - Agreement Associable Document with
	 * the specified properties
	 */
	AgreementAssociableDocument find(Agreement agreement, Document document);
	
	/**
	 * Finds an Agreement Associable Document by the given properties
	 * excluding specified Agreement Associable Document.
	 * 
	 * @param agreement - Agreement
	 * @param document - Document
	 * @param excludedAgreementAssociableDocument - Agreement Associable
	 * Document to exclude
	 * @return AgreementAssociableDocument - Agreement Associable Document
	 * by the given properties excluding specified Agreement Associable Document
	 */
	AgreementAssociableDocument findExcluding(
			Agreement agreement, Document document, AgreementAssociableDocument
			excludedAgreementAssociableDocument);
	
	/**
	 * Returns a list of Agreement Associable Documents by the specified 
	 * Agreement.
	 * 
	 * @param agreement - Agreement
	 * @return List of Agreement Associable Documents by the specified 
	 * Agreement
	 */
	List<AgreementAssociableDocument> findByAgreement(Agreement agreement);
}
