package omis.health.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.health.domain.component.LabWorkOrder;
import omis.health.domain.component.LabWorkSampleRestrictions;

/**
 * Request for a lab work to be required as part of a health request.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jun 3, 2014)
 * @since OMIS 3.0
 */
public interface LabWorkRequirementRequest
		extends Creatable, Updatable {

	/**
	 * Sets the ID.
	 * 
	 * @param id ID
	 */
	void setId(Long id);
	
	/**
	 * Returns the ID.
	 * 
	 * @return ID
	 */
	Long getId();
	
	/**
	 * Sets the health request.
	 * 
	 * @param healthRequest health request
	 */
	void setHealthRequest(HealthRequest healthRequest);
	
	/**
	 * Returns the health request.
	 * 
	 * @return health request
	 */
	HealthRequest getHealthRequest();
	
	/**
	 * Sets the order for the lab work appointment.
	 * 
	 * @param order order for lab work appointment
	 */
	void setOrder(LabWorkOrder order);
	
	/**
	 * Returns the order for the lab work appointment.
	 * 
	 * @return order for lab work appointment
	 */
	LabWorkOrder getOrder();
	
	/**
	 * Sets the category.
	 * 
	 * @param category category
	 */
	void setCategory(LabWorkCategory category);
	
	/**
	 * Returns the category.
	 * 
	 * @return category
	 */
	LabWorkCategory getCategory();
	
	/**
	 * Sets the sample date.
	 * 
	 * @param sampleDate sample date
	 */
	void setSampleDate(Date sampleDate);
	
	/**
	 * Returns the sample date.
	 * 
	 * @return sample date
	 */
	Date getSampleDate();
	
	/**
	 * Sets the sample lab.
	 * 
	 * @param sampleLab sample lab
	 */
	void setSampleLab(Lab sampleLab);
	
	/**
	 * Returns the sample lab.
	 * 
	 * @return sample lab
	 */
	Lab getSampleLab();
	
	/**
	 * Sets the sample restrictions.
	 * 
	 * @param sampleRestrictions sample restrictions
	 */
	void setSampleRestrictions(LabWorkSampleRestrictions sampleRestrictions);
	
	/**
	 * Returns the sample restrictions.
	 * 
	 * @return sample restrictions
	 */
	LabWorkSampleRestrictions getSampleRestrictions();
	
	/**
	 * Sets the results lab.
	 * 
	 * @param resultsLab results lab
	 */
	void setResultsLab(Lab resultsLab);
	
	/**
	 * Returns the results lab.
	 *  
	 * @return results lab
	 */
	Lab getResultsLab();
	
	/**
	 * Sets the scheduling notes.
	 * 
	 * @param schedulingNotes scheduling notes
	 */
	void setSchedulingNotes(String schedulingNotes);
	
	/**
	 * Returns the scheduling notes.
	 * 
	 * @return scheduling notes
	 */
	String getSchedulingNotes();
	
	/**
	 * Compares {@code this} and {@code obj} for equality.
	 * 
	 * <p>Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * 
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
	 * 
	 * <p>Any mandatory property of {@code this} may be used in the hash code.
	 * If a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * 
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null}
	 */
	@Override
	int hashCode();
}