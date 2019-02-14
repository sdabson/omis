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
package omis.earlyreleasetracking.report.impl.hibernate;

import java.util.List;
import org.hibernate.SessionFactory;
import omis.earlyreleasetracking.report.EarlyReleaseRequestSummary;
import omis.earlyreleasetracking.report.EarlyReleaseRequestSummaryReportService;
import omis.offender.domain.Offender;

/**
 * Early Release Request Summary Report Service Hibernate Implementation.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Feb 11, 2019)
 *@since OMIS 3.0
 *
 */
public class EarlyReleaseRequestSummaryReportServiceHibernateImpl
		implements EarlyReleaseRequestSummaryReportService {
	
	private static final String FIND_BY_OFFENDER_QUERY_NAME =
			"findEarlyReleaseRequestSummariesByOffender";
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private final SessionFactory sessionFactory;
	
	/**
	 * Constructor for EarlyReleaseRequestSummaryReportServiceHibernateImpl.
	 * 
	 * @param sessionFactory Session Factory
	 */
	public EarlyReleaseRequestSummaryReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}



	/** {@inheritDoc} */
	@Override
	public List<EarlyReleaseRequestSummary> findByOffender(
			final Offender offender) {
		@SuppressWarnings("unchecked")
		List<EarlyReleaseRequestSummary> summaries = this.sessionFactory
				.getCurrentSession()
				.getNamedQuery(FIND_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.list();
		
		return summaries;
	}

}
