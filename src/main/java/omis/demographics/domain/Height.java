package omis.demographics.domain;

import java.io.Serializable;

/**
 * Height measurement in feet and inches. An example would be 5ft 6in.
 * 
 * @author Stephen Abson
 * @version 0.1.2 (Jan 31, 2013)
 * @since OMIS 3.0
 */
public class Height implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private static final int MIN_INCHES = 0;
	
	private static final int MAX_INCHES = 11;
	
	private Integer feet;
	
	private Integer inches;
	
	/** Instantiates a default height. */
	public Height() {
		// Default instance
	}
	
	/**
	 * Instantiates with the specified height values.
	 * 
	 * @param feet feet
	 * @param inches inches
	 * @throws IllegalArgumentException if inches is not between {@code 0}
	 * and {@code 11}
	 */
	public Height(final Integer feet, final Integer inches) {
		if (inches != null) {
			validate(inches);
		}
		this.feet = feet;
		this.inches = inches;
	}
	
	/**
	 * Sets the feet value.
	 * 
	 * @param feet new feet value
	 */
	public void setFeet(final Integer feet) {
		this.feet = feet;
	}

	/**
	 * Returns the feet value.
	 * 
	 * @return feet value
	 */
	public Integer getFeet() {
		return this.feet;
	}

	/**
	 * Sets the inches value.
	 * 
	 * @param inches new inches value.
	 */
	public void setInches(final Integer inches) {
		this.inches = inches;
	}

	/**
	 * Returns the inches value.
	 * 
	 * @return inches value
	 */
	public Integer getInches() {
		return this.inches;
	}
	
	/**
	 * Changes the height.
	 * 
	 * @param feet feet
	 * @param inches inches
	 * @throws IllegalArgumentException if inches is not between {@code 0}
	 * and {@code 11} 
	 */
	public void change(final Integer feet, final Integer inches) {
		if (inches != null) {
			validate(inches);
		}
		this.setFeet(feet);
		this.setInches(inches);
	}
	
	// Validates feet and inches values
	private void validate(final int inches) {
		if (inches < MIN_INCHES || inches > MAX_INCHES) {
			throw new IllegalArgumentException(
					"Inches must be between " + MIN_INCHES + " and "
					+ MAX_INCHES);
		}
	}
	
	/**
	 * Compares {@code this} and {@code obj} for equality.
	 * 
	 * @param obj reference object to which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code obj} are equal;
	 * {@code false} otherwise
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Height)) {
			return false;
		}
		Height that = (Height) obj;
		if (!((this.getFeet() == null && that.getFeet() == null)
				|| (this.getFeet() != null 
						&& this.getFeet().equals(that.getFeet())))) {
			return false;
		}
		if (!((this.getInches() == null && that.getInches() == null)
				|| (this.getInches() != null
						&& this.getInches().equals(that.getInches())))) {
			return false;
		}
		return true;
	}
	
	/**
	 * Returns a hash code for the object.
	 * 
	 * <p>Any mandatory property of {@code this} may be used in the hash code.
	 * If a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * 
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null}
	 */
	@Override
	public int hashCode() {
		int hashCode = 14;
		if (this.getFeet() != null) {
			hashCode = 29 * hashCode + this.getFeet().hashCode();
		}
		if (this.getInches() != null) {
			hashCode = 29 * hashCode + this.getInches().hashCode();
		}
		return hashCode;
	}
	
	/**
	 * Returns a string representing the height in feet and inches.
	 * 
	 * @return height in feet and inches
	 */
	@Override
	public String toString() {
		return String.format("%dft %din", this.getFeet(), this.getInches());
	}
}