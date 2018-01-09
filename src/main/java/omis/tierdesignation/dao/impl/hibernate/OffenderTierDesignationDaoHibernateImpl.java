package omis.tierdesignation.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.datatype.DateRange;
import omis.offender.domain.Offender;
import omis.tierdesignation.dao.OffenderTierDesignationDao;
import omis.tierdesignation.domain.OffenderTierDesignation;
import omis.tierdesignation.domain.TierChangeReason;
import omis.tierdesignation.domain.TierLevel;
import omis.tierdesignation.domain.TierSource;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of offender tier designation data access object.
 * @author Jason Nelson
 * @version 0.1.0 (September 20, 2012)
 * @since OMIS 3.0
 * @see OffenderTierDesignation
 * @see OffenderTierDesignationDao
 */
public class OffenderTierDesignationDaoHibernateImpl
		extends GenericHibernateDaoImpl<OffenderTierDesignation>
		implements OffenderTierDesignationDao {
	
	/* Query names. */
	
	private static final String FIND_BY_OFFENDER_QUERY_NAME
		= "findOffenderTierDesignationsByOffender";
	
	private static final String FIND_QUERY_NAME = "findOffenderTierDesignation";
	
	private static final String FIND_EXCLUDING_QUERY_NAME
	= "findOffenderTierDesignationExcluding";
	
	/* Parameter names. */
	
	private static final String OFFENDER_PARAM_NAME = "offender";

	private static final String LEVEL_PARAM_NAME = "level";

	private static final String SOURCE_PARAM_NAME = "source";

	private static final String CHANGE_REASON_PARAM_NAME = "changeReason";

	private static final String START_DATE_PARAM_NAME = "startDate";

	private static final String END_DATE_PARAM_NAME = "endDate";

	private static final String EXCLUDED_OFFENDER_TIER_DESIGNATION_PARAM
			= "excludedOffenderTierDesignation";
	
	/**
	 * Instantiates a data access object for tier designations
	 *  with the specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public OffenderTierDesignationDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	@Override
	public List<OffenderTierDesignation> findByOffender(
			final Offender offender) {
		@SuppressWarnings("unchecked")
		List<OffenderTierDesignation> offenderTierDesignations =
				getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender).list();
		return offenderTierDesignations;
	}

	/** {@inheritDoc} */
	@Override
	public OffenderTierDesignation find(
			final Offender offender, final TierLevel level,
			final TierSource source, final TierChangeReason changeReason,
			final DateRange dateRange) {
		OffenderTierDesignation offenderTierDesignation
				= (OffenderTierDesignation) this.getSessionFactory()
					.getCurrentSession()
			.getNamedQuery(FIND_QUERY_NAME)
			.setParameter(OFFENDER_PARAM_NAME, offender)
			.setParameter(LEVEL_PARAM_NAME, level)
			.setParameter(SOURCE_PARAM_NAME, source)
			.setParameter(CHANGE_REASON_PARAM_NAME, changeReason)
			.setDate(START_DATE_PARAM_NAME, dateRange.getStartDate())
			.setDate(END_DATE_PARAM_NAME, dateRange.getEndDate())
			.uniqueResult();
		return offenderTierDesignation;
				
	}

	/** {@inheritDoc} */
	@Override
	public OffenderTierDesignation findExcluding(final Offender offender,
			final TierLevel level, final TierSource source,
			final TierChangeReason changeReason, final DateRange dateRange,
			final OffenderTierDesignation excludedOffenderTierDesignation) {
		OffenderTierDesignation offenderTierDesignation
				= (OffenderTierDesignation) this.getSessionFactory()
				.getCurrentSession()
			.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
			.setParameter(OFFENDER_PARAM_NAME, offender)
			.setParameter(LEVEL_PARAM_NAME, level)
			.setParameter(SOURCE_PARAM_NAME, source)
			.setParameter(CHANGE_REASON_PARAM_NAME, changeReason)
			.setDate(START_DATE_PARAM_NAME, dateRange.getStartDate())
			.setDate(END_DATE_PARAM_NAME, dateRange.getEndDate())
			.setParameter(EXCLUDED_OFFENDER_TIER_DESIGNATION_PARAM,
					excludedOffenderTierDesignation)
			.uniqueResult();
		return offenderTierDesignation;
	}
}