package omis.health.report.delegate.impl.hibernate;

import java.util.Date;

import org.hibernate.SessionFactory;

import omis.health.report.delegate.UnitLookUpDelegate;
import omis.offender.domain.Offender;

/**
 * Hibernate delegate to look up legacy unit data.
 * 
 * <p>This delegate will be removed from production.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (May 21, 2014)
 * @since OMIS 3.0
 */
// TODO: Technical debt - remove this class. Use unit entities instead.
public class LegacyUnitLookUpDelegateHibernateImpl
		implements UnitLookUpDelegate {

	private final SessionFactory sessionFactory;
	
	/**
	 * Instantiates an Hibernate delegate to look up legacy unit data.
	 * 
	 * @param sessionFactory session factory
	 */
	public LegacyUnitLookUpDelegateHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/** {@inheritDoc} */
	@Override
	public String findUnitAbbreviationForOffender(final Offender offender,
			final Date date) {
		String result = (String)
				this.sessionFactory.getCurrentSession().createSQLQuery(
						"select legacy_find_ofndr_unit_cd_fun("
						+ ":offenderNumber, :date) from dual")
				.setInteger("offenderNumber", offender.getOffenderNumber())
				.setTimestamp("date", date).uniqueResult();
		return result;
	}

	/** {@inheritDoc} */
	@Override
	public String findUnitAbbreviationByOffenderNumber(
			final Integer offenderNumber, final Date date) {
		String result = (String)
				this.sessionFactory.getCurrentSession().createSQLQuery(
				"select legacy_find_ofndr_unit_cd_fun(:offenderNumber, :date) from dual")
				.setInteger("offenderNumber", offenderNumber)
				.setTimestamp("date", date).uniqueResult();
		return result;
	}
}