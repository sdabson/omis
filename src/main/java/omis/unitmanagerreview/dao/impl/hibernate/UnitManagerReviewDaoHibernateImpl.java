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
package omis.unitmanagerreview.dao.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.offender.domain.Offender;
import omis.staff.domain.StaffAssignment;
import omis.unitmanagerreview.dao.UnitManagerReviewDao;
import omis.unitmanagerreview.domain.UnitManagerReview;

/**
 * Hibernate implementation of the unit manager review data access object.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 29, 2018)
 * @since OMIS 3.0
 */
public class UnitManagerReviewDaoHibernateImpl 
		extends GenericHibernateDaoImpl<UnitManagerReview>
		implements UnitManagerReviewDao {

	/* Queries. */
	
	private static final String FIND_QUERY_NAME = 
			"findUnitManagerReview";
	
	private static final String FIND_EXCLUDING_QUERY_NAME = 
			"findUnitManagerReviewExcluding";
	
	/* Parameters. */
	
	private static final String STAFF_ASSIGNMENT_PARAM_NAME = "staffAssignment";
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private static final String EXCLUDED_PARAM_NAME = 
			"excludedUnitManagerReview";
	
	/** Instantiates a hibernate implementation of the data access object for 
	 *  unit manager review.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public UnitManagerReviewDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public UnitManagerReview find(final StaffAssignment staffAssignment, 
			final Offender offender) {
		UnitManagerReview unitManagerReview = (UnitManagerReview) this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(STAFF_ASSIGNMENT_PARAM_NAME, staffAssignment)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.uniqueResult();
		return unitManagerReview;
	}

	/** {@inheritDoc} */
	@Override
	public UnitManagerReview findExcluding(
			final StaffAssignment staffAssignment, final Offender offender,
			final UnitManagerReview excludedUnitManagerReview) {
		UnitManagerReview unitManagerReview = (UnitManagerReview) this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(STAFF_ASSIGNMENT_PARAM_NAME, staffAssignment)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(EXCLUDED_PARAM_NAME, excludedUnitManagerReview)
				.uniqueResult();
		return unitManagerReview;
	}
}