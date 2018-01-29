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
package omis.trackeddocument.report;

import java.util.List;
import omis.person.domain.Person;

/**
 * Report service for docket document tracking.
 * 
 * @author Yidong Li
 * @version 0.1.0 (Dec 13, 2017)
 * @since OMIS 3.0
 */
public interface DocketDocumentTrackingReportService {
	/**
	 * Returns the list of docket document receival summary.
	 * 
	 * @param defendant defendant
	 * @return list of docketDocumentReceivalSummary
	 */
	List<DocketDocumentReceivalSummary> summarizedByDefendant(
		Person defendant);
}
