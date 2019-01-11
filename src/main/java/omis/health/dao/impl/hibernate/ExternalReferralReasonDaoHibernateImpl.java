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
import omis.health.dao.ExternalReferralReasonDao;
import omis.health.domain.ExternalReferralReason;

/**
 * Hibernate implementation of data access object for external referral reasons.
 * 
 * @author Stephen Abson
 * @author Yidong Li
 * @version 0.1.0 (Oct 26, 2018)
 * @since OMIS 3.0
 */
public class ExternalReferralReasonDaoHibernateImpl
		extends GenericHibernateDaoImpl<ExternalReferralReason>
		implements ExternalReferralReasonDao {

	/* Query names. */
	
	private static final String FIND_ALL_QUERY_NAME
		= "findExternalReferralReasons";
	
	private static final String FIND_EXISTING_QUERY_NAME
		= "findExistingExternalReferralReason";
	
	/* Parameters. */
	
	private static final String NAME_PARAM_NAME = "name";

	/* Constructor. */
	
	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * external referral reasons.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public ExternalReferralReasonDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<ExternalReferralReason> findAll() {
		@SuppressWarnings("unchecked")
		List<ExternalReferralReason> reasons = this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_ALL_QUERY_NAME).list();
		return reasons;
	}
	
	/** {@inheritDoc} */
	@Override
	public ExternalReferralReason findExisting(final String name) {
		ExternalReferralReason reason
			= (ExternalReferralReason) this.getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_EXISTING_QUERY_NAME)
			.setParameter(NAME_PARAM_NAME, name)
			.setReadOnly(true)
			.uniqueResult();
		return reason;
	}
}
