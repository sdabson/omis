package omis.presentenceinvestigation.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;

/**
 * Supervision history note.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Aug 8, 2017)
 * @since OMIS 3.0
 */
public interface SupervisionHistoryNote extends Creatable, Updatable {
	
	/**
	 * Returns the ID of the supervision history note.
	 *
	 *
	 * @return ID
	 */
	public Long getId();
	
	/**
	 * Sets the ID of the supervision history note.
	 *
	 *
	 * @param id ID
	 */
	public void setId(Long id);
	
	/**
	 * Returns the description of the supervision history note.
	 *
	 *
	 * @return description
	 */
	public String getDescription();
	
	/**
	 * Sets the description of the supervision history note.
	 *
	 *
	 * @param description description
	 */
	public void setDescription(String description);
	
	/**
	 * Returns the date of the supervision history note.
	 *
	 *
	 * @return date
	 */
	public Date getDate();
	
	/**
	 * Sets the date of the supervision history note.
	 *
	 *
	 * @param date date
	 */
	public void setDate(Date date);
	
	/**
	 * Returns the supervision history section summary of the supervision 
	 * history note.
	 *
	 *
	 * @return supervision history section summary
	 */
	public SupervisionHistorySectionSummary 
		getSupervisionHistorySectionSummary();
	
	/**
	 * Sets the supervision history summary of the supervision history note.
	 *
	 *
	 * @param supervisionHistorySummary
	 */
	public void setSupervisionHistorySectionSummary(
			SupervisionHistorySectionSummary supervisionHistorySectionSummary);
	
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
