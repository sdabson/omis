package omis.presentenceinvestigation.web.form;

import java.util.ArrayList;
import java.util.List;


/**
 * JailAdjustmentSectionSummaryForm.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Feb 27, 2017)
 *@since OMIS 3.0
 *
 */
public class JailAdjustmentSectionSummaryForm {

	private List<JailAdjustmentSectionSummaryNoteItem>
		jailAdjustmentSectionSummaryNoteItems =
		new ArrayList<JailAdjustmentSectionSummaryNoteItem>();
	
	/**
	 * 
	 */
	public JailAdjustmentSectionSummaryForm() {
	}

	/**
	 * Returns the jailAdjustmentSectionSummaryNoteItems
	 * @return jailAdjustmentSectionSummaryNoteItems -
	 *  List<JailAdjustmentSectionSummaryNoteItem>
	 */
	public List<JailAdjustmentSectionSummaryNoteItem>
			getJailAdjustmentSectionSummaryNoteItems() {
		return jailAdjustmentSectionSummaryNoteItems;
	}

	/**
	 * Sets the jailAdjustmentSectionSummaryNoteItems
	 * @param jailAdjustmentSectionSummaryNoteItems -
	 *  List<JailAdjustmentSectionSummaryNoteItem>
	 */
	public void setJailAdjustmentSectionSummaryNoteItems(
			final List<JailAdjustmentSectionSummaryNoteItem>
				jailAdjustmentSectionSummaryNoteItems) {
		this.jailAdjustmentSectionSummaryNoteItems =
				jailAdjustmentSectionSummaryNoteItems;
	}
	
	
	
	
}
