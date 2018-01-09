/**
 * 
 */
package omis.separationneed.dao.impl.hibernate;


import java.util.Date;
import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.offender.domain.Offender;
import omis.person.domain.Person;
import omis.relationship.domain.Relationship;
import omis.separationneed.dao.SeparationNeedDao;
import omis.separationneed.domain.SeparationNeed;

import org.hibernate.SessionFactory;

/**
 * Hibernate entity configurable implementation of data access object for
 * separation need.
 * 
 * @author Joel Norris 
 * @version 0.1.0 (Feb, 21 2013)
 * @since OMIS 3.0
 */
public class SeparationNeedDaoHibernateImpl 
	extends GenericHibernateDaoImpl<SeparationNeed> 
	implements SeparationNeedDao {

	/* Query names. */
	
	private static final String FIND_SEPARATION_NEED_QUERY_NAME
		= "findSeparationNeed";
	
	private static final String FIND_SEPARATION_NEED_EXCLUDING_QUERY_NAME
		= "findSeparationNeedExcluding";
	
	private static final String FIND_SEPARATION_NEEDS_BY_OFFENDER_QUERY_NAME
		= "findSeparationNeedsByOffender";
	
	private static final String
	FIND_SEPARATION_NEEDS_BY_TARGET_OFFENDER_QUERY_NAME
		= "findSeparationNeedsByTargetOffender";
	
	private static final String FIND_IN_DATE_RANGE_QUERY_NAME
		= "findSeparationNeedsInDateRange";
	
	private static final String FIND_IN_DATE_RANGE_EXCLUDING_QUERY_NAME
		= "findSeparationNeedsInDateRangeExcluding";
	
	/* Parameter names. */
	
	private static final String RELATIONSHIP_PARAM_NAME = "relationship";
	
	private static final String START_DATE_PARAM_NAME = "startDate";
	
	private static final String END_DATE_PARAM_NAME = "endDate";
	
	private static final String DATE_PARAM_NAME = "date";
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private static final String TARGET_OFFENDER_PARAM_NAME = "targetOffender";
	
	private static final String SEPARATION_NEED_PARAM_NAME = "separationNeed";
	 
	/* Constructor. */
	
	/**
	 * Instantiates a data access object for separation need with the specified
	 * session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public SeparationNeedDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<SeparationNeed> findByOffender(
			final Offender offender) {
		@SuppressWarnings("unchecked")
		List<SeparationNeed> separationNeeds = getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_SEPARATION_NEEDS_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.list();		
		return separationNeeds;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<SeparationNeed> findByTargetOffender(
			final Offender offender) {
		@SuppressWarnings("unchecked")
		List<SeparationNeed> separationNeeds = getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
						FIND_SEPARATION_NEEDS_BY_TARGET_OFFENDER_QUERY_NAME)
				.setParameter(TARGET_OFFENDER_PARAM_NAME, offender)
				.list();
		return separationNeeds;
	}

	/** {@inheritDoc} */
	@Override
	public Offender retreiveOffenderFromRelationship(
			final Person person) {
		Offender offender = (Offender) getSessionFactory()
				.getCurrentSession()
				.getNamedQuery("findOffenderByRelationship")
				.setParameter("person", person).uniqueResult();
		return offender;
	}

	/** {@inheritDoc} */
	@Override
	public SeparationNeed find(final Relationship relationship, 
			final Date date) {
		SeparationNeed separationNeed = (SeparationNeed)
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_SEPARATION_NEED_QUERY_NAME)
				.setParameter(RELATIONSHIP_PARAM_NAME, relationship)
				.setDate(DATE_PARAM_NAME, date)
				.uniqueResult();
		return separationNeed;
	}
	
	/** {@inheritDoc} */
	@Override
	public SeparationNeed findExcluding(final Relationship relationship, 
			final Date date,
			final SeparationNeed separationNeed){
		SeparationNeed sepNeed = (SeparationNeed)
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_SEPARATION_NEED_EXCLUDING_QUERY_NAME)
				.setParameter(RELATIONSHIP_PARAM_NAME, relationship)
				.setDate(DATE_PARAM_NAME, date)
				.setParameter(SEPARATION_NEED_PARAM_NAME, separationNeed)
				.uniqueResult();
		return sepNeed;
	}

	/** {@inheritDoc} */
	@Override
	public List<SeparationNeed> findInDateRange(final Relationship relationship,
			final Date startDate, final Date endDate) {
		@SuppressWarnings("unchecked")
		List<SeparationNeed> separationNeeds = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_IN_DATE_RANGE_QUERY_NAME)
				.setParameter(RELATIONSHIP_PARAM_NAME, relationship)
				.setDate(START_DATE_PARAM_NAME, startDate)
				.setDate(END_DATE_PARAM_NAME, endDate)
				.list();
		return separationNeeds;
	}

	/** {@inheritDoc} */
	@Override
	public List<SeparationNeed> findInDateRangeExcluding(
			final Relationship relationship, final Date startDate,
			final Date endDate, final SeparationNeed separationNeed) {
		@SuppressWarnings("unchecked")
		List<SeparationNeed> separationNeeds = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_IN_DATE_RANGE_EXCLUDING_QUERY_NAME)
				.setParameter(RELATIONSHIP_PARAM_NAME, relationship)
				.setDate(START_DATE_PARAM_NAME, startDate)
				.setDate(END_DATE_PARAM_NAME, endDate)
				.setParameter(SEPARATION_NEED_PARAM_NAME, separationNeed)
				.list();
		return separationNeeds;
	}
}