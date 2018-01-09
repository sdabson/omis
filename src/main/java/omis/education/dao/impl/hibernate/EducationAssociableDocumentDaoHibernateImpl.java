package omis.education.dao.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.document.domain.Document;
import omis.education.dao.EducationAssociableDocumentDao;
import omis.education.domain.EducationAssociableDocument;
import omis.offender.domain.Offender;

/**
 * EducationAssociableDocumentDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 13, 2017)
 *@since OMIS 3.0
 *
 */
public class EducationAssociableDocumentDaoHibernateImpl
		extends GenericHibernateDaoImpl<EducationAssociableDocument>
		implements EducationAssociableDocumentDao {
	
	private static final String FIND_EDUCATION_ASSOCIABLE_DOCUMENT_QUERY_NAME =
			"findEducationAssociableDocument";
	
	private static final String
		FIND_EDUCATION_ASSOCIABLE_DOCUMENT_EXCLUDING_QUERY_NAME =
			"findEducationAssociableDocumentExcluding";
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private static final String DOCUMENT_PARAM_NAME = "document";
	
	private static final String EDUCATION_ASSOCIABLE_DOCUMENT_PARAM_NAME =
			"educationAssociableDocument";
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected EducationAssociableDocumentDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public EducationAssociableDocument find(final Offender offender,
			final Document document) {
		EducationAssociableDocument educationAssociableDocument = 
				(EducationAssociableDocument) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_EDUCATION_ASSOCIABLE_DOCUMENT_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(DOCUMENT_PARAM_NAME, document)
				.uniqueResult();
				
		return educationAssociableDocument;
	}

	/**{@inheritDoc} */
	@Override
	public EducationAssociableDocument findExcluding(final Offender offender,
			final Document document,
			EducationAssociableDocument educationAssociableDocumentExcluded) {
		EducationAssociableDocument educationAssociableDocument = 
				(EducationAssociableDocument) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
						FIND_EDUCATION_ASSOCIABLE_DOCUMENT_EXCLUDING_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(DOCUMENT_PARAM_NAME, document)
				.setParameter(EDUCATION_ASSOCIABLE_DOCUMENT_PARAM_NAME,
						educationAssociableDocumentExcluded)
				.uniqueResult();
				
		return educationAssociableDocument;
	}

}
