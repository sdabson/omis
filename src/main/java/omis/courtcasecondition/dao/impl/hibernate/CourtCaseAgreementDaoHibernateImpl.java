package omis.courtcasecondition.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.condition.domain.Agreement;
import omis.courtcasecondition.dao.CourtCaseAgreementDao;
import omis.courtcasecondition.domain.CourtCaseAgreement;
import omis.courtcasecondition.domain.CourtCaseAgreementCategory;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.docket.domain.Docket;
import omis.offender.domain.Offender;

/**
 * Hibernate Implementation for CourtCaseAgreement DAO.
 *  
 * @author Jonny Santy
 * @author Annie Jacques
 * @version 0.1.1 (Jun 9, 2017)
 * @since OMIS 3.0
 */
public class CourtCaseAgreementDaoHibernateImpl
	extends GenericHibernateDaoImpl <CourtCaseAgreement>
	implements CourtCaseAgreementDao{
	
	/* Query names. */
	
	private static final String FIND_QUERY_NAME = "findCourtCaseAgreement";
	
	private static final String FIND_EXCLUDING_QUERY_NAME = 
			"findCourtCaseAgreementExcluding";
	
	private static final String FIND_BY_OFFENDER_QUERY_NAME =
			"findCourtCaseAgreementsByOffender";
	
	/* Parameter names. */
	
	private static final String AGREEMENT_PARAM_NAME = "agreement";
	
	private static final String COURT_CASE_AGREEMENT_PARAM_NAME =
			"courtCaseAgreement";
	
	private static final String COURT_CASE_AGREEMENT_CAT_PARAM_NAME =
			"courtCaseAgreementCategory";

	private static final String DOCKET_PARAM_NAME = "docket";
	
	private static final String OFFENDER_PARAM_NAME = "offender";

	/* Constructor. */
	
	/**
	 * Instantiates a data access object for court case agreement with the
	 * specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	protected CourtCaseAgreementDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public CourtCaseAgreement find(final Agreement agreement, 
			final CourtCaseAgreementCategory category, final Docket docket) {
		CourtCaseAgreement courtCaseAgreement = 
				(CourtCaseAgreement) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_QUERY_NAME)
				.setParameter(AGREEMENT_PARAM_NAME, agreement)
				.setParameter(COURT_CASE_AGREEMENT_CAT_PARAM_NAME, category)
				.setParameter(DOCKET_PARAM_NAME, docket)
				.uniqueResult();
		return courtCaseAgreement;
	}

	/** {@inheritDoc} */
	@Override
	public CourtCaseAgreement findExcluding(
			final CourtCaseAgreement courtCaseAgreement,
			final Agreement agreement, final CourtCaseAgreementCategory category,
			final Docket docket) {
		CourtCaseAgreement courtCaseAgreementExcluding = 
				(CourtCaseAgreement) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(AGREEMENT_PARAM_NAME, agreement)
				.setParameter(COURT_CASE_AGREEMENT_PARAM_NAME, courtCaseAgreement)
				.setParameter(COURT_CASE_AGREEMENT_CAT_PARAM_NAME, category)
				.setParameter(DOCKET_PARAM_NAME, docket)
				.uniqueResult();
		return courtCaseAgreementExcluding;
	}

	/**{@inheritDoc} */
	@Override
	public List<CourtCaseAgreement> findByOffender(final Offender offender) {
		@SuppressWarnings("unchecked")
		List<CourtCaseAgreement> courtCaseAgreements = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.list();
		
		return courtCaseAgreements;
	}
}
