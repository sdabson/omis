package omis.workrestriction.web.form;

import java.util.Date;
import java.util.List;

import omis.user.domain.UserAccount;

/**
 * WorkRestrictionForm.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Aug 17, 2016)
 *@since OMIS 3.0
 *
 */
public class WorkRestrictionForm {
	private String category;
	
	private UserAccount authorizedByUser;
	
	private String authorizedByQuery;
	
	private Date authorizationDate;
	
	private String notes;
	
	private List<NoteItem> noteItems;
	
	/**
	 * Default Constructor 
	 */
	public WorkRestrictionForm() {
	}
	
	
	/**
	 * Returns the category
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Sets the category
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * Returns the authorized-by user account
	 * @return the authorizedByUser
	 */
	public UserAccount getAuthorizedByUser() {
		return authorizedByUser;
	}

	/**
	 * Sets the authorized-by user account
	 * @param authorizedByUser the authorizedByUser to set
	 */
	public void setAuthorizedByUser(UserAccount authorizedByUser) {
		this.authorizedByUser = authorizedByUser;
	}
	
	/**
	 * Returns the query for the authorized-by user
	 * @return the authorizedByQuery
	 */
	public String getAuthorizedByQuery() {
		return authorizedByQuery;
	}


	/**
	 * Sets the query for the authorized-by user
	 * @param authorizedByQuery the authorizedByQuery to set
	 */
	public void setAuthorizedByQuery(String authorizedByQuery) {
		this.authorizedByQuery = authorizedByQuery;
	}


	/**
	 * Returns the authorization date
	 * @return the authorizationDate
	 */
	public Date getAuthorizationDate() {
		return authorizationDate;
	}

	/**
	 * Sets the authorization date
	 * @param authorizationDate the authorizationDate to set
	 */
	public void setAuthorizationDate(Date authorizationDate) {
		this.authorizationDate = authorizationDate;
	}

	/**
	 * Returns the notes
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * Sets the notes
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}


	/**
	 * @return the noteItems
	 */
	public List<NoteItem> getNoteItems() {
		return this.noteItems;
	}


	/**
	 * @param noteItems the noteItems to set
	 */
	public void setNoteItems(List<NoteItem> noteItems) {
		this.noteItems = noteItems;
	}
	
	
	
	
	
}
