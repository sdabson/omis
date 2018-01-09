package omis.detainernotification.report;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 *
 * @author Sheronda Vaughn
 * @author Annie Jacques
 * @version 0.1.0 (Jul 12, 2016)
 * @since OMIS 3.0
 */
public class DetainerSummary implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private final Long detainerId;
	
	private final Date warrantReceivedDate;
	
	private final Date notificationDesignationDate;
	
	private final String detainerTypeName;
	
	private final String requestingAgencyLocationName;
	
	private final String courtCaseNumber;
	
	private final String warrantNumber;
	
	private final Date cancellationDate;

	/**
	 * Constructor.  
	 * 
	 * @param warrantReceivedDate warrant received date
	 * @param notificationDesignationName notification designation name
	 * @param detainerTypeName detainer type name
	 * @param requestingAgencyLocationName requesting agency location name
	 * @param courtCaseNumber court case number
	 * @param warrantNumber warrant number
	 * @param cancellationDate cancellation date
	 */
	public DetainerSummary(final Long detainerId, final Date warrantReceivedDate, 
			final Date notificationDesignationDate, 
			final String detainerTypeName, 
			final String requestingAgencyLocationName, 
			final String courtCaseNumber, final String warrantNumber, 
			final Date cancellationDate) {
		this.detainerId = detainerId;
		this.warrantReceivedDate = warrantReceivedDate;
		this.notificationDesignationDate = notificationDesignationDate;
		this.detainerTypeName = detainerTypeName;
		this.requestingAgencyLocationName = requestingAgencyLocationName;
		this.courtCaseNumber = courtCaseNumber;
		this.warrantNumber = warrantNumber;
		this.cancellationDate = cancellationDate;
	}
	
	/**
	 * Returns the detainer ID
	 * 
	 * @return the detainerId
	 */
	public Long getDetainerId() {
		return detainerId;
	}

	/**
	 * Returns the warrant received date of the detainer.
	 * 
	 * @return the warrantReceivedDate
	 */
	public Date getWarrantReceivedDate() {
		return this.warrantReceivedDate;
	}

	/**
	 * Returns the notification designation date of the detainer.
	 * 
	 * @return the notificationDesignationDate
	 */
	public Date getNotificationDesignationDate() {
		return this.notificationDesignationDate;
	}

	/**
	 * Returns the detainer type name of the detainer.
	 * 
	 * @return the detainerTypeName
	 */
	public String getDetainerTypeName() {
		return this.detainerTypeName;
	}

	/**
	 * Returns the requesting agency location name of the detainer.
	 * 
	 * @return the requestingAgencyLocationName
	 */
	public String getRequestingAgencyLocationName() {
		return this.requestingAgencyLocationName;
	}

	/**
	 * Returns the court case number of the detainer.
	 * 
	 * @return the courtCaseNumber
	 */
	public String getCourtCaseNumber() {
		return this.courtCaseNumber;
	}

	/**
	 * Returns the warrant number of the detainer.
	 * 
	 * @return the warrantNumber
	 */
	public String getWarrantNumber() {
		return this.warrantNumber;
	}

	/**
	 * Returns the cancellation date of the detainer.
	 * 
	 * @return the cancellationDate
	 */
	public Date getCancellationDate() {
		return this.cancellationDate;
	}
}