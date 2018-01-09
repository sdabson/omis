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
package omis.caution.service;

import java.util.Date;
import java.util.List;

import omis.caution.domain.CautionDescription;
import omis.caution.domain.CautionSource;
import omis.caution.domain.OffenderCaution;
import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;

/**
 * Service for cautions.
 * 
 * @author Stephen Abson
 * @version 0.1.3 (June 19, 2013)
 * @since OMIS 3.0
 */
public interface OffenderCautionService {
	
	/**
	 * Returns cautions for the offender.
	 * 
	 * @param offender offender
	 * @return cautions for offender
	 */
	List<OffenderCaution> findByOffender(Offender offender);
	
	/**
	 * Returns cautions for the offender on the date.
	 * 
	 * @param offender offender
	 * @param date date
	 * @return cautions for offender on date
	 */
	List<OffenderCaution> findByOffenderOnDate(Offender offender, Date date);
	
	/**
	 * Saves a new caution.
	 * 
	 * @param offender offender
	 * @param description description
	 * @param source source
	 * @param dateRange date range
	 * @param comment comment
	 * @param sourceComment source comment
	 * @return saved caution
	 * @throws DuplicateEntityFoundException if caution exists
	 */
	OffenderCaution save(Offender offender, CautionDescription description,
			CautionSource source, DateRange dateRange, String comment,
			String sourceComment)
				throws DuplicateEntityFoundException;
	
	/**
	 * Updates a caution.
	 * 
	 * @param caution caution
	 * @param description description
	 * @param source source
	 * @param dateRange date range
	 * @param comment comment
	 * @param sourceComment source comment
	 * @return updated caution
	 * @throws DuplicateEntityFoundException if caution exists
	 */
	OffenderCaution update(OffenderCaution caution,
			CautionDescription description, CautionSource source,
			DateRange dateRange, String comment, String sourceComment)
				throws DuplicateEntityFoundException;
	
	/**
	 * Removes the caution.
	 * 
	 * @param caution caution to remove
	 */
	void remove(OffenderCaution caution);
	
	/**
	 * Returns caution sources.
	 * 
	 * @return caution sources
	 */
	List<CautionSource> findSources();
	
	/**
	 * Returns caution descriptions.
	 * 
	 * @return caution descriptions
	 */
	List<CautionDescription> findDescriptions();
}