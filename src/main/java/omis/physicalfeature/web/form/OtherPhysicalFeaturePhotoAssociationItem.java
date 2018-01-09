package omis.physicalfeature.web.form;

import omis.physicalfeature.domain.PhysicalFeaturePhotoAssociation;
import omis.physicalfeature.domain.ProcessStatus;

/**
 * Other physical feature photo association item.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Feb 26, 2014)
 * @since OMIS 3.0
 */
public class OtherPhysicalFeaturePhotoAssociationItem {

	private PhysicalFeaturePhotoAssociation physicalFeaturePhotoAssociation;
	
	private ProcessStatus status;

	/**
	 * Returns the physical feature photo association of the other physical 
	 * feature photo association item.
	 * @return physical feature photo association
	 */
	public PhysicalFeaturePhotoAssociation 
	getPhysicalFeaturePhotoAssociation() {
		return this.physicalFeaturePhotoAssociation;
	}

	/**
	 * Sets the physical feature photo association of the other physical
	 * feature photo association item.
	 * @param physicalFeaturePhotoAssociation physical feature photo association
	 */
	public void setPhysicalFeaturePhotoAssociation(
			final PhysicalFeaturePhotoAssociation 
			physicalFeaturePhotoAssociation) {
		this.physicalFeaturePhotoAssociation = physicalFeaturePhotoAssociation;
	}

	/**
	 * Returns the process status of the other physical feature photo 
	 * association item.
	 * @return process status
	 */
	public ProcessStatus getStatus() {
		return this.status;
	}

	/**
	 * Sets the process status of the other physical feature photo item.
	 * @param status process status
	 */
	public void setStatus(final ProcessStatus status) {
		this.status = status;
	}
}