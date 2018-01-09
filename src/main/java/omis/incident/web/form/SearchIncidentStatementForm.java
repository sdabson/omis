package omis.incident.web.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import omis.incident.domain.Jurisdiction;
import omis.location.domain.Location;

/** 
 * Form object for search incident report.
 * 
 * @author: Yidong Li
 * @author: Joel Norris
 * @version 0.1.1 (Oct 13, 2015)
 * @since OMIS 3.0
 */
public class SearchIncidentStatementForm {
	private Date startDate;
	private Date endDate;
	private List<InvolvedPersonItem> items 
		= new ArrayList<InvolvedPersonItem>();
	private Location location;
	private Jurisdiction jurisdiction;
	private Boolean searchMode;
	
	/**
	 * Instantiates a search incident report form.
	 */
	public SearchIncidentStatementForm() {
		// Default instantiation
	}
	
	/**
	 * Returns the start date.
	 * 
	 * @return start date
	 */
	public Date getStartDate() {
		return this.startDate;
	}
	
	/**
	 * Sets the start date.
	 * 
	 * @param startDate start date
	 */
	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}
	
	/**
	 * Returns the end date.
	 * 
	 * @return end date
	 */
	public Date getEndDate() {
		return this.endDate;
	}
	
	/**
	 * Sets the end date.
	 * 
	 * @param endDate end date
	 */
	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}
	
	/**
	 * Returns the list of involved person items.
	 * 
	 * @return involved person items
	 */
	public List<InvolvedPersonItem> getItems() {
		return this.items;
	}

	/**
	 * Sets the list of involved person items.
	 * 
	 * @param items involved person items
	 */
	public void setItems(final List<InvolvedPersonItem> items) {
		this.items = items;
	}

	/**
	 * Returns the location.
	 * 
	 * @return location
	 */
	public Location getLocation() {
		return this.location;
	}
	
	/**
	 * Sets the location.
	 * 
	 * @param location location
	 */
	public void setLocation(final Location location) {
		this.location = location;
	}
	
	/**
	 * Returns the jurisdiction.
	 * 
	 * @return jurisdiction
	 */
	public Jurisdiction getJurisdiction() {
		return this.jurisdiction;
	}
	
	/**
	 * Sets the jurisdiction.
	 * 
	 * @param jurisdiction jurisdiction
	 */
	public void setJurisdiction(final Jurisdiction jurisdiction) {
		this.jurisdiction = jurisdiction;
	}
	
	/**
	 * Returns the search mode.
	 * 
	 * @return search mode
	 */
	public Boolean getSearchMode() {
		return this.searchMode;
	}
	
	/**
	 * Sets the search mode.
	 * 
	 * @param searchMode search mode
	 */
	public void setSearchMode(final Boolean searchMode) {
		this.searchMode = searchMode;
	}
}