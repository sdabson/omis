package omis.detainernotification.dao.impl.hibernate;


import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.detainernotification.dao.InterstateAgreementDetainerDao;
import omis.detainernotification.domain.Detainer;
import omis.detainernotification.domain.InterstateAgreementDetainer;

/**
 * InterstateAgreementDetainerDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jul 12, 2016)
 *@since OMIS 3.0
 *
 */
public class InterstateAgreementDetainerDaoHibernateImpl extends GenericHibernateDaoImpl<InterstateAgreementDetainer>
		implements InterstateAgreementDetainerDao {

	/* Query Names */
	
	private static final String FIND_INTERSTATE_AGREEMENT_DETAINER_QUERY_NAME =
			"findInterstateAgreementDetainer";
	
	private static final String 
		FIND_INTERSTATE_AGREEMENT_DETAINER_EXCLUDING_QUERY_NAME =
			"findInterstateAgreementDetainerExcluding";
	
	/* Parameter Names */
	
	private static final String DETAINER_PARAM_NAME = "detainer";
	
	private static final String INTERSTATE_AGREEMENT_DETAINER_PARAM_NAME =
			"interstateAgreementDetainer";
	
	/**
	 * Constructor
	 * @param sessionFactory - session factory
	 * @param entityName - entity name
	 */
	public InterstateAgreementDetainerDaoHibernateImpl(SessionFactory sessionFactory, 
			String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method Implementations */

	/**{@inheritDoc} */
	@Override
	public InterstateAgreementDetainer find(Detainer detainer) {
		InterstateAgreementDetainer interstateAgreementDetainer = 
				(InterstateAgreementDetainer) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
						FIND_INTERSTATE_AGREEMENT_DETAINER_QUERY_NAME)
				.setParameter(DETAINER_PARAM_NAME, detainer)
				.uniqueResult();
		return interstateAgreementDetainer;
	}

	/**{@inheritDoc} */
	@Override
	public InterstateAgreementDetainer findExcluding(Detainer detainer,
			InterstateAgreementDetainer excludedInterstateAgreementDetainer) {
		InterstateAgreementDetainer interstateAgreementDetainer = 
				(InterstateAgreementDetainer) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
						FIND_INTERSTATE_AGREEMENT_DETAINER_EXCLUDING_QUERY_NAME)
				.setParameter(DETAINER_PARAM_NAME, detainer)
				.setParameter(INTERSTATE_AGREEMENT_DETAINER_PARAM_NAME, 
						excludedInterstateAgreementDetainer)
				.uniqueResult();
		return interstateAgreementDetainer;
	}

}
