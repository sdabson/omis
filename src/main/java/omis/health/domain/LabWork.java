package omis.health.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.health.domain.component.LabWorkOrder;
import omis.health.domain.component.LabWorkResults;
import omis.health.domain.component.LabWorkSampleRestrictions;

/**
 * Lab Work.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Apr 1, 2014)
 * @since OMIS 3.0
 */
public interface LabWork extends Creatable, Updatable {

	/**
	 * Returns the ID.
	 * 
	 * @return id
	 */
	Long getId();
	
	/**
	 * Sets the ID.
	 * 
	 * @param id id
	 */
	void setId(Long id);
	
	/**
	 * Returns the offender appointment association.
	 * 
	 * @return offender appointment association
	 */
	OffenderAppointmentAssociation getOffenderAppointmentAssociation();
	
	/**
	 * Sets the offender appointment association.
	 * 
	 * @param offenderAppointmentAssociation offender appointment association
	 */
	void setOffenderAppointmentAssociation(
			OffenderAppointmentAssociation offenderAppointmentAssociation);
		
	/**
	 * Returns the reschedule required status.
	 * 
	 * @return reschedule required
	 */
	Boolean getRescheduleRequired();
	
	/**
	 * Sets the reschedule requires status.
	 * 
	 * @param rescheduleRequired reschedule required
	 */
	void setRescheduleRequired(Boolean rescheduleRequired);
	
	/**
	 * Returns the scheduling notes.
	 * 
	 * @return scheduling notes
	 */
	String getSchedulingNotes();
	
	/**
	 * Sets the scheduling notes.
	 * 
	 * @param schedulingNotes scheduling notes
	 */
	void setSchedulingNotes(String schedulingNotes);
	
	/**
	 * Returns the sample notes.
	 * 
	 * @return sample notes
	 */
	String getSampleNotes();
	
	/**
	 * Sets the sample notes.
	 * 
	 * @param sampleNotes sample notes
	 */
	void setSampleNotes(String sampleNotes);
	
	/**
	 * Returns the lab work order.
	 * 
	 * @return lab work order
	 */
	LabWorkOrder getOrder();
	
	/**
	 * Sets the lab work order.
	 * 
	 * @param order lab work order
	 */
	void setOrder(LabWorkOrder order);
	
	/**
	 * Returns the sample lab.
	 * 
	 * @return sample lab
	 */
	Lab getSampleLab();
	
	/**
	 * Sets the sample lab.
	 * 
	 * @param sampleLab sample lab
	 */
	void setSampleLab(Lab sampleLab);
	
	/**
	 * Returns lab work category.
	 * 
	 * @return lab work category
	 */
	LabWorkCategory getLabWorkCategory();

	/**
	 * Sets the lab work category.
	 * 
	 * @param labWorkCategory lab work category
	 */
	void setLabWorkCategory(LabWorkCategory labWorkCategory);
	
	/**
	 * Returns lab work results.
	 * 
	 * @return lab work results
	 */
	LabWorkResults getResults();
	
	/**
	 * Sets lab work results.
	 * 
	 * @param results lab work results
	 */
	void setResults(LabWorkResults results);
	
	/**
	 * Returns the lab work sample restrictions.
	 * 
	 * @return lab work sample restrictions
	 */
	LabWorkSampleRestrictions getSampleRestrictions();
	
	/**
	 * Sets the lab work sample restrictions.
	 * 
	 * @param sampleRestrictions lab work sample restrictions
	 */
	void setSampleRestrictions(LabWorkSampleRestrictions sampleRestrictions);	
	
	/**
	 * Returns sample taken.
	 * 
	 * @return sample taken
	 */
	Boolean getSampleTaken();
	
	/**
	 * Sets sample taken.
	 * 
	 * @param sampleTaken sample taken
	 */
	void setSampleTaken(Boolean sampleTaken);
	
	/**
	 * Compares {@code this} and {@code obj} for equality.
	 * <p>
	 * Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * @param obj reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code obj} are equal;
	 * {@code false} otherwise
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the comparison is {@code null} 
	 */
	@Override
	boolean equals(Object obj);
	
	/**
	 * Returns a hash code for {@code this}.
	 * <p>
	 * Any mandatory property of {@code this} may be used in the hash code. If
	 * a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null}
	 */
	@Override
	int hashCode();
}