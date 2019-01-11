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
package omis.program.service.delegate;

import omis.instance.factory.InstanceFactory;
import omis.location.domain.Location;
import omis.program.dao.ProgramLocationOfferDao;
import omis.program.domain.Program;
import omis.program.domain.ProgramLocationOffer;
import omis.program.exception.ProgramLocationOfferExistsException;

/**
 * Delegate for offering of program at location.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Apr 13, 2016)
 * @since OMIS 3.0
 */
public class ProgramLocationOfferDelegate {

	private final InstanceFactory<ProgramLocationOffer>
	programLocationOfferInstanceFactory;
	
	private final ProgramLocationOfferDao programLocationOfferDao;
	
	/**
	 * Instantiates delegate offering program at location.
	 * 
	 * @param programLocationOfferInstanceFactory instance factory for offering
	 * of program at location
	 * @param programLocationOfferDao data access object offering program
	 * at location
	 */
	public ProgramLocationOfferDelegate(
			final InstanceFactory<ProgramLocationOffer>
				programLocationOfferInstanceFactory,
			final ProgramLocationOfferDao programLocationOfferDao) {
		this.programLocationOfferInstanceFactory
			= programLocationOfferInstanceFactory;
		this.programLocationOfferDao = programLocationOfferDao;
	}
	
	/**
	 * Creates offering of program at location.
	 * 
	 * @param program program
	 * @param location location
	 * @return offering of program at location
	 * @throws ProgramLocationOfferExistsException if program is offered at
	 * location
	 */
	public ProgramLocationOffer create(
			final Program program, final Location location)
				throws ProgramLocationOfferExistsException {
		if (this.programLocationOfferDao.find(program, location) != null) {
			throw new ProgramLocationOfferExistsException(
					"Program is already offered at location");
		}
		ProgramLocationOffer offer = this.programLocationOfferInstanceFactory
				.createInstance();
		offer.setProgram(program);
		offer.setLocation(location);
		return this.programLocationOfferDao.makePersistent(offer);
	}
}