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
package omis.health.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.health.dao.LabWorkCategoryDao;
import omis.health.domain.LabWorkCategory;

import org.hibernate.SessionFactory;

/**
 * Lab work category data access object hibernate implementaiton.
 * 
 * @author Joel Norris
 * @author Yidong Li
 * @version 0.1.0 (Nov. 9, 2014)
 * @since OMIS 3.0
 */
public class LabWorkCategoryDaoHibernateImpl 
	extends GenericHibernateDaoImpl<LabWorkCategory>
	implements LabWorkCategoryDao {
	/* Queries */
	private static final String FINB_EXISTING_CATEGORY
	= "findExistingLabWorkCategory";
	
	/* Parameter name */
	private static final String NAME_PARAMETER_NAME = "name";

	/**
	 * Instantiates a lab work category dao with the specified session factory
	 * and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public LabWorkCategoryDaoHibernateImpl(final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<LabWorkCategory> findAll() {
		@SuppressWarnings("unchecked")
		List<LabWorkCategory> categories = this.getSessionFactory()
			.getCurrentSession().getNamedQuery("findLabWorkCategories")
			.list();
		return categories;
	}
	
	/** {@inheritDoc} */
	@Override
	public LabWorkCategory findExisting(String name) {
		LabWorkCategory category = (LabWorkCategory) this.getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FINB_EXISTING_CATEGORY)
			.setParameter(NAME_PARAMETER_NAME, name)
			.setReadOnly(true)
			.uniqueResult();
		return category;
	}
}