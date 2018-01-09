package omis.bedplacement.report.impl.hibernate;

import java.util.Date;

import org.hibernate.SessionFactory;

import omis.bedplacement.report.BedPlacementLookupDelegate;
import omis.offender.domain.Offender;

/**
 * Legacy bed placement lookup delegate hibernate implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Oct. 24, 2017)
 * @since OMIS 3.0
 */
public class LegacyBedPlacementLookupDelegateHibernateImpl
	implements BedPlacementLookupDelegate {

	/* Helpers. */
	
	final SessionFactory sessionFactory;
	
	/* Query names. */
	
	private static final String LEGACY_FIND_UNIT_NAME_BY_OFFENDER_QUERY_NAME
		= "findLegacyUnitNameByOffender";
	
	/* Parameter names. */
	
	private static final String OFFENDER_NUMBER_PARAM_NAME = "offenderNumber";
	
	/* Constructor. */
	
	/**
	 * Instantiates an instance of legacy bed placement lookup delegate with the
	 * specified session factory.
	 * 
	 * @param sessionFactory session factory
	 */
	public LegacyBedPlacementLookupDelegateHibernateImpl(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/* Getters. */
	
	/**
	 * Returns session factory.
	 * 
	 * @return session factory
	 */
	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}
	
	/** {@inheritDoc} */
	@Override
	public String findUnitNameByOffender(final Offender offender, final Date date) {
		String unitName = (String) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(LEGACY_FIND_UNIT_NAME_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_NUMBER_PARAM_NAME, offender.getOffenderNumber())
				.uniqueResult();
		return unitName;
	}

}