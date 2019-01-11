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
package omis.health.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.datatype.DateRange;
import omis.facility.domain.Facility;
import omis.health.domain.InternalReferral;
import omis.health.domain.MedicalFacility;
import omis.health.domain.ProviderAssignment;
import omis.health.domain.ProviderTitle;
import omis.person.domain.Person;

/**
 * Provider Assignment Data Access Object.
 *
 * @author Joel Norris
 * @author Stephen Abson
 * @author Yidong Li
 * @version 0.1.0 (Oct 24, 2018)
 * @since OMIS 3.0
 */
public interface ProviderAssignmentDao
	extends GenericDao<ProviderAssignment> {

	/**
	 * Returns the provider assignment with the specified provider, facility,
	 * and dateRange.
	 *
	 * @param provider person provider
	 * @param facility facility
	 * @param dateRange dateRange
	 * @return provider assignment; {@code null} if no provider assignment
	 * is found
	 */
	ProviderAssignment find(Person provider, Facility facility,
			DateRange dateRange);
	
	/**
	 * Returns the provider assignment with the specified provider, facility,
	 * and dateRange, excluding the specified provider assignment.
	 *
	 * @param provider person provider
	 * @param facility facility
	 * @param dateRange dateRange
	 * @param assignment provider assignment
	 * @return provider assignment; {@code null} if no provider assignment
	 * is found
	 */
	ProviderAssignment findExcluding(Person provider, Facility facility,
			DateRange dateRange, ProviderAssignment assignment);

	/**
	 * Counts the amount of assignments with the specified provider and facility
	 * that that have a start or end date that fall within the specified date
	 * range.
	 *
	 * @param provider provider
	 * @param facility facility
	 * @param dateRange date range
	 * @return count of provider assignments
	 */
	Long countByDateRange(Person provider, DateRange dateRange,
			Facility facility);
	
	/**
	 * Counts the amount of assignments with the specified provider and facility
	 * that that have a start or end date that fall within the specified date
	 * range, excluding the specified provider assignment.
	 * 
	 * @param provider provider
	 * @param dateRange date range
	 * @param facility facility
	 * @param providerAssignment provider assignment
	 * @return count of provider assignments
	 */
	Long countByDateRangeExcluding(Person provider, DateRange dateRange,
			Facility facility, ProviderAssignment providerAssignment);

	/**
	 * Returns a list of all provider assignments for the specified facility
	 * on the specified date.
	 *
	 * @param facility facility
	 * @param date date
	 * @return list of provider assignments
	 */
	List<ProviderAssignment> findByFacility(Facility facility, Date date);

	/**
	 * Returns a list of all external provider assignments for the specified
	 * facility on the specified date.
	 *
	 * @param facility facility
	 * @param date date
	 * @return list of provider assignments
	 */
	List<ProviderAssignment> findExternalByFacility(
			Facility facility, Date date);
	
	/** Find provider by internal referral.
	 * @param internalReferral internal Referral.
	 * @return list of provider assignments. */
	List<ProviderAssignment> findByInternalReferral(
			InternalReferral internalReferral);
	
	/**
	 * Returns external providers assigned to medical facility on date.
	 * 
	 * @param medicalFacility medical facility
	 * @param date date
	 * @return external providers assigned to organization on date
	 */
	List<ProviderAssignment> findExternalByMedicalFacility(
			MedicalFacility medicalFaclity, Date date);

	/**
	 * Returns external providers assigned to organization on date for facility.
	 * 
	 * @param facility facility
	 * @param medicalFacility medical facility
	 * @param date date
	 * @return external providers assigned to organization on date
	 */
	List<ProviderAssignment> findExternalForFacilityByMedicalFacility(
			Facility facility, MedicalFacility medicalFacility, Date date);

	/**
	 * Returns internal provider assignments for facility on date.
	 * 
	 * @param facility facility
	 * @param date date
	 * @return internal provider assignments for facility on date
	 */
	List<ProviderAssignment> findInternalByFacilityOnDate(Facility facility,
			Date date);
	
	/**
	 * Returns the provider assignment with the specified provider, facility,
	 * and title.
	 *
	 * @param provider provider
	 * @param facility facility
	 * @param title title
	 * @return provider assignment; {@code null} if no provider assignment
	 * is found
	 */
	ProviderAssignment findExisting(Person provider, Facility facility,
		ProviderTitle title);
}