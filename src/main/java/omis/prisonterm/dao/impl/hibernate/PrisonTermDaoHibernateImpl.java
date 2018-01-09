package omis.prisonterm.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.offender.domain.Offender;
import omis.prisonterm.dao.PrisonTermDao;
import omis.prisonterm.domain.PrisonTerm;
import omis.prisonterm.domain.PrisonTermStatus;

/**
 * Hibernate implementation of data access object for prison terms.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (March 21, 2017)
 * @since OMIS 3.0
 */

public class PrisonTermDaoHibernateImpl 
		extends GenericHibernateDaoImpl<PrisonTerm> 
		implements PrisonTermDao {

	/* Query names. */
	
	private static final String FIND_EXLUDING_QUERY_NAME 
		= "findExcludingPrisonTerm";
	
	private static final String FIND_PRISON_TERM_QUERY_NAME 
		= "findPrisonTerm";
	
	private static final String FIND_BY_OFFENDER_QUERY_NAME = "findByOffender";
	
	private static final String FIND_ACTIVE_PRISON_TERM_BY_OFFENDER 
		= "findExcludingActiveByOffender";
	
	/* Parameter names. */
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private static final String ACTION_DATE_PARAM_NAME = "actionDate";
	
	private static final String STATUS_PARAM_NAME = "status";
	
	private static final String EXCLUDED_PRISON_TERM_PARAM_NAME 
		= "excludedPrisonTerm";
	
	/* Constructor.*/
	
	/**
	 * Instantiates a data access object for prison terms with the
	 * specified session factory and entity name.
	 *
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public PrisonTermDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public PrisonTerm findExcluding(
			final Offender offender, final Date actionDate, 
			final PrisonTermStatus status, 
			final PrisonTerm excludedPrisonTerm) {
		PrisonTerm prisonTerm = (PrisonTerm)getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_EXLUDING_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(ACTION_DATE_PARAM_NAME, actionDate)
				.setParameter(STATUS_PARAM_NAME, status)
				.setParameter(EXCLUDED_PRISON_TERM_PARAM_NAME, excludedPrisonTerm)
				.uniqueResult();
		return prisonTerm;		
	}
	
	/** {@inheritDoc} */
	@Override
	public PrisonTerm find(
			final Offender offender, 
			final Date actionDate, final PrisonTermStatus status) {
		PrisonTerm prisonTerm = (PrisonTerm)getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_PRISON_TERM_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(ACTION_DATE_PARAM_NAME, actionDate)
				.setParameter(STATUS_PARAM_NAME, status)
				.uniqueResult();
		return prisonTerm;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<PrisonTerm> findByOffender(final Offender offender) {
		@SuppressWarnings("unchecked")
		List<PrisonTerm> prisonTerms = getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.list();
		return prisonTerms;
	}

	/** {@inheritDoc} */
	@Override
	public PrisonTerm findExcludingActiveByOffender(final Offender offender, 
			final PrisonTerm excludedPrisonTerm) {
		PrisonTerm prisonTerm = (PrisonTerm)getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_ACTIVE_PRISON_TERM_BY_OFFENDER)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(EXCLUDED_PRISON_TERM_PARAM_NAME, 
						excludedPrisonTerm)
				.uniqueResult();
		return prisonTerm;
	}
	
}
