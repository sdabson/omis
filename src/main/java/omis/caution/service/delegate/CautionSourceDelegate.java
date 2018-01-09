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

import omis.caution.dao.CautionSourceDao;
import omis.caution.domain.CautionSource;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;

/**
 * Delegate for caution sources.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Jun 17, 2016)
 * @since OMIS 3.0
 */
public class CautionSourceDelegate {
	
	/* Instance factories. */
	
	private final InstanceFactory<CautionSource> cautionSourceInstanceFactory;
	
	/* DAOs. */
	
	private final CautionSourceDao cautionSourceDao;
	
	/* Constructors. */
	
	/**
	 * Instantiates delegate for caution sources.
	 * 
	 * @param cautionSourceInstanceFactory instance factory for caution sources
	 * @param cautionSourceDao data access object for caution sources
	 */
	public CautionSourceDelegate(
			final InstanceFactory<CautionSource> cautionSourceInstanceFactory,
			final CautionSourceDao cautionSourceDao) {
		this.cautionSourceInstanceFactory = cautionSourceInstanceFactory;
		this.cautionSourceDao = cautionSourceDao;
	}

	/**
	 * Creates caution source. 
	 * 
	 * @param name name
	 * @param medical whether medical
	 * @param valid whether valid
	 * @return caution
	 * @throws DuplicateEntityFoundException if caution source exists
	 */
	public CautionSource create(
			final String name, final Boolean medical, final Boolean valid)
				throws DuplicateEntityFoundException {
		if (this.cautionSourceDao.find(name) != null) {
			throw new DuplicateEntityFoundException("Caution source exists");
		}
		CautionSource cautionSource = this.cautionSourceInstanceFactory
					.createInstance();
		cautionSource.setName(name);
		cautionSource.setMedical(medical);
		cautionSource.setValid(valid);
		return this.cautionSourceDao.makePersistent(cautionSource);
	}
}