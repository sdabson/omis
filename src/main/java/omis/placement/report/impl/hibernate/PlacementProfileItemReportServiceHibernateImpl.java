package omis.placement.report.impl.hibernate;

import java.util.Date;

import omis.offender.domain.Offender;
import omis.placement.report.PlacementProfileItemReportService;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

/** Hibernate implementation of placement profile report service.
 * @author Ryan Johns
 * @version 0.1.0 (Mar 16, 2016)
 * @since OMIS 3.0 */
public class PlacementProfileItemReportServiceHibernateImpl 
				implements PlacementProfileItemReportService {
	private static final String 
					FIND_CORRECTIONAL_STATUS_BY_OFFENDER_DATE_QUERY_NAME
						= "findCorrectionalStatusTermForOffenderOnDate";
	private static final String
					FIND_SUPERVISORY_ORG_TERM_BY_OFFENDER_DATE_QUERY_NAME
						= "findSupervisoryOrganizationTermForOffenderOnDate";
	private static final String OFFENDER_PARAM_NAME = "offender";
	private static final String DATE_PARAM_NAME = "date";
	
	private final SessionFactory sessionFactory;
	
	/** Constructor.
	 * @param sessionFactory - session factory. */
	public PlacementProfileItemReportServiceHibernateImpl(
					final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/** {@inheritDoc} */
	@Override 
	public boolean findPlacementExistenceByOffenderAndDate(
					final Offender offender, final Date effectiveDate) {
		return (
				this.findCorrectionalStatusExistenceByOffenderAndDate(
						offender, effectiveDate) 
				&& this.findSupervisoryOrganizationTermByOffenderAndDate(
						offender, effectiveDate));
	}
	
	private boolean findCorrectionalStatusExistenceByOffenderAndDate(
					final Offender offender, final Date effectiveDate) {
		Query q = this.sessionFactory.getCurrentSession().getNamedQuery(
						FIND_CORRECTIONAL_STATUS_BY_OFFENDER_DATE_QUERY_NAME);
		q.setEntity(OFFENDER_PARAM_NAME, offender);
		q.setDate(DATE_PARAM_NAME, effectiveDate);
		return (!q.list().isEmpty());
	}
	
	private boolean findSupervisoryOrganizationTermByOffenderAndDate(
					final Offender offender, final Date effectiveDate) {
		Query q = this.sessionFactory.getCurrentSession().getNamedQuery(
						FIND_SUPERVISORY_ORG_TERM_BY_OFFENDER_DATE_QUERY_NAME);
		q.setEntity(OFFENDER_PARAM_NAME, offender);
		q.setDate(DATE_PARAM_NAME, effectiveDate);
		return (!q.list().isEmpty());
	}
}