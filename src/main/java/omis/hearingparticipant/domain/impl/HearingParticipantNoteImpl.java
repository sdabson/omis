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

import java.util.Date;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.hearingparticipant.domain.HearingParticipant;
import omis.hearingparticipant.domain.HearingParticipantNote;

/**
 * Hearing Participant Note Implementation.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Jan 12, 2018)
 *@since OMIS 3.0
 *
 */
public class HearingParticipantNoteImpl implements HearingParticipantNote {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Date date;
	
	private String description;
	
	private HearingParticipant participant;
	
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
	public String getDescription() {
		return this.description;
	}

	/**{@inheritDoc} */
	@Override
	public void setDescription(final String description) {
		this.description = description;
	}

	/**{@inheritDoc} */
	@Override
	public Date getDate() {
		return this.date;
	}

	/**{@inheritDoc} */
	@Override
	public void setDate(final Date date) {
		this.date = date;
	}

	/**{@inheritDoc} */
	@Override
	public HearingParticipant getParticipant() {
		return this.participant;
	}

	/**{@inheritDoc} */
	@Override
	public void setParticipant(
			final HearingParticipant participant) {
		this.participant = participant;
	}
	
	/**{@inheritDoc}*/
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof HearingParticipantNote)) {
			return false;
		}
		
		HearingParticipantNote that = (HearingParticipantNote) obj;
		
		if (this.getParticipant() == null) {
			throw new IllegalStateException("Participant required.");
		}
		if (this.getDate() == null) {
			throw new IllegalStateException("Date required.");
		}
		if (this.getDescription() == null) {
			throw new IllegalStateException("Description required.");
		}
		
		if (!this.getParticipant().equals(that.getParticipant())) {
			return false;
		}
		if (!this.getDate().equals(that.getDate())) {
			return false;
		}
		if (!this.getDescription().equals(that.getDescription())) {
			return false;
		}
		
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getParticipant() == null) {
			throw new IllegalStateException("Participant required.");
		}
		if (this.getDate() == null) {
			throw new IllegalStateException("Date required.");
		}
		if (this.getDescription() == null) {
			throw new IllegalStateException("Description required.");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getParticipant().hashCode();
		hashCode = 29 * hashCode + this.getDate().hashCode();
		hashCode = 29 * hashCode + this.getDescription().hashCode();
		
		return hashCode;
	}
}
