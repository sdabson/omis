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
import omis.supervision.domain.SupervisoryOrganization;
import omis.supervision.domain.SupervisoryOrganizationTerm;

/**
 * Data access object for supervisory organization terms
 *  that provide correctional supervision for an offender.
 * 
 * @author Jason Nelson
 * @author Stephen Abson
 * @version 0.1.0 (Nov 8, 2013)
 * @since OMIS 3.0
 */
public interface SupervisoryOrganizationTermDao extends
		GenericDao<SupervisoryOrganizationTerm> {
	
	/**
	 * Returns all supervisiory organization terms for the specified offender.
	 * 
	 * @param offender offender
	 * @return all supervisiory organization terms for offender
	 */
	List<SupervisoryOrganizationTerm> findByOffender(Offender offender);
	
	/**
	 * Returns the supervisiory organization term for the offender on the date.
	 * 
	 * @param offender offender
	 * @param date date
	 * @return supervisiory organization term for offender on date
	 */
	SupervisoryOrganizationTerm findForOffenderOnDate(
			Offender offender, Date date);

	/**
	 * Returns number of supervisory organizations terms for offender between
	 * dates.
	 * 
	 * @param offender offender
	 * @param startDate start date
	 * @param endDate end date
	 * @return number of supervisory organization terms for offender between 
	 * dates
	 */
	long countForOffenderBetweenDates(
			Offender offender, Date startDate, Date endDate);

	/**
	 * Returns number of supervisory organization terms for offender between
	 * dates excluding supervisory organization terms.
	 * 
	 * @param offender offender
	 * @param startDate start date
	 * @param endDate end date
	 * @param excludeSupervisoryOrganizationTerms supervisory organization terms
	 * to exclude
	 * @return number of supervisory organization terms of offender between
	 * dates excluding supervisory organization terms
	 */
	long countForOffenderBetweenDatesExcluding(
			Offender offender, Date startDate, Date endDate,
			SupervisoryOrganizationTerm... excludeSupervisoryOrganizationTerms);

	/**
	 * Returns supervisory organization term for offender with start date.
	 * 
	 * @param offender offender
	 * @param endDate end date
	 * @return supervisory organization term for offender with start date
	 */
	SupervisoryOrganizationTerm findForOffenderWithEndDate(Offender offender,
			Date endDate);

	/**
	 * Returns supervisory organization term for offender with end date.
	 * 
	 * @param offender offender
	 * @param startDate start date
	 * @return supervisory organization term for offender with end date
	 */
	SupervisoryOrganizationTerm findForOffenderWithStartDate(Offender offender,
			Date startDate);
	
	/**
	 * Returns supervisory organization term.
	 * 
	 * @param offender offender
	 * @param supervisoryOrganization supervisory organization
	 * @param startDate start date
	 * @param endDate end date
	 * @return supervisory organization term
	 */
	SupervisoryOrganizationTerm find(Offender offender,
			SupervisoryOrganization supervisoryOrganization,
			Date startDate, Date endDate);
	
	/**
	 * Returns supervisory organization term other than supervisory organization
	 * terms to be excluded.
	 * 
	 * @param offender offender
	 * @param supervisoryOrganization supervisory organization
	 * @param startDate start date
	 * @param endDate end date
	 * @param excludedSupervisoryOrganizationTerms supervisory organization
	 * terms to exclude
	 * @return supervisory organization term
	 */
	SupervisoryOrganizationTerm findExcluding(Offender offender,
			SupervisoryOrganization supervisoryOrganization,
			Date startDate, Date endDate,
			SupervisoryOrganizationTerm...
			excludedSupervisoryOrganizationTerms);
}