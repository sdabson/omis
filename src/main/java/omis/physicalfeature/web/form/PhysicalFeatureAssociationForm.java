package omis.physicalfeature.web.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import omis.physicalfeature.domain.FeatureClassification;
import omis.physicalfeature.domain.PhysicalFeature;

/**
 * Offender physical feature form.
 * 
 * @author Joel Norris
 * @version 0.1.1 (Aug 10, 2013)
 * @since OMIS 3.0
 */
public class PhysicalFeatureAssociationForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private FeatureClassification featureClassification;
	
	private PhysicalFeature feature;
	
	private String description;
	
	private Date originationDate;
	
	private List<PhysicalFeaturePhotoItem> photoItems
		= new ArrayList<PhysicalFeaturePhotoItem>();
	
	private List<PhysicalFeatureAssociationNoteItem> noteItems
		= new ArrayList<PhysicalFeatureAssociationNoteItem>();
	
	/** 
	 * Instantiates a default physical feature association form.
	 */
	public PhysicalFeatureAssociationForm() {
		//Default constructor.
	}

	/**
	 * Returns the feature classification of the physical feature association
	 * form.
	 * @return feature classification
	 */
	public FeatureClassification getFeatureClassification() {
		return this.featureClassification;
	}

	/**
	 * Sets the feature classification of the physical feature association form.
	 * @param featureClassification feature classification
	 */
	public void setFeatureClassification(
			final FeatureClassification featureClassification) {
		this.featureClassification = featureClassification;
	}

	/**
	 * Returns the physical feature of the physical feature association form.
	 * @return physical feature
	 */
	public PhysicalFeature getFeature() {
		return this.feature;
	}

	/**
	 * Sets the physical feature of the physical feature association form.
	 * @param feature physical feature
	 */
	public void setFeature(final PhysicalFeature feature) {
		this.feature = feature;
	}

	/**
	 * Returns the description of the physical feature association form.
	 * @return description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Sets the description of the physical feature association form.
	 * @param description description
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * Sets the origination date.
	 * 
	 * @return origination date
	 */
	public Date getOriginationDate() {
		return this.originationDate;
	}

	/**
	 * Returns the origination date.
	 * 
	 * @param originationDate origination date
	 */
	public void setOriginationDate(final Date originationDate) {
		this.originationDate = originationDate;
	}

	/**
	 * Returns the physical feature photo items.
	 * 
	 * @return physical feature photo items
	 */
	public List<PhysicalFeaturePhotoItem> getPhotoItems() {
		return this.photoItems;
	}

	/**
	 * Sets the physical feature photo items.
	 * 
	 * @param photoItems physical feature photo items
	 */
	public void setPhotoItems(final List<PhysicalFeaturePhotoItem> photoItems) {
		this.photoItems = photoItems;
	}

	/**
	 * Returns a list of physical feature association note items.
	 * 
	 * @return list of physical feature association note items
	 */
	public List<PhysicalFeatureAssociationNoteItem> getNoteItems() {
		return this.noteItems;
	}

	/**
	 * Sets a list of physical feature association note items.
	 * 
	 * @param noteItems physical feature association note items
	 */
	public void setNoteItems(
			final List<PhysicalFeatureAssociationNoteItem> noteItems) {
		this.noteItems = noteItems;
	}
}