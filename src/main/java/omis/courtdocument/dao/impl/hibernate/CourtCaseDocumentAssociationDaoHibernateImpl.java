package omis.courtdocument.dao.impl.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import omis.courtcase.domain.CourtCase;
import omis.courtdocument.dao.CourtCaseDocumentAssociationDao;
import omis.courtdocument.domain.CourtCaseDocumentAssociation;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.document.domain.Document;
import omis.offender.domain.Offender;

/** Hibernate implementation of court document association dao.
 * @author Ryan Johns
 * @version 0.1.0 (Nov 18, 2015)
 * @since OMIS 3.0 */
public class CourtCaseDocumentAssociationDaoHibernateImpl 
	extends GenericHibernateDaoImpl<CourtCaseDocumentAssociation>
		implements CourtCaseDocumentAssociationDao {
	/* Queries */
	private static final String 
		FIND_BY_DOCUMENT_AND_COURT_CASE_QUERY_NAME =
			"findCourtDocumentAssociationsByDocumentAndCourtCase";
	private static final String 
		FIND_BY_DOCUMENT_AND_COURT_CASE_EXCLUDING_QUERY_NAME = 
			"findCourtDocumentAssociationsByDocumentAndCourtCaseExcluding";
	private static final String
		FIND_COUNT_BY_OFFENDER 
			= "findCourtDocumentAssociationByOffender";
	
	/* Parameters */
	private static final String DOCUMENT_PARAM_NAME = "document";
	private static final String COURT_CASE_PARAM_NAME = "courtCase";
	private static final String EXCLUDING_PARAM_NAME = "excluding";
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	/** Constructor. 
	 * @param sessionFactory - session factory.
	 * @param entityName - entity name. */
	public CourtCaseDocumentAssociationDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<CourtCaseDocumentAssociation> findByDocumentAndCourtCase(
			final Document document, final CourtCase courtCase) {
		Query q = this.getSessionFactory().getCurrentSession().getNamedQuery(
				FIND_BY_DOCUMENT_AND_COURT_CASE_QUERY_NAME);
		q.setEntity(DOCUMENT_PARAM_NAME, document);
		q.setEntity(COURT_CASE_PARAM_NAME, courtCase);
		return this.cast(q.list());
	}
	
	/** {@inheritDoc} */
	@Override
	public List<CourtCaseDocumentAssociation> 
		findByDocumentAndCourtCaseExcluding(
			final Document document, final CourtCase courtCase, 
			final CourtCaseDocumentAssociation...excluding) {
		Query q = this.getSessionFactory().getCurrentSession().getNamedQuery(
				FIND_BY_DOCUMENT_AND_COURT_CASE_EXCLUDING_QUERY_NAME);
		q.setEntity(DOCUMENT_PARAM_NAME, document);
		q.setEntity(COURT_CASE_PARAM_NAME, courtCase);
		q.setParameterList(EXCLUDING_PARAM_NAME, excluding);
		return this.cast(q.list());
	}
	
	/** {@inhertiDoc} */
	@Override
	public Integer findCountByOffender(final Offender offender) {
		Query q = this.getSessionFactory().getCurrentSession().getNamedQuery(
				FIND_COUNT_BY_OFFENDER);
		q.setEntity(OFFENDER_PARAM_NAME, offender);
		return ((Long) q.uniqueResult()).intValue();
	}
	
		
	/* Cast. */
	@SuppressWarnings("unchecked")
	private List<CourtCaseDocumentAssociation> cast(final List<?> obj) {
		return (List<CourtCaseDocumentAssociation>) obj;
	}
}
