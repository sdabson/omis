package omis.bedplacement.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import omis.bedplacement.domain.BedPlacement;
import omis.bedplacement.domain.BedPlacementReason;
import omis.bedplacement.exception.BedOccupiedException;
import omis.bedplacement.exception.BedPlacementDateConflictException;
import omis.bedplacement.service.BedPlacementService;
import omis.bedplacement.service.delegate.BedPlacementDelegate;
import omis.bedplacement.service.delegate.BedPlacementReasonDelegate;
import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
import omis.facility.domain.Bed;
import omis.facility.domain.Complex;
import omis.facility.domain.Facility;
import omis.facility.domain.Level;
import omis.facility.domain.Room;
import omis.facility.domain.Section;
import omis.facility.domain.Unit;
import omis.facility.service.delegate.BedDelegate;
import omis.facility.service.delegate.ComplexDelegate;
import omis.facility.service.delegate.FacilityDelegate;
import omis.facility.service.delegate.LevelDelegate;
import omis.facility.service.delegate.RoomDelegate;
import omis.facility.service.delegate.SectionDelegate;
import omis.facility.service.delegate.UnitDelegate;
import omis.location.domain.Location;
import omis.locationterm.domain.LocationTerm;
import omis.locationterm.service.delegate.LocationTermDelegate;
import omis.offender.domain.Offender;
import omis.user.domain.UserAccount;

/**
 * Implementation of service for bed placement.
 * 
 * @author Joel Norris 
 * @author Josh Divine
 * @version 0.1.2 (Aug 3, 2017)
 * @since OMIS 3.0
 */
public class BedPlacementServiceImpl implements BedPlacementService {
	
	/* Data access objects. */
	
	private BedPlacementDelegate bedPlacementDelegate;
	
	private FacilityDelegate facilityDelegate;
	
	private ComplexDelegate complexDelegate;
	
	private UnitDelegate unitDelegate;
	
	private SectionDelegate sectionDelegate;
	
	private LevelDelegate levelDelegate;
	
	private RoomDelegate roomDelegate;
	
	private BedDelegate bedDelegate;
	
	private LocationTermDelegate locationTermDelegate;
	
	private BedPlacementReasonDelegate bedPlacementReasonDelegate;
	
	/* Constructors. */
	
	
	/**
	 * Instantiates a bed placement service implementation with the specified
	 * delegates.
	 * @param bedPlacementDelegate bed placement delegate
	 * @param facilityDelegate facility delegate
	 * @param complexDelegate complex delegate
	 * @param unitDelegate unit delegate
	 * @param sectionDelegate section delegate
	 * @param levelDelegate level delegate
	 * @param roomDelegate room delegate
	 * @param bedDelegate bed delegate
	 * @param locationTermDelegate location term delegate
	 * @param bedPlacementReasonDelegate bed placement reason delegate
	 */
	public BedPlacementServiceImpl(
			final BedPlacementDelegate bedPlacementDelegate,
			final FacilityDelegate facilityDelegate, 
			final ComplexDelegate complexDelegate, 
			final UnitDelegate unitDelegate, 
			final SectionDelegate sectionDelegate, 
			final LevelDelegate levelDelegate, final RoomDelegate roomDelegate, 
			final BedDelegate bedDelegate, 
			final LocationTermDelegate locationTermDelegate, 
			final BedPlacementReasonDelegate bedPlacementReasonDelegate) {
		this.bedPlacementDelegate = bedPlacementDelegate;
		this.facilityDelegate = facilityDelegate;
		this.complexDelegate = complexDelegate;
		this.unitDelegate = unitDelegate;
		this.sectionDelegate = sectionDelegate;
		this.levelDelegate = levelDelegate;
		this.roomDelegate = roomDelegate;
		this.bedDelegate = bedDelegate;
		this.locationTermDelegate = locationTermDelegate;
		this.bedPlacementReasonDelegate = bedPlacementReasonDelegate;
	}
	
	/* Public services. */

	/** {@inheritDoc} */
	@Override
	public BedPlacement create(final Offender offender, final Bed bed, 
			final Boolean confirmed, final DateRange dateRange, 
			final String description, final BedPlacementReason reason) 
		throws DuplicateEntityFoundException, BedPlacementDateConflictException, 
			BedOccupiedException {
		return this.bedPlacementDelegate.create(offender, bed, confirmed, 
				dateRange, description, reason);
	}

	/** {@inheritDoc} */
	@Override
	public BedPlacement update(final BedPlacement bedPlacement, final Bed bed,
			final Boolean confirmed, final DateRange dateRange, 
			final String description,final BedPlacementReason reason)
		throws DuplicateEntityFoundException, BedPlacementDateConflictException, 
			BedOccupiedException {
		return this.bedPlacementDelegate.update(bedPlacement, bed, confirmed, 
				dateRange, description, reason);
	}
	
	/** {@inheritDoc} */
	@Override
	public void remove(final BedPlacement bedPlacement) {
		this.bedPlacementDelegate.remove(bedPlacement);
	}

	/** {@inheritDoc} */
	@Override
	public List<BedPlacement> findByOffender(
			final Offender offender) {
		return this.bedPlacementDelegate.findBedPlacementByOffender(offender);
	}

	/** {@inheritDoc} */
	@Override
	public BedPlacement findBedPlacementByOffenderOnDate(
			final Offender offender, final Date date) {
		return this.bedPlacementDelegate
				.findBedPlacementByOffenderOnDate(offender, date);
	}

	/** {@inheritDoc} */
	@Override
	public Facility findFacility(final Offender offender, final Date date) {
		LocationTerm locationTerm = this.locationTermDelegate
				.findByOffenderOnDate(offender, date);
		final Location location;
		if (locationTerm != null) {
			location = locationTerm.getLocation();
		} else {
			location = null;
		}
		return this.facilityDelegate.findByLocation(location);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<Complex> findComplexes(final Facility facility) {
		return this.complexDelegate.findByFacility(facility);
	}

	/** {@inheritDoc} */
	@Override
	public List<Bed> findBeds(final Room room) {
		return this.bedDelegate.findByRoom(room);
	}

	/** {@inheritDoc} */
	@Override
	public List<Bed> findOccupiedBeds(final List<BedPlacement> bedPlacements, 
			final List<Bed> beds) {
		List<Bed> occupiedBeds = new ArrayList<Bed>();
		for (BedPlacement bedPlacement: bedPlacements) {
			for (Bed bed: beds) {
				if (bed == bedPlacement.getBed() && bedPlacement
					.getConfirmed()) {
					occupiedBeds.add(bed);
				}
			}
		}
		return occupiedBeds;
	}

	/** {@inheritDoc} */
	@Override
	public List<BedPlacement> findSpecificBedPlacementsByFacility(
			final Facility facility, final Date date) {
		return this.bedPlacementDelegate.findSpecificBedPlacementsByFacility(
				facility, date);
	}

	/** {@inheritDoc} */
	@Override
	public void endBedPlacementAtDate(final BedPlacement bedPlacement,
			final Date currentDate, final Date endDate,
			final UserAccount userAccount, final Date updateDate) {
		this.bedPlacementDelegate.endBedPlacementAtDate(bedPlacement, 
				currentDate, endDate, userAccount, updateDate);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<BedPlacement> findSpecifiedPlannedBedPlacements(
			final Facility facility, final Date date) {
		return this.bedPlacementDelegate.findSpecificPlannedBedPlacements(
				facility, date);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<BedPlacement> findSpecifiedCompletedBedPlacements(
			final Facility facility, final Date date) {
		return this.bedPlacementDelegate.findSpecificCompletedBedPlacements(
				facility, date);
	}

	/** {@inheritDoc} */
	@Override
	public BedPlacement confirm(final BedPlacement bedPlacement, 
			final Date date) 
		throws BedPlacementDateConflictException, BedOccupiedException {
		return this.bedPlacementDelegate.confirm(bedPlacement, date);
	}

	/** {@inheritDoc} */
	@Override
	public List<BedPlacementReason> findBedPlacementReasons() {
		return this.bedPlacementReasonDelegate.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public BedPlacement endBedPlacement(final BedPlacement bedPlacement, 
			final Date endDate) {
		return this.bedPlacementDelegate.endBedPlacement(bedPlacement, endDate);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<Room> findRooms(final Facility facility, final Complex complex,
			final Unit unit, final Section section, final Level level) {
		return this.roomDelegate.findRooms(facility, complex, unit, section, 
				level);
	}

	/** {@inheritDoc} */
	@Override
	public List<Level> findLevels(final Facility facility, 
			final Complex complex, final Unit unit, final Section section) {
		return this.levelDelegate.findLevels(facility, complex, unit, section);
	}

	/** {@inheritDoc} */
	@Override
	public List<Section> findSections(final Facility facility, 
			final Complex complex, final Unit unit, final Level level) {
		return this.sectionDelegate.findSections(facility, complex, unit, level);
	}

	/** {@inheritDoc} */
	@Override
	public List<Unit> findUnits(final Facility facility, 
			final Complex complex) {
		return this.unitDelegate.findUnits(facility, complex);
	}
}