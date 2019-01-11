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
package omis.courtdocument.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.courtdocument.domain.CourtDocumentAssociation;
import omis.courtdocument.domain.CourtDocumentCategory;
import omis.docket.domain.Docket;
import omis.document.domain.Document;
import omis.offender.domain.Offender;

/** 
 * Implementation of court document association.
 * 
 * @author Ryan Johns
 * @author Josh Divine
 * @version 0.1.1 (Aug 6, 2018)
 * @since OMIS 3.0
 */
public class CourtDocumentAssociationImpl 
	implements CourtDocumentAssociation {
	
	private static final long serialVersionUID = 1L;
	
	private static final String DATE_REQUIRED_MSG = "Date is required";
	
	private static final String CATEGORY_REQUIRED_MSG = "Category is required";
	
	private static final String DOCUMENT_REQUIRED_MSG = "Document is required";
	
	private Long id;	

	private Docket docket;
	
	private Offender offender;
	
	private Date date;
	
	private CourtDocumentCategory courtDocumentCategory;
	
	private Document document;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;

	/** Constructor. */
	public CourtDocumentAssociationImpl() { /* Do nothing. */ }

	/** {@inheritDoc} */
	@Override
	public Long getId() { 
		return this.id;
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
	public Offender getOffender() {
		return offender;
	}

	/** {@inheritDoc} */
	@Override
	public void setOffender(final Offender offender) {
		this.offender = offender;
	}
	
	/** {@inheritDoc} */
	@Override
	public Date getDate() { 
		return this.date; 
	}
	
	/** {@inheritDoc} */
	@Override
	public void setDate(final Date date) {
		this.date = date; 
	}
	
	/** {@inheritDoc} */
	@Override
	public CourtDocumentCategory getCategory() {
		return this.courtDocumentCategory;
	}

	/** {@inheritDoc} */
	@Override
	public void setCategory(final CourtDocumentCategory category) { 
		this.courtDocumentCategory = category; 
	}
	
	/** {@inheritDoc} */
	@Override
	public Document getDocument() {
		return this.document; 
	}

	/** {@inheritDoc} */
	@Override
	public void setDocument(final Document document) {
		this.document = document;
	}
	
	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature; 
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
		return this.updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setUpdateSignature(final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		this.checkState();
		return 29 * this.getCategory().hashCode()
				+ 29 * this.getDate().hashCode()
				+ 29 * this.getDocument().hashCode();
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		} 
		if (!(obj instanceof CourtDocumentAssociation)) {
			return false;
		}
		this.checkState();
		CourtDocumentAssociation that = 
					(CourtDocumentAssociation) obj;
		if (this.getCategory().equals(that.getCategory())
					&& this.getDate().equals(that.getDate())
					&& this.getDocument().equals(that.getDocument())) {
			return false;
		}
		return true;
	}
	
	/* Checks state. 
	 * @throws IllegalStateException - when required attributes are accounted
	 * for.*/
	private void checkState() {
		if (this.getDate() == null) {
			throw new IllegalStateException(DATE_REQUIRED_MSG);
		}
		
		if (this.getCategory() == null) {
			throw new IllegalStateException(CATEGORY_REQUIRED_MSG);
		}
		
		if (this.getDocument() == null) {
			throw new IllegalStateException(DOCUMENT_REQUIRED_MSG);
		}
	}
}