package omis.stg.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.stg.domain.SecurityThreatGroupActivity;
import omis.stg.domain.SecurityThreatGroupActivityNote;

/**
 * Security threat group activity note implementation.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (Nov 29, 2016)
 * @since OMIS 3.0
 */

public class SecurityThreatGroupActivityNoteImpl
		implements SecurityThreatGroupActivityNote {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private SecurityThreatGroupActivity activity;
	
	private Date date;
	
	private String value;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	/**
	 * Instantiates an implementation of security threat group activity level.
	 */
	public SecurityThreatGroupActivityNoteImpl() {
		// Default instantiation
	}
	
	/** {@inheritDoc} */
	@Override
	public void setId(Long id) {
		this.id = id;	
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/** {@inheritDoc} */
	@Override
	public void setActivity(SecurityThreatGroupActivity activity) {
		this.activity = activity;
	}

	/** {@inheritDoc} */
	@Override
	public SecurityThreatGroupActivity getActivity() {
		return activity;
	}

	/** {@inheritDoc} */
	@Override
	public void setDate(Date date) {
		this.date = date;
	}

	/** {@inheritDoc} */
	@Override
	public Date getDate() {
		return this.date;
	}

	/** {@inheritDoc} */
	@Override
	public void setValue(String value) {
		this.value = value;
	}

	/** {@inheritDoc} */
	@Override
	public String getValue() {
		return this.value;
	}
	
	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setUpdateSignature(UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}	
		if (!(o instanceof SecurityThreatGroupActivityNote)) {
			return false;
		}
		
		SecurityThreatGroupActivityNote that = (
				SecurityThreatGroupActivityNote) o;
		
		if (this.getActivity() == null) {
			throw new IllegalStateException("Activity required.");
		}
		if (!this.getActivity().equals(that.getActivity())) {
			return false;
		}
		if (this.getDate() == null) {
			throw new IllegalStateException("Date required");
		}
		if (!this.getDate().equals(that.getDate())) {
			return false;
		}
		if (this.getValue() == null) {
			throw new IllegalStateException("Value required");
		}
		if (!this.getValue().equals(that.getValue())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getActivity() == null) {
			throw new IllegalStateException(
					"SecurityThreatGroupActivity required.");
		}
		if (this.getDate() == null) {
			throw new IllegalStateException("Date required");
		}
		if (this.getValue() == null) {
			throw new IllegalStateException("Value required");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getActivity().hashCode();
		hashCode = 29 * hashCode + this.getDate().hashCode();
		hashCode = 29 * hashCode + this.getValue().hashCode();
		
		return hashCode;
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return String.format(
				"Activity: %s, Date: %s, Value: %s, ",
				this.getActivity(),
				this.getDate(),
				this.getValue());
	}
	
}
