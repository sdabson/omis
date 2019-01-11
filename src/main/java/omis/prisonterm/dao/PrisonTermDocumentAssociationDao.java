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
package omis.prisonterm.dao;

import omis.dao.GenericDao;
import omis.document.domain.Document;
import omis.prisonterm.domain.PrisonTerm;
import omis.prisonterm.domain.PrisonTermDocumentAssociation;

/**
 * Prison Term Document Association Data Access Object.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Dec 18, 2018)
 *@since OMIS 3.0
 *
 */
public interface PrisonTermDocumentAssociationDao
		extends GenericDao<PrisonTermDocumentAssociation> {

	/**
	 * Returns a Prison Term Document Association with the specified properties.
	 * 
	 * @param document Document
	 * @param prisonTerm Prison Term
	 * @return Prison Term Document Association with the specified properties.
	 */
	PrisonTermDocumentAssociation find(
			Document document, PrisonTerm prisonTerm);
	
	/**
	 * Returns a Prison Term Document Association with the specified properties
	 * excluding the given Prison Term Document Association.
	 * 
	 * @param document Document
	 * @param prisonTerm Prison Term
	 * @param prisonTermDocumentAssociationExcluding Prison Term Document
	 * Association
	 * @return Prison Term Document Association with the specified properties
	 * excluding the given Prison Term Document Association.
	 */
	PrisonTermDocumentAssociation findExcluding(
			Document document, PrisonTerm prisonTerm,
			PrisonTermDocumentAssociation
				prisonTermDocumentAssociationExcluding);
}
