package omis.hearing.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.hearing.domain.ImposedSanction;
import omis.hearing.domain.Infraction;
import omis.offender.domain.Offender;

/**
 * ImposedSanctionImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Apr 17, 2017)
 *@since OMIS 3.0
 *
 */
public class ImposedSanctionImpl implements ImposedSanction {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String description;
	
	private Offender offender;
	
	private Infraction infraction;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	/**{@inheritDoc} */
	@Override
	public void setCreationSignature(final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/**{@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}

	/**{@inheritDoc} */
	@Override
	public void setUpdateSignature(final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}

	/**{@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
	}

	/**{@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/**{@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/**{@inheritDoc} */
	@Override
	public String getDescription() {
		return this.description;
	}

	/**{@inheritDoc} */
	@Override
	public void setDescription(final String description) {
		this.description = description;
	}

	/**{@inheritDoc} */
	@Override
	public Offender getOffender() {
		return this.offender;
	}

	/**{@inheritDoc} */
	@Override
	public void setOffender(final Offender offender) {
		this.offender = offender;
	}

	/**{@inheritDoc} */
	@Override
	public Infraction getInfraction() {
		return this.infraction;
	}

	/**{@inheritDoc} */
	@Override
	public void setInfraction(final Infraction infraction) {
		this.infraction = infraction;
	}
	
	/**{@inheritDoc}*/
	@Override
	public boolean equals(final Object obj){
		if(this == obj){
			return true;
		}
		if(!(obj instanceof ImposedSanction)){
			return false;
		}
		
		ImposedSanction that = (ImposedSanction) obj;
		
		if(this.getInfraction() == null){
			throw new IllegalStateException("Infraction required.");
		}
		if(this.getDescription() == null){
			throw new IllegalStateException("Description required.");
		}
		if(this.getOffender() == null){
			throw new IllegalStateException("Offender required.");
		}
		
		if(!this.getInfraction().equals(that.getInfraction())){
			return false;
		}
		if(!this.getDescription().equals(that.getDescription())){
			return false;
		}
		
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if(this.getInfraction() == null){
			throw new IllegalStateException("Infraction required.");
		}
		if(this.getDescription() == null){
			throw new IllegalStateException("Description required.");
		}
		if(this.getOffender() == null){
			throw new IllegalStateException("Offender required.");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getInfraction().hashCode();
		hashCode = 29 * hashCode + this.getDescription().hashCode();
		
		return hashCode;
	}
	
}
