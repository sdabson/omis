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
package omis.probationparole.report;

import java.io.Serializable;
import java.util.Date;

/**
 * Probation Parole Document Association Summary.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Dec 6, 2018)
 *@since OMIS 3.0
 *
 */
public class ProbationParoleDocumentAssociationSummary implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private final Long probationParoleDocumentAssociationId;
	
	private final Date fileDate;
	
	private final String updateUserFirstName;
	
	private final String updateUserLastName;
	
	private final String documentTitle;
	
	private final String categoryName;

	/**
	 * @param probationParoleDocumentAssociationId - Probation Parole Document
	 * Association ID
	 * @param fileDate - File Date
	 * @param updateUserFirstName - Update User First Name
	 * @param updateUserLastName - Update User Last Name
	 * @param documentTitle - Document Title
	 * @param categoryName - Category Name
	 */
	public ProbationParoleDocumentAssociationSummary(
			final Long probationParoleDocumentAssociationId,
			final Date fileDate, final String updateUserFirstName,
			final String updateUserLastName, final String documentTitle,
			final String categoryName) {
		this.probationParoleDocumentAssociationId =
				probationParoleDocumentAssociationId;
		this.fileDate = fileDate;
		this.updateUserFirstName = updateUserFirstName;
		this.updateUserLastName = updateUserLastName;
		this.documentTitle = documentTitle;
		this.categoryName = categoryName;
	}

	/**
	 * Returns the probationParoleDocumentAssociationId.
	 *
	 * @return probationParoleDocumentAssociationId
	 */
	public Long getProbationParoleDocumentAssociationId() {
		return this.probationParoleDocumentAssociationId;
	}

	/**
	 * Returns the fileDate.
	 *
	 * @return fileDate
	 */
	public Date getFileDate() {
		return this.fileDate;
	}

	/**
	 * Returns the updateUserFirstName.
	 *
	 * @return updateUserFirstName
	 */
	public String getUpdateUserFirstName() {
		return this.updateUserFirstName;
	}
	
	/**
	 * Returns the updateUserLastName.
	 *
	 * @return updateUserLastName
	 */
	public String getUpdateUserLastName() {
		return this.updateUserLastName;
	}

	/**
	 * Returns the documentTitle.
	 *
	 * @return documentTitle
	 */
	public String getDocumentTitle() {
		return this.documentTitle;
	}

	/**
	 * Returns the categoryName.
	 *
	 * @return categoryName
	 */
	public String getCategoryName() {
		return this.categoryName;
	}
}
