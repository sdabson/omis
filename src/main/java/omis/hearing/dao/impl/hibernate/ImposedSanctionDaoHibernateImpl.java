package omis.hearing.dao.impl.hibernate;


import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.hearing.dao.ImposedSanctionDao;
import omis.hearing.domain.ImposedSanction;
import omis.hearing.domain.Infraction;

/**
 * ImposedSanctionDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Apr 17, 2017)
 *@since OMIS 3.0
 *
 */
public class ImposedSanctionDaoHibernateImpl
	extends GenericHibernateDaoImpl<ImposedSanction>
	implements ImposedSanctionDao{
	
	private static final String FIND_IMPOSED_SANCTION_QUERY_NAME =
			"findImposedSanction";
	
	private static final String FIND_IMPOSED_SANCTION_EXCLUDING_QUERY_NAME =
			"findImposedSanctionExcluding";
	
	private static final String FIND_IMPOSED_SANCTIONS_BY_INFRACTION_QUERY_NAME =
			"findImposedSanctionsByInfraction";
	
	private static final String DESCRIPTION_PARAM_NAME = "description";
	
	private static final String IMPOSED_SANCTION_PARAM_NAME = "imposedSanction";
	
	private static final String INFRACTION_PARAM_NAME = "infraction";
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected ImposedSanctionDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public ImposedSanction find(final Infraction infraction,
			final String description) {
		ImposedSanction imposedSanction =
				(ImposedSanction) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_IMPOSED_SANCTION_QUERY_NAME)
				.setParameter(INFRACTION_PARAM_NAME, infraction)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.uniqueResult();
		
		return imposedSanction;
	}

	/**{@inheritDoc} */
	@Override
	public ImposedSanction findExcluding(final Infraction infraction,
			final String description,
			final ImposedSanction imposedSanctionExcluded) {
		ImposedSanction imposedSanction =
				(ImposedSanction) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_IMPOSED_SANCTION_EXCLUDING_QUERY_NAME)
				.setParameter(INFRACTION_PARAM_NAME, infraction)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.setParameter(IMPOSED_SANCTION_PARAM_NAME,
						imposedSanctionExcluded)
				.uniqueResult();
		
		return imposedSanction;
	}

	/**{@inheritDoc} */
	@Override
	public ImposedSanction findByInfraction(final Infraction infraction) {
		ImposedSanction imposedSanction =
				(ImposedSanction) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_IMPOSED_SANCTIONS_BY_INFRACTION_QUERY_NAME)
				.setParameter(INFRACTION_PARAM_NAME, infraction)
				.uniqueResult();
		
		return imposedSanction;
	}

}
