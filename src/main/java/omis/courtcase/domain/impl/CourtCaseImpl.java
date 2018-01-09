package omis.courtcase.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.courtcase.domain.CourtCase;
import omis.courtcase.domain.JurisdictionAuthority;
import omis.courtcase.domain.OffenderDangerDesignator;
import omis.courtcase.domain.component.CourtCaseFlags;
import omis.courtcase.domain.component.CourtCasePersonnel;
import omis.docket.domain.Docket;
import omis.region.domain.State;

/**
 * Implementation of court case.
 * 
 * @author Joel Norris
 * @author Josh Divine
 * @version 0.1.1 (May 9, 2017)
 * @since OMIS 3.0
 */
public class CourtCaseImpl implements CourtCase {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Docket docket;
	
	private Date pronouncementDate;
	
	private Date sentenceReviewDate;
	
	private CourtCasePersonnel personnel;
	
	private CourtCaseFlags flags;
	
	private String comments;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;

	private String interStateNumber;
	
	private State interState;
	
	private JurisdictionAuthority jurisdictionAuthority;
	
	private OffenderDangerDesignator dangerDesignator;
	
	/**
	 * Instantiates a default instance of court case.
	 */
	public CourtCaseImpl() {
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
	public Docket getDocket() {
		return this.docket;
	}

	/** {@inheritDoc} */
	@Override
	public void setDocket(final Docket docket) {
		this.docket = docket;
	}

	/** {@inheritDoc} */
	@Override
	public Date getPronouncementDate() {
		return this.pronouncementDate;
	}

	/** {@inheritDoc} */
	@Override
	public void setPronouncementDate(final Date pronouncementDate) {
		this.pronouncementDate = pronouncementDate;
	}

	/** {@inheritDoc} */
	@Override
	public Date getSentenceReviewDate() {
		return this.sentenceReviewDate;
	}

	/** {@inheritDoc} */
	@Override
	public void setSentenceReviewDate(final Date sentenceReviewDate) {
		this.sentenceReviewDate = sentenceReviewDate;
	}
	
	/** {@inheritDoc} */
	@Override
	public CourtCasePersonnel getPersonnel() {
		return this.personnel;
	}

	/** {@inheritDoc} */
	@Override
	public void setPersonnel(
			final CourtCasePersonnel courtCasePersonnel) {
		this.personnel = courtCasePersonnel;
	}

	/** {@inheritDoc} */
	@Override
	public CourtCaseFlags getFlags() {
		return this.flags;
	}

	/** {@inheritDoc} */
	@Override
	public void setFlags(final CourtCaseFlags courtCaseFlags) {
		this.flags = courtCaseFlags;
	}
	

	/** {@inheritDoc} */
	@Override
	public String getComments() {
		return this.comments;
	}

	/** {@inheritDoc} */
	@Override
	public void setComments(final String comments) {
		this.comments = comments;
	}

	/** {@inheritDoc} */
	@Override
	public String getInterStateNumber() {
		return this.interStateNumber;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setInterStateNumber(final String interStateNumber) {
		this.interStateNumber = interStateNumber;
	}
	
	/** {@inheritDoc} */
	@Override
	public State getInterState() {
		return this.interState;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setInterState(final State interState) {
		this.interState = interState;
	}
	
	/** {@inheritDoc} */
	@Override
	public JurisdictionAuthority getJurisdictionAuthority() {
		return this.jurisdictionAuthority;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setJurisdictionAuthority(
			final JurisdictionAuthority jurisdictionAuthority) {
		this.jurisdictionAuthority = jurisdictionAuthority;
	}
	
	/** {@inheritDoc} */
	@Override
	public OffenderDangerDesignator getDangerDesignator() {
		return this.dangerDesignator;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setDangerDesignator(
			final OffenderDangerDesignator dangerDesignator) {
		this.dangerDesignator = dangerDesignator;
	}
	
	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(
			final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setUpdateSignature(final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}	
		if (!(o instanceof CourtCase)) {
			return false;
		}
		
		CourtCase that = (CourtCase) o;
		
		if (this.getDocket() == null) {
			throw new IllegalStateException("Docket required.");
		}
		if (!this.getDocket().equals(that.getDocket())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getDocket() == null) {
			throw new IllegalStateException("Docket required.");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getDocket().hashCode();
		
		return hashCode;
	}
}