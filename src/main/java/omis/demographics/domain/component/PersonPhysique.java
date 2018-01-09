package omis.demographics.domain.component;

import java.io.Serializable;

import omis.demographics.domain.Build;
import omis.demographics.domain.Height;
import omis.demographics.domain.Weight;

/**
 * The physique of a person.
 * 
 * @author Stephen Abson
 * @version 0.1.1 (Jan 31, 2013)
 * @since OMIS 3.0
 */
public class PersonPhysique implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Height height;

	private Weight weight;
	
	private Build build;
	
	/** Instantiates a default person physique. */
	public PersonPhysique() {
		// Default instance
	}
	
	/**
	 * Instantiates a person physique with the specified height, weight and
	 * build.
	 * 
	 * @param height height
	 * @param weight weight
	 * @param build build
	 */
	public PersonPhysique(final Height height, final Weight weight,
			final Build build) {
		this.height = height;
		this.weight = weight;
		this.build = build;
	}
	
	/**
	 * Sets the height.
	 * 
	 * @param height height
	 */
	public void setHeight(final Height height) {
		this.height = height;
	}
	
	/**
	 * Returns the height.
	 * 
	 * @return height
	 */
	public Height getHeight() {
		return this.height;
	}
	
	/**
	 * Sets the weight.
	 * 
	 * @param weight weight
	 */
	public void setWeight(final Weight weight) {
		this.weight = weight;
	}
	
	/**
	 * Returns the weight.
	 * 
	 * @return weight
	 */
	public Weight getWeight() {
		return this.weight;
	}

	/**
	 * Sets the build.
	 * 
	 * @param build build
	 */
	public void setBuild(final Build build) {
		this.build = build;
	}
	
	/**
	 * Returns the build.
	 * 
	 * @return build
	 */
	public Build getBuild() {
		return this.build;
	}
}