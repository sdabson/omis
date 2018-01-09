package omis.offenderphoto.report.impl.hibernate;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import omis.offender.domain.Offender;
import omis.offenderphoto.report.OffenderPhotoAssociationProfileItemReportService;

/** Hibernate implementation of offender photo association profile item report 
 * service.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 12, 2016)
 * @since OMIS 3.0 */
public class 
	OffenderPhotoAssociationProfileItemReportServiceHibernateImpl
		implements OffenderPhotoAssociationProfileItemReportService {
	private final SessionFactory sessionFactory;
	
	private static final String 
		FIND_COUNT_BY_OFFENDER_AND_DATE_QUERY_NAME 
		= "findOffenderPhotoAssociationCountByOffender";
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	/** Constructor.
	 * @param sessionFactory - session factory. */
	public OffenderPhotoAssociationProfileItemReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/** {@inheritDoc} */
	@Override
	public Integer findOffenderPhotoAssociationCountByOffender(
			final Offender offender) {
		Query q = this.sessionFactory.getCurrentSession().getNamedQuery(
				FIND_COUNT_BY_OFFENDER_AND_DATE_QUERY_NAME);
		q.setEntity(OFFENDER_PARAM_NAME, offender);
		return ((Long) q.uniqueResult()).intValue();
	}
}
