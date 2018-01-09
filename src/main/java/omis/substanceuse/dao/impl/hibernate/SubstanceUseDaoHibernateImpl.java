package omis.substanceuse.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.offender.domain.Offender;
import omis.substance.domain.Substance;
import omis.substanceuse.dao.SubstanceUseDao;
import omis.substanceuse.domain.SubstanceUse;

/**
 * Substance use data access object hibernate implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Nov 17, 2016)
 * @since OMIS 3.0
 */
public class SubstanceUseDaoHibernateImpl 
	extends GenericHibernateDaoImpl<SubstanceUse>
	implements SubstanceUseDao {

	/* Query names. */
	
	public static final String FIND_USES_BY_OFFENDER_QUERY_NAME
		= "findUsesByOffender";
	
	public static final String FIND_SUBSTANCE_USE_QUERY_NAME
		= "findSubstanceUse";
	
	public static final String FIND_SUBSTANCE_USE_EXCLUDING_QUERY_NAME
		= "findSubstanceUseExcluding";
	
	/* Parameter names. */
	
	public static final String OFFENDER_PARAM_NAME = "offender";
	
	public static final String SUBSTANCE_PARAM_NAME = "substance";
	
	public static final String SUBSTANCE_USE_PARAM_NAME = "substanceUse";

	/**
	 * Instantiates an instance of substance use data access object with the
	 * specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public SubstanceUseDaoHibernateImpl(
			final SessionFactory sessionFactory, 
			final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public List<SubstanceUse> findByOffender(final Offender offender) {
		@SuppressWarnings("unchecked")
		List<SubstanceUse> uses = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_USES_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.list();
		return uses;
	}

	/** {@inheritDoc} */
	@Override
	public SubstanceUse find(final Offender offender, 
			final Substance substance) {
		SubstanceUse substanceUse = (SubstanceUse) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_SUBSTANCE_USE_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(SUBSTANCE_PARAM_NAME, substance)
				.uniqueResult();
		return substanceUse;
	}

	/** {@inheritDoc} */
	@Override
	public SubstanceUse findExcluding(SubstanceUse use, Offender offender, Substance substance) {
		SubstanceUse substanceUse = (SubstanceUse) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_SUBSTANCE_USE_EXCLUDING_QUERY_NAME)
				.setParameter(SUBSTANCE_USE_PARAM_NAME, use)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(SUBSTANCE_PARAM_NAME, substance)
				.uniqueResult();
		return substanceUse;
	}
}