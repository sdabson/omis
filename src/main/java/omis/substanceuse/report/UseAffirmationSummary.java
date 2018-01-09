package omis.substanceuse.report;

import java.io.Serializable;
import java.util.Date;

/**
 * Use affirmation summary.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Nov 28, 2016)
 * @since OMIS 3.0
 */
public class UseAffirmationSummary implements Serializable {

	private static final long serialVersionUID = 1L;
	private final Long id;
	private final Date date;
	private final String frequencyName;
	private final Long quantity;
	private final String measurementName;
	
	/**
	 * Instantiates an instance of use affirmation summary.
	 * 
	 * @param id id
	 * @param date date
	 * @param frequencyName frequency name
	 * @param quantity quantity
	 * @param measurementName measurement name
	 */
	public UseAffirmationSummary(final Long id, final Date date,
			final String frequencyName, final Long quantity,
			final String measurementName) {
		this.id = id;
		this.date = date;
		this.frequencyName = frequencyName;
		this.quantity = quantity;
		this.measurementName = measurementName;
	}

	/**
	 * Returns the id.
	 * 
	 * @return id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * Returns the date.
	 * 
	 * @return date
	 */
	public Date getDate() {
		return this.date;
	}

	/**
	 * Returns the frequency name.
	 * 
	 * @return frequency name
	 */
	public String getFrequencyName() {
		return this.frequencyName;
	}

	/**
	 * Returns the quantity.
	 * 
	 * @return quantity
	 */
	public Long getQuantity() {
		return this.quantity;
	}

	/**
	 * Returns the measurement name.
	 * 
	 * @return measurement name
	 */
	public String getMeasurementName() {
		return this.measurementName;
	}
}