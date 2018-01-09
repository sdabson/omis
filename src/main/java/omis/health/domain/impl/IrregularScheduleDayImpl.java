package omis.health.domain.impl;

import java.util.Date;

import omis.datatype.DateRange;
import omis.health.domain.IrregularScheduleDay;
import omis.health.domain.ProviderAssignment;

/**
 * Irregular Schedule Day Implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Apr 7, 2014)
 * @since OMIS 3.0
 */
public class IrregularScheduleDayImpl implements IrregularScheduleDay {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private DateRange timeRange;
	
	private Date day;
	
	private ProviderAssignment providerAssignment;
	
	/**
	 * Instantiates a default instance of irregular schedule day.
	 */
	public IrregularScheduleDayImpl() {
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
	public DateRange getTimeRange() {
		return this.timeRange;
	}

	/** {@inheritDoc} */
	@Override
	public void setTimeRange(final DateRange timeRange) {
		this.timeRange = timeRange;
	}

	/** {@inheritDoc} */
	@Override
	public Date getDay() {
		return this.day;
	}

	/** {@inheritDoc} */
	@Override
	public void setDay(final Date day) {
		this.day = day;
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
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}	
		if (!(o instanceof IrregularScheduleDay)) {
			return false;
		}
		
		IrregularScheduleDay that = (IrregularScheduleDay) o;
		
		if (this.getDay() == null) {
			throw new IllegalStateException("Day required.");
		}
		if (!this.getDay().equals(that.getDay())) {
			return false;
		}
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
		if (this.getDay() == null) {
			throw new IllegalStateException("Day required.");
		}
		if (this.getProviderAssignment() == null) {
			throw new IllegalStateException("Provider Assignment required.");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getDay().hashCode();
		hashCode = 29 * hashCode + this.getProviderAssignment().hashCode();
		
		return hashCode;
	}
}