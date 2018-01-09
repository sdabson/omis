package omis.placementscreening.web.form;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import omis.facility.domain.Facility;
import omis.placementscreening.domain.ProgramCategory;
import omis.placementscreening.domain.ReferralScreeningCenter;
import omis.user.domain.UserAccount;

/** Program referral form.
 * @author Ryan Johns
 * @version 0.1.0 (Feb 24, 2015)
 * @since OMIS 3.0 */
public class PlacementReferralForm implements Serializable {
	private static final long serialVersionUID = 1L;
	private Facility referringFacility;
	private ProgramCategory programCategory;
	private ReferralScreeningCenter referralScreeningCenter;
	private Date referralDate;
	private UserAccount referringUser;
	private List<TreatmentScreeningItem> treatmentScreeningItems;
	private List<PrereleaseScreeningItem> prereleaseScreeningItems;
	
	/** Constructor. */
	public PlacementReferralForm() { /* Do nothing. */ }
	
	/** Sets referring facility.
	 * @param referringFacility referring facility. */
	public void setReferringFacility(final Facility referringFacility) {
		this.referringFacility = referringFacility;
	}
	
	/** Sets referring user.
	 * @param referringUser referring user.. */
	public void setReferringUser(final UserAccount referringUser) {
		this.referringUser = referringUser;
	}
	
	/** Sets program category. 
	 * @param programCategory program category. */
	public void setProgramCategory(final ProgramCategory programCategory) {
		this.programCategory = programCategory;
	}
	
	/** Sets Referral screening center.
	 * @param referralScreeningCenter referral screening center.. */
	public void setReferralScreeningCenter(
			final ReferralScreeningCenter referralScreeningCenter) {
		this.referralScreeningCenter = referralScreeningCenter; 
	}
	
	/** Sets referral date.
	 * @param referralDate referral date. */
	public void setReferralDate(final Date referralDate) {
		this.referralDate = referralDate;
	}
	
	/** Sets treatment screening items.
	 * @param treatmentScreeningItems treatment screening items. */
	public void setTreatmentScreeningItems(
			final List<TreatmentScreeningItem> treatmentScreeningItems) {
		this.treatmentScreeningItems = treatmentScreeningItems;
	}
	
	/** Sets prerelease screening items.
	 * @param prereleaseScreeningItems prerelease screening items. */
	public void setPrereleaseScreeningItems(
			final List<PrereleaseScreeningItem> prereleaseScreeningItems) {
		this.prereleaseScreeningItems = prereleaseScreeningItems;
	}
	
	/** Gets Referring facility.
	 * @return referring facility. */
	public Facility getReferringFacility() { 
		return this.referringFacility; 
	}
	
	/** Gets referring user.
	 * @return referring user. */
	public UserAccount getReferringUser() { 
		return this.referringUser; 
	}
	
	/** Gets program category.
	 * @return program category. */
	public ProgramCategory getProgramCategory() { 
		return this.programCategory; 
	}
	
	/** Gets referral screening center.
	 * @return referral screening center.. */
	public ReferralScreeningCenter getReferralScreeningCenter() { 
		return this.referralScreeningCenter;
	}
	
	/** Gets referral date.
	 * @return referralDate referral date. */
	public Date getReferralDate() { 
		return this.referralDate; 
	}
	
	/** Gets treatment screening items.
	 * @return treatment screening items. */
	public List<TreatmentScreeningItem> getTreatmentScreeningItems() {
		return this.treatmentScreeningItems;
	}
	
	/** Gets prerelease screening items. 
	 * @return prerelease screening items. */
	public List<PrereleaseScreeningItem> getPrereleaseScreeningItems() {
		return this.prereleaseScreeningItems; 
	}
	
	/** Gets screening items.
	 * @return screening items. */
	public String getScreeningItems() {
		return null;
	}
}
