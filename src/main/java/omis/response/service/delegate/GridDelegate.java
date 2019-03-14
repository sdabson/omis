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
import omis.response.dao.GridDao;
import omis.response.domain.Grid;

/**
 * Grid delegate.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Feb 26, 2019)
 * @since OMIS 3.0
 */
public class GridDelegate {

	/* Data access objects. */
	
	private final GridDao gridDao;
	
	/* Instance factories. */
	
	private final InstanceFactory<Grid> gridInstanceFactory;
	
	/* Constructor. */
	
	/**
	 * Instantiates an grid delegate with the specified data access object and 
	 * instance factory.
	 * 
	 * @param gridDao grid data access object
	 * @param gridInstanceFactory grid instance factory
	 */
	public GridDelegate(final GridDao gridDao, 
			final InstanceFactory<Grid> gridInstanceFactory) {
		this.gridDao = gridDao;
		this.gridInstanceFactory = gridInstanceFactory;
	}
	
	/* Methods. */
	
	/**
	 * Creates a new grid.
	 * 
	 * @param name name
	 * @param title title
	 * @param valid valid
	 * @return grid
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public Grid create(final String name, final String title, 
			final Boolean valid) throws DuplicateEntityFoundException {
		if (this.gridDao.find(name) != null) {
			throw new DuplicateEntityFoundException("Grid already exists.");
		}
		Grid grid = this.gridInstanceFactory.createInstance();
		populateGrid(grid, name, title, valid);
		return this.gridDao.makePersistent(grid);
	}
	
	/**
	 * Updates an existing grid.
	 * 
	 * @param name name
	 * @param title title
	 * @param valid valid
	 * @return grid
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public Grid update(final Grid grid, final String name, final String title, 
			final Boolean valid) throws DuplicateEntityFoundException {
		if (this.gridDao.find(name) != null) {
			throw new DuplicateEntityFoundException("Grid already exists.");
		}
		populateGrid(grid, name, title, valid);
		return this.gridDao.makePersistent(grid);
	}

	/**
	 * Removes the specified grid.
	 * 
	 * @param grid grid
	 */
	public void remove(final Grid grid) {
		this.gridDao.makeTransient(grid);
	}
	
	/**
	 * Returns a list of active grid.
	 * 
	 * @return list of active grid
	 */
	public List<Grid> findActive() {
		return this.gridDao.findActive();
	}
	
	// Populates a grid
	private void populateGrid(final Grid grid, final String name,
			final String title, final Boolean valid) {
		grid.setName(name);
		grid.setTitle(title);
		grid.setValid(valid);
	}
}