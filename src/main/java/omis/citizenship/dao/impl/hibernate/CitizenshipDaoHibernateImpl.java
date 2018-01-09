package omis.citizenship.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.citizenship.dao.CitizenshipDao;
import omis.citizenship.domain.Citizenship;
import omis.country.domain.Country;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.offender.domain.Offender;

/**
 * Hibernate implementation of data access object for citizenships.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 24, 2014)
 * @since OMIS 3.0
 */
public class CitizenshipDaoHibernateImpl
		extends GenericHibernateDaoImpl<Citizenship>
		implements CitizenshipDao {

	private static final String FIND_BY_OFFENDER_QUERY_NAME
		= "findCitizenshipByOffender";
	
	private static final String FIND_BY_COUNTRY_QUERY_NAME
		= "findCitizenshipsByCountry";
	
	private static final String OFFENDER_PARAM_NAME = "offender";

	private static final String COUNTRY_PARAM_NAME = "country";

	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * citizenships.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public CitizenshipDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public Citizenship findByOffender(final Offender offender) {
		Citizenship citizenship = (Citizenship) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender).uniqueResult();
		return citizenship;
	}

	/** {@inheritDoc} */
	@Override
	public List<Citizenship> findByCountry(final Country country) {
		@SuppressWarnings("unchecked")
		List<Citizenship> citizenships = this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_BY_COUNTRY_QUERY_NAME)
				.setParameter(COUNTRY_PARAM_NAME, country).list();
		return citizenships;
	}
}