package omis.education.web.form;

import java.util.Date;
import java.util.List;

import omis.audit.domain.VerificationMethod;
import omis.education.domain.EducationalAchievementCategory;
import omis.education.domain.InstituteCategory;
import omis.education.domain.component.Institute;
import omis.user.domain.UserAccount;

/**
 * EducationForm.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jul 26, 2016)
 *@since OMIS 3.0
 *
 */
public class EducationForm {
	
	private Date attendedStartDate;
	
	private Date attendedEndDate;
	
	private Institute institute;
	
	private List<InstituteCategory> instituteCategories;
	
	private String description;
	
	private Date achievementDate;
	
	private String achievementDescription;
	
	private EducationalAchievementCategory achievementCategory;
	
	private List<EducationalAchievementCategory> achievementCategories;
	
	private Boolean specialEducation;
	
	private Boolean graduated;
	
	private Boolean verified;
	
	private UserAccount verifiedUser;
	
	private Date verifiedDate;
	
	private VerificationMethod verificationMethod;
	
	private List<EducationalAchievementItem> achievementItems;
	
	private List<EducationNoteItem> noteItems;
	
	private List<VerificationMethod> verificationMethods;
	
	/**
	 * Default constructor for education form
	 */
	public EducationForm(){
		//Default
	}
	
	/**
	 * Returns achievement date
	 * @return the achievementDate
	 */
	public Date getAchievementDate() {
		return achievementDate;
	}


	/**
	 * Sets achievement date
	 * @param achievementDate the achievementDate to set
	 */
	public void setAchievementDate(Date achievementDate) {
		this.achievementDate = achievementDate;
	}


	/**
	 * Returns achievement description
	 * @return the achievementDescription
	 */
	public String getAchievementDescription() {
		return achievementDescription;
	}


	/**
	 * Sets achievement description
	 * @param achievementDescription the achievementDescription to set
	 */
	public void setAchievementDescription(String achievementDescription) {
		this.achievementDescription = achievementDescription;
	}

	/**
	 * Returns the attended start date
	 * @return the attendedStartDate
	 */
	public Date getAttendedStartDate() {
		return attendedStartDate;
	}

	/**
	 * Sets the attended start date
	 * @param attendedStartDate the attendedStartDate to set
	 */
	public void setAttendedStartDate(Date attendedStartDate) {
		this.attendedStartDate = attendedStartDate;
	}

	/**
	 * Returns the attended end date
	 * @return the attendedEndDate
	 */
	public Date getAttendedEndDate() {
		return attendedEndDate;
	}

	/**
	 * Sets the attended end date
	 * @param attendedEndDate the attendedEndDate to set
	 */
	public void setAttendedEndDate(Date attendedEndDate) {
		this.attendedEndDate = attendedEndDate;
	}

	/**
	 * Returns the institute (component of Education Term)
	 * @return the institute
	 */
	public Institute getInstitute() {
		return institute;
	}

	/**
	 * Sets the institute (component of Education Term)
	 * @param institute the institute to set
	 */
	public void setInstitute(Institute institute) {
		this.institute = institute;
	}

	/**
	 * Returns the institute categories
	 * @return the instituteCategories
	 */
	public List<InstituteCategory> getInstituteCategories() {
		return instituteCategories;
	}

	/**
	 * Sets the institute categories
	 * @param instituteCategories the instituteCategories to set
	 */
	public void setInstituteCategories(List<InstituteCategory> 
			instituteCategories) {
		this.instituteCategories = instituteCategories;
	}

	/**
	 * Returns the description of the education term
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the education term
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns special education
	 * @return the specialEducation
	 */
	public Boolean getSpecialEducation() {
		return specialEducation;
	}

	/**
	 * Sets special education
	 * @param specialEducation the specialEducation to set
	 */
	public void setSpecialEducation(Boolean specialEducation) {
		this.specialEducation = specialEducation;
	}

	/**
	 * Returns graduated (form control)
	 * @return the graduated
	 */
	public Boolean getGraduated() {
		return graduated;
	}



	/**
	 * Sets graduated (form control)
	 * @param graduated the graduated to set
	 */
	public void setGraduated(Boolean graduated) {
		this.graduated = graduated;
	}

	/**
	 * Returns verified (verification signature result)
	 * @return the verified
	 */
	public Boolean getVerified() {
		return verified;
	}


	/**
	 * Sets verified (verification signature result)
	 * @param verified the verified to set
	 */
	public void setVerified(Boolean verified) {
		this.verified = verified;
	}


	/**
	 * Returns verified user
	 * @return the verifiedUser
	 */
	public UserAccount getVerifiedUser() {
		return verifiedUser;
	}


	/**
	 * Sets verified user
	 * @param verifiedUser the verifiedUser to set
	 */
	public void setVerifiedUser(UserAccount verifiedUser) {
		this.verifiedUser = verifiedUser;
	}

	/**
	 * Returns verified date
	 * @return the verifiedDate
	 */
	public Date getVerifiedDate() {
		return verifiedDate;
	}
	
	/**
	 * Sets verified date
	 * @param verifiedDate the verifiedDate to set
	 */
	public void setVerifiedDate(Date verifiedDate) {
		this.verifiedDate = verifiedDate;
	}

	/**
	 * Returns verification method
	 * @return the verificationMethod
	 */
	public VerificationMethod getVerificationMethod() {
		return verificationMethod;
	}

	/**
	 * Sets verification method
	 * @param verificationMethod the verificationMethod to set
	 */
	public void setVerificationMethod(VerificationMethod verificationMethod) {
		this.verificationMethod = verificationMethod;
	}

	/**
	 * Returns list of achievement categories
	 * @return the achievementCategories
	 */
	public List<EducationalAchievementCategory> getAchievementCategories() {
		return achievementCategories;
	}

	/**
	 * Sets achievement categories
	 * @param achievementCategories the achievementCategories to set
	 */
	public void setAchievementCategories(List<EducationalAchievementCategory> 
			achievementCategories) {
		this.achievementCategories = achievementCategories;
	}

	/**
	 * Returns a list of verification methods
	 * @return the verificationMethods
	 */
	public List<VerificationMethod> getVerificationMethods() {
		return verificationMethods;
	}

	/**
	 * Returns a list of educational achievement items (for listing an
	 * education term's educational achievements)
	 * @return the achievementItems
	 */
	public List<EducationalAchievementItem> getAchievementItems() {
		return achievementItems;
	}

	/**
	 * Sets a list of educational achievement items (for listing an
	 * education term's educational achievements)
	 * @param achievementItems the achievementItems to set
	 */
	public void setAchievementItems(List<EducationalAchievementItem> achievementItems) {
		this.achievementItems = achievementItems;
	}

	/**
	 * Returns a list of education note items (for listing an
	 * education term's education notes)
	 * @return the noteItems
	 */
	public List<EducationNoteItem> getNoteItems() {
		return noteItems;
	}

	/**
	 * Sets a list of education note items (for listing an
	 * education term's education notes)
	 * @param noteItems the noteItems to set
	 */
	public void setNoteItems(List<EducationNoteItem> noteItems) {
		this.noteItems = noteItems;
	}

	/**
	 * Sets the verification methods
	 * @param verificationMethods the verificationMethods to set
	 */
	public void setVerificationMethods(List<VerificationMethod> verificationMethods) {
		this.verificationMethods = verificationMethods;
	}

	/**
	 * Returns the achievement's category
	 * @return the achievementCategory
	 */
	public EducationalAchievementCategory getAchievementCategory() {
		return achievementCategory;
	}

	/**
	 * Sets the achievement's category
	 * @param achievementCategory the achievementCategory to set
	 */
	public void setAchievementCategory(EducationalAchievementCategory 
			achievementCategory) {
		this.achievementCategory = achievementCategory;
	}
}
