package omis.dna.domain.impl;

 import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.dna.domain.DnaSample;
import omis.offender.domain.Offender;
/**
 * Offender Dna Implementation.
 * @author Jason Nelson
 * @author Joel Norris
 * @author Stephen Abson
 * @author Sheronda Vaughn
 * @version 0.1.1 (February 23, 2015)
 * @since OMIS 3.0
 */
public class DnaSampleImpl implements DnaSample {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Offender offender;
	
	private String collectionEmployee;
	
	private Date date;
	
	private Date time;
	
	private String location;
	
	private String witness;
	
	private String comment;
	
	private UpdateSignature updateSignature;
	
	private CreationSignature creationSignature;
	
	/**
	 * Default Constructor For Offender DNA Samples.	 
	 */
	public DnaSampleImpl() {
		//do nothing
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
	public Offender getOffender() {
		return this.offender;
	}

	/** {@inheritDoc} */
	@Override
	public void setOffender(final Offender offender) {
		this.offender = offender;
	}

	/** {@inheritDoc} */
	@Override
	public String getCollectionEmployee() {
		return this.collectionEmployee;
	}

	/** {@inheritDoc} */
	@Override
	public void setCollectionEmployee(final String collectionEmployee) {
		this.collectionEmployee = collectionEmployee;
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
	public Date getTime() {
		return this.time;
	}

	/** {@inheritDoc} */
	@Override
	public void setTime(final Date time) {
		this.time = time;
	}
	
	/** {@inheritDoc} */
	@Override
	public String getLocation() {
		return this.location;
	}

	/** {@inheritDoc} */
	@Override
	public void setLocation(final String location) {
		this.location = location;
	}

	/** {@inheritDoc} */
	@Override
	public String getWitness() {
		return this.witness;
	}

	/** {@inheritDoc} */
	@Override
	public void setWitness(final String witness) {
		this.witness = witness;
	}

	/** {@inheritDoc} */
	@Override
	public String getComment() {
		return this.comment;
	}

	/** {@inheritDoc} */
	@Override
	public void setComment(final String comment) {
		this.comment = comment;
	}

	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setUpdateSignature(final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
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
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}	
		if (!(o instanceof DnaSample)) {
			return false;
		}
		
		DnaSample that = (DnaSample) o;
		
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required.");
		}
		if (!this.getOffender().equals(that.getOffender())) {
			return false;
		}
		if (this.getDate() == null) {
			throw new IllegalStateException("Date required");
		}
		if (!this.getDate().equals(that.getDate())) {
			return false;
		}
		if (this.getCollectionEmployee() == null) {
			throw new IllegalStateException("Collection employee required.");
		}
		if (!this.getCollectionEmployee().equals(that
				.getCollectionEmployee())) {
			return false;
		}
		if (this.getLocation() == null) {
			throw new IllegalStateException("Location required");
		}
		if (!this.getLocation().equals(that.getLocation())) {
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
		if (this.getDate() == null) {
			throw new IllegalStateException("Date required");
		}
		if (this.getCollectionEmployee() == null) {
			throw new IllegalStateException("Collection employee required.");
		}
		if (this.getLocation() == null) {
			throw new IllegalStateException("Location required");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getOffender().hashCode();
		hashCode = 29 * hashCode + this.getDate().hashCode();
		hashCode = 29 * hashCode + this.getCollectionEmployee().hashCode();
		hashCode = 29 * hashCode + this.getLocation().hashCode();
		return hashCode;
	}
}