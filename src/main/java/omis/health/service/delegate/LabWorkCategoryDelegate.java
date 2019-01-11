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

import omis.health.dao.LabWorkCategoryDao;
import omis.health.domain.LabWorkCategory;
import omis.health.exception.LabWorkCategoryExistsException;
import omis.instance.factory.InstanceFactory;

/**
 * Delegate for lab work category.
 * 
 * @author Yidong Li
 * @version 0.1.0 (Nov. 8, 2018)
 * @since OMIS 3.0
 */
public class LabWorkCategoryDelegate {
	private final InstanceFactory<LabWorkCategory>
		labWorkCategoryInstanceFactory;
	private final LabWorkCategoryDao labWorkCategoryDao;
	
	/**
	 * Instantiates a delegate for lab work category
	 * @param labWorkCategoryInstanceFactory labWorkCategoryInstanceFactory
	 * @param labWorkCategoryDao labWorkCategoryDao
	 * 
	 */
	public LabWorkCategoryDelegate(
		final InstanceFactory<LabWorkCategory> labWorkCategoryInstanceFactory,
		final LabWorkCategoryDao labWorkCategoryDao) {
		this.labWorkCategoryInstanceFactory
			= labWorkCategoryInstanceFactory;
		this.labWorkCategoryDao = labWorkCategoryDao;
	}

	/**
	 * Creates and persists a lab work category.
	 * 
	 * @param name name
	 * @param valid valid
	 * @param sortOrder sort order
	 * @throws LabWorkCategoryExistsException if lab work category
	 * already exists
	 */
	public LabWorkCategory create(
		final String name,
		final Short sortOrder,
		final Boolean valid) throws LabWorkCategoryExistsException {
		if(this.labWorkCategoryDao.findExisting(name)!=null) {
			throw new LabWorkCategoryExistsException("Lab work category"
					+ "already exists");
		}
		
		LabWorkCategory category = this.labWorkCategoryInstanceFactory
			.createInstance();
		category.setName(name);
		category.setSortOrder(sortOrder);
		category.setValid(valid);
		return this.labWorkCategoryDao.makePersistent(category);
	}
}