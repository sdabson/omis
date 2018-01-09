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

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.offender.domain.Offender;
import omis.supervision.domain.CorrectionalStatus;
import omis.supervision.domain.CorrectionalStatusTerm;

/**
 * Data access object for correctional status terms
 *  that designate a correctional status for an offender.
 * 
 * @author Jason Nelson
 * @author Stephen Abson
 * @version 0.1.0 (Nov 8, 2013)
 * @since OMIS 3.0
 */
public interface CorrectionalStatusTermDao extends
		GenericDao<CorrectionalStatusTerm> {
	
	/**
	 * Returns all correctional status terms for the offender.
	 * 
	 * @param offender offender
	 * @return all correctional status terms for offender
	 */
	List<CorrectionalStatusTerm> findByOffender(Offender offender);
	
	/**
	 * Returns the correctional status term for the offender on the date.
	 * 
	 * @param offender offender
	 * @param date date
	 * @return correctional status term for offender on date
	 */
	CorrectionalStatusTerm findForOffenderOnDate(Offender offender, Date date);
	
	/**
	 * Returns number of correctional statuses for offender between dates.
	 * 
	 * @param offender offender
	 * @param startDate start date
	 * @param endDate end date
	 * @return number of correctional statuses for offender between dates
	 */
	long countForOffenderBetweenDates(Offender offender, Date startDate,
			Date endDate);

	/**
	 * Returns number of correctional statuses for offender between dates
	 * excluding correctional status terms.
	 * 
	 * @param offender offender
	 * @param startDate start date
	 * @param endDate end date
	 * @param excludeCorrectionalStatusTerms correctional status terms to
	 * exclude
	 * @return number of correctional statuses for offender between dates
	 * excluding correcitonal status terms
	 */
	long countForOffenderBetweenDatesExcluding(
			Offender offender, Date startDate, Date endDate,
			CorrectionalStatusTerm... excludeCorrectionalStatusTerms);

	/**
	 * Returns correctional status term for offender with end date.
	 * 
	 * @param offender offender
	 * @param endDate end date
	 * @return correctional status term for offender with end date
	 */
	CorrectionalStatusTerm findForOffenderWithEndDate(
			Offender offender, Date endDate);

	/**
	 * Returns correctional status term for offender with start date.
	 * 
	 * @param offender offender
	 * @param startDate start date
	 * @return correctional status term for offender with start date
	 */
	CorrectionalStatusTerm findForOffenderWithStartDate(Offender offender,
			Date startDate);
	
	/**
	 * Returns correctional status term.
	 * 
	 * @param offender offender
	 * @param correctionalStatus correctional status
	 * @param startDate startDate
	 * @param endDate endDate
	 * @return correctional status term
	 */
	CorrectionalStatusTerm find(Offender offender,
			CorrectionalStatus correctionalStatus, Date startDate,
			Date endDate);
	
	/**
	 * Returns correctional status term other than correctional status
	 * terms to be excluded.
	 * 
	 * @param offender offender
	 * @param correctionalStatus correctional status
	 * @param startDate startDate
	 * @param endDate endDate
	 * @param excludedCorrectionalStatusTerms correctional status terms to
	 * exclude
	 * @return correctional status term
	 */
	CorrectionalStatusTerm findExcluding(Offender offender,
			CorrectionalStatus correctionalStatus, Date startDate, Date endDate,
			CorrectionalStatusTerm... excludedCorrectionalStatusTerms);
}