package omis.presentenceinvestigation.web.form;

import omis.presentenceinvestigation.domain.PresentenceInvestigationTaskAssociation;

/**
 * PresentenceInvestigationTaskItem.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jun 26, 2017)
 *@since OMIS 3.0
 *
 */
public class PresentenceInvestigationTaskItem {
	
	private PresentenceInvestigationTaskAssociation taskAssociation;
	
	private PresentenceInvestigationTaskItemOperation itemOperation;
	
	/**
	 * 
	 */
	public PresentenceInvestigationTaskItem() {
	}

	/**
	 * Returns the taskAssociation
	 * @return taskAssociation - PresentenceInvestigationTaskAssociation
	 */
	public PresentenceInvestigationTaskAssociation getTaskAssociation() {
		return taskAssociation;
	}

	/**
	 * Sets the taskAssociation
	 * @param taskAssociation - PresentenceInvestigationTaskAssociation
	 */
	public void setTaskAssociation(
			final PresentenceInvestigationTaskAssociation taskAssociation) {
		this.taskAssociation = taskAssociation;
	}

	/**
	 * Returns the itemOperation
	 * @return itemOperation - PresentenceInvestigationTaskItemOperation
	 */
	public PresentenceInvestigationTaskItemOperation getItemOperation() {
		return itemOperation;
	}

	/**
	 * Sets the itemOperation
	 * @param itemOperation - PresentenceInvestigationTaskItemOperation
	 */
	public void
		setItemOperation(
				final PresentenceInvestigationTaskItemOperation itemOperation) {
		this.itemOperation = itemOperation;
	}
}
