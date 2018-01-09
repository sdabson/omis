/**
 * 
 */
package omis.specialneed.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.datatype.DateRange;
import omis.offender.domain.Offender;
import omis.offender.domain.OffenderAssociable;

/**
 * Special need.
 * 
 * @author Joel Norris 
 * @author Sheronda Vaughn
 * @version 0.1.1 (Sep 01, 2016)
 * @since OMIS 3.0
 */
public interface SpecialNeed extends OffenderAssociable, Creatable, Updatable {

	/**
	 * Returns the id of the special need.
	 * 
	 * @return the id
	 */
	Long getId();

	/**
	 * Sets the id of the special need.
	 * @param id the id to set
	 */
	void setId(Long id);

	/**
	 * Returns the comment of the special need.
	 * 
	 * @return the comment
	 */
	String getComment();

	/**
	 * Sets the comment of the special need.
	 * 
	 * @param comment the comment to set
	 */
	void setComment(String comment);

	/**
	 * Returns the source comment.
	 * 
	 * @return source comment
	 */
	String getSourceComment();
	
	/**
	 * Sets the source comment.
	 * 
	 * @param sourceComment source comment
	 */
	void setSourceComment(String sourceComment);
	
	/**
	 * Returns the date range of the special need.
	 * 
	 * @return the dateRange
	 */
	DateRange getDateRange();
	
	/**
	 * returns the description of the special need.
	 * 
	 * @return the description
	 */
	SpecialNeedCategory getCategory();

	/**
	 * Sets the description of the special need.
	 * 
	 * @param category the description to set
	 */
	void setCategory(SpecialNeedCategory category);
	
	/**
	 * Returns the source of the special need.
	 * 
	 * @return the source
	 */
	SpecialNeedSource getSource();

	/**
	 * Sets the source of the special need.
	 * 
	 * @param source the source to set
	 */
	void setSource(SpecialNeedSource source);
	
	/**
	 * Returns the classification of the special need.
	 *
	 *
	 * @return the classification
	 */
	SpecialNeedClassification getClassification();
	
	/**
	 * Sets the classification of the special need.
	 *
	 *
	 * @param classification classification
	 */
	void setClassification(SpecialNeedClassification classification);
	
	/**
	 * Returns the offender of the special need.
	 * 
	 * @return the offender
	 */
	Offender getOffender();

	/**
	 * Sets the offender of the special need.
	 * 
	 * @param offender the offender to set
	 */
	void setOffender(Offender offender);

	/**
	 * Sets the date range of the special need.
	 * 
	 * @param dateRange the dateRange to set
	 */
	void setDateRange(DateRange dateRange);
	
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