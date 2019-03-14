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
package omis.response.service.impl;

import java.util.ArrayList;
import java.util.List;

import omis.response.domain.Grid;
import omis.response.domain.Response;
import omis.response.domain.ResponseCategory;
import omis.response.domain.ResponseLevel;
import omis.response.exception.ResponseExistsException;
import omis.response.service.ResponseService;
import omis.response.service.delegate.GridDelegate;
import omis.response.service.delegate.ResponseDelegate;
import omis.response.service.delegate.ResponseLevelDelegate;

/**
 * Implementation of response service.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Feb 26, 2019)
 * @since OMIS 3.0
 */
public class ResponseServiceImpl implements ResponseService {

	private ResponseDelegate responseDelegate;
	
	private GridDelegate gridDelegate;
	
	private ResponseLevelDelegate responseLevelDelegate;
	
	/**
	 * Instantiates an implementation of the response service with the required 
	 * delegates.
	 * 
	 * @param responseDelegate response delegate
	 * @param gridDelegate grid delegate
	 * @param responseLevelDelegate response level delegate
	 */
	public ResponseServiceImpl(final ResponseDelegate responseDelegate,
			final GridDelegate gridDelegate,
			final ResponseLevelDelegate responseLevelDelegate) {
		this.responseDelegate = responseDelegate;
		this.responseLevelDelegate = responseLevelDelegate;
		this.gridDelegate = gridDelegate;
	}
	
	/** {@inheritDoc} */
	@Override
	public Response createResponse(final String description, final Grid grid,
			final ResponseCategory category, final ResponseLevel level, 
			final Boolean valid) throws ResponseExistsException {
		return this.responseDelegate.create(description, category, grid, level, 
				valid);
	}

	/** {@inheritDoc} */
	@Override
	public Response updateResponse(final Response response, 
			final String description, final ResponseLevel level, 
			final Boolean valid) throws ResponseExistsException {
		return this.responseDelegate.update(response, description, 
				response.getCategory(), response.getGrid(), level, valid);
	}

	/** {@inheritDoc} */
	@Override
	public void removeResponse(final Response response) {
		this.responseDelegate.remove(response);
	}

	/** {@inheritDoc} */
	@Override
	public List<Grid> findGrids() {
		return this.gridDelegate.findActive();
	}

	/** {@inheritDoc} */
	@Override
	public List<ResponseCategory> findResponseCategories() {
		List<ResponseCategory> categories = new ArrayList<ResponseCategory>();
		for (ResponseCategory responseCategory : ResponseCategory.values()) {
			categories.add(responseCategory);
		}
		return categories;
	}

	/** {@inheritDoc} */
	@Override
	public List<ResponseLevel> findResponseLevels() {
		return this.responseLevelDelegate.findActive();
	}
}