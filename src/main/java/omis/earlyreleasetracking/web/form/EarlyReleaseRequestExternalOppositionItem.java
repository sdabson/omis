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
package omis.earlyreleasetracking.web.form;

import java.io.Serializable;
import java.util.Date;
import omis.earlyreleasetracking.domain.EarlyReleaseRequestExternalOpposition;
import omis.earlyreleasetracking.domain.ExternalOppositionPartyCategory;

/**
 * Early Release Request External Opposition Item.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Feb 13, 2019)
 *@since OMIS 3.0
 *
 */
public class EarlyReleaseRequestExternalOppositionItem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private EarlyReleaseRequestExternalOpposition
				earlyReleaseRequestExternalOpposition;
	
	private Date date;
	
	private ExternalOppositionPartyCategory party;
	
	private String narrative;
	
	private EarlyReleaseRequestItemOperation itemOperation;
	
	/**
	 * Default Contructor for EarlyReleaseRequestExternalOppositionItem.
	 */
	public EarlyReleaseRequestExternalOppositionItem() {
	}

	/**
	 * Returns the earlyReleaseRequestExternalOpposition.
	 *
	 * @return earlyReleaseRequestExternalOpposition
	 */
	public EarlyReleaseRequestExternalOpposition
			getEarlyReleaseRequestExternalOpposition() {
		return this.earlyReleaseRequestExternalOpposition;
	}

	/**
	 * Sets the earlyReleaseRequestExternalOpposition.
	 *
	 * @param earlyReleaseRequestExternalOpposition Early Release Request
	 * External Opposition
	 */
	public void setEarlyReleaseRequestExternalOpposition(
			final EarlyReleaseRequestExternalOpposition
					earlyReleaseRequestExternalOpposition) {
		this.earlyReleaseRequestExternalOpposition =
				earlyReleaseRequestExternalOpposition;
	}

	/**
	 * Returns the date.
	 *
	 * @return date
	 */
	public Date getDate() {
		return this.date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date - date
	 */
	public void setDate(final Date date) {
		this.date = date;
	}

	/**
	 * Returns the party.
	 *
	 * @return party
	 */
	public ExternalOppositionPartyCategory getParty() {
		return this.party;
	}

	/**
	 * Sets the party.
	 *
	 * @param party - party
	 */
	public void setParty(final ExternalOppositionPartyCategory party) {
		this.party = party;
	}

	/**
	 * Returns the narrative.
	 *
	 * @return narrative
	 */
	public String getNarrative() {
		return this.narrative;
	}

	/**
	 * Sets the narrative.
	 *
	 * @param narrative - narrative
	 */
	public void setNarrative(final String narrative) {
		this.narrative = narrative;
	}

	/**
	 * Returns the itemOperation.
	 *
	 * @return itemOperation
	 */
	public EarlyReleaseRequestItemOperation getItemOperation() {
		return this.itemOperation;
	}

	/**
	 * Sets the itemOperation.
	 *
	 * @param itemOperation - itemOperation
	 */
	public void setItemOperation(
			final EarlyReleaseRequestItemOperation itemOperation) {
		this.itemOperation = itemOperation;
	}
}
