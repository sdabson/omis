package omis.detainernotification.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.detainernotification.domain.Detainer;
import omis.detainernotification.domain.DetainerNote;

/**
 * Detainer note implementation.
 * 
 * @author Joel Norris
 * @version 05/16/2017
 * @since OMIS 3.0
 */
public class DetainerNoteImpl implements DetainerNote {
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private Date date;
	private String value;
	private Detainer detainer;
	private CreationSignature creationSignature;
	private UpdateSignature updateSignature;
	
	/**
	 * Instantiates a default instance of detainer note implementation.
	 */
	public DetainerNoteImpl() {
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
	public Date getDate() {
		return date;
	}

	/** {@inheritDoc} */
	@Override
	public void setDate(Date date) {
		this.date = date;
	}

	/** {@inheritDoc} */
	@Override
	public String getValue() {
		return value;
	}

	/** {@inheritDoc} */
	@Override
	public void setValue(String value) {
		this.value = value;
	}

	/** {@inheritDoc} */
	@Override
	public Detainer getDetainer() {
		return detainer;
	}

	/** {@inheritDoc} */
	@Override
	public void setDetainer(Detainer detainer) {
		this.detainer = detainer;
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
		if (!(o instanceof DetainerNote)) {
			return false;
		}
		
		DetainerNote that = (DetainerNote) o;
		
		if (this.getValue() == null) {
			throw new IllegalStateException("Value required.");
		}
		if (!this.getValue().equals(that.getValue())) {
			return false;
		}
		if (this.getDate() == null) {
			throw new IllegalStateException("Date required");
		}
		if (!this.getDate().equals(that.getDate())) {
			return false;
		}
		if (this.getDetainer() == null) {
			throw new IllegalStateException("Detainer required");
		}
		if (!this.getDetainer().equals(that.getDetainer())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getValue() == null) {
			throw new IllegalStateException("Value required.");
		}
		if (this.getDate() == null) {
			throw new IllegalStateException("Date required");
		}
		if (this.getDetainer() == null) {
			throw new IllegalStateException("Detainer required");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getValue().hashCode();
		hashCode = 29 * hashCode + this.getDate().hashCode();
		hashCode = 29 * hashCode + this.getDetainer().hashCode();
		
		return hashCode;
	}
}