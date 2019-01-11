/*
 * OMIS - Offender Management Information System
 * Copyright (C) 2011 - 2017 State of Montana
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package omis.placement.report;

import java.util.Date;

/** Summary for offender location. 
 * Note: Revisit this when we are ready to move location to production.
 * @author Ryan Johns
 * @author Stephen Abson
 * @version 0.1.0 (Jul 28, 2016)
 * @since OMIS 3.0 */
public interface OffenderPlacementSummary {
	/** Gets current location name. 
	 * @return current location name. */
	String getCurrentLocationName();
	
	/** Gets current location reason.
	 * @return current location reason. */
	String getCurrentLocationReasonName();
	
	/** Gets current location start date.
	 * @return current location start date. */
	Date getCurrentLocationStartDate();
	
	/** Gets current location start time.
	 * @return current location start time. */
	Date getCurrentLocationStartTime();
	
	/** Gets correctional status name.
	 * @return correctional status name. */
	String getCorrectionalStatusName();
	
	/** Gets correctional status start date.
	 * @return correctional status start date. */
	Date getCorrectionalStatusStartDate();
	
	/** Gets correctional status start time.
	 * @return correctional status start time. */
	Date getCorrectionalStatusStartTime();
	
	/** Get correctional status reason.
	 * @return correctional status reason. */
	String getCorrectionalStatusReasonName();
	
	/** Gets confidential offender.
	 * @return confidential offender. */
	String getConfidentialOffender();
	
	/** Ges supervising officer.
	 * @return supervising officer. */
	String getSupervisingOfficer();
	
	/** Gets supervising officer start date.
	 * @return supervision start date. */
	Date getSupervisionStartDate();
	
	/** Gets Parole eligibility date.
	 * @return parole eligibility date. */
	Date getParoleEligibilityDate();
	
	/** Gets Prison discharge date.
	 * @return prison discharge date. */
	Date getPrisonDischargeDate();
	
	/** Gets probation discharge date.
	 * @return probation discharge date. */
	Date getProbationDischargeDate();
	
	/** Gets chime id.
	 * @return chimes id. */
	String getChimesId();
	
	/**
	 * Returns whether offender is placed.
	 * 
	 * @return whether offender is placed
	 */
	Boolean getPlaced();
	
	/**
	 * Returns whether offender is located.
	 * 
	 * @return whether offender is located
	 */
	Boolean getLocated();
	
	/**
	 * Returns whether offender has an assigned officer.
	 * 
	 * @return whether offender has an assigned officer
	 */
	Boolean getOfficerAssigned();
	
	/** Sets current location name.
	 * @param currentLocationName - current location name. */
	void setCurrentLocationName(String currentLocationName);
	
	/** Sets current location reason name.
	 * @param currentLocationReasonName - current location reason name. */
	void setCurrentLocationReasonName(String currentLocationReasonName);
	
	/** Sets current location start date.
	 * @param currentLocationStartDate - current location start date. */
	void setCurrentLocationStartDate(Date currentLocationStartDate);
	
	/** Sets current location start time.
	 * @param currentLocationStartTime - current location start time. */
	void setCurrentLocationStartTime(Date currentLocationStartTime);
	
	/** Sets correctional status name.
	 * @param correctionalStatusName - correctional  status name. */
	void setCorrectionalStatusName(String correctionalStatusName);
	
	/** Sets correctional status start date.
	 * @param correctionalStatusStartDate - correctional Status start date. */
	void setCorrectionalStatusStartDate(Date correctionalStatusStartDate);
	
	/** Sets correctional status start time.
	 * @param correctionalStatusStartTime - correctional status start time. */
	void setCorrectionalStatusStartTime(Date correctionalStatusStartTime);
	
	/** Sets correctional status reason.
	 * @param correctionalStatusReasonName - correctional status reason name. */
	void setCorrectionalStatusReasonName(String correctionalStatusReasonName);

	/** Sets confidential offender.
	 * @param confidentialOffender - confidential offender. */
	void setConfidentialOffender(String confidentialOffender);
	
	/** Sets supervising officer.
	 * @param supervisingOfficer - supervising officer. */
	void setSupervisingOfficer(String supervisingOfficer);
	
	/** Sets supervision start date.
	 * @param supervisionStartDate - supervision start date. */
	void setSupervisionStartDate(Date supervisionStartDate);
	
	/** Sets Parole eligibility date.
	 * @param paroleEligibilityDate - parole eligibility date. */
	void setParoleEligibilityDate(Date paroleEligibilityDate);
	
	/** Sets Prison discharge date.
	 * @param prisonDischargeDate - prison discharge date. */
	void setPrisonDischargeDate(Date prisonDischargeDate);
	
	/** Sets probation discharge date.
	 * @param probationDischargeDate - probation discharge date. */
	void setProbationDischargeDate(Date probationDischargeDate);
	
	/** Sets chimes id.
	 * @param chimesId - chimes id. */
	void setChimesId(String chimesId);
	
	/**
	 * Sets whether offender is placed.
	 * 
	 * @param placed whether offender is placed
	 */
	void setPlaced(Boolean placed);
	
	/**
	 * Sets whether offender is located.
	 * 
	 * @param located set whether offender is located
	 */
	void setLocated(Boolean located);
	
	/**
	 * Sets whether offender has an officer assignment.
	 * 
	 * @param officerAssigned whether offender has an officer assignment
	 */
	void setOfficerAssigned(Boolean officerAssigned);
}
