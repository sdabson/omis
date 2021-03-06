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
package omis.supervision.dao;

import omis.dao.GenericDao;
import omis.supervision.domain.AllowedCorrectionalStatusChangeReasonRule;
import omis.supervision.domain.CorrectionalStatus;
import omis.supervision.domain.PlacementTermChangeReason;

/**
 * Data access object for rule to allow correctional status change reason.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Jan 12, 2015)
 * @since OMIS 3.0
 */
public interface AllowedCorrectionalStatusChangeReasonRuleDao
		extends GenericDao<AllowedCorrectionalStatusChangeReasonRule> {

	/**
	 * Returns rule to allow correctional status change reason.
	 * 
	 * @param fromCorrectionalStatus correctional status from which to change
	 * @param toCorrectionalStatus correctional status to which to change
	 * @param changeReason change reason
	 * @return rule to allowe correctional status change reason
	 */
	AllowedCorrectionalStatusChangeReasonRule find(
			CorrectionalStatus fromCorrectionalStatus,
			CorrectionalStatus toCorrectionalStatus,
			PlacementTermChangeReason changeReason);
}