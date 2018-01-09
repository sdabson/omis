package omis.presentenceinvestigation.web.form;

import java.util.ArrayList;
import java.util.List;

/**
 * PsychologicalSectionSummaryForm.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 2, 2017)
 *@since OMIS 3.0
 *
 */
public class PsychologicalSectionSummaryForm {
	
	private List<PsychologicalSectionSummaryNoteItem>
			psychologicalSectionSummaryNoteItems =
			new ArrayList<PsychologicalSectionSummaryNoteItem>();
	
	private List<PsychologicalSectionSummaryDocumentItem>
			psychologicalSectionSummaryDocumentItems =
			new ArrayList<PsychologicalSectionSummaryDocumentItem>();

	/**
	 * Default Constructor for PsychologicalSectionSummaryForm
	 */
	public PsychologicalSectionSummaryForm() {
	}
	
	/**
	 * Returns the psychologicalSectionSummaryNoteItems
	 * @return psychologicalSectionSummaryNoteItems -
	 *  List<PsychologicalSectionSummaryNoteItem>
	 */
	public List<PsychologicalSectionSummaryNoteItem>
			getPsychologicalSectionSummaryNoteItems() {
		return psychologicalSectionSummaryNoteItems;
	}

	/**
	 * Sets the psychologicalSectionSummaryNoteItems
	 * @param psychologicalSectionSummaryNoteItems -
	 *  List<PsychologicalSectionSummaryNoteItem>
	 */
	public void setPsychologicalSectionSummaryNoteItems(
			final List<PsychologicalSectionSummaryNoteItem>
					psychologicalSectionSummaryNoteItems) {
		this.psychologicalSectionSummaryNoteItems =
				psychologicalSectionSummaryNoteItems;
	}

	/**
	 * Returns the psychologicalSectionSummaryDocumentItems
	 * @return psychologicalSectionSummaryDocumentItems -
	 *  List<PsychologicalSectionSummaryDocumentItem>
	 */
	public List<PsychologicalSectionSummaryDocumentItem>
			getPsychologicalSectionSummaryDocumentItems() {
		return psychologicalSectionSummaryDocumentItems;
	}

	/**
	 * Sets the psychologicalSectionSummaryDocumentItems
	 * @param psychologicalSectionSummaryDocumentItems -
	 *  List<PsychologicalSectionSummaryDocumentItem>
	 */
	public void setPsychologicalSectionSummaryDocumentItems(
			final List<PsychologicalSectionSummaryDocumentItem>
					psychologicalSectionSummaryDocumentItems) {
		this.psychologicalSectionSummaryDocumentItems =
				psychologicalSectionSummaryDocumentItems;
	}
	
	
	
	
}
