package omis.substancetest.domain.impl;

import omis.substance.domain.Substance;
import omis.substancetest.domain.SubstanceTestResultValue;
import omis.substancetest.domain.TestKit;
import omis.substancetest.domain.TestKitParameter;

/**
 * Test Kit Impl.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 11, 2013)
 * @since OMIS 3.0
 */
public class TestKitParameterImpl implements TestKitParameter {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Substance substance;
	
	private TestKit testKit;
	
	private SubstanceTestResultValue defaultResultValue;
	
	private Boolean valid;
	
	/**
	 * Instantiates a default instance of test kit parameter implementation.
	 */
	public TestKitParameterImpl() {
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
	public TestKit getTestKit() {
		return this.testKit;
	}

	/** {@inheritDoc} */
	@Override
	public void setTestKit(final TestKit testKit) {
		this.testKit = testKit;
	}

	/** {@inheritDoc} */
	@Override
	public SubstanceTestResultValue getDefaultResultValue() {
		return this.defaultResultValue;
	}

	/** {@inheritDoc} */
	@Override
	public void setDefaultResultValue(
			final SubstanceTestResultValue defaultResultValue) {
		this.defaultResultValue = defaultResultValue;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getValid() {
		return this.valid;
	}

	/** {@inheritDoc} */
	@Override
	public void setValid(final Boolean valid) {
		this.valid = valid;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (!(obj instanceof TestKitParameter)) {
			return false;
		}
		
		TestKitParameter that = (TestKitParameter) obj;
		
		if (this.getSubstance() == null) {
			throw new IllegalStateException("Substance required.");
		}
		if (!this.getSubstance().equals(that.getSubstance())) {
			return false;
		}
		if (this.getTestKit() == null) {
			throw new IllegalStateException("Test kit required.");
		}
		if (!this.getTestKit().equals(that.getTestKit())) {
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
		if (this.getTestKit() == null) {
			throw new IllegalStateException("Test kit required.");
		}
		
		int hashCode = 14;
		hashCode = hashCode * 29 + this.getSubstance().hashCode();
		hashCode = hashCode * 29 + this.getTestKit().hashCode();
		return hashCode;
	}
}