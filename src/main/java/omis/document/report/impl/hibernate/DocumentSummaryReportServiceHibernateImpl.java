package omis.document.report.impl.hibernate;

import java.util.List;

import omis.document.report.DocumentSummary;
import omis.document.report.DocumentSummaryReportService;
import omis.offender.domain.Offender;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

/** Hibernate implementation of document summary report service.
 * @author Ryan Johns
 * @version 0.1.0 (Dec 9, 2015)
 * @since OMIS 3.0 */
public class DocumentSummaryReportServiceHibernateImpl 
	implements DocumentSummaryReportService {
	/* Queries */
	private static final String 
		FIND_DOCUMENT_SUMMARIES_BY_OFFENDER_QUERY_NAME =
			"findDocumentSummariesByOffender";
	
	/* Parameters */
	private static final String OFFENDER_PARAM_NAME = "offender";
	private final SessionFactory sessionFactory;
	
	/** Constructor.
	 * @param sessionFactory - session factory. */
	public DocumentSummaryReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<DocumentSummary> findByOffender(final Offender offender) {
		Query q = this.sessionFactory.getCurrentSession().getNamedQuery(
				FIND_DOCUMENT_SUMMARIES_BY_OFFENDER_QUERY_NAME);
		q.setEntity(OFFENDER_PARAM_NAME, offender);
		return this.cast(q.list());
	}
	
	/* casts to document summaries. */
	@SuppressWarnings("unchecked")
	private List<DocumentSummary> cast(final List<?> objs) {
		return (List<DocumentSummary>) objs;
	}

}
