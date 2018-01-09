package omis.health.domain.impl;

import omis.datatype.DateRange;
import omis.health.domain.ProviderAssignment;
import omis.health.domain.ProviderSchedule;

/**
 * Provider Schedule Implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Apr 7, 2014)
 * @since OMIS 3.0
 */
public class ProviderScheduleImpl implements ProviderSchedule {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private ProviderAssignment providerAssignment;
	
	private DateRange sundayTimeRange;
	
	private DateRange mondayTimeRange;
	
	private DateRange tuesdayTimeRange;
	
	private DateRange wednesdayTimeRange;
	
	private DateRange thursdayTimeRange;
	
	private DateRange fridayTimeRange;
	
	private DateRange saturdayTimeRange;
	
	/**
	 * Instantiates a default instance of provider schedule.
	 */
	public ProviderScheduleImpl() {
		//Default constructor.
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public ProviderAssignment getProviderAssignment() {
		return this.providerAssignment;
	}

	/** {@inheritDoc} */
	@Override
	public void setProviderAssignment(
			final ProviderAssignment providerAssignment) {
		this.providerAssignment = providerAssignment;
	}

	/** {@inheritDoc} */
	@Override
	public DateRange getSundayTimeRange() {
		return this.sundayTimeRange;
	}

	/** {@inheritDoc} */
	@Override
	public void setSundayTimeRange(final DateRange sundayTimeRange) {
		this.sundayTimeRange = sundayTimeRange;
	}

	/** {@inheritDoc} */
	@Override
	public DateRange getMondayTimeRange() {
		return this.mondayTimeRange;
	}

	/** {@inheritDoc} */
	@Override
	public void setMondayTimeRange(final DateRange mondayTimeRange) {
		this.mondayTimeRange = mondayTimeRange;
	}

	/** {@inheritDoc} */
	@Override
	public DateRange getTuesdayTimeRange() {
		return this.tuesdayTimeRange;
	}

	/** {@inheritDoc} */
	@Override
	public void setTuesdayTimeRange(final DateRange tuesdayTimeRange) {
		this.tuesdayTimeRange = tuesdayTimeRange;
	}

	/** {@inheritDoc} */
	@Override
	public DateRange getWednesdayTimeRange() {
		return this.wednesdayTimeRange;
	}

	/** {@inheritDoc} */
	@Override
	public void setWednesdayTimeRange(final DateRange wednesdayTimeRange) {
		this.wednesdayTimeRange = wednesdayTimeRange;
	}

	/** {@inheritDoc} */
	@Override
	public DateRange getThursdayTimeRange() {
		return this.thursdayTimeRange;
	}

	/** {@inheritDoc} */
	@Override
	public void setThursdayTimeRange(final DateRange thursdayTimeRange) {
		this.thursdayTimeRange = thursdayTimeRange;
	}

	/** {@inheritDoc} */
	@Override
	public DateRange getFridayTimeRange() {
		return this.fridayTimeRange;
	}

	/** {@inheritDoc} */
	@Override
	public void setFridayTimeRange(final DateRange fridayTimeRange) {
		this.fridayTimeRange = fridayTimeRange;
	}

	/** {@inheritDoc} */
	@Override
	public DateRange getSaturdayTimeRange() {
		return this.saturdayTimeRange;
	}

	/** {@inheritDoc} */
	@Override
	public void setSaturdayTimeRange(final DateRange saturdayTimeRange) {
		this.saturdayTimeRange = saturdayTimeRange;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}	
		if (!(o instanceof ProviderSchedule)) {
			return false;
		}
		
		ProviderSchedule that = (ProviderSchedule) o;
		
		if (this.getProviderAssignment() == null) {
			throw new IllegalStateException("Provider Assignment required.");
		}
		if (!this.getProviderAssignment().equals(
				that.getProviderAssignment())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getProviderAssignment() == null) {
			throw new IllegalStateException("ProviderAssignment required.");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getProviderAssignment().hashCode();
		
		return hashCode;
	}
}