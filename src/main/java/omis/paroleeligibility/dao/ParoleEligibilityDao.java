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
package omis.paroleeligibility.dao;

import java.util.Date;

import omis.dao.GenericDao;
import omis.offender.domain.Offender;
import omis.paroleeligibility.domain.ParoleEligibility;

public interface ParoleEligibilityDao extends GenericDao<ParoleEligibility> {

	/**
	 * Finds a parole eligibility.
	 * 
	 * @param offender offender
	 * @param hearingEligibilityDate hearing eligibility date
	 * @return parole eligibility
	 */
	ParoleEligibility find(Offender offender, Date hearingEligibilityDate);

	/**
	 * Finds a parole eligibility, not including the specified parole 
	 * eligibility.
	 * @param paroleEligibility parole eligibility
	 * @param hearingEligibilityDate hearing eligibility date
	 * @param offender offender
	 * @return parole eligibility, not including the specified parole
	 * eligibility
	 */
	ParoleEligibility findExcluding(ParoleEligibility paroleEligibility, 
			Offender offender, Date hearingEligibilityDate);

}
