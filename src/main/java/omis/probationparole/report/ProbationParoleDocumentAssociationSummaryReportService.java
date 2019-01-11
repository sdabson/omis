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
package omis.probationparole.report;

import java.util.List;
import omis.offender.domain.Offender;

/**
 * Probation Parole Document Association Summary Report Service.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Dec 6, 2018)
 *@since OMIS 3.0
 *
 */
public interface ProbationParoleDocumentAssociationSummaryReportService {
	
	/**
	 * Returns a list of Probation Parole Document Association Summaries for
	 * the specified offender.
	 * 
	 * @param offender - Offender
	 * @return List of Probation Parole Document Association Summaries for
	 * the specified offender.
	 */
	List<ProbationParoleDocumentAssociationSummary> findByOffender(
			Offender offender);
	
}
