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
package omis.courtdocument.report;

import java.io.Serializable;
import java.util.Date;

/** 
 * Report summary for court document association.
 * 
 * @author Ryan Johns
 * @author Josh Divine
 * @version 0.1.1 (Aug 6, 2018)
 * @since OMIS 3.0
 */ 
public class CourtDocumentAssociationSummary implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private final Long courtDocumentAssociationId;
	
	private final Date fileDate;
	
	private final String docketName;
	
	private final String courtName;
	
	private final String categoryName;
	
	private final String documentTitle;
	
	private final String updateUserFirstName;
	
	private final String updateUserLastName;
	
	private final String updateUserMiddleName;
	
	private final Date updateDate;
	
	private final String offenderFirstName;
	
	private final String offenderMiddleName;
	
	private final String offenderLastName;
	
	private final String offenderNumber;
	
	/** 
	 * Constructor.
	 * 
	 * @param courtDocumentAssociationId court document association id
	 * @param fileDate file date
	 * @param docketName docket name
	 * @param courtName court name
	 * @param categoryName category name
	 * @param documentTitle document title
	 * @param updateUserFirstName update user first name
	 * @param updateUserLastName update user last name
	 * @param updateUserMiddleName update user middle name
	 * @param updateDate update date
	 * @param offenderFirstName offender first name
	 * @param offenderMiddleName offender middle name
	 * @param offenderLastName offender last name
	 * @param offenderNumber offender number
	 */
	public CourtDocumentAssociationSummary( 
			final Long courtDocumentAssociationId, final Date fileDate, 
			final String docketName, final String courtName, 
			final String categoryName, final String documentTitle,
			final String updateUserFirstName, final String updateUserLastName,
			final String updateUserMiddleName, final Date updateDate,
			final String offenderFirstName, final String offenderMiddleName,
			final String offenderLastName, final Integer offenderNumber) {
		this.courtDocumentAssociationId = courtDocumentAssociationId;
		this.fileDate = fileDate;
		this.docketName = docketName;
		this.courtName = courtName;
		this.categoryName = categoryName;
		this.documentTitle = documentTitle;
		this.updateUserFirstName = updateUserFirstName;
		this.updateUserLastName = updateUserLastName;
		this.updateUserMiddleName = updateUserMiddleName;
		this.updateDate = updateDate;
		this.offenderFirstName = offenderFirstName;
		this.offenderLastName = offenderLastName;
		this.offenderMiddleName = offenderMiddleName;
		this.offenderNumber = offenderNumber.toString();
	}
	
	/** 
	 * Returns the court document association id.
	 * 
	 * @return court document association id
	 */
	public Long getCourtDocumentAssociationId() { 
		return this.courtDocumentAssociationId; 
	}
	
	/** 
	 * Returns the file date.
	 *  
	 * @return file date
	 */
	public Date getFileDate() { 
		return this.fileDate; 
	}
	
	/** 
	 * Returns the docket name.
	 * @return docket name
	 */
	public String getDocketName() {
		return this.docketName; 
	}
	
	/** 
	 * Returns the court name.
	 * 
	 * @return court name
	 */
	public String getCourtName() { 
		return this.courtName; 
	}
	
	/** 
	 * Returns the category name.
	 * 
	 * @return category name
	 */
	public String getCategoryName() { 
		return this.categoryName; 
	}
	
	/** 
	 * Returns the document title.
	 * 
	 * @return document title
	 */
	public String getDocumentTitle() { 
		return this.documentTitle; 
	}
	
	/** 
	 * Returns the update user first name.
	 *  
	 * @return update user first name
	 */
	public String getUpdateUserFirstName() { 
		return this.updateUserFirstName; 
	}
	
	/** 
	 * Returns the update user last name.
	 * 
	 * @return update user last name
	 */
	public String getUpdateUserLastName() { 
		return this.updateUserLastName; 
	}
	
	/** 
	 * Returns the update user middle name.
	 * 
	 * @return update user middle name
	 */
	public String getUpdateUserMiddleName() { 
		return this.updateUserMiddleName; 
	}
	
	/** 
	 * Returns the update date.
	 * 
	 * @return update date
	 */
	public Date getUpdateDate() { 
		return this.updateDate; 
	}
	
	/**
	 * Returns the offender first name.
	 *
	 * @return offender first name
	 */
	public String getOffenderFirstName() {
		return offenderFirstName;
	}

	/**
	 * Returns the offender middle name.
	 *
	 * @return offender middle name
	 */
	public String getOffenderMiddleName() {
		return offenderMiddleName;
	}

	/**
	 * Returns the offender last name.
	 *
	 * @return offender last name
	 */
	public String getOffenderLastName() {
		return offenderLastName;
	}

	/**
	 * Returns the offender number.
	 *
	 * @return offender number
	 */
	public String getOffenderNumber() {
		return offenderNumber;
	}
}