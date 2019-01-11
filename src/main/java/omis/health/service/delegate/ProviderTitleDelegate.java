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
package omis.health.service.delegate;

import omis.health.dao.ProviderTitleDao;
import omis.health.domain.ProviderAssignmentCategory;
import omis.health.domain.ProviderTitle;
import omis.health.exception.ProviderTitleExistsException;
import omis.instance.factory.InstanceFactory;

/**
 * Delegate for provider title.
 * 
 * @author Yidong Li
 * @version 0.1.0 (Oct 26, 2018)
 * @since OMIS 3.0
 */
public class ProviderTitleDelegate {
	private final InstanceFactory<ProviderTitle>
		providerTitleInstanceFactory;
	private final ProviderTitleDao providerTitleDao;
	
	/**
	 * Instantiates a delegate for provider title
	 * @param providerTitleInstanceFactory providerTitleInstanceFactory
	 * @param providerTitleDao providerTitleDao
	 * 
	 */
	public ProviderTitleDelegate(
		final InstanceFactory<ProviderTitle>
		providerTitleInstanceFactory,
		final ProviderTitleDao providerTitleDao) {
		this.providerTitleInstanceFactory
			= providerTitleInstanceFactory;
		this.providerTitleDao = providerTitleDao;
	}

	/**
	 * Creates and persists a provider title.
	 * 
	 * @param category provider assignment category
	 * @param name name
	 * @param abbreviation abbreviation
	 * @param description description
	 * @param valid valid
	 * @param sortOrder sort order
	 * @throws ProviderTitleExistsException if provider title exists
	 */
	public ProviderTitle create(
		final ProviderAssignmentCategory category, final String name,
		final String abbreviation,
		final String description,
		final Boolean valid,
		final Short sortOrder) throws ProviderTitleExistsException {
		if(this.providerTitleDao.findExisting(name)!=null) {
			throw new ProviderTitleExistsException(""
				+ "Provider title already exists");
		}
		
		ProviderTitle title = this.providerTitleInstanceFactory
			.createInstance();
		title.setAbbreviation(abbreviation);
		title.setCategory(category);
		title.setDescription(description);
		title.setName(name);
		title.setSortOrder(sortOrder);
		title.setValid(valid);
		return this.providerTitleDao.makePersistent(title);
	}
}