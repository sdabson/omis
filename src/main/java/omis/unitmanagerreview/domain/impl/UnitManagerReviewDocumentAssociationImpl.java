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
package omis.unitmanagerreview.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.document.domain.Document;
import omis.unitmanagerreview.domain.UnitManagerReview;
import omis.unitmanagerreview.domain.UnitManagerReviewDocumentAssociation;

/**
 * Implementation of unit manager review document association.
 *  
 * @author Josh Divine
 * @version 0.1.0 (Jan 29, 2018)
 * @since OMIS 3.0
 */
public class UnitManagerReviewDocumentAssociationImpl 
		implements UnitManagerReviewDocumentAssociation {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Document document;
	
	private UnitManagerReview unitManagerReview;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	/** 
	 * Instantiates an implementation of unit manager review document 
	 * association. 
	 */
	public UnitManagerReviewDocumentAssociationImpl() {
		// Default constructor.
	}
	
	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return id;
	}

	/** {@inheritDoc} */
	@Override
	public void setDocument(final Document document) {
		this.document = document;
	}

	/** {@inheritDoc} */
	@Override
	public Document getDocument() {
		return document;
	}

	/** {@inheritDoc} */
	@Override
	public void setUnitManagerReview(
			final UnitManagerReview unitManagerReview) {
		this.unitManagerReview = unitManagerReview;
	}

	/** {@inheritDoc} */
	@Override
	public UnitManagerReview getUnitManagerReview() {
		return unitManagerReview;
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
		return creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setUpdateSignature(final UpdateSignature updateSignature) {
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
		if (!(obj instanceof UnitManagerReviewDocumentAssociation)) {
			return false;
		}
		UnitManagerReviewDocumentAssociation that = 
				(UnitManagerReviewDocumentAssociation) obj;
		if (this.getDocument() == null) {
			throw new IllegalStateException("Document required");
		}
		if (!this.getDocument().equals(that.getDocument())) {
			return false;
		}
		if (this.getUnitManagerReview() == null) {
			throw new IllegalStateException("Unit manager review required");
		}
		if (!this.getUnitManagerReview().equals(that.getUnitManagerReview())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getDocument() == null) {
			throw new IllegalStateException("Document required");
		}
		if (this.getUnitManagerReview() == null) {
			throw new IllegalStateException("Unit manager review required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getDocument().hashCode();
		hashCode = 29 * hashCode + this.getUnitManagerReview().hashCode();
		
		return hashCode;
	}
}