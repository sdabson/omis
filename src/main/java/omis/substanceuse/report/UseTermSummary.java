package omis.substanceuse.report;

import java.io.Serializable;
import java.util.Date;

/**
 * Use term summary
 * @author Joel Norris
 * @version 0.1.0
 * @since OMIS 3.0
 */
public class UseTermSummary 
	implements Serializable {

	private static final long serialVersionUID = 1L;
	private final Long id;
	private final Date startDate;
	private final Date endDate;
	private final String frequencyName;
	private final Long quantity;
	private final String measurementName;
	
	/**
	 * Instantiates an instance of use term summary.
	 * 
	 * @param id id
	 * @param startDate start date
	 * @param endDate end date
	 * @param frequencyName frequency name
	 * @param quantity quantity
	 * @param measurementName measurement
	 */
	public UseTermSummary(final Long id, final Date startDate,
			final Date endDate, final String frequencyName, final Long quantity,
			final String measurementName) {
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
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
	 * Returns the start date.
	 * 
	 * @return start date
	 */
	public Date getStartDate() {
		return this.startDate;
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
	 * Returns measurement name.
	 * 
	 * @return measurement name
	 */
	public String getMeasurementName() {
		return this.measurementName;
	}
}