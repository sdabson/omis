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
package omis.supervision.service;

import java.util.Date;
import java.util.List;

import omis.offender.domain.Offender;
import omis.supervision.domain.CorrectionalStatus;
import omis.supervision.domain.PlacementTerm;
import omis.supervision.domain.PlacementTermChangeReason;
import omis.supervision.domain.SupervisoryOrganization;
import omis.supervision.exception.OffenderNotUnderSupervisionException;
import omis.supervision.exception.OffenderSupervisedByOrganizationException;

/**
 * Service to change supervisory organizations for an offender.
 * 
 * @author Stephen Abson
 * @version 0.1.1 (Dec 19, 2014)
 * @since OMIS 3.0
 */
public interface ChangeSupervisoryOrganizationService {

	/**
	 * Changes the supervisory organization at the date.
	 * 
	 * <p>The method will end the placement term active at the specified date.
	 * A new placement term will be created starting at the specified date.
	 * 
	 * <p>The new placement term is returned.
	 * 
	 * @param offender offender the supervisory organization of which to change
	 * on the date
	 * @param supervisoryOrganization supervisory organization
	 * @param changeReason change reason
	 * @param effectiveDate effective date
	 * @return placement term reflecting change in supervisory organization
	 * @throws OffenderNotUnderSupervisionException if offender is not
	 * under supervision on date 
	 * @throws OffenderSupervisedByOrganizationException if offender is
	 * supervised by organization on date
	 */
	PlacementTerm change(Offender offender,
			SupervisoryOrganization supervisoryOrganization,
			PlacementTermChangeReason changeReason,
			Date effectiveDate)
					throws OffenderNotUnderSupervisionException,
						OffenderSupervisedByOrganizationException;
	
	/**
	 * Returns allowed supervisory organizations for change on correctional
	 * status from supervisory organization.
	 * 
	 * @param correctionalStatus correctional status
	 * @param supervisoryOrganization supervisory organization from which to
	 * change
	 * @return allowed supervisory organizations
	 */
	List<SupervisoryOrganization> findAllowedSupervisoryOrganizationsForChange(
			CorrectionalStatus correctionalStatus,
			SupervisoryOrganization supervisoryOrganization);
	
	/**
	 * Returns change reasons.
	 * 
	 * @deprecated Use
	 * {@code findAllowedChangeReasons(CorrectionalStatus,CorrectionalStatus)}.
	 * @return change reasons
	 */
	@Deprecated
	List<PlacementTermChangeReason> findChangeReasons();
	
	/**
	 * Returns placement term change reasons for correctional status.
	 * 
	 * @param fromCorrectionalStatus correctional status from which to change
	 * @param toCorrectionalStatus correctional status to which to change
	 * @return placement term change reasons for correctional status
	 */
	List<PlacementTermChangeReason> findAllowedChangeReasons(
			CorrectionalStatus fromCorrectionalStatus,
			CorrectionalStatus toCorrectionalStatus);
}