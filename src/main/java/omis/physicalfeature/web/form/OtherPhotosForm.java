package omis.physicalfeature.web.form;

import java.util.ArrayList;
import java.util.List;

import omis.offender.domain.Offender;
import omis.physicalfeature.domain.PhysicalFeatureAssociation;

/**
 * Form for other photos.
 * @author Joel Norris
 * @version 0.1.0 (Feb 26, 2014)
 * @since OMIS 3.0
 */
public class OtherPhotosForm {

	private PhysicalFeatureAssociation physicalFeatureAssociation;
	
	private List<OtherPhysicalFeaturePhotoAssociationItem> oPFPAItems =
			new ArrayList<OtherPhysicalFeaturePhotoAssociationItem>();
	
	private Offender offender;
	
	/**
	 * Instantiates a default instance of other photos form.
	 */
	public OtherPhotosForm() {
		//Default constructor;
	}
	
	/**
	 * Returns the physical feature association of the other photos form.
	 * 
	 * @return physical feature association
	 */
	public PhysicalFeatureAssociation getPhysicalFeatureAssociation() {
		return this.physicalFeatureAssociation;
	}

	/**
	 * Sets the physical feature association of the other photos form.
	 * 
	 * @param physicalFeatureAssociation physical feature association
	 */
	public void setPhysicalFeatureAssociation(
			final PhysicalFeatureAssociation physicalFeatureAssociation) {
		this.physicalFeatureAssociation = physicalFeatureAssociation;
	}

	/**
	 * Returns the list of other physical feature photo association items for
	 * the other photos form.
	 * 
	 * @return list of other physical feature photo association items
	 */
	public List<OtherPhysicalFeaturePhotoAssociationItem> getoPFPAItems() {
		return this.oPFPAItems;
	}

	/**
	 * Sets the list of other physical feature photo association items for
	 * the other photos form.
	 * 
	 * @param oPFPAItems list of other physical feature photo association items
	 */
	public void setoPFPAItems(
			final List<OtherPhysicalFeaturePhotoAssociationItem> oPFPAItems) {
		this.oPFPAItems = oPFPAItems;
	}

	/**
	 * Returns the offender of the other photos form.
	 * @return offender
	 */
	public Offender getOffender() {
		return this.offender;
	}

	/**
	 * Sets the offender of the other photos form.
	 * @param offender offender
	 */
	public void setOffender(final Offender offender) {
		this.offender = offender;
	}
}