package omis.presentenceinvestigation.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;

/**
 * PsychologicalSectionSummaryNote.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 1, 2017)
 *@since OMIS 3.0
 *
 */
public interface PsychologicalSectionSummaryNote extends Creatable, Updatable {
	
	/**
	 * Returns the ID of the PsychologicalSectionSummaryNote
	 * @return ID
	 */
	public Long getId();
	
	/**
	 * Sets the ID of the PsychologicalSectionSummaryNote
	 * @param id - Long
	 */
	public void setId(Long id);
	
	/**
	 * Returns the description of the PsychologicalSectionSummaryNote
	 * @return description - string
	 */
	public String getDescription();
	
	/**
	 * Sets the description of the PsychologicalSectionSummaryNote
	 * @param description - String
	 */
	public void setDescription(String description);
	
	/**
	 * Returns the date of the PsychologicalSectionSummaryNote
	 * @return date - Date
	 */
	public Date getDate();
	
	/**
	 * Sets the date of the PsychologicalSectionSummaryNote
	 * @param date - Date
	 */
	public void setDate(Date date);
	
	/**
	 * Returns the psychologicalSectionSummary of the 
	 * PsychologicalSectionSummaryNote
	 * @return psychologicalSectionSummary - PsychologicalSectionSummary
	 */
	public PsychologicalSectionSummary getPsychologicalSectionSummary();
	
	/**
	 * Sets the psychologicalSectionSummary of the 
	 * PsychologicalSectionSummaryNote
	 * @param psychologicalSectionSummary - PsychologicalSectionSummary
	 */
	public void setPsychologicalSectionSummary(
			PsychologicalSectionSummary psychologicalSectionSummary);
	
	/** Compares {@code this} and {@code obj} for equality.
	 * <p>
	 * Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * @param obj reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code obj} are equal;
	 * {@code false} otherwise
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the comparison is {@code null} */
	@Override
	boolean equals(Object obj);
	
	/** Returns a hash code for {@code this}.
	 * <p>
	 * Any mandatory property of {@code this} may be used in the hash code. If
	 * a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null} */
	@Override
	int hashCode();
	
}
