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
package omis.boardhearing.dao.impl.hibernate;

import java.util.List;
import org.hibernate.SessionFactory;
import omis.boardhearing.dao.BoardHearingCategoryDao;
import omis.boardhearing.domain.BoardHearingCategory;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.paroleeligibility.domain.AppearanceCategory;

/**
 * Board Hearing Category DAO Hibernate Implementation.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Jan 24, 2018)
 *@since OMIS 3.0
 *
 */
public class BoardHearingCategoryDaoHibernateImpl
		extends GenericHibernateDaoImpl<BoardHearingCategory>
		implements BoardHearingCategoryDao {
	
	private static final String FIND_BY_APPEARANCE_CATEGORY_QUERY_NAME =
			"findBoardHearingCategoriesByAppearanceCategory";
	
	private static final String APPEARANCE_CATEGORY_PARAM_NAME =
			"appearanceCategory";
	
	/**
	 * @param sessionFactory - Session Factory
	 * @param entityName - String entity name
	 */
	protected BoardHearingCategoryDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/**{@inheritDoc} */
	@Override
	public List<BoardHearingCategory> findByAppearanceCategory(
			final AppearanceCategory appearanceCategory) {
		@SuppressWarnings("unchecked")
		List<BoardHearingCategory> categories = this.getSessionFactory()
				.getCurrentSession().getNamedQuery(
						FIND_BY_APPEARANCE_CATEGORY_QUERY_NAME)
				.setParameter(APPEARANCE_CATEGORY_PARAM_NAME,
						appearanceCategory)
				.list();
		
		return categories;
	}
}
