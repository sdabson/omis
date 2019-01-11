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
package omis.offenderrelationship.report;

import java.util.Date;
import java.util.List;

import omis.offender.domain.Offender;
import omis.person.domain.Person;

/**
 * Service for reporting offender relations.
 * 
 * @author Yidong Li
 * @author Stephen Abson
 * @version 0.1.1 (Dec 6, 2018)
 * @since OMIS 3.0
 */
public interface OffenderRelationReportService {
	
	/**
	 * Returns offender relationship summary for specific offender.
	 * 
	 * @param offender offender
	 * @param effectiveDate effective date
	 * @return summary of offender relations
	 */
	List<OffenderRelationSummary> summarizeByOffender(Offender offender, 
			Date effectiveDate);
	
	/**
	 * Summarizes offender relations for supplied relation.
	 * 
	 * @param relation relation by which to summarize offender relations
	 * @param effectiveDate effective date
	 * @return offender relations for supplied relation
	 */
	List<OffenderRelationSummary> summarizeByRelation(Person relation,
			Date effectiveDate);
}