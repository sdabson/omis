package omis.health.domain;

import java.io.Serializable;
import java.util.Date;

import omis.datatype.DateRange;

/**
 * Irregular Schedule Day.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Apr 7, 2014)
 * @since OMIS 3.0
 */
public interface IrregularScheduleDay extends Serializable {

	/**
	 * Returns the ID of the irregular schedule day.
	 * 
	 * @return id
	 */
	Long getId();
	
	/**
	 * Sets the ID of the irregular schedule day.
	 * 
	 * @param id id
	 */
	void setId(Long id);
	
	/**
	 * Returns the time range of the irregular schedule day.
	 * 
	 * @return time range
	 */
	DateRange getTimeRange();
	
	/**
	 * Sets the time range of the irregular schedule day.
	 * 
	 * @param timeRange time range
	 */
	void setTimeRange(DateRange timeRange);
	
	/**
	 * Returns the day of the irregular schedule day.
	 * 
	 * @return day
	 */
	Date getDay();
	
	/**
	 * Sets the day of the irregular schedule day.
	 * 
	 * @param day day
	 */
	void setDay(Date day);
	
	/**
	 * Returns the provider assignment of the irregular schedule day.
	 * 
	 * @return provider assignment
	 */
	ProviderAssignment getProviderAssignment();
	
	/**
	 * Sets the provider assignment of the irregular schedule day.
	 * 
	 * @param providerAssignment provider assignment
	 */
	void setProviderAssignment(ProviderAssignment providerAssignment);
	
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