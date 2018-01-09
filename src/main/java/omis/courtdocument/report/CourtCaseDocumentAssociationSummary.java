package omis.courtdocument.report;

import java.io.Serializable;
import java.util.Date;

/** Report summary for court case document.
 * @author Ryan Johns
 * @version 0.1.0 (Dec 14, 2015)
 * @since OMIS 3.0 */ 
public class CourtCaseDocumentAssociationSummary implements Serializable {
	private static final long serialVersionUID = 1L;
	private final Long documentId;
	private final Long courtCaseDocumentAssociationId;
	private final Date fileDate;
	private final String docketName;
	private final String courtName;
	private final String categoryName;
	private final String documentTitle;
	private final String updateUserFirstName;
	private final String updateUserLastName;
	private final String updateUserMiddleName;
	private final Date updateDate;
	
	/** Constructor.
	 * @param documentId - document id.
	 * @param courtCaseDocumentAssociationId - court case document association 
	 * id.
	 * @param fileDate - file date.
	 * @param docketName - docket name.
	 * @param courtName - court name.
	 * @param categoryName - category name.
	 * @param documentTitle - document title.
	 * @param updateUserFirstName - update user first name.
	 * @param updateUserLastName - update user last name.
	 * @param updateUserMiddleName - update user middle name.
	 * @param updateDate - update date. */
	public CourtCaseDocumentAssociationSummary(final Long documentId, 
			final Long courtCaseDocumentAssociationId, final Date fileDate, 
			final String docketName, final String courtName, 
			final String categoryName, final String documentTitle,
			final String updateUserFirstName, final String updateUserLastName,
			final String updateUserMiddleName, final Date updateDate) {
		this.documentId = documentId;
		this.courtCaseDocumentAssociationId = courtCaseDocumentAssociationId;
		this.fileDate = fileDate;
		this.docketName = docketName;
		this.courtName = courtName;
		this.categoryName = categoryName;
		this.documentTitle = documentTitle;
		this.updateUserFirstName = updateUserFirstName;
		this.updateUserLastName = updateUserLastName;
		this.updateUserMiddleName = updateUserMiddleName;
		this.updateDate = updateDate;
	}
	
	/** Gets document id.
	 * @return document id. */
	public Long getDocumentId() {
		return this.documentId; 
	}
	
	/** Gets court document id.
	 * @return court document id. */
	public Long getCourtCaseDocumentAssociationId() { 
		return this.courtCaseDocumentAssociationId; 
	}
	
	/** Gets file date. 
	 * @return file date. */
	public Date getFileDate() { 
		return this.fileDate; 
	}
	
	/** Gets docket name.
	 * @return docket name. */
	public String getDocketName() {
		return this.docketName; 
	}
	
	/** Gets court name.
	 * @return court name. */
	public String getCourtName() { 
		return this.courtName; 
	}
	
	/** Gets category name.
	 * @return category name. */
	public String getCategoryName() { 
		return this.categoryName; 
	}
	
	/** Gets document title.
	 * @return document title. */
	public String getDocumentTitle() { 
		return this.documentTitle; 
	}
	
	/** Getts update user first name. 
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
