package omis.judge.domain.impl;

import omis.datatype.DateRange;
import omis.judge.domain.JudgeTerm;
import omis.person.domain.Person;

/**
 * Judge Term implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Oct 23, 2014)
 * @since OMIS 3.0
 */
public class JudgeTermImpl implements JudgeTerm {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Person judge;
	
	private DateRange dateRange;
	
	/**
	 * Instantiates a default instance of judge term.
	 */
	public JudgeTermImpl() {
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
	public Person getJudge() {
		return this.judge;
	}

	/** {@inheritDoc} */
	@Override
	public void setJudge(final Person judge) {
		this.judge = judge;
	}

	/** {@inheritDoc} */
	@Override
	public DateRange getDateRange() {
		return this.dateRange;
	}

	/** {@inheritDoc} */
	@Override
	public void setDateRange(final DateRange dateRange) {
		this.dateRange = dateRange;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}	
		if (!(o instanceof JudgeTerm)) {
			return false;
		}
		
		JudgeTerm that = (JudgeTerm) o;
		
		if (this.getJudge() == null) {
			throw new IllegalStateException("Judge required.");
		}
		if (!this.getJudge().equals(that.getJudge())) {
			return false;
		}
		
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getJudge() == null) {
			throw new IllegalStateException("Judge required.");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getJudge().hashCode();
		
		return hashCode;
	}
}