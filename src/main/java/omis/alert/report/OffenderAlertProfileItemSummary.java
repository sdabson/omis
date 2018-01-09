package omis.alert.report;

/** Summary for offender alert profile item.
 * @author Ryan Johns
 * @version 0.1.0 (Jan 17, 2016)
 * @since OMIS 3.0 */
public class OffenderAlertProfileItemSummary {
	private final Long activeAlertCount;
	private final Long inactiveAlertCount;
	
	/** Constructor. 
	 * @param activeAlertCount - active alert count.
	 * @param inactiveAlertCount - inactive alert count. */
	public OffenderAlertProfileItemSummary(final Long activeAlertCount, final Long inactiveAlertCount) {
		this.activeAlertCount = activeAlertCount;
		this.inactiveAlertCount = inactiveAlertCount;
	}
	
	/** Gets active alert count.
	 * @return active alert count. */
	public Long getActiveAlertCount() {
		return this.activeAlertCount;
	}
	
	/** Gets inactive alert count.
	 * @return inactive alert count. */
	public Long getInactiveAlertCount() {
		return this.inactiveAlertCount;
	}
}
