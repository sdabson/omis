package omis.substancetest.domain.impl;

import omis.substancetest.domain.SubstanceTestResultValue;

/**
 * Substance Test Result Option Impl.
 * @author Joel Norris
 * @version 0.1.0 (Jul 2, 2013)
 * @since OMIS 3.0
 */
public class SubstanceTestResultValueImpl 
	implements SubstanceTestResultValue {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String name;
	
	/*This property is to help rank options so that when the test they 
	are used on is displayed in a list or profile screen, the most important 
	result can be seen for that particular test. */
	private Integer importance;
	
	private Boolean valid;

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
	public Integer getImportance() {
		return this.importance;
	}

	/** {@inheritDoc} */
	@Override
	public void setImportance(final Integer importance) {
		this.importance = importance;
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
		if (!(obj instanceof SubstanceTestResultValue)) {
			return false;
		}
		
		SubstanceTestResultValue that = (SubstanceTestResultValue) obj;
		
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