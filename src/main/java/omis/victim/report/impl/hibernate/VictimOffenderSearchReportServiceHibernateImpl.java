package omis.victim.report.impl.hibernate;

import java.util.Date;
import java.util.List;

import omis.offender.report.OffenderSummary;
import omis.offender.report.delegate.hibernate.OffenderReportDelegate;
import omis.victim.report.VictimOffenderSearchReportService;

/**
 * Hibernate implementation of service to report offenders for victims.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Aug 12, 2015)
 * @since OMIS 3.0
 */
public class VictimOffenderSearchReportServiceHibernateImpl
		implements VictimOffenderSearchReportService {
	
	/* Resources. */

	private final OffenderReportDelegate offenderReportDelegate;
	
	/* Constructors. */
	
	/**
	 * Instantiates Hibernate implementation of service to report offenders
	 * for victims.
	 * 
	 * @param offenderReportDelegate delegate to report offenders
	 */
	public VictimOffenderSearchReportServiceHibernateImpl(
			final OffenderReportDelegate offenderReportDelegate) {
		this.offenderReportDelegate = offenderReportDelegate;
	}
	
	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<OffenderSummary> summarizeByName(
			final String lastName, final String firstName) {
		return this.offenderReportDelegate.summarizeByName(lastName, firstName);
	}

	/** {@inheritDoc} */
	@Override
	public List<OffenderSummary> summarizeByOffenderNumber(
			final Integer offenderNumber) {
		return this.offenderReportDelegate
				.summarizeByOffenderNumber(offenderNumber);
	}

	/** {@inheritDoc} */
	@Override
	public List<OffenderSummary> summarizeBySocialSecurityNumber(
			final Integer socialSecurityNumber) {
		return this.offenderReportDelegate
				.summarizeBySocialSecurityNumber(socialSecurityNumber);
	}

	/** {@inheritDoc} */
	@Override
	public List<OffenderSummary> summarizeByBirthDate(
			final Date birthDate) {
		return this.offenderReportDelegate.summarizeByBirthDate(birthDate);
	}
}