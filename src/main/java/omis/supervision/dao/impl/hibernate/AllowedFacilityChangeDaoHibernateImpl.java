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
package omis.supervision.dao.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.supervision.dao.AllowedFacilityChangeDao;
import omis.supervision.domain.AllowedFacilityChange;

/**
 * Hibernate implementation of data access object for allowed facility change.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Dec 17, 2014)
 * @since OMIS 3.0
 */
public class AllowedFacilityChangeDaoHibernateImpl
		extends GenericHibernateDaoImpl<AllowedFacilityChange>
		implements AllowedFacilityChangeDao {

	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * allowed facility change.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public AllowedFacilityChangeDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
}