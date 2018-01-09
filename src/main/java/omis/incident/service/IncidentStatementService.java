package omis.incident.service;

import java.util.Date;
import java.util.List;

import omis.audit.domain.VerificationSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.facility.domain.Complex;
import omis.facility.domain.Facility;
import omis.facility.domain.FacilityArea;
import omis.facility.domain.Level;
import omis.facility.domain.Room;
import omis.facility.domain.Section;
import omis.facility.domain.Unit;
import omis.incident.domain.IncidentStatement;
import omis.incident.domain.IncidentStatementCategory;
import omis.incident.domain.IncidentStatementNote;
import omis.incident.domain.IncidentStatementSubmissionCategory;
import omis.incident.domain.InformationSourceCategory;
import omis.incident.domain.InvolvedParty;
import omis.incident.domain.InvolvedPartyCategory;
import omis.incident.domain.Jurisdiction;
import omis.incident.domain.component.IncidentScene;
import omis.person.domain.Person;
import omis.user.domain.UserAccount;

/**
 * Incident report service.
 * 
 * @author: Yidong Li
 * @author: Joel Norris
 * @version: 0.1.1 (Sep 6, 2015)
 * @since: OMIS 3.0
 */

public interface IncidentStatementService {
	
	/**
	 * Creation of incident report.
	 * @param reporter reporter
	 * @param documenter user account 
	 * @param reportDate report date
	 * @param incidentDate incident date
	 * @param summary summary
	 * @param scene incident scene
	 * @param title incident report title
	 * @param jurisdiction jurisdiction
	 * @param confidentialInformant confidential informant
	 * @param informationSourceName information source name
	 * @param submissionCategory incident statement submission category
	 * @param incidentStatementCategory incidentStatementCategory
	 * @return incidentReport, new created incident report */
	IncidentStatement create(Person informant, InformationSourceCategory category,
		Person reporter, UserAccount documenter, Date reportDate,
		Date incidentDate, String summary, IncidentScene scene, String title,
		Jurisdiction jurisdiction, Boolean confidentialInformant,
		String informationSourceName,
		IncidentStatementSubmissionCategory submissionCategory,
		IncidentStatementCategory incidentStatementCategory)
		throws DuplicateEntityFoundException;
	
	/** 
	 * Creation of involved party.
	 * 
	 * @param statement incident statement 
	 * @param person person.
	 * @param verificationSignature verification signature.
	 * @param category involved party category
	 * @param narrative narrative
	 * @param name name
	 * @return involved party
	 */
	InvolvedParty createInvolvedParty(IncidentStatement statement, Person person, 
		VerificationSignature verificationSignature,
		InvolvedPartyCategory category, String narrative, String name) 
		throws DuplicateEntityFoundException;
	
	/** Creation of note
	 * @param statement incident statement 
	 * @param note note
	 * @param date date
	 * @return incidentReportNote, incident report note */
	IncidentStatementNote createNote(IncidentStatement statement, String note,
			Date date) 
		throws DuplicateEntityFoundException;
	
	/** Update of incident statement.
	 * @param statement statement
	 * @param documenter user account
	 * @param informant informant
	 * @param category informant source category
	 * @param reporter reporter
	 * @param reportDate report date.
	 * @param incidentDate incident date
	 * @param summary summary
	 * @param scene incident scene
	 * @param title incident report title
	 * @param confidentialInformant confidential informant
	 * @param jurisdiction jurisdiction
	 * @param informationSourceName information source name
	 * @param submissionCategory incident statement submission category
	 * @param incidentStatementCategory incident statement category
	 * @return incidentReport, new updated incident report */
	IncidentStatement update(IncidentStatement statement, Person informant,
			InformationSourceCategory category, Person reporter,
			UserAccount documenter, Date reportDate, Date incidentDate,
			String summary, IncidentScene scene, String title,
			Boolean confidentialInformant, Jurisdiction jurisdiction,
			String informationSourceName,
			IncidentStatementSubmissionCategory submissionCategory,
			IncidentStatementCategory incidentStatementCategory)
		throws DuplicateEntityFoundException;
	
	/** Update of note
	 * @param incidentStatementNote incident statement note 
	 * @param note note
	 * @param date date
	 * @return incidentStatementNote, incident statement note */
	IncidentStatementNote updateNote(
			IncidentStatementNote incidentStatementNote, String note, Date date) 
		throws DuplicateEntityFoundException;
	
	/** Verification of involved party
	 * @param involvedParty involved party to be verified
	 * @param verificationSignature verification signature.
	 * @return involvedParty, involved party */
	InvolvedParty verifyInvolvedParty(InvolvedParty involvedParty, 
		VerificationSignature verificationSignature); 
	
	/** Removal of incident report
	 * @param report incident report */
	void remove(IncidentStatement report); 
	
	/** Removal of involved party
	 * @param involved party */
	void removeInvolvedParty(InvolvedParty involvedPart); 
	
	/** Removal of note
	 * @param incident statement note */
	void removeNote(IncidentStatementNote note); 
	
	/** Find involved parties
	 * @param incident statement 
	 * @return statement incident statement*/
	List<InvolvedParty> findInvolvedParties(IncidentStatement statement); 
	
	/** Find incident report notes
	 * @param statement incident statement
	 * @return list of incident statement note */
	List<IncidentStatementNote> findNotes(IncidentStatement statement);

	/**
	 * Returns facilities.
	 * 
	 * @return list of facilities
	 */
	List<Facility> findFacilities();
	
	/**
	 * Returns complexes for the specified facility.
	 * 
	 * @param facility facility
	 * @return list of complexes
	 */
	List<Complex> findComplexesByFacility(final Facility facility);

	/**
	 * Returns the units for the specified facility and/or complex.
	 * 
	 * @param facility facility
	 * @param complex complex
	 * @return list of complexes
	 */
	List<Unit> findUnits(Facility facility, Complex complex);

	/**
	 * Returns the levels for the specified facility, complex, unit, and/or
	 * section.
	 * 
	 * @param facility facility
	 * @param complex complex
	 * @param unit unit
	 * @param section section
	 * @return list of levels
	 */
	List<Level> findLevels(Facility facility, Complex complex, Unit unit,
			Section section);

	/**
	 * Returns the sections for the specified facility, complex, unit, and/or
	 * level.
	 * 
	 * @param facility facility
	 * @param complex complex
	 * @param unit unit
	 * @param level level
	 * @return list of sections
	 */
	List<Section> findSections(Facility facility, Complex complex, Unit unit,
			Level level);

	/**
	 * Returns rooms for the specified facility, complex, unit, section, and/or
	 * level.
	 * 
	 * @param facility facility
	 * @param complex complex
	 * @param unit unit
	 * @param section section
	 * @param level level
	 * @return list of rooms
	 */
	List<Room> findRooms(Facility facility, Complex complex, Unit unit,
			Section section, Level level);

	/**
	 * Returns all jurisdictions.
	 * 
	 * @return list of jurisdictions
	 */
	List<Jurisdiction> findJurisdictions();
	
	/**
	 * Returns user account by user name.
	 * 
	 * @param userName user name
	 * @return user account
	 */
	UserAccount findUserAccountByUserName(String userName);
	
	/**
	 * Returns incident statement categories.
	 * 
	 * @return list of incident statement categories
	 */
	List<IncidentStatementCategory> findCategories();

	/**
	 * Returns facility areas by complex or facility.
	 * 
	 * @param facility facility
	 * @param complex complex
	 * @return list of facility areas
	 */
	List<FacilityArea> findFacilityAreas(Facility facility, Complex complex);
}