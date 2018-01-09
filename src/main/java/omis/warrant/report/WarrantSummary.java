package omis.warrant.report;

import java.util.Date;

import omis.warrant.domain.WarrantReasonCategory;

/**
 * WarrantSummary.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 9, 2017)
 *@since OMIS 3.0
 *
 */
public class WarrantSummary {
	
	private final Long warrantId;
	
	private final Date warrantDate;
	
	private final WarrantReasonCategory warrantReasonCategory;
	
	private final String clearedByLastName;
	
	private final String clearedByFirstName;
	
	private final Date clearedByDate;
	
	private final Long warrantReleaseId;
	
	private final Long warrantCancellationId;
	
	private final Long warrantArrestId;

	/**
	 * @param warrantId
	 * @param warrantDate
	 * @param warrantReasonCategory
	 * @param clearedByLastName
	 * @param clearedByFirstName
	 * @param clearedByDate
	 * @param warrantReleaseId
	 * @param warrantCancellationId
	 */
	public WarrantSummary(final Long warrantId, final Date warrantDate,
			final WarrantReasonCategory warrantReasonCategory,
			final String clearedByLastName, final String clearedByFirstName,
			final Date clearedByDate, final Long warrantReleaseId,
			final Long warrantCancellationId, final Long warrantArrestId) {
		this.warrantId = warrantId;
		this.warrantDate = warrantDate;
		this.warrantReasonCategory = warrantReasonCategory;
		this.clearedByLastName = clearedByLastName;
		this.clearedByFirstName = clearedByFirstName;
		this.clearedByDate = clearedByDate;
		this.warrantReleaseId = warrantReleaseId;
		this.warrantCancellationId = warrantCancellationId;
		this.warrantArrestId = warrantArrestId;
	}
	
	/**
	 * @param warrantId
	 * @param warrantDate
	 * @param warrantReasonCategory
	 */
	public WarrantSummary(final Long warrantId, final Date warrantDate,
			final WarrantReasonCategory warrantReasonCategory, final Long warrantArrestId) {
		this.warrantId = warrantId;
		this.warrantDate = warrantDate;
		this.warrantReasonCategory = warrantReasonCategory;
		this.clearedByLastName = null;
		this.clearedByFirstName = null;
		this.clearedByDate = null;
		this.warrantReleaseId = null;
		this.warrantCancellationId = null;
		this.warrantArrestId = warrantArrestId;
	}

	/**
	 * Returns the warrantId
	 * @return warrantId - Long
	 */
	public Long getWarrantId() {
		return warrantId;
	}

	/**
	 * Returns the warrantDate
	 * @return warrantDate - Date
	 */
	public Date getWarrantDate() {
		return warrantDate;
	}

	/**
	 * Returns the warrantReasonCategoryName
	 * @return warrantReasonCategory - WarrantReasonCategory
	 */
	public WarrantReasonCategory getWarrantReasonCategory() {
		return warrantReasonCategory;
	}

	/**
	 * Returns the clearedByLastName
	 * @return clearedByLastName - String
	 */
	public String getClearedByLastName() {
		return clearedByLastName;
	}

	/**
	 * Returns the clearedByFirstName
	 * @return clearedByFirstName - String
	 */
	public String getClearedByFirstName() {
		return clearedByFirstName;
	}

	/**
	 * Returns the clearedByDate
	 * @return clearedByDate - Date
	 */
	public Date getClearedByDate() {
		return clearedByDate;
	}

	/**
	 * Returns the warrantReleaseId
	 * @return warrantReleaseId - Long
	 */
	public Long getWarrantReleaseId() {
		return warrantReleaseId;
	}

	/**
	 * Returns the warrantCancellationId
	 * @return warrantCancellationId - Long
	 */
	public Long getWarrantCancellationId() {
		return warrantCancellationId;
	}

	/**
	 * Returns the warrantArrestId
	 * @return warrantArrestId - Long
	 */
	public Long getWarrantArrestId() {
		return warrantArrestId;
	}
	
	
	
}
