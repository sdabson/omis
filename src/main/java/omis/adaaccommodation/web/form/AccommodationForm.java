package omis.adaaccommodation.web.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import omis.adaaccommodation.domain.AccommodationCategory;
import omis.adaaccommodation.domain.AuthorizationSourceCategory;
import omis.adaaccommodation.domain.Disability;
import omis.adaaccommodation.domain.DisabilityClassificationCategory;
import omis.user.domain.UserAccount;

/**
 * Accommodation form.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 24, 2015)
 * @since OMIS 3.0
 */
public class AccommodationForm {

	private Disability disability;
	
	private String accommodationDescription;
	
	private AccommodationCategory accommodationCategory;
	
	private List<AccommodationNoteItem> accommodationNotes 
		= new ArrayList<AccommodationNoteItem>();

	private String disabilityDescription;
	
	private DisabilityClassificationCategory disabilityCategory;

	private Date authorizationDate;

	private UserAccount authorizationUser;

	private AuthorizationSourceCategory authorizationSourceCategory;

	private String authorizationComments;

	private Boolean temporaryAuthorization;
	
	private Date startDate;
	
	private Date endDate;
	
	/** 
	 * Instantiates a default instance of accommodation form.
	 */
	public AccommodationForm() {
		//Default constructor.
	}
	
	/**
	 * Returns the ADA accommodation.
	 * 
	 * @return disability
	 */
	public Disability getDisability() {
		return this.disability;
	}

	/**
	 * Sets the ADA accommodation disability.
	 * 
	 * @param disability disability
	 */
	public void setDisability(final Disability disability) {
		this.disability = disability;
	}
	
	/**
	 * Returns the ADA accommodation description.
	 *
	 * @return the accommodation description
	 */
	public String getAccommodationDescription() {
		return this.accommodationDescription;
	}

	/**
	 * Sets the ADA accommodation description.
	 *
	 * @param accommodationDescription the accommodation description to set
	 */
	public void setAccommodationDescription(
			final String accommodationDescription) {
		this.accommodationDescription = accommodationDescription;
	}

	/**
	 * Returns the ADA accommodation category.
	 *
	 * @return the accommodation category
	 */
	public AccommodationCategory getAccommodationCategory() {
		return this.accommodationCategory;
	}

	/**
	 * Sets the ADA accommodation category.
	 *
	 * @param accommodationCategory the accommodation category to set
	 */
	public void setAccommodationCategory(
			final AccommodationCategory accommodationCategory) {
		this.accommodationCategory = accommodationCategory;
	}
	
	/**
	 * Returns the ADA accommodation notes.
	 *
	 * @return the accommodationNotes
	 */
	public List<AccommodationNoteItem> getAccommodationNotes() {
		return this.accommodationNotes;
	}

	/**
	 * Sets the ADA accommodation notes.
	 *
	 * @param accommodationNotes the accommodation notes to set
	 */
	public void setAccommodationNotes(
			final List<AccommodationNoteItem> accommodationNotes) {
		this.accommodationNotes = accommodationNotes;
	}

	/**
	 * Returns the disability description.
	 *
	 * @return the disabilityDescription
	 */
	public String getDisabilityDescription() {
		return this.disabilityDescription;
	}

	/**
	 * Sets the disability description.
	 *
	 * @param disabilityDescription the disabilityDescription to set
	 */
	public void setDisabilityDescription(final String disabilityDescription) {
		this.disabilityDescription = disabilityDescription;
	}

	/**
	 * Returns the disability category.
	 *
	 * @return the disabilityCategory
	 */
	public DisabilityClassificationCategory getDisabilityCategory() {
		return this.disabilityCategory;
	}

	/**
	 * Sets the disability category.
	 *
	 * @param disabilityCategory the disabilityCategory to set
	 */
	public void setDisabilityCategory(
			final DisabilityClassificationCategory disabilityCategory) {
		this.disabilityCategory = disabilityCategory;
	}

	/**
	 * Returns the authorization date.
	 *
	 * @return the authorizationDate
	 */
	public Date getAuthorizationDate() {
		return this.authorizationDate;
	}

	/**
	 * Sets the authorization date.
	 *
	 * @param authorizationDate the authorizationDate to set
	 */
	public void setAuthorizationDate(final Date authorizationDate) {
		this.authorizationDate = authorizationDate;
	}

	/**
	 * Returns the authoriztion user.
	 *
	 * @return the authorizationUser
	 */
	public UserAccount getAuthorizationUser() {
		return this.authorizationUser;
	}

	/**
	 * Sets the authorization user.
	 *
	 * @param authorizationUser the authorizationUser to set
	 */
	public void setAuthorizationUser(final UserAccount authorizationUser) {
		this.authorizationUser = authorizationUser;
	}

	/**
	 * Returns the authorization category.
	 *
	 * @return the authorizationSourceCategory
	 */
	public AuthorizationSourceCategory getAuthorizationSourceCategory() {
		return this.authorizationSourceCategory;
	}

	/**
	 * Sets the authorization category.
	 *
	 * @param authorizationSourceCategory the authorizationSourceCategory to set
	 */
	public void setAuthorizationSourceCategory(
			final AuthorizationSourceCategory authorizationSourceCategory) {
		this.authorizationSourceCategory = authorizationSourceCategory;
	}

	/**
	 * Returns the authorization comments.
	 *
	 * @return the authorizationComments
	 */
	public String getAuthorizationComments() {
		return this.authorizationComments;
	}

	/**
	 * Sets the authorization comments.
	 *
	 * @param authorizationComments the authorizationComments to set
	 */
	public void setAuthorizationComments(final String authorizationComments) {
		this.authorizationComments = authorizationComments;
	}	

	/** Sets temporary authorization.
	 * @param temporaryAuthorization - temporary authorization. */
	public void setTemporaryAuthorization(
			final Boolean temporaryAuthorization) {
		this.temporaryAuthorization = temporaryAuthorization;
	}
	
	/** Returns temporary authorization.
	 * @return temporary authorization. */
	public Boolean getTemporaryAuthorization() {
		return this.temporaryAuthorization;
	}
	
	/**
	 *	Returns the authorization start date.
	 *
	 * @return the startDate
	 */
	public Date getStartDate() {
		return this.startDate;
	}

	/**
	 * Sets the authorization start date.
	 * 
	 * @param startDate the startDate to set
	 */
	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Returns the authorization end date.
	 * 
	 * @return the endDate
	 */
	public Date getEndDate() {
		return this.endDate;
	}

	/**
	 * Sets the authorization end date.
	 * 
	 * @param endDate the endDate to set
	 */
	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}
}	