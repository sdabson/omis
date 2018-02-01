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
package omis.offenseterm.service.impl;

import java.util.List;

import omis.court.domain.Court;
import omis.court.service.delegate.CourtDelegate;
import omis.docket.domain.Docket;
import omis.docket.exception.DocketExistsException;
import omis.docket.service.delegate.DocketDelegate;
import omis.offenseterm.service.OffenseTermDocketService;

/**
 * Implementation of service to manage offense term dockets.
 *
 * @author Josh Divine
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class OffenseTermDocketServiceImpl 
		implements OffenseTermDocketService {

	private final CourtDelegate courtDelegate;
	
	private final DocketDelegate docketDelegate;
	
	/**
	 * Instantiates implementation of service to manage offense term dockets.
	 * 
	 * @param courtDelegate delegate for courts
	 * @param docketDelegate delegate for dockets
	 */
	public OffenseTermDocketServiceImpl(final CourtDelegate courtDelegate,
			final DocketDelegate docketDelegate) {
		this.courtDelegate = courtDelegate;
		this.docketDelegate = docketDelegate;
	}

	/** {@inheritDoc} */
	@Override
	public Docket update(final Docket docket, final Court court, 
			final String docketValue) throws DocketExistsException {
		return this.docketDelegate.update(docket, court, docketValue);
	}

	/** {@inheritDoc} */
	@Override
	public List<Court> findCourts() {
		return this.courtDelegate.findAllCourts();
	}

}
