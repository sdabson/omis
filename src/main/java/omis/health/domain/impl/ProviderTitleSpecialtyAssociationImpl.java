package omis.health.domain.impl;

import omis.health.domain.ProviderSpecialty;
import omis.health.domain.ProviderTitle;
import omis.health.domain.ProviderTitleSpecialtyAssociation;

/**
 * Provider title specialty association implementation.
 * 
 * @author Sheronda Vaughn
 * @version 0.1.0 (Apr 28, 2014)
 * @since OMIS 3.0
 */
public class ProviderTitleSpecialtyAssociationImpl 
		implements ProviderTitleSpecialtyAssociation {

	public static final long serialVersionUID = 1L;
	private Long id;
	private ProviderSpecialty specialty;
	private ProviderTitle title;
	
	/**
	 * Instantiates a default instance of the provider title specialty
	 * association implementation.
	 */
	public ProviderTitleSpecialtyAssociationImpl(){
		//Default constructor
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
	public ProviderSpecialty getSpecialty() {
		return this.specialty;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setSpecialty(final ProviderSpecialty specialty) {
		this.specialty = specialty;
	}
	 
	/** {@inheritDoc} */
	@Override
	public ProviderTitle getTitle() {
		return this.title;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setTitle(final ProviderTitle title) {
		this.title = title;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object o) {
		if (this == o){
			return true;
		}
		if(!(o instanceof ProviderTitleSpecialtyAssociation)) {
			return false;
		}
		
		ProviderTitleSpecialtyAssociation that = 
				(ProviderTitleSpecialtyAssociation) o;
		
		if (this.getSpecialty() == null) {
			throw new IllegalStateException("Provider Specialty required");
		}
		if (!this.getSpecialty().equals(that.getSpecialty())) {
			return false;
		}
		
		if (this.getTitle() == null) {
			throw new IllegalStateException("Provider Title required");
		}
		if(!this.getTitle().equals(that.getTitle())) {
			return false;
		}
		return true;
	}
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getSpecialty() == null) {
			throw new IllegalStateException("Provider Specialty required");
		}
		if (this.getTitle() == null) {
			throw new IllegalStateException("Provider Title required");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getSpecialty().hashCode();
		hashCode = 29 * hashCode + this.getTitle().hashCode();
		
		return hashCode;
	}
}


