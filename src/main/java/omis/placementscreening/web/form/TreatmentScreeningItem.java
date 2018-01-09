package omis.placementscreening.web.form;

import java.io.Serializable;

import omis.placementscreening.domain.ReferralScreening;
import omis.placementscreening.domain.ReferralScreeningCenter;

/** Treatment screening item. Comparable collection item.
 * @author Ryan Johns
 * @version 0.1.0 (Feb 24, 2015)
 * @since OMIS 3.0 */
public class TreatmentScreeningItem 
	implements Comparable<TreatmentScreeningItem>, Serializable {
	private static final long serialVersionUID = 1L;
	private ReferralScreening referralScreening;
	private ScreeningItemOperation itemOperation;
	private Integer order;
	private ReferralScreeningCenter referralScreeningCenter;
	
	/** Constructor. */
	public TreatmentScreeningItem() { /* Do nothing. */ }
	
	/** Constructor.
	 * @param order order.
	 * @param itemOperation item operation. */
	public TreatmentScreeningItem(final Integer order,
			final ScreeningItemOperation itemOperation) { 
		this.order = order; 
		this.itemOperation = itemOperation;
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
	public void setReferralScreeningCenter(final ReferralScreeningCenter 
			referralScreeningCenter) {
		this.referralScreeningCenter = referralScreeningCenter;
	}
	
	/** Sets referral screening.
	 * @param referralScreening referral screening. */
	public void setReferralScreening(
			final ReferralScreening referralScreening) {
		this.referralScreening = referralScreening;
	}
	
	/** Gets item operation.
	 * @return item operation. */
	public ScreeningItemOperation getScreeningItemOperation() {
		return this.itemOperation; 
	}
	
	/** Gets order.
	 * @return order order. */
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
	
	/** Gets referral screening. 
	 * @return referral screening. */
	public ReferralScreening getReferralScreening() { 
		return this.referralScreening; 
	}
	
	/** Compare to other treatment screening items via order property.
	 * @param that other treatment screening item.
	 * @return a negative integer, zero, or a positive integer as this object 
	 * is less than, equal to, or greater than the specified object.  */
	@Override
	public int compareTo(final TreatmentScreeningItem that) {
		return this.getOrder() - that.getOrder();
	}
	
}
