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
package omis.caution.dao.impl.hibernate;

import java.util.List;

import omis.caution.dao.CautionDescriptionDao;
import omis.caution.domain.CautionDescription;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of data access object for caution descriptions.
 * 
 * @author Stephen Abson
 * @version 0.1.1 (Jan 23, 2013)
 * @since OMIS 3.0
 */
public class CautionDescriptionDaoHibernateImpl
		extends GenericHibernateDaoImpl<CautionDescription>
		implements CautionDescriptionDao {

	/**
	 * Instantiates a data access object for caution descriptions with the
	 * specified session factory and entity name.
	 *
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public CautionDescriptionDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<CautionDescription> findAll() {
		@SuppressWarnings("unchecked")
		List<CautionDescription> descriptions = getSessionFactory()
			.getCurrentSession().getNamedQuery("findCautionDescriptions")
			.list();
		return descriptions;
	}

	/** {@inheritDoc} */
	@Override
	public CautionDescription find(final String name) {
		CautionDescription cautionDescription = (CautionDescription)
			getSessionFactory()
				.getCurrentSession().getNamedQuery("findCautionDescription")
				.setParameter("name", name).uniqueResult();
		return cautionDescription;
	}
}