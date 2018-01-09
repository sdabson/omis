package omis.caseload.web.form;

import java.util.Date;

import omis.caseload.domain.CaseworkCategory;
import omis.caseload.domain.ContactCategory;
import omis.user.domain.UserAccount;

/** Caseload form.
 * @author Sheronda Vaughn
 * @version 0.1.0 (Sep 15, 2016)
 * @since OMIS 3.0
 */
public class CaseloadForm {

	private UserAccount userAccount;
	private Date startDate;
	private Date endDate;
	private Date lastContactDate;
	private ContactCategory category;
	private String caseloadName;
	private CaseworkCategory caseworkCategory;

	/** returns user account.
	 * @return the userAccount */
	public UserAccount getUserAccount() {
		return this.userAccount;
	}

	/** Sets user account.
	 * @param userAccount user account */
	public void setUserAccount(final UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	
	/** Returns start date.
	 * @return the starDate */
	public Date getStartDate() {
		return this.startDate;
	}

	/** sets start date.
	 * @param starDate star date */
	public void setStartDate(final Date starDate) {
		this.startDate = starDate;
	}

	/** Gets end date.
	 * @return the endDate.	*/
	public Date getEndDate() {
		return this.endDate;
	}

	/** Sets end date.
	 * @param endDate end date */
	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}

	/** Gets last contact date.
	 * @return the lastContactDate */
	public Date getLastContactDate() {
		return this.lastContactDate;
	}

	/** Sets last contact date.
	 * @param lastContactDate lastContactDate */
	public void setLastContactDate(final Date lastContactDate) {
		this.lastContactDate = lastContactDate;
	}

	/** Gets category.
	 * @return the category */
	public ContactCategory getCategory() {
		return this.category;
	}

	/** Sets category.
	 * @param category category  */
	public void setCategory(final ContactCategory category) {
		this.category = category;
	}

	/** Gets caseload name.
	 * @return the caseloadName */
	public String getCaseloadName() {
		return this.caseloadName;
	}

	/** Sets caseload name.
	 * @param caseloadName caseloadName */
	public void setCaseloadName(final String caseloadName) {
		this.caseloadName = caseloadName;
	}

	/** Gets case work category.
	 * @return the caseWorkCategory */
	public CaseworkCategory getCaseworkCategory() {
		return this.caseworkCategory;
	}

	/** Sets casework category.
	 * @param caseWorkCategory caseWorkCategory */
	public void setCaseworkCategory(final CaseworkCategory caseWorkCategory) {
		this.caseworkCategory = caseWorkCategory;
	}
}