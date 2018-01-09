package omis.substancetest.domain.impl;

import java.util.Date;

import omis.offender.domain.Offender;
import omis.substancetest.domain.SampleRequest;
import omis.substancetest.domain.component.SampleRequestResolution;

/**
 * Substance test sample request implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Apr 28, 2015)
 * @since OMIS 3.0
 */
public class SampleRequestImpl 
implements SampleRequest {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Offender offender;
	
	private Date date;

	private SampleRequestResolution resolution;
	
	/**
	 * Instantiates a default instance of substance test sample request.
	 */
	public SampleRequestImpl() {
		//Default constructor.
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public Offender getOffender() {
		return this.offender;
	}

	/** {@inheritDoc} */
	@Override
	public void setOffender(final Offender offender) {
		this.offender = offender;
	}

	/** {@inheritDoc} */
	@Override
	public Date getDate() {
		return this.date;
	}

	/** {@inheritDoc} */
	@Override
	public void setDate(final Date date) {
		this.date = date;
	}
	
	/** {@inheritDoc} */
	@Override
	public SampleRequestResolution getResolution() {
		return this.resolution;
	}

	/** {@inheritDoc} */
	@Override
	public void setResolution(final SampleRequestResolution resolution) {
		this.resolution = resolution;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}	
		if (!(o instanceof SampleRequest)) {
			return false;
		}
		
		SampleRequest that = (SampleRequest) o;
		
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required.");
		}
		if (!this.getOffender().equals(that.getOffender())) {
			return false;
		}
		if (this.getDate() == null) {
			throw new IllegalStateException("Name required");
		}
		if (!this.getDate().equals(that.getDate())) {
			return false;
		}
		if (this.getResolution() != null) {
			if (!this.getResolution().equals(that.getResolution())) {
				return false;
			}
		} else if (that.getResolution() != null) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required.");
		}
		if (this.getDate() == null) {
			throw new IllegalStateException("Date required.");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getOffender().hashCode();
		hashCode = 29 * hashCode + this.getDate().hashCode();
		if (this.getResolution() != null) {
			hashCode = 29 * hashCode + this.getResolution().hashCode();
		}
		
		return hashCode;
	}
}