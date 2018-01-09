package omis.presentenceinvestigation.web.form;

import java.util.ArrayList;
import java.util.List;

/**
 * OtherPertinentInformationSectionSummaryForm.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 19, 2017)
 *@since OMIS 3.0
 *
 */
public class OtherPertinentInformationSectionSummaryForm {
	
	private String description;
	
	private List<OtherPertinentInformationSectionSummaryNoteItem>
		otherPertinentInformationSectionSummaryNoteItems =
		new ArrayList<OtherPertinentInformationSectionSummaryNoteItem>();
	
	/**
	 * 
	 */
	public OtherPertinentInformationSectionSummaryForm() {
	}

	/**
	 * Returns the description
	 * @return description - String
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description
	 * @param description - String
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * Returns the otherPertinentInformationSectionSummaryNoteItems
	 * @return otherPertinentInformationSectionSummaryNoteItems -
	 * List<OtherPertinentInformationSectionSummaryNoteItem>
	 */
	public List<OtherPertinentInformationSectionSummaryNoteItem>
		getOtherPertinentInformationSectionSummaryNoteItems() {
		return otherPertinentInformationSectionSummaryNoteItems;
	}

	/**
	 * Sets the otherPertinentInformationSectionSummaryNoteItems
	 * @param otherPertinentInformationSectionSummaryNoteItems -
	 * List<OtherPertinentInformationSectionSummaryNoteItem>
	 */
	public void setOtherPertinentInformationSectionSummaryNoteItems(
			final List<OtherPertinentInformationSectionSummaryNoteItem>
				otherPertinentInformationSectionSummaryNoteItems) {
		this.otherPertinentInformationSectionSummaryNoteItems =
				otherPertinentInformationSectionSummaryNoteItems;
	}
}
