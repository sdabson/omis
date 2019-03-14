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

import org.hibernate.SessionFactory;
import omis.earlyreleasetracking.report.EarlyReleaseRequestProfileItemReportService;
import omis.offender.domain.Offender;

/**
 * Early Release Request Profile Item Report Service Hibernate Implementation.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Mar 7, 2019)
 *@since OMIS 3.0
 *
 */
public class EarlyReleaseRequestProfileItemReportServiceHibernateImpl
		implements EarlyReleaseRequestProfileItemReportService {
	
	private static final String FIND_STATUS_BY_OFFENDER_QUERY_NAME =
			"findEarlyReleaseRequestStatusByOffender";
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private final SessionFactory sessionFactory;
	
	/**
	 * Constructor for Early Release Request Profile Item Report Service.
	 * 
	 * @param sessionFactory Session Factory
	 */
	public EarlyReleaseRequestProfileItemReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/** {@inheritDoc} */
	@Override
	public String findEarlyReleaseRequestStatusByOffender(
			final Offender offender) {
		String status = (String) this.sessionFactory.getCurrentSession()
				.getNamedQuery(FIND_STATUS_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setMaxResults(1)
				.uniqueResult();
		
		return status;
	}
}
