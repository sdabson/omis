package omis.warrant.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.facility.domain.Facility;
import omis.region.domain.County;
import omis.warrant.domain.Warrant;
import omis.warrant.domain.WarrantRelease;
import omis.warrant.domain.component.ClearSignature;

/**
 * WarrantRelease.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 8, 2017)
 *@since OMIS 3.0
 *
 */
public class WarrantReleaseImpl implements WarrantRelease {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Warrant warrant;
	
	private String instructions;
	
	private County county;
	
	private Facility facility;
	
	private Date releaseTimestamp;
	
	private String addressee;
	
	private ClearSignature clearSignature;
	
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
	public Warrant getWarrant() {
		return this.warrant;
	}

	/**{@inheritDoc} */
	@Override
	public void setWarrant(final Warrant warrant) {
		this.warrant = warrant;
	}

	/**{@inheritDoc} */
	@Override
	public String getInstructions() {
		return this.instructions;
	}

	/**{@inheritDoc} */
	@Override
	public void setInstructions(final String instructions) {
		this.instructions = instructions;
	}

	/**{@inheritDoc} */
	@Override
	public County getCounty() {
		return this.county;
	}

	/**{@inheritDoc} */
	@Override
	public void setCounty(final County county) {
		this.county = county;
	}

	/**{@inheritDoc} */
	@Override
	public Facility getFacility() {
		return this.facility;
	}

	/**{@inheritDoc} */
	@Override
	public void setFacility(final Facility facility) {
		this.facility = facility;
	}

	/**{@inheritDoc} */
	@Override
	public Date getReleaseTimestamp() {
		return this.releaseTimestamp;
	}

	/**{@inheritDoc} */
	@Override
	public void setReleaseTimestamp(final Date releaseTimestamp) {
		this.releaseTimestamp = releaseTimestamp;
	}

	/**{@inheritDoc} */
	@Override
	public String getAddressee() {
		return this.addressee;
	}

	/**{@inheritDoc} */
	@Override
	public void setAddressee(final String addressee) {
		this.addressee = addressee;
	}

	/**{@inheritDoc} */
	@Override
	public ClearSignature getClearSignature() {
		return this.clearSignature;
	}

	/**{@inheritDoc} */
	@Override
	public void setClearSignature(final ClearSignature clearSignature) {
		this.clearSignature = clearSignature;
	}
	
	/**{@inheritDoc}*/
	@Override
	public boolean equals(final Object obj){
		if(this == obj){
			return true;
		}
		if(!(obj instanceof WarrantRelease)){
			return false;
		}
		
		WarrantRelease that = (WarrantRelease) obj;
		
		if(this.getWarrant() == null){
			throw new IllegalStateException("Warrant required.");
		}
		if(this.getCounty() == null){
			throw new IllegalStateException("Country required.");
		}
		if(this.getFacility() == null){
			throw new IllegalStateException("Facility required.");
		}
		if(this.getReleaseTimestamp() == null){
			throw new IllegalStateException("ReleaseTimestamp required.");
		}
		if(this.getAddressee() == null){
			throw new IllegalStateException("Addressee required.");
		}
		if(this.getClearSignature().getPerson() == null){
			throw new IllegalStateException("ClearSignature Person required.");
		}
		if(this.getClearSignature().getDate() == null){
			throw new IllegalStateException("ClearSignature Date required.");
		}
		
		if(!this.getWarrant().equals(that.getWarrant())){
			return false;
		}
		
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if(this.getWarrant() == null){
			throw new IllegalStateException("Warrant required.");
		}
		if(this.getCounty() == null){
			throw new IllegalStateException("Country required.");
		}
		if(this.getFacility() == null){
			throw new IllegalStateException("Facility required.");
		}
		if(this.getReleaseTimestamp() == null){
			throw new IllegalStateException("ReleaseTimestamp required.");
		}
		if(this.getAddressee() == null){
			throw new IllegalStateException("Addressee required.");
		}
		if(this.getClearSignature().getPerson() == null){
			throw new IllegalStateException("ClearSignature Person required.");
		}
		if(this.getClearSignature().getDate() == null){
			throw new IllegalStateException("ClearSignature Date required.");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getWarrant().hashCode();
		hashCode = 29 * hashCode + this.getReleaseTimestamp().hashCode();
		hashCode = 29 * hashCode + this.getClearSignature().getPerson().hashCode();
		hashCode = 29 * hashCode + this.getClearSignature().getDate().hashCode();
		
		return hashCode;
	}
	
}
