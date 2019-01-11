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
package omis.courtdocument.report;

import java.util.List;

import omis.offender.domain.Offender;

/** 
 * Report service for court case document summaries.
 * 
 * @author Ryan Johns
 * @author Josh Divine
 * @version 0.1.1 (Aug 6, 2018)
 * @since OMIS 3.0
 */
public interface CourtDocumentAssociationSummaryReportService {

	/** 
	 * Returns a list of court document association summaries by offender.
	 * 
	 * @param offender offender 
	 * @return list of court document association summaries
	 */
	List<CourtDocumentAssociationSummary> findByOffender(Offender offender);
	
	/** 
	 * Returns a list of court document association summaries without dockets by 
	 * offender.
	 * 
	 * @param offender offender 
	 * @return list of court document association summaries
	 */
	List<CourtDocumentAssociationSummary> findByOffenderWithoutDocket(
			Offender offender);
}