package omis.victim.report;

import java.io.Serializable;
import java.util.Date;

/**
 * Summary of the victim's documents.
 *
 *
 * @author Trevor Isles
 * @version 0.1.0 (Nov 1, 2017)
 * @since OMIS 3.0
 */

public class VictimDocumentAssociationSummary implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private final Long id;
	
	private final String documentTitle;
	
	private final Date documentUploadDate;
	
	private final String userLastName;
	
	private final String userFirstName;

	/**
	 * Instantiates summary of victim documents.
	 * 
	 * @param id - victim document association ID
	 * @param documentTitle - victim document title
	 * @param documentUploadDate - victim document upload date
	 * @param userLastName - last user's last name
	 * @param userFirstName - last user's first name
	 */
	public VictimDocumentAssociationSummary(
			final Long id, 
			final String documentTitle, 
			final Date documentUploadDate,
			final String userLastName, 
			final String userFirstName) {
		this.id = id;
		this.documentTitle = documentTitle;
		this.documentUploadDate = documentUploadDate;
		this.userLastName = userLastName;
		this.userFirstName = userFirstName;
	}

	/**
	 * Returns the ID of the victim document association.
	 * 
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Returns the victim document title.
	 * 
	 * @return the documentTitle
	 */
	public String getDocumentTitle() {
		return documentTitle;
	}

	/**
	 * Returns the victim document upload date.
	 * 
	 * @return the documentUploadDate
	 */
	public Date getDocumentUploadDate() {
		return documentUploadDate;
	}

	/**
	 * Returns the last user's last name.
	 * 
	 * @return the userLastName
	 */
	public String getUserLastName() {
		return userLastName;
	}

	/**
	 * Returns the last user's first name.
	 * 
	 * @return the userFirstName
	 */
	public String getUserFirstName() {
		return userFirstName;
	}	
	
}
