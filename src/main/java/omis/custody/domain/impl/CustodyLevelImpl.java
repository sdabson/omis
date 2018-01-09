package omis.custody.domain.impl;

import omis.custody.domain.CustodyLevel;

/**
 * Custody Level Implementation.
 * 
 * @author Joel Norris 
 * @version 0.1.0 (Mar, 07 2013)
 * @since OMIS 3.0
 */
public class CustodyLevelImpl implements CustodyLevel {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String name;
	
	private Boolean valid;
	
	/** Instantiates a default instance of custody level implementation. */
	public CustodyLevelImpl() {
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
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		
		if (!(o instanceof CustodyLevel)) {
			return false;
		}
		
		CustodyLevel that = (CustodyLevel) o;
		
		if (this.getName() == null) {
			throw new IllegalStateException("Branch Name required");
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
			throw new IllegalStateException("Branch Name required");
		}

		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getName().hashCode();
		
		return hashCode;
	}
}