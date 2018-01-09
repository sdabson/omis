package omis.financial.report;

/** Summary for financial profile item.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 18, 2017)
 * @since OMIS 3.0 */
public class FinancialProfileItemSummary {
	private final Long assetCount;
	private final Long liabilityCount;
	
	/** Constructor.
	 * @param assetCount - asset count.
	 * @param liabilityCount - liability count. */
	public FinancialProfileItemSummary(final Long assetCount, 
			final Long liabilityCount) {
		this.assetCount = assetCount;
		this.liabilityCount = liabilityCount;
	}
	
	/** Gets asset count.
	 * @return asset count. */
	public Long getAssetCount() {
		return this.assetCount;
	}
	
	/** Gets liability count.
	 * @return liability count. */
	public Long getLiabilityCount() {
		return this.liabilityCount;
	}
}
