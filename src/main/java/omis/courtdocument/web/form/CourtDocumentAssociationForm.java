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
package omis.courtdocument.web.form;

import java.util.Date;
import java.util.List;

import omis.courtdocument.domain.CourtDocumentCategory;
import omis.docket.domain.Docket;
import omis.document.web.form.DocumentForm;
import omis.document.web.form.DocumentTagItem;

/** 
 * Court document association form.
 * 
 * @author Ryan Johns
 * @author Josh Divine
 * @version 0.1.1 (Aug 6, 2018)
 * @since OMIS 3.0
 */
public class CourtDocumentAssociationForm implements DocumentForm {
	
	private static final long serialVersionUID = 1L;
	
	private byte[] data;
	
	private String title;
	
	private Docket docket;
	
	private Date date;
	
	private CourtDocumentCategory courtDocumentCategory;
	
	private String fileExtension;
	
	private List<DocumentTagItem> documentTagItems;
	
	/** Constructor. */
	public CourtDocumentAssociationForm() { 
		// Default constructor
	}
	
	/** 
	 * Returns the docket.
	 * 
	 * @return docket
	 */
	public Docket getDocket() {
		return this.docket; 
	}
	
	/**
	 * Sets the docket.
	 *  
	 * @param docket docket
	 */
	public void setDocket(final Docket docket) {
		this.docket= docket;
	}
	
	/** 
	 * Returns the court document category.
	 * 
	 * @return court document category
	 */
	public CourtDocumentCategory getCourtDocumentCategory() { 
		return this.courtDocumentCategory;
	}
	
	/** 
	 * Sets the court case document category.
	 * 
	 * @param courtDocumentCategory court document category
	 */
	public void setCourtDocumentCategory(
			final CourtDocumentCategory courtDocumentCategory) {
		this.courtDocumentCategory = courtDocumentCategory;
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
	
	/** {@inheritDoc} */
	@Override
	public byte[] getData() {
		return this.data;
	}

	/** {@inheritDoc} */
	@Override
	public void setData(final byte[] data) {
		this.data = data;
	}
	
	/** {@inheritDoc} */
	@Override
	public String getTitle() {
		return this.title;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setTitle(final String title) {
		this.title = title;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<DocumentTagItem> getDocumentTagItems() {
		return this.documentTagItems; 
	}
	
	/** {@inheritDoc} */
	@Override
	public void setDocumentTagItems(
			final List<DocumentTagItem> documentTagItems) {
		this.documentTagItems = documentTagItems;
	}
	
	/** {@inheritDoc} */
	@Override
	public String getFileExtension() {
		return this.fileExtension;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setFileExtension(final String fileExtension) {
		this.fileExtension = fileExtension;
	}
}