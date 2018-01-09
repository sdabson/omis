package omis.hearing.report;

/**
 * Violation profile summary.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 19, 2017)
 * @since OMIS 3.0
 */
public class ViolationProfileSummary {
	
	private Long unresolvedCount;
	
	private Long resolvedCount;

	/** Instantiates an implementation of ViolationProfileSummary */
	public ViolationProfileSummary(final Long unresolvedCount, 
			final Long resolvedCount) {
		this.unresolvedCount = unresolvedCount;
		this.resolvedCount = resolvedCount;
	}

	/**
	 * Returns the unresolved count.
	 *
	 * @return the unresolvedCount
	 */
	public Long getUnresolvedCount() {
		return this.unresolvedCount;
	}

	/**
	 * Returns the resolved count.
	 *
	 * @return the resolvedCount
	 */
	public Long getResolvedCount() {
		return this.resolvedCount;
	}
}