package omis.document.report;

import java.util.Date;

/** Summary for documents.
 * @author Ryan Johns
 * @version 0.1.0 (Nov 24, 2015)
 * @since OMIS 3.0 */
public class DocumentSummary {
	private final Long documentId;
	private final Long documentAssociationId;
	private final String documentTypeName;
	private final String documentClass;
	private final String title;
	private final String updateUserFirstName;
	private final String updateUserLastName;
	private final String updateUserMiddleName;
	private final Date updateDate;
	
	/** Constructor.
	 * @param documentId - document id.
	 * @param documentAssociationId - document association id.
	 * @param documentTypeName - document type name.
	 * @param documentClass - document class. 
	 * @param title - title.
	 * @param updateUserFirstName - update user first name.
	 * @param updateUserLastName - update user last name.
	 * @param updateUserMiddleName - update user middle name.
	 * @param updateDate - update date. */
	public DocumentSummary(final Long documentId, 
			final Long documentAssociationId, final String documentTypeName, 
			final String documentClass, final String title, 
			final String updateUserFirstName, final String updateUserLastName, 
			final String updateUserMiddleName, final Date updateDate) {
		this.documentId = documentId;
		this.documentAssociationId = documentAssociationId;
		this.documentTypeName = documentTypeName;
		this.documentClass = documentClass;
		this.title = title;
		this.updateUserFirstName = updateUserFirstName;
		this.updateUserLastName = updateUserLastName;
		this.updateUserMiddleName = updateUserMiddleName;
		this.updateDate = updateDate;
	}
	
	/** Gets document id.
	 * @return document id. */
	public String getDocumentId() { 
		return String.valueOf(this.documentId);
	}
	
	/** Gets document association id. 
	 * @return document association id. */
	public String getDocumentAssociationId() { 
		return String.valueOf(this.documentAssociationId); 
	}
	
	/** Gets document type name.
	 * @return document type name. */
	public String getDocumentTypeName() { 
		return this.documentTypeName; 
	}
	
	/** Gets document class name.
	 * @return document class name. */
	public String getDocumentClass() { 
		return this.documentClass; 
	}
	
	/** Gets title.
	 * @return title. */
	public String getTitle() { 
		return this.title; 
	}
	
	/** Gets update user first name.
	 * @return update user first name. */
	public String getUpdateUserFirstName() { 
		return this.updateUserFirstName; 
	}
	
	/** Gets update user last name.
	 * @return update user last name. */
	public String getUpdateUserLastName() { 
		return this.updateUserLastName; 
	}
	
	/** Gets update user middle name.
	 * @return update user middle name. */
	public String getUpdateUserMiddleName() { 
		return this.updateUserMiddleName; 
	}
	
	/** Gets update date.
	 * @return update date. */
	public Date getUpdateDate() { 
		return this.updateDate; 
	}
}
