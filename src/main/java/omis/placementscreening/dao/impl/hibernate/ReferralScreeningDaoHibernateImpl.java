package omis.placementscreening.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.offender.domain.Offender;
import omis.placementscreening.dao.ReferralScreeningDao;
import omis.placementscreening.domain.PlacementReferral;
import omis.placementscreening.domain.ProgramCategory;
import omis.placementscreening.domain.ReferralScreening;
import omis.placementscreening.domain.ReferralScreeningCenter;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

/** Implementation of Referral screening data access object.
 * @author Ryan Johns
 * @version 0.1.0 (Nov 14, 2014)
 * @since OMIS 3.0 */
public class ReferralScreeningDaoHibernateImpl extends
		GenericHibernateDaoImpl<ReferralScreening> implements
		ReferralScreeningDao {
	private static final String 
		FIND_BY_PLACEMENT_REFERRAL_AND_SCREENING_CENTER_QUERY_NAME = 
		"findReferralScreeningsByPlacementReferralAndReferralScreeningCenter";
	private static final String
		FIND_BY_PLCMNT_RFRL_SCRNG_CNTR_EXCLDNG_PLCMNT_SCRNG_QUERY_NAME =
		"findReferralScreeningsByPlcmntRfrlScrngCntrExcldngPlcmntScrng";
	private static final String
		FIND_BY_OFFENDER_OFFENDER_SCREENING_PROGRAM_REFERRAL_AND_DATE =
			"findReferralScreeningssByOffenderScreeningProgramCategoryAndDate";
	private static final String EXCLUDING_PARAM_NAME = "excluding";
	private static final String PLACEMENT_REFERRAL_PARAM_NAME = 
			"placementReferral";
	private static final String REFERRAL_SCREENING_CENTER_PARAM_NAME = 
			"referralScreeningCenter";
	private static final String DATE_PARAM_NAME = "date";
	private static final String
		PROGRAM_CATEGORY_PARAM_NAME = "programCategory";
	private static final String OFFENDER_PARAM_NAME = "offender";
//--------------------------------Constructors----------------------------------
	/** Constructor.
	 * @param sessionFactory session factory.
	 * @param entityName entity name. */
	public ReferralScreeningDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
//------------------------------------------------------------------------------
	/** {@inheritDoc} */
	@Override
	public List<ReferralScreening> 
		findByPlacementReferralAndReferralScreeningCenter(
				final PlacementReferral placementReferral,
				final ReferralScreeningCenter referralScreeningCenter) {
		Query q = this.getSessionFactory().getCurrentSession().getNamedQuery(
				FIND_BY_PLACEMENT_REFERRAL_AND_SCREENING_CENTER_QUERY_NAME);
		
		q.setParameter(PLACEMENT_REFERRAL_PARAM_NAME, placementReferral);
		q.setParameter(REFERRAL_SCREENING_CENTER_PARAM_NAME, 
				referralScreeningCenter);
		
		return this.cast(q.list());
	}
	
	/** {@inheritDoc} */
	@Override
	public List<ReferralScreening>
		findByPlacementAndScreeningCenterExcludingReferralScreening(
			final PlacementReferral placementReferral,
			final ReferralScreeningCenter referralScreeningCenter,
			final ReferralScreening... referralScreenings) {
		Query q = this.getSessionFactory().getCurrentSession().getNamedQuery(
				FIND_BY_PLCMNT_RFRL_SCRNG_CNTR_EXCLDNG_PLCMNT_SCRNG_QUERY_NAME);
		
		q.setEntity(PLACEMENT_REFERRAL_PARAM_NAME, placementReferral);
		q.setEntity(REFERRAL_SCREENING_CENTER_PARAM_NAME, 
				referralScreeningCenter);
		q.setParameterList(EXCLUDING_PARAM_NAME, referralScreenings);
		
		return this.cast(q.list());
	}
	
	/** {@inheritDoc} */
	@Override
	public List<ReferralScreening>
		findByOffenderScreeningProgramCategoryAndDate(
			final Offender offender,
			final ProgramCategory programCategory,
			final Date date) {
		Query q = this.getSessionFactory().getCurrentSession().getNamedQuery(
				FIND_BY_OFFENDER_OFFENDER_SCREENING_PROGRAM_REFERRAL_AND_DATE);
		
		q.setParameter(OFFENDER_PARAM_NAME, offender);
		q.setParameter(PROGRAM_CATEGORY_PARAM_NAME, programCategory);
		q.setParameter(DATE_PARAM_NAME, date);
		
		@SuppressWarnings("unchecked")
		List<ReferralScreening> referralScreenings = 
			(List<ReferralScreening>) q.list();
		
		return referralScreenings;
	}
	
	/* Cast to List<ReferralScreenings> */	
	private List<ReferralScreening> cast(final List<?> objs) {
		@SuppressWarnings("unchecked")
		List<ReferralScreening> referralScreenings = 
				(List<ReferralScreening>) objs;
		
		return referralScreenings;
	}
}
