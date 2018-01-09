package omis.health.service.impl;

import java.util.Date;
import java.util.List;

import omis.datatype.DateRange;
import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
import omis.facility.domain.Facility;
import omis.facility.domain.Unit;
import omis.facility.exception.FacilityLayoutException;
import omis.health.dao.MedicalFacilityDao;
import omis.health.dao.ProviderAssignmentDao;
import omis.health.dao.ProviderTitleDao;
import omis.health.dao.ProviderUnitAssignmentDao;
import omis.health.domain.MedicalFacility;
import omis.health.domain.ProviderAssignment;
import omis.health.domain.ProviderTitle;
import omis.health.domain.ProviderUnitAssignment;
import omis.health.service.ProviderAssignmentManager;
import omis.instance.factory.InstanceFactory;
import omis.person.domain.Person;
import omis.staff.dao.StaffAssignmentDao;
import omis.staff.exception.StaffAssignmentNotFoundException;
import omis.supervision.domain.SupervisoryOrganization;

/**
 * Provider Assignment Manager Implementation.
 * 
 * @author Joel Norris
 * @author Stephen Abson
 * @author Sheronda Vaughn
 * @version 0.1.0 (Apr 9, 2014)
 * @since OMIS 3.0
 */
public class ProviderAssignmentManagerImpl 
		implements ProviderAssignmentManager {

	/* Data access objects. */
	
	private final StaffAssignmentDao staffAssignmentDao;
	
	private final ProviderAssignmentDao providerAssignmentDao;
	
	private final ProviderUnitAssignmentDao providerUnitAssignmentDao;
	
	private final ProviderTitleDao providerTitleDao;
	
	private final MedicalFacilityDao medicalFacilityDao;
	
	
	/* Instance Factories */
	
	private final InstanceFactory<ProviderAssignment> 
	providerAssignmentInstanceFactory;
	
	private final InstanceFactory<ProviderUnitAssignment>
	providerUnitAssignmentInstanceFactory;
	
	/**
	 * Instantiates a provider assignment manager with the specified instance
	 * factories and component retriever.
	 * 
	 * @param staffAssignmentDao data access object for staff assignments
	 * @param providerAssignmentDao data access object for provider assignments
	 * @param providerUnitAssignmentDao data access object for provider unit
	 * assignments
	 * @param providerTitleDao data access object for provider titles
	 * @param medicalFacilityDao data access object for medical facilities
	 * @param providerAssignmentInstanceFactory provider assignment instance
	 * factory
	 * @param providerUnitAssignmentInstanceFactory provider unit assignment
	 * instance factory
	 */
	public ProviderAssignmentManagerImpl(
			final StaffAssignmentDao staffAssignmentDao,
			final ProviderAssignmentDao providerAssignmentDao,
			final ProviderUnitAssignmentDao providerUnitAssignmentDao,
			final ProviderTitleDao providerTitleDao,
			final MedicalFacilityDao medicalFacilityDao,
			final InstanceFactory<ProviderAssignment> 
				providerAssignmentInstanceFactory,
			final InstanceFactory<ProviderUnitAssignment>
				providerUnitAssignmentInstanceFactory) {
		this.staffAssignmentDao = staffAssignmentDao;
		this.providerAssignmentDao = providerAssignmentDao;
		this.providerUnitAssignmentDao = providerUnitAssignmentDao;
		this.providerTitleDao = providerTitleDao;
		this.medicalFacilityDao = medicalFacilityDao;
		this.providerAssignmentInstanceFactory = 
				providerAssignmentInstanceFactory;
		this.providerUnitAssignmentInstanceFactory =
				providerUnitAssignmentInstanceFactory;
	}
	
	/** {@inheritDoc} */
	@Override
	public ProviderAssignment assign(final Person provider, 
			final Facility facility, final DateRange dateRange, 
			final ProviderTitle title)
		throws DateConflictException, DuplicateEntityFoundException,
			StaffAssignmentNotFoundException {
		if (this.staffAssignmentDao.findOnDate(dateRange.getStartDate(), 
				provider, (SupervisoryOrganization) facility.getLocation()
						.getOrganization()) == null) {
			throw new StaffAssignmentNotFoundException("Staff Assignment not" 
					+ " found");
		}
		ProviderAssignment assignment = this.providerAssignmentDao
				.find(provider, facility, dateRange); 
		if (assignment != null) {
			throw new DuplicateEntityFoundException("Duplicate Entity Found");
		} else  {
			if (this.providerAssignmentDao.countByDateRange(provider, 
					dateRange, facility) > 0) {
				throw new DateConflictException("Date range conflict found.");
			}
			assignment = this.providerAssignmentInstanceFactory
					.createInstance();
			this.populateProviderAssignment(dateRange, provider, facility, 
					assignment, title, false);
		}
		return this.providerAssignmentDao.makePersistent(assignment);
	}

	/** {@inheritDoc} */
	@Override
	public ProviderAssignment assignExternal(final Person provider,
			final Facility facility, final MedicalFacility medicalFacility,
			final DateRange dateRange, 
			final ProviderTitle title)
		throws DateConflictException, DuplicateEntityFoundException {
		ProviderAssignment assignment = this.providerAssignmentDao
				.find(provider, facility, dateRange); 
		if (assignment != null) {
			throw new DuplicateEntityFoundException("Duplicate Entity Found");
		} else  {
			if (this.providerAssignmentDao.countByDateRange(provider, 
					dateRange, facility) > 0) {
				throw new DateConflictException("Date range conflict found.");
			}
			assignment = this.providerAssignmentInstanceFactory
					.createInstance();
			this.populateProviderAssignment(dateRange, provider, 
					facility, assignment, title, false);
			assignment.setMedicalFacility(medicalFacility);
			this.providerAssignmentDao.makePersistent(assignment);
		}
		return assignment;
	}

	/** {@inheritDoc} */
	@Override
	public ProviderAssignment assignContracted(final Person provider,
			final Facility facility, final DateRange dateRange,
			final ProviderTitle title)
        throws DateConflictException, 
							DuplicateEntityFoundException {
		ProviderAssignment assignment = this.providerAssignmentDao
				.find(provider, facility, dateRange); 
		if (assignment != null) {
			throw new DuplicateEntityFoundException(
					"Provider already assigned");
		} else  {
			if (this.providerAssignmentDao.countByDateRange(provider, 
					dateRange, facility) > 0) {
				throw new DateConflictException(
						"Conflicting provider assignment exists");
			}
			assignment = this.providerAssignmentInstanceFactory
					.createInstance();
			this.populateProviderAssignment(dateRange, provider, 
					facility, assignment, title, true);
			
			this.providerAssignmentDao.makePersistent(assignment);
		}
		return assignment;
	}
	
	/** {@inheritDoc} */
	@Override
	public ProviderAssignment update(final ProviderAssignment assignment,
			final ProviderTitle title, final DateRange dateRange) 
		throws DateConflictException, 
				DuplicateEntityFoundException {
		if (this.providerAssignmentDao.findExcluding(
					assignment.getProvider(), assignment.getFacility(), 
					dateRange, assignment) != null) {
			throw new DuplicateEntityFoundException(
						"Duplicate entity found" 
				+ "provider assignment exists");
			}	else {
					if (this.providerAssignmentDao.countByDateRangeExcluding(
							assignment.getProvider(), dateRange, 
							assignment.getFacility(), assignment) > 0) {
						throw new DateConflictException(
								"Date range conflict found");
					}
			}			
			assignment.setTitle(title);
			assignment.setDateRange(dateRange);
			this.providerAssignmentDao.makePersistent(assignment);
			return assignment;
	}
	
	/** {@inheritDoc} */
	@Override
	public ProviderUnitAssignment assignToUnit(
			final ProviderAssignment providerAssignment, final Unit unit)
		throws DuplicateEntityFoundException, FacilityLayoutException {
		if (this.providerUnitAssignmentDao.find(providerAssignment, 
				unit) != null) {
			throw new DuplicateEntityFoundException("Duplicate provider "
					+ "unit found");
		}
		if (!unit.getFacility().equals(providerAssignment
				.getFacility())) {
			throw new FacilityLayoutException("Unit not contained" 
				+ " within facility");
		}
		ProviderUnitAssignment unitAssignment = this
				.providerUnitAssignmentInstanceFactory.createInstance();
		this.populateProviderUnitAssignment(unitAssignment, 
				providerAssignment, unit);
		this.providerUnitAssignmentDao.makePersistent(unitAssignment);
		return unitAssignment;
	}

	/** {@inheritDoc} */
	@Override
	public void removeAssignment(final ProviderAssignment providerAssignment) {
		this.providerAssignmentDao.makeTransient(providerAssignment);
	}

	/** {@inheritDoc} */
	@Override
	public void removeUnitAssignment(
			final ProviderUnitAssignment providerUnitAssignment) {
		this.providerUnitAssignmentDao.makeTransient(providerUnitAssignment);
	}

	/** {@inheritDoc} */
	@Override
	public List<ProviderAssignment> findAssignmentsToFacility(
			final Facility facility, final Date date) {
		return this.providerAssignmentDao.findByFacility(facility, date);
	}

	/** {@inheritDoc} */
	@Override
	public List<ProviderUnitAssignment> findUnitAssignments(
			final Unit unit, final Date date) {
		return this.providerUnitAssignmentDao.findByUnit(unit, date);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<ProviderTitle> findTitles() {
		return this.providerTitleDao.findAll();
	}
	
	/** {@inheritDoc} */
	@Override
	public List<ProviderAssignment> findAssignmentsByMedicalFacility(
			final MedicalFacility medicalFacility, final Date date) {
		return this.providerAssignmentDao.findExternalByMedicalFacility(
				medicalFacility, date);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<MedicalFacility> findMedicalFacilities() {
		return this.medicalFacilityDao.findAll();
	}
	
	/*
	 * Populates a provider assignment.
	 */
	private void populateProviderAssignment(final DateRange dateRange,
			final Person provider, final Facility facility, 
			final ProviderAssignment assignment, final ProviderTitle title, 
			final Boolean contracted) {
		assignment.setDateRange(dateRange);
		assignment.setProvider(provider);
		assignment.setFacility(facility);	
		assignment.setTitle(title);
		assignment.setContracted(contracted);
	}
	
	/*
	 * Populates a provider unit assignment.
	 */
	private void populateProviderUnitAssignment(
			final ProviderUnitAssignment providerUnitAssignment,
			final ProviderAssignment providerAssignment, final Unit unit) {
		providerUnitAssignment.setProviderAssignment(providerAssignment);
		providerUnitAssignment.setUnit(unit);
	}
}