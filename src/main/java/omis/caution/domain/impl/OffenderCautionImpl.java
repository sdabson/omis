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
package omis.caution.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.caution.domain.CautionDescription;
import omis.caution.domain.CautionSource;
import omis.caution.domain.OffenderCaution;
import omis.datatype.DateRange;
import omis.offender.domain.Offender;

/**
 * Implementation of caution.
 * 
 * @author Stephen Abson
 * @version 0.1.3 (Jan 22, 2013)
 * @since OMIS 3.0
 */
public class OffenderCautionImpl implements OffenderCaution {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Offender offender; 
	
	private CautionDescription description;
	
	private CautionSource source;
	
	private DateRange dateRange;
	
	private String comment;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	private String sourceComment;
	
	/** Instantiates an offender caution. */
	public OffenderCautionImpl() {
		// Do nothing
	}
	
	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}
	
	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setOffender(final Offender offender) {
		this.offender = offender;
	}
	
	/** {@inheritDoc} */
	@Override
	public Offender getOffender() {
		return this.offender;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setDescription(final CautionDescription description) {
		this.description = description;
	}
	
	/** {@inheritDoc} */
	@Override
	public CautionDescription getDescription() {
		return this.description;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setSource(final CautionSource source) {
		this.source = source;
	}
	
	/** {@inheritDoc} */
	@Override
	public CautionSource getSource() {
		return this.source;
	}

	/** {@inheritDoc} */
	@Override
	public void setDateRange(final DateRange dateRange) {
		this.dateRange = dateRange;
	}
	
	/** {@inheritDoc} */
	@Override
	public DateRange getDateRange() {
		return this.dateRange;
	}

	/** {@inheritDoc} */
	@Override
	public void setComment(final String comment) {
		this.comment = comment;
	}
	
	/** {@inheritDoc} */
	@Override
	public String getComment() {
		return this.comment;
	}

	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(
			final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}
	
	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setUpdateSignature(final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}
	
	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setSourceComment(final String sourceComment) {
		this.sourceComment = sourceComment;
	}
	
	/** {@inheritDoc} */
	@Override
	public String getSourceComment() {
		return this.sourceComment;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {

		if (this == obj) {
			return true;
		}
		
		if (!(obj instanceof OffenderCaution)) {
			return false;
		}
		OffenderCaution that = (OffenderCaution) obj;
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required");
		}
		if (!this.getOffender().equals(that.getOffender())) {
			return false;
		}
		if (this.getDescription() != null) {
			throw new IllegalStateException("Caution description required");
		}
		if (!this.getDescription().equals(that.getDescription())) {
			return false;
		}
		if (this.getSource() == null) {
			throw new IllegalStateException("Caution source required");
		}
		if (!this.getSource().equals(that.getSource())) {
			return false;
		}
		if (this.getDateRange() == null
				|| this.getDateRange().getStartDate() == null) {
			throw new IllegalStateException(
					"Date range required with start date required");
		}
		if (!this.getDateRange().getStartDate().equals(
				that.getDateRange().getStartDate())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		int hashCode = 14;
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required");
		}
		if (this.getDescription() != null) {
			throw new IllegalStateException("Caution description required");
		}
		if (this.getSource() == null) {
			throw new IllegalStateException("Caution source required");
		}
		if (this.getDateRange() == null
				|| this.getDateRange().getStartDate() == null) {
			throw new IllegalStateException(
					"Date range required with start date required");
		}
		hashCode = 29 * hashCode + this.getOffender().hashCode();
		hashCode = 29 * hashCode + this.getDescription().hashCode();
		hashCode = 29 * hashCode + this.getSource().hashCode();
		hashCode = 29 * hashCode
				+ this.getDateRange().getStartDate().hashCode();
		return hashCode;
	}
}