package omis.bedplacement.report.impl.hibernate;

import java.util.Date;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import omis.bedplacement.report.BedPlacementProfileItemReportService;
import omis.offender.domain.Offender;

/** Hibernate implementation of bed placement profile item report service.
 * @author Ryan JOhns
 * @version 0.1.0 (Mar 28, 2016)
 * @since OMIS 3.0 */
public class BedPlacementProfileItemReportServiceHibernateImpl 
	implements BedPlacementProfileItemReportService {
	private final String 
		FIND_BED_PLACEMENT_COUNT_BY_OFFENDER_AND_DATE_QUERY_NAME 
			= "findBedPlacementCountByOffenderAndDate";
	private final String OFFENDER_PARAM_NAME = "offender";
	private final String EFFECTIVE_DATE_PARAM_NAME = "effectiveDate";
	
	private final SessionFactory sessionFactory;
	
	/** Constructor.
	 * @param sessionFactory - session factory. */
	public BedPlacementProfileItemReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean findBedPlacementExistenceByOffenderAndDate(
			final Offender offender, final Date effectiveDate) {
		Query q = this.sessionFactory.getCurrentSession().getNamedQuery(
				FIND_BED_PLACEMENT_COUNT_BY_OFFENDER_AND_DATE_QUERY_NAME);
		q.setEntity(OFFENDER_PARAM_NAME, offender);
		q.setDate(EFFECTIVE_DATE_PARAM_NAME, effectiveDate);
		return ((Long)q.uniqueResult() > 0);
	}
}
