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
package omis.caution.service.delegate;

import omis.caution.dao.CautionDescriptionDao;
import omis.caution.domain.CautionDescription;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;

/**
 * Delegate for caution descriptions.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Jun 17, 2016)
 * @since OMIS 3.0
 */
public class CautionDescriptionDelegate {

	/* Instance factories. */
	
	private final InstanceFactory<CautionDescription>
	cautionDescriptionInstanceFactory;
	
	/* DAOs. */
	
	private final CautionDescriptionDao cautionDescriptionDao;
	
	/* Constructors. */
	
	/**
	 * Instantiates delegate for caution descriptions.
	 * 
	 * @param cautionDescriptionInstanceFactory instance factory for
	 * caution descriptions
	 * @param cautionDescriptionDao data access object for caution descriptions
	 */
	public CautionDescriptionDelegate(
			final InstanceFactory<CautionDescription>
				cautionDescriptionInstanceFactory,
			final CautionDescriptionDao cautionDescriptionDao) {
		this.cautionDescriptionInstanceFactory
			= cautionDescriptionInstanceFactory;
		this.cautionDescriptionDao = cautionDescriptionDao;
	}
	
	/**
	 * Creates caution description.
	 * 
	 * @param name name
	 * @param valid whether valid
	 * @return caution description
	 * @throws DuplicateEntityFoundException if caution description exists
	 */
	public CautionDescription create(
			final String name, final Boolean valid)
				throws DuplicateEntityFoundException {
		if(this.cautionDescriptionDao.find(name) != null) {
			throw new DuplicateEntityFoundException(
					"Caution description exists");
		}
		CautionDescription cautionDescription
			= this.cautionDescriptionInstanceFactory.createInstance();
		cautionDescription.setName(name);
		cautionDescription.setValid(valid);
		return this.cautionDescriptionDao.makePersistent(cautionDescription);
	}
}