package omis.visitation.service.impl;

import java.util.Date;
import java.util.List;

import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
import omis.facility.dao.FacilityDao;
import omis.facility.domain.Facility;
import omis.location.domain.Location;
import omis.offender.domain.Offender;
import omis.visitation.dao.VisitDao;
import omis.visitation.domain.Visit;
import omis.visitation.service.FacilityVisitationService;
import omis.visitation.service.delegate.VisitDelegate;

/**
 * Facility visitation service implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (March 8, 2017)
 * @since OMIS 3.0
 */
public class FacilityVisitationServiceImpl
implements FacilityVisitationService {

	/* Data access objects. */
	
	private FacilityDao facilityDao;
	
	private VisitDao visitDao;
	
	/* Delegates. */
	
	private VisitDelegate visitDelegate;
	
	/**
	 * Instantiates an instance of facility visitation service.
	 * 
	 * @param facilityDao facility data access object
	 * @param visitDao visit data access object
	 * @param visitDelegate visit delegate
	 */
	public FacilityVisitationServiceImpl(final FacilityDao facilityDao,
			final VisitDao visitDao, final VisitDelegate visitDelegate) {
		this.facilityDao = facilityDao;
		this.visitDao = visitDao;
		this.visitDelegate = visitDelegate;
	}
	
	/** {@inheritDoc} */
	@Override
	public Facility findFacilityByLocation(final Location location) {
		return this.facilityDao.findFacilityByLocation(location);
	}

	/** {@inheritDoc} */
	@Override
	public List<Facility> findFacilities() {
		return this.facilityDao.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public Visit endVisit(Visit visit, Date endDate)
			throws DuplicateEntityFoundException, DateConflictException {
		return this.visitDelegate.update(visit.getVisitationAssociation(),
				visit.getDateRange().getStartDate(), endDate,
				visit.getBadgeNumber(), visit.getFlags(), visit.getNotes(),
				visit, visit.getLocation());
	}

	/** {@inheritDoc} */
	@Override
	public void removeVisit(Visit visit) {
		this.visitDelegate.remove(visit);
	}

	/** {@inheritDoc} */
	@Override
	public void checkOutAllOffenderVisits(final Offender offender,
			final Date date) {
		for (Visit visit : this.visitDao
				.findVisitsByOffenderOnDate(offender, date)) {
			if (visit.getDateRange().getEndDate() == null) {
				visit.getDateRange().setEndDate(new Date());
			}
		}
	}

	/** {@inheritDoc} */
	@Override
	public List<Offender> findOffendersWithVisitsOnDate(final Facility facility,
			final Date date) {
		return this.visitDao.findOffendersWithVisitsByFacilityOnDate(facility,
				date);
	}
}