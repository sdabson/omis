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
package omis.courtdocument.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.docket.domain.Docket;
import omis.document.domain.DocumentAssociable;
import omis.offender.domain.Offender;

/** 
 * Court document association.
 * 
 * @author Ryan Johns
 * @author Josh Divine
 * @version 0.1.1 (Aug 6, 2018)
 * @since OMIS 3.0 
 */
public interface CourtDocumentAssociation 
	extends DocumentAssociable, Creatable, Updatable {
	
	/** 
	 * Gets the id.
	 * 
	 * @return id
	 */
	Long getId();
	
	/** 
	 * Sets the id.
	 * 
	 * @param id id
	 */
	void setId(Long id);
	
	/** 
	 * Gets the docket.
	 * 
	 * @return docket
	 */
	Docket getDocket();
	
	/** 
	 * Sets the docket.
	 * 
	 * @param docket docket
	 */
	void setDocket(Docket docket);
	
	/**
	 * Gets the offender.
	 * 
	 * @return offender
	 */
	Offender getOffender();
	
	/**
	 * Sets the offender.
	 * 
	 * @param offender offender
	 */
	void setOffender(Offender offender);
	
	/** 
	 * Gets the document date.
	 * 
	 * @return document date
	 */
	Date getDate();

	/** 
	 * Sets the document date.
	 * 
	 * @param date document date
	 */
	void setDate(Date date);
	
	/** 
	 * Gets the court document category.
	 * 
	 * @return category court document category
	 */
	CourtDocumentCategory getCategory();
	
	/** 
	 * Sets the court document category.
	 * 
	 * @param category court document category
	 */
	void setCategory(CourtDocumentCategory category);
}