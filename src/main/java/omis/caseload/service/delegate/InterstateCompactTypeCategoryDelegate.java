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
package omis.caseload.service.delegate;

import java.util.List;

import omis.caseload.dao.InterstateCompactTypeCategoryDao;
import omis.caseload.domain.InterstateCompactTypeCategory;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;

/**
 * Interstate compact type category delegate.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Sep 7, 2018)
 * @since OMIS 3.0
 */
public class InterstateCompactTypeCategoryDelegate {

	/* DAOs */
	private final InterstateCompactTypeCategoryDao 
			interstateCompactTypeCategoryDao;
	
	/* Instance factory. */
	private final InstanceFactory<InterstateCompactTypeCategory> 
			interstateCompactTypeCategoryInstanceFactory;
	
	/**
	 * Instantiates an implementation of interstate compact type category 
	 * delegate with the specified data access object and instance factory.
	 * 
	 * @param interstateCompactTypeCategoryDao interstate compact end 
	 * reason category data access object
	 * @param interstateCompactTypeCategoryInstanceFactory interstate 
	 * compact type category instance factory
	 */
	public InterstateCompactTypeCategoryDelegate(
			final InterstateCompactTypeCategoryDao 
					interstateCompactTypeCategoryDao,
			final InstanceFactory<InterstateCompactTypeCategory> 
					interstateCompactTypeCategoryInstanceFactory) {
		this.interstateCompactTypeCategoryDao = 
				interstateCompactTypeCategoryDao;
		this.interstateCompactTypeCategoryInstanceFactory = 
				interstateCompactTypeCategoryInstanceFactory;
	}
	
	/**
	 * Creates a new interstate compact type category.
	 * 
	 * @param name name
	 * @param valid valid
	 * @return interstate compact type category
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public InterstateCompactTypeCategory create(
			final String name, final Boolean valid) 
					throws DuplicateEntityFoundException {
		if (this.interstateCompactTypeCategoryDao.find(name) != null) {
			throw new DuplicateEntityFoundException(
					"Interstate compact type category already exists.");
		}
		InterstateCompactTypeCategory interstateCompactTypeCategory = 
				this.interstateCompactTypeCategoryInstanceFactory
				.createInstance();
		populateInterstateCompactTypeCategory(
				interstateCompactTypeCategory, name, valid);
		return this.interstateCompactTypeCategoryDao.makePersistent(
				interstateCompactTypeCategory);
	}
	
	/**
	 * Updates an existing interstate compact type category.
	 * 
	 * @param interstateCompactTypeCategory interstate compact type 
	 * category
	 * @param name name
	 * @param valid valid
	 * @return interstate compact type category
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public InterstateCompactTypeCategory update(
			final InterstateCompactTypeCategory 
					interstateCompactTypeCategory,
			final String name, final Boolean valid) 
					throws DuplicateEntityFoundException {
		if (this.interstateCompactTypeCategoryDao.findExcluding(name, 
				interstateCompactTypeCategory) != null) {
			throw new DuplicateEntityFoundException(
					"Interstate compact type category already exists.");
		}
		populateInterstateCompactTypeCategory(
				interstateCompactTypeCategory, name, valid);
		return this.interstateCompactTypeCategoryDao.makePersistent(
				interstateCompactTypeCategory);
	}

	/**
	 * Removes the specified interstate compact type category.
	 * 
	 * @param interstateCompactTypeCategory interstate compact type 
	 * category
	 */
	public void remove(
			final InterstateCompactTypeCategory 
					interstateCompactTypeCategory) {
		this.interstateCompactTypeCategoryDao.makeTransient(
				interstateCompactTypeCategory);
	}
	
	/**
	 * Returns a list of interstate compact type categories.
	 * 
	 * @return list of interstate compact type categories
	 */
	public List<InterstateCompactTypeCategory> findActive() {
		return this.interstateCompactTypeCategoryDao.findActive();
	}
	
	//Populates an interstate compact type category
	private void populateInterstateCompactTypeCategory(
			final InterstateCompactTypeCategory 
					interstateCompactTypeCategory,
			final String name, final Boolean valid) {
		interstateCompactTypeCategory.setName(name);
		interstateCompactTypeCategory.setValid(valid);
	}
}