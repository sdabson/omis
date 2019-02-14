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
import omis.earlyreleasetracking.dao.ExternalOppositionPartyCategoryDao;
import omis.earlyreleasetracking.domain.ExternalOppositionPartyCategory;

/**
 * External Opposition Party Category Dao Hibernate Implementation.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Feb 8, 2019)
 *@since OMIS 3.0
 *
 */
public class ExternalOppositionPartyCategoryDaoHibernateImpl
		extends GenericHibernateDaoImpl<ExternalOppositionPartyCategory>
		implements ExternalOppositionPartyCategoryDao {
	
	private static final String FIND_ALL_QUERY_NAME =
			"findAllExternalOppositionPartyCategories";
	
	/**
	 * @param sessionFactory Session Factory
	 * @param entityName Entity Name
	 */
	public ExternalOppositionPartyCategoryDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public List<ExternalOppositionPartyCategory> findAllCategories() {
		@SuppressWarnings("unchecked")
		List<ExternalOppositionPartyCategory> categories =
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_ALL_QUERY_NAME)
				.list();
		
		return categories;
	}

}
