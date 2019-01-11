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
package omis.presentenceinvestigation.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.docket.domain.Docket;
import omis.presentenceinvestigation.domain.PresentenceInvestigationDocketAssociation;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;

/**
 * Implementation of presentence investigation docket association.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Oct 25, 2018)
 * @since OMIS 3.0
 */
public class PresentenceInvestigationDocketAssociationImpl
		implements PresentenceInvestigationDocketAssociation {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Docket docket;
	
	private PresentenceInvestigationRequest presentenceInvestigationRequest;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	/**
	 * Instantiates a default implementation of presentence investigation docket 
	 * association.
	 */
	public PresentenceInvestigationDocketAssociationImpl() {
		// Default constructor
	}
	
	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return id;
	}

	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public Docket getDocket() {
		return docket;
	}

	/** {@inheritDoc} */
	@Override
	public void setDocket(final Docket docket) {
		this.docket = docket;
	}

	/** {@inheritDoc} */
	@Override
	public PresentenceInvestigationRequest getPresentenceInvestigationRequest() {
		return presentenceInvestigationRequest;
	}

	/** {@inheritDoc} */
	@Override
	public void setPresentenceInvestigationRequest(
			final PresentenceInvestigationRequest 
				presentenceInvestigationRequest) {
		this.presentenceInvestigationRequest = presentenceInvestigationRequest;
	}

	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return creationSignature;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(
			final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setUpdateSignature(final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		} 
		if (!(obj instanceof PresentenceInvestigationDocketAssociation)) {
			return false;
		}
		PresentenceInvestigationDocketAssociation that = 
				(PresentenceInvestigationDocketAssociation) obj;
		if (this.getDocket() == null) {
			throw new IllegalStateException("Docket required.");
		}
		if (!this.getDocket().equals(that.getDocket())) {
			return false;
		}
		if (this.getPresentenceInvestigationRequest() == null) {
			throw new IllegalStateException(
					"Presentence investigation request required.");
		}
		if (!this.getPresentenceInvestigationRequest().equals(
				that.getPresentenceInvestigationRequest())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getDocket() == null) {
			throw new IllegalStateException("Docket required.");
		}
		if (this.getPresentenceInvestigationRequest() == null) {
			throw new IllegalStateException(
					"Presentence investigation request required.");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getDocket().hashCode();
		hashCode = 29 * hashCode + 
				this.getPresentenceInvestigationRequest().hashCode();
		return hashCode;
	}
}