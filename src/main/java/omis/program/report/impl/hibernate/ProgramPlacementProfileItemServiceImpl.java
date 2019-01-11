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
package omis.program.report.impl.hibernate;

import java.util.Date;

import org.hibernate.SessionFactory;

import omis.offender.domain.Offender;
import omis.program.report.ProgramPlacementProfileItemService;

public class ProgramPlacementProfileItemServiceImpl 
	implements ProgramPlacementProfileItemService {

	private static final String 
		FIND_PROGRAM_PLACEMENTS_BY_OFFENDER_ON_DATE_QUERY_NAME 
			= "findProgramPlacementsByOffenderOnDate";
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private static final String DATE_PARAM_NAME = "date";
	
	private final SessionFactory sessionFactory;

	/**
	 * Constructor.
	 * 
	 * @param sessionFactory session factory
	 */
	public ProgramPlacementProfileItemServiceImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/** {@inheritDoc} */
	@Override
	public Boolean findProgramPlacementExistenceByOffenderOnDate(
			final Offender offender, final Date effectiveDate) {
		return !this.sessionFactory.getCurrentSession()
				.getNamedQuery(
					FIND_PROGRAM_PLACEMENTS_BY_OFFENDER_ON_DATE_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(DATE_PARAM_NAME, effectiveDate)
				.list().isEmpty();
	}
}
