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
package omis.trackeddocument.service.delegate;

import java.util.List;

import omis.instance.factory.InstanceFactory;
import omis.trackeddocument.dao.TrackedDocumentCategoryDao;
import omis.trackeddocument.domain.TrackedDocumentCategory;

/**
 * Tracked document category delegate.
 * 
 *@author Yidong Li
 *@author Sheronda Vaughn
 *@version 0.1.0 (Dec 12, 2017)
 *@since OMIS 3.0
 *
 */
public class TrackedDocumentCategoryDelegate {
	private final TrackedDocumentCategoryDao trackedDocumentCategoryDao;
	/* Instance factories. */
	private InstanceFactory<TrackedDocumentCategory> 
		trackedDocumentCategoryInstanceFactory;
	
	/**
	 * Instantiates an implementation of tracked document category delegate.
	 * @param trackedDocumentCategoryDao trackedDocumentCategoryDao
	 * @param trackedDocumentCategoryInstanceFactory tracked document category
	 * instance factory
	 */
	public TrackedDocumentCategoryDelegate(
		final TrackedDocumentCategoryDao trackedDocumentCategoryDao,
		final InstanceFactory<TrackedDocumentCategory> 
			trackedDocumentCategoryInstanceFactory) {
		this.trackedDocumentCategoryDao = trackedDocumentCategoryDao;
		this.trackedDocumentCategoryInstanceFactory 
			= trackedDocumentCategoryInstanceFactory;
	}
	
	/**
	 * Returns a list of all OffenderFlagCategorys.
	 * @return List of all OffenderFlagCategorys
	 */
	public List<TrackedDocumentCategory> findAll() {
		return this.trackedDocumentCategoryDao.findCategories();
	}
	
	/**
	 * Creates a tracked document category.
	 *
	 *
	 * @param name name
	 * @param valid valid
	 * @return new tracked document category
	 */
	public TrackedDocumentCategory create(final String name, 
			final Boolean valid) {
		TrackedDocumentCategory category 
			= this.trackedDocumentCategoryInstanceFactory.createInstance();
		category.setName(name);
		category.setValid(valid);
		return this.trackedDocumentCategoryDao.makePersistent(category);
	}
}