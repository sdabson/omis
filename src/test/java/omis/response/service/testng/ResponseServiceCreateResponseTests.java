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
package omis.response.service.testng;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.exception.DuplicateEntityFoundException;
import omis.response.domain.Grid;
import omis.response.domain.Response;
import omis.response.domain.ResponseCategory;
import omis.response.domain.ResponseLevel;
import omis.response.exception.ResponseExistsException;
import omis.response.service.ResponseService;
import omis.response.service.delegate.GridDelegate;
import omis.response.service.delegate.ResponseDelegate;
import omis.response.service.delegate.ResponseLevelDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.util.PropertyValueAsserter;

/**
 * Tests method to create responses.
 *
 * @author Josh Divine
 * @version 0.1.0 (Feb 27, 2019)
 * @since OMIS 3.0
 */
@Test
public class ResponseServiceCreateResponseTests
	extends AbstractHibernateTransactionalTestNGSpringContextTests {

	/* Delegates. */

	@Autowired
	private GridDelegate gridDelegate;

	@Autowired
	private ResponseLevelDelegate responseLevelDelegate;

	@Autowired
	private ResponseDelegate responseDelegate;
	
	/* Services. */

	@Autowired
	@Qualifier("responseService")
	private ResponseService responseService;

	/* Test methods. */

	/**
	 * Tests the creation of a response.
	 * 
	 * @throws ResponseExistsException if response already exists
	 * @throws DuplicateEntityFoundException if entity already exists
	 */
	@Test
	public void testCreateResponse() throws ResponseExistsException, 
			DuplicateEntityFoundException {
		// Arrangements
		String description = "Description";
		Grid grid = this.gridDelegate.create("Name", "Title", true);
		ResponseCategory category = ResponseCategory.INCENTIVE;
		ResponseLevel level = this.responseLevelDelegate.create("Name", true);
		Boolean valid = true;

		// Action
		Response response = this.responseService.createResponse(description, 
				grid, category, level, valid);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("description", description)
			.addExpectedValue("grid", grid)
			.addExpectedValue("category", category)
			.addExpectedValue("level", level)
			.addExpectedValue("valid", valid)
			.performAssertions(response);
	}

	/**
	 * Tests {@code ResponseExistsException} is thrown.
	 * 
	 * @throws ResponseExistsException if response already exists
	 * @throws DuplicateEntityFoundException if entity already exists
	 */
	@Test(expectedExceptions = {ResponseExistsException.class})
	public void testResponseExistsException() throws ResponseExistsException, 
			DuplicateEntityFoundException {
		// Arrangements
		String description = "Description";
		Grid grid = this.gridDelegate.create("Name", "Title", true);
		ResponseCategory category = ResponseCategory.INCENTIVE;
		ResponseLevel level = this.responseLevelDelegate.create("Name", true);
		Boolean valid = true;
		this.responseDelegate.create(description, category, grid, level, valid);

		// Action
		this.responseService.createResponse(description, grid, category, level, 
				valid);
	}

}