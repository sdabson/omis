package omis.placementscreening.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.offender.domain.Offender;
import omis.placementscreening.dao.PlacementReferralDao;
import omis.placementscreening.domain.PlacementReferral;
import omis.placementscreening.domain.ProgramCategory;

import org.hibernate.Query;
import org.hibernate.SessionFactory;


/** Implementation of program referral hibernate data access object.
 * @author Ryan Johns
 * @version 0.1.0 (Nov 13, 2014)
 * @since OMIS 3.0 */
public class PlacementReferralDaoHibernateImpl 
	extends GenericHibernateDaoImpl<PlacementReferral> 
	implements PlacementReferralDao {
	private static final String
		FIND_BY_OFFENDER_DATE_PROGRAM_CATEGORY_QUERY_NAME = 
			"findOpenPlacementReferralsByOffenderAndProgramCategory";
	private static final String
		PROGRAM_CATEGORY_PARAM_NAME = "programCategory";
	private static final String OFFENDER_PARAM_NAME = "offender";
	
//--------------------------------Constructors----------------------------------
	/** Constructor.
	 * @param sessionFactory session factory.
	 * @param entityName entity name. */
	public PlacementReferralDaoHibernateImpl(
			final SessionFactory sessionFactory, 
			final String entityName) {
		super(sessionFactory, entityName);
	}
//------------------------------------------------------------------------------
	/** {@inheritDoc} */
	@Override
	public List<PlacementReferral> 
		findOpenPlacementReferralsByOffenderAndProgramCategory(
			final Offender offender,  
			final ProgramCategory programCategory) {
		Query q = this.getSessionFactory()
				.getCurrentSession().getNamedQuery(
						FIND_BY_OFFENDER_DATE_PROGRAM_CATEGORY_QUERY_NAME);
		
		q.setParameter(OFFENDER_PARAM_NAME, offender);
		q.setParameter(PROGRAM_CATEGORY_PARAM_NAME, programCategory);
		
		@SuppressWarnings("unchecked")
		List<PlacementReferral> placementReferrals = 
			(List<PlacementReferral>) q.list();
		
		return placementReferrals;
	}
}
