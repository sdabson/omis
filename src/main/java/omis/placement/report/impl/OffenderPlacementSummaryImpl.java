package omis.placement.report.impl;

import java.util.Date;

import omis.caseload.domain.OfficerCaseAssignment;
import omis.confidentialoffender.domain.ConfidentialOffenderDesignator;
import omis.datatype.DateRange;
import omis.locationterm.domain.LocationReasonTerm;
import omis.placement.report.OffenderPlacementSummary;
import omis.prisonterm.domain.PrisonTerm;
import omis.supervision.domain.PlacementTerm;

/** Implementation of location summary.
 * @author Ryan Johns
 * @author Stephen Abson
 * @version 0.1.0 (Jul 28, 2016)
 * @since OMIS 3.0 */
public class OffenderPlacementSummaryImpl implements OffenderPlacementSummary {
	private String currentLocationReasonName;
	private Date currentLocationStartDate;
	private Date currentLocationStartTime;
	private String currentLocationName;
	private String correctionalStatusName;
	private Date correctionalStatusStartDate;
	private Date correctionalStatusStartTime;
	private String correctionalStatusReasonName;
	private String  confidentialOffender;
	private String supervisingOfficer;
	private Date supervisionStartDate;
	private Date paroleEligibilityDate;
	private Date probationDischargeDate;
	private Date prisonDischargeDate;
	private String chimesId;
	private Boolean placed;
	private Boolean located;
	private Boolean officerAssigned;
	
	/** Default Constructor. */
	public OffenderPlacementSummaryImpl() {
	}
	
	/**
	 * Instantiates implementation of location summary.
	 * 
	 * @param currentLocationReasonName name of current location reason
	 * @param currentLocationStartDate start date of current location reason
	 * @param currentLocationName name of current location
	 * @param correctionalStatusName name of correctional status
	 * @param correctionalStatusStartDate start date of correctional status
	 * @param correctionalStatusReasonName name of correctional status reason
	 * @param confidentialOffender confidential offender message
	 * @param supervisingOfficer supervising officer
	 * @param supervisionStartDate supervision start date
	 */
	public OffenderPlacementSummaryImpl(
			final String currentLocationReasonName,
			final Date currentLocationStartDate,
			final String currentLocationName,
			final String correctionalStatusName,
			final Date correctionalStatusStartDate,
			final String correctionalStatusReasonName,
			final String  confidentialOffender,
			final String supervisingOfficer,
			final Date supervisionStartDate) {
		this.currentLocationReasonName = currentLocationReasonName;
		this.currentLocationStartDate = currentLocationStartDate;
		this.currentLocationStartTime = currentLocationStartDate;
		this.currentLocationName = currentLocationName;
		this.correctionalStatusName = correctionalStatusName;
		this.correctionalStatusStartDate = correctionalStatusStartDate;
		this.correctionalStatusStartTime = correctionalStatusStartDate;
		this.correctionalStatusReasonName = correctionalStatusReasonName;
		this.confidentialOffender = confidentialOffender;
		this.supervisingOfficer = supervisingOfficer;
		this.supervisionStartDate = supervisionStartDate;
	}
	
	/** Constructor.
	 * @param currentLocationReasonName - current location reason name.
	 * @param currentLocationStartDate - current location start date.
	 * @param currentLocationStartTime - current location start time.
	 * @param currentLocationName - current location name. */
	public OffenderPlacementSummaryImpl(final String currentLocationReasonName, 
					final Date currentLocationStartDate, 
					final Date currentLocationStartTime, 
					final String currentLocationName) {
		this.currentLocationReasonName = currentLocationReasonName;
		this.currentLocationStartDate = currentLocationStartDate;
		this.currentLocationStartTime = currentLocationStartTime;
		this.currentLocationName = currentLocationName;
	}
	
	/**
	 * Instantiates implementation of offender placement summary.
	 * 
	 * @param locationReasonTerm location reason term
	 * @param placementTerm placement term
	 * @param officerCaseAssignment officer case assignment
	 * @param confidentialOffenderDesignator confidentialOffenderDesignator
	 * @param prisonTerm prison term
	 */
	public OffenderPlacementSummaryImpl(
			final LocationReasonTerm locationReasonTerm,
			final PlacementTerm placementTerm,
			final OfficerCaseAssignment officerCaseAssignment,
			final ConfidentialOffenderDesignator confidentialOffenderDesignator,
			final PrisonTerm prisonTerm) {
		if (locationReasonTerm != null) {
			this.currentLocationReasonName
				= locationReasonTerm.getReason().getName();
			this.currentLocationStartDate = DateRange.getStartDate(
				locationReasonTerm.getLocationTerm().getDateRange());
			this.currentLocationStartTime = this.currentLocationStartDate;
			this.currentLocationName
				= locationReasonTerm.getLocationTerm().getLocation()
					.getOrganization().getName();
			this.located = true;
		} else {
			this.located = false;
		}
		if (placementTerm != null) {
			this.correctionalStatusName = placementTerm.getCorrectionalStatusTerm()
					.getCorrectionalStatus().getName();
			this.correctionalStatusStartDate
				= DateRange.getStartDate(placementTerm.getCorrectionalStatusTerm()
						.getDateRange());
			this.correctionalStatusStartTime = this.correctionalStatusStartDate;
			this.correctionalStatusReasonName
				= placementTerm.getStartChangeReason().getName();
			this.placed = true;
		} else {
			this.placed = false;
		}
		if (prisonTerm != null) {
			this.prisonDischargeDate = prisonTerm.getProjectedDischargeDate();
			this.paroleEligibilityDate = prisonTerm.getParoleEligibilityDate();
		}
		
		// TODO - Move formatting to presentation tier - SA
		// Add two properties to represent confidential offender message
		// and allow presentation tier to determine formatting
		if (confidentialOffenderDesignator != null) {
			this.confidentialOffender
				= String.format("%s - %s",
					confidentialOffenderDesignator.getCategory().getDescription(),
					confidentialOffenderDesignator.getCategory().getInstruction());
		}
		
		// TODO - Move formatting to presentation tier - SA
		// Add name properties to represent officer and allow presentation tier
		// to determine formatting
		if (officerCaseAssignment != null) {
			this.supervisingOfficer
				= String.format("%s, %s",
						officerCaseAssignment.getOfficer().getUser().getName()
							.getLastName(),
						officerCaseAssignment.getOfficer().getUser().getName()
							.getFirstName());
			this.supervisionStartDate = DateRange.getStartDate(
					officerCaseAssignment.getDateRange());
			this.officerAssigned = true;
		} else {
			this.officerAssigned = false;
		}
	}
	
	/** {@inheritDoc} */
	@Override
	public String getCurrentLocationReasonName() {
		return this.currentLocationReasonName;
	}
	
	/** {@inheritDoc} */
	@Override
	public Date getCurrentLocationStartDate() {
		return this.currentLocationStartDate;
	}
	
	/** {@inheritDoc} */
	@Override
	public String getCurrentLocationName() {
		return this.currentLocationName;
	}
	
	/** {@inheritDoc} */
	@Override
	public Date getCurrentLocationStartTime() {
		return this.currentLocationStartTime;
	}
	
	/** {@inheritDoc} */
	@Override
	public String getCorrectionalStatusName() {
		return this.correctionalStatusName;
	}
	
	/** {@inheritDoc} */
	@Override
	public Date getCorrectionalStatusStartDate() {
		return this.correctionalStatusStartDate;
	}
	
	/** {@inheritDoc} */
	@Override
	public Date getCorrectionalStatusStartTime() {
		return this.correctionalStatusStartTime;
	}
	
	/** {@inheritDoc} */
	@Override
	public String getCorrectionalStatusReasonName() {
		return this.correctionalStatusReasonName;
	}
	
	/** {@inheritDoc} */
	@Override
	public String getConfidentialOffender() {
		return this.confidentialOffender;
	}
	
	/** {@inheritDoc} */
	@Override
	public String  getSupervisingOfficer() {
		return this.supervisingOfficer;
	}
	
	/** {@inheritDoc} */
	@Override
	public Date getSupervisionStartDate() {
		return this.supervisionStartDate;
	}
	
	/** {@inheritDoc} */
	@Override
	public Date getProbationDischargeDate() {
		return this.probationDischargeDate;
	}
	
	/** {@inheritDoc} */
	@Override
	public Date getParoleEligibilityDate() {
		return this.paroleEligibilityDate;
	}
	
	/** {@inheritDoc} */
	@Override
	public Date getPrisonDischargeDate() {
		return this.prisonDischargeDate;
	}
	
	/** {@inheritDoc} */
	@Override
	public String getChimesId() {
		return this.chimesId;
	}
	
	/** {@inheritDoc} */
	@Override
	public Boolean getPlaced() {
		return this.placed;
	}
	
	/** {@inheritDoc} */
	@Override
	public Boolean getLocated() {
		return this.located;
	}
	
	/** {@inheritDoc} */
	@Override
	public Boolean getOfficerAssigned() {
		return this.officerAssigned;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setCurrentLocationName(final String currentLocationName) {
		this.currentLocationName = currentLocationName;
	}
	
	
	/** {@inheritDoc} */
	@Override
	public void setCurrentLocationReasonName(
					final String currentLocationReasonName) {
		this.currentLocationReasonName = currentLocationReasonName;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setCurrentLocationStartDate(
					final Date currentLocationStartDate) {
		this.currentLocationStartDate = currentLocationStartDate;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setCurrentLocationStartTime(
					final Date currentLocationStartTime) {
		this.currentLocationStartTime = currentLocationStartTime;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setCorrectionalStatusName(final String correctionalStatusName) {
		this.correctionalStatusName = correctionalStatusName;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setCorrectionalStatusStartDate(
					final Date correctionalStatusStartDate) {
		this.correctionalStatusStartDate = correctionalStatusStartDate;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setCorrectionalStatusStartTime(
					final Date correctionalStatusStartTime) {
		this.correctionalStatusStartTime = correctionalStatusStartTime;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setCorrectionalStatusReasonName(
					final String correctionalStatusReasonName) {
		this.correctionalStatusReasonName = correctionalStatusReasonName;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setConfidentialOffender(final String confidentialOffender) {
		this.confidentialOffender = confidentialOffender;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setSupervisingOfficer(final String supervisingOfficer) {
		this.supervisingOfficer = supervisingOfficer;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setSupervisionStartDate(final Date supervisionStartDate) {
		this.supervisionStartDate = supervisionStartDate;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setParoleEligibilityDate(final Date paroleEligibilityDate) {
		this.paroleEligibilityDate = paroleEligibilityDate;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setProbationDischargeDate(final Date probationDischargeDate) {
		this.probationDischargeDate = probationDischargeDate;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setPrisonDischargeDate(final Date prisonDischargeDate) {
		this.prisonDischargeDate = prisonDischargeDate;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setChimesId(final String chimesId) {
		this.chimesId = chimesId;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setPlaced(final Boolean placed) {
		this.placed = placed;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setLocated(final Boolean located) {
		this.located = located;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setOfficerAssigned(final Boolean officerAssigned) {
		this.officerAssigned = officerAssigned;
	}
}