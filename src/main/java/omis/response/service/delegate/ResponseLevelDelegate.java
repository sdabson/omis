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
package omis.response.service.delegate;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.response.dao.ResponseLevelDao;
import omis.response.domain.ResponseLevel;

/**
 * Response level delegate.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Feb 26, 2019)
 * @since OMIS 3.0
 */
public class ResponseLevelDelegate {

	/* Data access objects. */
	
	private final ResponseLevelDao responseLevelDao;
	
	/* Instance factories. */
	
	private final InstanceFactory<ResponseLevel> responseLevelInstanceFactory;
	
	/* Constructor. */
	
	/**
	 * Instantiates an response level delegate with the specified data access 
	 * object and instance factory.
	 * 
	 * @param responseLevelDao response level data access object
	 * @param responseLevelInstanceFactory response level instance factory
	 */
	public ResponseLevelDelegate(final ResponseLevelDao responseLevelDao, 
			final InstanceFactory<ResponseLevel> responseLevelInstanceFactory) {
		this.responseLevelDao = responseLevelDao;
		this.responseLevelInstanceFactory = responseLevelInstanceFactory;
	}
	
	/* Methods. */
	
	/**
	 * Creates a new response level.
	 * 
	 * @param name name
	 * @param valid valid
	 * @return response level
	 * @throws DuplicateEntityFoundException thrown when duplicate entity exists
	 */
	public ResponseLevel create(final String name, final Boolean valid) 
			throws DuplicateEntityFoundException {
		if (this.responseLevelDao.find(name) != null) {
			throw new DuplicateEntityFoundException(
					"Response level already exists.");
		}
		ResponseLevel responseLevel = 
				this.responseLevelInstanceFactory.createInstance();
		populateResponseLevel(responseLevel, name, valid);
		return this.responseLevelDao.makePersistent(responseLevel);
	}
	
	/**
	 * Updates an existing response level.
	 * 
	 * @param responseLevel response level
	 * @param name name
	 * @param valid valid
	 * @return response level
	 * @throws DuplicateEntityFoundException thrown when duplicate entity exists
	 */
	public ResponseLevel update(final ResponseLevel responseLevel, final String name, 
			final Boolean valid) throws DuplicateEntityFoundException {
		if (this.responseLevelDao.findExcluding(name, responseLevel) != null) {
			throw new DuplicateEntityFoundException(
					"Response level already exists.");
		}
		populateResponseLevel(responseLevel, name, valid);
		return this.responseLevelDao.makePersistent(responseLevel);
	}

	/**
	 * Removes the specified response level.
	 * 
	 * @param responseLevel response level
	 */
	public void remove(final ResponseLevel responseLevel) {
		this.responseLevelDao.makeTransient(responseLevel);
	}
	
	/**
	 * Returns a list of response levels.
	 * 
	 * @return list of response levels
	 */
	public List<ResponseLevel> findActive() {
		return this.responseLevelDao.findActive();
	}
	
	// Populates a response level
	private void populateResponseLevel(final ResponseLevel responseLevel,
			final String name, final Boolean valid) {
		responseLevel.setName(name);
		responseLevel.setValid(valid);
	}
	
}