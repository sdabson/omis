package omis.courtdocument.domain;

import omis.audit.domain.Creatable;
import omis.document.domain.DocumentAssociableCategory;

/** Court document category.
 * @author Ryan Johns
 * @version 0.1.0 (Nov 9, 2015)
 * @since OMIS 3.0 */
public interface CourtDocumentCategory extends DocumentAssociableCategory, 
	Creatable {
	/** Gets id.
	 * @return id. */
	Long getId();
	
	/** Sets id.
	 * @param id - id. */
	void setId(Long id);
}
