package omis.stg.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.offender.domain.Offender;
import omis.stg.dao.SecurityThreatGroupActivityInvolvementDao;
import omis.stg.domain.SecurityThreatGroupActivity;
import omis.stg.domain.SecurityThreatGroupActivityInvolvement;

/**
 * Hibernate implementation of data access object for security threat group
 * activity note.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (Dec 1, 2016)
 * @since OMIS 3.0
 */

public class SecurityThreatGroupActivityInvolvementDaoHibernateImpl 
		extends GenericHibernateDaoImpl<SecurityThreatGroupActivityInvolvement>
		implements SecurityThreatGroupActivityInvolvementDao {

	/* Query names. */

	private static final String FIND_ACTIVITY_INVOLVEMENT_QUERY_NAME 
		= "findActivityInvolvement";
	
	private static final String FIND_ACTIVITY_INVOLVEMENTS_QUERY_NAME 
		= "findActivityInvolvements";
	
	private static final String FIND_ACTIVITY_INVOLVEMENT_EXCLUDING_QUERY_NAME
		= "findActivityInvolvementExcluding";
	
	private static final String FIND_OFFENDERS_QUERY_NAME = "findOffender";
	
	/* Parameter names. */
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private static final String ACTIVITY_PARAM_NAME = "activity";
	
	private static final String NARRATIVE_PARAM_NAME = "narrative";
	
	private static final String EXCLUDED_INVOLVEMENT_PARAM_NAME 
		= "excludedInvolvement";
	
	/* Constructor. */
	
	/**
	 * Instantiates a hibernate implementation of data access object for
	 * security threat group activity involvement.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public SecurityThreatGroupActivityInvolvementDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<SecurityThreatGroupActivityInvolvement> findInvolvements(
			final SecurityThreatGroupActivity activity) {
		@SuppressWarnings("unchecked")
		List<SecurityThreatGroupActivityInvolvement> involvements 
		= this.getSessionFactory().getCurrentSession().getNamedQuery(
				FIND_ACTIVITY_INVOLVEMENTS_QUERY_NAME)
				.setParameter(ACTIVITY_PARAM_NAME, activity)
				.list();
		return involvements;
	}

	@Override
	public List<Offender> findOffender(final String queryString) {
		@SuppressWarnings("unchecked")
		List<Offender> offenders = this.getSessionFactory().getCurrentSession()
			.getNamedQuery(FIND_OFFENDERS_QUERY_NAME).list();
		return offenders;
	}

	/** {@inheritDoc} */
	@Override
	public SecurityThreatGroupActivityInvolvement find(Offender offender, 
			SecurityThreatGroupActivity activity,
			String narrative) {
		SecurityThreatGroupActivityInvolvement involvement 
		= (SecurityThreatGroupActivityInvolvement) 
		this.getSessionFactory().getCurrentSession()
			.getNamedQuery(FIND_ACTIVITY_INVOLVEMENT_QUERY_NAME)
			.setParameter(OFFENDER_PARAM_NAME, offender)
			.setParameter(ACTIVITY_PARAM_NAME, activity)
			.setParameter(NARRATIVE_PARAM_NAME, narrative)
			.uniqueResult();			
		return involvement;
	}
	
	/** {@inheritDoc} */
	@Override
	public SecurityThreatGroupActivityInvolvement findExcluding(Offender offender, 
			SecurityThreatGroupActivity activity,
			String narrative, 
			SecurityThreatGroupActivityInvolvement excludedInvolvement) {
		SecurityThreatGroupActivityInvolvement involvement 
		= (SecurityThreatGroupActivityInvolvement) 
		this.getSessionFactory().getCurrentSession()
			.getNamedQuery(FIND_ACTIVITY_INVOLVEMENT_EXCLUDING_QUERY_NAME)
			.setParameter(OFFENDER_PARAM_NAME, offender)
			.setParameter(ACTIVITY_PARAM_NAME, activity)
			.setParameter(NARRATIVE_PARAM_NAME, narrative)
			.setParameter(EXCLUDED_INVOLVEMENT_PARAM_NAME, excludedInvolvement)
			.uniqueResult();			
		return involvement;
	
	}
}
