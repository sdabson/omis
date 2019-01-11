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

import omis.courtdocument.domain.CourtDocumentCategory;
import omis.dao.GenericDao;

/** 
 * Data access object for court document category.
 * 
 * @author Ryan Johns
 * @author Josh Divine
 * @version 0.1.2 (Sep 25, 2018)
 * @since OMIS 3.0
 */
public interface CourtDocumentCategoryDao 
	extends GenericDao<CourtDocumentCategory> {

	/**
	 * Returns the court document category with the specified name.
	 * 
	 * @param name name
	 * @return court document category
	 */
	CourtDocumentCategory find(String name);
	
	/**
	 * Returns the court document category with the specified name excluding the 
	 * specified court document category.
	 * 
	 * @param name name
	 * @param excludedCourtDocumentCategory excluded court document category
	 * @return court document category
	 */
	CourtDocumentCategory findExcluding(String name, 
			CourtDocumentCategory excludedCourtDocumentCategory);
	
	/**
	 * Returns a list of valid court document categories.
	 * 
	 * @return list of valid court document categories
	 */
	List<CourtDocumentCategory> findValid();
}