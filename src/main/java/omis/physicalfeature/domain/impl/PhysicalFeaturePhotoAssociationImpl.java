package omis.physicalfeature.domain.impl;

import omis.personphoto.domain.impl.AbstractPersonPhotoAssociation;
import omis.physicalfeature.domain.PhysicalFeatureAssociation;
import omis.physicalfeature.domain.PhysicalFeaturePhotoAssociation;

/**
 * Physical feature photo implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Nov 21, 2013)
 * @since OMIS 3.0
 */
public class PhysicalFeaturePhotoAssociationImpl
	extends AbstractPersonPhotoAssociation
	implements PhysicalFeaturePhotoAssociation {

	private static final long serialVersionUID = 1L;
	
	private PhysicalFeatureAssociation physicalFeatureAssociation;
	
	/**
	 * Instantiates a default instance of physical feature photo implementation.
	 */
	public PhysicalFeaturePhotoAssociationImpl() {
		//Default constructor.
	}

	/** {@inheritDoc} */
	@Override
	public PhysicalFeatureAssociation getPhysicalFeatureAssociation() {
		return this.physicalFeatureAssociation;
	}

	/** {@inheritDoc} */
	@Override
	public void setPhysicalFeatureAssociation(
			final PhysicalFeatureAssociation physicalFeatureAssociation) {
		this.physicalFeatureAssociation = physicalFeatureAssociation;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}	
		if (!(o instanceof PhysicalFeaturePhotoAssociation)) {
			return false;
		}
		
		PhysicalFeaturePhotoAssociation that = 
				(PhysicalFeaturePhotoAssociation) o;
		
		if (this.getPerson() == null) {
			throw new IllegalStateException("Person required");
		}
		if (!this.getPerson().equals(that.getPerson())) {
			return false;
		}
		if (this.getPhoto() == null) {
			throw new IllegalStateException("Photo required.");
		}
		if (!this.getPhoto().equals(that.getPhoto())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getPhysicalFeatureAssociation() == null) {
			throw new IllegalStateException(" Physical feautre association" 
					+ " required.");
		}
		if (this.getPhoto() == null) {
			throw new IllegalStateException("Photo required.");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getPerson().hashCode();
		hashCode = 29 * hashCode + this.getPhoto().hashCode();
		
		return hashCode;
	}
}