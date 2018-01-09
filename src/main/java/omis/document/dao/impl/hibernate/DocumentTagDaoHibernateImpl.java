package omis.document.dao.impl.hibernate;

import java.util.Arrays;
import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.document.dao.DocumentTagDao;
import omis.document.domain.Document;
import omis.document.domain.DocumentTag;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

/** Hibernate implementation of document tag dao.
 * @author Ryan Johns
 * @version 0.1.0 (May 19, 2015)
 * @since OMIS 3.0 */
public class DocumentTagDaoHibernateImpl 
					extends GenericHibernateDaoImpl<DocumentTag> 
					implements DocumentTagDao {
	/* Queries */
	private static final String FIND_BY_TAG_NAME_AND_DOCUMENT_QUERY_NAME = 
					"findDocumentTagByTagNameAndDocument";
	private static final String FIND_BY_DOCUMENT_QUERY_NAME = 
				"findDocumentTagsByDocument";
	private static final String 
		FIND_BY_TAG_NAME_AND_DOCUMENT_EXCLUDING_QUERY_NAME = 
				"findDocumentTagByTagNameAndDocumentExcluding";
	
	/* Parameters */
	private static final String TAG_NAME_PARAM_NAME = "tagName";
	private static final String DOCUMENT_PARAM_NAME = "document";
	private static final String EXCLUDING_PARAM_NAME = "excluding";
	
	/** Constructor.
	 * @param sessionFactory session factory.
	 * @param entityName entity name. */
	public DocumentTagDaoHibernateImpl(final SessionFactory sessionFactory,
					final String entityName) {
		super(sessionFactory, entityName);
	}

	
	/** {@inheritDoc} */
	@Override
	public DocumentTag findByTagNameAndDocument(final String tagName, 
					final Document document) {
		Query q = this.getSessionFactory().getCurrentSession().getNamedQuery(
						FIND_BY_TAG_NAME_AND_DOCUMENT_QUERY_NAME);
		q.setParameter(DOCUMENT_PARAM_NAME, document);
		q.setParameter(TAG_NAME_PARAM_NAME, tagName);
		return (DocumentTag) q.uniqueResult();
		
	}
	
	/** {@inheritDoc} */
	@Override
	public DocumentTag findByTagNameAndDocumentExcluding(final String tagName,
			final Document document, final DocumentTag...documentTags) {
		Query q = this.getSessionFactory().getCurrentSession().getNamedQuery(
				FIND_BY_TAG_NAME_AND_DOCUMENT_EXCLUDING_QUERY_NAME);
		q.setParameter(TAG_NAME_PARAM_NAME, tagName);
		q.setParameter(DOCUMENT_PARAM_NAME, document);
		q.setParameterList(EXCLUDING_PARAM_NAME, Arrays.asList(documentTags));
		return (DocumentTag) q.uniqueResult();
	}
	
	/** {@inheritDoc} */
	@Override
	public List<DocumentTag> findByDocument(final Document document) {
		Query q = this.getSessionFactory().getCurrentSession().getNamedQuery(
				FIND_BY_DOCUMENT_QUERY_NAME);
		q.setParameter(DOCUMENT_PARAM_NAME, document);
		return this.cast(q.list());
	}
	
	//Casts to document tags.
	@SuppressWarnings("unchecked")
	private List<DocumentTag> cast(final List<?> objs) {
		return (List<DocumentTag>) objs;
	} 
}
