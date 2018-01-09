package omis.bedplacement.report.impl.hibernate;

import java.util.Date;

import org.hibernate.SessionFactory;

import omis.bedplacement.report.BedPlacementLookupDelegate;
import omis.offender.domain.Offender;

/**
 * Bed placement lookup delegate hibernate implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Oct 24, 2017)
 * @since OMIS 3.0
 */
public class BedPlacementLookupDelegateHibernateImpl 
	implements BedPlacementLookupDelegate {

	/* Helpers. */
	
	private final SessionFactory sessionFactory;
	
	/* Query names. */
	
	private static final String FIND_HOUSING_UNIT_NAME_BY_OFFENDER_ON_DATE
		= "findHousingUnitNameByOffenderOnDate";
	
	/* Parameter names. */
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	private static final String DATE_PARAM_NAME = "date";
	
	/* Constructor. */
	
	/**
	 * Instantiates an instance of bed placement lookup delegate with the specified session factory.
	 * 
	 * @param sessionFactory session factory
	 */
	public BedPlacementLookupDelegateHibernateImpl(final SessionFactory sessionFactory) {
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
	
	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public String findUnitNameByOffender(final Offender offender, final Date date) {
		String unitName = (String) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_HOUSING_UNIT_NAME_BY_OFFENDER_ON_DATE)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setDate(DATE_PARAM_NAME, date)
				.uniqueResult();
		return unitName;
	}
}