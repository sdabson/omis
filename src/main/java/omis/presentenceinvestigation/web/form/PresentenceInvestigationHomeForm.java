package omis.presentenceinvestigation.web.form;

import java.util.ArrayList;
import java.util.List;

/**
 * PresentenceInvestigationHomeForm.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jun 26, 2017)
 *@since OMIS 3.0
 *
 */
public class PresentenceInvestigationHomeForm {
	
	private List<PresentenceInvestigationTaskItem> summaryTaskItems =
			new ArrayList<PresentenceInvestigationTaskItem>();
	
	private List<PresentenceInvestigationTaskItem> basicInformationTaskItems =
			new ArrayList<PresentenceInvestigationTaskItem>();
	
	private List<PresentenceInvestigationTaskItem> legalTaskItems =
			new ArrayList<PresentenceInvestigationTaskItem>();
	
	private List<PresentenceInvestigationTaskItem> relationshipsTaskItems =
			new ArrayList<PresentenceInvestigationTaskItem>();
	
	private List<PresentenceInvestigationTaskItem> complianceTaskItems =
			new ArrayList<PresentenceInvestigationTaskItem>();
	
	private List<PresentenceInvestigationTaskItem> caseManagementTaskItems =
			new ArrayList<PresentenceInvestigationTaskItem>();
	
	private List<PresentenceInvestigationRequestNoteItem>
			presentenceInvestigationRequestNoteItems =
				new ArrayList<PresentenceInvestigationRequestNoteItem>();
	
	/**
	 * 
	 */
	public PresentenceInvestigationHomeForm() {
	}

	/**
	 * Returns the summaryTaskItems
	 * @return summaryTaskItems - List&lt;PresentenceInvestigationTaskItem&gt;
	 */
	public List<PresentenceInvestigationTaskItem> getSummaryTaskItems() {
		return summaryTaskItems;
	}

	/**
	 * Sets the summaryTaskItems
	 * @param summaryTaskItems - List&lt;PresentenceInvestigationTaskItem&gt;
	 */
	public void setSummaryTaskItems(
			final List<PresentenceInvestigationTaskItem> summaryTaskItems) {
		this.summaryTaskItems = summaryTaskItems;
	}

	/**
	 * Returns the basicInformationTaskItems
	 * @return basicInformationTaskItems - List&lt;PresentenceInvestigationTaskItem&gt;
	 */
	public List<PresentenceInvestigationTaskItem> getBasicInformationTaskItems() {
		return basicInformationTaskItems;
	}

	/**
	 * Sets the basicInformationTaskItems
	 * @param basicInformationTaskItems - List&lt;PresentenceInvestigationTaskItem&gt;
	 */
	public void setBasicInformationTaskItems(
			final List<PresentenceInvestigationTaskItem>
				basicInformationTaskItems) {
		this.basicInformationTaskItems = basicInformationTaskItems;
	}

	/**
	 * Returns the legalTaskItems
	 * @return legalTaskItems - List&lt;PresentenceInvestigationTaskItem&gt;
	 */
	public List<PresentenceInvestigationTaskItem> getLegalTaskItems() {
		return legalTaskItems;
	}

	/**
	 * Sets the legalTaskItems
	 * @param legalTaskItems - List&lt;PresentenceInvestigationTaskItem&gt;
	 */
	public void setLegalTaskItems(
			final List<PresentenceInvestigationTaskItem> legalTaskItems) {
		this.legalTaskItems = legalTaskItems;
	}

	/**
	 * Returns the relationshipsTaskItems
	 * @return relationshipsTaskItems - List&lt;PresentenceInvestigationTaskItem&gt;
	 */
	public List<PresentenceInvestigationTaskItem> getRelationshipsTaskItems() {
		return relationshipsTaskItems;
	}

	/**
	 * Sets the relationshipsTaskItems
	 * @param relationshipsTaskItems - List&lt;PresentenceInvestigationTaskItem&gt;
	 */
	public void setRelationshipsTaskItems(
			final List<PresentenceInvestigationTaskItem> relationshipsTaskItems) {
		this.relationshipsTaskItems = relationshipsTaskItems;
	}

	/**
	 * Returns the complianceTaskItems
	 * @return complianceTaskItems - List&lt;PresentenceInvestigationTaskItem&gt;
	 */
	public List<PresentenceInvestigationTaskItem> getComplianceTaskItems() {
		return complianceTaskItems;
	}

	/**
	 * Sets the complianceTaskItems
	 * @param complianceTaskItems - List&lt;PresentenceInvestigationTaskItem&gt;
	 */
	public void setComplianceTaskItems(
			final List<PresentenceInvestigationTaskItem> complianceTaskItems) {
		this.complianceTaskItems = complianceTaskItems;
	}

	/**
	 * Returns the caseManagementTaskItems
	 * @return caseManagementTaskItems - List&lt;PresentenceInvestigationTaskItem&gt;
	 */
	public List<PresentenceInvestigationTaskItem> getCaseManagementTaskItems() {
		return caseManagementTaskItems;
	}

	/**
	 * Sets the caseManagementTaskItems
	 * @param caseManagementTaskItems - List&lt;PresentenceInvestigationTaskItem&gt;
	 */
	public void setCaseManagementTaskItems(
			final List<PresentenceInvestigationTaskItem> caseManagementTaskItems) {
		this.caseManagementTaskItems = caseManagementTaskItems;
	}

	/**
	 * Returns the presentenceInvestigationRequestNoteItems
	 * @return presentenceInvestigationRequestNoteItems -
	 * List&lt;PresentenceInvestigationRequestNoteItem&gt;
	 */
	public List<PresentenceInvestigationRequestNoteItem>
		getPresentenceInvestigationRequestNoteItems() {
		return presentenceInvestigationRequestNoteItems;
	}

	/**
	 * Sets the presentenceInvestigationRequestNoteItems
	 * @param presentenceInvestigationRequestNoteItems -
	 * List&lt;PresentenceInvestigationRequestNoteItem&gt;
	 */
	public void setPresentenceInvestigationRequestNoteItems(
			final List<PresentenceInvestigationRequestNoteItem>
				presentenceInvestigationRequestNoteItems) {
		this.presentenceInvestigationRequestNoteItems =
				presentenceInvestigationRequestNoteItems;
	}
}
