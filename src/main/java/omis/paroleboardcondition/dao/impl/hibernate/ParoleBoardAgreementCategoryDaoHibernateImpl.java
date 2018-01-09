package omis.paroleboardcondition.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.paroleboardcondition.dao.ParoleBoardAgreementCategoryDao;
import omis.paroleboardcondition.domain.ParoleBoardAgreementCategory;

/**
 * Parole Board Agreement Category DAO Hibernate Implementation.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Dec 18, 2017)
 *@since OMIS 3.0
 *
 */
public class ParoleBoardAgreementCategoryDaoHibernateImpl
		extends GenericHibernateDaoImpl<ParoleBoardAgreementCategory>
		implements ParoleBoardAgreementCategoryDao {
	
	private static final String FIND_QUERY_NAME =
			"findParoleBoardAgreementCategory";
	
	private static final String FIND_EXCLUDING_QUERY_NAME =
			"findParoleBoardAgreementCategoryExcluding";
	
	private static final String FIND_ALL_QUERY_NAME =
			"findParoleBoardAgreementCategories";
	
	private static final String NAME_PARAM_NAME = "name";
	
	private static final String PAROLE_BOARD_AGREEMENT_CATEGORY_PARAM_NAME =
			"paroleBoardAgreementCategory";
	
	/**
	 * @param sessionFactory - Session Factory
	 * @param entityName - String Entity Name
	 */
	protected ParoleBoardAgreementCategoryDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/**{@inheritDoc} */
	@Override
	public ParoleBoardAgreementCategory find(final String name) {
		ParoleBoardAgreementCategory category = (ParoleBoardAgreementCategory)
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.uniqueResult();
		
		return category;
	}

	/**{@inheritDoc} */
	@Override
	public ParoleBoardAgreementCategory findExcluding(final String name,
			final ParoleBoardAgreementCategory
				paroleBoardAgreementCategoryExcluded) {
		ParoleBoardAgreementCategory category = (ParoleBoardAgreementCategory)
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(PAROLE_BOARD_AGREEMENT_CATEGORY_PARAM_NAME,
						paroleBoardAgreementCategoryExcluded)
				.uniqueResult();
		
		return category;
	}

	/**{@inheritDoc} */
	@Override
	public List<ParoleBoardAgreementCategory> findAll() {
		@SuppressWarnings("unchecked")
		List<ParoleBoardAgreementCategory> categories = this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_ALL_QUERY_NAME)
				.list();
		
		return categories;
	}
}
