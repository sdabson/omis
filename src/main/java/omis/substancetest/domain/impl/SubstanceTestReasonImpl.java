package omis.substancetest.domain.impl;

import omis.substancetest.domain.SubstanceTestReason;

/**
 * Substance Test Reason Impl.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 4, 2013)
 * @since OMIS 3.0
 */
public class SubstanceTestReasonImpl implements SubstanceTestReason {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String name;
	
	private Boolean valid;
	
	private Short sortOrder;
	
	/**
	 * Instantiates a default instance of substance test reason implementation. 
	 */
	public SubstanceTestReasonImpl() {
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
	public Short getSortOrder() {
		return this.sortOrder;
	}

	/** {@inheritDoc} */
	@Override
	public void setSortOrder(final Short sortOrder) {
		this.sortOrder = sortOrder;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (!(obj instanceof SubstanceTestReason)) {
			return false;
		}
		
		SubstanceTestReason that = (SubstanceTestReason) obj;
		
		if (this.getName() == null) {
			throw new IllegalStateException("Name required.");
		}
		if (!this.getName().equals(that.getName())) {
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
		int hashCode = 14;
		hashCode = hashCode * 29 + this.getName().hashCode();
		return hashCode;
	}
}