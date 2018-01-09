package omis.presentenceinvestigation.web.form;

import java.util.ArrayList;
import java.util.List;

/**
 * ChemicalUseSectionSummaryForm.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 6, 2017)
 *@since OMIS 3.0
 *
 */
public class ChemicalUseSectionSummaryForm {
	
	private String text;
	
	private List<ChemicalUseSectionSummaryNoteItem>
		chemicalUseSectionSummaryNoteItems =
		new ArrayList<ChemicalUseSectionSummaryNoteItem>();
	
	private List<ChemicalUseSectionSummaryDocumentAssociationItem>
		chemicalUseSectionSummaryDocumentAssociationItems =
		new ArrayList<ChemicalUseSectionSummaryDocumentAssociationItem>();
	
	/**
	 * 
	 */
	public ChemicalUseSectionSummaryForm() {
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
	 * Returns the chemicalUseSectionSummaryNoteItems
	 * @return chemicalUseSectionSummaryNoteItems -
	 * List<ChemicalUseSectionSummaryNoteItem>
	 */
	public List<ChemicalUseSectionSummaryNoteItem>
			getChemicalUseSectionSummaryNoteItems() {
		return chemicalUseSectionSummaryNoteItems;
	}

	/**
	 * Sets the chemicalUseSectionSummaryNoteItems
	 * @param chemicalUseSectionSummaryNoteItems -
	 * List<ChemicalUseSectionSummaryNoteItem>
	 */
	public void setChemicalUseSectionSummaryNoteItems(
			final List<ChemicalUseSectionSummaryNoteItem>
				chemicalUseSectionSummaryNoteItems) {
		this.chemicalUseSectionSummaryNoteItems =
				chemicalUseSectionSummaryNoteItems;
	}

	/**
	 * Returns the chemicalUseSectionSummaryDocumentAssociationItems
	 * @return chemicalUseSectionSummaryDocumentAssociationItems -
	 * List<ChemicalUseSectionSummaryDocumentAssociationItem>
	 */
	public List<ChemicalUseSectionSummaryDocumentAssociationItem>
			getChemicalUseSectionSummaryDocumentAssociationItems() {
		return chemicalUseSectionSummaryDocumentAssociationItems;
	}

	/**
	 * Sets the chemicalUseSectionSummaryDocumentAssociationItems
	 * @param chemicalUseSectionSummaryDocumentAssociationItems -
	 * List<ChemicalUseSectionSummaryDocumentAssociationItem>
	 */
	public void setChemicalUseSectionSummaryDocumentAssociationItems(
			final List<ChemicalUseSectionSummaryDocumentAssociationItem>
				chemicalUseSectionSummaryDocumentAssociationItems) {
		this.chemicalUseSectionSummaryDocumentAssociationItems =
				chemicalUseSectionSummaryDocumentAssociationItems;
	}
	
	
	
}
