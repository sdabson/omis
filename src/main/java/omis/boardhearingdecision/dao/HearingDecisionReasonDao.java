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
package omis.boardhearingdecision.dao;

import java.util.List;

import omis.boardhearingdecision.domain.DecisionCategory;
import omis.boardhearingdecision.domain.HearingDecisionReason;
import omis.dao.GenericDao;

/**
 * Data access object for hearing decision reason.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 22, 2018)
 * @since OMIS 3.0
 */
public interface HearingDecisionReasonDao 
	extends GenericDao<HearingDecisionReason> {

	/**
	 * Returns the hearing decision reason for the specified reason name.
	 * 
	 * @param reasonName reason name
	 * @return hearing decision reason
	 */
	HearingDecisionReason find(String reasonName);

	/**
	 * Returns the hearing decision reason for the specified reason name 
	 * excluding the specified hearing decision reason.
	 * 
	 * @param reasonName reason name
	 * @param excludedHearingDecisionReason hearing decision reason
	 * @return hearing decision reason
	 */
	HearingDecisionReason findExcluding(String reasonName, 
			HearingDecisionReason excludedHearingDecisionReason);

	/**
	 * Returns a list of hearing decision reasons for the specified decision 
	 * category.
	 * 
	 * @param decisionCategory decision category
	 * @return list of hearing decision reasons
	 */
	List<HearingDecisionReason> findByDecisionCategory(
			DecisionCategory decisionCategory);
}
