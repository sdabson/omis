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
import omis.program.dao.ProgramSupervisoryOrganizationOfferDao;
import omis.program.domain.Program;
import omis.program.domain.ProgramSupervisoryOrganizationOffer;
import omis.program.exception.ProgramSupervisoryOrganizationOfferExistsException;
import omis.supervision.domain.SupervisoryOrganization;

/**
 * Delegate for offering of programs by supervisory organization.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Apr 13, 2016)
 * @since OMIS 3.0
 */
public class ProgramSupervisoryOrganizationOfferDelegate {

	private final InstanceFactory<ProgramSupervisoryOrganizationOffer>
	programSupervisoryOrganizationOfferInstanceFactory;
	
	private final ProgramSupervisoryOrganizationOfferDao
	programSupervisoryOrganizationOfferDao;
	
	/**
	 * Delegate offering programs by supervisory organization.
	 * 
	 * @param programSupervisoryOrganizationOfferInstanceFactory instance
	 * factory for offering of programs by supervisory organization
	 * @param programSupervisoryOrganizationOfferDao data access object for
	 * offering programs by supervisory organization
	 */
	public ProgramSupervisoryOrganizationOfferDelegate(
			final InstanceFactory<ProgramSupervisoryOrganizationOffer>
				programSupervisoryOrganizationOfferInstanceFactory,
			final ProgramSupervisoryOrganizationOfferDao
				programSupervisoryOrganizationOfferDao) {
		this.programSupervisoryOrganizationOfferInstanceFactory
			= programSupervisoryOrganizationOfferInstanceFactory;
		this.programSupervisoryOrganizationOfferDao
			= programSupervisoryOrganizationOfferDao;
	}

	/**
	 * Creates offering of program by supervisory organization.
	 * 
	 * @param program program
	 * @param supervisoryOrganization supervisory organization
	 * @return offering of program by supervisory organization
	 * @throws ProgramSupervisoryOrganizationOfferExistsException if
	 * program is offered at supervisory organization
	 */
	public ProgramSupervisoryOrganizationOffer create(final Program program,
			final SupervisoryOrganization supervisoryOrganization)
					throws ProgramSupervisoryOrganizationOfferExistsException {
		if (this.programSupervisoryOrganizationOfferDao
				.find(program, supervisoryOrganization) != null) {
			throw new ProgramSupervisoryOrganizationOfferExistsException (
					"Program is already offered by supervisory organization");
		}
		ProgramSupervisoryOrganizationOffer offer
			= this.programSupervisoryOrganizationOfferInstanceFactory
				.createInstance();
		offer.setProgram(program);
		offer.setSupervisoryOrganization(supervisoryOrganization);
		return this.programSupervisoryOrganizationOfferDao
				.makePersistent(offer);
	}
}