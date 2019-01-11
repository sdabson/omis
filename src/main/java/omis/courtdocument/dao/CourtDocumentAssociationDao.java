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
package omis.courtdocument.dao;

import java.util.List;

import omis.courtdocument.domain.CourtDocumentAssociation;
import omis.dao.GenericDao;
import omis.document.domain.Document;
import omis.offender.domain.Offender;

/** 
 * Data access object for Court document associations.
 * 
 * @author Ryan Johns
 * @author Josh Divine
 * @version 0.1.1 (Aug 6, 2018)
 * @since OMIS 3.0
 */
public interface CourtDocumentAssociationDao 
	extends GenericDao<CourtDocumentAssociation> {
	
	/** 
	 * Returns a list of court document associations by document.
	 * 
	 * @param document document
	 * @return list of court document association
	 */
	List<CourtDocumentAssociation> findByDocument(
			final Document document);
	
	/** 
	 * Returns a list of court document associations by document excluding the 
	 * specified court document associations.
	 * 
	 * @param document document
	 * @param excluding court document associations
	 * @return list of court document association 
	 */
	List<CourtDocumentAssociation> findByDocumentExcluding(
			final Document document, 
			final CourtDocumentAssociation... excluding);
	
	/** Finds court document association count by offender.
	 * @param offender - offender.
	 * @return count. */
	Integer findCountByOffender(Offender offender);
}