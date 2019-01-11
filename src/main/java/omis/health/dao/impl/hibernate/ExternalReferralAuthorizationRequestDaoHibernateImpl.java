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

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.facility.domain.Facility;
import omis.health.dao.ExternalReferralAuthorizationRequestDao;
import omis.health.domain.ExternalReferralAuthorizationRequest;
import omis.health.domain.MedicalFacility;
import omis.offender.domain.Offender;

/**
 * Hibernate implementation of data access object for external referral
 * authorization requests.
 * 
 * @author Stephen Abson
 * @author Yidong Li
 * @version 0.1.0 (Oct 24, 2018)
 * @since OMIS 3.0
 */
public class ExternalReferralAuthorizationRequestDaoHibernateImpl
		extends GenericHibernateDaoImpl<ExternalReferralAuthorizationRequest>
		implements ExternalReferralAuthorizationRequestDao {

	/* Query names. */
	
	private static final String FIND_BY_FACILITY_QUERY_NAME
		= "findExternalReferralAuthorizationRequestByFacility";
	
	private static final String FIND_EXISTING_QUERY_NAME
	= "findExistingExternalReferralAuthorizationRequest";
	
	/* Parameters. */
	
	private static final String FACILITY_PARAM_NAME = "facility";
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private static final String DATE_PARAM_NAME = "date";
	
	private static final String MEDICAL_FACILITY_PARAM_NAME = "medicalFacility";
	
	/* Constructors. */

	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * external referral authorization requests.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public ExternalReferralAuthorizationRequestDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<ExternalReferralAuthorizationRequest> findByFacility(
			final Facility facility) {
		@SuppressWarnings("unchecked")
		List<ExternalReferralAuthorizationRequest> requests
			= this.getSessionFactory().getCurrentSession().getNamedQuery(
					FIND_BY_FACILITY_QUERY_NAME)
				.setParameter(FACILITY_PARAM_NAME, facility).list();
		return requests;
	}
	
	/** {@inheritDoc} */
	@Override
	public ExternalReferralAuthorizationRequest find(Offender offender,
		final Facility facility, final Date date,
		final MedicalFacility medicalFacility) {
		ExternalReferralAuthorizationRequest request		
			= (ExternalReferralAuthorizationRequest) this.getSessionFactory()
		.getCurrentSession().getNamedQuery(FIND_EXISTING_QUERY_NAME)
		.setParameter(OFFENDER_PARAM_NAME, offender)
		.setParameter(FACILITY_PARAM_NAME, facility)
		.setParameter(DATE_PARAM_NAME, date)
		.setParameter(MEDICAL_FACILITY_PARAM_NAME, medicalFacility)
		.uniqueResult();
		return request;
	}
}