/* 
* OMIS - Offender Management Information System 
* Copyright (C) 2011 - 2017 State of Montana 
* 
* This program is free software: you can redistribute it and/or modify 
* it under the terms of the GNU General Public License as published by 
* the Free Software Foundation, either version 3 of the License, or 
* (at your option) any later version. 
* 
* This program is distributed in the hope that it will be useful, 
* but WITHOUT ANY WARRANTY; without even the implied warranty of 
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the 
* GNU General Public License for more details. 
* 
* You should have received a copy of the GNU General Public License 
* along with this program.  If not, see <http://www.gnu.org/licenses/>. 
*/
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
