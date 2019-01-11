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
package omis.staff.service.impl;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.staff.domain.StaffTitle;
import omis.staff.service.StaffTitleService;
import omis.staff.service.delegate.StaffTitleDelegate;

/**
 * Implementation of service for staff titles.
 * 
 * @author Stephen Abson
 * @author Josh Divine
 * @version 0.1.1 (Nov 9, 2017)
 * @since OMIS 3.0
 */
public class StaffTitleServiceImpl
		implements StaffTitleService {
	
	private final StaffTitleDelegate staffTitleDelegate;
	
	/**
	 * Instantiates an implementation of service for staff titles.
	 * 
	 * @param staffTitleDelegate delegate for staff titles
	 */
	public StaffTitleServiceImpl(final StaffTitleDelegate staffTitleDelegate) {
		this.staffTitleDelegate = staffTitleDelegate;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<StaffTitle> findAll() {
		return this.staffTitleDelegate.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public StaffTitle create(final String name, final Short sortOrder,
			final Boolean valid) throws DuplicateEntityFoundException {
		return this.staffTitleDelegate.create(name, sortOrder, valid);
	}

	/** {@inheritDoc} */
	@Override
	public StaffTitle update(final StaffTitle staffTitle, final String name,
			final Short sortOrder, final Boolean valid)
					throws DuplicateEntityFoundException{
		return this.staffTitleDelegate.update(staffTitle, name, sortOrder, 
				valid);
	}
	
	/** {@inheritDoc} */
	@Override
	public void remove(final StaffTitle staffTitle) {
		this.staffTitleDelegate.remove(staffTitle);
	}

	/** {@inheritDoc} */
	@Override
	public short findHighestSortOrder() {
		return this.staffTitleDelegate.findHighestSortOrder();
	}
}