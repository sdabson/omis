package omis.specialneed.dao.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.specialneed.dao.SpecialNeedAssociableDocumentDao;
import omis.specialneed.domain.SpecialNeed;
import omis.specialneed.domain.SpecialNeedAssociableDocument;

/**
 * Hibernate implementation of the special need associable document data access 
 * object.
 *
 * @author Josh Divine
 * @version 0.1.0 (Nov 1, 2017)
 * @since OMIS 3.0
 */
public class SpecialNeedAssociableDocumentDaoHibernateImpl 
	extends GenericHibernateDaoImpl<SpecialNeedAssociableDocument> 
	implements SpecialNeedAssociableDocumentDao {
	
	/* Queries. */
	
	private static final String FIND_QUERY_NAME = 
			"findSpecialNeedAssociableDocument";
	
	private static final String FIND_EXCLUDING_QUERY_NAME = 
			"findSpecialNeedAssociableDocumentExcluding";
	
	private static final String FIND_BY_SPECIAL_NEED_QUERY_NAME = 
			"findSpecialNeedAssociableDocumentBySpecialNeed";
	
	/* Parameters. */
	
	private static final String SPECIAL_NEED_PARAM_NAME = "specialNeed";
	
	private static final String EXCLUDED_DOCUMENT_PARAM_NAME = 
			"excludedDocument";

	/** Instantiates a hibernate implementation of the data access object for 
	 *  special need associable document.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public SpecialNeedAssociableDocumentDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName); 
	}

	/** {@inheritDoc} */
	@Override
	public SpecialNeedAssociableDocument find(final SpecialNeed specialNeed) {
		SpecialNeedAssociableDocument document = (SpecialNeedAssociableDocument)
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(SPECIAL_NEED_PARAM_NAME, specialNeed)
				.uniqueResult();
		return document;
	}

	/** {@inheritDoc} */
	@Override
	public SpecialNeedAssociableDocument findExcluding( 
			final SpecialNeed specialNeed, 
			final SpecialNeedAssociableDocument excludedAssociableDocument) {
		SpecialNeedAssociableDocument document = (SpecialNeedAssociableDocument)
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(SPECIAL_NEED_PARAM_NAME, specialNeed)
				.setParameter(EXCLUDED_DOCUMENT_PARAM_NAME, 
						excludedAssociableDocument)
				.uniqueResult();
		return document;
	}

	/** {@inheritDoc} */
	@Override
	public SpecialNeedAssociableDocument findBySpecialNeed(
			final SpecialNeed specialNeed) {
		SpecialNeedAssociableDocument document = (SpecialNeedAssociableDocument)
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_SPECIAL_NEED_QUERY_NAME)
				.setParameter(SPECIAL_NEED_PARAM_NAME, specialNeed)
				.uniqueResult();
		return document;
	}	
}