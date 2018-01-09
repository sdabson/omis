package omis.health.domain;

import java.io.Serializable;

import omis.datatype.DateRange;

/**
 * Provider Schedule.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Apr 7, 2014)
 * @since OMIS 3.0
 */
public interface ProviderSchedule extends Serializable {

	/**
	 * Returns the ID of the provider schedule.
	 * 
	 * @return id
	 */
	Long getId();
	
	/**
	 * Sets the ID of the provider schedule.
	 * 
	 * @param id id
	 */
	void setId(Long id);
	
	/**
	 * Returns the provider assignment of the provider schedule.
	 * 
	 * @return provider assignment
	 */
	ProviderAssignment getProviderAssignment();
	
	/**
	 * Sets the provider assignment of the provider schedule.
	 * 
	 * @param providerAssignment provider assignment
	 */
	void setProviderAssignment(ProviderAssignment providerAssignment);
	
	/**
	 * Returns the Sunday time range of the provider schedule.
	 * 
	 * @return Sunday time range
	 */
	DateRange getSundayTimeRange();
	
	/**
	 * Sets the Sunday time range of the provider schedule.
	 * 
	 * @param sundayTimeRange Sunday time range
	 */
	void setSundayTimeRange(DateRange sundayTimeRange);
	
	/**
	 * Returns the Monday time range of the provider schedule.
	 * 
	 * @return Monday time range
	 */
	DateRange getMondayTimeRange();
	
	/**
	 * Sets the Monday time range of the provider schedule.
	 * 
	 * @param mondayTimeRange Monday time range
	 */
	void setMondayTimeRange(DateRange mondayTimeRange);
	
	/**
	 * Returns the Tuesday time range of the provider schedule.
	 * 
	 * @return Tuesday time range
	 */
	DateRange getTuesdayTimeRange();
	
	/**
	 * Sets the Tuesday time range of the provider schedule.
	 * 
	 * @param tuesdayTimeRange Tuesday time range
	 */
	void setTuesdayTimeRange(DateRange tuesdayTimeRange);
	
	/**
	 * Returns the Wednesday time range of the provider schedule.
	 * 
	 * @return Wednesday time range
	 */
	DateRange getWednesdayTimeRange();
	
	/**
	 * Sets the Wednesday time range of the provider schedule.
	 * 
	 * @param wednesdayTimeRange Wednesday time range
	 */
	void setWednesdayTimeRange(DateRange wednesdayTimeRange);
	
	/**
	 * Returns the Thursday time range of the provider schedule.
	 * 
	 * @return Thursday time range
	 */
	DateRange getThursdayTimeRange();
	
	/**
	 * Sets the Thursday time range of the provider schedule.
	 * 
	 * @param thursdayTimeRange Thursday time range
	 */
	void setThursdayTimeRange(DateRange thursdayTimeRange);
	
	/**
	 * Returns the Friday time range of the provider schedule.
	 * 
	 * @return Friday time range
	 */
	DateRange getFridayTimeRange();
	
	/**
	 * Sets the Friday time range of the provider schedule.
	 * 
	 * @param fridayTimeRange Friday time range
	 */
	void setFridayTimeRange(DateRange fridayTimeRange);
	
	/**
	 * Returns the Saturday time range of the provider schedule.
	 * 
	 * @return Saturday time range
	 */
	DateRange getSaturdayTimeRange();
	
	/**
	 * Sets the Saturday time range of the provider schedule.
	 * 
	 * @param saturdayTimeRange Saturday time range
	 */
	void setSaturdayTimeRange(DateRange saturdayTimeRange);
	
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