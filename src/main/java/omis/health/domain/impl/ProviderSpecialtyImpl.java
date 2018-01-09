package omis.health.domain.impl;

import omis.health.domain.ProviderSpecialty;

/**
 * Provider specialty implementation.
 * 
 * @author Sheronda Vaugn
 * @version 0.1.0 (Apr 24, 2014)
 * @since OMIS 3.0
 */
public class ProviderSpecialtyImpl implements ProviderSpecialty {
	
	private static final long serialVersionUID = 1L; 
	
	private Long id;
	
	private String name; 
	
	private String abbreviation;
	
	private String description;
	
	private Short sortOrder;
	
	private Boolean valid;

	/**
	 * Instantiates a default instance of provider specialty.
	 */
	public ProviderSpecialtyImpl() {
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
	public String getAbbreviation() {
		return this.abbreviation;		
	}
	
	/** {@inheritDoc} */
	@Override
	public void setAbbreviation(final String abbreviation) {
		this.abbreviation = abbreviation;
	}
	
	/** {@inheritDoc} */
	@Override
	public String getDescription() {
		return this.description ;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setDescription(final String description) {
		this.description =  description;
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
		if (this == o){
			return true;
		}
		if (!(o instanceof ProviderSpecialty)){
			return false;
		}
	
		ProviderSpecialty that = (ProviderSpecialty) o;
		if (this.getName() == null) {
			throw new IllegalStateException("Name Required.");
		}
		if (this.getName().equals(that.getName())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getName() == null) {
			throw new IllegalStateException("Name Required.");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getName().hashCode();
		return hashCode;
	}
}