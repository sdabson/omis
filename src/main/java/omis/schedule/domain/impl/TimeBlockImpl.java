package omis.schedule.domain.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import omis.datatype.DateRange;
import omis.schedule.domain.TimeBlock;

/**
 * Implementation of time block.
 * 
 * @see TimeBlock
 * @author Stephen Abson
 * @version 0.1.0 (Nov 5, 2012)
 * @since OMIS 3.0
 */
public class TimeBlockImpl implements TimeBlock {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private DateRange dateRange;
	
	private String name;

	private Short sortOrder;
	
	private Boolean valid;

	/** Instantiates a default time block implementation. */
	public TimeBlockImpl() {
		// Instantiate default
	}
	
	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/** {@inheritDoc} */
	@Override
	public void setDateRange(final DateRange dateRange) {
		this.dateRange = dateRange;
	}

	/** {@inheritDoc} */
	@Override
	public DateRange getDateRange() {
		return this.dateRange;
	}

	/** {@inheritDoc} */
	@Override
	public void setName(final String name) {
		this.name = name;
	}

	/** {@inheritDoc} */
	@Override
	public String getName() {
		return this.name;
	}

	/** {@inheritDoc} */
	@Override
	public void setSortOrder(final Short sortOrder) {
		this.sortOrder = sortOrder;
	}

	/** {@inheritDoc} */
	@Override
	public Short getSortOrder() {
		return this.sortOrder;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setValid(final Boolean valid) {
		this.valid = valid;
	}
	
	/** {@inheritDoc} */
	@Override
	public Boolean getValid() {
		return this.valid;
	}
	
	/** {@inheritDoc} */
	@Override
	public DateRange createDateRangeForDate(final Date date) {
		return new DateRange(
				date.getTime() + this.getDateRange().getStartDate().getTime(),
				date.getTime() + this.getDateRange().getEndDate().getTime());
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof TimeBlock)) {
			return false;
		}
		TimeBlock that = (TimeBlock) obj;
		if (this.getDateRange() == null) {
			throw new IllegalStateException("Date range required");
		}
		if (!this.getDateRange().equals(that.getDateRange())) {
			return false;
		}
		if (this.getValid() == null) {
			throw new IllegalStateException("Valid flag required");
		}
		if (!this.getValid().equals(that.getValid())) {
			return false;
		}
		if (this.getName() == null) {
			throw new IllegalStateException("Name required");
		}
		
		// Property "name" is not mandatory but use it in natural key if set
		// as per the contract of the "TimeBlock" type
		if (this.getName() != null) {
			if (!this.getName().equals(that.getName())) {
				return false;
			}
		} else if (that.getName() != null) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getDateRange() == null) {
			throw new IllegalStateException("Date range required");
		}
		if (this.getValid() == null) {
			throw new IllegalStateException("Valid flag required");
		}
		int hashCode = 14;
		hashCode = hashCode * 29 + this.getDateRange().hashCode();
		hashCode = hashCode * 29 + this.getValid().hashCode();
		
		// Property "name" is not mandatory but use it in natural key if set
		// as per the contract of the "TimeBlock" type
		if (this.getName() != null) {
			hashCode = hashCode * 29 + this.getName().hashCode();
		}
		return hashCode;
	}
	
	/** {@inheritDoc} */
	@Override
	public String toString() {
		String dateRangeText;
		if (this.getDateRange() != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("K:mm a");
			if (this.getDateRange().getStartDate() != null) {
				dateRangeText = dateFormat.format(this.getDateRange()
						.getStartDate());
			} else {
				dateRangeText = "[start date not set]";
			}
			dateRangeText = " to ";
			if (this.getDateRange().getEndDate() != null) {
				dateRangeText = dateFormat.format(
						this.getDateRange().getEndDate());
			} else {
				dateRangeText = "[end date not set]";
			}
		} else {
			dateRangeText = "Not set";
		}
		if (this.getName() != null) {
			return String.format("%s (%s)%s", this.getName(), dateRangeText,
					this.getValid());
		} else {
			return dateRangeText + (this.getValid());
		}
	}
}