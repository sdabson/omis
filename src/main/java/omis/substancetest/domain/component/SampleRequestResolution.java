package omis.substancetest.domain.component;

import java.io.Serializable;

import omis.substancetest.domain.SampleRequestStatusReason;
import omis.substancetest.domain.SubstanceTestSample;

/**
 * Sample request resolution.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Mar 8, 2016)
 * @since OMIS 3.0
 */
public class SampleRequestResolution implements Serializable {

	private static final long serialVersionUID = 1L;

	private SubstanceTestSample sample;
	
	private SampleRequestStatusReason statusReason;
	
	/**
	 * Instantiates a default instance of sample request resolution.
	 */
	public SampleRequestResolution() {
		//Default constructor.
	}
	
	/**
	 * Instantiates a sample request resolution with the specified sample
	 * and status reason.
	 * 
	 * @param sample substance test sample
	 * @param statusReason sample status reason
	 */
	public SampleRequestResolution(final SubstanceTestSample sample,
			final SampleRequestStatusReason statusReason) {
		this.sample = sample;
		this.statusReason = statusReason;
	}

	/**
	 * Returns the substance test sample.
	 * 
	 * @return sample
	 */
	public SubstanceTestSample getSample() {
		return this.sample;
	}

	/**
	 * Sets the substance test sample.
	 * 
	 * @param sample substance test sample
	 */
	public void setSample(final SubstanceTestSample sample) {
		this.sample = sample;
	}

	/**
	 * Returns the sample status reason.
	 * 
	 * @return status reason
	 */
	public SampleRequestStatusReason getStatusReason() {
		return this.statusReason;
	}

	/**
	 * Sets the sample status reason.
	 * 
	 * @param statusReason sample status reason
	 */
	public void setStatusReason(final SampleRequestStatusReason statusReason) {
		this.statusReason = statusReason;
	}
}