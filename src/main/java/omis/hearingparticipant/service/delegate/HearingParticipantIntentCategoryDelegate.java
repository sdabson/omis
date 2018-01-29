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
package omis.hearingparticipant.service.delegate;

import java.util.List;
import omis.hearingparticipant.dao.HearingParticipantIntentCategoryDao;
import omis.hearingparticipant.domain.HearingParticipantIntentCategory;

/**
 * Hearing Participant Intent Category Delegate.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Jan 16, 2018)
 *@since OMIS 3.0
 *
 */
public class HearingParticipantIntentCategoryDelegate {
	
	private final HearingParticipantIntentCategoryDao
		hearingParticipantIntentCategoryDao;
	
	/**
	 * Contructor for HearingParticipantIntentCategoryDelegate.
	 * @param hearingParticipantIntentCategoryDao - Hearing Participant Intent
	 * Category Dao
	 */
	public HearingParticipantIntentCategoryDelegate(
			final HearingParticipantIntentCategoryDao
				hearingParticipantIntentCategoryDao) {
		this.hearingParticipantIntentCategoryDao =
				hearingParticipantIntentCategoryDao;
	}
	
	/**
	 * Returns a list of all HearingParticipantIntentCategorys.
	 * @return List of all HearingParticipantIntentCategorys
	 */
	public List<HearingParticipantIntentCategory> findAll() {
		return this.hearingParticipantIntentCategoryDao.findAll();
	}
}
