package omis.military.domain.impl;

import omis.military.domain.MilitaryBranch;

/**
 * Military branch implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (May 13, 2015)
 * @since OMIS 3.0
 */
public class MilitaryBranchImpl implements MilitaryBranch {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String name;
	
	private String shortName;
	
	private Boolean valid;
	
	/**
	 * Instantiates a default instance of military branch.
	 */
	public MilitaryBranchImpl() {
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
	public String getShortName() {
		return this.shortName;
	}

	/** {@inheritDoc} */
	@Override
	public void setShortName(final String shortName) {
		this.shortName = shortName;
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
		if (!(o instanceof MilitaryBranch)) {
			return false;
		}
		
		MilitaryBranch that = (MilitaryBranch) o;
		
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
		
		hashCode = 29 * hashCode + this.getName().hashCode();
		
		return hashCode;
	}
	
	/** {@inheritDoc} */
	@Override
	public String toString() {
		return String.format(
				"Name: %s", 
				this.getName());
	}
}