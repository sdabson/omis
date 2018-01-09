package omis.military.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.military.dao.MilitaryServiceTermDao;
import omis.military.domain.MilitaryBranch;
import omis.military.domain.MilitaryServiceTerm;
import omis.offender.domain.Offender;

import org.hibernate.SessionFactory;

/**
 * Military service term data access object hibernate implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (May 13, 2015)
 * @since OMIS 3.0
 */
public class MilitaryServiceTermDaoHibernateImpl
extends GenericHibernateDaoImpl<MilitaryServiceTerm>
implements MilitaryServiceTermDao {

	/* Query names. */
	
	private static final String FIND_MILITARY_SERVICE_TERM_QUERY_NAME
		= "findMilitaryServiceTerm";
	
	private static final String 
	FIND_MILITARY_SERVICE_TERM_EXCLUDING_QUERY_NAME 
		= "findMilitaryServiceTermExcluding";
	
	private static final String FIND_SERVICE_TERMS_IN_DATE_RANGE_QUERY_NAME
		= "findMilitaryServiceTermsInDateRange";
	
	private static final String 
	FIND_SERVICE_TERMS_IN_DATE_RANGE_EXCLUDING_QUERY_NAME
		= "findMilitaryServiceTermsInDateRangeExcluding";
	
	private static final String FIND_SERVICE_TERMS_BY_OFFENDER_QUERY_NAME
		= "findMilitaryServiceTermsByOffender";
	
	/* Parameter names. */
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private static final String START_DATE_PARAM_NAME = "startDate";
	
	private static final String END_DATE_PARAM_NAME = "endDate";
	
	private static final String MILITARY_BRANCH_PARAM_NAME = "branch";
	
	private static final String MILITARY_SERVICE_TERM_PARAM_NAME 
		= "serviceTerm";
	
	/* Constructors. */
	
	/**
	 * Instantiates a military service term data access object with the
	 * specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public MilitaryServiceTermDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method implementations. */

	/** {@inheritDoc} */
	@Override
	public MilitaryServiceTerm find(final Offender offender, 
			final Date startDate, final MilitaryBranch branch) {
		MilitaryServiceTerm serviceTerm = (MilitaryServiceTerm) 
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_MILITARY_SERVICE_TERM_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setDate(START_DATE_PARAM_NAME, startDate)
				.setParameter(MILITARY_BRANCH_PARAM_NAME, branch)
				.uniqueResult();
		return serviceTerm;
	}

	/** {@inheritDoc} */
	@Override
	public MilitaryServiceTerm findExcluding(
			final MilitaryServiceTerm serviceTerm, final Offender offender, 
			final Date startDate, final MilitaryBranch branch) {
		MilitaryServiceTerm militaryServiceTerm = (MilitaryServiceTerm) 
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(
						FIND_MILITARY_SERVICE_TERM_EXCLUDING_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setDate(START_DATE_PARAM_NAME, startDate)
				.setParameter(MILITARY_BRANCH_PARAM_NAME, branch)
				.setParameter(MILITARY_SERVICE_TERM_PARAM_NAME, serviceTerm)
				.uniqueResult();
		return militaryServiceTerm;
	}

	/** {@inheritDoc} */
	@Override
	public List<MilitaryServiceTerm> findWithinDateRange(
			final Offender offender, final Date startDate, final Date endDate) {
		@SuppressWarnings("unchecked")
		List<MilitaryServiceTerm> serviceTerms = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_SERVICE_TERMS_IN_DATE_RANGE_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setDate(START_DATE_PARAM_NAME, startDate)
				.setDate(END_DATE_PARAM_NAME, endDate)
				.list();
		return serviceTerms;
	}

	/** {@inheritDoc} */
	@Override
	public List<MilitaryServiceTerm> findWithinDateRangeExcluding(
			final Offender offender, final Date startDate, final Date endDate, 
			final MilitaryServiceTerm serviceTerm) {
		@SuppressWarnings("unchecked")
		List<MilitaryServiceTerm> serviceTerms = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
						FIND_SERVICE_TERMS_IN_DATE_RANGE_EXCLUDING_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setDate(START_DATE_PARAM_NAME, startDate)
				.setDate(END_DATE_PARAM_NAME, endDate)
				.setParameter(MILITARY_SERVICE_TERM_PARAM_NAME, serviceTerm)
				.list();
		return serviceTerms;
	}

	/** {@inheritDoc} */
	@Override
	public List<MilitaryServiceTerm> findByOffender(Offender offender) {
		@SuppressWarnings("unchecked")
		List<MilitaryServiceTerm> serviceTerms = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_SERVICE_TERMS_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.list();
		return serviceTerms;
	}
}