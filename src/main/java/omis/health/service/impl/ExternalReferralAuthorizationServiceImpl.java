package omis.health.service.impl;

import java.util.Date;
import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.facility.domain.Facility;
import omis.health.dao.ExternalReferralAuthorizationDao;
import omis.health.dao.ExternalReferralAuthorizationRequestDao;
import omis.health.dao.ExternalReferralReasonDao;
import omis.health.dao.ExternalReferralRequestAuthorizationRequirementDao;
import omis.health.dao.MedicalFacilityDao;
import omis.health.dao.ProviderAssignmentDao;
import omis.health.domain.ExternalReferralAuthorization;
import omis.health.domain.ExternalReferralAuthorizationRequest;
import omis.health.domain.ExternalReferralAuthorizationStatus;
import omis.health.domain.ExternalReferralMedicalPanelReviewDecisionStatus;
import omis.health.domain.ExternalReferralReason;
import omis.health.domain.ExternalReferralRequestAuthorizationRequirement;
import omis.health.domain.HealthRequest;
import omis.health.domain.HealthRequestCategory;
import omis.health.domain.MedicalFacility;
import omis.health.domain.ProviderAssignment;
import omis.health.domain.component.ExternalReferralMedicalPanelReviewDecision;
import omis.health.exception.HealthRequestException;
import omis.health.exception.ProviderException;
import omis.health.exception.ReferralAuthorizationException;
import omis.health.service.ExternalReferralAuthorizationService;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;
import omis.person.domain.Person;

/**
 * Implementation of service for authorization of external referrals.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (May 7, 2014)
 * @since OMIS 3.0
 */
public class ExternalReferralAuthorizationServiceImpl
		implements ExternalReferralAuthorizationService {

	private final ExternalReferralAuthorizationRequestDao
	externalReferralAuthorizationRequestDao;
	
	private final InstanceFactory<ExternalReferralAuthorizationRequest>
	externalReferralAuthorizationRequestInstanceFactory;
	
	private final ExternalReferralAuthorizationDao
	externalReferralAuthorizationDao;
	
	private final InstanceFactory<ExternalReferralAuthorization>
	externalReferralAuthorizationInstanceFactory;
	
	private final ExternalReferralReasonDao externalReferralReasonDao;
	
	private final ProviderAssignmentDao providerAssignmentDao;

	private final ExternalReferralRequestAuthorizationRequirementDao
	externalReferralRequestAuthorizationRequirementDao;
	
	private final InstanceFactory
		<ExternalReferralRequestAuthorizationRequirement>
	externalReferralRequestAuthorizationRequirementInstanceFactory;
	
	private final MedicalFacilityDao medicalFacilityDao;
	
	/**
	 * Instantiates an implementation of service for authorization of
	 * external referrals.
	 * 
	 * @param externalReferralAuthorizationRequestDao data access object for
	 * external referral authorizations
	 * @param externalReferralAuthorizationRequestInstanceFactory
	 * instance factory for external referral authorization requests
	 * @param externalReferralAuthorizationDao data access object for external
	 * referral authorizations
	 * @param externalReferralAuthorizationInstanceFactory instance factory
	 * for external referral authorizations
	 * @param providerAssignmentDao data access object for provider assignments
	 * @param externalReferralReasonDao data access object for external
	 * referral reasons
	 * @param externalReferralRequestAuthorizationRequirementDao data access
	 * object for external referral request authorization requirements
	 * @param externalReferralRequestAuthorizationRequirementInstanceFactory
	 * instance factory for external referral request authorization
	 * requirements
	 * @param medicalFacilityDao data access object for medical facility
	 */
	public ExternalReferralAuthorizationServiceImpl(
			final ExternalReferralAuthorizationRequestDao
			externalReferralAuthorizationRequestDao,
			final InstanceFactory<ExternalReferralAuthorizationRequest>
			externalReferralAuthorizationRequestInstanceFactory,
			final ExternalReferralAuthorizationDao
			externalReferralAuthorizationDao,
			final InstanceFactory<ExternalReferralAuthorization>
			externalReferralAuthorizationInstanceFactory,
			final ProviderAssignmentDao providerAssignmentDao,
			final ExternalReferralReasonDao externalReferralReasonDao,
			final ExternalReferralRequestAuthorizationRequirementDao
			externalReferralRequestAuthorizationRequirementDao,
			final InstanceFactory
				<ExternalReferralRequestAuthorizationRequirement>
			externalReferralRequestAuthorizationRequirementInstanceFactory,
			final MedicalFacilityDao medicalFacilityDao) {
		this.externalReferralAuthorizationRequestDao
			= externalReferralAuthorizationRequestDao;
		this.externalReferralAuthorizationRequestInstanceFactory
			= externalReferralAuthorizationRequestInstanceFactory;
		this.externalReferralAuthorizationDao
			= externalReferralAuthorizationDao;
		this.externalReferralAuthorizationInstanceFactory
			= externalReferralAuthorizationInstanceFactory;
		this.providerAssignmentDao = providerAssignmentDao;
		this.externalReferralReasonDao = externalReferralReasonDao;
		this.externalReferralRequestAuthorizationRequirementDao
			= externalReferralRequestAuthorizationRequirementDao;
		this.externalReferralRequestAuthorizationRequirementInstanceFactory
			= externalReferralRequestAuthorizationRequirementInstanceFactory;
		this.medicalFacilityDao = medicalFacilityDao;
	}
	
	/** {@inheritDoc} */
	@Override
	public ExternalReferralAuthorizationRequest requestAuthorization(
			final Offender offender, final Date date,
			final ProviderAssignment providerAssignment,
			final MedicalFacility medicalFacility,
			final Facility facility, final ExternalReferralReason reason,
			final String reasonNotes,
			final ProviderAssignment referredByProviderAssignment,
			final Date referredDate)
			throws DuplicateEntityFoundException {
		ExternalReferralAuthorizationRequest request
			= this.externalReferralAuthorizationRequestInstanceFactory
				.createInstance();
		request.setOffender(offender);
		request.setDate(date);
		request.setProviderAssignment(providerAssignment);
		request.setMedicalFacility(medicalFacility);
		request.setFacility(facility);
		request.setReason(reason);
		request.setReasonNotes(reasonNotes);
		request.setReferredByProviderAssignment(referredByProviderAssignment);
		request.setReferredDate(referredDate);
		return this.externalReferralAuthorizationRequestDao
				.makePersistent(request);
	}

	/** {@inheritDoc} */
	@Override
	public ExternalReferralAuthorizationRequest
	requestAuthorizationFromHealthRequest(
			final HealthRequest healthRequest, final Date date,
			final ProviderAssignment providerAssignment,
			final MedicalFacility medicalFacility,
			final ExternalReferralReason reason,
			final String reasonNotes,
			final ProviderAssignment referredByProviderAssignment,
			final Date referredDate)
					throws DuplicateEntityFoundException,
					HealthRequestException {
		if (!HealthRequestCategory.EXTERNAL_MEDICAL
				.equals(healthRequest.getCategory())) {
			throw new HealthRequestException(
					"Request is not for external referral");
		}
		if (this.externalReferralRequestAuthorizationRequirementDao
				.find(healthRequest) != null) {
			throw new HealthRequestException(
				"External referral authorization request exists for request");
		}
		ExternalReferralAuthorizationRequest request
			= this.externalReferralAuthorizationRequestInstanceFactory
				.createInstance();
		request.setOffender(healthRequest.getOffender());
		request.setDate(date);
		request.setProviderAssignment(providerAssignment);
		request.setMedicalFacility(medicalFacility);
		request.setFacility(healthRequest.getFacility());
		request.setReason(reason);
		request.setReasonNotes(reasonNotes);
		request.setReferredByProviderAssignment(referredByProviderAssignment);
		request.setReferredDate(referredDate);
		request = this.externalReferralAuthorizationRequestDao
				.makePersistent(request);
		ExternalReferralRequestAuthorizationRequirement requirement =
			this.externalReferralRequestAuthorizationRequirementInstanceFactory
				.createInstance();
		requirement.setAuthorizationRequest(request);
		requirement.setReferralRequest(healthRequest);
		this.externalReferralRequestAuthorizationRequirementDao
			.makePersistent(requirement);
		return request;
	}

	/** {@inheritDoc} */
	@Override
	public ExternalReferralAuthorizationRequest updateRequest(
			ExternalReferralAuthorizationRequest request,
			ProviderAssignment providerAssignment,
			MedicalFacility medicalFacility, ExternalReferralReason reason,
			final String reasonNotes,
			final ProviderAssignment referredByProviderAssignment,
			final Date referredDate)
			throws DuplicateEntityFoundException {
		request.setProviderAssignment(providerAssignment);
		request.setMedicalFacility(medicalFacility);
		request.setReason(reason);
		request.setReasonNotes(reasonNotes);
		request.setReferredByProviderAssignment(referredByProviderAssignment);
		request.setReferredDate(referredDate);
		return this.externalReferralAuthorizationRequestDao
			.makePersistent(request);
	}
	
	/** {@inheritDoc} */
	@Override
	public ExternalReferralAuthorization authorizeRequest(
			final ExternalReferralAuthorizationRequest request,
			final Date decisionDate, final Person authorizedBy,
			final String notes)
					throws ReferralAuthorizationException {
		if (this.externalReferralAuthorizationDao
				.findByRequest(request) != null) {
			throw new ReferralAuthorizationException(
					"Referral already authorized");
		}
		ExternalReferralAuthorization authorization
			= this.externalReferralAuthorizationInstanceFactory
				.createInstance();
		authorization.setRequest(request);
		return this.authorize(authorization, decisionDate, authorizedBy, notes);
	}
	
	/** {@inheritDoc} */
	@Override
	public ExternalReferralAuthorization authorize(
			final ExternalReferralAuthorization authorization,
			final Date decisionDate, final Person authorizedBy,
			final String notes) {
		authorization.setAuthorizedBy(authorizedBy);
		authorization.setDecisionDate(decisionDate);
		authorization.setNotes(notes);
		authorization.setStatus(ExternalReferralAuthorizationStatus.APPROVED);
		return this.externalReferralAuthorizationDao
				.makePersistent(authorization);
	}

	/** {@inheritDoc} */
	@Override
	public void removeRequestAuthorization(
			final ExternalReferralAuthorizationRequest request) {
		ExternalReferralAuthorization authorization
			= this.externalReferralAuthorizationDao.findByRequest(request);
		this.externalReferralAuthorizationDao.makeTransient(authorization);
	}
	
	/** {@inheritDoc} */
	@Override
	public ExternalReferralAuthorization
	approveInternalReferralAlternativeTreatmentFromRequest(
			final ExternalReferralAuthorizationRequest request,
			final Date decisionDate, final Person approvedBy,
			final String notes) throws ReferralAuthorizationException {
		if (this.externalReferralAuthorizationDao
				.findByRequest(request) != null) {
			throw new ReferralAuthorizationException(
					"Referral already authorized");
		}
		ExternalReferralAuthorization authorization
			= this.externalReferralAuthorizationInstanceFactory
				.createInstance();
		authorization.setRequest(request);
		return this.approveInternalReferralAlternativeTreatment(
				authorization, decisionDate, approvedBy, notes);
	}

	/** {@inheritDoc} */
	@Override
	public ExternalReferralAuthorization
	approveInternalReferralAlternativeTreatment(
			final ExternalReferralAuthorization authorization,
			final Date decisionDate, final Person approvedBy,
			final String notes) {
		authorization.setAuthorizedBy(approvedBy);
		authorization.setDecisionDate(decisionDate);
		authorization.setNotes(notes);
		authorization.setStatus(ExternalReferralAuthorizationStatus
				.EXTERNAL_ALTERNATIVE_TREATMENT);
		return this.externalReferralAuthorizationDao
				.makePersistent(authorization);
	}

	/** {@inheritDoc} */
	@Override
	public ExternalReferralAuthorization
	approveExternalReferralAlternativeTreatmentFromRequest(
			final ExternalReferralAuthorizationRequest request,
			final Date decisionDate, final Person approvedBy,
			final String notes)
					throws ReferralAuthorizationException {
		if (this.externalReferralAuthorizationDao
				.findByRequest(request) != null) {
			throw new ReferralAuthorizationException(
					"Referral already authorized");
		}
		ExternalReferralAuthorization authorization
			= this.externalReferralAuthorizationInstanceFactory
				.createInstance();
		authorization.setRequest(request);
		return this.approveInternalReferralAlternativeTreatment(
				authorization, decisionDate, approvedBy, notes);
	}

	/** {@inheritDoc} */
	@Override
	public ExternalReferralAuthorization
	approveExternalReferralAlternativeTreatment(
			final ExternalReferralAuthorization authorization,
			final Date decisionDate, final Person approvedBy,
			final String notes) {
		authorization.setAuthorizedBy(approvedBy);
		authorization.setDecisionDate(decisionDate);
		authorization.setNotes(notes);
		authorization.setStatus(ExternalReferralAuthorizationStatus
				.INTERNAL_ALTERNATIVE_TREATMENT);
		return this.externalReferralAuthorizationDao
				.makePersistent(authorization);
	}	
	
	/** {@inheritDoc} */
	@Override
	public ExternalReferralAuthorization requireRequestReview(
			final ExternalReferralAuthorizationRequest request, 
			final Date decisionDate, final Person requiredBy,
			final String notes)
					throws ReferralAuthorizationException {
		if (this.externalReferralAuthorizationDao
				.findByRequest(request) != null) {
			throw new ReferralAuthorizationException(
					"Referral already authorized");
		}
		ExternalReferralAuthorization authorization
			= this.externalReferralAuthorizationInstanceFactory
				.createInstance();
		authorization.setRequest(request);
		return this.requireReview(authorization, decisionDate, requiredBy,
				notes);
	}
	
	/** {@inheritDoc} */
	@Override
	public ExternalReferralAuthorization requireReview(
			final ExternalReferralAuthorization authorization,
			final Date decisionDate, final Person requiredBy,
			final String notes) {
		authorization.setAuthorizedBy(requiredBy);
		authorization.setDecisionDate(decisionDate);
		authorization.setNotes(notes);
		authorization.setStatus(
				ExternalReferralAuthorizationStatus.PANEL_REVIEW_REQUIRED);
		return this.externalReferralAuthorizationDao
				.makePersistent(authorization);
	}

	/** {@inheritDoc} */
	@Override
	public void reviewAuthorization(
			final ExternalReferralAuthorization authorization,
			final Date reviewDate,
			final ExternalReferralMedicalPanelReviewDecisionStatus status)
					throws ReferralAuthorizationException {
		if (authorization.getPanelReviewDecision() == null) {
			ExternalReferralMedicalPanelReviewDecision panelReviewDecision
				= new ExternalReferralMedicalPanelReviewDecision();
			panelReviewDecision.setReviewDate(reviewDate);
			panelReviewDecision.setStatus(status);
			authorization.setPanelReviewDecision(panelReviewDecision);
		} else {
			throw new ReferralAuthorizationException("Request already reviewd");
		}
	}

	/** {@inheritDoc} */
	@Override
	public List<ProviderAssignment> findProviderAssignmentsByFacility(
			final Facility facility, final Date date) {
		return this.providerAssignmentDao.findByFacility(facility, date);
	}

	/** {@inheritDoc} */
	@Override
	public List<ExternalReferralReason> findReasons() {
		return this.externalReferralReasonDao.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public List<MedicalFacility> findProviderMedicalFacilities(
			ProviderAssignment providerAssignment)
				throws ProviderException {
		return this.medicalFacilityDao.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public List<ProviderAssignment> findProviderAssignmentsByMedicalFacility(
			final MedicalFacility medicalFacility, final Date date) {
		return this.providerAssignmentDao.findExternalByMedicalFacility(
				medicalFacility, date);
	}

	/** {@inheritDoc} */
	@Override
	public List<MedicalFacility> findMedicalFacilities() {
		return this.medicalFacilityDao.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public boolean hasAuthorization(
			final ExternalReferralAuthorizationRequest request) {
		return this.externalReferralAuthorizationDao.findByRequest(request)
				!= null;
	}

	/** {@inheritDoc} */
	@Override
	public ExternalReferralAuthorization findAuthorization(
			final ExternalReferralAuthorizationRequest request) {
		return this.externalReferralAuthorizationDao.findByRequest(request);
	}

	/** {@inheritDoc} */
	@Override
	public List<ProviderAssignment> findInternalProviderAssignmentsByFacility(
			final Facility facility, final Date date) {
		return this.providerAssignmentDao.findInternalByFacilityOnDate(
				facility, date);
	}
}