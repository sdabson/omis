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
package omis.grievance.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.grievance.dao.GrievanceUnitDao;
import omis.grievance.domain.GrievanceUnit;

import org.hibernate.SessionFactory;

/**
 * Grievance unit data access object hibernate implementation.
 * 
 * @author Yidong Li
 * @author Stephen Abson
 * @version 0.1.0 (May 19, 2015)
 * @since OMIS 3.0
 */
public class GrievanceUnitDaoHibernateImpl
		extends GenericHibernateDaoImpl<GrievanceUnit>
		implements GrievanceUnitDao {

	/* Query names. */
	
	private static final String FIND_UNITS_QUERY_NAME 
		= "findGrievanceUnits";
	
	private static final String FIND_UNIT_QUERY_NAME
		= "findGrievanceUnit";
	
	/* Parameter names. */
	
	private static final String NAME_PARAM_NAME = "name";
	
	/* Constructor. */
	
	/**
	 * Instantiates a grievance unit data access object
	 * with the specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public GrievanceUnitDaoHibernateImpl(
			SessionFactory sessionFactory, String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<GrievanceUnit> findAll() {
		@SuppressWarnings("unchecked")
		List<GrievanceUnit> units = 
			this.getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_UNITS_QUERY_NAME)
			.list();
		return units;
	}

	/** {@inheritDoc} */
	@Override
	public GrievanceUnit find(final String name) {
		GrievanceUnit unit = (GrievanceUnit) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_UNIT_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.uniqueResult();
		return unit;
	}
}