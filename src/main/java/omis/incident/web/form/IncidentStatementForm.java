package omis.incident.web.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import omis.facility.domain.Complex;
import omis.facility.domain.Facility;
import omis.facility.domain.FacilityArea;
import omis.facility.domain.Level;
import omis.facility.domain.Room;
import omis.facility.domain.Section;
import omis.facility.domain.Unit;
import omis.incident.domain.IncidentStatementCategory;
import omis.incident.domain.IncidentStatementSubmissionCategory;
import omis.incident.domain.InformationSourceCategory;
import omis.incident.domain.Jurisdiction;
import omis.person.domain.Person;
import omis.user.domain.UserAccount;

/**
 * Incident report form.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Sep 6, 2015)
 * @since OMIS 3.0
 */
public class IncidentStatementForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private Person reporter;
	
	private UserAccount documenter;
	
	private String title;
	
	private Date incidentDate;
	
	private Date incidentTime;
	
	private Date statementDate;
	
	private String summary;
	
	private String location;
	
	private Facility facility;
	
	private Complex complex;
	
	private Unit unit;
	
	private Room room;
	
	private Level level;
	
	private Section section;
	
	private FacilityArea facilityArea;
	
	private String incidentReportNumber;
	
	private Jurisdiction jurisdiction;
	
	private IncidentStatementCategory category;
	
	private Boolean facilityScene;
	
	private Boolean housingScene;
	
	private Boolean confidentialInformant;
	
	private Person informant;
	
	private InformationSourceCategory informationSourceCategory;
	
	private List<IncidentStatementNoteItem> incidentStatementNoteItems
		= new ArrayList<IncidentStatementNoteItem>();
	
	private List<InvolvedPartyItem> involvedPartyItems
		= new ArrayList<InvolvedPartyItem>();
	
	private IncidentStatementSubmissionCategory submissionCategory;
	
	private String informationSourceName;
	
	/**
	 * Instantiates a default instance of incident report form.
	 */
	public IncidentStatementForm() {
		//Default constructor.
	}

	/**
	 * Returns the reporter.
	 * 
	 * @return reporter
	 */
	public Person getReporter() {
		return this.reporter;
	}

	/**
	 * Sets the reporter.
	 * 
	 * @param reporter reporter
	 */
	public void setReporter(final Person reporter) {
		this.reporter = reporter;
	}

	/**
	 * Returns the documenter.
	 * 
	 * @return documenter
	 */
	public UserAccount getDocumenter() {
		return this.documenter;
	}

	/**
	 * Sets the documenter.
	 * 
	 * @param documenter documenter
	 */
	public void setDocumenter(final UserAccount documenter) {
		this.documenter = documenter;
	}

	/**
	 * Returns the title.
	 * 
	 * @return title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Sets the title.
	 * 
	 * @param title title
	 */
	public void setTitle(final String title) {
		this.title = title;
	}

	/**
	 * Returns the incident date.
	 * 
	 * @return incident date
	 */
	public Date getIncidentDate() {
		return this.incidentDate;
	}

	/**
	 * Sets the incident date.
	 * 
	 * @param incidentDate incident date
	 */
	public void setIncidentDate(final Date incidentDate) {
		this.incidentDate = incidentDate;
	}

	/**
	 * Returns the incident time.
	 * 
	 * @return incident time
	 */
	public Date getIncidentTime() {
		return this.incidentTime;
	}

	/**
	 * Sets the incident time.
	 * 
	 * @param incidentTime incident time
	 */
	public void setIncidentTime(final Date incidentTime) {
		this.incidentTime = incidentTime;
	}

	/**
	 * Returns the statement date.
	 * 
	 * @return statement date
	 */
	public Date getStatementDate() {
		return this.statementDate;
	}

	/**
	 * Sets the statement date.
	 * 
	 * @param statementDate statement date
	 */
	public void setStatementDate(final Date statementDate) {
		this.statementDate = statementDate;
	}

	/**
	 * Returns the summary.
	 * 
	 * @return summary
	 */
	public String getSummary() {
		return this.summary;
	}

	/**
	 * Sets the summary.
	 * 
	 * @param summary summary
	 */
	public void setSummary(final String summary) {
		this.summary = summary;
	}

	/**
	 * Returns the location.
	 * 
	 * @return location
	 */
	public String getLocation() {
		return this.location;
	}

	/**
	 * Sets the location.
	 * 
	 * @param location location
	 */
	public void setLocation(final String location) {
		this.location = location;
	}

	/**
	 * Returns the facility.
	 * 
	 * @return facility
	 */
	public Facility getFacility() {
		return this.facility;
	}

	/**
	 * Sets the facility.
	 * 
	 * @param facility facility
	 */
	public void setFacility(final Facility facility) {
		this.facility = facility;
	}

	/**
	 * Returns the complex.
	 * 
	 * @return complex
	 */
	public Complex getComplex() {
		return this.complex;
	}

	/**
	 * Sets the complex.
	 * 
	 * @param complex complex
	 */
	public void setComplex(final Complex complex) {
		this.complex = complex;
	}

	/**
	 * Returns the unit.
	 * 
	 * @return unit
	 */
	public Unit getUnit() {
		return this.unit;
	}

	/**
	 * Sets the unit.
	 * 
	 * @param unit unit
	 */
	public void setUnit(final Unit unit) {
		this.unit = unit;
	}

	/**
	 * Returns the room.
	 * 
	 * @return room
	 */
	public Room getRoom() {
		return this.room;
	}

	/**
	 * Sets the room.
	 * 
	 * @param room room
	 */
	public void setRoom(final Room room) {
		this.room = room;
	}

	/**
	 * Returns the level.
	 * 
	 * @return level
	 */
	public Level getLevel() {
		return this.level;
	}

	/**
	 * Sets the level.
	 * 
	 * @param level level
	 */
	public void setLevel(final Level level) {
		this.level = level;
	}

	/**
	 * Returns the section.
	 * 
	 * @return section
	 */
	public Section getSection() {
		return this.section;
	}

	/**
	 * Sets the section.
	 * 
	 * @param section section
	 */
	public void setSection(final Section section) {
		this.section = section;
	}

	/**
	 * Returns the facility area.
	 * 
	 * @return facility area
	 */
	public FacilityArea getFacilityArea() {
		return this.facilityArea;
	}

	/**
	 * Sets the facility area.
	 * 
	 * @param facilityArea facility area
	 */
	public void setFacilityArea(final FacilityArea facilityArea) {
		this.facilityArea = facilityArea;
	}

	/**
	 * Returns the incident report number.
	 * 
	 * @return incident report number
	 */
	public String getIncidentReportNumber() {
		return this.incidentReportNumber;
	}

	/**
	 * Sets the incident report number.
	 * 
	 * @param incidentReportNumber incident report number
	 */
	public void setIncidentReportNumber(final String incidentReportNumber) {
		this.incidentReportNumber = incidentReportNumber;
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
	 * Returns the incident statement category.
	 * 
	 * @return incident statement category
	 */
	public IncidentStatementCategory getCategory() {
		return this.category;
	}

	/**
	 * Sets the incident statement category.
	 * 
	 * @param category incident statement category
	 */
	public void setCategory(final IncidentStatementCategory category) {
		this.category = category;
	}

	/**
	 * Returns whether facility scene applies.
	 * 
	 * @return facility scene
	 */
	public Boolean getFacilityScene() {
		return this.facilityScene;
	}

	/**
	 * Sets whether facility scene applies.
	 * 
	 * @param facilityScene facility scene
	 */
	public void setFacilityScene(Boolean facilityScene) {
		this.facilityScene = facilityScene;
	}

	/**
	 * Returns whether housing scene applies.
	 * 
	 * @return housing scene
	 */
	public Boolean getHousingScene() {
		return this.housingScene;
	}

	/**
	 * Sets whether housing scene applies.
	 * 
	 * @param housingScene housing scene
	 */
	public void setHousingScene(final Boolean housingScene) {
		this.housingScene = housingScene;
	}

	/**
	 * Returns whether confidential informant applies.
	 * 
	 * @return confidential informant
	 */
	public Boolean getConfidentialInformant() {
		return this.confidentialInformant;
	}
	
	/**
	 * Sets whether confidential informant applies.
	 * 
	 * @param confidentialInformant confidential informant
	 */
	public void setConfidentialInformant(final Boolean confidentialInformant) {
		this.confidentialInformant = confidentialInformant;
	}
	
	/**
	 * Returns the incident report note items.
	 * 
	 * @return list of incident report note items
	 */
	public List<IncidentStatementNoteItem> getIncidentStatementNoteItems() {
		return this.incidentStatementNoteItems;
	}

	/**
	 * Sets the incident report note items.
	 * 
	 * @param incidentStatementNoteItems list of incident report note items
	 */
	public void setIncidentStatementNoteItems(
			final List<IncidentStatementNoteItem> incidentStatementNoteItems) {
		this.incidentStatementNoteItems = incidentStatementNoteItems;
	}

	/**
	 * Returns the involved party items.
	 * 
	 * @return list of involved party items
	 */
	public List<InvolvedPartyItem> getInvolvedPartyItems() {
		return this.involvedPartyItems;
	}

	/**
	 * Sets the involved party items.
	 * 
	 * @param involvedPartyItems list of involved party items
	 */
	public void setInvolvedPartyItems(
			final List<InvolvedPartyItem> involvedPartyItems) {
		this.involvedPartyItems = involvedPartyItems;
	}

	/**
	 * Returns the incident statement submission category.
	 * 
	 * @return incident statement submission category
	 */
	public IncidentStatementSubmissionCategory getSubmissionCategory() {
		return this.submissionCategory;
	}

	/**
	 * Sets the incident statement submission category.
	 * 
	 * @param submissionCategory incident statement submission category
	 */
	public void setSubmissionCategory(
			IncidentStatementSubmissionCategory submissionCategory) {
		this.submissionCategory = submissionCategory;
	}

	/**
	 * Returns the informant.
	 * 
	 * @return informant
	 */
	public Person getInformant() {
		return this.informant;
	}

	/**
	 * Sets the informant.
	 * 
	 * @param informant person
	 */
	public void setInformant(final Person informant) {
		this.informant = informant;
	}

	/**
	 * Returns the information source category.
	 * 
	 * @return information source category
	 */
	public InformationSourceCategory getInformationSourceCategory() {
		return this.informationSourceCategory;
	}

	/**
	 * Sets the information source category.
	 * 
	 * @param informationSourceCategory information source category
	 */
	public void setInformationSourceCategory(
			final InformationSourceCategory informationSourceCategory) {
		this.informationSourceCategory = informationSourceCategory;
	}

	/**
	 * Returns the information source name.
	 * 
	 * @return information source name
	 */
	public String getInformationSourceName() {
		return this.informationSourceName;
	}

	/**
	 * Sets the information source name.
	 * 
	 * @param informationSourceName information source name
	 */
	public void setInformationSourceName(final String informationSourceName) {
		this.informationSourceName = informationSourceName;
	}
}