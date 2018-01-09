package omis.probationterm.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.courtcase.domain.CourtCase;
import omis.offender.domain.Offender;
import omis.probationterm.domain.ProbationTerm;
import omis.term.domain.component.Term;

/**
 * Probation term implementation.
 * 
 * @author Josh Divine
 * @author Stephen ABson
 * @version 0.1.1 (November 21, 2017)
 * @since OMIS 3.0
 */
public class ProbationTermImpl implements ProbationTerm {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Offender offender;
	
	private CourtCase courtCase;

	private Term term;
	
	private Date startDate;
	
	private Date terminationDate;
	
	private Date expirationDate;
	
	private Integer jailCredit;
	
	private Integer sentenceDays;

	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	/**
	 * Instantiates a default instance of probation term.
	 */
	public ProbationTermImpl() {
		//Default constructor.
	}
	
	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(
			final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setUpdateSignature(final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setOffender(final Offender offender) {
		this.offender = offender;
	}

	/** {@inheritDoc} */
	@Override
	public Offender getOffender() {
		return offender;
	}

	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return id;
	}

	/** {@inheritDoc} */
	@Override
	public void setCourtCase(final CourtCase courtCase) {
		this.courtCase = courtCase;
	}

	/** {@inheritDoc} */
	@Override
	public CourtCase getCourtCase() {
		return courtCase;
	}

	/** {@inheritDoc} */
	@Override
	public void setTerm(final Term term) {
		this.term = term;
	}

	/** {@inheritDoc} */
	@Override
	public Term getTerm() {
		return term;
	}

	/** {@inheritDoc} */
	@Override
	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	/** {@inheritDoc} */
	@Override
	public Date getStartDate() {
		return startDate;
	}

	/** {@inheritDoc} */
	@Override
	public void setTerminationDate(final Date terminationDate) {
		this.terminationDate = terminationDate;
	}
	
	/** {@inheritDoc} */
	public Date getTerminationDate() {
		return this.terminationDate;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setExpirationDate(final Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	/** {@inheritDoc} */
	@Override
	public Date getExpirationDate() {
		return expirationDate;
	}

	/** {@inheritDoc} */
	@Override
	public void setJailCredit(final Integer jailCredit) {
		this.jailCredit = jailCredit;
	}

	/** {@inheritDoc} */
	@Override
	public Integer getJailCredit() {
		return jailCredit;
	}

	/** {@inheritDoc} */
	@Override
	public void setSentenceDays(final Integer sentenceDays) {
		this.sentenceDays = sentenceDays;
	}

	/** {@inheritDoc} */
	@Override
	public Integer getSentenceDays() {
		return sentenceDays;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}	
		if (!(o instanceof ProbationTerm)) {
			return false;
		}
		
		ProbationTerm that = (ProbationTerm) o;
		
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required.");
		}
		if (!this.getOffender().equals(that.getOffender())) {
			return false;
		}
		
		if (this.getCourtCase() == null) {
			throw new IllegalStateException("Court case required.");
		}
		if (!this.getCourtCase().equals(that.getCourtCase())) {
			return false;
		}
		
		if (this.getStartDate() == null) {
			throw new IllegalStateException("Start date required.");
		}
		if (!this.getStartDate().equals(that.getStartDate())) {
			return false;
		}
		return true;
		
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required.");
		}
		if (this.getCourtCase() == null) {
			throw new IllegalStateException("Court case required.");
		}
		if (this.getStartDate() == null) {
			throw new IllegalStateException("Start date required.");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getOffender().hashCode();
		hashCode = 29 * hashCode + this.getCourtCase().hashCode();
		hashCode = 29 * hashCode + this.getStartDate().hashCode();
		return hashCode;
	}
}