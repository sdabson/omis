package omis.health.report.impl.hibernate;

import java.util.ArrayList;
import java.util.List;

import omis.facility.domain.Facility;
import omis.health.report.InternalReferralReportService;
import omis.health.report.ResolvedInternalReferralSummary;
import omis.health.report.ScheduledInternalReferralSummary;
import omis.health.report.delegate.UnitLookUpDelegate;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

/** Hibernate implementation of scheduled referrals.
 * @author Ryan Johns
 * @version 0.1.1 (Apr 30, 2014)
 * @since OMIS 3.0 */
public class InternalReferralReportServiceHibernateImpl
		implements InternalReferralReportService {
	private static final String
	FIND_SCHEDULED_INTERNAL_REFERRALS_SUMMARY_BY_FACILITY =
		"findScheduledInternalReferralSummaryByFacility";

	private static final String
	FIND_RESOLVED_INTERNAL_REFERRALS_SUMMARY_BY_FACILITY =
		"findResolvedInternalReferralSummaryByFacility";

	private final SessionFactory sessionFactory;
	
	private final UnitLookUpDelegate unitLookUpDelegate;

	/** Constructor.
	 * @param sessionFactory session factory. */
	public InternalReferralReportServiceHibernateImpl(
			final SessionFactory sessionFactory,
			final UnitLookUpDelegate unitLookUpDelegate) {
		this.sessionFactory = sessionFactory;
		this.unitLookUpDelegate = unitLookUpDelegate;
	}

	/** {@inheritDoc} */
	@Override
	public List<ScheduledInternalReferralSummary> findScheduledInternalReferrals(
			final Facility facility) {
		final Query q = this.sessionFactory.getCurrentSession().getNamedQuery(
				FIND_SCHEDULED_INTERNAL_REFERRALS_SUMMARY_BY_FACILITY);

		q.setParameter("facility", facility);

		@SuppressWarnings("unchecked")
		final List<ScheduledInternalReferralSummary> result =
				(List<ScheduledInternalReferralSummary>) q.list();

		// Look away now... [SA]
		
		// TODO: Remove this when units can be looked up from OMIS 3.0 data
		final List<ScheduledInternalReferralSummary> summaries =
				new ArrayList<ScheduledInternalReferralSummary>();
		for (ScheduledInternalReferralSummary summary : result) {
			String unitName = this.unitLookUpDelegate
					.findUnitAbbreviationByOffenderNumber(
							summary.getOffenderNumber(), null);
			summaries.add(new ScheduledInternalReferralSummary(
				summary.getAppointmentId(),
				summary.getReferralId(),
				summary.getOffenderLastName(),
				summary.getOffenderMiddleName(),
				summary.getOffenderFirstName(),
				summary.getOffenderNumber(),
				summary.getScheduledDate(),
				summary.getProviderLastName(),
				summary.getProviderMiddleName(),
				summary.getProviderFirstName(),
				summary.getProviderTitleName(),
				unitName,
				summary.getNotes(),
				summary.getLocationDesignator(),
				summary.getReasonName()
			));
		}
		return summaries;
	}

	/** {@inheritDoc} */
	@Override
	public List<ResolvedInternalReferralSummary> findResolvedInternalReferrals(
			final Facility facility) {
		final Query q = this.sessionFactory.getCurrentSession().getNamedQuery(
				FIND_RESOLVED_INTERNAL_REFERRALS_SUMMARY_BY_FACILITY);

		q.setParameter("facility", facility);
		@SuppressWarnings("unchecked")
		final List<ResolvedInternalReferralSummary> result =
			(List<ResolvedInternalReferralSummary>) q.list();

		return result;
	}

}
