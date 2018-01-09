package omis.courtdocument.report.impl.hibernate;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import omis.courtdocument.report.CourtCaseDocumentAssociationProfileItemReportService;
import omis.offender.domain.Offender;

/** Hibernate implementation of court case document association profile item report service.
 * @author Ryan Johns
 * @version 0.1.0 (Dec 5, 2016)
 * @since OMIS 3.0 */
public class CourtCaseDocumentAssociationProfileItemReportServiceHibernateImpl
		implements CourtCaseDocumentAssociationProfileItemReportService {
	private static final String OFFENDER_PARAM = "offender";
	private static final String FIND_COURT_CASE_DOCUMENT_ASSOCIATION_COUNT_BY_OFFENDER 
		= "findCourtCaseDocumentAssociationCountByOffender";
	private final SessionFactory sessionFactory;
	
	/** Constructor.
	 * @param sessionFactory - session factory.  */
	public CourtCaseDocumentAssociationProfileItemReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/** {@inheritDoc} */
	public Integer findCourtCaseDocumentCountByOffender(final Offender offender) {
		Query q = this.sessionFactory.getCurrentSession().getNamedQuery(
				FIND_COURT_CASE_DOCUMENT_ASSOCIATION_COUNT_BY_OFFENDER);
		q.setEntity(OFFENDER_PARAM, offender);
		return ((Long)q.uniqueResult()).intValue();
	}
}
