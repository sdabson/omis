package omis.bedplacement.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import omis.bedplacement.dao.BedPlacementDao;
import omis.bedplacement.domain.BedPlacement;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.datatype.DateRange;
import omis.facility.domain.Bed;
import omis.facility.domain.Facility;
import omis.offender.domain.Offender;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of data access object for bed placement.
 * 
 * @author Joel Norris 
 * @version 0.1.0 (Apr, 04 2013)
 * @since OMIS 3.0
 */
public class BedPlacementDaoHibernateImpl
	extends GenericHibernateDaoImpl<BedPlacement>
	implements BedPlacementDao {

	/* Query names. */
	
	private static final String FIND_BED_PLACEMENT_QUERY_NAME
		= "findBedPlacementByOffenderAndDateRange";
	
	private static final String FIND_BED_PLACEMENT_EXCLUDING_QUERY_NAME
		= "findBedPlacementByOffenderAndDateRangeExcluding";
	
	private static final String FIND_BED_PLACEMENTS_BY_OFFENDER
		= "findBedPlacementsByOffender";
	
	private static final String FIND_BED_PLACEMENT_BY_OFFENDER_ON_DATE
		= "findBedPlacementByOffenderOnDate";
	
	private static final String FIND_BED_PLACEMENTS_WITHIN_DATE_RANGE_QUERY_NAME
		= "findBedPlacementsWithinDateRange";
	
	private static final String 
		FIND_BED_PLACEMENTS_WITHIN_DATE_RANGE_EXCLUDING_QUERY_NAME
		= "findBedPlacementsWithinDateRangeExcluding";
	
	private static final String
		FIND_CONFIRMED_BED_PLACEMENT_BY_OFFENDER_ON_DATE_QUERY_NAME
		= "findConfirmedBedPlacementByOffenderOnDate";
	
	private static final String FIND_CONFIRMED_BED_PLACEMENT_BY_BED_ON_DATE
		= "findConfirmedBedPlacementByBedOnDate";
	
	private static final String 
		FIND_CONFIRMED_BED_PLACEMENT_BY_BED_ON_DATE_EXCLUDING
		= "findConfirmedBedPlacementByBedOnDateExcluding";
	
	/* Parameter names. */
	
	private static final String START_DATE_PARAM_NAME = "startDate";
	
	private static final String END_DATE_PARAM_NAME = "endDate";
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private static final String BED_PLACEMENT_PARAM_NAME = "bedPlacement";
	
	private static final String DATE_PARAM_NAME = "date";
	
	private static final String FACILITY_PARAM_NAME = "facility";
	
	private static final String BED_PARAM_NAME = "bed";
	
	/** 
	 * Instantiates a data access object for bed placement with the specified
	 * session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public BedPlacementDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<BedPlacement> findBedPlacementByOffender(
			final Offender offender) {
		@SuppressWarnings("unchecked")
		List<BedPlacement> bedPlacements = getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_BED_PLACEMENTS_BY_OFFENDER)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.list();
		return bedPlacements;
	}

	/** {@inheritDoc} */
	@Override
	public BedPlacement findBedPlacementByOffenderOnDate(
			final Offender offender, final Date date) {
		BedPlacement bedPlacement = (BedPlacement) getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_BED_PLACEMENT_BY_OFFENDER_ON_DATE)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(DATE_PARAM_NAME, date)
				.uniqueResult();
		return bedPlacement;
	}

	/** {@inheritDoc} */
	@Override
	public List<BedPlacement> findSpecificBedPlacementsByFacility(
			final Facility facility, final Date date) {
		@SuppressWarnings("unchecked")
		List<BedPlacement> bedPlacements = getSessionFactory()
				.getCurrentSession()
				.getNamedQuery("findSpecificBedPlacementsByFacility")
				.setParameter(FACILITY_PARAM_NAME, facility)
				.setParameter(DATE_PARAM_NAME, date)
				.list();
		return bedPlacements;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<BedPlacement> findSpecificCompletedBedPlacements(
			final Facility facility, final Date date) {
		@SuppressWarnings("unchecked")
		List<BedPlacement> bedPlacements = getSessionFactory()
				.getCurrentSession()
				.getNamedQuery("findSpecificOccupiedBedsByFacility")
				.setParameter(FACILITY_PARAM_NAME, facility)
				.setParameter(DATE_PARAM_NAME, date)
				.list();
		return bedPlacements;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<BedPlacement> findSpecificPlannedBedPlacements(
		final Facility facility, final Date date) {
		@SuppressWarnings("unchecked")
		List<BedPlacement> bedPlacements = getSessionFactory()
				.getCurrentSession()
				.getNamedQuery("findPlannedBedPlacements")
				.setParameter(FACILITY_PARAM_NAME, facility)
				.setParameter(DATE_PARAM_NAME, date)
				.list();
		return bedPlacements;	
	}

	/** {@inheritDoc} */
	@Override
	public BedPlacement find(final Offender offender, 
			final DateRange dateRange) {
		BedPlacement bedPlacement = (BedPlacement) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_BED_PLACEMENT_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setDate(START_DATE_PARAM_NAME, dateRange.getStartDate())
				.setDate(END_DATE_PARAM_NAME, dateRange.getEndDate())
				.uniqueResult();
		return bedPlacement;
	}

	/** {@inheritDoc} */
	@Override
	public BedPlacement findExcluding(final Offender offender, 
			final DateRange dateRange, final BedPlacement bedPlacement) {
		BedPlacement result = (BedPlacement) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_BED_PLACEMENT_EXCLUDING_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setDate(START_DATE_PARAM_NAME, dateRange.getStartDate())
				.setDate(END_DATE_PARAM_NAME, dateRange.getEndDate())
				.setParameter(BED_PLACEMENT_PARAM_NAME, bedPlacement)
				.uniqueResult();
		return result;
	}

	/** {@inheritDoc} */
	@Override
	public List<BedPlacement> findWithinDateRange(final Offender offender,
			final DateRange dateRange) {
		@SuppressWarnings("unchecked")
		List<BedPlacement> bedPlacements = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_BED_PLACEMENTS_WITHIN_DATE_RANGE_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setDate(START_DATE_PARAM_NAME, dateRange.getStartDate())
				.setDate(END_DATE_PARAM_NAME, dateRange.getEndDate())
				.list();
		return bedPlacements;
	}

	/** {@inheritDoc} */
	@Override
	public List<BedPlacement> findWithinDateRangeExcluding(
			final Offender offender, final DateRange dateRange, 
			final BedPlacement bedPlacement) {
		@SuppressWarnings("unchecked")
		List<BedPlacement> bedPlacements = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
					FIND_BED_PLACEMENTS_WITHIN_DATE_RANGE_EXCLUDING_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setDate(START_DATE_PARAM_NAME, dateRange.getStartDate())
				.setDate(END_DATE_PARAM_NAME, dateRange.getEndDate())
				.setParameter(BED_PLACEMENT_PARAM_NAME, bedPlacement)
				.list();
		return bedPlacements;
	}

	/** {@inheritDoc} */
	@Override
	public BedPlacement findConfirmedBedPlacementByOffenderOnDate(
			Offender offender, Date date) {
		BedPlacement bedPlacement = (BedPlacement) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
					FIND_CONFIRMED_BED_PLACEMENT_BY_OFFENDER_ON_DATE_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setDate(DATE_PARAM_NAME, date)
				.uniqueResult();
		return bedPlacement;
	}

	/** {@inheritDoc} */
	@Override
	public BedPlacement findConfirmedBedPlacementByBedOnDate(
			final Date date, final Bed bed, final Offender offender) {
		BedPlacement bedPlacement = (BedPlacement) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_CONFIRMED_BED_PLACEMENT_BY_BED_ON_DATE)
				.setDate(DATE_PARAM_NAME, date)
				.setParameter(BED_PARAM_NAME, bed)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.uniqueResult();
		return bedPlacement;
	}

	/** {@inheritDoc} */
	@Override
	public BedPlacement findConfirmedBedPlacementByBedOnDateExcluding(
			final Date date, final Bed bed, final BedPlacement bedPlacement) {
		BedPlacement occupiedBedPlacement = (BedPlacement) 
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
						FIND_CONFIRMED_BED_PLACEMENT_BY_BED_ON_DATE_EXCLUDING)
				.setDate(DATE_PARAM_NAME, date)
				.setParameter(BED_PARAM_NAME, bed)
				.setParameter(BED_PLACEMENT_PARAM_NAME, bedPlacement)
				.uniqueResult();
				return occupiedBedPlacement;
	}
}