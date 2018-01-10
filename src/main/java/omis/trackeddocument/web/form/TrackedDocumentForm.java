package omis.trackeddocument.web.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import omis.docket.domain.Docket;

/** Form object for tracked document creation/edit.
 * @author: Yidong Li
 * @version 0.1.1 (Dec 18, 2017)
 * @since OMIS 3.0 */
public class TrackedDocumentForm implements Serializable {
	private static final long serialVersionUID = 1L;
	private Docket docket;
	private List<TrackedDocumentReceivalItem> trackedDocumentReceivalItems 
		= new ArrayList<TrackedDocumentReceivalItem>();
	
	/** Instantiates a tracked document form. */
	public TrackedDocumentForm() {
		// Default instantiation
	}
	
	/**
	 * Get tracked document receival items.
	 * @return tracked document receival items
	 */
	public List<TrackedDocumentReceivalItem> getTrackedDocumentReceivalItems() {
		return this.trackedDocumentReceivalItems;
	}
	
	/**
	 * Set tracked document receival items.
	 * @param trackedDocumentReceivalItems tracked document receival items
	 */
	public void setTrackedDocumentReceivalItems(
		final List<TrackedDocumentReceivalItem> trackedDocumentReceivalItems) {
		this.trackedDocumentReceivalItems = trackedDocumentReceivalItems;
	}
	 
	/**
	 * Get docket.
	 * @return docket
	 */
	public Docket getDocket() {
		return this.docket;
	}
	
	/**
	 * Set docket.
	 * @param docket docket
	 */
	public void setDocket(final Docket docket) {
		this.docket = docket;
	}
}	
