package omis.offenseterm.report.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.offenseterm.report.OffenseTermProfileItemService;
import omis.person.domain.Person;

/** 
 * Service implementation for offense term profile related operations.
 * 
 * @author Josh Divine
 * @version 0.1.0 (May 16, 2017)
 * @since OMIS 3.0 
 */
public class OffenseTermProfileItemServiceImpl 
	implements OffenseTermProfileItemService {

	private static final String FIND_OFFENSE_TERM_COUNT_BY_PERSON_QUERY_NAME 
		= "findCourtCaseCountByDefendant";
	
	private static final String 
		FIND_CURRENT_OFFENSE_TERM_COUNT_BY_PERSON_QUERY_NAME 
			= "findActiveSentenceCountByPerson";
	
	private static final String PERSON_PARAM_NAME = "person";
	
	private static final String DEFENDANT_PARAM_NAME = "defendant";
	
	private final SessionFactory sessionFactory;

	/**
	 * Constructor.
	 * 
	 * @param sessionFactory session factory
	 */
	public OffenseTermProfileItemServiceImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/** {@inheritDoc} */
	@Override
	public Integer findOffenseTermCountByPerson(Person person) {
		Long count = (Long) this.sessionFactory.getCurrentSession()
				.getNamedQuery(FIND_OFFENSE_TERM_COUNT_BY_PERSON_QUERY_NAME)
				.setParameter(DEFENDANT_PARAM_NAME, person)
				.uniqueResult();
		return count.intValue();
	}

	/** {@inheritDoc} */
	@Override
	public Integer findCurrentOffenseTermCountByPerson(Person person) {
		Long count = (Long) this.sessionFactory.getCurrentSession()
				.getNamedQuery(
						FIND_CURRENT_OFFENSE_TERM_COUNT_BY_PERSON_QUERY_NAME)
				.setParameter(PERSON_PARAM_NAME, person)
				.uniqueResult();
		return count.intValue();
	}
}
