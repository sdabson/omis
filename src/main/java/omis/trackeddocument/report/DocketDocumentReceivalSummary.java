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
package omis.trackeddocument.report;

import java.io.Serializable;

/**
 * Docket document receival summary.
 * 
 * @author Yidong Li
 * @version 0.1.0 (Dec 12, 2017)
 * @since OMIS 3.0
 */
public class DocketDocumentReceivalSummary implements Serializable {
	private static final long serialVersionUID = 1L;
	private final Long docketId;
	private final String docketValue;
	private final String courtName;
	private final Long count;

	/**
	 * Instantiates a docket document receival summary with the
	 * specified properties.
	 * 
	 * @param docketId docket Id
	 * @param docketValue docket value
	 * @param count count
	 * @param courtName court name
	 */
	public DocketDocumentReceivalSummary(final Long docketId, 
			final String docketValue, 
			final String courtName, 
			final Long count) {
		this.docketValue = docketValue;
		this.docketId = docketId;
		this.count = count;
		this.courtName = courtName;
	}

	/**
	 * Returns the docket id.
	 * 
	 * @return docket id
	 */
	public Long getDocketId() {
		return this.docketId;
	}

	/**
	 * Returns the court name.
	 * 
	 * @return court name
	 */
	public String getCourtName() {
		return this.courtName;
	}
	
	/**
	 * Returns docket value.
	 * 
	 * @return docket value
	 */
	public String getDocketValue() {
		return this.docketValue;
	}

	/**
	 * Returns the count of associated receival.
	 * 
	 * @return count
	 */
	public Long getCount() {
		return this.count;
	}
}