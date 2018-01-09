package omis.grievance.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.grievance.dao.GrievanceComplaintCategoryDao;
import omis.grievance.domain.GrievanceComplaintCategory;
import omis.grievance.domain.GrievanceSubject;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

/**
 * Grievance complaint category data access object hibernate implementation.
 * 
 * @author Yidong Li
 * @author Stephen Abson
 * @version 0.1.0 (May 19, 2015)
 * @since OMIS 3.0
 */
public class GrievanceComplaintCategoryDaoHibernateImpl 
	extends GenericHibernateDaoImpl<GrievanceComplaintCategory>	
	implements GrievanceComplaintCategoryDao {

	/* Query names. */
	
	private static final String FIND_BY_SUBJECT_QUERY_NAME
 		= "findGrievanceComplaintCategoriesBySubject";

	private static final String FIND_QUERY_NAME
		= "findGrievanceComplaintCategory";
	
	/* Parameter names. */
	
	private static final String SUBJECT_PARAMETER_NAME = "subject";

	private static final String NAME_PARAM_NAME = "name";

	/**
	 * Instantiates an instance of grievance complaint category data access 
	 * object with the specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public GrievanceComplaintCategoryDaoHibernateImpl(
		final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public List<GrievanceComplaintCategory> findBySubject(final GrievanceSubject 
		subject){
		Query q = getSessionFactory()
			.getCurrentSession() 
			.getNamedQuery(FIND_BY_SUBJECT_QUERY_NAME)
			.setParameter(SUBJECT_PARAMETER_NAME, subject);
		@SuppressWarnings("unchecked")
		List<GrievanceComplaintCategory> result 
			= (List<GrievanceComplaintCategory>)q.list();
		return result; 
	}

	/** {@inheritDoc} */
	@Override
	public GrievanceComplaintCategory find(final String name) {
		GrievanceComplaintCategory complaintCategory
				= (GrievanceComplaintCategory) this.getSessionFactory()
					.getCurrentSession().getNamedQuery(FIND_QUERY_NAME)
					.setParameter(NAME_PARAM_NAME, name)
					.uniqueResult();
		return complaintCategory;
	}
}