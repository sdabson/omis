package omis.incident.service.impl;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.audit.domain.VerificationSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.facility.dao.ComplexDao;
import omis.facility.dao.FacilityAreaDao;
import omis.facility.dao.FacilityDao;
import omis.facility.dao.LevelDao;
import omis.facility.dao.RoomDao;
import omis.facility.dao.SectionDao;
import omis.facility.dao.UnitDao;
import omis.facility.domain.Complex;
import omis.facility.domain.Facility;
import omis.facility.domain.FacilityArea;
import omis.facility.domain.Level;
import omis.facility.domain.Room;
import omis.facility.domain.Section;
import omis.facility.domain.Unit;
import omis.incident.dao.IncidentStatementCategoryDao;
import omis.incident.dao.IncidentStatementDao;
import omis.incident.dao.IncidentStatementNoteDao;
import omis.incident.dao.InvolvedPartyDao;
import omis.incident.dao.JurisdictionDao;
import omis.incident.domain.IncidentStatement;
import omis.incident.domain.IncidentStatementCategory;
import omis.incident.domain.IncidentStatementNote;
import omis.incident.domain.IncidentStatementSubmissionCategory;
import omis.incident.domain.InformationSource;
import omis.incident.domain.InformationSourceCategory;
import omis.incident.domain.InvolvedParty;
import omis.incident.domain.InvolvedPartyCategory;
import omis.incident.domain.Jurisdiction;
import omis.incident.domain.component.IncidentScene;
import omis.incident.service.IncidentStatementService;
import omis.instance.factory.InstanceFactory;
import omis.person.domain.Person;
import omis.user.domain.UserAccount;
import omis.user.service.delegate.UserAccountDelegate;

/**
 * Implementation of incident service.
 * 
 * @author: Yidong Li
 * @author: Joel Norris
 * @version: 0.1.1 (Sep 6, 2015)
 * @since: OMIS 3.0
 */
public class IncidentStatementServiceImpl implements IncidentStatementService {
	
	/* Data access objects */
	
	private final IncidentStatementDao incidentReportDao;
	private final InvolvedPartyDao involvedPartyDao;
	private final IncidentStatementNoteDao incidentStatementNoteDao;
	private final FacilityDao facilityDao;
	private final ComplexDao complexDao;
	private final UnitDao unitDao;
	private final SectionDao sectionDao;
	private final LevelDao levelDao;
	private final RoomDao roomDao;
	private final JurisdictionDao jurisdictionDao;
	private final FacilityAreaDao facilityAreaDao;
	private final IncidentStatementCategoryDao incidentStatementCategoryDao;
	
	/* Instance factories. */
	
	private final InstanceFactory<IncidentStatement>
	incidentStatementInstanceFactory;
	private final InstanceFactory<InvolvedParty>
	involvedPartyInstanceFactory;
	private final InstanceFactory<IncidentStatementNote>
	incidentStatementNoteInstanceFactory;
	
	/* Audit Component Retriever */
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/* Delegates. */
	
	private final UserAccountDelegate userAccountDelegate;
	
	/**
	 * Instantiates an instance of incident service implementation.
	 * 
	 * @param incidentReportDao incident report data access object
	 * @param involvedPartyDao involved party data access object
	 * @param incidentReportNoteDao incident report note data access object
	 * @param personCategoryDao person category data access object
	 * @param facilityDao facility data access object
	 * @param complexDao complex data access object
	 * @param unitDao unit data access object
	 * @param sectionDao section data access object
	 * @param levelDao level data access object
	 * @param roomDao room data access object
	 * @param jurisdictionDao jurisdiction data access object
	 * @param facilityAreaDao facility area data access object
	 * @param incidentStatementInstanceFactory incident report instance factory
	 * @param involvedPartyInstanceFactory involved party instance factory
	 * @param incidentStatementNoteInstanceFactory incident report note instance
	 * factory
	 * @param auditComponentRetriever audit component retriever
	 * @param userAccountDelegate user account delegate
	 */
	public IncidentStatementServiceImpl(
		final IncidentStatementDao incidentStatementDao,
		final InvolvedPartyDao involvedPartyDao,
		final IncidentStatementNoteDao incidentStatementNoteDao,
		final FacilityDao facilityDao,
		final ComplexDao complexDao,
		final UnitDao unitDao,
		final SectionDao sectionDao,
		final LevelDao levelDao,
		final RoomDao roomDao,
		final JurisdictionDao jurisdictionDao,
		final FacilityAreaDao facilityAreaDao,
		final IncidentStatementCategoryDao incidentStatementCategoryDao,
		final InstanceFactory<IncidentStatement>
			incidentStatementInstanceFactory,
		final InstanceFactory<InvolvedParty> involvedPartyInstanceFactory,
		final InstanceFactory<IncidentStatementNote>
		incidentStatementNoteInstanceFactory,
		final AuditComponentRetriever auditComponentRetriever,
		final UserAccountDelegate userAccountDelegate) {
			this.incidentReportDao = incidentStatementDao;
			this.involvedPartyDao = involvedPartyDao;
			this.incidentStatementNoteDao = incidentStatementNoteDao;
			this.facilityDao = facilityDao;
			this.complexDao = complexDao;
			this.unitDao = unitDao;
			this.sectionDao = sectionDao;
			this.levelDao = levelDao;
			this.roomDao = roomDao;
			this.jurisdictionDao = jurisdictionDao;
			this.facilityAreaDao = facilityAreaDao;
			this.incidentStatementCategoryDao = incidentStatementCategoryDao;
			this.incidentStatementInstanceFactory
				= incidentStatementInstanceFactory;
			this.involvedPartyInstanceFactory
				= involvedPartyInstanceFactory;
			this.incidentStatementNoteInstanceFactory
				= incidentStatementNoteInstanceFactory;
			this.auditComponentRetriever
				= auditComponentRetriever;
			this.userAccountDelegate = userAccountDelegate;
	}	

	/** {@inheritDoc} */
	@Override
	public IncidentStatement create(final Person informant,
		final InformationSourceCategory informationSourceCategory,
		final Person reporter, final UserAccount documenter,
		final Date reportDate, final Date incidentDate, final String summary,
		final IncidentScene scene, final String title,
		final Jurisdiction jurisdiction, final Boolean confidentialInformant,
		final String informationSourceName,
		final IncidentStatementSubmissionCategory submissionCategory,
		final IncidentStatementCategory incidentStatementCategory)
			throws DuplicateEntityFoundException {
		if (this.incidentReportDao.find(reporter,
				reportDate,
			incidentDate, summary, scene)!=null){
			throw new DuplicateEntityFoundException(
					"Duplicate incident report found.");
		}
		IncidentStatement incidentReport 
			= this.incidentStatementInstanceFactory.createInstance();
		incidentReport.setNumber(this.incidentReportDao.generateReportNumber());
		incidentReport.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		this.populateReport(incidentReport, confidentialInformant, title,
				incidentDate, scene, summary, reportDate,
				new InformationSource(informant, informationSourceCategory,
						informationSourceName),
				documenter, reporter, jurisdiction,
				submissionCategory,
				incidentStatementCategory);
		return this.incidentReportDao.makePersistent(incidentReport);
	}	

	
	/** {@inheritDoc} */
	@Override
	public InvolvedParty createInvolvedParty(final IncidentStatement report, 
			final Person person,
			final VerificationSignature verificationSignature, 
			final InvolvedPartyCategory category, final String narrative,
			final String name)
		throws DuplicateEntityFoundException{
		if (this.involvedPartyDao.find(report, person) != null){
			throw new DuplicateEntityFoundException("Involved party"
				+ " Already Exist.");
		}
		InvolvedParty involvedParty 
		= this.involvedPartyInstanceFactory.createInstance();
		involvedParty.setPerson(person);
		involvedParty.setStatement(report);
		involvedParty.setVerificationSignature(verificationSignature);
		involvedParty.setCategory(category);
		involvedParty.setNarrative(narrative);
		involvedParty.setName(name);
		return involvedPartyDao.makePersistent(involvedParty);
	}
	
	/** {@inheritDoc} */
	@Override
	public IncidentStatementNote createNote(final IncidentStatement report, 
		final String note, final Date date) 
		throws DuplicateEntityFoundException{
		if (this.incidentStatementNoteDao.findIncidentStatementNote(report, 
			note, date)!=null){
				throw new DuplicateEntityFoundException(
						"Involved party already exists.");
			}
			IncidentStatementNote incidentReportNote  
			= this.incidentStatementNoteInstanceFactory.createInstance();
			incidentReportNote.setDate(date);
			incidentReportNote.setNote(note);
			incidentReportNote.setStatement(report);
			incidentReportNote.setCreationSignature(new CreationSignature(
					this.auditComponentRetriever.retrieveUserAccount(),
					this.auditComponentRetriever.retrieveDate()));
			incidentReportNote.setUpdateSignature(new UpdateSignature(
					this.auditComponentRetriever.retrieveUserAccount(), 
					this.auditComponentRetriever.retrieveDate()));
			incidentStatementNoteDao.makePersistent(incidentReportNote);
			return incidentReportNote;
	}
	
	/** {@inheritDoc} */
	@Override
	public IncidentStatement update(final IncidentStatement report, 
			final Person informant, final InformationSourceCategory category,
			final Person reporter, final UserAccount documenter,
			final Date reportDate, final Date incidentDate,
			final String summary, final IncidentScene scene, final String title,
			final Boolean confidentialInformant,
			final Jurisdiction jurisdiction,
			final String informationSourceName,
			final IncidentStatementSubmissionCategory submissionCategory,
			final IncidentStatementCategory incidentStatementCategory)
		throws DuplicateEntityFoundException {
		if (this.incidentReportDao.findExcluding(report, reporter, 
				reportDate, incidentDate, summary, scene) !=null){
			throw new DuplicateEntityFoundException(
					"Incident report already exists.");
		}
		this.populateReport(report, confidentialInformant, title, incidentDate,
				scene, summary, reportDate,
				new InformationSource(
						informant, category, informationSourceName),
				documenter, reporter, jurisdiction,
				submissionCategory, incidentStatementCategory);
		return this.incidentReportDao.makePersistent(report);
	}
	
	
	/** {@inheritDoc} */
	@Override
	public IncidentStatementNote updateNote(
			final IncidentStatementNote incidentReportNote, final String note,
			final Date date)
		throws DuplicateEntityFoundException {
		if (this.incidentStatementNoteDao
				.findIncidentStatementNoteExcluding(incidentReportNote, note,
						date)!=null){
			throw new DuplicateEntityFoundException(
					"Incident report note already exist.");
		}
		incidentReportNote.setDate(date);
		incidentReportNote.setNote(note);
		incidentReportNote.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
		return this.incidentStatementNoteDao.makePersistent(incidentReportNote);
		
	}
	
	/** {@inheritDoc} */
	@Override
	public InvolvedParty verifyInvolvedParty(final InvolvedParty involvedParty, 
		final VerificationSignature verificationSignature){
		involvedParty.setVerificationSignature(verificationSignature);
		return this.involvedPartyDao.makePersistent(involvedParty);
	}
	
	/** {@inheritDoc} */
	@Override
	public void remove(final IncidentStatement report){
		this.incidentReportDao.makeTransient(report);
	}
	
	/** {@inheritDoc} */
	@Override
	public void removeInvolvedParty(final InvolvedParty involvedPart){
		this.involvedPartyDao.makeTransient(involvedPart);
	}
	
	/** {@inheritDoc} */
	@Override
	public void removeNote(final IncidentStatementNote note){
		this.incidentStatementNoteDao.makeTransient(note);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<InvolvedParty> findInvolvedParties(
			final IncidentStatement report){
		return this.involvedPartyDao.findInvolvedParties(report);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<IncidentStatementNote> findNotes(
			final IncidentStatement report){
		return this.incidentStatementNoteDao.findIncidentStatementNotes(report);
	}

	/** {@inheritDoc} */
	@Override
	public List<Facility> findFacilities() {
		return this.facilityDao.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public List<Complex> findComplexesByFacility(final Facility facility) {
		return this.complexDao.findByFacility(facility);
	}

	/** {@inheritDoc} */
	@Override
	public List<Jurisdiction> findJurisdictions() {
		return this.jurisdictionDao.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public List<Unit> findUnits(final Facility facility,
			final Complex complex) {
		return this.unitDao.findUnits(facility, complex);
	}

	/** {@inheritDoc} */
	@Override
	public List<Level> findLevels(final Facility facility,
			final Complex complex,
			final Unit unit, final Section section) {
		return this.levelDao.findLevels(facility, complex, unit, section);
	}

	/** {@inheritDoc} */
	@Override
	public List<Section> findSections(final Facility facility,
			final Complex complex, final Unit unit, final Level level) {
		return this.sectionDao.findSections(facility, complex, unit, level);
	}

	/** {@inheritDoc} */
	@Override
	public List<Room> findRooms(final Facility facility, final Complex complex,
			final Unit unit, final Section section, final Level level) {
		return this.roomDao.findRooms(facility, complex, unit, section, level);
	}

	/** {@inheritDoc} */
	@Override
	public UserAccount findUserAccountByUserName(final String userName) {
		return this.userAccountDelegate.findByUsername(userName);
	}

	/** {@inheritDoc} */
	@Override
	public List<IncidentStatementCategory> findCategories() {
		return this.incidentStatementCategoryDao.findAll();
	}
	
	/** {@inheritDoc} */
	@Override
	public List<FacilityArea> findFacilityAreas(final Facility facility,
			final Complex complex) {
		if(complex != null) {
			return this.facilityAreaDao.findFacilityAreasByComplex(complex);
		} else {
			return this.facilityAreaDao.findFacilityAreasByFacility(facility);
		}
	}
	
	/* Helper methods. */
	
	/*
	 * Populates the specified incident report.
	 * 
	 * @param report incident report
	 * @param confidentialInformant confidential informant
	 * @param title title
	 * @param incidentDate incident date
	 * @param scene incident scene
	 * @param summary summary
	 * @param reportDate report date
	 * @param source information source
	 * @param documenter documenter
	 * @param reporter reporter
	 * @param jurisdiction jurisdiction
	 * @param submissionCategory incident statement submission category
	 * @param incidentStatementCategory incident statement category
	 * @return populated incident report
	 */
	private IncidentStatement populateReport(final IncidentStatement statement,
			final Boolean confidentialInformant, final String title,
			final Date incidentDate, final IncidentScene scene,
			final String summary, Date statementDate,
			final InformationSource source, final UserAccount documenter,
			final Person reporter, final Jurisdiction jurisdiction,
			final IncidentStatementSubmissionCategory submissionCategory,
			final IncidentStatementCategory incidentStatementCategory) {
		statement.setConfidentialInformant(confidentialInformant);
		statement.setTitle(title);
		statement.setIncidentDate(incidentDate); 
		statement.setScene(scene);
		statement.setStatementDate(statementDate);
		statement.setInformationSource(source);
		statement.setDocumenter(documenter);
		statement.setSummary(summary);
		statement.setReporter(reporter);
		statement.setJurisdiction(jurisdiction);
		statement.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		statement.setSubmissionCategory(submissionCategory);
		statement.setCategory(incidentStatementCategory);
		return statement;
	}
}