package omis.commitstatus.dao.impl.hibernate;

import java.util.List;

import omis.commitstatus.dao.CommitStatusDao;
import omis.commitstatus.domain.CommitStatus;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;

import org.hibernate.SessionFactory;

/**
 * Commit status data access object hibernate implementation.
 * 
 * @author Yidong Li
 * @version 0.1.0 (May 31, 2017)
 * @since OMIS 3.0
 */
public class CommitStatusDaoHibernateImpl 
	extends GenericHibernateDaoImpl<CommitStatus>	
	implements CommitStatusDao {

	/* Query names. */
	private static final String FIND_ALL_COMMIT_STATUSES_QUERY_NAME
 		= "findAllCommitStatuses";
	private static final String FIND_COMMIT_STATUS_QUERY_NAME
		= "findCommitStatus";

	/* Parameter names. */
	private static final String NAME_PARAM_NAME = "name";
		
	/**
	 * Instantiates an instance of commit status data access object with
	 * the specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public CommitStatusDaoHibernateImpl(
		final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	public List<CommitStatus> findStatuses() {
		List<CommitStatus> commitStatuses;
		commitStatuses = (List<CommitStatus>) getSessionFactory()
			.getCurrentSession() 
			.getNamedQuery(FIND_ALL_COMMIT_STATUSES_QUERY_NAME);
		return commitStatuses;
	}
	
	/** {@inheritDoc} */
	@Override
	public CommitStatus findStatus(final String name){
		CommitStatus commitStatus = (CommitStatus) getSessionFactory()
			.getCurrentSession() 
			.getNamedQuery(FIND_COMMIT_STATUS_QUERY_NAME)
			.setParameter(NAME_PARAM_NAME, name)
			.uniqueResult();
		return commitStatus;
	}
}
