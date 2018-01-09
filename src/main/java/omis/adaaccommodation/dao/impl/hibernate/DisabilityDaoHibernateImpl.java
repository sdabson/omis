package omis.adaaccommodation.dao.impl.hibernate;

import omis.adaaccommodation.dao.DisabilityDao;
import omis.adaaccommodation.domain.Disability;
import omis.adaaccommodation.domain.DisabilityClassificationCategory;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.offender.domain.Offender;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of the disability data access object.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Aug 6, 2015)
 * @since OMIS 3.0
 */
public class DisabilityDaoHibernateImpl 
	extends GenericHibernateDaoImpl<Disability>
		implements DisabilityDao {

	/* Queries. */
	private static final String FIND_DISABILITY_QUERY_NAME = "findDisability";
	private static final String FIND_DISABILITY_EXCLUDING_QUERY_NAME 
		= "findDisabilityExcluding";
	
	/* Parameters. */
	private static final String OFFENDER_PARAM_NAME = "offender";
	private static final String DESCRIPTION_PARAM_NAME
		= "disabilityDescription";
	private static final String DISABILITY_CLASSIFICATION_CATEGORY_PARAM_NAME
		= "disabilityCategory";
	private static final String DISABILITY_PARAM_NAME = "disability";

	/**
	 * Instantiates a hibernate implementation of the data access object for
	 * disability.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public DisabilityDaoHibernateImpl(final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public Disability find(final Offender offender, final String description,
			final DisabilityClassificationCategory 
			disabilityCategory) {
		Disability disability = 
				(Disability) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_DISABILITY_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.setParameter(DISABILITY_CLASSIFICATION_CATEGORY_PARAM_NAME,
						disabilityCategory)
				.uniqueResult();
		return disability;
	}

	/** {@inheritDoc} */
	@Override
	public Disability findExcluding(final Offender offender, 
			final String description,
			final DisabilityClassificationCategory 
			disabilityClassificationCategory,
			final Disability disability) {
		Disability thisDisability =
				(Disability) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_DISABILITY_EXCLUDING_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(DISABILITY_CLASSIFICATION_CATEGORY_PARAM_NAME, 
						disabilityClassificationCategory)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.setParameter(DISABILITY_PARAM_NAME, disability)
				.uniqueResult();
		return thisDisability;
	}
}