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
import omis.facility.domain.Facility;
import omis.health.domain.Lab;
import omis.health.domain.LabWork;
import omis.health.domain.LabWorkCategory;
import omis.health.domain.LabWorkReferral;
import omis.health.domain.OffenderAppointmentAssociation;
import omis.offender.domain.Offender;

/**
 * Lab work data access object.
 * 
 * @author Joel Norris
 * @author Yidong Li
 * @version 0.1.0 (Nov 8, 2018)
 * @since OMIS 3.0
 */
public interface LabWorkDao extends GenericDao<LabWork> {

	/**
	 * Returns the lab work with the specified offender appointment
	 * association.
	 * 
	 * @param association association
	 * @return lab work; {@code null} if no lab work found
	 */
	LabWork find(OffenderAppointmentAssociation association);
	
	/**
	 * Returns the lab work with the specified properties.
	 * 
	 * @param offenderAppointmentAssociation offender appointment
	 * association
	 * @param category lab work category
	 * @param sampleLab sample lab
	 * @param resultsLab results lab
	 * @return matching lab work
	 */
	LabWork findLabWork(
			OffenderAppointmentAssociation offenderAppointmentAssociation,
			LabWorkCategory category, Lab sampleLab, Lab resultsLab);
	
	/**
	 * Returns a list of lab work with the specified offender appointment
	 * association, category, sample lab, and results lab excluding the 
	 * specified lab work.
	 *  
	 * @param labWork lab work
	 * @param offenderAppointmentAssociation offender appointment association
	 * @param category lab work category
	 * @param sampleLab lab where the sample will be taken
	 * @param resultsLab the lab where the results will be acquired
	 * @return list of lab work matching criteria
	 */
	List<LabWork> findExcluding(LabWork labWork, 
			OffenderAppointmentAssociation offenderAppointmentAssociation,
			LabWorkCategory category, Lab sampleLab, Lab resultsLab);

	/**
	 * Returns a list of lab work that share a lab work requirement with the
	 * specified offender appointment association.
	 * 
	 * @param offenderAppointmentAssociation offender appointment association
	 * @return list of required lab work
	 */
	List<LabWork> findRequiredLabWork(
			OffenderAppointmentAssociation offenderAppointmentAssociation);
	
	/**
	 * Returns a list of lab work that are associated with the specified
	 * lab work referral.
	 * 
	 * @param referral lab work referral
	 * @return list of associated lab work
	 */
	List<LabWork> findLabWorkByReferral(LabWorkReferral referral);
	
	/**
	 * Returns a list of lab work that share the same offender appointment 
	 * association.
	 * 
	 * @param offenderAppointmentAssociation offender appointment association
	 * @return list of related lab work
	 */
	List<LabWork> findByOffenderAppointmentAssociation(
			OffenderAppointmentAssociation offenderAppointmentAssociation);

	/**
	 * Returns lab works for the specified offender whose sample date matches
	 * the specified date.
	 * 
	 * @param offender offender
	 * @param date date to match
	 * @return list of matching lab works
	 */
	List<LabWork> findByOffenderAndDate(Offender offender, Date date);
	
	/**
	 * Returns the incomplete lab work by offender.
	 * 
	 * @param offender offender
	 * @param facility facility
	 * @param startDate start date
	 * @param endDate end date
	 * @param sampleMustBeTaken yes or no to taking sample
	 * @return lab work;
	 */
	List<LabWork> findIncompleteByOffender(Offender offender, Facility facility, 
			Date startDate, Date endDate, Boolean sampleMustBeTaken);

	/**
	 * Returns the incomplete lab work by offender.
	 * 
	 * @param offender offender
	 * @param facility facility
	 * @param startDate start date
	 * @param endDate end date
	 * @param sampleMustBeTaken yes or no to taking sample
	 * @return lab work;
	 */
	List<LabWork> findIncompleteByOffenderBySearch(Offender offender, 
			Facility facility, Date startDate, Date endDate, 
			Boolean sampleMustBeTaken);
	
	/**
	 * Returns the incomplete lab work by facility.
	 * 
	 * @param facility facility
	 * @param startDate start date
	 * @param endDate end date
	 * @param sampleMustBeTaken yes or no to taking sample
	 * @return lab work;
	 */	
	 List<LabWork> findIncompleteByFacility(Facility facility, Date startDate, 
			 Date endDate, Boolean sampleMustBeTaken);
	 
	 /**
	 * Returns the existing lab work.
	 * 
	 * @param offenderAppointmentAssociation offender appointment
	 * association
	 * @return lab work;
	 */	
	 LabWork findExisting(OffenderAppointmentAssociation
		offenderAppointmentAssociation);
}