package omis.health.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.health.domain.LabWork;
import omis.health.domain.LabWorkRequirement;
import omis.health.domain.OffenderAppointmentAssociation;

/**
 * Lab work requirement data access object.
 * 
 * @author Joel Norris
 * @version 0.1.0 (May 5, 2014)
 * @since OMIS 3.0
 */
public interface LabWorkRequirementDao extends GenericDao<LabWorkRequirement> {

	/**
	 * Returns the lab work requirement with the specified natural key 
	 * properties.
	 * 
	 * @param appointmentAssociation offender appointment association
	 * @param labWork lab work
	 * @return lab work requirement; {@code null} if no lab work requirement 
	 * found
	 */
	LabWorkRequirement find(
			OffenderAppointmentAssociation appointmentAssociation,
			LabWork labWork);

	/**
	 * Returns the lab work requirements with the specified offender appointment
	 * association.
	 * 
	 * @param offenderAppointmentAssociation offender appointment association
	 * @return list of lab work requirements
	 */
	List<LabWorkRequirement> findByOffenderAppointmentAssociaiton(
			OffenderAppointmentAssociation offenderAppointmentAssociation);
	
	/**
	 * Returns a list of all lab work requirements with the specified lab work.
	 * 
	 * @param labWork lab work
	 * @return list of lab work requirements
	 */
	List<LabWorkRequirement> findByLabWork(LabWork labWork);
	
	/**
	 * Returns a list of all lab work requirements with the specified lab work
	 * excluding any that include the specified offender appointment 
	 * association.
	 * 
	 * @param labWork lab work
	 * @param offenderAppointmentAssociation offender appointment association
	 * @return list of lab work requirements
	 */
	List<LabWorkRequirement> findByLabWorkExcluding(LabWork labWork, 
			OffenderAppointmentAssociation offenderAppointmentAssociation);
}