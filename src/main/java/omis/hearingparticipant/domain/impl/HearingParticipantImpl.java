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
package omis.hearingparticipant.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.boardhearing.domain.BoardHearing;
import omis.hearingparticipant.domain.HearingParticipant;
import omis.hearingparticipant.domain.HearingParticipantCategory;
import omis.hearingparticipant.domain.HearingParticipantIntentCategory;
import omis.person.domain.Person;

/**
 * Hearing Participant Implementation.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Jan 12, 2018)
 *@since OMIS 3.0
 *
 */
public class HearingParticipantImpl implements HearingParticipant {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private BoardHearing boardHearing;
	
	private Person person;
	
	private Boolean boardApproved;
	
	private Boolean witness;
	
	private Boolean facilityApproved;
	
	private String comments;
	
	private HearingParticipantIntentCategory intent;
	
	private HearingParticipantCategory category;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	/**{@inheritDoc} */
	@Override
	public void setCreationSignature(
			final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/**{@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}

	/**{@inheritDoc} */
	@Override
	public void setUpdateSignature(final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}

	/**{@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
	}

	/**{@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/**{@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}
	
	/**{@inheritDoc} */
	@Override
	public BoardHearing getBoardHearing() {
		return this.boardHearing;
	}
	
	/**{@inheritDoc} */
	@Override
	public void setBoardHearing(final BoardHearing boardHearing) {
		this.boardHearing = boardHearing;
	}
	
	/**{@inheritDoc} */
	@Override
	public Person getPerson() {
		return this.person;
	}

	/**{@inheritDoc} */
	@Override
	public void setPerson(final Person person) {
		this.person = person;
	}

	/**{@inheritDoc} */
	@Override
	public Boolean getBoardApproved() {
		return this.boardApproved;
	}

	/**{@inheritDoc} */
	@Override
	public void setBoardApproved(final Boolean boardApproved) {
		this.boardApproved = boardApproved;
	}

	/**{@inheritDoc} */
	@Override
	public Boolean getWitness() {
		return this.witness;
	}

	/**{@inheritDoc} */
	@Override
	public void setWitness(final Boolean witness) {
		this.witness = witness;
	}
	
	/**{@inheritDoc} */
	@Override
	public Boolean getFacilityApproved() {
		return this.facilityApproved;
	}
	
	/**{@inheritDoc} */
	@Override
	public void setFacilityApproved(final Boolean facilityApproved) {
		this.facilityApproved = facilityApproved;
	}
	
	/**{@inheritDoc} */
	@Override
	public String getComments() {
		return this.comments;
	}

	/**{@inheritDoc} */
	@Override
	public void setComments(final String comments) {
		this.comments = comments;
	}

	/**{@inheritDoc} */
	@Override
	public HearingParticipantCategory getCategory() {
		return this.category;
	}

	/**{@inheritDoc} */
	@Override
	public void setCategory(final HearingParticipantCategory category) {
		this.category = category;
	}

	/**{@inheritDoc} */
	@Override
	public HearingParticipantIntentCategory getIntent() {
		return this.intent;
	}

	/**{@inheritDoc} */
	@Override
	public void setIntent(final HearingParticipantIntentCategory intent) {
		this.intent = intent;
	}
	
	/**{@inheritDoc}*/
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof HearingParticipant)) {
			return false;
		}
		
		HearingParticipant that = (HearingParticipant) obj;

		if (this.getBoardHearing() == null) {
			throw new IllegalStateException("Board Hearing required.");
		}
		if (this.getPerson() == null) {
			throw new IllegalStateException("Person required.");
		}
		if (this.getBoardApproved() == null) {
			throw new IllegalStateException("Board Approved required.");
		}
		if (this.getWitness() == null) {
			throw new IllegalStateException("Witness required.");
		}
		if (this.getFacilityApproved() == null) {
			throw new IllegalStateException("Facility Approved required.");
		}
		if (this.getCategory() == null) {
			throw new IllegalStateException("Category required.");
		}

		if (!this.getBoardHearing().equals(that.getBoardHearing())) {
			return false;
		}
		if (!this.getPerson().equals(that.getPerson())) {
			return false;
		}
		if (!this.getCategory().equals(that.getCategory())) {
			return false;
		}
		
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getBoardHearing() == null) {
			throw new IllegalStateException("Board Hearing required.");
		}
		if (this.getPerson() == null) {
			throw new IllegalStateException("Person required.");
		}
		if (this.getBoardApproved() == null) {
			throw new IllegalStateException("Board Approved required.");
		}
		if (this.getWitness() == null) {
			throw new IllegalStateException("Witness required.");
		}
		if (this.getFacilityApproved() == null) {
			throw new IllegalStateException("Facility Approved required.");
		}
		if (this.getCategory() == null) {
			throw new IllegalStateException("Category required.");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getBoardHearing().hashCode();
		hashCode = 29 * hashCode + this.getPerson().hashCode();
		hashCode = 29 * hashCode + this.getCategory().hashCode();
		
		return hashCode;
	}
	
}
