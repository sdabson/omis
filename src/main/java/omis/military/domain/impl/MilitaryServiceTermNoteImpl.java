package omis.military.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.military.domain.MilitaryServiceTerm;
import omis.military.domain.MilitaryServiceTermNote;

/**
 * Military service term note implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (May 13, 2015)
 * @since OMIS 3.0
 */
public class MilitaryServiceTermNoteImpl implements MilitaryServiceTermNote {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String note;
	
	private Date date;
	
	private MilitaryServiceTerm serviceTerm;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	/**
	 * Instantiates a default instance of military service term note.
	 */
	public MilitaryServiceTermNoteImpl() {
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
	public MilitaryServiceTerm getServiceTerm() {
		return this.serviceTerm;
	}

	/** {@inheritDoc} */
	@Override
	public void setServiceTerm(final MilitaryServiceTerm serviceTerm) {
		this.serviceTerm = serviceTerm;
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
		if (!(o instanceof MilitaryServiceTermNote)) {
			return false;
		}
		
		MilitaryServiceTermNote that = (MilitaryServiceTermNote) o;
		
		if (this.getServiceTerm() == null) {
			throw new IllegalStateException("ServiceTerm required.");
		}
		if (!this.getServiceTerm().equals(that.getServiceTerm())) {
			return false;
		}
		if (this.getNote() == null) {
			throw new IllegalStateException("Name required");
		}
		if (!this.getNote().equals(that.getNote())) {
			return false;
		}
		if (this.getDate() == null) {
			throw new IllegalStateException("Name required");
		}
		if (!this.getDate().equals(that.getDate())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getServiceTerm() == null) {
			throw new IllegalStateException("ServiceTerm required.");
		}
		if (this.getNote() == null) {
			throw new IllegalStateException("Name required");
		}
		if (this.getDate() == null) {
			throw new IllegalStateException("Name required");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getServiceTerm().hashCode();
		hashCode = 29 * hashCode + this.getNote().hashCode();
		hashCode = 29 * hashCode + this.getDate().hashCode();
		
		return hashCode;
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return String.format(
				"Service term note: #%s, Date: %s,",
				this.getServiceTerm(),
				this.getDate());
	}
}