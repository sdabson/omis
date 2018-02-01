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
package omis.unitmanagerreview.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.document.domain.Document;
import omis.unitmanagerreview.domain.UnitManagerReview;
import omis.unitmanagerreview.domain.UnitManagerReviewDocumentAssociation;

/**
 * Data access object for unit manager review document association.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 29, 2018)
 * @since OMIS 3.0
 */
public interface UnitManagerReviewDocumentAssociationDao 
		extends GenericDao<UnitManagerReviewDocumentAssociation> {

	/**
	 * Returns the unit manager review document association for the specified 
	 * document and unit manager review.
	 * 
	 * @param document document
	 * @param unitManagerReview unit manager review
	 * @return unit manager review document association
	 */
	UnitManagerReviewDocumentAssociation find(Document document, 
			UnitManagerReview unitManagerReview);
	
	/**
	 * Returns a list of unit manager review document associations for the 
	 * specified unit manager review.
	 * 
	 * @param unitManagerReview unit manager review
	 * @return list of unit manager review document associations
	 */
	List<UnitManagerReviewDocumentAssociation> findByUnitManagerReview(
			UnitManagerReview unitManagerReview);
}