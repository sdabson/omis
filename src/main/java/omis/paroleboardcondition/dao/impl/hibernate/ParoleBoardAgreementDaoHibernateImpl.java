package omis.paroleboardcondition.dao.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.condition.domain.Agreement;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.paroleboardcondition.dao.ParoleBoardAgreementDao;
import omis.paroleboardcondition.domain.ParoleBoardAgreement;
import omis.paroleboardcondition.domain.ParoleBoardAgreementCategory;

/**
 * Parole Board Agreement DAO Hibernate Implementation.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Dec 18, 2017)
 *@since OMIS 3.0
 *
 */
public class ParoleBoardAgreementDaoHibernateImpl
		extends GenericHibernateDaoImpl<ParoleBoardAgreement>
		implements ParoleBoardAgreementDao {
	
	private static final String FIND_QUERY_NAME = "findParoleBoardAgreement";
	
	private static final String FIND_EXCLUDING_QUERY_NAME =
			"findParoleBoardAgreementExcluding";
	
	private static final String AGREEMENT_PARAM_NAME = "agreement";
	
	private static final String CATEGORY_PARAM_NAME = "category";
	
	private static final String PAROLE_BOARD_AGREEMENT_PARAM_NAME =
			"paroleBoardAgreement";
	
	/**
	 * @param sessionFactory - Session Factory
	 * @param entityName - String Entity Name
	 */
	protected ParoleBoardAgreementDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public ParoleBoardAgreement find(final Agreement agreement,
			final ParoleBoardAgreementCategory category) {
		ParoleBoardAgreement paroleBoardAgreement = (ParoleBoardAgreement)
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(AGREEMENT_PARAM_NAME, agreement)
				.setParameter(CATEGORY_PARAM_NAME, category)
				.uniqueResult();
		
		return paroleBoardAgreement;
	}

	/**{@inheritDoc} */
	@Override
	public ParoleBoardAgreement findExcluding(final Agreement agreement,
			final ParoleBoardAgreementCategory category,
			final ParoleBoardAgreement paroleBoardAgreementExcluding) {
		ParoleBoardAgreement paroleBoardAgreement = (ParoleBoardAgreement)
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(AGREEMENT_PARAM_NAME, agreement)
				.setParameter(CATEGORY_PARAM_NAME, category)
				.setParameter(PAROLE_BOARD_AGREEMENT_PARAM_NAME,
						paroleBoardAgreementExcluding)
				.uniqueResult();
		
		return paroleBoardAgreement;
	}

}
