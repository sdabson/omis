package omis.incident.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.incident.domain.IncidentStatementCategory;
import omis.incident.domain.Jurisdiction;
import omis.location.domain.Location;
import omis.person.domain.Person;

/** 
 * Form object for search incident report.
 * 
 * @author: Yidong Li
 * @author: Joel Norris
 * @version 0.1.2 (February 6, 2019)
 * @since OMIS 3.0
 */
public class SearchIncidentStatementForm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Date startDate;
	private Date endDate;
	private String keywords;
	private IncidentStatementCategory category;
	private Location location;
	private Jurisdiction jurisdiction;
	private Person reporter;
	private String title;
	private Person involvedParty;
	private String involvedPartyName;
	private InvolvedPartyOption involvedPartyOption;

	/**
	 * Instantiates a search incident report form.
	 */
	public SearchIncidentStatementForm() {
		// Default instantiation
	}
	
	/**
	 * Returns the start date.
	 * 
	 * @return start date
	 */
	public Date getStartDate() {
		return this.startDate;
	}
	
	/**
	 * Sets the start date.
	 * 
	 * @param startDate start date
	 */
	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}
	
	/**
	 * Returns the end date.
	 * 
	 * @return end date
	 */
	public Date getEndDate() {
		return this.endDate;
	}
	
	/**
	 * Sets the end date.
	 * 
	 * @param endDate end date
	 */
	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * Returns the location.
	 * 
	 * @return location
	 */
	public Location getLocation() {
		return this.location;
	}
	
	/**
	 * Sets the location.
	 * 
	 * @param location location
	 */
	public void setLocation(final Location location) {
		this.location = location;
	}
	
	/**
	 * Returns the jurisdiction.
	 * 
	 * @return jurisdiction
	 */
	public Jurisdiction getJurisdiction() {
		return this.jurisdiction;
	}
	
	/**
	 * Sets the jurisdiction.
	 * 
	 * @param jurisdiction jurisdiction
	 */
	public void setJurisdiction(final Jurisdiction jurisdiction) {
		this.jurisdiction = jurisdiction;
	}

	/**
	 * Returns keywords.
	 * 
	 * @return keywords
	 */
	public String getKeywords() {
		return this.keywords;
	}

	/**
	 * Sets keywords.
	 * 
	 * @param keywords keywords
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	/**
	 * Returns incident statement category.
	 * 
	 * @return incident statement category
	 */
	public IncidentStatementCategory getCategory() {
		return this.category;
	}

	/**
	 * Sets incident statement category.
	 * 
	 * @param category incident statement category
	 */
	public void setCategory(final IncidentStatementCategory category) {
		this.category = category;
	}

	/**
	 * Returns reporter of incident statement.
	 * 
	 * @return reporter
	 */
	public Person getReporter() {
		return this.reporter;
	}

	/**
	 * Sets reporter of incident statement.
	 * 
	 * @param reporter reporter
	 */
	public void setReporter(final Person reporter) {
		this.reporter = reporter;
	}

	/**
	 * Returns title.
	 * 
	 * @return title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Sets title.
	 * 
	 * @param title title
	 */
	public void setTitle(final String title) {
		this.title = title;
	}

	/**
	 * Returns involved party.
	 * 
	 * @return involved party
	 */
	public Person getInvolvedParty() {
		return this.involvedParty;
	}

	/**
	 * Sets involved party.
	 * 
	 * @param involvedParty involved party
	 */
	public void setInvolvedParty(final Person involvedParty) {
		this.involvedParty = involvedParty;
	}

	/**
	 * Returns involved party name.
	 * 
	 * @return involved party name
	 */
	public String getInvolvedPartyName() {
		return this.involvedPartyName;
	}

	/**
	 * Sets involved party name.
	 * 
	 * @param involvedPartyName involved party name
	 */
	public void setInvolvedPartyName(final String involvedPartyName) {
		this.involvedPartyName = involvedPartyName;
	}

	/**
	 * Returns involved party option.
	 * 
	 * @return involved party option
	 */
	public InvolvedPartyOption getInvolvedPartyOption() {
		return this.involvedPartyOption;
	}

	/**
	 * Sets involved party option.
	 * 
	 * @param involvedPartyOption involved party option
	 */
	public void setInvolvedPartyOption(final InvolvedPartyOption involvedPartyOption) {
		this.involvedPartyOption = involvedPartyOption;
	}
}