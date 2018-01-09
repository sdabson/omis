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
package omis.supervision.report;

import java.io.Serializable;
import java.util.Date;

/** Supervision summary.
 * @author Ryan Johns
 * @author Stephen Abson
 * @version 0.1.0 (Jul 27, 2015)
 * @since OMIS 3.0 */
public class SupervisionSummary implements Serializable {
	private static final long serialVersionUID = 1L;
	private final String correctionalStatusName;
	private final Date correctionalStatusTermStartDate;
	private final String supervisoryOrganziationName;
	private final String placementTermStartReasonName;
	private final Date supervisoryOrganizationTermStartDate;
	
	/** Constructor.
	 * @param correctionalStatusName - correctional status name.
	 * @param correctionalStatusTermStartDate - correctional status term start 
	 * date.
	 * @param supervisoryOrganizationName - supervisory organization name.
	 * @param placementTermStartReasonName - placement term start reason 
	 * name.
	 * @param supervisoryOrganizationTermStartDate - supervisory organization 
	 * term start date. */
	public SupervisionSummary(final String correctionalStatusName, 
			final Date correctionalStatusTermStartDate, 
			final String supervisoryOrganizationName, 
			final String placementTermStartReasonName,
			final Date supervisoryOrganizationTermStartDate) {
		this.correctionalStatusName = correctionalStatusName;
		this.correctionalStatusTermStartDate = correctionalStatusTermStartDate;
		this.supervisoryOrganziationName = supervisoryOrganizationName;
		this.placementTermStartReasonName = placementTermStartReasonName;
		this.supervisoryOrganizationTermStartDate = 
				supervisoryOrganizationTermStartDate;
	}
	/** Gets correctional Status name.
	 * @return correctional status name. */
	public String getCorrectionalStatusName() { 
		return this.correctionalStatusName; 
	} 
	
	/** Gets correctional status start date.
	 * @return correctional status start date. */
	public Date getCorrectionalStatusTermStartDate() { 
		return this.correctionalStatusTermStartDate; 
	}
	
	/** Gets supervisory organization name.
	 * @return supervisory organization name. */
	public String getSupervisoryOrganizationName() { 
		return this.supervisoryOrganziationName; 
	}
	
	/** Gets placement term  start reason name.
	 * @return placement term start reason name. */
	public String getPlacementTermStartReasonName() { 
		return this.placementTermStartReasonName;
	}
	
	/** Gets supervisory organization term start date.
	 * @return supervisory organization term start date. */
	public Date getSupervisoryOrganizationTermStartDate() {
		return this.supervisoryOrganizationTermStartDate;
	}
}