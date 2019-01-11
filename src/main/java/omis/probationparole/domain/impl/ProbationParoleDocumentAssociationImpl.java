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
package omis.probationparole.domain.impl;

import java.io.Serializable;
import java.util.Date;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.document.domain.Document;
import omis.offender.domain.Offender;
import omis.probationparole.domain.ProbationParoleDocumentAssociation;
import omis.probationparole.domain.ProbationParoleDocumentCategory;
import omis.util.EqualityChecker;

/**
 * Probation Parole Document Association Implementation.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Dec 6, 2018)
 *@since OMIS 3.0
 *
 */
public class ProbationParoleDocumentAssociationImpl
		implements ProbationParoleDocumentAssociation {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Document document;
	
	private Offender offender;
	
	private Date date;
	
	private ProbationParoleDocumentCategory category;
	
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
	public ProbationParoleDocumentCategory getCategory() {
		return this.category;
	}

	/** {@inheritDoc} */
	@Override
	public void setCategory(final ProbationParoleDocumentCategory category) {
		this.category = category;
	}

	/**{@inheritDoc}*/
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ProbationParoleDocumentAssociation)) {
			return false;
		}
		
		ProbationParoleDocumentAssociation that =
				(ProbationParoleDocumentAssociation) obj;
		
		if (this.getDocument() == null) {
			throw new IllegalStateException("Document required.");
		}
		
		return EqualityChecker.create(Serializable.class)
				.add(this.getDocument(), that.getDocument())
				.check();
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getDocument() == null) {
			throw new IllegalStateException("Document required.");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getDocument().hashCode();
		
		return hashCode;
	}
}
