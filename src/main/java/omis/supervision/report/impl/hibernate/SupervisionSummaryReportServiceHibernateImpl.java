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
package omis.supervision.report.impl.hibernate;

import java.util.Date;

import omis.offender.domain.Offender;
import omis.supervision.report.SupervisionSummary;
import omis.supervision.report.SupervisionSummaryReportService;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

/** Hibernate implementation of supervision summary report service.
 * @author Ryan Johns
 * @version 0.1.0 (Jul 28, 2015)
 * @since OMIS 3.0 */
public class SupervisionSummaryReportServiceHibernateImpl 
	implements SupervisionSummaryReportService {
	private static final String 
		FIND_SUPERVISION_SUMMARY_BY_OFFENDER_AND_DATE_QUERY_NAME =
			"findSupervisionSummaryByOffenderAndDate";
	private static final String DATE_PARAM_NAME = "date";
	private static final String OFFENDER_PARAM_NAME = "offender";
	private final SessionFactory sessionFactory;
	
	/** Constructor.
	 * @param sessionFactory - session factory. */
	public SupervisionSummaryReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	
	/** {@inheritDoc} */
	@Override
	public SupervisionSummary findByOffenderAndDate(final Offender offender,
			final Date date) {
		Query q = this.sessionFactory.getCurrentSession().getNamedQuery(
				FIND_SUPERVISION_SUMMARY_BY_OFFENDER_AND_DATE_QUERY_NAME);
		q.setTimestamp(DATE_PARAM_NAME, date);
		q.setEntity(OFFENDER_PARAM_NAME, offender);
		return (SupervisionSummary) q.setMaxResults(1).uniqueResult();
	}
}
