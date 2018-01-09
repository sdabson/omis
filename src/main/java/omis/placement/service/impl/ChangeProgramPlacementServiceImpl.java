package omis.placement.service.impl;

import java.util.Date;
import java.util.List;

import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
import omis.locationterm.domain.LocationTerm;
import omis.locationterm.service.delegate.LocationTermDelegate;
import omis.offender.domain.Offender;
import omis.placement.service.ChangeProgramPlacementService;
import omis.program.domain.Program;
import omis.program.domain.ProgramPlacement;
import omis.program.service.delegate.ProgramDelegate;
import omis.program.service.delegate.ProgramPlacementDelegate;
import omis.supervision.domain.PlacementTerm;
import omis.supervision.service.delegate.PlacementTermDelegate;

/**
 * Implementation of service to change program placements.
 *
 * @author Stephen Abson
 * @version 0.0.1 (May 3, 2016)
 * @since OMIS 3.0
 */
public class ChangeProgramPlacementServiceImpl
		implements ChangeProgramPlacementService {

	private final ProgramPlacementDelegate programPlacementDelegate;
	
	private final PlacementTermDelegate placementTermDelegate;
	
	private final LocationTermDelegate locationTermDelegate;
	
	private final ProgramDelegate programDelegate;
	
	/**
	 * Instantiates implementation of service to change program placement.
	 * 
	 * @param programPlacementDelegate delegate for program placements
	 * @param placementTermDelegate delegate for placement terms
	 * @param locationTermDelegate delegate for location terms
	 * @param programDelegate delegate for programs
	 */
	public ChangeProgramPlacementServiceImpl(
			final ProgramPlacementDelegate programPlacementDelegate,
			final PlacementTermDelegate placementTermDelegate,
			final LocationTermDelegate locationTermDelegate,
			final ProgramDelegate programDelegate) {
		this.programPlacementDelegate = programPlacementDelegate;
		this.placementTermDelegate = placementTermDelegate;
		this.locationTermDelegate = locationTermDelegate;
		this.programDelegate = programDelegate;
	}
	
	/** {@inheritDoc} */
	@Override
	public ProgramPlacement change(
			final Offender offender,
			final Date effectiveDate,
			final Date endDate,
			final Program program)
				throws DuplicateEntityFoundException {
		PlacementTerm placementTerm = this.placementTermDelegate
				.findForOffenderOnDate(offender, effectiveDate);
		LocationTerm locationTerm = this.locationTermDelegate
				.findByOffenderOnDate(offender, effectiveDate);
		return this.programPlacementDelegate
				.create(offender, new DateRange(effectiveDate, endDate),
						program, placementTerm, locationTerm);
	}

	/** {@inheritDoc} */
	@Override
	public List<Program> findAllowedProgramsForOffenderOnDate(
			final Offender offender, final Date effectiveDate) {
		PlacementTerm placementTerm
			= this.placementTermDelegate.findForOffenderOnDate(
					offender, effectiveDate);
		if (placementTerm == null) {
			throw new IllegalArgumentException(
					"Offender not under supervision");
		}
		if (placementTerm.getCorrectionalStatusTerm()
				.getCorrectionalStatus().getLocationRequired()) {
			LocationTerm locationTerm = this.locationTermDelegate
					.findByOffenderOnDate(offender, effectiveDate);
			if (locationTerm != null) {
				return this.programDelegate.findOfferedByLocation(
						locationTerm.getLocation());
			} else {
				return this.programDelegate
						.findOfferedBySupervisoryOrganization(
								placementTerm.getSupervisoryOrganizationTerm()
									.getSupervisoryOrganization());
			}
		} else {
			return this.programDelegate.findOfferedBySupervisoryOrganization(
					placementTerm.getSupervisoryOrganizationTerm()
						.getSupervisoryOrganization());
		}
	}
}