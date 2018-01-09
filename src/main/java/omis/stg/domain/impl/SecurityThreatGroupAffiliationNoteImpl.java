package omis.stg.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.stg.domain.SecurityThreatGroupAffiliation;
import omis.stg.domain.SecurityThreatGroupAffiliationNote;

/**
 * Security threat group affiliation note implementation.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (October 31, 2016)
 * @since OMIS 3.0
 *
 */
public class SecurityThreatGroupAffiliationNoteImpl 
	implements SecurityThreatGroupAffiliationNote {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Date date;
	
	private String note;
	
	private SecurityThreatGroupAffiliation affiliation;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	/** Instantiates a default instance of security threat group affiliation 
	 * note.
	 */
	public SecurityThreatGroupAffiliationNoteImpl() {
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
	public String getNote() {
		return this.note;
	}

	/** {@inheritDoc} */
	@Override
	public void setNote(final String note) {
		this.note = note;
	}

	/** {@inheritDoc} */
	@Override
	public SecurityThreatGroupAffiliation getAffiliation() {
		return this.affiliation;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setAffiliation(final SecurityThreatGroupAffiliation affiliation) {
		this.affiliation = affiliation;
	}

	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(final CreationSignature creationSignature) {
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
		if (!(o instanceof SecurityThreatGroupAffiliationNote)) {
			return false;
		}
		
		SecurityThreatGroupAffiliationNote that = (
				SecurityThreatGroupAffiliationNote) o;
		
		if (this.getAffiliation() == null) {
			throw new IllegalStateException(
					"Security threat group affiliation required.");
		}
		if (!this.getAffiliation().equals(that.getAffiliation())) {
			return false;
		}
		if (this.getNote() == null) {
			throw new IllegalStateException("Note required");
		}
		if (!this.getNote().equals(that.getNote())) {
			return false;
		}
		if (this.getDate() == null) {
			throw new IllegalStateException("Date required");
		}
		if (!this.getDate().equals(that.getDate())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getAffiliation() == null) {
			throw new IllegalStateException(
					"Security threat group affiliation required.");
		}
		if (this.getNote() == null) {
			throw new IllegalStateException("Note required");
		}
		if (this.getDate() == null) {
			throw new IllegalStateException("Date required");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getAffiliation().hashCode();
		hashCode = 29 * hashCode + this.getNote().hashCode();
		hashCode = 29 * hashCode + this.getDate().hashCode();
		
		return hashCode;
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return String.format(
				"Security threat group affiliation note: #%s, Date: %s,",
				this.getNote(), this.getDate());
	}
}
