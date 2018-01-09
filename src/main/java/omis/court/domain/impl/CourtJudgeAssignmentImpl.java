package omis.court.domain.impl;

import omis.court.domain.Court;
import omis.court.domain.CourtJudgeAssignment;
import omis.datatype.DateRange;
import omis.person.domain.Person;

/**
 * Implementation of assignment of judge to court.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jul 15, 2013)
 * @since OMIS 3.0
 */
public class CourtJudgeAssignmentImpl
		implements CourtJudgeAssignment {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;

	private Person judge;

	private Court court;

	private DateRange dateRange;

	/**
	 * Instantiates a default implementation of assignment of judge to court.
	 */
	public CourtJudgeAssignmentImpl() {
		// Default instantiation
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
	public void setJudge(final Person judge) {
		this.judge = judge;
	}

	/** {@inheritDoc} */
	@Override
	public Person getJudge() {
		return this.judge;
	}

	/** {@inheritDoc} */
	@Override
	public void setCourt(final Court court) {
		this.court = court;
	}

	/** {@inheritDoc} */
	@Override
	public Court getCourt() {
		return this.court;
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
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}
		CourtJudgeAssignment that = (CourtJudgeAssignment) obj;
		if (this.getJudge() == null) {
			throw new IllegalStateException("Judge required");
		}
		if (!this.getJudge().equals(that.getJudge())) {
			return false;
		}
		if (this.getCourt() == null) {
			throw new IllegalStateException("Court not found");
		}
		if (!this.getCourt().equals(that.getCourt())) {
			return false;
		}
		if (this.getDateRange() != null) {
			if (!this.getDateRange().equals(that.getDateRange())) {
				return false;
			}
		} else if (that.getDateRange() != null) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getJudge() == null) {
			throw new IllegalStateException("Judge required");
		}
		if (this.getCourt() == null) {
			throw new IllegalStateException("Court not found");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getJudge().hashCode();
		hashCode = 29 * hashCode + this.getCourt().hashCode();
		if (this.getDateRange() != null) {
			hashCode = 29 * hashCode + this.getDateRange().hashCode();
		}
		return hashCode;
	}
}