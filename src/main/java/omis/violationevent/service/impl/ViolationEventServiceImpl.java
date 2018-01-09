package omis.violationevent.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import omis.asrc.dao.AssessmentSanctionRevocationCenterDao;
import omis.asrc.domain.AssessmentSanctionRevocationCenter;
import omis.communitysupervision.dao.CommunitySupervisionOfficeDao;
import omis.communitysupervision.domain.CommunitySupervisionOffice;
import omis.condition.domain.Condition;
import omis.condition.service.delegate.ConditionDelegate;
import omis.disciplinaryCode.domain.DisciplinaryCode;
import omis.disciplinaryCode.service.delegate.DisciplinaryCodeDelegate;
import omis.document.domain.Document;
import omis.document.domain.DocumentTag;
import omis.document.service.delegate.DocumentDelegate;
import omis.document.service.delegate.DocumentTagDelegate;
import omis.exception.DuplicateEntityFoundException;
import omis.facility.domain.Facility;
import omis.facility.service.delegate.FacilityDelegate;
import omis.offender.domain.Offender;
import omis.prerelease.dao.PreReleaseCenterDao;
import omis.prerelease.domain.PreReleaseCenter;
import omis.supervision.dao.SupervisoryOrganizationDao;
import omis.supervision.domain.SupervisoryOrganization;
import omis.treatment.dao.TreatmentCenterDao;
import omis.treatment.domain.TreatmentCenter;
import omis.violationevent.domain.ConditionViolation;
import omis.violationevent.domain.DisciplinaryCodeViolation;
import omis.violationevent.domain.ViolationEvent;
import omis.violationevent.domain.ViolationEventCategory;
import omis.violationevent.domain.ViolationEventDocument;
import omis.violationevent.domain.ViolationEventNote;
import omis.violationevent.service.ViolationEventService;
import omis.violationevent.service.delegate.ConditionViolationDelegate;
import omis.violationevent.service.delegate.DisciplinaryCodeViolationDelegate;
import omis.violationevent.service.delegate.ViolationEventDelegate;
import omis.violationevent.service.delegate.ViolationEventDocumentDelegate;
import omis.violationevent.service.delegate.ViolationEventNoteDelegate;

/**
 * ViolationEventServiceImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Aug 30, 2017)
 *@since OMIS 3.0
 *
 */
public class ViolationEventServiceImpl implements ViolationEventService {
	
	private final ViolationEventDelegate violationEventDelegate;
	
	private final DisciplinaryCodeViolationDelegate
			disciplinaryCodeViolationDelegate;
	
	private final ViolationEventDocumentDelegate violationEventDocumentDelegate;
	
	private final ViolationEventNoteDelegate violationEventNoteDelegate;
	
	private final ConditionViolationDelegate conditionViolationDelegate;
	
	private final DocumentDelegate documentDelegate;
	
	private final DocumentTagDelegate documentTagDelegate;
	
	private final SupervisoryOrganizationDao supervisoryOrganizationDao;
	
	private final DisciplinaryCodeDelegate disciplinaryCodeDelegate;
	
	private final FacilityDelegate facilityDelegate;
	
	private final TreatmentCenterDao treatmentCenterDao;
	
	private final PreReleaseCenterDao preReleaseCenterDao;
	
	private final CommunitySupervisionOfficeDao communitySupervisionOfficeDao;
	
	private final AssessmentSanctionRevocationCenterDao
			assessmentSanctionRevocationCenterDao;
	
	private final ConditionDelegate conditionDelegate;
	
	/**
	 * Default Constructor for ViolationEventServiceImpl
	 * @param violationEventDelegate
	 * @param disciplinaryCodeViolationDelegate
	 * @param violationEventDocumentDelegate
	 * @param violationEventNoteDelegate
	 * @param conditionViolationDelegate
	 * @param documentDelegate
	 * @param documentTagDelegate
	 * @param supervisoryOrganizationDao
	 * @param disciplinaryCodeDelegate
	 * @param facilityDelegate
	 * @param treatmentCenterDao
	 * @param preReleaseCenterDao
	 * @param communitySupervisionOfficeDao
	 * @param assessmentSanctionRevocationCenterDao
	 * @param conditionDelegate
	 */
	public ViolationEventServiceImpl(
			final ViolationEventDelegate violationEventDelegate,
			final DisciplinaryCodeViolationDelegate disciplinaryCodeViolationDelegate,
			final ViolationEventDocumentDelegate violationEventDocumentDelegate,
			final ViolationEventNoteDelegate violationEventNoteDelegate,
			final ConditionViolationDelegate conditionViolationDelegate,
			final DocumentDelegate documentDelegate,
			final DocumentTagDelegate documentTagDelegate,
			final SupervisoryOrganizationDao supervisoryOrganizationDao,
			final DisciplinaryCodeDelegate disciplinaryCodeDelegate,
			final FacilityDelegate facilityDelegate,
			final TreatmentCenterDao treatmentCenterDao,
			final PreReleaseCenterDao preReleaseCenterDao,
			final CommunitySupervisionOfficeDao communitySupervisionOfficeDao,
			final AssessmentSanctionRevocationCenterDao
					assessmentSanctionRevocationCenterDao,
			final ConditionDelegate conditionDelegate) {
		this.violationEventDelegate = violationEventDelegate;
		this.disciplinaryCodeViolationDelegate =
				disciplinaryCodeViolationDelegate;
		this.violationEventDocumentDelegate = violationEventDocumentDelegate;
		this.violationEventNoteDelegate = violationEventNoteDelegate;
		this.conditionViolationDelegate = conditionViolationDelegate;
		this.documentDelegate = documentDelegate;
		this.documentTagDelegate = documentTagDelegate;
		this.supervisoryOrganizationDao = supervisoryOrganizationDao;
		this.disciplinaryCodeDelegate = disciplinaryCodeDelegate;
		this.facilityDelegate = facilityDelegate;
		this.treatmentCenterDao = treatmentCenterDao;
		this.preReleaseCenterDao = preReleaseCenterDao;
		this.communitySupervisionOfficeDao = communitySupervisionOfficeDao;
		this.assessmentSanctionRevocationCenterDao =
				assessmentSanctionRevocationCenterDao;
		this.conditionDelegate = conditionDelegate;
	}
	
	
	/**{@inheritDoc} */
	@Override
	public ViolationEvent createViolationEvent(final Offender offender,
			final SupervisoryOrganization jurisdiction,
			final Date date, final String details,
			final ViolationEventCategory category)
					throws DuplicateEntityFoundException {
		return this.violationEventDelegate.create(offender, jurisdiction,
				date, details, category);
	}

	/**{@inheritDoc} */
	@Override
	public ViolationEvent updateViolationEvent(
			final ViolationEvent violationEvent,
			final SupervisoryOrganization jurisdiction,
			final Date date, final String details,
			final ViolationEventCategory category)
					throws DuplicateEntityFoundException {
		return this.violationEventDelegate.update(violationEvent, jurisdiction,
				date, details, category);
	}

	/**{@inheritDoc} */
	@Override
	public void removeViolationEvent(final ViolationEvent violationEvent) {
		this.violationEventDelegate.remove(violationEvent);
	}

	/**{@inheritDoc} */
	@Override
	public List<ViolationEvent> findViolationEventsByOffender(
			final Offender offender) {
		return this.violationEventDelegate.findByOffender(offender);
	}

	/**{@inheritDoc} */
	@Override
	public DisciplinaryCodeViolation createDisciplinaryCodeViolation(
			final DisciplinaryCode disciplinaryCode,
			final ViolationEvent violationEvent)
					throws DuplicateEntityFoundException {
		return this.disciplinaryCodeViolationDelegate
				.create(disciplinaryCode, violationEvent);
	}

	/**{@inheritDoc} */
	@Override
	public DisciplinaryCodeViolation updateDisciplinaryCodeViolation(
			final DisciplinaryCodeViolation disciplinaryCodeViolation,
			final DisciplinaryCode disciplinaryCode)
					throws DuplicateEntityFoundException {
		return this.disciplinaryCodeViolationDelegate
				.update(disciplinaryCodeViolation, disciplinaryCode);
	}

	/**{@inheritDoc} */
	@Override
	public void removeDisciplinaryCodeViolation(
			final DisciplinaryCodeViolation disciplinaryCodeViolation) {
		this.disciplinaryCodeViolationDelegate.remove(disciplinaryCodeViolation);
	}

	/**{@inheritDoc} */
	@Override
	public List<DisciplinaryCodeViolation> 
		findDisciplinaryCodeViolationsByViolationEvent(
				final ViolationEvent violationEvent) {
		return this.disciplinaryCodeViolationDelegate
				.findByViolationEvent(violationEvent);
	}

	/**{@inheritDoc} */
	@Override
	public ViolationEventDocument createViolationEventDocument(
			final Document document, final ViolationEvent violationEvent)
					throws DuplicateEntityFoundException {
		return this.violationEventDocumentDelegate
				.create(document, violationEvent);
	}

	/**{@inheritDoc} */
	@Override
	public ViolationEventDocument updateViolationEventDocument(
			final ViolationEventDocument violationEventDocument,
			final Document document)
					throws DuplicateEntityFoundException {
		return this.violationEventDocumentDelegate
				.update(violationEventDocument, document);
	}

	/**{@inheritDoc} */
	@Override
	public void removeViolationEventDocument(
			final ViolationEventDocument violationEventDocument) {
		this.violationEventDocumentDelegate.remove(violationEventDocument);
	}

	/**{@inheritDoc} */
	@Override
	public List<ViolationEventDocument>
		findViolationEventDocumentsByViolationEvent(
				final ViolationEvent violationEvent) {
		return this.violationEventDocumentDelegate
				.findByViolationEvent(violationEvent);
	}

	/**{@inheritDoc} */
	@Override
	public ViolationEventNote createViolationEventNote(final Date date,
			final String description, final ViolationEvent violationEvent)
					throws DuplicateEntityFoundException {
		return this.violationEventNoteDelegate
				.create(date, description, violationEvent);
	}

	/**{@inheritDoc} */
	@Override
	public ViolationEventNote updateViolationEventNote(
			final ViolationEventNote violationEventNote, final Date date,
			final String description) throws DuplicateEntityFoundException {
		return this.violationEventNoteDelegate
				.update(violationEventNote, date, description);
	}

	/**{@inheritDoc} */
	@Override
	public void removeViolationEventNote(
			final ViolationEventNote violationEventNote) {
		this.violationEventNoteDelegate.remove(violationEventNote);
	}

	/**{@inheritDoc} */
	@Override
	public List<ViolationEventNote> findViolationEventNotesByViolationEvent(
			final ViolationEvent violationEvent) {
		return this.violationEventNoteDelegate
				.findByViolationEvent(violationEvent);
	}

	/**{@inheritDoc} */
	@Override
	public Document createDocument(final Date documentDate,
			final String filename, final String fileExtension,
			final String title) throws DuplicateEntityFoundException {
		return this.documentDelegate.create(documentDate, filename,
				fileExtension, title);
	}

	/**{@inheritDoc} */
	@Override
	public Document updateDocument(final Document document, final String title,
			final Date documentDate) throws DuplicateEntityFoundException {
		return this.documentDelegate.update(document, title, documentDate);
	}

	/**{@inheritDoc} */
	@Override
	public void removeDocument(final Document document) {
		this.documentDelegate.remove(document);
	}

	/**{@inheritDoc} */
	@Override
	public DocumentTag createDocumentTag(final Document document,
			final String name) throws DuplicateEntityFoundException {
		return this.documentTagDelegate.tagDocument(document, name);
	}

	/**{@inheritDoc} */
	@Override
	public DocumentTag updateDocumentTag(final DocumentTag documentTag,
			final String name) throws DuplicateEntityFoundException {
		return this.documentTagDelegate.update(documentTag, name);
	}

	/**{@inheritDoc} */
	@Override
	public void removeDocumentTag(final DocumentTag documentTag) {
		this.documentTagDelegate.removeTag(documentTag);
	}

	/**{@inheritDoc} */
	@Override
	public List<DisciplinaryCode>
		findDisciplinaryCodesByJurisdictionAndEventDate(
				final SupervisoryOrganization jurisdiction,
				final Date eventDate) {
		return this.disciplinaryCodeDelegate
				.findBySupervisoryOrganizationAndDate(jurisdiction, eventDate);
	}

	/**{@inheritDoc} */
	@Override
	public List<SupervisoryOrganization> findSupervisoryOrganizations() {
		return this.supervisoryOrganizationDao.findAll();
	}

	/**{@inheritDoc} */
	@Override
	public List<DocumentTag> findDocumentTagsByDocument(
			final Document document) {
		return this.documentTagDelegate.findByDocument(document);
	}


	/**{@inheritDoc} */
	@Override
	public List<SupervisoryOrganization> findFacilitySupervisoryOrganizations() {
		List<SupervisoryOrganization> facilitySupervisoryOrganizations =
				new ArrayList<SupervisoryOrganization>();
		
		List<Facility> facilities = this.facilityDelegate.findAll();
		List<SupervisoryOrganization> supervisoryOrganizations =
				this.supervisoryOrganizationDao.findAll();
		
		for(SupervisoryOrganization supervisoryOrganization :
			supervisoryOrganizations){
			for(Facility facility : facilities){
				if(facility.getLocation().getOrganization()
						.equals(supervisoryOrganization)){
					facilitySupervisoryOrganizations
						.add(supervisoryOrganization);
				}
			}
		}
		
		return facilitySupervisoryOrganizations;
	}


	/**{@inheritDoc} */
	@Override
	public ConditionViolation createConditionViolation(
			final Condition condition, final ViolationEvent violationEvent)
			throws DuplicateEntityFoundException {
		return this.conditionViolationDelegate.create(condition, violationEvent);
	}


	/**{@inheritDoc} */
	@Override
	public ConditionViolation updateConditionViolation(
			final ConditionViolation conditionViolation,
			final Condition condition)
					throws DuplicateEntityFoundException {
		return this.conditionViolationDelegate.update(conditionViolation,
				condition);
	}


	/**{@inheritDoc} */
	@Override
	public void removeConditionViolation(
			final ConditionViolation conditionViolation) {
		this.conditionViolationDelegate.remove(conditionViolation);
	}


	/**{@inheritDoc} */
	@Override
	public List<Condition> findConditionsByOffenderAndEventDate(
			final Offender offender, final  Date eventDate) {
		return this.conditionDelegate
				.findByOffenderAndEffectiveDate(offender, eventDate);
	}


	/**{@inheritDoc} */
	@Override
	public List<ConditionViolation> findConditionViolationsByViolationEvent(
			final ViolationEvent violationEvent) {
		return this.conditionViolationDelegate
				.findByViolationEvent(violationEvent);
	}


	/**{@inheritDoc} */
	@Override
	public List<SupervisoryOrganization>
			findTreatmentCenterSupervisoryOrganizations() {
		
		List<SupervisoryOrganization> treatmentCenterSupervisoryOrganizations =
				new ArrayList<SupervisoryOrganization>();
		
		List<TreatmentCenter> treatmentCenters =
				this.treatmentCenterDao.findAll();
		List<SupervisoryOrganization> supervisoryOrganizations =
				this.supervisoryOrganizationDao.findAll();
		
		for(SupervisoryOrganization supervisoryOrganization :
			supervisoryOrganizations){
			for(TreatmentCenter treatmentCenter : treatmentCenters){
				if(treatmentCenter.getLocation().getOrganization()
						.equals(supervisoryOrganization)){
					treatmentCenterSupervisoryOrganizations
						.add(supervisoryOrganization);
				}
			}
		}
		
		return treatmentCenterSupervisoryOrganizations;
	}


	/**{@inheritDoc} */
	@Override
	public List<SupervisoryOrganization>
			findPreReleaseCenterSupervisoryOrganizations() {
		List<SupervisoryOrganization> preReleaseCenterSupervisoryOrganizations =
				new ArrayList<SupervisoryOrganization>();
		
		List<PreReleaseCenter> preReleaseCenters =
				this.preReleaseCenterDao.findAll();
		List<SupervisoryOrganization> supervisoryOrganizations =
				this.supervisoryOrganizationDao.findAll();
		
		for(SupervisoryOrganization supervisoryOrganization :
			supervisoryOrganizations){
			for(PreReleaseCenter preReleaseCenter : preReleaseCenters){
				if(preReleaseCenter.getLocation().getOrganization()
						.equals(supervisoryOrganization)){
					preReleaseCenterSupervisoryOrganizations
						.add(supervisoryOrganization);
				}
			}
		}
		
		return preReleaseCenterSupervisoryOrganizations;
	}


	/**{@inheritDoc} */
	@Override
	public List<SupervisoryOrganization> 
			findCommunitySupervisionOfficeSupervisoryOrganizations() {
		List<SupervisoryOrganization>
			communitySupervisionOfficeSupervisoryOrganizations =
				new ArrayList<SupervisoryOrganization>();
		
		List<CommunitySupervisionOffice> communitySupervisionOffices =
				this.communitySupervisionOfficeDao.findAll();
		List<SupervisoryOrganization> supervisoryOrganizations =
				this.supervisoryOrganizationDao.findAll();
		
		for(SupervisoryOrganization supervisoryOrganization :
			supervisoryOrganizations){
			for(CommunitySupervisionOffice communitySupervisionOffice :
				communitySupervisionOffices){
				if(communitySupervisionOffice.getLocation().getOrganization()
						.equals(supervisoryOrganization)){
					communitySupervisionOfficeSupervisoryOrganizations
						.add(supervisoryOrganization);
				}
			}
		}
		
		return communitySupervisionOfficeSupervisoryOrganizations;
	}


	/**{@inheritDoc} */
	@Override
	public List<SupervisoryOrganization>
			findAssessmentSanctionRevocationCenterSupervisoryOrganizations() {
		List<SupervisoryOrganization>
				assessmentSanctionRevocationCenterSupervisoryOrganizations =
					new ArrayList<SupervisoryOrganization>();
		
		List<AssessmentSanctionRevocationCenter>
				assessmentSanctionRevocationCenters =
					this.assessmentSanctionRevocationCenterDao.findAll();
		List<SupervisoryOrganization> supervisoryOrganizations =
				this.supervisoryOrganizationDao.findAll();
		
		for(SupervisoryOrganization supervisoryOrganization :
			supervisoryOrganizations){
			for(AssessmentSanctionRevocationCenter
					assessmentSanctionRevocationCenter :
						assessmentSanctionRevocationCenters){
				if(assessmentSanctionRevocationCenter.getLocation()
						.getOrganization().equals(supervisoryOrganization)){
					assessmentSanctionRevocationCenterSupervisoryOrganizations
						.add(supervisoryOrganization);
				}
			}
		}
		
		return assessmentSanctionRevocationCenterSupervisoryOrganizations;
	}

	
}
