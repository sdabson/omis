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
package omis.earlyreleasetracking.service.delegate;

import java.util.List;
import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.earlyreleasetracking.dao.EarlyReleaseStatusCategoryDao;
import omis.earlyreleasetracking.domain.EarlyReleaseStatusCategory;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;

/**
 * Early Release Status Category Delegate.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Feb 8, 2019)
 *@since OMIS 3.0
 *
 */
public class EarlyReleaseStatusCategoryDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"Early Release Status Category already exists with "
			+ "the given name.";
	
	private final EarlyReleaseStatusCategoryDao
				earlyReleaseStatusCategoryDao;
	
	private final InstanceFactory<EarlyReleaseStatusCategory> 
		earlyReleaseStatusCategoryInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for EarlyReleaseStatusCategoryDelegate.
	 * 
	 * @param earlyReleaseStatusCategoryDao Early Release Status Category DAO
	 * @param earlyReleaseStatusCategoryInstanceFactory Early Release Status
	 * Category Instance Factory
	 * @param auditComponentRetriever Audit Component Retriever
	 */
	public EarlyReleaseStatusCategoryDelegate(
			final EarlyReleaseStatusCategoryDao
					earlyReleaseStatusCategoryDao,
			final InstanceFactory<EarlyReleaseStatusCategory> 
				earlyReleaseStatusCategoryInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.earlyReleaseStatusCategoryDao = 
				earlyReleaseStatusCategoryDao;
		this.earlyReleaseStatusCategoryInstanceFactory =
				earlyReleaseStatusCategoryInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates an Early Release Status Category with the given properties.
	 * 
	 * @param name Name
	 * @param valid Valid
	 * @return Newly created Early Release Status Category.
	 * @throws DuplicateEntityFoundException When an Early Release Status
	 * Category already exists with the given name.
	 */
	public EarlyReleaseStatusCategory create(final String name,
			final Boolean valid)
					throws DuplicateEntityFoundException {
		if (this.earlyReleaseStatusCategoryDao.find(name) != null) {
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		EarlyReleaseStatusCategory earlyReleaseStatusCategory = 
				this.earlyReleaseStatusCategoryInstanceFactory
				.createInstance();
		
		earlyReleaseStatusCategory.setName(name);
		earlyReleaseStatusCategory.setValid(valid);
		earlyReleaseStatusCategory.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		earlyReleaseStatusCategory.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.earlyReleaseStatusCategoryDao.makePersistent(
				earlyReleaseStatusCategory);
	}

	/**
	 * Updates the provided  Early Release Status Category with the
	 * given properties.
	 * 
	 * @param earlyReleaseStatusCategory Early Release Status Category to Update
	 * @param name Name
	 * @param valid Valid
	 * @return Updated Early Release Status Category.
	 * @throws DuplicateEntityFoundException When an Early Release Status
	 * Category already exists with the given name.
	 */
	public EarlyReleaseStatusCategory update(
			final EarlyReleaseStatusCategory
				earlyReleaseStatusCategory, 
			final String name, final Boolean valid)
					throws DuplicateEntityFoundException {
		if (this.earlyReleaseStatusCategoryDao.findExcluding(
				name, earlyReleaseStatusCategory) != null) {
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}

		earlyReleaseStatusCategory.setName(name);
		earlyReleaseStatusCategory.setValid(valid);
		earlyReleaseStatusCategory.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.earlyReleaseStatusCategoryDao.makePersistent(
				earlyReleaseStatusCategory);
	}
	
	/**
	 * Removes the specified Early Release Status Category.
	 * 
	 * @param earlyReleaseStatusCategory - Early Release Status Category
	 * to remove.
	 */
	public void remove(final EarlyReleaseStatusCategory
			earlyReleaseStatusCategory) {
		this.earlyReleaseStatusCategoryDao.makeTransient(
				earlyReleaseStatusCategory);
	}

	/**
	 * Returns a list of all valid Early Release Status Categories.
	 * 
	 * @return List of all valid Early Release Status Categories.
	 */
	public List<EarlyReleaseStatusCategory> findAllCategories() {
		return this.earlyReleaseStatusCategoryDao.findAllCategories();
	}
}
