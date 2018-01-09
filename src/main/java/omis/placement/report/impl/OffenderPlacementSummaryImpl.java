package omis.placement.report.impl;

import java.util.Date;

import omis.placement.report.OffenderPlacementSummary;

/** Implementation of location summary.
 * @author Ryan Johns
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
	
	/** Default Constructor. */
	public OffenderPlacementSummaryImpl() {
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
}