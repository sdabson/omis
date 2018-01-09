package omis.substanceuse.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.substanceuse.dao.UseFrequencyDao;
import omis.substanceuse.domain.UseFrequency;

/**
 * Use frequency data access object hibernate implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Nov 18, 2016)
 * @since OMIS 3.0
 */
public class UseFrequencyDaoHibernateImpl 
	extends GenericHibernateDaoImpl<UseFrequency>
	implements UseFrequencyDao {

	/* Query names. */
	
	private static final String FIND_VALID_FREQUENCIES_QUERY_NAME 
		= "findValidUseFrequencies";
	
	public UseFrequencyDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public List<UseFrequency> findAll() {
		@SuppressWarnings("unchecked")
		List<UseFrequency> frequencies = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_VALID_FREQUENCIES_QUERY_NAME)
				.list();
		return frequencies;
	}
}