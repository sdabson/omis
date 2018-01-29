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
package omis.trackeddocument.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.trackeddocument.dao.TrackedDocumentCategoryDao;
import omis.trackeddocument.domain.TrackedDocumentCategory;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of data access object for tracked document category.
 * 
 * @author Yidong Li
 * @version 0.1.0 (Dec 11, 2017)
 * @since OMIS 3.0
 */
public class TrackedDocumentCategoryDaoHibernateImpl
		extends GenericHibernateDaoImpl<TrackedDocumentCategory>
		implements TrackedDocumentCategoryDao {
	/* Query names. */
	private static final String FIND_CATEGORIES_QUERY_NAME
		= "findTrackedDocumentCategories";

	/* Constructors. */
	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * tracked document category.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public TrackedDocumentCategoryDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method implementations. */
	/** {@inheritDoc} */
	@Override
	public List<TrackedDocumentCategory> findCategories() {
		@SuppressWarnings("unchecked")
		List<TrackedDocumentCategory> categories = this.getSessionFactory()
			.getCurrentSession().getNamedQuery(FIND_CATEGORIES_QUERY_NAME)
			.list();
		return categories;
	}
}