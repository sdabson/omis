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

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.health.dao.InternalReferralReasonDao;
import omis.health.domain.InternalReferralReason;

/**
 * Hibernate implementation of data access object for internal referral reasons.
 * 
 * @author Stephen Abson
 * @author Yidong Li
 * @version 0.1.0 (Nov 5, 2018)
 * @since OMIS 3.0
 */
public class InternalReferralReasonDaoHibernateImpl
		extends GenericHibernateDaoImpl<InternalReferralReason>
		implements InternalReferralReasonDao {

	/* Queries. */
	
	private static final String FIND_ALL_QUERY_NAME
		= "findAllInternalReferralReasons";
	
	private static final String FIND_EXISTING_QUERY_NAME
	= "findExistingInternalReferralReasons";
	
	/* Parameters. */

	private static final String NAME_PARAM_NAME
		= "name";

	/* Constructors. */
	
	/**
	 * Hibernate implementation of data access object for internal referral
	 * reasons.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public InternalReferralReasonDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<InternalReferralReason> findAll() {
		@SuppressWarnings("unchecked")
		List<InternalReferralReason> reasons = this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_ALL_QUERY_NAME).list();
		return reasons;
	}
	
	/** {@inheritDoc} */
	@Override
	public InternalReferralReason findExisting(String name) {
		InternalReferralReason reason
		= (InternalReferralReason) this.getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_EXISTING_QUERY_NAME)
			.setParameter(NAME_PARAM_NAME, name)
			.setReadOnly(true)
			.uniqueResult();
		return reason;
	}
}