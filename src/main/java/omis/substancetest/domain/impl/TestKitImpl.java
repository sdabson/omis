package omis.substancetest.domain.impl;

import omis.substancetest.domain.SampleCollectionMethod;
import omis.substancetest.domain.TestKit;

/**
 * Test Kit Impl.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 11, 2013)
 * @since OMIS 3.0
 */
public class TestKitImpl implements TestKit {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String name;
	
	private SampleCollectionMethod sampleCollectionMethod;
	
	private Boolean valid;
	
	/**
	 * Instantiates a default instance of test kit implementation.
	 */
	public TestKitImpl() {
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
	public String getName() {
		return this.name;
	}

	/** {@inheritDoc} */
	@Override
	public void setName(final String name) {
		this.name = name;
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
		
		if (!(obj instanceof TestKit)) {
			return false;
		}
		
		TestKit that = (TestKit) obj;
		
		if (this.getName() == null) {
			throw new IllegalStateException("Name required.");
		}
		if (!this.getName().equals(that.getName())) {
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
		
		return true;
		
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getName() == null) {
			throw new IllegalStateException("Name required.");
		}
		if (this.getSampleCollectionMethod() == null) {
			throw new IllegalStateException(
					"Sample collection method required.");
		}
		
		int hashCode = 14;
		hashCode = hashCode * 29 + this.getName().hashCode();
		hashCode = hashCode * 29 + this.getSampleCollectionMethod().hashCode();
		return hashCode;
	}
}