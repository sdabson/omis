package omis.detainernotification.domain.component;

import java.io.Serializable;
import java.util.Date;

import omis.detainernotification.domain.DetainerWarrantCancellationReason;

/**
 * Detainer warrant cancellation.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 7, 2016)
 * @since OMIS 3.0
 */
public class DetainerWarrantCancellation implements Serializable {

	private static final long serialVersionUID = 1L;	
	
	private Date date;
	
	private DetainerWarrantCancellationReason reason;
	
	/** Instantiates a default detainer warrant cancellation. */
	public DetainerWarrantCancellation() {
		//Default constructor.
	}

	public DetainerWarrantCancellation(
			final Date date, final DetainerWarrantCancellationReason reason) {
		this.date  = date;
		this.reason = reason;
	}
	
	public void setDate(final Date date) {
		if (date != null) {
			this.date = date;
		} else {
			this.date = null;
		}
	}
	
	public void setReason(final DetainerWarrantCancellationReason reason) {
		if (reason != null) {
			this.reason = reason;
		} else {
			this.reason = null;
		}		
	}
	
	public Date getDate() {
		if (this.date != null) {
			return this.date;
		} else {
			return null;
		}
	}
	
	public DetainerWarrantCancellationReason getReason() {
		if (this.reason != null) {
			return this.reason;
		} else {
			return null;
		}
	}	
}