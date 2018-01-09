package omis.placementscreening.service.impl;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.demographics.domain.Sex;
import omis.exception.DuplicateEntityFoundException;
import omis.facility.domain.Facility;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;
import omis.person.domain.Person;
import omis.placementscreening.dao.PlacementReferralDao;
import omis.placementscreening.dao.ReferralScreeningCenterDao;
import omis.placementscreening.dao.ReferralScreeningDao;
import omis.placementscreening.domain.PlacementReferral;
import omis.placementscreening.domain.ProgramCategory;
import omis.placementscreening.domain.ReferralScreening;
import omis.placementscreening.domain.ReferralScreeningCenter;
import omis.placementscreening.domain.ScreeningLevelCategory;
import omis.placementscreening.domain.component.ReferralSource;
import omis.placementscreening.exception.ReferralDateCategoryConflictException;
import omis.placementscreening.service.PlacementReferralService;
import omis.user.dao.UserAccountDao;
import omis.user.domain.UserAccount;

/** Implementation of program referral service.
 * @author Ryan Johns
 * @version 0.1.0 (Nov 13, 2014)
 * @since OMIS 3.0 */
public class PlacementReferralServiceImpl implements PlacementReferralService {
	private static final String DUPLICATE_PROGRAM_REFERRAL_FOUND = 
			"Duplicate program referral entity found.";
	private static final String DUPLICATE_REFERRAL_SCREENING_FOUND =
			"Duplicate referral screening entity found.";
	private static final String 
		PLACEMENT_REFERRAL_CATEGORY_INSIDE_MINIMUM_TIME_EXISTS = 
			"Existing referral for placement screening exists within the "
			+ "%1d %2s period";
	private static final Integer PLACEMENT_CATEGORY_RE_REFER_WAIT_TIME = 40;
	private static final String RE_REFER_WAIT_UNIT = "days";
	private final PlacementReferralDao placementReferralDao;
	private final ReferralScreeningDao referralScreeningDao;
	private final ReferralScreeningCenterDao referralScreeningCenterDao;
	private final UserAccountDao userAccountDao;
	private final InstanceFactory<PlacementReferral> 
		placementReferralInstanceFactory;
	private final InstanceFactory<ReferralScreening>
		referralScreeningInstanceFactory;
	private final AuditComponentRetriever auditComponentRetriever;
//--------------------------------Constructors----------------------------------
	/** Default constructor. 
	 * @param placementReferralDao placement referral dao.
	 * @param referralScreeningDao referral screening dao.
	 * @param referralScreeningCenterDao referral screening facility dao.
	 * @param placementReferralInstanceFactory placement referral instance 
	 * factory.
	 * @param referralScreeningInstanceFactory referral screening instance 
	 * factory.
	 * @param userAccountDao user account dao.
	 * @param auditComponentRetriever audit component retriever. */
	public PlacementReferralServiceImpl(
			final PlacementReferralDao placementReferralDao,
			final ReferralScreeningDao referralScreeningDao,
			final ReferralScreeningCenterDao referralScreeningCenterDao,
			final InstanceFactory<PlacementReferral> 
				placementReferralInstanceFactory,
			final InstanceFactory<ReferralScreening> 
				referralScreeningInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever,
			final UserAccountDao userAccountDao) {
		this.placementReferralDao = placementReferralDao;
		this.referralScreeningDao = referralScreeningDao;
		this.referralScreeningCenterDao = referralScreeningCenterDao;
		this.placementReferralInstanceFactory = 
				placementReferralInstanceFactory;
		this.referralScreeningInstanceFactory = 
				referralScreeningInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
		this.userAccountDao = userAccountDao;
	}
//------------------------------------------------------------------------------
	/** {@inheritDoc} */
	@Override
	public List<ReferralScreeningCenter> 
		findAllReferralScreeningCentersByProgram(
				final ProgramCategory programCategory,
				final Sex sex) {
		return this.referralScreeningCenterDao
				.findByProgramCategory(programCategory, sex);
	}

	/** {@inheritDoc} */
	@Override
	public PlacementReferral referToPlacement(final Offender offender,
			final Date date, final Facility facility, 
			final Facility referringFacility, final Person referringPerson, 
			final ProgramCategory programCategory) 
		throws DuplicateEntityFoundException,
		ReferralDateCategoryConflictException {
		if (this.placementReferralDao
				.findOpenPlacementReferralsByOffenderAndProgramCategory(
				offender, programCategory).size() > 0) {
			throw new DuplicateEntityFoundException(
					DUPLICATE_PROGRAM_REFERRAL_FOUND);
		}
		
		if (this.referralScreeningDao
				.findByOffenderScreeningProgramCategoryAndDate(
						offender, programCategory, date).size() > 0) {
			throw new ReferralDateCategoryConflictException(
					String.format(
						PLACEMENT_REFERRAL_CATEGORY_INSIDE_MINIMUM_TIME_EXISTS,
						PLACEMENT_CATEGORY_RE_REFER_WAIT_TIME,
						RE_REFER_WAIT_UNIT));
		}
		return this.placementReferralDao.makePersistent(
				this.instantiatePlacementReferral(
				offender, date, facility, referringFacility, referringPerson, 
				programCategory));
	}

	/** {@inheritDoc} */
	@Override
	public ReferralScreening updateOrderOfScreening(
			final ReferralScreening referralScreening,
			final Integer order) throws DuplicateEntityFoundException {
		if (this.referralScreeningDao
				.findByPlacementAndScreeningCenterExcludingReferralScreening(
			referralScreening.getPlacementReferral(),
			referralScreening.getReferralScreeningCenter(),
			referralScreening).size() > 0) {
			throw new DuplicateEntityFoundException(
					DUPLICATE_REFERRAL_SCREENING_FOUND);
		}
		
		referralScreening.setScreeningOrder(order);
		return referralScreening;
	}
	
	/** {@inheritDoc} */
	@Override
	public ReferralScreening queueLocalScreening(final Integer screeningIndex, 
			final PlacementReferral placementReferral, 
			final ReferralScreeningCenter referralScreeningCenter) 
		throws DuplicateEntityFoundException {
		if (this.referralScreeningDao
				.findByPlacementReferralAndReferralScreeningCenter(
						placementReferral, referralScreeningCenter)
						.size() > 0) {
			throw new DuplicateEntityFoundException(
					DUPLICATE_REFERRAL_SCREENING_FOUND);
		}
		return this.referralScreeningDao.makePersistent(
				this.instatiateReferralScreening(screeningIndex, 
						placementReferral, referralScreeningCenter, 
						ScreeningLevelCategory.LOCAL));
	}
	
	/** {@inheritDoc} */
	@Override
	public void dequeueLocalScreening(
			final ReferralScreening referralScreening) {
		this.referralScreeningDao.makeTransient(referralScreening);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<ReferralScreeningCenter> queueScreeningCenters(
			final ReferralScreeningCenter referralScreeningCenter, 
			final Sex sex) {
		return this.referralScreeningCenterDao
				.orderReferralScreeningCenters(referralScreeningCenter, 
						sex);
	}

	/** {@inheritDoc} */
	@Override
	public UserAccount findUserByUsername(final String username) {
		return this.userAccountDao.findByUsername(username);
	}
	
	/* Creates instance of program referral. */
	private PlacementReferral instantiatePlacementReferral(
			final Offender offender, 
			final Date date, final Facility facility, 
			final Facility referringFacility, final Person referringPerson, 
			final ProgramCategory programCategory) {
		PlacementReferral placementReferral = 
				this.placementReferralInstanceFactory
				.createInstance();
		placementReferral.setDate(date);
		placementReferral.setProgramCategory(programCategory);
		placementReferral.setOffender(offender);
		placementReferral.setFacility(facility);
		placementReferral.setReferralSource(new ReferralSource(
				referringFacility, referringPerson));
		placementReferral.setProgramCategory(programCategory);
		placementReferral.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
				
		return placementReferral;
	}
	
	/* Creates instance of referral screening. */
	private ReferralScreening instatiateReferralScreening(
			final Integer screeningOrder, 
			final PlacementReferral placementReferral, 
			final ReferralScreeningCenter referralScreeningCenter, 
			final ScreeningLevelCategory screeningLevelCategory) {
		ReferralScreening referralScreening = 
				this.referralScreeningInstanceFactory.createInstance();
		referralScreening.setScreeningOrder(screeningOrder);
		referralScreening.setPlacementReferral(placementReferral);
		referralScreening.setReferralScreeningCenter(
				referralScreeningCenter);
		referralScreening.setScreeningLevelCategory(screeningLevelCategory);
		referralScreening.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		referralScreening.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return referralScreening;
	}
	

}
