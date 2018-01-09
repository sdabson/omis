package omis.citation.report;

import omis.citation.domain.MisdemeanorDisposition;
import omis.datatype.Month;

/**
 * Summary for misdemeanor citation.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (Aug 19, 2016)
 * @since OMIS 3.0
 */

public class MisdemeanorCitationSummary {
	private final Long id;
	
	private final String offenderLastName;
	
	private final String offenderFirstName;
	
	private final String offenderMiddleName;
	
	private final String offenderSuffix;
	
	private final Integer offenderNumber;
	
	private final String offenseName;
	
	private final String stateName;
	
	private final String cityName;
	
	private final Integer day;
	
	private final Month month;
	
	private final Integer year;
	
	private final Integer counts;
	
	private final MisdemeanorDisposition disposition;
	
	/** Constructor.
	 * @param id - misdemeanor citation id.
	 * @param offenderLastName - offender last name.
	 * @param offenderFirstName - offender first name.
	 * @param offenderMiddleName - offender middle name.
	 * @param offenderSuffix - offender suffix.
	 * @param offenderNumber - offender number.
	 * @param offenseName - offense name.
	 * @param stateName - state name.
	 * @param cityName - city name.
	 * @param day - day
	 * @param month - month
	 * @param year - year
	 * @param counts - counts
	 * @param disposition - disposition	 */ 	
	public MisdemeanorCitationSummary(final Long id, final String offenderLastName,
			final String offenderFirstName, final String offenderMiddleName,
			final String offenderSuffix, final Integer offenderNumber,
			final String offenseName, final String stateName, 
			final String cityName, final Integer day, final Month month,
			final Integer year, final Integer counts, 
			final MisdemeanorDisposition disposition) {
		this.id = id;
		this.offenderLastName = offenderLastName;
		this.offenderFirstName = offenderFirstName;
		this.offenderMiddleName = offenderMiddleName;
		this.offenderSuffix = offenderSuffix;
		this.offenderNumber = offenderNumber;
		this.offenseName = offenseName;
		this.stateName = stateName;
		this.cityName = cityName;
		this.day = day;
		this.month = month;
		this.year = year;
		this.counts = counts;
		this.disposition = disposition;
	}
	
	/** Gets misdemeanor citation id.
	 * @return misdemeanor citation id. */
	public Long getId() {
		return this.id;
	}
	
	/** Gets offender last name.
	 * @return offender last name. */
	public String getoffenderLastName() {
		return this.offenderLastName;
	}
	
	/** Gets offender first name.
	 * @return offender first name. */
	public String getoffenderFirstName() {
		return this.offenderFirstName;
	}
	
	/** Gets offender middle name.
	 * @return offender middle name. */
	public String getoffenderMiddletName() {
		return this.offenderMiddleName;
	}
	
	/** Gets offender suffix.
	 * @return offender suffix. */
	public String getoffenderSuffix() {
		return this.offenderSuffix;
	}
	
	/** Gets offender number.
	 * @return offender number. */
	public Integer getoffenderNumber() {
		return this.offenderNumber;
	}
	
	/** Gets misdemeanor offense name.
	 * @return misdemeanor offense name. */
	public String getOffenseName() {
		return this.offenseName;
	}
	
	/** Gets state name.
	 * @return state name. */
	public String getStateName() {
		return this.stateName;
	}
	
	/** Gets city name.
	 * @return city name. */
	public String getCityName() {
		return this.cityName;
	}
	
	/** Gets citation day.
	 * @return citation day. */
	public Integer getDay() {
		return this.day;
	}
	
	/** Gets citation month.
	 * @return citation month. */
	public Month getMonth() {
		return this.month;
	}
	
	/** Gets citation year.
	 * @return citation year. */
	public Integer getYear() {
		return this.year;
	}
	
	/** Gets misdemeanor citation counts.
	 * @return citation counts */
	public Integer getCounts() {
		return this.counts;
	}
	
	/** Gets misdemeanor disposition.
	 * @return misdemeanor disposition. */
	public MisdemeanorDisposition getMisdemeanorDisposition() {
		return this.disposition;
	}
}
