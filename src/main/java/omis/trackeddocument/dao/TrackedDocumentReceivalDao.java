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
package omis.trackeddocument.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.docket.domain.Docket;
import omis.trackeddocument.domain.TrackedDocumentCategory;
import omis.trackeddocument.domain.TrackedDocumentReceival;

/**
 * Data access object for tracked document receival.
 * 
 * @author Yidong Li
 * @version 0.1.0 (Dec 11, 2017)
 * @since OMIS 3.0
 */
public interface TrackedDocumentReceivalDao
		extends GenericDao<TrackedDocumentReceival> {
	/**
	 * Finds existing receivals.
	 * 
	 * @param docket docket
	 * @param category tracked document category
	 * @param receivedDate received date
	 * @return TrackedDocumentReceival existing tracked document receival
	 */
	TrackedDocumentReceival findExistingReceival(Docket docket, 
		TrackedDocumentCategory category, Date receivedDate);

	/**
	 * Returns receivals.
	 * 
	 * @param docket docket
	 * @return a list of tracked document receival
	 */
	List<TrackedDocumentReceival> findReceivalsByDocket(Docket docket);
}