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

import omis.caseload.dao.InterstateCompactEndReasonCategoryDao;
import omis.caseload.domain.InterstateCompactEndReasonCategory;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;

/**
 * Interstate compact end reason category delegate.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Sep 7, 2018)
 * @since OMIS 3.0
 */
public class InterstateCompactEndReasonCategoryDelegate {

	/* DAOs */
	private final InterstateCompactEndReasonCategoryDao 
			interstateCompactEndReasonCategoryDao;
	
	/* Instance factory. */
	private final InstanceFactory<InterstateCompactEndReasonCategory> 
			interstateCompactEndReasonCategoryInstanceFactory;
	
	/**
	 * Instantiates an implementation of interstate compact end reason category 
	 * delegate with the specified data access object and instance factory.
	 * 
	 * @param interstateCompactEndReasonCategoryDao interstate compact end 
	 * reason category data access object
	 * @param interstateCompactEndReasonCategoryInstanceFactory interstate 
	 * compact end reason category instance factory
	 */
	public InterstateCompactEndReasonCategoryDelegate(
			final InterstateCompactEndReasonCategoryDao 
					interstateCompactEndReasonCategoryDao,
			final InstanceFactory<InterstateCompactEndReasonCategory> 
					interstateCompactEndReasonCategoryInstanceFactory) {
		this.interstateCompactEndReasonCategoryDao = 
				interstateCompactEndReasonCategoryDao;
		this.interstateCompactEndReasonCategoryInstanceFactory = 
				interstateCompactEndReasonCategoryInstanceFactory;
	}
	
	/**
	 * Creates a new interstate compact end reason category.
	 * 
	 * @param name name
	 * @param valid valid
	 * @return interstate compact end reason category
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public InterstateCompactEndReasonCategory create(
			final String name, final Boolean valid) 
					throws DuplicateEntityFoundException {
		if (this.interstateCompactEndReasonCategoryDao.find(name) != null) {
			throw new DuplicateEntityFoundException(
					"Interstate compact end reason category already exists.");
		}
		InterstateCompactEndReasonCategory interstateCompactEndReasonCategory = 
				this.interstateCompactEndReasonCategoryInstanceFactory
				.createInstance();
		populateInterstateCompactEndReasonCategory(
				interstateCompactEndReasonCategory, name, valid);
		return this.interstateCompactEndReasonCategoryDao.makePersistent(
				interstateCompactEndReasonCategory);
	}
	
	/**
	 * Updates an existing interstate compact end reason category.
	 * 
	 * @param interstateCompactEndReasonCategory interstate compact end reason 
	 * category
	 * @param name name
	 * @param valid valid
	 * @return interstate compact end reason category
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public InterstateCompactEndReasonCategory update(
			final InterstateCompactEndReasonCategory 
					interstateCompactEndReasonCategory,
			final String name, final Boolean valid) 
					throws DuplicateEntityFoundException {
		if (this.interstateCompactEndReasonCategoryDao.findExcluding(name, 
				interstateCompactEndReasonCategory) != null) {
			throw new DuplicateEntityFoundException(
					"Interstate compact end reason category already exists.");
		}
		populateInterstateCompactEndReasonCategory(
				interstateCompactEndReasonCategory, name, valid);
		return this.interstateCompactEndReasonCategoryDao.makePersistent(
				interstateCompactEndReasonCategory);
	}

	/**
	 * Removes the specified interstate compact end reason category.
	 * 
	 * @param interstateCompactEndReasonCategory interstate compact end reason 
	 * category
	 */
	public void remove(
			final InterstateCompactEndReasonCategory 
					interstateCompactEndReasonCategory) {
		this.interstateCompactEndReasonCategoryDao.makeTransient(
				interstateCompactEndReasonCategory);
	}
	
	/**
	 * Returns a list of interstate compact end reason categories.
	 * 
	 * @return list of interstate compact end reason categories
	 */
	public List<InterstateCompactEndReasonCategory> findActive() {
		return this.interstateCompactEndReasonCategoryDao.findActive();
	}
	
	//Populates an interstate compact end reason category
	private void populateInterstateCompactEndReasonCategory(
			final InterstateCompactEndReasonCategory 
					interstateCompactEndReasonCategory,
			final String name, final Boolean valid) {
		interstateCompactEndReasonCategory.setName(name);
		interstateCompactEndReasonCategory.setValid(valid);
	}
}