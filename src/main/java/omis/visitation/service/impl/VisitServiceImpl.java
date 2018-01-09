package omis.visitation.service.impl;

import java.util.Date;
import java.util.List;

import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
import omis.location.domain.Location;
import omis.locationterm.dao.LocationTermDao;
import omis.locationterm.domain.LocationTerm;
import omis.offender.domain.Offender;
import omis.util.DateManipulator;
import omis.visitation.domain.Visit;
import omis.visitation.domain.VisitationAssociation;
import omis.visitation.domain.component.VisitFlags;
import omis.visitation.service.VisitService;
import omis.visitation.service.delegate.VisitDelegate;
import omis.visitation.service.delegate.VisitationAssociationDelegate;

/**
 * Visit Service implementation.
 * 
 * @author Joel Norris
 * @version 0.1.2 (Dec 28th, 2016)
 * @since OMIS 3.0
 */
public class VisitServiceImpl implements VisitService {

	/* Data access objects. */
	
	private final LocationTermDao locationTermDao;
	
	/* Delegates. */
	
	private final VisitDelegate visitDelegate;
	
	private final VisitationAssociationDelegate visitationAssociationDelegate;
	
	/**
	 * Instantiates a visit service implementation with the specified data
	 * access objects, instance factories, and component retrievers.
	 * 
	 * @param visitationAssociationDelegate visitation association delegate
	 * @param locationTermDao location term data access object
	 * @param auditComponentRetriever audit component retriever
	 */
	public VisitServiceImpl(
			final VisitationAssociationDelegate visitationAssociationDelegate,
			final LocationTermDao locationTermDao,
			final VisitDelegate visitDelegate) {
		this.visitationAssociationDelegate = visitationAssociationDelegate;
		this.locationTermDao = locationTermDao;
		this.visitDelegate = visitDelegate;
	}

	/** {@inheritDoc} */
	@Override
	public Visit log(final VisitationAssociation visitationAssociation,
			final Date date, final Date startTime, final Date endTime,
			final String badgeNumber, final VisitFlags flags,
			final String notes, final Location location)
		throws DuplicateEntityFoundException, DateConflictException {
		Date startDate = DateManipulator.getDateAtTimeOfDay(date, startTime);
		final Date endDate;
		if (endTime != null) {
			endDate = DateManipulator.getDateAtTimeOfDay(date, endTime);
		} else {
			endDate = null;
		}
		return this.visitDelegate.create(visitationAssociation,
				startDate, endDate, badgeNumber, flags, notes, location);
	}

	/** {@inheritDoc} */
	@Override
	public Visit update(final Visit visit, final Date date,
			final Date startTime, final Date endTime, final String badgeNumber,
			final VisitFlags flags, final String notes, final Location location)
		throws DuplicateEntityFoundException, DateConflictException {
		Date startDate = DateManipulator.getDateAtTimeOfDay(date, startTime);
		final Date endDate;
		if (endTime != null) {
			endDate = DateManipulator.getDateAtTimeOfDay(date, endTime);
		} else {
			endDate = null;
		}
		return this.visitDelegate.update(visit.getVisitationAssociation(),
				startDate, endDate, badgeNumber, flags, notes, visit, location);
	}

	/** {@inheritDoc} */
	@Override
	public void remove(final Visit visit) {
		this.visitDelegate.remove(visit);
	}

	/** {@inheritDoc} */
	@Override
	public List<Visit> findByOffender(final Offender offender) {
		return this.visitDelegate.findVisitByOffender(offender);
	}

	/** {@inheritDoc} */
	@Override
	public Visit endVisit(final Visit visit, final Date endDate)
		throws DuplicateEntityFoundException, DateConflictException{
		return this.visitDelegate.update(visit.getVisitationAssociation(),
				visit.getDateRange().getStartDate(), endDate,
				visit.getBadgeNumber(), visit.getFlags(), visit.getNotes(),
				visit, visit.getLocation());
	}

	/** {@inheritDoc} */
	@Override
	public List<VisitationAssociation> findSpecialVisitAssociationsByOffender(
			final Offender offender, final Date date) {
		return this.visitationAssociationDelegate
			.findSpecialVisitVisitationAssociationsByOffender(offender, date);
	}

	/** {@inheritDoc} */
	@Override
	public List<VisitationAssociation>
	findApprovedVisitationAssociaitonsByOffender(final Offender offender, 
			final Date date) {
		return this.visitationAssociationDelegate
				.findApprovedVisitationAssociationsByOffender(offender, date);
	}

	/** {@inheritDoc} */
	@Override
	public LocationTerm findLocationTermByOffenderOnDate(
			final Offender offender, final Date date) {
		return this.locationTermDao.findByOffenderOnDate(offender, date);
	}
}