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
package omis.courtdocument.service.delegate;

import java.util.List;

import omis.courtdocument.dao.CourtDocumentCategoryDao;
import omis.courtdocument.domain.CourtDocumentCategory;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;

/** 
 * Delegate for court document category service related operations.
 * 
 * @author Ryan Johns
 * @author Josh Divine
 * @version 0.1.2 (Sep 25, 2018)
 * @since OMIS 3.0
 */
public class CourtDocumentCategoryDelegate {
	
	private final CourtDocumentCategoryDao courtDocumentCategoryDao;
	
	private final InstanceFactory<CourtDocumentCategory> 
			courtDocumentCategoryInstanceFactory;
	
	/** Constructor.
	 * @param courtDocumentCategoryDao - court document category dao. */
	public CourtDocumentCategoryDelegate(
			final CourtDocumentCategoryDao courtDocumentCategoryDao,
			final InstanceFactory<CourtDocumentCategory> 
					courtDocumentCategoryInstanceFactory) {
		this.courtDocumentCategoryDao = courtDocumentCategoryDao;
		this.courtDocumentCategoryInstanceFactory = 
				courtDocumentCategoryInstanceFactory;
	}
	
	/** 
	 * Returns a list of court document categories.
	 * 
	 * @return list of court document categories
	 */
	public List<CourtDocumentCategory> findAll() {
		return this.courtDocumentCategoryDao.findValid();
	}

	/**
	 * Creates a new court document category.
	 * 
	 * @param name name
	 * @param valid valid
	 * @return court document category
	 */
	public CourtDocumentCategory create(final String name, final Boolean valid) 
			throws DuplicateEntityFoundException {
		if (this.courtDocumentCategoryDao.find(name) != null) {
			throw new DuplicateEntityFoundException(
					"Court document category already exists.");
		}
		CourtDocumentCategory courtDocumentCategory = this
				.courtDocumentCategoryInstanceFactory.createInstance();
		populateCourtDocumentCategory(courtDocumentCategory, name, valid);
		return this.courtDocumentCategoryDao.makePersistent(
				courtDocumentCategory);
	}

	/**
	 * Updates an existing court document category.
	 * 
	 * @param courtDocumentCategory court document category
	 * @param name name
	 * @param valid valid
	 * @return court document category
	 */
	public CourtDocumentCategory update(
			final CourtDocumentCategory courtDocumentCategory, 
			final String name, final Boolean valid) 
			throws DuplicateEntityFoundException {
		if (this.courtDocumentCategoryDao.findExcluding(name, 
				courtDocumentCategory) != null) {
			throw new DuplicateEntityFoundException(
					"Court document category already exists.");
		}
		populateCourtDocumentCategory(courtDocumentCategory, name, valid);
		return this.courtDocumentCategoryDao.makePersistent(
				courtDocumentCategory);
	}
	
	/**
	 * Removes the specified court document category.
	 * 
	 * @param courtDocumentCategory court document category
	 */
	public void remove(final CourtDocumentCategory courtDocumentCategory) {
		this.courtDocumentCategoryDao.makeTransient(courtDocumentCategory);
	}

	// Populates a court document category
	private void populateCourtDocumentCategory(
			final CourtDocumentCategory courtDocumentCategory,
			final String name, final Boolean valid) {
		courtDocumentCategory.setName(name);
		courtDocumentCategory.setValid(valid);
	}
}