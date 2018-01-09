package omis.demographics.domain;

import java.io.Serializable;

/**
 * Weight in pounds.
 * 
 * @author Stephen Abson
 * @version 0.1.2 (Jan 31, 2013)
 * @since OMIS 3.0
 */
public class Weight implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer pounds;
	
	/** Instantiates a default weight. */
	public Weight() {
		// Default instance
	}
	
	/**
	 * Instantiates a weight with the specified pounds.
	 * 
	 * @param pounds pounds
	 */
	public Weight(final Integer pounds) {
		this.pounds = pounds;
	}
	
	/**
	 * Sets the weight in pounds.
	 * 
	 * @param pounds weight in pounds
	 */
	public void setPounds(final Integer pounds) {
		this.pounds = pounds;
	}

	/**
	 * Returns the weight in pounds.
	 * 
	 * @return weight in pounds
	 */
	public Integer getPounds() {
		return this.pounds;
	}
	
	/**
	 * Compare {@code this} and {@code obj} that for equality.
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
		if (!(obj instanceof Weight)) {
			return false;
		}
		Weight that = (Weight) obj;
		return (this.getPounds() == null && that.getPounds() == null)
			|| (this.getPounds() != null
				&& this.getPounds().equals(that.getPounds()));
	}
	
	/**
	 * Returns a hash code for the weight.
	 * 
	 * @return hash code
	 */
	@Override
	public int hashCode() {
		int hashCode = 14;
		if (this.getPounds() != null) {
			hashCode = 29 * hashCode + this.getPounds().hashCode();
		}
		return hashCode;
	}
	
	/**
	 * Returns a string representation of the weight including the value
	 * in pounds.
	 * 
	 * @return string representation including value in pounds 
	 */
	@Override
	public String toString() {
		return String.format("%d%s", this.getPounds(), "lb");
	}
}