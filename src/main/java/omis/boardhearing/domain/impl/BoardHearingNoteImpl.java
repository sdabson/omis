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
package omis.boardhearing.domain.impl;

import java.util.Date;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.boardhearing.domain.BoardHearing;
import omis.boardhearing.domain.BoardHearingNote;

/**
 * Board Hearing Note Implementation.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Dec 28, 2017)
 *@since OMIS 3.0
 *
 */
public class BoardHearingNoteImpl implements BoardHearingNote {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String description;
	
	private Date date;
	
	private BoardHearing hearing;
	
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
	public BoardHearing getHearing() {
		return this.hearing;
	}

	/**{@inheritDoc} */
	@Override
	public void setHearing(final BoardHearing hearing) {
		this.hearing = hearing;
	}
	
	/**{@inheritDoc}*/
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof BoardHearingNote)) {
			return false;
		}
		
		BoardHearingNote that = (BoardHearingNote) obj;
		
		if (this.getHearing() == null) {
			throw new IllegalStateException("Hearing required.");
		}
		if (this.getDescription() == null) {
			throw new IllegalStateException("Description required.");
		}
		if (this.getDate() == null) {
			throw new IllegalStateException("Date required.");
		}
		
		if (!this.getHearing().equals(that.getHearing())) {
			return false;
		}
		if (!this.getDescription().equals(that.getDescription())) {
			return false;
		}
		if (!this.getDate().equals(that.getDate())) {
			return false;
		}
		
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getHearing() == null) {
			throw new IllegalStateException("Hearing required.");
		}
		if (this.getDescription() == null) {
			throw new IllegalStateException("Description required.");
		}
		if (this.getDate() == null) {
			throw new IllegalStateException("Date required.");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getHearing().hashCode();
		hashCode = 29 * hashCode + this.getDescription().hashCode();
		hashCode = 29 * hashCode + this.getDate().hashCode();
		
		return hashCode;
	}
}
