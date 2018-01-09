package omis.person.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.datatype.DateRange;

/**
 * Alternative person name association.
 * 
 * <p>The {@code name} of this should <b>never</b> be equal to the {@code name}
 * of {@code person} ({@code this.getPerson().getName().equals(this.getName())}
 * should <b>always</b> return {@code false}).
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Nov 20, 2013)
 * @since OMIS 3.0
 */
public interface AlternativeNameAssociation
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
	 * Returns the name.
	 * 
	 * @param name name
	 */
	void setName(PersonName name);
	
	/**
	 * Returns the person name.
	 * 
	 * @return name
	 */
	PersonName getName();
	
	/**
	 * Sets the date range.
	 * 
	 * @param dateRange date range
	 */
	void setDateRange(DateRange dateRange);
	
	/**
	 * Returns the date range.
	 * 
	 * @return date range
	 */
	DateRange getDateRange();
	
	/**
	 * Sets the category.
	 * 
	 * @param category category
	 */
	void setCategory(AlternativeNameCategory category);
	
	/**
	 * Returns the category.
	 * 
	 * @return category
	 */
	AlternativeNameCategory getCategory();
	
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