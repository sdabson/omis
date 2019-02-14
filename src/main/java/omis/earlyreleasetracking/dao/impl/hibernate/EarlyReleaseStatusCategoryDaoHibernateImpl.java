/*
 * OMIS - Offender Management Information System
 * Copyright (C) 2011 - 2017 State of Montana
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package omis.earlyreleasetracking.dao.impl.hibernate;

import java.util.List;
import org.hibernate.SessionFactory;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.earlyreleasetracking.dao.EarlyReleaseStatusCategoryDao;
import omis.earlyreleasetracking.domain.EarlyReleaseStatusCategory;

/**
 * Early Release Status Category Dao Hibernate Implementation.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Feb 8, 2019)
 *@since OMIS 3.0
 *
 */
public class EarlyReleaseStatusCategoryDaoHibernateImpl
		extends GenericHibernateDaoImpl<EarlyReleaseStatusCategory>
		implements EarlyReleaseStatusCategoryDao {
	
	private static final String FIND_QUERY_NAME =
			"findEarlyReleaseStatusCategory";

	private static final String FIND_EXCLUDING_QUERY_NAME =
			"findEarlyReleaseStatusCategoryExcluding";

	private static final String FIND_ALL_QUERY_NAME =
			"findAllEarlyReleaseStatusCategories";
	
	private static final String NAME_PARAM_KEY = "name";
	
	private static final String EARLY_RELEASE_STATUS_CATEGORY_PARAM =
			"earlyReleaseStatusCategory";
	
	/**
	 * @param sessionFactory Session Factory
	 * @param entityName Entity Name
	 */
	public EarlyReleaseStatusCategoryDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public EarlyReleaseStatusCategory find(final String name) {
		EarlyReleaseStatusCategory category =
				(EarlyReleaseStatusCategory) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(NAME_PARAM_KEY, name)
				.uniqueResult();
		
		return category;
	}

	/** {@inheritDoc} */
	@Override
	public EarlyReleaseStatusCategory findExcluding(final String name,
			final EarlyReleaseStatusCategory
				earlyReleaseStatusCategoryExcluding) {
		EarlyReleaseStatusCategory category =
				(EarlyReleaseStatusCategory) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(NAME_PARAM_KEY, name)
				.setParameter(EARLY_RELEASE_STATUS_CATEGORY_PARAM,
						earlyReleaseStatusCategoryExcluding)
				.uniqueResult();
		
		return category;
	}

	/** {@inheritDoc} */
	@Override
	public List<EarlyReleaseStatusCategory> findAllCategories() {
		@SuppressWarnings("unchecked")
		List<EarlyReleaseStatusCategory> categories =
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_ALL_QUERY_NAME)
				.list();
		
		return categories;
	}

}
