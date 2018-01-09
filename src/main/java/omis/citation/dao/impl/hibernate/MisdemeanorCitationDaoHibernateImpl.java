package omis.citation.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.citation.dao.MisdemeanorCitationDao;
import omis.citation.domain.MisdemeanorCitation;
import omis.citation.domain.MisdemeanorDisposition;
import omis.citation.domain.MisdemeanorOffense;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.datatype.Month;
import omis.offender.domain.Offender;
import omis.region.domain.State;

/**
 * Hibernate implementation of data access object for misdemeanor citations.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (Aug 09, 2016)
 * @since OMIS 3.0
 */

public class MisdemeanorCitationDaoHibernateImpl 
		extends GenericHibernateDaoImpl<MisdemeanorCitation> 
		implements MisdemeanorCitationDao {

	/* Query names. */
	
	private static final String FIND_EXCLUDING_QUERY_NAME 
		= "findExcludingCitation";
	
	private static final String FIND_BY_OFFENDER_QUERY_NAME 
		= "findCitationByOffender";
	
	private static final String FIND_CITATION_QUERY_NAME
		= "findCitations";
	
	/* Parameter names. */
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private static final String YEAR_PARAM_NAME = "year";
	
	private static final String DAY_PARAM_NAME = "day";
	
	private static final String STATE_PARAM_NAME = "state";
	
	private static final String MONTH_PARAM_NAME = "month";
	
	private static final String OFFENSE_PARAM_NAME = "offense";
	
	private static final String DISPOSITION_PARAM_NAME = "disposition";
	
	private static final String EXCLUDED_CITATION_PARAM_NAME 
		= "excludedCitation";
	
	/* Constructor.*/
	
	/**
	 * Instantiates a data access object for misdemeanor citations with the
	 * specified session factory and entity name.
	 *
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public MisdemeanorCitationDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method implementations. */

	/** {@inheritDoc} */
	@Override
	public MisdemeanorCitation findExcluding(
			final Offender offender,
			final State state,
			final MisdemeanorOffense offense,
			final Integer day,
			final Month month,
			final Integer year, 
			final MisdemeanorDisposition disposition,
			final MisdemeanorCitation excludedCitation) {
		MisdemeanorCitation misdemeanorCitation = (MisdemeanorCitation)
				getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(STATE_PARAM_NAME, state)
				.setParameter(DAY_PARAM_NAME, day)
				.setParameter(MONTH_PARAM_NAME, month)
				.setParameter(YEAR_PARAM_NAME, year)
				.setParameter(OFFENSE_PARAM_NAME, offense)
				.setParameter(DISPOSITION_PARAM_NAME, disposition)
				.setParameter(EXCLUDED_CITATION_PARAM_NAME, excludedCitation)
				.uniqueResult();
		return misdemeanorCitation;
	}

	/** {@inheritDoc} */
	@Override
	public List<MisdemeanorCitation> findByOffender(Offender offender) {
		@SuppressWarnings("unchecked")
		List<MisdemeanorCitation> citations = getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender).list();
		return citations;
	}
	
	/** {@inheritDoc} */
	@Override
	public MisdemeanorCitation find(Offender offender, 
			MisdemeanorOffense offense, State state, Integer day, Month month, Integer year, 
			MisdemeanorDisposition disposition) {
		MisdemeanorCitation misdemeanorCitation = (MisdemeanorCitation)
				getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_CITATION_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(OFFENSE_PARAM_NAME, offense)
				.setParameter(STATE_PARAM_NAME, state)
				.setParameter(DAY_PARAM_NAME, day)
				.setParameter(MONTH_PARAM_NAME, month)
				.setParameter(YEAR_PARAM_NAME, year)
				.setParameter(DISPOSITION_PARAM_NAME, disposition)
				.uniqueResult();
		return misdemeanorCitation;
	}

}
