/**
 * DetainerAgencyImpl
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jul 8, 2016)
 *@since OMIS 3.0
 *
 */
package omis.detainernotification.domain.impl;

import omis.address.domain.Address;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.detainernotification.domain.DetainerAgency;


public class DetainerAgencyImpl implements DetainerAgency {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String agencyName;
	
	private Boolean valid;
	
	private String telephoneNumber;
	
	private Address address;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	
	
	/* Constructors */
	
	public DetainerAgencyImpl(){
		//Nothing
	}
	
	
	
	/* Getters and Setters */
	
	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;

	}

	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setUpdateSignature(final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;

	}

	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/** {@inheritDoc} */
	@Override
	public String getAgencyName() {
		return this.agencyName;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean isValid() {
		return this.valid;
	}

	/** {@inheritDoc} */
	@Override
	public String getTelephoneNumber() {
		return this.telephoneNumber;
	}

	/** {@inheritDoc} */
	@Override
	public Address getAddress() {
		return this.address;
	}

	/** {@inheritDoc} */
	@Override
	public void setId(Long id) {
		this.id = id;

	}

	/** {@inheritDoc} */
	@Override
	public void setAgencyName(final String agencyName) {
		this.agencyName = agencyName;

	}

	/** {@inheritDoc} */
	@Override
	public void setValid(boolean valid) {
		this.valid = valid;

	}

	/** {@inheritDoc} */
	@Override
	public void setTelephoneNumber(final String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;

	}

	/** {@inheritDoc} */
	@Override
	public void setAddress(final Address address) {
		this.address = address;

	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if(this == obj){
			return true;
		}
		if(!(obj instanceof DetainerAgency)){
			return false;
		}
		
		DetainerAgency that = (DetainerAgency) obj;
		
		if (this.getAgencyName() == null) {
			throw new IllegalStateException("Agency name required.");
		}
		if (this.isValid() == null) {
			throw new IllegalStateException("Validity required.");
		}

		if (!this.getAgencyName().equals(that.getAgencyName())) {
			return false;
		}
		
		return true;
	}

	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getAgencyName() == null) {
			throw new IllegalStateException("Agency name required.");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getAgencyName().hashCode();
	
		return hashCode;
	}

}
