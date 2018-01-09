package omis.adaaccommodation.domain.impl;

import omis.adaaccommodation.domain.Disability;
import omis.adaaccommodation.domain.DisabilityClassificationCategory;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.offender.domain.Offender;

/**
 * Implenentation of ADA disability.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 17, 2015)
 * @since OMIS 3.0
 */
public class DisabilityImpl 
	implements Disability {

	private static final long serialVersionUID = 1L;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	private Long id;
	
	private Offender offender;
	
	private String description;
	
	private DisabilityClassificationCategory disabilityClassification;
		
	/** Instantiates an implementation of ADA disability. */
	public DisabilityImpl() {
		// Default instantiation
	}

	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setUpdateSignature(final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/** {@inheritDoc} */
	@Override
	public void setOffender(final Offender offender) {
		this.offender = offender;
	}

	/** {@inheritDoc} */
	@Override
	public Offender getOffender() {
		return this.offender;
	}

	/** {@inheritDoc} */
	@Override
	public void setDescription(final String description) {
		this.description = description;
	}

	/** {@inheritDoc} */
	@Override
	public String getDescription() {
		return this.description;
	}

	/** {@inheritDoc} */
	@Override
	public void setDisabilityClassification(
			final DisabilityClassificationCategory disabilityClassification) {
		this.disabilityClassification = disabilityClassification;
	}

	/** {@inheritDoc} */
	@Override
	public DisabilityClassificationCategory getDisabilityClassification() {
		return this.disabilityClassification;
	}
}