package omis.courtdocument.report.impl.hibernate;

import java.util.List;

import omis.courtdocument.report.CourtCaseDocumentAssociationSummary;
import omis.courtdocument.report
	.CourtCaseDocumentAssociationSummaryReportService;
import omis.offender.domain.Offender;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

/** Hibernate implementation of court case document association summary report
 * service.
 * @author Ryan Johns
 * @version 0.1.0 (Nov 30, 2015)
 * @since OMIS 3.0 */
public class CourtCaseDocumentAssociationSummaryReportServiceHibernateImpl
		implements CourtCaseDocumentAssociationSummaryReportService {
	/* Queries */
	private static final String 
		FIND_CRT_CSE_DCMNT_ASSOC_SUM_BY_OFFENDER_QRY_NAME = 
		"findCourtCaseDocumentAssociationSummariesByOffender";
	
	/* Parameters */
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private final SessionFactory sessionFactory;
	
	/** Constructor.
	 * @param sessionFactory - session factory. */
	public CourtCaseDocumentAssociationSummaryReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<CourtCaseDocumentAssociationSummary> findByOffender(
			final Offender offender) {
		Query q = this.sessionFactory.getCurrentSession().getNamedQuery(
				FIND_CRT_CSE_DCMNT_ASSOC_SUM_BY_OFFENDER_QRY_NAME);
		q.setParameter(OFFENDER_PARAM_NAME, offender);
		return this.cast(q.list());
	}
	
	/* Private cast to list of court case document associations summary. */
	@SuppressWarnings("unchecked")
	private List<CourtCaseDocumentAssociationSummary> cast(final List<?> objs) {
		return (List<CourtCaseDocumentAssociationSummary>) objs;
	}
}
