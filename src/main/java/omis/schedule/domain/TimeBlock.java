package omis.schedule.domain;

import java.io.Serializable;
import java.util.Date;

import omis.datatype.DateRange;

/**
 * Time block for an event.
 * <p>
 * A time block is a predetermined period of time during which an event is
 * scheduled.
 * <p>
 * Each time block has an optional {@code name} property which may be used as
 * to represent the time block (as opposed to simply using the time of the date
 * range). If set, the {@code name} property should be used to distinguish
 * between time blocks.
 * @author Stephen Abson
 * @version 0.1.0 (Nov 5, 2012)
 * @since OMIS 3.0
 */
public interface TimeBlock extends Serializable {

	/**
	 * Sets the ID of the time block.
	 * @param id time block ID
	 */
	void setId(Long id);
	
	/**
	 * Returns the ID of the time block.
	 * @return time block ID
	 */
	Long getId();
	
	/**
	 * Sets the date range of the time block.
	 * @param dateRange time block date range
	 */
	void setDateRange(DateRange dateRange);
	
	/**
	 * Returns the date range of the time block.
	 * @return time block date range
	 */
	DateRange getDateRange();
	
	/**
	 * Sets the optional name of the time block.
	 * @param name optional name of time block
	 */
	void setName(String name);
	
	/**
	 * Returns the optional name of the time block.
	 * @return optional name of time block
	 */
	String getName();
	
	/**
	 * Sets the sort order.
	 * @param sortOrder sort order
	 */
	void setSortOrder(Short sortOrder);
	
	/**
	 * Returns the sort order.
	 * @return sort order
	 */
	Short getSortOrder();
	
	/**
	 * Sets whether the time block is valid.
	 * @param valid whether time block is valid
	 */
	void setValid(Boolean valid);
	
	/**
	 * Returns whether the time block is valid.
	 * @return whether time block is valid
	 */
	Boolean getValid();
	
	/**
	 * Creates and returns a date range on the day of the specified date
	 * for the time range of the block.
	 * @param date date on which to create date range
	 * @return date range of time block for day of given date
	 */
	DateRange createDateRangeForDate(Date date);
	
	/**
	 * Compares {@code this} and {@code o} for equality.
	 * <p>
	 * Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * @param object reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code o} are equal;
	 * {@code false} otherwise
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the comparison is {@code null} 
	 */
	@Override
	boolean equals(Object object);
	
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
	
	/**
	 * Returns a string representation of the time block including the times
	 * and optional name.
	 * @return string including times and optional name
	 */
	@Override
	String toString();
}