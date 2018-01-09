/**
 * 
 */
package omis.separationneed.domain.impl;

import omis.separationneed.domain.SeparationNeedReason;

/**
 * Implementation of SeparationNeedReason.
 * 
 * @author Joel Norris 
 * @version 0.1.0 (Feb, 21 2013)
 * @since OMIS 3.0
 */
public class SeparationNeedReasonImpl implements SeparationNeedReason {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String name;
	
	private Boolean valid;

	/**Instantiates an association. */
	public SeparationNeedReasonImpl() {
		//empty constructor
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
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SeparationNeedReason)) {
			return false;
		}
		
		SeparationNeedReason that = (SeparationNeedReason) obj;
		
		if (this.getName() == null) {
			throw new IllegalStateException("Name required");
		}

		if (!this.getName().equals(that.getName())) {
			return false;
		}	
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		int hashCode = 14;
		
		if (this.getName() == null) {
			throw new IllegalStateException("Name required");
		}
		
		hashCode = 29 * hashCode + this.getName().hashCode();
		
		return hashCode;
	}
}