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

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.instance.factory.InstanceFactory;
import omis.response.dao.ResponseDao;
import omis.response.domain.Grid;
import omis.response.domain.Response;
import omis.response.domain.ResponseCategory;
import omis.response.domain.ResponseLevel;
import omis.response.exception.ResponseExistsException;

/**
 * Response delegate.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Feb 26, 2019)
 * @since OMIS 3.0
 */
public class ResponseDelegate {

	/* Data access objects. */
	
	private final ResponseDao responseDao;
	
	/* Instance factories. */
	
	private final InstanceFactory<Response> responseInstanceFactory;
	
	/* Component retrievers. */
	
	private AuditComponentRetriever auditComponentRetriever;
	
	/* Constructor. */
	
	/**
	 * Instantiates an response delegate with the specified data access object 
	 * and instance factory.
	 * 
	 * @param responseDao response data access object
	 * @param responseInstanceFactory response instance factory
	 */
	public ResponseDelegate(final ResponseDao responseDao, 
			final InstanceFactory<Response> responseInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.responseDao = responseDao;
		this.responseInstanceFactory = responseInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/* Methods. */
	
	/**
	 * Creates a new response.
	 * 
	 * @param description description
	 * @param category response category
	 * @param grid grid
	 * @param level response level
	 * @param valid valid
	 * @return response
	 * @throws ResponseExistsException response already exists
	 */
	public Response create(final String description, 
			final ResponseCategory category, final Grid grid, 
			final ResponseLevel level, final Boolean valid) 
					throws ResponseExistsException {
		if (this.responseDao.find(description) != null) {
			throw new ResponseExistsException("Response already exists.");
		}
		Response response = this.responseInstanceFactory.createInstance();
		populateResponse(response, description, category, grid, level, valid);
		response.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
		return this.responseDao.makePersistent(response);
	}
	
	/**
	 * Updates an existing response.
	 * 
	 * @param response response
	 * @param description description
	 * @param category response category
	 * @param grid grid
	 * @param level response level
	 * @param valid valid
	 * @return response
	 * @throws ResponseExistsException response already exists
	 */
	public Response update(final Response response, final String description, 
			final ResponseCategory category, final Grid grid, 
			final ResponseLevel level, final Boolean valid) 
					throws ResponseExistsException {
		if (this.responseDao.findExcluding(description, response) != null) {
			throw new ResponseExistsException("Response already exists.");
		}
		populateResponse(response, description, category, grid, level, valid);
		return this.responseDao.makePersistent(response);
	}

	/**
	 * Removes the specified response.
	 * 
	 * @param response response
	 */
	public void remove(final Response response) {
		this.responseDao.makeTransient(response);
	}
	
	// Populates a response
	private void populateResponse(final Response response,
			final String description, final ResponseCategory category,
			final Grid grid, final ResponseLevel level, final Boolean valid) {
		response.setDescription(description);
		response.setCategory(category);
		response.setGrid(grid);
		response.setLevel(level);
		response.setValid(valid);
		response.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
	}
}