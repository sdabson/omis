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
package omis.earlyreleasetracking.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.document.domain.Document;
import omis.earlyreleasetracking.domain.EarlyReleaseRequest;
import omis.earlyreleasetracking.domain.EarlyReleaseRequestDocumentAssociation;

/**
 * Early Release Request Document Association Implementation.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Feb 7, 2019)
 *@since OMIS 3.0
 *
 */
public class EarlyReleaseRequestDocumentAssociationImpl
		implements EarlyReleaseRequestDocumentAssociation {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private EarlyReleaseRequest earlyReleaseRequest;
	
	private Document document;
	
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
	
	/** {@inheritDoc} */
	@Override
	public EarlyReleaseRequest getEarlyReleaseRequest() {
		return this.earlyReleaseRequest;
	}

	/** {@inheritDoc} */
	@Override
	public void setEarlyReleaseRequest(
			final EarlyReleaseRequest earlyReleaseRequest) {
		this.earlyReleaseRequest = earlyReleaseRequest;
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
	
	/**{@inheritDoc}*/
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof EarlyReleaseRequestDocumentAssociation)) {
			return false;
		}
		
		EarlyReleaseRequestDocumentAssociation that =
				(EarlyReleaseRequestDocumentAssociation) obj;
		
		if (this.getEarlyReleaseRequest() == null) {
			throw new IllegalStateException("EarlyReleaseRequest required.");
		}
		if (this.getDocument() == null) {
			throw new IllegalStateException("Document required.");
		}
		
		if (!this.getEarlyReleaseRequest().equals(
				that.getEarlyReleaseRequest())) {
			return false;
		}
		if (!this.getDocument().equals(that.getDocument())) {
			return false;
		}
		
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getEarlyReleaseRequest() == null) {
			throw new IllegalStateException("EarlyReleaseRequest required.");
		}
		if (this.getDocument() == null) {
			throw new IllegalStateException("Document required.");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getEarlyReleaseRequest().hashCode();
		hashCode = 29 * hashCode + this.getDocument().hashCode();
		
		return hashCode;
	}
}
