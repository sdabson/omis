package omis.presentenceinvestigation.web.form;

import java.util.ArrayList;
import java.util.List;

import omis.presentenceinvestigation.domain.HealthRating;

/**
 * Health section summary form.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (May 10, 2017)
 * @since OMIS 3.0
 */
public class HealthSectionSummaryForm {
	
	private String summary;
	
	private List<HealthSectionSummaryNoteItem>
		healthSectionSummaryNoteItems =
		new ArrayList<HealthSectionSummaryNoteItem>();
	
	private List<HealthSectionSummaryDocumentAssociationItem>
		healthSectionSummaryDocumentAssociationItems =
		new ArrayList<HealthSectionSummaryDocumentAssociationItem>();
	
	private HealthRating rating;

	/** Instantiates an implementation of HealthSectionSummaryForm */
	public HealthSectionSummaryForm() {
		// Default constructor.
	}

	/**
	 * Return health section summary.
	 *
	 * @return the summary
	 */
	public String getSummary() {
		return this.summary;
	}

	/**
	 * Sets the health section summary.
	 *
	 * @param summary summary
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}

	/**
	 * Return list of health section summary note items.
	 *
	 * @return the healthSectionSummaryNoteItems
	 */
	public List<HealthSectionSummaryNoteItem> 
		getHealthSectionSummaryNoteItems() {
		return this.healthSectionSummaryNoteItems;
	}

	/**
	 * Sets list of health section summary note items.
	 *
	 * @param healthSectionSummaryNoteItems healthSectionSummaryNoteItems
	 */
	public void setHealthSectionSummaryNoteItems(
			List<HealthSectionSummaryNoteItem> healthSectionSummaryNoteItems) {
		this.healthSectionSummaryNoteItems = healthSectionSummaryNoteItems;
	}

	/**
	 * Returns list of health section summary document association items.
	 *
	 * @return the healthSectionSummaryDocumentAssociationItems
	 */
	public List<HealthSectionSummaryDocumentAssociationItem> 
		getHealthSectionSummaryDocumentAssociationItems() {
		return this.healthSectionSummaryDocumentAssociationItems;
	}

	/**
	 * Sets list of health section summary document association items.
	 *
	 * @param healthSectionSummaryDocumentAssociationItems 
	 * health section summary document association items
	 */
	public void setHealthSectionSummaryDocumentAssociationItems(
			final List<HealthSectionSummaryDocumentAssociationItem> 
			healthSectionSummaryDocumentAssociationItems) {
		this.healthSectionSummaryDocumentAssociationItems 
			= healthSectionSummaryDocumentAssociationItems;
	}

	/**
	 *
	 *
	 * @return the rating
	 */
	public HealthRating getRating() {
		return this.rating;
	}

	/**
	 *
	 *
	 * @param rating rating
	 */
	public void setRating(final HealthRating rating) {
			this.rating = rating;
	}
}