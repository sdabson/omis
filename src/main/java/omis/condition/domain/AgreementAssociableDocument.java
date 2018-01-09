package omis.condition.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.document.domain.Document;

/**
 * Agreement Associable Document.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Nov 27, 2017)
 *@since OMIS 3.0
 *
 */
public interface AgreementAssociableDocument extends Creatable, Updatable {
	
	/**
	 * Returns the ID of the Agreement Associable Document.
	 * @return ID
	 */
	Long getId();
	
	/**
	 * Sets the ID of the Agreement Associable Document.
	 * @param id - Long
	 */
	void setId(Long id);
	
	/**
	 * Returns the Agreement for the Agreement Associable Document.
	 * @return agreement - Agreement
	 */
	Agreement getAgreement();
	
	/**
	 * Sets the Agreement for the Agreement Associable Document.
	 * @param agreement - Agreement
	 */
	void setAgreement(Agreement agreement);
	
	/**
	 * Returns the Document for the Agreement Associable Document.
	 * @return document - Document
	 */
	Document getDocument();
	
	/**
	 * Sets the Document for the Agreement Associable Document.
	 * @param document - Document
	 */
	void setDocument(Document document);
	
}
