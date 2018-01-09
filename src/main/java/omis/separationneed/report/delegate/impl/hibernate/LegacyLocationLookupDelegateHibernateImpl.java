package omis.separationneed.report.delegate.impl.hibernate;

import java.util.Date;

import org.hibernate.SessionFactory;

import omis.offender.domain.Offender;
import omis.separationneed.report.delegate.LegacyLocationLookupDelegate;

/**
 * Legacy location lookup delegate hibernate implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Sept 23, 2016)
 * @since OMIS 3.0
 */
public class LegacyLocationLookupDelegateHibernateImpl
	implements LegacyLocationLookupDelegate {
	
	private SessionFactory sessionFactory;
	
	/**
	 * Instantiates an instance of legacy location lookup delegate
	 * with the specified session factory.
	 * 
	 * @param sessioFactory session factory
	 */
	public LegacyLocationLookupDelegateHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/** {@inheritDoc} */
	@Override
	public String findLocationForOffender(
			final Offender offender, final Date date) {
		String result = (String)
				this.sessionFactory.getCurrentSession().createSQLQuery(
						"select legacy_find_ofndr_bed_cd_fun("
						+ ":offenderNumber, :date) from dual")
				.setInteger("offenderNumber", offender.getOffenderNumber())
				.setTimestamp("date", date).uniqueResult();
		return result;
	}

}