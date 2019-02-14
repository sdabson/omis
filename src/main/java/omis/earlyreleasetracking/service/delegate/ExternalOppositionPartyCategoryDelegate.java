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
import omis.earlyreleasetracking.dao.ExternalOppositionPartyCategoryDao;
import omis.earlyreleasetracking.domain.ExternalOppositionPartyCategory;
import omis.instance.factory.InstanceFactory;

/**
 * External Opposition Party Category Delegate.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Feb 8, 2019)
 *@since OMIS 3.0
 *
 */
public class ExternalOppositionPartyCategoryDelegate {
	
	private final ExternalOppositionPartyCategoryDao
			externalOppositionPartyCategoryDao;
	
	private final InstanceFactory<ExternalOppositionPartyCategory>
			externalOppositionPartyCategoryInstanceFactory;
	
	/**
	 * Contructor for ExternalOppositionPartyCategoryDelegate.
	 * 
	 * @param externalOppositionPartyCategoryDao External Opposition Party
	 * Category DAO
	 * @param externalOppositionPartyCategoryInstanceFactory External Opposition
	 * Party Category Instance Factory
	 */
	public ExternalOppositionPartyCategoryDelegate(
			final ExternalOppositionPartyCategoryDao
				externalOppositionPartyCategoryDao,
			final InstanceFactory<ExternalOppositionPartyCategory>
				externalOppositionPartyCategoryInstanceFactory) {
		this.externalOppositionPartyCategoryDao =
				externalOppositionPartyCategoryDao;
		this.externalOppositionPartyCategoryInstanceFactory =
				externalOppositionPartyCategoryInstanceFactory;
	}

	/**
	 * Returns a list of all valid External Opposition Party Categories.
	 * 
	 * @return List of all valid External Opposition Party Categories.
	 */
	public List<ExternalOppositionPartyCategory> findAllCategories() {
		return this.externalOppositionPartyCategoryDao.findAllCategories();
	}
	
	/**
	 * Creates an External Opposition Party Category, for testing purposes.
	 * 
	 * @param name Name
	 * @param valid Valid
	 * @return Newly Created External Opposition Party Category
	 */
	public ExternalOppositionPartyCategory create(
			final String name, final Boolean valid) {
		ExternalOppositionPartyCategory category =
				this.externalOppositionPartyCategoryInstanceFactory
				.createInstance();
		category.setName(name);
		category.setValid(valid);
		return this.externalOppositionPartyCategoryDao.makePersistent(category);
	}
}
