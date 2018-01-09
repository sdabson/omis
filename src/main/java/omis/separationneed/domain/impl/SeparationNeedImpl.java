/**
 * 
 */
package omis.separationneed.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.person.domain.Person;
import omis.relationship.domain.Relationship;
import omis.separationneed.domain.SeparationNeed;
import omis.separationneed.domain.SeparationNeedRemoval;

/**
 * Implementation of Separation Need.
 * 
 * @author Joel Norris 
 * @version 0.1.0 (Feb, 21 2013)
 * @since OMIS 3.0
 */
public class SeparationNeedImpl implements SeparationNeed {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Relationship relationship;
	
	private Date date;
	
	private Boolean confidential;
	
	private Person reportingStaff;
	
	private SeparationNeedRemoval removal;
	
	private String creationComment;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	
	/**Instantiates a default instance of separation need. */
	public SeparationNeedImpl() {
		//default constructor
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
	public Relationship getRelationship() {
		return this.relationship;
	}

	/** {@inheritDoc} */
	@Override
	public void setRelationship(final Relationship relationship) {
		this.relationship = relationship;
	}	

	/** {@inheritDoc} */
	@Override
	public Date getDate() {
		return this.date;
	}

	/** {@inheritDoc} */
	@Override
	public void setDate(final Date date) {
		this.date = date;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getConfidential() {
		return this.confidential;
	}

	/** {@inheritDoc} */
	@Override
	public void setConfidential(final Boolean confidential) {
		this.confidential = confidential;
	}

	/** {@inheritDoc} */
	@Override
	public Person getReportingStaff() {
		return this.reportingStaff;
	}

	/** {@inheritDoc} */
	@Override
	public void setReportingStaff(final Person reportingStaff) {
		this.reportingStaff = reportingStaff;
	}

	/** {@inheritDoc} */
	@Override
	public SeparationNeedRemoval getRemoval() {
		return this.removal;
	}

	/** {@inheritDoc} */
	@Override
	public void setRemoval(final SeparationNeedRemoval removal) {
		this.removal = removal;
	}

	/** {@inheritDoc} */
	@Override
	public String getCreationComment() {
		return this.creationComment;
	}

	/** {@inheritDoc} */
	@Override
	public void setCreationComment(final String creationComment) {
		this.creationComment = creationComment;
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
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SeparationNeed)) {
			return false;
		}
		
		SeparationNeed that = (SeparationNeed) obj;
		
		if (this.getRelationship() == null) {
			throw new IllegalStateException("Relationship required");
		}

		if (!this.getRelationship().equals(that.getRelationship())) {
			return false;
		}
				
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		int hashCode = 14;

		if (this.getRelationship() == null) {
			throw new IllegalStateException("Relationship required");
		}
		
		hashCode = 29 * hashCode + this.getRelationship().hashCode();
		
		return hashCode;
	}
}