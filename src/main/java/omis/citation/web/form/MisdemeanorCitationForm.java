package omis.citation.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.citation.domain.MisdemeanorDisposition;
import omis.citation.domain.MisdemeanorOffense;
import omis.datatype.Month;
import omis.region.domain.City;
import omis.region.domain.State;

/**
 * Used to capture misdemeanor citation information.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (Aug 12, 2016)
 * @since OMIS 3.0
 */

public class MisdemeanorCitationForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private MisdemeanorOffense offense;
	
	private State state;
	
	private City city;
	
	private Month month;
	
	private Integer year;
	
	private Integer counts;
	
	private MisdemeanorDisposition disposition;
	
	private Boolean createNewOffense;
	
	private String offenseName;
	
	private Boolean partialDate;
	
	private Date date;
	
	/** Instantiates a default caution form. */
	public MisdemeanorCitationForm() {
		// Default instantiation
	}
	
	/**
	 * Returns the misdemeanor offense.
	 * 
	 * @return misdemeanor offense
	 */
	public MisdemeanorOffense getOffense() {
		return this.offense;
	}

	/**
	 * Sets the misdemeanor offense.
	 * 
	 * @param offense misdemeanor offense
	 */
	public void setOffense(final MisdemeanorOffense offense) {
		this.offense = offense;
	}
	
	/**
	 * Returns the state.
	 * 
	 * @return state state
	 */
	public State getState() {
		return this.state;
	}
	
	/**
	 * Sets the state.
	 * 
	 * @param state state
	 */
	public void setState(final State state) {
		this.state = state;
	}
	
	/**
	 * Returns the city.
	 * 
	 * @return city city
	 */
	public City getCity() {
		return this.city;
	}
	
	/**
	 * Sets the city.
	 * 
	 * @param city city
	 */
	public void setCity(final City city) {
		this.city = city;
	}
	
	/**
	 * Returns the month of the citation.
	 * 
	 * @return month month
	 */
	public Month getMonth() {
		return this.month;
	}
	
	/**
	 * Sets the month of the citation.
	 * 
	 * @param month month
	 */
	public void setMonth(final Month month) {
		this.month = month;
	}
	
	/**
	 * Returns the year of the citation.
	 * 
	 * @return year year
	 */
	public Integer getYear() {
		return this.year;
	}
	
	/**
	 * Sets the year of the citation.
	 * 
	 * @param year year
	 */
	public void setYear(final Integer year) {
		this.year = year;
	}
	
	/**
	 * Returns the number of counts for the citation.
	 * 
	 * @return counts counts
	 */
	public Integer getCounts() {
		return this.counts;
	}
	
	/**
	 * Sets the number of counts for the citation.
	 * 
	 * @param counts counts
	 */
	public void setCounts(final Integer counts) {
		this.counts = counts;
	}
	
	/**
	 * Returns the disposition of the citation.
	 * 
	 * @return disposition misdemeanor disposition
	 */
	public MisdemeanorDisposition getDisposition() {
		return this.disposition;
	}
	
	/**
	 * Sets the disposition of the citation.
	 * 
	 * @param disposition misdemeanor disposition
	 */
	public void setDisposition(final MisdemeanorDisposition disposition) {
		this.disposition = disposition;
	}

	/**
	 * Returns whether to create a new offense.
	 * 
	 * @return whether to create a new offense
	 */
	public Boolean getCreateNewOffense() {
		return createNewOffense;
	}

	/**
	 * Sets whether to create a new offense.
	 * 
	 * @return whether to create a new offense
	 */
	public void setCreateNewOffense(Boolean createNewOffense) {
		this.createNewOffense = createNewOffense;
	}

	/**
	 * Returns new offense name.
	 * 
	 * @return new offense name
	 */
	public String getOffenseName() {
		return offenseName;
	}

	/**
	 * Sets new offense name.
	 * 
	 * @return new offense name
	 */
	public void setOffenseName(String offenseName) {
		this.offenseName = offenseName;
	}
	
	/**
	 * Returns whether to create a partial date.
	 * 
	 * @return whether to create a partial date
	 */
	public Boolean getPartialDate() {
		return partialDate;
	}
	
	/**
	 * Sets whether to create a partial date.
	 * 
	 * @return whether to create a partial date
	 */
	public void setPartialDate(Boolean partialDate) {
		this.partialDate = partialDate;
	}
	
	/**
	 * Returns a date.
	 * 
	 * @return a date
	 */
	public Date getDate() {
		return date;
	}
	
	/**
	 * Sets a date.
	 * 
	 * @return a date
	 */
	public void setDate(Date date) {
		this.date = date;
	}
}
