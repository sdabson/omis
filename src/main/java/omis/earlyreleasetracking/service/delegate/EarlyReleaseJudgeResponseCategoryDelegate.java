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
import omis.earlyreleasetracking.dao.EarlyReleaseJudgeResponseCategoryDao;
import omis.earlyreleasetracking.domain.EarlyReleaseJudgeResponseCategory;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;

/**
 * Early Release Judge Response Category Delegate.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Feb 8, 2019)
 *@since OMIS 3.0
 *
 */
public class EarlyReleaseJudgeResponseCategoryDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"Early Release Judge Response Category already exists with "
			+ "the given name.";
	
	private final EarlyReleaseJudgeResponseCategoryDao
				earlyReleaseJudgeResponseCategoryDao;
	
	private final InstanceFactory<EarlyReleaseJudgeResponseCategory> 
		earlyReleaseJudgeResponseCategoryInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for EarlyReleaseJudgeResponseCategoryDelegate.
	 * 
	 * @param earlyReleaseJudgeResponseCategoryDao Early Release Judge Response
	 * Category DAO
	 * @param earlyReleaseJudgeResponseCategoryInstanceFactory Early Release
	 * Judge Response Category Instance Factory
	 * @param auditComponentRetriever Audit Component Retriever
	 */
	public EarlyReleaseJudgeResponseCategoryDelegate(
			final EarlyReleaseJudgeResponseCategoryDao
					earlyReleaseJudgeResponseCategoryDao,
			final InstanceFactory<EarlyReleaseJudgeResponseCategory> 
				earlyReleaseJudgeResponseCategoryInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.earlyReleaseJudgeResponseCategoryDao = 
				earlyReleaseJudgeResponseCategoryDao;
		this.earlyReleaseJudgeResponseCategoryInstanceFactory =
				earlyReleaseJudgeResponseCategoryInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates an Early Release Judge Response Category with the given
	 * properties.
	 * 
	 * @param name Name
	 * @param valid Valid
	 * @return Newly created Early Release Judge Response Category.
	 * @throws DuplicateEntityFoundException When an Early Release Judge
	 * Response Category already exists with the given name.
	 */
	public EarlyReleaseJudgeResponseCategory create(final String name,
			final Boolean valid)
					throws DuplicateEntityFoundException {
		if (this.earlyReleaseJudgeResponseCategoryDao.find(name) != null) {
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		EarlyReleaseJudgeResponseCategory earlyReleaseJudgeResponseCategory = 
				this.earlyReleaseJudgeResponseCategoryInstanceFactory
				.createInstance();
		
		earlyReleaseJudgeResponseCategory.setName(name);
		earlyReleaseJudgeResponseCategory.setValid(valid);
		earlyReleaseJudgeResponseCategory.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		earlyReleaseJudgeResponseCategory.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.earlyReleaseJudgeResponseCategoryDao.makePersistent(
				earlyReleaseJudgeResponseCategory);
	}

	/**
	 * Updates the provided  Early Release Judge Response Category with the
	 * given properties.
	 * 
	 * @param earlyReleaseJudgeResponseCategory Early Release Judge Response
	 * Category To Update
	 * @param name Name
	 * @param valid Valid
	 * @return Updated Early Release Judge Response Category.
	 * @throws DuplicateEntityFoundException When an Early Release Judge
	 * Response Category already exists with the given name.
	 */
	public EarlyReleaseJudgeResponseCategory update(
			final EarlyReleaseJudgeResponseCategory
				earlyReleaseJudgeResponseCategory, 
			final String name, final Boolean valid)
					throws DuplicateEntityFoundException {
		if (this.earlyReleaseJudgeResponseCategoryDao.findExcluding(
				name, earlyReleaseJudgeResponseCategory) != null) {
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}

		earlyReleaseJudgeResponseCategory.setName(name);
		earlyReleaseJudgeResponseCategory.setValid(valid);
		earlyReleaseJudgeResponseCategory.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.earlyReleaseJudgeResponseCategoryDao.makePersistent(
				earlyReleaseJudgeResponseCategory);
	}
	
	/**
	 * Removes the specified Early Release Judge Response Category.
	 * 
	 * @param earlyReleaseJudgeResponseCategory - Early Release Judge Response
	 * Category to remove.
	 */
	public void remove(final EarlyReleaseJudgeResponseCategory
			earlyReleaseJudgeResponseCategory) {
		this.earlyReleaseJudgeResponseCategoryDao.makeTransient(
				earlyReleaseJudgeResponseCategory);
	}

	/**
	 * Returns a list of all valid Early Release Judge Response Categories.
	 * 
	 * @return List of all valid Early Release Judge Response Categories.
	 */
	public List<EarlyReleaseJudgeResponseCategory> findAllCategories() {
		return this.earlyReleaseJudgeResponseCategoryDao.findAllCategories();
	}
	
}
