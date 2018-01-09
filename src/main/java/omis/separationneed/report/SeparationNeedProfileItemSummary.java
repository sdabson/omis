package omis.separationneed.report;

/** Report object for separation needs profile item.
 * @author Ryan Johns
 * @version 0.1.0 (Mar 31, 2017)
 * @since OMIS 3.0 */
public class SeparationNeedProfileItemSummary {
 private Long activeCount;
 private Long totalCount;
 
 /** Constructor.
  * @param activeCount - active count.
  * @param totalCount - total count. */
 public SeparationNeedProfileItemSummary(final Long activeCount, final Long totalCount) {
	 this.activeCount = activeCount;
	 this.totalCount = totalCount;
 }
 
 /** Gets active count.
  * @return active count. */
 public Long getActiveCount() {
	 return this.activeCount;
 }
 
 /** Gets total count.
  * @return total count. */
 public Long getTotalCount() {
	 return this.totalCount;
 }
}
