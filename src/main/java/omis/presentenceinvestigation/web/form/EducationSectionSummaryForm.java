package omis.presentenceinvestigation.web.form;

import java.util.ArrayList;
import java.util.List;

/**
 * EducationSectionSummaryForm.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 16, 2017)
 *@since OMIS 3.0
 *
 */
public class EducationSectionSummaryForm {
	
	private String text;
	
	private List<EducationSectionSummaryNoteItem>
			educationSectionSummaryNoteItems =
			new ArrayList<EducationSectionSummaryNoteItem>();
	
	/**
	 * 
	 */
	public EducationSectionSummaryForm() {
	}

	/**
	 * Returns the text
	 * @return text - String
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets the text
	 * @param text - String
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Returns the educationSectionSummaryNoteItems
	 * @return educationSectionSummaryNoteItems -
	 * List<EducationSectionSummaryNoteItem>
	 */
	public List<EducationSectionSummaryNoteItem>
			getEducationSectionSummaryNoteItems() {
		return educationSectionSummaryNoteItems;
	}

	/**
	 * Sets the educationSectionSummaryNoteItems
	 * @param educationSectionSummaryNoteItems -
	 * List<EducationSectionSummaryNoteItem>
	 */
	public void setEducationSectionSummaryNoteItems(
			List<EducationSectionSummaryNoteItem>
				educationSectionSummaryNoteItems) {
		this.educationSectionSummaryNoteItems =
				educationSectionSummaryNoteItems;
	}
	
	
	
}
