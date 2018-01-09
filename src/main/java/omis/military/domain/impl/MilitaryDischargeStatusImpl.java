package omis.military.domain.impl;

import omis.military.domain.MilitaryDischargeStatus;

/**
 * Military discharge status implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (May 13, 2015)
 * @since OMIS 3.0
 */
public class MilitaryDischargeStatusImpl implements MilitaryDischargeStatus {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Boolean valid;
	
	private String name;
	
	/**
	 * Instantiates a default instance of military discharge status.
	 */
	public MilitaryDischargeStatusImpl() {
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
		if (!(o instanceof MilitaryDischargeStatus)) {
			return false;
		}
		
		MilitaryDischargeStatus that = (MilitaryDischargeStatus) o;
		
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
	public String toString() {
		return String.format(
				"Id: #%s, Name: %s, Valid: %s",
				this.getId(),
				this.getName(),
				this.getValid());
	}
}