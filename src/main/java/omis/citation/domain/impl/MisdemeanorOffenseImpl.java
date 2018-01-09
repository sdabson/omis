package omis.citation.domain.impl;

import omis.citation.domain.MisdemeanorOffense;

/**
 * Misdemeanor offense implementation.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (Aug 5, 2016)
 * @since OMIS 3.0
 */

public class MisdemeanorOffenseImpl implements MisdemeanorOffense {

	private static final long serialVersionUID = 1L; 
	
	private Long id;
	
	private Boolean valid;
	
	private String name;
	
	/**
	 * Instantiates a default instance of misdemeanor offense.
	 */
	public MisdemeanorOffenseImpl() {
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
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}	
		if (!(o instanceof MisdemeanorOffense)) {
			return false;
		}
		
		MisdemeanorOffense that = (MisdemeanorOffense) o;
		
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
			throw new IllegalStateException("Name required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getName().hashCode();
		return hashCode;
	}
	
	/** {@inheritDoc} */
	@Override
	public String toString() {
		return String.format(
				"Id: #%s, Name: %s, Valid: %s",
				this.getId(),
				this.getName(),
				this.getValid());
	}
	
}
