package omis.substance.web.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import omis.person.domain.Person;
import omis.substance.domain.SubstanceLab;

/**
 * Substance Test Form.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 21, 2013)
 * @since OMIS 3.0
 */
public class SubstanceTestForm implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String testComment;
	
	private Boolean labInvolved;
	
	private List<ResultItem> resultItems = 
			new ArrayList<ResultItem>();
	
	private List<LabResultItem> labResultItems = 
			new ArrayList<LabResultItem>();
	
	private Date labResultDate;
	
	private Date labRequestDate;
	
	private Date resultDate;
	
	private SubstanceLab lab;
	
	private String privateLabJustification;
	
	private Person authorizingStaff;
	
	/**
	 * Instantiates a default instance of substance test form.
	 */
	public SubstanceTestForm() {
		//Default constructor.
	}

	/**
	 * Return the test comment of the substance test form.
	 * @return the testComment
	 */
	public String getTestComment() {
		return this.testComment;
	}

	/**
	 * Set the test comment of the substance test form.
	 * @param testComment the testComment to set
	 */
	public void setTestComment(final String testComment) {
		this.testComment = testComment;
	}

	/**
	 * Return the result date of the substance test form.
	 * @return the resultDate
	 */
	public Date getResultDate() {
		return this.resultDate;
	}

	/**
	 * Set the result date of the substance test form.
	 * @param resultDate the resultDate to set
	 */
	public void setResultDate(final Date resultDate) {
		this.resultDate = resultDate;
	}

	/**
	 * Returns the result items of the substance test form.
	 * @return list of result items
	 */
	public List<ResultItem> getResultItems() {
		return this.resultItems;
	}

	/**
	 * Sets the result items of the substance test form.
	 * @param resultItems result items
	 */
	public void setResultItems(
			final List<ResultItem> 
			resultItems) {
		this.resultItems = resultItems;
	}

	/**
	 * Returns whether lab involved applies.
	 * 
	 * @return lab involved
	 */
	public Boolean getLabInvolved() {
		return this.labInvolved;
	}

	/**
	 * Sets whether lab involved applies.
	 * 
	 * @param labInvolved lab involved
	 */
	public void setLabInvolved(final Boolean labInvolved) {
		this.labInvolved = labInvolved;
	}

	/**
	 * Returns lab result items.
	 * 
	 * @return lab result items
	 */
	public List<LabResultItem> getLabResultItems() {
		return this.labResultItems;
	}

	/**
	 * Sets lab result items.
	 * 
	 * @param labResultItems lab result items
	 */
	public void setLabResultItems(final List<LabResultItem> labResultItems) {
		this.labResultItems = labResultItems;
	}

	/**
	 * Returns lab result date.
	 * 
	 * @return lab result date
	 */
	public Date getLabResultDate() {
		return this.labResultDate;
	}

	/**
	 * Sets lab result date.
	 * 
	 * @param labResultDate lab result date
	 */
	public void setLabResultDate(final Date labResultDate) {
		this.labResultDate = labResultDate;
	}

	/**
	 * Return lab request date.
	 * 
	 * @return lab request date
	 */
	public Date getLabRequestDate() {
		return this.labRequestDate;
	}

	/**
	 * Sets lab request date.
	 * 
	 * @param labRequestDate lab request date
	 */
	public void setLabRequestDate(final Date labRequestDate) {
		this.labRequestDate = labRequestDate;
	}

	/**
	 * Returns the lab.
	 * 
	 * @return substance lab
	 */
	public SubstanceLab getLab() {
		return this.lab;
	}

	/**
	 * Sets the lab.
	 * 
	 * @param lab substance lab
	 */
	public void setLab(final SubstanceLab lab) {
		this.lab = lab;
	}

	/**
	 * Returns the private lab justification.
	 * 
	 * @return private lab justification
	 */
	public String getPrivateLabJustification() {
		return this.privateLabJustification;
	}

	/**
	 * Sets the private lab justification.
	 * 
	 * @param privateLabJustification private lab justification
	 */
	public void setPrivateLabJustification(final String privateLabJustification) {
		this.privateLabJustification = privateLabJustification;
	}

	/**
	 * Returns authorizing staff.
	 * 
	 * @return authorizing staff
	 */
	public Person getAuthorizingStaff() {
		return this.authorizingStaff;
	}

	/**
	 * Sets authorizing staff.
	 * 
	 * @param authorizingStaff authorizing staff
	 */
	public void setAuthorizingStaff(final Person authorizingStaff) {
		this.authorizingStaff = authorizingStaff;
	}
}