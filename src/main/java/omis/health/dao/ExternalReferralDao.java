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
package omis.health.dao;

import java.util.Date;

import omis.dao.GenericDao;
import omis.health.domain.ExternalReferral;
import omis.health.domain.ExternalReferralAuthorization;
import omis.health.domain.OffenderAppointmentAssociation;
import omis.health.domain.ProviderAssignment;
import omis.offender.domain.Offender;

/**
 * Data access object for external referrals.
 * 
 * @author Stephen Abson
 * @author Sheronda Vaughn
 * @author Yidong Li
 * @version 0.1.0 (Nov 7, 2018)
 * @since OMIS 3.0
 */
public interface ExternalReferralDao
		extends GenericDao<ExternalReferral> {

	/**
	 * Returns the external referral authorized by the authorization.
	 * 
	 * <p>Returns {@code null} if no external referral is authorized by the
	 * authorization.
	 * 
	 * @param authorization authorization
	 * @return external referral authorized by authorization
	 */
	ExternalReferral findByAuthorization(
			ExternalReferralAuthorization authorization);
	
	/**
	 * Returns the external referral by business key.
	 * @param offender offender
	 * @param date date	
	 * @param startTime startTime	
	 * @param providerAssignment provider assignment
	 * @return external referral
	 */
	// TODO: Remove providerAssignment as it is not part of business key - SA
	ExternalReferral find(Offender offender, Date date, Date startTime, 
			ProviderAssignment providerAssignment);

	/**
	 * Returns the external referral by specified properties until matching
	 * excluded referral.
	 * 
	 * @param excludedReferral referral to exclude
	 * @param offender offender
	 * @param date date
	 * @param startTime start time
	 * @param providerAssignment provider assignment
	 * @return external referral with specified properties
	 */
	// TODO: Remove providerAssignment as it is not part of business key - SA
	ExternalReferral findExcluding(ExternalReferral excludedReferral,
			Offender offender, Date date, Date startTime,
			ProviderAssignment providerAssignment);
	
	/**
	 * Returns the existing external referral by offender appointment
	 * association.
	 * 
	 * @param offenderAppointmentAssociation offender appointment
	 * association
	 * @return existing external referral
	 */
	ExternalReferral findExisting(OffenderAppointmentAssociation
		offenderAppointmentAssociation);
}