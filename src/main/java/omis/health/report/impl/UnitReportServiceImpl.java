package omis.health.report.impl;

import java.util.Date;

import omis.health.report.UnitReportService;
import omis.health.report.delegate.UnitLookUpDelegate;
import omis.offender.domain.Offender;

/**
 * Implementation of unit report service.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (May 21, 2014)
 * @since OMIS 3.0
 */
public class UnitReportServiceImpl
		implements UnitReportService {

	private final UnitLookUpDelegate unitLookUpDelegate;
	
	/**
	 * Implementation of unit report service.
	 * 
	 * @param unitLookUpDelegate delegate to lookup unit information
	 */
	public UnitReportServiceImpl(
			final UnitLookUpDelegate unitLookUpDelegate) {
		this.unitLookUpDelegate = unitLookUpDelegate;
	}
	
	/** {@inheritDoc} */
	@Override
	public String findUnitAbbreviation(
			final Offender offender, final Date date) {
		return this.unitLookUpDelegate
				.findUnitAbbreviationForOffender(offender, date);
	}
}