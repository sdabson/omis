package omis.trackeddocument.report;

import java.io.Serializable;

/**
 * Docket document receival summary.
 * 
 * @author Yidong Li
 * @version 0.1.0 (Dec 12, 2017)
 * @since OMIS 3.0
 */
public class DocketDocumentReceivalSummary implements Serializable {

	private static final long serialVersionUID = 1L;

	private final Long docketId;
	
	private final String docketValue;
	
	private final String courtName;
	
	private final Long count;

	/**
	 * Instantiates a docket document receival summary with the
	 * specified properties.
	 * 
	 * @param docketId docket Id
	 * @param docketValue docket value
	 * @param count count
	 * @param courtName court name
	 */
	public DocketDocumentReceivalSummary(final Long docketId, 
			final String docketValue, 
			final String courtName, 
			final Long count) {
		this.docketValue = docketValue;
		this.docketId = docketId;
		this.count = count;
		this.courtName = courtName;
	}

	/**
	 * Returns the docket id.
	 * 
	 * @return docket id
	 */
	public Long getDocketId() {
		return this.docketId;
	}

	/**
	 * Returns the court name.
	 * 
	 * @return court name
	 */
	public String getCourtName() {
		return this.courtName;
	}
	
	/**
	 * Returns docket value.
	 * 
	 * @return docket value
	 */
	public String getDocketValue() {
		return this.docketValue;
	}

	/**
	 * Returns the count of associated receival.
	 * 
	 * @return count
	 */
	public Long getCount() {
		return this.count;
	}
}