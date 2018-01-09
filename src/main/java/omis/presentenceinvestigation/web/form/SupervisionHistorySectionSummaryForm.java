package omis.presentenceinvestigation.web.form;

import java.util.ArrayList;
import java.util.List;

import omis.hearing.report.ViolationSummary;

/**
 * Supervision history section summary form.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Aug 14, 2017)
 * @since OMIS 3.0
 */
public class SupervisionHistorySectionSummaryForm {
	
	private String text;	
	
	private List<SupervisionHistoryNoteItem> supervisionHistoryNoteItems 
		= new ArrayList<SupervisionHistoryNoteItem>();

	private List<ViolationSummary> resolvedSummaries 
		= new ArrayList<ViolationSummary>();
	
	/** Instantiates an implementation of supervision history 
	 * section summary form */
	public SupervisionHistorySectionSummaryForm() {
		// Default constructor.
	}
	
	/**
	 * Returns the text of the supervision history section summary.
	 *
	 * @return the text
	 */
	public String getText() {
		return this.text;
	}

	/**
	 * Sets the text of the supervision history section summary.
	 *
	 * @param text text
	 */
	public void setText(String text) {
		 this.text = text;
	}

	/**
	 * Return a list of supervision history note items.
	 *
	 * @return the supervision history note items
	 */
	public List<SupervisionHistoryNoteItem> getSupervisionHistoryNoteItems() {
		return this.supervisionHistoryNoteItems;
	}

	/**
	 * Sets a list of supervision history note items.
	 *
	 * @param supervisionHistoryNoteItems supervision history note items
	 */
	public void setSupervisionHistoryNoteItems(
			List<SupervisionHistoryNoteItem> supervisionHistoryNoteItems) {
		this.supervisionHistoryNoteItems = supervisionHistoryNoteItems;
	}

	/**
	 * Return a list of resolved violation summaries.
	 *
	 * @return the resolvedSummaries
	 */
	public List<ViolationSummary> getResolvedSummaries() {
		return this.resolvedSummaries;
	}

	/**
	 * Sets a list of resolved summaries.
	 *
	 * @param resolvedSummaries resolved summaries
	 */
	public void setResolvedSummaries(List<ViolationSummary> resolvedSummaries) {
			this.resolvedSummaries = resolvedSummaries;
	}
}