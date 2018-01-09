package omis.courtcase.report;

import java.io.Serializable;
import java.util.Date;

/**
 * Charge summary.
 * 
 * @author Josh Divine
 * @version 0.1.1 (Aug 15, 2016)
 * @since OMIS 3.0
 */
public class ChargeSummary implements Serializable {
	
	private static final long serialVersionUID = 1;
	
	private final Long chargeId;
	
	private final String offenseViolationCode;
	
	private final String offenseName;
	
	private final String offenseUrl;
	
	private final Date date;
	
	private final Date fileDate;
	
	private final Long defendantId;
	
	private final String defendantLastName;
	
	private final String defendantFirstName;
	
	private final String defendantMiddleName;
	
	private final String defendantSuffix;
	
	private final Boolean defendantOffender;
	
	private final Integer defendantOffenderNumber;
	
	private final String courtName;
	
	private final String docketValue;
	
	private final Long courtCaseId;
	
	private final Integer counts;
	
	
	public ChargeSummary(final Long chargeId,
			final String offenseViolationCode,
			final String offenseName,
			final String offenseUrl,
			final Date date,
			final Date fileDate,
			final Long defendantId,
			final String defendantLastName,
			final String defendantFirstName,
			final String defendantMiddleName,
			final String defendantSuffix,
			final Boolean defendantOffender,
			final Integer defendantOffenderNumber,
			final String courtName,
			final String docketValue,
			final Long courtCaseId,
			final Integer counts) {
		this.chargeId = chargeId;
		this.offenseViolationCode = offenseViolationCode;
		this.offenseName = offenseName;
		this.date = date;
		this.fileDate = fileDate;
		this.offenseUrl = offenseUrl;
		this.defendantId = defendantId;
		this.defendantLastName = defendantLastName;
		this.defendantFirstName = defendantFirstName;
		this.defendantMiddleName = defendantMiddleName;
		this.defendantSuffix = defendantSuffix;
		this.defendantOffender = defendantOffender;
		this.defendantOffenderNumber = defendantOffenderNumber;
		this.courtName = courtName;
		this.docketValue = docketValue;
		this.courtCaseId = courtCaseId;
		this.counts = counts;
	}

	/**
	 * Returns the id of the charge.
	 * 
	 * @return charge ID
	 */
	public Long getChargeId() {
		return chargeId;
	}

	/**
	 * Returns the offense violation code of the charge.
	 * 
	 * @return offense violation code
	 */
	public String getOffenseViolationCode() {
		return offenseViolationCode;
	}

	/**
	 * Returns the offense name of the charge.
	 * 
	 * @return offense name
	 */
	public String getOffenseName() {
		return offenseName;
	}

	/**
	 * Returns the date of the charge.
	 * 
	 * @return date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Returns the file date of the charge.
	 * 
	 * @return file date
	 */
	public Date getFileDate() {
		return fileDate;
	}

	/**
	 * Returns the offense URL.
	 * 
	 * @return offense URL
	 */
	public String getOffenseUrl() {
		return offenseUrl;
	}

	/**
	 * Returns the defendant id.
	 * 
	 * @return defendant ID
	 */
	public Long getDefendantId() {
		return defendantId;
	}

	/**
	 * Returns the defendant last name.
	 * 
	 * @return defendant last name
	 */
	public String getDefendantLastName() {
		return defendantLastName;
	}

	/**
	 * Returns the defendant first name.
	 * 
	 * @return defendant first name
	 */
	public String getDefendantFirstName() {
		return defendantFirstName;
	}

	/**
	 * Returns the defendant middle name.
	 * 
	 * @return defendant middle name
	 */
	public String getDefendantMiddleName() {
		return defendantMiddleName;
	}

	/**
	 * Returns the defendant suffix.
	 * 
	 * @return defendant suffix
	 */
	public String getDefendantSuffix() {
		return defendantSuffix;
	}

	/**
	 * Returns whether the defendant is an offender.
	 * 
	 * @return defendant is offender
	 */
	public Boolean getDefendantOffender() {
		return defendantOffender;
	}

	/**
	 * Returns the defendant offender number.
	 * 
	 * @return defendant offender number
	 */
	public Integer getDefendantOffenderNumber() {
		return defendantOffenderNumber;
	}
	
	/**
	 * Returns the court name.
	 * 
	 * @return court name
	 */
	public String getCourtName() {
		return courtName;
	}

	/**
	 * Returns the docket value.
	 * 
	 * @return docket value
	 */
	public String getDocketValue() {
		return docketValue;
	}

	/**
	 * Returns the court case id.
	 * 
	 * @return court case ID
	 */
	public Long getCourtCaseId() {
		return courtCaseId;
	}

	/**
	 * Returns the number of counts.
	 * 
	 * @return number of counts
	 */
	public Integer getCounts() {
		return counts;
	}
}