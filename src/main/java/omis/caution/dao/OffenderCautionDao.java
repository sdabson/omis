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
package omis.caution.dao;

import java.util.Date;
import java.util.List;

import omis.caution.domain.CautionDescription;
import omis.caution.domain.CautionSource;
import omis.caution.domain.OffenderCaution;
import omis.dao.GenericDao;
import omis.datatype.DateRange;
import omis.offender.domain.Offender;

/**
 * Data access object for cautions.
 * 
 * @author Stephen Abson
 * @version 0.1.4 (Nov 18, 2013)
 * @since OMIS 3.0
 * @see OffenderCaution
 */
public interface OffenderCautionDao
		extends GenericDao<OffenderCaution> {

	/**
	 * Returns cautions by offender.
	 * 
	 * @param offender offender whose cautions to return
	 * @return cautions for offender
	 */
	List<OffenderCaution> findByOffender(Offender offender);
	
	/**
	 * Returns caution be offender on date.
	 * 
	 * @param offender offender
	 * @param date date
	 * @return cautions by offender on date
	 */
	List<OffenderCaution> findByOffenderOnDate(Offender offender, Date date);
	
	/**
	 * Returns the caution with the specified properties.
	 * 
	 * @param offender offender
	 * @param dateRange date range
	 * @param source source
	 * @param description description
	 * @return returns the caution with the specified properties or
	 * {@code null} if not found
	 */
	OffenderCaution find(Offender offender, DateRange dateRange,
			CautionSource source, CautionDescription description);
	
	/**
	 * Returns the caution with the specified properties excluding the specified
	 * caution.
	 * 
	 * @param offender offender
	 * @param dateRange date range
	 * @param source source
	 * @param description description
	 * @param excludedCaution caution to exclude
	 * @return returns the caution with the specified properties or
	 * {@code null} if not found
	 */
	OffenderCaution findExcluding(Offender offender, DateRange dateRange,
			CautionSource source, CautionDescription description,
			OffenderCaution excludedCaution);
}