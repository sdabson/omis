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
 * @version 0.1.0 (May 5, 2014)
 * @since OMIS 3.0
 */
public interface LabWorkDao extends GenericDao<LabWork> {

	/**
	 * Returns the lab work with the specified offender appointment association.
	 * 
	 * @param association association
	 * @return lab work; {@code null} if no lab work found
	 */
	LabWork find(OffenderAppointmentAssociation association);
	
	/**
	 * Returns the lab work with the specified properties.
	 * 
	 * @param offenderAppointmentAssociation offender appointment association
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
}