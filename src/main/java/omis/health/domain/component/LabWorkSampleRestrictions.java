package omis.health.domain.component;

import java.io.Serializable;

/**
 * Lab work sample restrictions.
 * 
 * <p>Restrictions apply prior to the sample being taken.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jun 13, 2014)
 * @since OMIS 3.0
 */
public class LabWorkSampleRestrictions
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Boolean nothingPerOral;
	
	private Boolean noLeaky;
	
	private Boolean noMeds;
	
	/** Instantiates a lab work sample restriction. */
	public LabWorkSampleRestrictions() {
		// Default instantiation
	}
	
	/**
	 * Instantiates a lab work sample restriction.
	 *
	 * @param nothingPerOral whether nothing per oral restiction applied
	 * @param noLeaky whether no leaky restriction applies
	 * @param noMeds whether no medications restriction applies
	 */
	public LabWorkSampleRestrictions(
			final Boolean nothingPerOral, final Boolean noLeaky,
			final Boolean noMeds) {
		this.nothingPerOral = nothingPerOral;
		this.noLeaky = noLeaky;
		this.noMeds = noMeds;
	}

	/**
	 * Sets whether nothing per oral restriction applies.
	 * 
	 * @param nothingPerOral whether nothing per oral restriction applies
	 */
	public void setNothingPerOral(final Boolean nothingPerOral) {
		this.nothingPerOral = nothingPerOral;
	}
	
	/**
	 * Returns whether nothing per oral restriction applies.
	 * 
	 * @return whether nothing per oral restriction applies
	 */
	public Boolean getNothingPerOral() {
		return this.nothingPerOral;
	}

	/**
	 * Sets whether no leaky restriction applies.
	 * 
	 * @param noLeaky whether no leaky restriction applies
	 */
	public void setNoLeaky(final Boolean noLeaky) {
		this.noLeaky = noLeaky;
	}
	
	/**
	 * Returns whether no leaky restriction applies.
	 * 
	 * @return whether no leaky restriction applies
	 */
	public Boolean getNoLeaky() {
		return this.noLeaky;
	}

	/**
	 * Returns whether no medication restriction applies.
	 * 
	 * @return whether no medication restriction applies.
	 */
	public Boolean getNoMeds() {
		return this.noMeds;
	}

	/**
	 * Sets whether no medication restriction applies.
	 * 
	 * @param noMeds whether no medication restriction applies
	 */
	public void setNoMeds(final Boolean noMeds) {
		this.noMeds = noMeds;
	}
}