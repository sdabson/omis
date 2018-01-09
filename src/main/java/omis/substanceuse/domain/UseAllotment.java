package omis.substanceuse.domain;

import java.io.Serializable;

/**
 * Use allotment.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Nov 16, 2016)
 * @since OMIS 3.0
 */
public class UseAllotment implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long quantity;
	private UseMeasurement measurement;
	
	/**
	 * Instantiates a default instance of use allotment.
	 */
	public UseAllotment() {
		//Default constructor.
	}
	
	/**
	 * Instantiates an instance of use allotment with the specified
	 * quantity and measurement.
	 * 
	 * @param quantity quantity
	 * @param measurement measurement
	 */
	public UseAllotment(final Long quantity, final UseMeasurement measurement) {
		this.quantity = quantity;
		this.measurement = measurement;
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
	 * Sets the quantity.
	 * 
	 * @param quantity quantity
	 */
	public void setQuantity(final Long quantity) {
		this.quantity = quantity;
	}

	/**
	 * Returns the measurement.
	 * 
	 * @return measurement
	 */
	public UseMeasurement getMeasurement() {
		return this.measurement;
	}

	/**
	 * Sets the measurement.
	 * 
	 * @param measurement measurement
	 */
	public void setMeasurement(final UseMeasurement measurement) {
		this.measurement = measurement;
	}
}