package omis.court.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import omis.court.dao.CourtJudgeAssignmentDao;
import omis.court.domain.Court;
import omis.court.domain.CourtJudgeAssignment;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.person.domain.Person;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of data access object for assignment of judge to
 * court.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jul 3, 2013)
 * @since OMIS 3.0
 */
public class CourtJudgeAssignmentDaoHibernateImpl
		extends GenericHibernateDaoImpl<CourtJudgeAssignment>
		implements CourtJudgeAssignmentDao {

	/* Query names. */
	
	private static final String FIND_BY_COURT_ON_DATE_QUERY_NAME
		= "findJudgesByCourtOnDate";

	/* Parameter names. */
	
	private static final String COURT_PARAM_NAME = "court";

	private static final String DATE_PARAM_NAME = "date";
	
	/* Constructor. */
	
	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * assignment of judge to court.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public CourtJudgeAssignmentDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<Person> findJudgesByCourtOnDate(final Court court,
			final Date date) {
		@SuppressWarnings("unchecked")
		List<Person> judges = this.getSessionFactory().getCurrentSession()
			.getNamedQuery(FIND_BY_COURT_ON_DATE_QUERY_NAME)
			.setParameter(COURT_PARAM_NAME, court)
			.setDate(DATE_PARAM_NAME, date).list();
		return judges;
	}
}