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
package omis.probationparole.web.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import omis.document.domain.Document;
import omis.document.web.form.DocumentTagItem;
import omis.probationparole.domain.ProbationParoleDocumentCategory;

/**
 * Probation Parole Document Form.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Dec 7, 2018)
 *@since OMIS 3.0
 *
 */
public class ProbationParoleDocumentForm implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private ProbationParoleDocumentCategory category;
	
	private Document document;
	
	private String title;
	
	private List<DocumentTagItem> documentTagItems =
			new ArrayList<DocumentTagItem>();
	
	private Date date;
	
	private String fileExtension;
	
	private byte[] data;
	
	/**
	 * 
	 */
	public ProbationParoleDocumentForm() {
	}

	/**
	 * Returns the category.
	 *
	 * @return category
	 */
	public ProbationParoleDocumentCategory getCategory() {
		return this.category;
	}

	/**
	 * Sets the category.
	 *
	 * @param category - category
	 */
	public void setCategory(final ProbationParoleDocumentCategory category) {
		this.category = category;
	}

	/**
	 * Returns the document.
	 *
	 * @return document
	 */
	public Document getDocument() {
		return this.document;
	}

	/**
	 * Sets the document.
	 *
	 * @param document - document
	 */
	public void setDocument(final Document document) {
		this.document = document;
	}

	/**
	 * Returns the title.
	 *
	 * @return title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title - title
	 */
	public void setTitle(final String title) {
		this.title = title;
	}

	/**
	 * Returns the documentTagItems.
	 *
	 * @return documentTagItems
	 */
	public List<DocumentTagItem> getDocumentTagItems() {
		return this.documentTagItems;
	}

	/**
	 * Sets the documentTagItems.
	 *
	 * @param documentTagItems - document tag items
	 */
	public void setDocumentTagItems(
			final List<DocumentTagItem> documentTagItems) {
		this.documentTagItems = documentTagItems;
	}

	/**
	 * Returns the date.
	 *
	 * @return date
	 */
	public Date getDate() {
		return this.date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date - date
	 */
	public void setDate(final Date date) {
		this.date = date;
	}

	/**
	 * Returns the fileExtension.
	 *
	 * @return fileExtension
	 */
	public String getFileExtension() {
		return this.fileExtension;
	}

	/**
	 * Sets the fileExtension.
	 *
	 * @param fileExtension - file extension
	 */
	public void setFileExtension(final String fileExtension) {
		this.fileExtension = fileExtension;
	}

	/**
	 * Returns the data.
	 *
	 * @return data
	 */
	public byte[] getData() {
		return this.data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data - data
	 */
	public void setData(final byte[] data) {
		this.data = data;
	}
}
