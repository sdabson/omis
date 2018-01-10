package omis.trackeddocument.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.docket.domain.Docket;
import omis.trackeddocument.dao.TrackedDocumentReceivalDao;
import omis.trackeddocument.domain.TrackedDocumentCategory;
import omis.trackeddocument.domain.TrackedDocumentReceival;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of data access object for tracked document receival.
 * 
 * @author Yidong Li
 * @version 0.1.0 (Dec 11, 2017)
 * @since OMIS 3.0
 */
public class TrackedDocumentReceivalDaoHibernateImpl
		extends GenericHibernateDaoImpl<TrackedDocumentReceival>
		implements TrackedDocumentReceivalDao {

	/* Query names. */
	
	private static final String FIND_RECEIVALS_BY_DOCKET_QUERY_NAME
		= "findTrackedDocumentReceivalsByDocket";
	
	private static final String FIND_EXISTING_RECEIVAL_QUERY_NAME
		= "findExistingTrackedDocumentReceival";
	
	/* Parameters. */
	private static final String DOCKET_PARAM_NAME = "docket";
	
	private static final String CATEGORY_PARAM_NAME = "category";
	
	private static final String RECEIVED_DATE_PARAM_NAME = "receivedDate";
	
	/* Constructors. */
	
	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * tracked document category.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public TrackedDocumentReceivalDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method implementations. */
	/** {@inheritDoc} */
	@Override
	public TrackedDocumentReceival findExistingReceival(final Docket docket, 
		final TrackedDocumentCategory category, final Date receivedDate) {
		TrackedDocumentReceival receival 
			= (TrackedDocumentReceival) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_EXISTING_RECEIVAL_QUERY_NAME)
				.setParameter(DOCKET_PARAM_NAME, docket)
				.setParameter(CATEGORY_PARAM_NAME, category)
				.setTimestamp(RECEIVED_DATE_PARAM_NAME, receivedDate)
				.uniqueResult();
		return receival;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<TrackedDocumentReceival> findReceivalsByDocket(
		final Docket docket) {
		@SuppressWarnings("unchecked")
		List<TrackedDocumentReceival> receivals = this.getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_RECEIVALS_BY_DOCKET_QUERY_NAME)
			.setParameter(DOCKET_PARAM_NAME, docket)
			.list();
		return receivals;
	}
}