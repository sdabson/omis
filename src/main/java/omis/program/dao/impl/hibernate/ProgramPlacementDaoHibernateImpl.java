package omis.program.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.datatype.DateRange;
import omis.offender.domain.Offender;
import omis.program.dao.ProgramPlacementDao;
import omis.program.domain.Program;
import omis.program.domain.ProgramPlacement;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of data access object for program placements.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Dec 9, 2015)
 * @since OMIS 3.0
 */
public class ProgramPlacementDaoHibernateImpl
		extends GenericHibernateDaoImpl<ProgramPlacement>
		implements ProgramPlacementDao {

	/* Query names. */
	
	private static final String FIND_QUERY_NAME = "findProgramPlacement";
	
	private static final String FIND_EXCLUDING_QUERY_NAME
		= "findProgramPlacementExcluding";
	
	private static final String FIND_BY_OFFENDER_QUERY_NAME
		= "findProgramPlacementsByOffender";
	
	private static final String COUNT_EXCLUDING_QUERY_NAME
		= "countProgramPlacementsForOffenderBetweenDatesExcluding";

	private static final String COUNT_AFTER_DATE_EXCLUDING_QUERY_NAME
		= "countProgramPlacementsForOffenderAfterDateExcluding";
	
	/* Parameters. */
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private static final String START_DATE_PARAM_NAME = "startDate";
	
	private static final String END_DATE_PARAM_NAME = "endDate";
	
	private static final String PROGRAM_PARAM_NAME = "program";
	
	private static final String EXCLUDED_PROGRAM_PLACEMENTS_PARAM_NAME
		= "excludedProgramPlacements";
	
	private static final String EXCLUDED_PROGRAM_PLACEMENT_PARAM_NAME
		= "excludedProgramPlacement";

	private static final String DATE_PARAM_NAME = "date";

	private static final String FIND_BY_OFFENDER_ON_DATE_QUERY_NAME
		= "findProgramPlacementsByOffenderOnDate";
	
	/* Constructors. */

	/**
	 * Instantiates Hibernate implementation of data access object for
	 * program placements.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public ProgramPlacementDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<ProgramPlacement> findAll() {
		throw new UnsupportedOperationException(
				"Find all program placements not allowed");
	}

	/** {@inheritDoc} */
	@Override
	public ProgramPlacement find(
			final Offender offender, final DateRange dateRange,
			final Program program) {
		ProgramPlacement programPlacement = (ProgramPlacement)
				this.getSessionFactory()
					.getCurrentSession().getNamedQuery(FIND_QUERY_NAME)
					.setParameter(OFFENDER_PARAM_NAME, offender)
					.setTimestamp(START_DATE_PARAM_NAME,
							DateRange.getStartDate(dateRange))
					.setTimestamp(END_DATE_PARAM_NAME,
							DateRange.getEndDate(dateRange))
					.setParameter(PROGRAM_PARAM_NAME, program)
					.uniqueResult();
		return programPlacement;
	}

	/** {@inheritDoc} */
	@Override
	public ProgramPlacement findExcluding(final Offender offender,
			final DateRange dateRange, final Program program,
			final ProgramPlacement... excludedProgramPlacements) {
		ProgramPlacement programPlacement = (ProgramPlacement)
				this.getSessionFactory()
					.getCurrentSession().getNamedQuery(
								FIND_EXCLUDING_QUERY_NAME)
							.setParameter(OFFENDER_PARAM_NAME, offender)
							.setTimestamp(START_DATE_PARAM_NAME,
									DateRange.getStartDate(dateRange))
							.setTimestamp(END_DATE_PARAM_NAME,
									DateRange.getEndDate(dateRange))
							.setParameter(PROGRAM_PARAM_NAME, program)
							.setParameterList(EXCLUDED_PROGRAM_PLACEMENTS_PARAM_NAME,
									excludedProgramPlacements)
								.uniqueResult();
				return programPlacement;
	}

	/** {@inheritDoc} */
	@Override
	public List<ProgramPlacement> findByOffender(final Offender offender) {
		@SuppressWarnings("unchecked")
		List<ProgramPlacement> programPlacements
			= this.getSessionFactory().getCurrentSession().getNamedQuery(
						FIND_BY_OFFENDER_QUERY_NAME)
					.setParameter(OFFENDER_PARAM_NAME, offender).list();
		return programPlacements;
	}

	/** {@inheritDoc} */
	@Override
	public ProgramPlacement findByOffenderOnDate(
			final Offender offender, final Date date) {
		ProgramPlacement programPlacement
			= (ProgramPlacement) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(
					FIND_BY_OFFENDER_ON_DATE_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(DATE_PARAM_NAME, date)
				.uniqueResult();
		return programPlacement;
	}

	/** {@inheritDoc} */
	@Override
	public long countExcluding(final Offender offender, final Date startDate, 
			final Date endDate, 
			final ProgramPlacement... excludedProgramPlacements) {
		long count = (Long) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(COUNT_EXCLUDING_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setTimestamp(START_DATE_PARAM_NAME, startDate)
				.setTimestamp(END_DATE_PARAM_NAME, endDate)
				.setParameterList(EXCLUDED_PROGRAM_PLACEMENTS_PARAM_NAME,
						excludedProgramPlacements)
				.uniqueResult();
		return count;
	}

	/** {@inheritDoc} */
	@Override
	public long countAfterDateExcluding(final Offender offender, 
			final Date startDate, 
			final ProgramPlacement excludedProgramPlacement) {
		long count = (long) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(COUNT_AFTER_DATE_EXCLUDING_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(START_DATE_PARAM_NAME, startDate)
				.setParameter(EXCLUDED_PROGRAM_PLACEMENT_PARAM_NAME, 
						excludedProgramPlacement)
				.uniqueResult();
		return count;
	}
}