package omis.substanceuse.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.offender.domain.Offender;
import omis.substance.domain.Substance;
import omis.substanceuse.domain.SubstanceUse;

/**
 * Substance use implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Nov 16, 2016)
 * @since OMIS 3.0
 */
public class SubstanceUseImpl implements SubstanceUse{

	private static final long serialVersionUID = 1L;
	private Long id;
	private Offender offender;
	private Substance substance;
	private CreationSignature creationSignature;
	private UpdateSignature updateSignature;

	/**
	 * Instantiates a default instance of substance use.
	 */
	public SubstanceUseImpl() {
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
	public Substance getSubstance() {
		return this.substance;
	}

	/** {@inheritDoc} */
	@Override
	public void setSubstance(final Substance substance) {
		this.substance = substance;
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
		if (!(o instanceof SubstanceUse)) {
			return false;
		}
		
		SubstanceUse that = (SubstanceUse) o;
		
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required.");
		}
		if (!this.getOffender().equals(that.getOffender())) {
			return false;
		}
		if (this.getSubstance() == null) {
			throw new IllegalStateException("Substance required");
		}
		if (!this.getSubstance().equals(that.getSubstance())) {
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
		if (this.getSubstance() == null) {
			throw new IllegalStateException("Substance required");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getOffender().hashCode();
		hashCode = 29 * hashCode + this.getSubstance().hashCode();
		
		return hashCode;
	}
}