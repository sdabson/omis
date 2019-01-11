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
package omis.probationparole.dao;

import omis.dao.GenericDao;
import omis.document.domain.Document;
import omis.probationparole.domain.ProbationParoleDocumentAssociation;

/**
 * Probation Parole Document Association Data Access Object.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Dec 6, 2018)
 *@since OMIS 3.0
 *
 */
public interface ProbationParoleDocumentAssociationDao
		extends GenericDao<ProbationParoleDocumentAssociation> {
	
	/**
	 * Returns the Probation Parole Document Association with the specified
	 * properties.
	 * 
	 * @param document - Document
	 * @return Probation Parole Document Association with the specified
	 * properties.
	 */
	ProbationParoleDocumentAssociation find(Document document);
	
	/**
	 * Returns the Probation Parole Document Association with the specified
	 * properties that is not the given Probation Parole Document Association.
	 * 
	 * @param document - Document
	 * @param probationParoleDocumentAssociationExcluding - Probation Parole
	 * Document Association to exclude
	 * @return Probation Parole Document Association with the specified
	 * properties that is not the given Probation Parole Document Association.
	 */
	ProbationParoleDocumentAssociation findExcluding(Document document, 
			ProbationParoleDocumentAssociation 
				probationParoleDocumentAssociationExcluding);
}
