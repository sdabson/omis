package omis.substancetest.domain.impl;

import omis.substance.domain.Substance;
import omis.substancetest.domain.LabResult;
import omis.substancetest.domain.SubstanceTest;
import omis.substancetest.domain.SubstanceTestResultValue;

/**
 * Crime Lab Result Implementation. 
 * @author Joel Norris
 * @version 0.1.0 (Jan 2, 2014)
 * @since OMIS 3.0
 */
public class LabResultImpl implements LabResult {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Substance substance;
	
	private SubstanceTestResultValue value;
	
	private SubstanceTest substanceTest;
	
	/**
	 * Instantiates a default instance of crime lab result 
	 * implementation.
	 */
	public LabResultImpl() {
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
	public SubstanceTestResultValue getValue() {
		return this.value;
	}

	/** {@inheritDoc} */
	@Override
	public void setValue(final SubstanceTestResultValue value) {
		this.value = value;
	}

	/** {@inheritDoc} */
	@Override
	public SubstanceTest getSubstanceTest() {
		return this.substanceTest;
	}

	/** {@inheritDoc} */
	@Override
	public void setSubstanceTest(final SubstanceTest substanceTest) {
		this.substanceTest = substanceTest;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}	
		if (!(o instanceof LabResult)) {
			return false;
		}
		
		LabResult that = (LabResult) o;
		
		if (this.getSubstance() == null) {
			throw new IllegalStateException("Substance required.");
		}
		if (!this.getSubstance().equals(that.getSubstance())) {
			return false;
		}
		if (this.getSubstanceTest() == null) {
			throw new IllegalStateException("Substance test required.");
		}
		if (!this.getSubstanceTest().equals(that.getSubstanceTest())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getSubstance() == null) {
			throw new IllegalStateException("Substance required.");
		}
		if (this.getSubstanceTest() == null) {
			throw new IllegalStateException("Substance test required.");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getSubstance().hashCode();
		hashCode = 29 * hashCode + this.getSubstanceTest().hashCode();
		
		return hashCode;
	}
}