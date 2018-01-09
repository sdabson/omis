package omis.substance.report;

import java.io.Serializable;
import java.util.Date;

/**
 * Substance summary.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 24, 2013)
 * @since OMIS 3.0
 */
public class SubstanceTestSummary implements Serializable,
	Comparable<SubstanceTestSummary> {

	private static final long serialVersionUID = 1L;
	
	private final Long sampleId;

	private final String collectionMethodName;
	
	private final Date sampleDate;
	
	private final String substanceTestReason;
	
	private final Date testDate;
	
	private final Long substanceTestId;
	
	private final String testResultName;
	
	private final String crimeLabResultName;
	
	private final Date crimeLabRequestedDate;
	
	private final Date crimeLabResultDate;
	
	private final Long substanceTestSampleRequestId;
	
	private final Date substanceTestSampleRequestDate;
	
	private final String sampleRequestStatusReason;

	/**
	 * Instantiates a substance summary.
	 * 
	 * @param sampleId sample id
	 * @param collectionMethodName sample collection method collectionMethodName
	 * @param sampleDate sample collected date 
	 * @param substanceTestReason substance test reason collectionMethodName
	 * @param testDate substance test result date
	 * @param substanceTestId substance test id
	 * @param testResultName testResultName
	 * @param crimeLabRequestDate crime lab request date
	 * @param crimeLabResultDate crime lab result date
	 */
	public SubstanceTestSummary(final Long sampleId,
			final String collectionMethodName, final Date sampleDate, 
			final String substanceTestReason, final Date testDate, 
			final Long substanceTestId, final String testResultName,
			final String crimeLabResultName, final Date crimeLabRequestDate,
			final Date crimeLabResultDate) {
		this.sampleId = sampleId;
		this.collectionMethodName = collectionMethodName;
		this.sampleDate = sampleDate;
		this.substanceTestReason = substanceTestReason;
		this.testDate = testDate;
		this.substanceTestId = substanceTestId;
		this.testResultName = testResultName;
		this.crimeLabResultName = crimeLabResultName;
		this.crimeLabRequestedDate = crimeLabRequestDate;
		this.crimeLabResultDate = crimeLabResultDate;
		this.substanceTestSampleRequestId = null;
		this.substanceTestSampleRequestDate = null;
		this.sampleRequestStatusReason = null;
	}

	/**
	 * Instantiates a substance test summary with the substance test reason,
	 * request date, and request id.
	 * 
	 * @param substanceTestSampleRequestId substance test sample request id
	 * @param substanceTestSampleRequestDate substance test sample request date
	 * @param substanceTestReason substance test reason
	 */
	public SubstanceTestSummary(final Long substanceTestSampleRequestId,
			final Date substanceTestSampleRequestDate,
			final String substanceTestReason) {
		this.sampleId = null;
		this.collectionMethodName = null;
		this.sampleDate = null;
		this.substanceTestReason = substanceTestReason;
		this.testDate = null;
		this.substanceTestId = null;
		this.testResultName = null;
		this.crimeLabResultName = null;
		this.crimeLabRequestedDate = null;
		this.crimeLabResultDate = null;
		this.substanceTestSampleRequestId = substanceTestSampleRequestId;
		this.substanceTestSampleRequestDate = substanceTestSampleRequestDate;
		this.sampleRequestStatusReason = null;
	}
	
	/**
	 * Instantiates a substance test summary with the sample request reason,
	 * request date, and request ID.
	 * 
	 * @param sampleRequestStatusReason
	 * @param substanceTestSampleRequestId
	 * @param substanceTestSampleRequestDate
	 */
	public SubstanceTestSummary(
			final String sampleRequestStatusReason,
			final Long substanceTestSampleRequestId,
			final Date substanceTestSampleRequestDate) {
		this.sampleId = null;
		this.collectionMethodName = null;
		this.sampleDate = null;
		this.substanceTestReason = null;
		this.testDate = null;
		this.substanceTestId = null;
		this.testResultName = null;
		this.crimeLabResultName = null;
		this.crimeLabRequestedDate = null;
		this.crimeLabResultDate = null;
		this.substanceTestSampleRequestId = substanceTestSampleRequestId;
		this.substanceTestSampleRequestDate = substanceTestSampleRequestDate;
		this.sampleRequestStatusReason = sampleRequestStatusReason;
	}
	
	/**
	 * Returns the sample id.
	 * 
	 * @return the sample id
	 */
	public Long getSampleId() {
		return this.sampleId;
	}

	/**
	 * Return the collection method name.
	 * 
	 * @return collection method name
	 */
	public String getName() {
		return this.collectionMethodName;
	}

	/**
	 * Returns the sample date.
	 * 
	 * @return the sample date
	 */
	public Date getSampleDate() {
		return this.sampleDate;
	}

	/**
	 * Return the substance test reason.
	 * 
	 * @return substance test reason
	 */
	public String getSubstanceTestReason() {
		return this.substanceTestReason;
	}

	/**
	 * Return the substance test result date.
	 * 
	 * @return test date
	 */
	public Date getTestDate() {
		return this.testDate;
	}

	/**
	 * Return the substance test id.
	 * 
	 * @return substance test id
	 */
	public Long getSubstanceTestId() {
		return this.substanceTestId;
	}
	
	/**
	 * Returns the test result name.
	 * 
	 * @return test result name
	 */
	public String getTestResultName() {
		return this.testResultName;
	}
	
	/**
	 * Returns the crime lab result name.
	 * 
	 * @return crime lab result name
	 */
	public String getCrimeLabResultName() {
		return this.crimeLabResultName;
	}

	/**
	 * Returns the collection method name.
	 * 
	 * @return collection method name.
	 */
	public String getCollectionMethodName() {
		return this.collectionMethodName;
	}

	/**
	 * Returns the crime lab request date.
	 * 
	 * @return crime lab request date
	 */
	public Date getCrimeLabRequestedDate() {
		return this.crimeLabRequestedDate;
	}

	/**
	 * Returns the crime lab result date.
	 * 
	 * @return crime lab result date
	 */
	public Date getCrimeLabResultDate() {
		return this.crimeLabResultDate;
	}

	/**
	 * Returns the substance test sample request id.
	 * 
	 * @return substance test sample request id
	 */
	public Long getSubstanceTestSampleRequestId() {
		return this.substanceTestSampleRequestId;
	}

	/**
	 * Returns the substance test sample request date.
	 * 
	 * @return substance test sample request date
	 */
	public Date getSubstanceTestSampleRequestDate() {
		return this.substanceTestSampleRequestDate;
	}

	/**
	 * returns the sample request status reason.
	 * 
	 * @return sample request status reason
	 */
	public String getSampleRequestStatusReason() {
		return this.sampleRequestStatusReason;
	}

	/**
	 * Compares substance test summaries and orders by their substance test
	 * sample request date or sample date.
	 * 
	 * @param that the substance test summary to compare
	 * @return ordering
	 */
	@Override
	public int compareTo(SubstanceTestSummary that) {
		final Date thisDate = this.pickImportantDate(this);
		final Date thatDate = this.pickImportantDate(that);
		if (thisDate == null || thatDate == null) {
			throw new IllegalStateException(
					"Request or sample date required to compare summaries");
		}
		return thisDate.compareTo(thatDate);
	}
	
	/* Helper method. */
	
	private Date pickImportantDate(SubstanceTestSummary summary) {
		if (summary.getSubstanceTestSampleRequestDate() != null) {
			return summary.getSubstanceTestSampleRequestDate();
		} else {
			return summary.getSampleDate();
		}
	}
	
}