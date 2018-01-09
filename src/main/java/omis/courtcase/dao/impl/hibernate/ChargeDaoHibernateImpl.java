package omis.courtcase.dao.impl.hibernate;

import java.util.List;

import omis.courtcase.dao.ChargeDao;
import omis.courtcase.domain.Charge;
import omis.courtcase.domain.CourtCase;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.offense.domain.Offense;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of data access object for charges.
 * 
 * @author Stephen Abson
 * @author Ryan Johns
 * @author Josh Divine
 * @version 0.1.5 (Aug 16, 2017)
 * @since OMIS 3.0
 */
public class ChargeDaoHibernateImpl
		extends GenericHibernateDaoImpl<Charge>
		implements ChargeDao {

	/* Query names. */
	
	private static final String FIND_BY_COURT_CASE_QUERY_NAME
		= "findChargesByCourtCase";
	
	private static final String FIND_QUERY_NAME = "findCharge";
	
	private static final String FIND_EXCLUDING_QUERY_NAME 
		= "findChargeExcluding";
	
	private static final String REMOVE_BY_COURT_CASE_EXCLUDING_QUERY_NAME
		= "removeChargesByCourtCaseExcluding";
	
	private static final String COUNT_BY_COURT_CASE_QUERY_NAME
		= "countChargesByCourtCase";
	
	/* Parameter names. */
	
	private static final String COURT_CASE_PARAM_NAME = "courtCase";

	private static final String OFFENSE_PARAM_NAME = "offense";

	private static final String COUNTS_PARAM_NAME = "counts";

	private static final String CHARGES_PARAM_NAME = "charges";

	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * charges with the specified resources.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public ChargeDaoHibernateImpl(final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<Charge> findByCourtCase(final CourtCase courtCase) {
		@SuppressWarnings("unchecked")
		List<Charge> charges = this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_COURT_CASE_QUERY_NAME)
				.setParameter(COURT_CASE_PARAM_NAME, courtCase).list();
		return charges;
	}

	/** {@inheritDoc} */
	@Override
	public Charge find(final CourtCase courtCase, final Offense offense,
			final Integer counts) {
		Charge charge = (Charge) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(COURT_CASE_PARAM_NAME, courtCase)
				.setParameter(OFFENSE_PARAM_NAME, offense)
				.setParameter(COUNTS_PARAM_NAME, counts)
				.uniqueResult();
		return charge;
	}
	
	/** {@inheritDoc} */
	@Override
	public Charge findExcluding(final CourtCase courtCase, 
			final Offense offense, final Integer counts, 
			final Charge...excluding) {
		Charge charge = (Charge) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(COURT_CASE_PARAM_NAME, courtCase)
				.setParameter(OFFENSE_PARAM_NAME, offense)
				.setParameter(COUNTS_PARAM_NAME, counts)
				.setParameterList(CHARGES_PARAM_NAME, excluding)
				.uniqueResult();
		return charge;
	}

	/** {@inheritDoc} */
	@Override
	public void removeByCourtCaseExcluding(final CourtCase courtCase,
			final Charge... excludedCharges) {
		this.getSessionFactory().getCurrentSession().getNamedQuery(
				REMOVE_BY_COURT_CASE_EXCLUDING_QUERY_NAME)
			.setParameter(COURT_CASE_PARAM_NAME, courtCase)
			.setParameterList(CHARGES_PARAM_NAME, excludedCharges)
			.executeUpdate();
	}

	/** {@inheritDoc} */
	@Override
	public Long countByCourtCase(final CourtCase courtCase) {
		Long count = (Long) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(COUNT_BY_COURT_CASE_QUERY_NAME)
				.setParameter(COURT_CASE_PARAM_NAME, courtCase)
				.uniqueResult();
		return count;
	}
}