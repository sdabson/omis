package omis.offenderphoto.report.impl.hibernate;

import omis.offender.domain.Offender;
import omis.offenderphoto.report.OffenderPhotoAssociationSummary;
import omis.offenderphoto.report.OffenderPhotoAssociationSummaryReportService;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

/** Hibernate implementation of offender photo summary report service.
 * @author Ryan Johns
 * @version 0.1.0 (July 28, 2015)
 * @since OMIS 3.0 */
public class OffenderPhotoAssociationSummaryReportServiceHibernateImpl 
	implements OffenderPhotoAssociationSummaryReportService {
	private static final String 
		FIND_OFFENDER_PHOTO_SUMMARY_BY_OFFENDER_QUERY_NAME = 
		"summarizeOffenderPhotoByOffender";
	private static final String OFFENDER_PARAM_NAME = "offender";
	private SessionFactory sessionFactory;
	
	/** Constructor.
	 * @param sessionFactory - session factory. */
	public OffenderPhotoAssociationSummaryReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/** {@inheritDoc} */
	@Override
	public OffenderPhotoAssociationSummary findByOffender(
			final Offender offender) {
		Query q = this.sessionFactory.getCurrentSession()
				.getNamedQuery(
						FIND_OFFENDER_PHOTO_SUMMARY_BY_OFFENDER_QUERY_NAME);
		q.setEntity(OFFENDER_PARAM_NAME, offender);
		return (OffenderPhotoAssociationSummary) q.uniqueResult();
	}
}
