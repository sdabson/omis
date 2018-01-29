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

import omis.boardhearing.domain.BoardHearingAppearanceCategory;
import omis.boardhearing.domain.BoardHearingCategory;
import omis.paroleeligibility.domain.AppearanceCategory;

/**
 * Board Hearing Appearance Category Implementation.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Jan 24, 2018)
 *@since OMIS 3.0
 *
 */
public class BoardHearingAppearanceCategoryImpl
		implements BoardHearingAppearanceCategory {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private BoardHearingCategory boardHearingCategory;
	
	private AppearanceCategory appearanceCategory;
	
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
	public BoardHearingCategory getBoardHearingCategory() {
		return this.boardHearingCategory;
	}

	/**{@inheritDoc} */
	@Override
	public void setBoardHearingCategory(
			final BoardHearingCategory boardHearingCategory) {
		this.boardHearingCategory = boardHearingCategory;
	}

	/**{@inheritDoc} */
	@Override
	public AppearanceCategory getAppearanceCategory() {
		return this.appearanceCategory;
	}

	/**{@inheritDoc} */
	@Override
	public void setAppearanceCategory(
			final AppearanceCategory appearanceCategory) {
		this.appearanceCategory = appearanceCategory;
	}
	
	/**{@inheritDoc}*/
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof BoardHearingCategory)) {
			return false;
		}
		
		BoardHearingAppearanceCategory that =
				(BoardHearingAppearanceCategory) obj;
		
		if (this.getBoardHearingCategory() == null) {
			throw new IllegalStateException("Board Hearing Category required.");
		}
		if (this.getAppearanceCategory() == null) {
			throw new IllegalStateException("Appearance Category required.");
		}
		
		if (!this.getBoardHearingCategory().equals(
				that.getBoardHearingCategory())) {
			return false;
		}
		if (!this.getAppearanceCategory().equals(
				that.getAppearanceCategory())) {
			return false;
		}
		
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getBoardHearingCategory() == null) {
			throw new IllegalStateException("Board Hearing Category required.");
		}
		if (this.getAppearanceCategory() == null) {
			throw new IllegalStateException("Appearance Category required.");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getBoardHearingCategory().hashCode();
		hashCode = 29 * hashCode + this.getAppearanceCategory().hashCode();
		
		return hashCode;
	}

}
