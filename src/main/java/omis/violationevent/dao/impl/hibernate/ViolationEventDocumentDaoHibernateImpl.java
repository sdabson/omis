package omis.violationevent.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.document.domain.Document;
import omis.violationevent.dao.ViolationEventDocumentDao;
import omis.violationevent.domain.ViolationEvent;
import omis.violationevent.domain.ViolationEventDocument;

/**
 * ViolationEventDocumentDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jan 17, 2017)
 *@since OMIS 3.0
 *
 */
public class ViolationEventDocumentDaoHibernateImpl extends GenericHibernateDaoImpl<ViolationEventDocument>
		implements ViolationEventDocumentDao {
	
	
	
	private static final String FIND_VIOLATION_EVENT_DOCUMENT_QUERY_NAME =
			"findViolationEventDocument";
	
	private static final String
		FIND_VIOLATION_EVENT_DOCUMENT_EXCLUDING_QUERY_NAME =
			"findViolationEventDocumentExcluding";
	
	private static final String
		FIND_VIOLATION_EVENT_DOCUMENTS_BY_VIOLATION_EVENT_QUERY_NAME =
			"findViolationEventDocumentsByViolationEvent";
	
	
	
	
	private static final String DOCUMENT_PARAM_NAME = "document";
	
	private static final String VIOLATION_EVENT_PARAM_NAME = "violationEvent";
	
	private static final String VIOLATION_EVEN_DOCUMENT_PARAM_NAME =
			"violationEventDocument";
	
	
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected ViolationEventDocumentDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public ViolationEventDocument find(final Document document,
			final ViolationEvent violationEvent) {
		ViolationEventDocument violationEventDocument = (ViolationEventDocument)
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_VIOLATION_EVENT_DOCUMENT_QUERY_NAME)
				.setParameter(DOCUMENT_PARAM_NAME, document)
				.setParameter(VIOLATION_EVENT_PARAM_NAME, violationEvent)
				.uniqueResult();
		
		return violationEventDocument;
	}

	/**{@inheritDoc} */
	@Override
	public ViolationEventDocument findExcluding(
			final ViolationEventDocument excludedViolationEventDocument,
			final Document document, final ViolationEvent violationEvent) {
		ViolationEventDocument violationEventDocument = (ViolationEventDocument)
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_VIOLATION_EVENT_DOCUMENT_EXCLUDING_QUERY_NAME)
				.setParameter(DOCUMENT_PARAM_NAME, document)
				.setParameter(VIOLATION_EVENT_PARAM_NAME, violationEvent)
				.setParameter(VIOLATION_EVEN_DOCUMENT_PARAM_NAME,
						excludedViolationEventDocument)
				.uniqueResult();
		
		return violationEventDocument;
	}

	/**{@inheritDoc} */
	@Override
	public List<ViolationEventDocument> findByViolationEvent(
			final ViolationEvent violationEvent) {
		@SuppressWarnings("unchecked")
		List<ViolationEventDocument> violationEventDocuments =
			this.getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(
					FIND_VIOLATION_EVENT_DOCUMENTS_BY_VIOLATION_EVENT_QUERY_NAME)
			.setParameter(VIOLATION_EVENT_PARAM_NAME, violationEvent)
			.list();
		
		return violationEventDocuments;
	}

}
