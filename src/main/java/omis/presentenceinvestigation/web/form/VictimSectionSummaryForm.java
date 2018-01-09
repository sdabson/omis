package omis.presentenceinvestigation.web.form;

import java.util.ArrayList;
import java.util.List;

/**
 * VictimSectionSummaryForm.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 20, 2017)
 *@since OMIS 3.0
 *
 */
public class VictimSectionSummaryForm {
	
	private String text;
	
	private List<VictimSectionSummaryNoteItem> victimSectionSummaryNoteItems =
			new ArrayList<VictimSectionSummaryNoteItem>();
	
	private List<VictimSectionSummaryDocketAssociationItem> 
		victimSectionSummaryDocketAssociationItems 
		= new ArrayList<VictimSectionSummaryDocketAssociationItem>();
	
	/**
	 * Default constructor of Victim Section Summary Form
	 */
	public VictimSectionSummaryForm() {
		// Default instantiation
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
	public void setText(final String text) {
		this.text = text;
	}

	/**
	 * Returns the victimSectionSummaryNoteItems
	 * @return victimSectionSummaryNoteItems - List<VictimSectionSummaryNoteItem>
	 */
	public List<VictimSectionSummaryNoteItem> getVictimSectionSummaryNoteItems() {
		return victimSectionSummaryNoteItems;
	}

	/**
	 * Sets the victimSectionSummaryNoteItems
	 * @param victimSectionSummaryNoteItems - List<VictimSectionSummaryNoteItem>
	 */
	public void setVictimSectionSummaryNoteItems(
			final List<VictimSectionSummaryNoteItem>
				victimSectionSummaryNoteItems) {
		this.victimSectionSummaryNoteItems = victimSectionSummaryNoteItems;
	}
	
	/**
	 * Returns the victim section summary docket association items.
	 * 
	 * @return victim section summary docket association items 
	 * - List<VictimSectionSummaryDocketAssociationItem>
	 */
	public List<VictimSectionSummaryDocketAssociationItem> 
			getVictimSectionSummaryDocketAssociationItems() {
		return victimSectionSummaryDocketAssociationItems;
	}

	/**
	 * Sets the victim section summary docket association items.
	 * 
	 * @param VictimSectionSummaryDocketAssociationItems victim section summary 
	 * docket association items 
	 */
	public void setVictimSectionSummaryDocketAssociationItems(
			final List<VictimSectionSummaryDocketAssociationItem>
				victimSectionSummaryDocketAssociationItems) {
			this.victimSectionSummaryDocketAssociationItems 
				= victimSectionSummaryDocketAssociationItems;
	}
	
}
