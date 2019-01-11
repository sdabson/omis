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
package omis.probationparole.service.delegate;

import java.util.List;
import omis.instance.factory.InstanceFactory;
import omis.probationparole.dao.ProbationParoleDocumentCategoryDao;
import omis.probationparole.domain.ProbationParoleDocumentCategory;

/**
 * Probation Parole Document Category Delegate.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Dec 6, 2018)
 *@since OMIS 3.0
 *
 */
public class ProbationParoleDocumentCategoryDelegate {
	
	private final ProbationParoleDocumentCategoryDao
			probationParoleDocumentCategoryDao;
	
	private final InstanceFactory<ProbationParoleDocumentCategory>
			probationParoleDocumentCategoryInstanceFactory;

	/**
	 * Constructor for ProbationParoleDocumentCategoryDelegate.
	 * 
	 * @param probationParoleDocumentCategoryDao - Probation Parole Document
	 * Category DAO
	 * @param probationParoleDocumentCategoryInstanceFactory - Probation
	 * Parole Document Category Instance Factory
	 */
	public ProbationParoleDocumentCategoryDelegate(
			final ProbationParoleDocumentCategoryDao
				probationParoleDocumentCategoryDao,
			final InstanceFactory<ProbationParoleDocumentCategory>
				probationParoleDocumentCategoryInstanceFactory) {
		this.probationParoleDocumentCategoryDao =
				probationParoleDocumentCategoryDao;
		this.probationParoleDocumentCategoryInstanceFactory =
				probationParoleDocumentCategoryInstanceFactory;
	}
	
	/**
	 * Returns a list of all valid Probation Parole Document Categories.
	 * 
	 * @return List of all valid Probation Parole Document Categories.
	 */
	public List<ProbationParoleDocumentCategory> findCategories() {
		return this.probationParoleDocumentCategoryDao.findCategories();
	}
	
	/**
	 * Creates a Probation Parole Document Category, for testing purposes.
	 * 
	 * @param name - name
	 * @param valid - valid
	 * @return Newly created Probation Parole Document Category.
	 */
	public ProbationParoleDocumentCategory create(
			final String name, final Boolean valid) {
		ProbationParoleDocumentCategory probationParoleDocumentCategory =
				this.probationParoleDocumentCategoryInstanceFactory
				.createInstance();
		
		probationParoleDocumentCategory.setName(name);
		probationParoleDocumentCategory.setValid(valid);
		
		return this.probationParoleDocumentCategoryDao.makePersistent(
				probationParoleDocumentCategory);
	}
}
