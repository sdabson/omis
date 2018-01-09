package omis.placementscreening.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.demographics.domain.Sex;
import omis.placementscreening.dao.ReferralScreeningCenterDao;
import omis.placementscreening.domain.ProgramCategory;
import omis.placementscreening.domain.ReferralScreeningCenter;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

/** Implementation of referral screening data access object.
 * @author Ryan Johns
 * @version 0.1.0 (Nov 17, 2014)
 * @since OMIS 3.0 */
public class ReferralScreeningCenterDaoHibernateImpl extends
		GenericHibernateDaoImpl<ReferralScreeningCenter> implements
		ReferralScreeningCenterDao {
	private static final String FIND_BY_PROGRAM_CATEGORY =
			"findReferralScreeningCentersByProgramCategory";
	private static final String 
		ORDER_BY_REFERRAL_SCREENING_FACILITY_SEX_QUERY_NAME =
		 "findReferralScreeningCentersBySexOrderByReferralScreeningCenter";
	private static final String PROGRAM_CATEGORY_PARAM_NAME = "programCategory";
	private static final String SEX_PARAM_NAME = "sex";
	private static final String ORDER_PARAM_NAME = "order";
//--------------------------------Constructors----------------------------------
	/** Constructor.
	 * @param sessionFactory session factory.
	 * @param entityName entity name. */
	public ReferralScreeningCenterDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
//------------------------------------------------------------------------------
	/** {@inheritDoc} */
	@Override
	public List<ReferralScreeningCenter> findByProgramCategory(
			final ProgramCategory programCategory, final Sex sex) {
		Query q = this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_PROGRAM_CATEGORY);
		q.setParameter(PROGRAM_CATEGORY_PARAM_NAME, programCategory);
		q.setParameter(SEX_PARAM_NAME, sex);
		return this.cast(q.list());
		
	}
	
	/** {@inheritDoc} */
	@Override
	public List<ReferralScreeningCenter> orderReferralScreeningCenters(
			final ReferralScreeningCenter referralScreeningCenter,
			final Sex sex) {
		Query q = this.getSessionFactory().getCurrentSession()
				.getNamedQuery(
						ORDER_BY_REFERRAL_SCREENING_FACILITY_SEX_QUERY_NAME);
		q.setParameter(PROGRAM_CATEGORY_PARAM_NAME,
				referralScreeningCenter.getProgramCategory());
		q.setParameter(SEX_PARAM_NAME, sex);
		q.setParameter(ORDER_PARAM_NAME, referralScreeningCenter.getOrder());
		return this.cast(q.list());
	}
	
	/* cast to list of referral screening centers. */
	private List<ReferralScreeningCenter> cast(final List<?> objs) {
		@SuppressWarnings("unchecked")
		List<ReferralScreeningCenter> screeningFacilities = 
			(List<ReferralScreeningCenter>) objs;
		
		return screeningFacilities;
	}
}
