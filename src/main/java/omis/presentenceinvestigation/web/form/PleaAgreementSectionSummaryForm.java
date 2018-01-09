package omis.presentenceinvestigation.web.form;

import java.util.ArrayList;
import java.util.List;

/**
 * PleaAgreementSectionSummaryForm.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Apr 13, 2017)
 *@since OMIS 3.0
 *
 */
public class PleaAgreementSectionSummaryForm {
	
	private String summary;
	
	private List<PleaAgreementSectionSummaryNoteItem>
		pleaAgreementSectionSummaryNoteItems =
		new ArrayList<PleaAgreementSectionSummaryNoteItem>();
	
	private List<PleaAgreementSectionSummaryAssociableDocumentItem>
		pleaAgreementSectionSummaryAssociableDocumentItems =
		new ArrayList<PleaAgreementSectionSummaryAssociableDocumentItem>();
	
	/**
	 * 
	 */
	public PleaAgreementSectionSummaryForm() {
	}

	/**
	 * Returns the summary
	 * @return summary - String
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * Sets the summary
	 * @param summary - String
	 */
	public void setSummary(final String summary) {
		this.summary = summary;
	}

	/**
	 * Returns the pleaAgreementSectionSummaryNoteItems
	 * @return pleaAgreementSectionSummaryNoteItems -
	 * List<PleaAgreementSectionSummaryNoteItem>
	 */
	public List<PleaAgreementSectionSummaryNoteItem>
			getPleaAgreementSectionSummaryNoteItems() {
		return pleaAgreementSectionSummaryNoteItems;
	}

	/**
	 * Sets the pleaAgreementSectionSummaryNoteItems
	 * @param pleaAgreementSectionSummaryNoteItems -
	 * List<PleaAgreementSectionSummaryNoteItem>
	 */
	public void setPleaAgreementSectionSummaryNoteItems(
			final List<PleaAgreementSectionSummaryNoteItem>
				pleaAgreementSectionSummaryNoteItems) {
		this.pleaAgreementSectionSummaryNoteItems =
				pleaAgreementSectionSummaryNoteItems;
	}

	/**
	 * Returns the pleaAgreementSectionSummaryAssociableDocumentItems
	 * @return pleaAgreementSectionSummaryAssociableDocumentItems -
	 * List<PleaAgreementSectionSummaryAssociableDocumentItem>
	 */
	public List<PleaAgreementSectionSummaryAssociableDocumentItem>
			getPleaAgreementSectionSummaryAssociableDocumentItems() {
		return pleaAgreementSectionSummaryAssociableDocumentItems;
	}

	/**
	 * Sets the pleaAgreementSectionSummaryAssociableDocumentItems
	 * @param pleaAgreementSectionSummaryAssociableDocumentItems -
	 * List<PleaAgreementSectionSummaryAssociableDocumentItem>
	 */
	public void setPleaAgreementSectionSummaryAssociableDocumentItems(
			final List<PleaAgreementSectionSummaryAssociableDocumentItem>
				pleaAgreementSectionSummaryAssociableDocumentItems) {
		this.pleaAgreementSectionSummaryAssociableDocumentItems =
				pleaAgreementSectionSummaryAssociableDocumentItems;
	}
	
	
}
