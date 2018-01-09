package omis.substanceuse.web.form;

import java.util.Date;

import omis.substanceuse.domain.UseFrequency;
import omis.substanceuse.domain.UseMeasurement;
import omis.substanceuse.domain.UseTermSource;

/**
 * Use term item.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Nov 29, 2016)
 * @since OMIS 3.0
 */
public class UseTermItem {

	private Date startDate;
	private Date endDate;
	private UseFrequency useFrequency;
	private Long useQuantity;
	private UseMeasurement useMeasurement;
	private UseTermSource termSource;
	
	/**
	 * Instantiates a default instance of use term item.
	 */
	public UseTermItem() {
		//Default constructor.
	}
	
	/**
	 * Instantiates an instance of use term item.
	 * 
	 * @param startDate start date
	 * @param endDate end date
	 * @param useFrequency use frequency
	 * @param useQuantity use quantity
	 * @param useMeasurement use measurement
	 * @param termSource use term source
	 */
	public UseTermItem(final Date startDate, final Date endDate,
			final UseFrequency useFrequency, final Long useQuantity,
			final UseMeasurement useMeasurement, 
			final UseTermSource termSource) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.useFrequency = useFrequency;
		this.useQuantity = useQuantity;
		this.useMeasurement = useMeasurement;
		this.termSource = termSource;
	}

	/**
	 * Returns the start date.
	 * 
	 * @return start date
	 */
	public Date getStartDate() {
		return this.startDate;
	}

	/**
	 * Sets the start date.
	 * 
	 * @param startDate start date
	 */
	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Returns the end date.
	 * 
	 * @return end date
	 */
	public Date getEndDate() {
		return this.endDate;
	}

	/**
	 * Sets the end date.
	 * 
	 * @param endDate end date
	 */
	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * Returns the user frequency.
	 * 
	 * @return user frequency
	 */
	public UseFrequency getUseFrequency() {
		return this.useFrequency;
	}

	/**
	 * Sets the user frequency.
	 * 
	 * @param useFrequency use frequency
	 */
	public void setUseFrequency(final UseFrequency useFrequency) {
		this.useFrequency = useFrequency;
	}

	/**
	 * Returns the use quantity.
	 * 
	 * @return use quantity
	 */
	public Long getUseQuantity() {
		return this.useQuantity;
	}

	/**
	 * Sets the use quantity.
	 * 
	 * @param useQuantity use quantity
	 */
	public void setUseQuantity(final Long useQuantity) {
		this.useQuantity = useQuantity;
	}

	/**
	 * Returns the use measurement.
	 * 
	 * @return use measurement
	 */
	public UseMeasurement getUseMeasurement() {
		return this.useMeasurement;
	}

	/**
	 * Sets the use measurement.
	 * 
	 * @param useMeasurement use measurement
	 */
	public void setUseMeasurement(final UseMeasurement useMeasurement) {
		this.useMeasurement = useMeasurement;
	}

	/**
	 * Returns the use term source.
	 * 
	 * @return use term source
	 */
	public UseTermSource getTermSource() {
		return this.termSource;
	}

	/**
	 * Sets the use term source.
	 * 
	 * @param termSource use term source
	 */
	public void setTermSource(final UseTermSource termSource) {
		this.termSource = termSource;
	}
}