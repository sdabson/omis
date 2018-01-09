package omis.document.dao.impl.hibernate;

import java.math.BigDecimal;
import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.document.dao.DocumentDao;
import omis.document.domain.Document;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

/** Hibernate implementation of document dao.
 * @author Ryan Johns
 * @author Annie Jacques
 * @version 0.1.0 (Feb 23, 2017)
 * @since OMIS 3.0 */
public class DocumentDaoHibernateImpl 
				extends GenericHibernateDaoImpl<Document>
				implements DocumentDao {
	/* Queries */
	private static final String FIND_BY_FILENAME_EXCLUDING_QUERY_NAME = 
					"findDocumentsByFilenameExcluding";
	private static final String FIND_BY_FILENAME_QUERY_NAME =
					"findDocumentsByFilename";
	private static final String FIND_NEXT_DOCUMENT_FILE_NAME_QUERY_NAME = 
					"findNextDocumentFilename";
	
	/* Parameters */
	private static final String FILENAME_PARAM_NAME = "filename";
	private static final String EXCLUDED_DOCUMENTS = "documents";
	
	/** Constructor.
	 * @param sessionFactory session factory.
	 * @param entityName entity name. */
	public DocumentDaoHibernateImpl(final SessionFactory sessionFactory,
					final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<Document> findByFileNameExcluding(final String fileName, 
					final Document...excludedDocuments) {
		Query q = this.getSessionFactory().getCurrentSession().getNamedQuery(
						FIND_BY_FILENAME_EXCLUDING_QUERY_NAME);
		q.setString(FILENAME_PARAM_NAME, fileName);
		q.setParameterList(EXCLUDED_DOCUMENTS, excludedDocuments);
		return this.cast(q.list());
	}
	
	/** {@inheritDoc} */
	@Override
	public List<Document> findByFileName(final String fileName) {
		Query q = this.getSessionFactory().getCurrentSession().getNamedQuery(
						FIND_BY_FILENAME_QUERY_NAME);
		q.setString(FILENAME_PARAM_NAME, fileName);
		return this.cast(q.list());
	}
	
	/** {@inheritDoc} */
	@Override
	public String findNextDocumentFilenameDistinguisher() {
		Query q = this.getSessionFactory().getCurrentSession().getNamedQuery(
				FIND_NEXT_DOCUMENT_FILE_NAME_QUERY_NAME);
		return ((BigDecimal) q.uniqueResult()).toPlainString();
	}
	
	//Casts to document.
	@SuppressWarnings("unchecked")
	private List<Document> cast(final List<?> objs) {
		return (List<Document>) objs;
	} 
}
