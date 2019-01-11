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
import omis.health.domain.InternalReferral;
import omis.health.domain.OffenderAppointmentAssociation;
import omis.health.domain.ProviderAssignment;
import omis.offender.domain.Offender;

/**
 * Data access object for internal referrals.
 *
 * @author Stephen Abson
 * @author Ryan Johns
 * @author Yidong Li
 * @version 0.1.0 (Oct 31, 2018)
 * @since OMIS 3.0
 */
public interface InternalReferralDao
		extends GenericDao<InternalReferral> {

	/** Returns internal referral by business key.
	 * @param offenderAppointmentAssociation offender appointment association.
	 * @return internal referral. */
	InternalReferral find(OffenderAppointmentAssociation
			offenderAppointmentAssociation);

	/** Return referral by business key.
	 * @param offender offender.
	 * @param date date.
	 * @param startTime start time.
	 * @param provider provider
	 * @return internal referral. */
	InternalReferral find(Offender offender, Date date, Date startTime,
			ProviderAssignment providerAssignment);

	/** Returns internal referral by business key excluding.
	 * @param offenderAppointmentAssociation offender appointment association
	 * excluding.
	 * @param exclude exclude.
	 * @return internal referral. */
	InternalReferral findExcluding(
			OffenderAppointmentAssociation offenderAppointmentAssociation,
			InternalReferral exclude);


	/** Return referral by business key excluding.
	 * @param offender offender.
	 * @param date date.
	 * @param startTime start time.
	 * @param provider provider
	 * @param exclude exclude.
	 * @return internal referral. */
	InternalReferral findExcluding(Offender offender, Date date, Date startTime,
			ProviderAssignment providerAssignment, InternalReferral exclude);
	
	/** Return existing internal referral.
	 * @param offenderAppointmentAssociation offender appointment
	 * association.
	 * @return existing internal referral. */
	InternalReferral findExisting(OffenderAppointmentAssociation
		offenderAppointmentAssociation);

}