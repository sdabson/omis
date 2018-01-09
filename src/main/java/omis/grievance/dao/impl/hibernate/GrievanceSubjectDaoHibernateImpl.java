package omis.grievance.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.grievance.dao.GrievanceSubjectDao;
import omis.grievance.domain.GrievanceSubject;

import org.hibernate.SessionFactory;

/**
 * Grievance subject data access object hibernate implementation.
 * 
 * @author Yidong Li
 * @author Stephen Abson
 * @version 0.1.0 (May 19, 2015)
 * @since OMIS 3.0
 */
public class GrievanceSubjectDaoHibernateImpl
		extends GenericHibernateDaoImpl<GrievanceSubject>
		implements GrievanceSubjectDao {

	/* Query names. */
	
	private static final String FIND_SUBJECTS_QUERY_NAME 
		= "findGrievanceSubjects";
	
	private static final String FIND_SUBJECT_QUERY_NAME
		= "findGrievanceSubject";
	
	/* Parameter names. */
	
	private static final String NAME_PARAM_NAME = "name";
	
	/* Constructors. */
	
	/**
	 * Instantiates a grievance subject data access object
	 * with the specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public GrievanceSubjectDaoHibernateImpl(
			SessionFactory sessionFactory, String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<GrievanceSubject> findAll() {
		@SuppressWarnings("unchecked")
		List<GrievanceSubject> subjects = 
			this.getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_SUBJECTS_QUERY_NAME)
			.list();
		return subjects;
	}

	/** {@inheritDoc} */
	@Override
	public GrievanceSubject find(final String name) {
		GrievanceSubject subject = (GrievanceSubject) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_SUBJECT_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.uniqueResult();
		return subject;
	}
}