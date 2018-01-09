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
import omis.supervision.dao.AllowedCorrectionalStatusChangeReasonRuleDao;
import omis.supervision.domain.AllowedCorrectionalStatusChangeReasonRule;
import omis.supervision.domain.CorrectionalStatus;
import omis.supervision.domain.PlacementTermChangeReason;

/**
 * Hibernate implementation of data access object for rule to allow correctional
 * status change reasons.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Jan 12, 2015)
 * @since OMIS 3.0
 */
public class AllowedCorrectionalStatusChangeReasonRuleDaoHibernateImpl
		extends GenericHibernateDaoImpl<AllowedCorrectionalStatusChangeReasonRule>
		implements AllowedCorrectionalStatusChangeReasonRuleDao {

	/* Queries */
	
	private static final String FIND_QUERY_NAME
		= "findAllowedCorrectionalStatusChangeReasonRule";
	
	/* Parameters. */
	
	private static final String FROM_CORRECTIONAL_STATUS_PARAM_NAME
		= "fromCorrectionalStatus";
	
	private static final String TO_CORRECTIONAL_STATUS_PARAM_NAME
		= "toCorrectionalStatus";
	
	private static final String CHANGE_REASON_PARAM_NAME
		= "changeReason";
	
	/* Constructors. */

	/**
	 * Instantiates an Hibernate implementation of data access object for rule
	 * to allow correctional status change reasons.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public AllowedCorrectionalStatusChangeReasonRuleDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public AllowedCorrectionalStatusChangeReasonRule find(
			final CorrectionalStatus fromCorrectionalStatus,
			final CorrectionalStatus toCorrectionalStatus,
			final PlacementTermChangeReason changeReason) {
		return (AllowedCorrectionalStatusChangeReasonRule)
				this.getSessionFactory().getCurrentSession().getNamedQuery(
						FIND_QUERY_NAME)
					.setParameter(FROM_CORRECTIONAL_STATUS_PARAM_NAME,
						fromCorrectionalStatus)
					.setParameter(TO_CORRECTIONAL_STATUS_PARAM_NAME,
						toCorrectionalStatus)
					.setParameter(CHANGE_REASON_PARAM_NAME,
						changeReason).uniqueResult();
	}
}