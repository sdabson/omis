package omis.substancetest.domain.impl;

import omis.substance.domain.Substance;
import omis.substancetest.domain.SubstanceTest;
import omis.substancetest.domain.SubstanceTestResult;
import omis.substancetest.domain.SubstanceTestResultValue;

/**
 * Substance Test Result Impl.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 4, 2013)
 * @since OMIS 3.0
 */
public class SubstanceTestResultImpl implements SubstanceTestResult {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;

	private Substance substance;
	
	private SubstanceTest substanceTest;
	
	private Boolean substanceUseAdmission;
	
	private Boolean admissionPriorToTest;
	
	private SubstanceTestResultValue value;
	
	/**
	 * Instantiates a default instance of substance test result
	 * implementation.
	 */
	public SubstanceTestResultImpl() {
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
	public Boolean getSubstanceUseAdmission() {
		return this.substanceUseAdmission;
	}

	/** {@inheritDoc} */
	@Override
	public void setSubstanceUseAdmission(
			final Boolean substanceUseAdmission) {
		this.substanceUseAdmission = substanceUseAdmission;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getAdmissionPriorToTest() {
		return this.admissionPriorToTest;
	}

	/** {@inheritDoc} */
	@Override
	public void setAdmissionPriorToTest(final Boolean admissionPriorToTest) {
		this.admissionPriorToTest = admissionPriorToTest;
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
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (!(obj instanceof SubstanceTestResult)) {
			return false;
		}
		
		SubstanceTestResult that = (SubstanceTestResult) obj;
		
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
		hashCode = hashCode * 29 + this.getSubstance().hashCode();
		hashCode = hashCode * 29 + this.getSubstanceTest().hashCode();
		return hashCode;
	}
}