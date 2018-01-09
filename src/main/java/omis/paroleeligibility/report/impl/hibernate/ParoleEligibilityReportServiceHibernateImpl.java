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
package omis.paroleeligibility.report.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.offender.domain.Offender;
import omis.paroleeligibility.report.ParoleEligibilityReportService;
import omis.paroleeligibility.report.ParoleEligibilitySummary;

/**
 * Hibernate implementation of the parole eligibility report service.
 *
 * @author Trevor Isles
 * @version 0.1.0 (Dec 19, 2017)
 * @since OMIS 3.0
 */
public class ParoleEligibilityReportServiceHibernateImpl 
		implements ParoleEligibilityReportService {
	
	/* Queries. */
	
	private static final String FIND_PAROLE_ELIGIBILITIES_BY_OFFENDER_QUERY_NAME
		= "findParoleEligibilitiesByOffender";
	
	/* Parameters.*/ 
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	
	/* Members. */
	private final SessionFactory sessionFactory;
	
	/**
	 * Constructor.
	 * 
	 * @param sessionFactory session factory
	 */
	public ParoleEligibilityReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<ParoleEligibilitySummary> findByOffender(
			final Offender offender) {
		@SuppressWarnings("unchecked")
		List<ParoleEligibilitySummary> summaries = this.sessionFactory
				.getCurrentSession()
				.getNamedQuery(FIND_PAROLE_ELIGIBILITIES_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.list();
		return summaries;
	}

}