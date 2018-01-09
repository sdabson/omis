package omis.bedplacement.report.impl.hibernate;

import java.util.Date;

import omis.bedplacement.report.BedPlacementSummary;
import omis.bedplacement.report.BedPlacementSummaryReportService;
import omis.offender.domain.Offender;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

/** Hibernate implementation of bed placement summary report service.
 * @author Ryan Johns
 * @version 0.1.0 (Jul 27, 2015)
 * @since OMIS 3.0 */
public class BedPlacementSummaryReportServiceHibernateImpl 
				implements BedPlacementSummaryReportService {
	private static final String 
					FIND_BED_PLACEMENT_BY_OFFENDER_AND_DATE_QUERY_NAME = 
					"findBedPlacementByOffenderAndDate";
	private final SessionFactory sessionFactory;
	
	/** Constructor.
	 * @param sessionFactory - session factory. */
	public BedPlacementSummaryReportServiceHibernateImpl(
				final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/** {@inheritDoc} */
	@Override
	public BedPlacementSummary findBedPlacementSummaryByOffenderAndDate(
				final Offender offender, final Date date) {
		Query q = this.sessionFactory.getCurrentSession().getNamedQuery(
						FIND_BED_PLACEMENT_BY_OFFENDER_AND_DATE_QUERY_NAME);
		q.setEntity("offender", offender);
		q.setTimestamp("date", date);
		return (BedPlacementSummary) q.setMaxResults(1).uniqueResult();
	}

}
