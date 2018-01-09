package omis.judge.domain.impl;

import omis.court.domain.Court;
import omis.datatype.DateRange;
import omis.judge.domain.JudgeCourtAssociation;
import omis.judge.domain.JudgeTerm;

/**
 * Judge court associaiton implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Oct 23, 2014)
 * @since OMIS 3.0
 */
public class JudgeCourtAssociationImpl implements JudgeCourtAssociation {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private JudgeTerm judgeTerm;
	
	private Court court;
	
	private DateRange dateRange;
	
	/**
	 * Instantiates a default instance of judge court association.
	 */
	public JudgeCourtAssociationImpl() {
		//Default constructor.
	}
	
	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return id;
	}

	/** {@inheritDoc} */
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public JudgeTerm getJudgeTerm() {
		return judgeTerm;
	}

	/** {@inheritDoc} */
	@Override
	public void setJudgeTerm(JudgeTerm judgeTerm) {
		this.judgeTerm = judgeTerm;
	}

	/** {@inheritDoc} */
	@Override
	public Court getCourt() {
		return court;
	}

	/** {@inheritDoc} */
	@Override
	public void setCourt(Court court) {
		this.court = court;
	}

	/** {@inheritDoc} */
	@Override
	public DateRange getDateRange() {
		return dateRange;
	}

	/** {@inheritDoc} */
	@Override
	public void setDateRange(DateRange dateRange) {
		this.dateRange = dateRange;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}	
		if (!(o instanceof JudgeCourtAssociation)) {
			return false;
		}
		
		JudgeCourtAssociation that = (JudgeCourtAssociation) o;
		
		if (this.getJudgeTerm() == null) {
			throw new IllegalStateException("JudgeTerm required.");
		}
		if (!this.getJudgeTerm().equals(that.getJudgeTerm())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getJudgeTerm() == null) {
			throw new IllegalStateException("JudgeTerm required.");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getJudgeTerm().hashCode();
		
		return hashCode;
	}
}