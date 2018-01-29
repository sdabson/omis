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
package omis.hearinganalysis.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.hearinganalysis.domain.HearingAnalysis;
import omis.hearinganalysis.domain.HearingAnalysisCategory;
import omis.paroleboarditinerary.domain.BoardAttendee;
import omis.paroleboarditinerary.domain.BoardMeetingSite;
import omis.paroleeligibility.domain.ParoleEligibility;

/**
 * Implementation of hearing analysis.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Dec 18, 2017)
 * @since OMIS 3.0
 */
public class HearingAnalysisImpl implements HearingAnalysis {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	private ParoleEligibility eligibility;
	
	private BoardMeetingSite boardMeetingSite;
	
	private HearingAnalysisCategory category;
	
	private BoardAttendee analyst;
	
	/** 
	 * Instantiates an implementation of hearing analysis. 
	 */
	public HearingAnalysisImpl() {
		// Default constructor.
	}
	
	/** {@inheritDoc} */
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return id;
	}

	/** {@inheritDoc} */
	@Override
	public void setEligibility(ParoleEligibility eligibility) {
		this.eligibility = eligibility;
	}

	/** {@inheritDoc} */
	@Override
	public ParoleEligibility getEligibility() {
		return eligibility;
	}

	/** {@inheritDoc} */
	@Override
	public void setBoardMeetingSite(BoardMeetingSite boardMeetingSite) {
		this.boardMeetingSite = boardMeetingSite;
	}

	/** {@inheritDoc} */
	@Override
	public BoardMeetingSite getBoardMeetingSite() {
		return boardMeetingSite;
	}

	/** {@inheritDoc} */
	@Override
	public void setCategory(HearingAnalysisCategory category) {
		this.category = category;
	}

	/** {@inheritDoc} */
	@Override
	public HearingAnalysisCategory getCategory() {
		return category;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setAnalyst(BoardAttendee analyst) {
		this.analyst = analyst;
	}

	/** {@inheritDoc} */
	@Override
	public BoardAttendee getAnalyst() {
		return analyst;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setUpdateSignature(UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof HearingAnalysis)) {
			return false;
		}
		HearingAnalysis that = (HearingAnalysis) obj;
		if (this.getEligibility() == null) {
			throw new IllegalStateException("Parole eligibility required");
		}
		if (!this.getEligibility().equals(that.getEligibility())) {
			return false;
		}
		if (this.getCategory() == null) {
			throw new IllegalStateException("Catetgory required");
		}
		if (!this.getCategory().equals(that.getCategory())) {
			return false;
		}
		if (this.getBoardMeetingSite() == null) { 
			if (that.getBoardMeetingSite() != null) {
				return false;
			}
		} else {
			if (!this.getBoardMeetingSite().equals(that.getBoardMeetingSite())) {
				return false;
			}
		}
		if (this.getAnalyst() == null) { 
			if (that.getAnalyst() != null) {
				return false;
			}
		} else {
			if (!this.getAnalyst().equals(that.getAnalyst())) {
				return false;
			}
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getEligibility() == null) {
			throw new IllegalStateException("Parole eligibility required");
		}
		if (this.getCategory() == null) {
			throw new IllegalStateException("Catetgory required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getEligibility().hashCode();
		hashCode = 29 * hashCode + this.getCategory().hashCode();
		if (this.getBoardMeetingSite() != null) {
			hashCode = 29 * hashCode + this.getBoardMeetingSite().hashCode();
		}
		if (this.getAnalyst() != null) {
			hashCode = 29 * hashCode + this.getAnalyst().hashCode();
		}
		return hashCode;
	}

}
