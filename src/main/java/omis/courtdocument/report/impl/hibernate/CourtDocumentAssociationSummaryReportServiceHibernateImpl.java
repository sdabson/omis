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
package omis.courtdocument.report.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.courtdocument.report.CourtDocumentAssociationSummary;
import omis.courtdocument.report
	.CourtDocumentAssociationSummaryReportService;
import omis.offender.domain.Offender;

/** 
 * Hibernate implementation of court case document association summary report
 * service.
 * 
 * @author Ryan Johns
 * @author Josh Divine
 * @version 0.1.2 (Aug 7, 2018)
 * @since OMIS 3.0 
 */
public class CourtDocumentAssociationSummaryReportServiceHibernateImpl
		implements CourtDocumentAssociationSummaryReportService {
	
	/* Queries */
	
	private static final String 
			FIND_CRT_DCMNT_ASSOC_SUM_BY_OFFENDER_QRY_NAME = 
			"findCourtDocumentAssociationSummariesByOffender";
	
	private static final String 
			FIND_CRT_DCMNT_ASSOC_SUM_WO_DCKT_BY_OFFENDER_QRY_NAME =
			"findCourtDocumentAssociationSummariesWithoutDocketByOffender";
	
	/* Parameters */
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private final SessionFactory sessionFactory;
	
	/** 
	 * Constructor.
	 * 
	 * @param sessionFactory session factory
	 */
	public CourtDocumentAssociationSummaryReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<CourtDocumentAssociationSummary> findByOffender(
			final Offender offender) {
		@SuppressWarnings("unchecked")
		List<CourtDocumentAssociationSummary> summaries = this.sessionFactory
				.getCurrentSession()
				.getNamedQuery(FIND_CRT_DCMNT_ASSOC_SUM_BY_OFFENDER_QRY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setReadOnly(true)
				.list();
		return summaries;
	}

	/** {@inheritDoc} */
	@Override
	public List<CourtDocumentAssociationSummary> findByOffenderWithoutDocket(
			final Offender offender) {
		@SuppressWarnings("unchecked")
		List<CourtDocumentAssociationSummary> summaries = this.sessionFactory
				.getCurrentSession()
				.getNamedQuery(
						FIND_CRT_DCMNT_ASSOC_SUM_WO_DCKT_BY_OFFENDER_QRY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setReadOnly(true)
				.list();
		return summaries;
	}
}