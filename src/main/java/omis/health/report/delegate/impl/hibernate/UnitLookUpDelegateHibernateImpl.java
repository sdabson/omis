package omis.health.report.delegate.impl.hibernate;

import java.util.Date;

import org.hibernate.SessionFactory;

import omis.health.report.delegate.UnitLookUpDelegate;
import omis.offender.domain.Offender;

/**
 * Hibernate implementation of unit look up delegate.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class UnitLookUpDelegateHibernateImpl
		implements UnitLookUpDelegate {
	
	private final SessionFactory sessionFactory;

	/**
	 * Instantiates Hibernate implementation of unit look up delegate.
	 * 
	 * @param sessionFactory session factory
	 */
	public UnitLookUpDelegateHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/** {@inheritDoc} */
	@Override
	public String findUnitAbbreviationForOffender(
			final Offender offender, final Date date) {
		return this.findUnitAbbreviationByOffenderNumber(
				offender.getOffenderNumber(), date);
	}

	/** {@inheritDoc} */
	@Override
	public String findUnitAbbreviationByOffenderNumber(
			final Integer offenderNumber, final Date date) {
		
		// TODO - Move query into a HBM file in bed placement module - SA
		// Alternatively, once legacy lookup is replaced, add unit look up
		// to queries for report objects that expose unit abbreviation
		return (String) this.sessionFactory.getCurrentSession().createQuery(
				"select bedPlacement.bed.room.unit.name"
				+ " from omis.bedplacement.domain.BedPlacement bedPlacement"
				+ " where bedPlacement.offender.offenderNumber = :offenderNumber"
				+ "   and (bedPlacement.dateRange.startDate <= :effectiveDate"
				+ "     and (bedPlacement.dateRange.endDate > :effectiveDate"
				+ "       or bedPlacement.dateRange.endDate is null))"
				+ "   and bedPlacement.confirmed = true")
			.setParameter("offenderNumber", offenderNumber)
			.setTimestamp("effectiveDate", date)
			.uniqueResult();
	}
}