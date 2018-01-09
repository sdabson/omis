package omis.condition.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.datatype.DateRange;
import omis.offender.domain.Offender;

/** 
 * Agreement Interface.
 * 
 * @author Jonny Santy
 * @author Trevor Isles
 * @author Annie Wahl
 * @version 0.1.2 (Nov 27, 2017)
 * @since OMIS 3.0 
 * */
public interface Agreement extends Creatable, Updatable {
	

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
	 * Gets offender.
	 * @return offender
	 */
	Offender getOffender();
	
	/**
	 * Sets offender.
	 * @param offender - Offender
	 */
	void setOffender(Offender offender);
	
	/**
	 * Gets date range.
	 * @return date range
	 */
	DateRange getDateRange();
	
	/**
	 * Returns the Category for the Agreement.
	 * @return category - AgreementCategory
	 */
	AgreementCategory getCategory();
	
	/**
	 * Sets the Category for the Agreement.
	 * @param category - AgreementCategory
	 */
	void setCategory(AgreementCategory category);
	
	/**
	 * Returns the Description for the Agreement.
	 * @return description - String
	 */
	String getDescription();
	
	/**
	 * Sets the Description for the Agreement.
	 * @param description - String
	 */
	void setDescription(String description);
	
	/**
	 * Sets date range.
	 * @param dateRange - Date Range
	 */
	void setDateRange(DateRange dateRange);

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
