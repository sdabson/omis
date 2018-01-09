package omis.offense.domain.impl;

import omis.offense.domain.Offense;
import omis.offense.domain.OffenseClassification;

/**
 * Implementation of offense.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (May 17, 2013)
 * @since OMIS 3.0
 */
public class OffenseImpl
		implements Offense {

	private static final long serialVersionUID = 1L;
	
	private Long id;

	private String violationCode;

	private String name;
	
	private OffenseClassification classification;
	
	private String shortName;
	
	private String url;
	
	private Boolean valid;

	/** Instantiates a default implementation of offense. */
	public OffenseImpl() {
		// Default instantiation
	}

	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/** {@inheritDoc} */
	@Override
	public void setViolationCode(final String violationCode) {
		this.violationCode = violationCode;
	}

	/** {@inheritDoc} */
	@Override
	public String getViolationCode() {
		return this.violationCode;
	}

	/** {@inheritDoc} */
	@Override
	public void setName(final String name) {
		this.name = name;
	}

	/** {@inheritDoc} */
	@Override
	public String getName() {
		return this.name;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setClassification(final OffenseClassification classification) {
		this.classification = classification;
	}
	
	/** {@inheritDoc} */
	@Override
	public OffenseClassification getClassification() {
		return this.classification;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	/** {@inheritDoc} */
	@Override
	public String getShortName() {
		return this.shortName;
	}

	/** {@inheritDoc} */
	@Override
	public void setUrl(String url) {
		this.url = url;
	}

	/** {@inheritDoc} */
	@Override
	public String getUrl() {
		return this.url;
	}

	/** {@inheritDoc} */
	@Override
	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getValid() {
		return this.valid;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof Offense)) {
			return false;
		}
		Offense that = (Offense) obj;
		if (this.getViolationCode() == null) {
			throw new IllegalStateException("Violation code required");
		}
		if (!this.getViolationCode().equals(that.getViolationCode())) {
			return false;
		}
		if (this.getName() == null) {
			throw new IllegalStateException("Name required");
		}
		if (!this.getName().equals(that.getName())) {
			return false;
		}
		if (this.getShortName() == null) {
			throw new IllegalStateException("Short name required");
		}
		if (!this.getShortName().equals(that.getShortName())) {
			return false;
		}
		if (this.getValid() == null) {
			throw new IllegalStateException("Valid required");
		}
		if (!this.getValid().equals(that.getValid())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getViolationCode() == null) {
			throw new IllegalStateException("Violation code required");
		}
		if (this.getName() == null) {
			throw new IllegalStateException("Name required");
		}
		if (this.getShortName() == null) {
			throw new IllegalStateException("Short name required");
		}
		if (this.getValid() == null) {
			throw new IllegalStateException("Valid required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getViolationCode().hashCode();
		hashCode = 29 * hashCode + this.getName().hashCode();
		hashCode = 29 * hashCode + this.getShortName().hashCode();
		hashCode = 29 * hashCode + this.getValid().hashCode();
		return hashCode;
	}
}