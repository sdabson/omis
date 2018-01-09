package omis.placementscreening.web.form;

import java.io.Serializable;

import omis.placementscreening.domain.ReferralScreening;
import omis.placementscreening.domain.ReferralScreeningCenter;

/** Pre release screening item. Comparable collection item.
 * @author Ryan Johns
 * @version 0.1.0 (Feb 24, 2015)
 * @since OMIS 3.0 */
public class PrereleaseScreeningItem 
	implements Comparable<PrereleaseScreeningItem>, Serializable {
	private static final long serialVersionUID = 1L;
	private ReferralScreening referralScreening;
	private ScreeningItemOperation itemOperation;
	private Integer order;
	private ReferralScreeningCenter referralScreeningCenter;
	
	/** Constructor. */
	public PrereleaseScreeningItem() { /* Do nothing. */ }
	
	/** Constructor. 
	 * @param order order.
	 * @param itemOperation item operation. */
	public PrereleaseScreeningItem(final Integer order, 
			final ScreeningItemOperation itemOperation) {
		this.order = order;
		this.itemOperation = itemOperation;
	}
	
	/** Sets referral screening.
	 * @param referralScreening referral screening. */
	public void setReferralScreening(
			final ReferralScreening referralScreening) {
		this.referralScreening = referralScreening;
	}
	
	/** Sets item operation. 
	 * @param itemOperation item operation. */
	public void setItemOperation(final ScreeningItemOperation itemOperation) {
		this.itemOperation = itemOperation;
	}
	
	/** Sets order.
	 * @param order order. */
	public void setOrder(final Integer order) { 
		this.order = order;
	}
	
	/** Sets referral screening center.
	 * @param referralScreeningCenter referral screening center. */
	public void setReferralScreeningCenter(
			final ReferralScreeningCenter referralScreeningCenter) {
		this.referralScreeningCenter = referralScreeningCenter;
	}
	
	/** Gets referral screening.
	 * @return referral screening. */
	public ReferralScreening getReferralScreening() { 
		return this.referralScreening; 
	}

	/** Gets order.
	 * @return order. */
	public Integer getOrder() { 
		return this.order; 
	}
	
	/** Gets referral screening center.
	 * @return referral screening center. */
	public ReferralScreeningCenter getReferralScreeningCenter() {
		return this.referralScreeningCenter; 
	}

	/** Gets item operation.
	 * @return item operation. */
	public ScreeningItemOperation getItemOperation() {
		return this.itemOperation; 
	}
	
	/** Comparison performed on order.
	 * @param that prerelease screening item. 
	 *  @return a negative integer, zero, or a positive integer as this object 
	 * is less than, equal to, or greater than the specified object.  */
	@Override
	public int compareTo(final PrereleaseScreeningItem that) {
		return this.getOrder() - that.getOrder();
	}

}
