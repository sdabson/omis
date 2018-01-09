package omis.health.domain.impl;

import omis.health.domain.ProviderAssignment;
import omis.health.domain.ProviderSpecialty;
import omis.health.domain.ProviderSpecialtyAssociation;

/**
 * Provider specialty association implementation.
 * 
 * @author Sheronda Vaugn
 * @version 0.1.0 (Apr 25, 2014)
 * @since OMIS 3.0
 */
public class ProviderSpecialtyAssociationImpl 
		implements ProviderSpecialtyAssociation {
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private ProviderAssignment providerAssignment;
	private ProviderSpecialty specialty;
	
	/**
	 * Instantiates a default instance of provider specialty association
	 * implementation.
	 */
	public ProviderSpecialtyAssociationImpl() {
		// Default constructor
	}
	
	/** {@inheritDoc} */
	@Override
	public Long getId(){
		return this.id;
	}
	/** {@inheritDoc} */
	@Override
	public void setId(final Long id){
		this.id = id;
	}
	
	/** {@inheritDoc} */
	@Override
	public ProviderAssignment getProviderAssignment() {
		return this.providerAssignment;
	}
	/** {@inheritDoc} */
	@Override
	public void 
		setProviderAssignment(final ProviderAssignment providerAssignment){
		this.providerAssignment = providerAssignment;
	}
	
	/** {@inheritDoc} */
	@Override
	public ProviderSpecialty getSpecialty() {
		return this.specialty;
	}
	/** {@inheritDoc} */
	@Override
	public void setSpecialty(final ProviderSpecialty specialty){
		this.specialty = specialty;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object o) {
		if (this == o){
			return true;
		}
		if (!(o instanceof ProviderSpecialtyAssociation)){
			return false;
		}
		
		ProviderSpecialtyAssociation that = (ProviderSpecialtyAssociation) o;
		
		if (this.getProviderAssignment() == null) {
			throw new IllegalStateException("Provider Assignment required.");
		}
		if (!this.getProviderAssignment().equals(that.getProviderAssignment())){
			return false;
		}
		
		if (this.getSpecialty() == null) {
			throw new IllegalStateException("Provider Specialty requried.");
		}
		if (!this.getSpecialty().equals(that.getSpecialty())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc}*/
	@Override
	public int hashCode() {
		if (this.getProviderAssignment() == null){
			throw new IllegalStateException("Provider Assignment required.");
		}
		if (this.getSpecialty() == null){
			throw new IllegalStateException("Provider Specialty required.");
		}
		
		int hashcode =  14;
		
		hashcode = 29 * hashcode + this.getProviderAssignment().hashCode();
		hashcode = 29 * hashcode + this.getSpecialty().hashCode();
		
		return hashcode;
	}	
}
