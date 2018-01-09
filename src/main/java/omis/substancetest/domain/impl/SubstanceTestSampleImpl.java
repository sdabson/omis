package omis.substancetest.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.offender.domain.Offender;
import omis.person.domain.Person;
import omis.substancetest.domain.SampleCollectionMethod;
import omis.substancetest.domain.SubstanceTestReason;
import omis.substancetest.domain.SubstanceTestSample;

/**
 * Substance test sample implementation.
 * 
 * @author Joel Norris
 * @version 0.1.1 (Apr 29, 2015)
 * @since OMIS 3.0
 */
public class SubstanceTestSampleImpl implements SubstanceTestSample {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Offender offender;
	
	private Date collectionDate;
	
	private Person collectionEmployee;
	
	private SampleCollectionMethod sampleCollectionMethod;
	
	private SubstanceTestReason substanceTestReason;
	
	private Boolean random;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	/**
	 * Instantiates a default instance of substance test sample implementation.
	 */
	public SubstanceTestSampleImpl() {
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
	public Date getCollectionDate() {
		return this.collectionDate;
	}

	/** {@inheritDoc} */
	@Override
	public void setCollectionDate(final Date collectionDate) {
		this.collectionDate = collectionDate;
	}

	/** {@inheritDoc} */
	@Override
	public Person getCollectionEmployee() {
		return this.collectionEmployee;
	}

	/** {@inheritDoc} */
	@Override
	public void setCollectionEmployee(final Person collectionEmployee) {
		this.collectionEmployee = collectionEmployee;
	}

	/** {@inheritDoc} */
	@Override
	public SampleCollectionMethod getSampleCollectionMethod() {
		return this.sampleCollectionMethod;
	}

	/** {@inheritDoc} */
	@Override
	public void setSampleCollectionMethod(
			final SampleCollectionMethod sampleCollectionMethod) {
		this.sampleCollectionMethod = sampleCollectionMethod;
	}

	/** {@inheritDoc} */
	@Override
	public SubstanceTestReason getSubstanceTestReason() {
		return this.substanceTestReason;
	}

	/** {@inheritDoc} */
	@Override
	public void setSubstanceTestReason(
			final SubstanceTestReason substanceTestReason) {
		this.substanceTestReason = substanceTestReason;
	}
	
	/** {@inheritDoc} */
	@Override
	public Boolean getRandom() {
		return this.random;
	}

	/** {@inheritDoc} */
	@Override
	public void setRandom(final Boolean random) {
		this.random = random;
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
		
		if (!(obj instanceof SubstanceTestSample)) {
			return false;
		}
		
		SubstanceTestSample that = (SubstanceTestSample) obj;
		
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required.");
		}
		if (!this.getOffender().equals(that.getOffender())) {
			return false;
		}
		if (this.getSampleCollectionMethod() == null) {
			throw new IllegalStateException(
					"Sample collection method required.");
		}
		if (!this.getSampleCollectionMethod().equals(
				that.getSampleCollectionMethod())) {
			return false;
		}
		if (this.getCollectionDate() == null) {
			throw new IllegalStateException("Collection date required.");
		}
		if (!this.getCollectionDate().equals(that.getCollectionDate())) {
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
		if (this.getSampleCollectionMethod() == null) {
			throw new IllegalStateException(
					"Sample collection method required.");
		}
		if (this.getCollectionDate() == null) {
			throw new IllegalStateException("Collection date required.");
		}
		int hashCode = 14;
		hashCode = hashCode * 29 + this.getOffender().hashCode();
		hashCode = hashCode * 29 + this.getSampleCollectionMethod().hashCode();
		hashCode = hashCode * 29 + this.getCollectionDate().hashCode();
		return hashCode;
	}
}