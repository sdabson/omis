package omis.placementscreening.web.controller;

import java.util.List;

import omis.demographics.domain.Sex;
import omis.exception.DuplicateEntityFoundException;
import omis.facility.service.FacilityService;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.placementscreening.domain.PlacementReferral;
import omis.placementscreening.domain.ProgramCategory;
import omis.placementscreening.domain.ReferralScreeningCenter;
import omis.placementscreening.domain.ScreeningLevelCategory;
import omis.placementscreening.exception.ReferralDateCategoryConflictException;
import omis.placementscreening.service.PlacementReferralService;
import omis.placementscreening.web.form.PlacementReferralForm;
import omis.placementscreening.web.form.PrereleaseScreeningItem;
import omis.placementscreening.web.form.ScreeningItemOperation;
import omis.placementscreening.web.form.TreatmentScreeningItem;
import omis.user.domain.UserAccount;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;

/** Delegate for program referral controller related operations.
 * @author Ryan Johns
 * @version 0.1.0 (Feb 05, 2015)
 * @since OMIS 3.0 */
public class PlacementReferralControllerDelegate {
	private static final String FACILITY_LIST_MODEL_KEY = "facilities";
	private static final String FORM_MODEL_KEY = "placementReferralForm";
	private static final String INDEX_MODEL_KEY = "index";
	private static final String 
		INSTITUTIONAL_SCREENING_CATEGORY_LIST_MODEL_KEY =
			"institutionalScreeningCategories";
	private static final String PROGRAM_CATEGORIES_MODEL_KEY = "categories";
	private static final String PRERELEASE_SCREENING_CENTERS_MODEL_KEY = 
			"prereleaseScreeningCenters";
	private static final String PRERELEASE_SCREENING_ITEM_MODEL_KEY = 
			"preReleaseScreeningItem";
	private static final String TREATMENT_SCREENING_CENTERS_MODEL_KEY =
			"treatmentScreeningCenters";
	private static final String TREATMENT_SCREENING_ITEM_MODEL_KEY =
			"treatmentScreeningItem";
	private static final String
		SCREENING_ITEM_LIST_MODEL_KEY =
			"screeningItems";
	private static final String PROGRAM_CATEGORY_LIST_MODEL_KEY = 
			"programCategories";
	private static final String REFERRING_FACILITY_LIST_MODEL_KEY = 
			"referringFacilities";
	private FacilityService facilityService;
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	private PlacementReferralService placementReferralService;
	
	/** Constructor.
	 * @param facilityService facility service. 
	 * @param offenderSummaryModelDelegate offender summary model delegate.
	 * @param placementReferralService placement referral service. */
	public PlacementReferralControllerDelegate(
			final FacilityService facilityService,
			final OffenderSummaryModelDelegate offenderSummaryModelDelegate,
			final PlacementReferralService placementReferralService) {
		this.facilityService = facilityService;
		this.offenderSummaryModelDelegate = offenderSummaryModelDelegate;
		this.placementReferralService = placementReferralService;
	}

	/** Prepares program referral edit map.
	 * @param offender offender being referred.
	 * @param placementReferralForm form.
	 * @return map map. */
	protected ModelMap prepareEditMap(final Offender offender, 
			final PlacementReferralForm placementReferralForm) {
		ModelMap map = new ModelMap();
		if (placementReferralForm.getReferringUser() == null) {
			placementReferralForm.setReferringUser(this.getUserAccount());
		}
		
		map.put(FORM_MODEL_KEY, placementReferralForm);
		map.put(INSTITUTIONAL_SCREENING_CATEGORY_LIST_MODEL_KEY, 
				ScreeningLevelCategory.values());
		map.put(PROGRAM_CATEGORY_LIST_MODEL_KEY, ProgramCategory.values());
		map.put(REFERRING_FACILITY_LIST_MODEL_KEY, 
				this.facilityService.findAll());
		
		map.put(PRERELEASE_SCREENING_CENTERS_MODEL_KEY, 
				this.placementReferralService
				.findAllReferralScreeningCentersByProgram(
						ProgramCategory.PRERELEASE, 
						offender.getIdentity().getSex()));
		this.offenderSummaryModelDelegate.add(map, offender);
		
		return map;
	}
	
	/** Prepares facility options.
	 * @param programCategory program category.
	 * @param sex sex. 
	 * @return map map. */
	protected ModelMap prepareFacilityOptionsMap(
			final ProgramCategory programCategory, final Sex sex) {
		ModelMap map = new ModelMap();
		map.put(FACILITY_LIST_MODEL_KEY, this.placementReferralService
				.findAllReferralScreeningCentersByProgram(programCategory, 
						sex));
		return map;
	}
	
	/** prepares screening queue.
	 * @param referralScreeningCenter referral screening center.
	 * @param sex sex. 
	 * @return map map. */
	protected ModelMap prepareScreeningQueue(
			final ReferralScreeningCenter referralScreeningCenter,
			final Sex sex) {
		ModelMap map = new ModelMap();
		List<ReferralScreeningCenter> rscs = this.placementReferralService
				.queueScreeningCenters(referralScreeningCenter, sex);
		
		if (referralScreeningCenter.getProgramCategory() 
				== ProgramCategory.PRERELEASE) {
			PrereleaseScreeningItem[] list = 
					new PrereleaseScreeningItem[rscs.size()]; 
				
			for (int x = 0; x < rscs.size(); x++) {
				ReferralScreeningCenter rsf =  rscs.get(x);
				
				PrereleaseScreeningItem temp = new PrereleaseScreeningItem();
				temp.setReferralScreeningCenter(rsf);
				temp.setItemOperation(ScreeningItemOperation.CREATE);
				temp.setOrder(x + 1);
				
				list[x] = temp;
			}	
			map.put(PRERELEASE_SCREENING_CENTERS_MODEL_KEY, 
					this.placementReferralService
					.findAllReferralScreeningCentersByProgram(
							referralScreeningCenter.getProgramCategory(), sex));
			
			map.put(SCREENING_ITEM_LIST_MODEL_KEY, list);
		} else {
			TreatmentScreeningItem[] list = 
					new TreatmentScreeningItem[rscs.size()];
			for (int x = 0; x < rscs.size(); x++) {
				ReferralScreeningCenter rsc = rscs.get(x);
				TreatmentScreeningItem temp = new TreatmentScreeningItem();
				temp.setReferralScreeningCenter(rsc);
				temp.setItemOperation(ScreeningItemOperation.CREATE);
				temp.setOrder(x + 1);
				
				list[x] = temp;
			}
			map.put(TREATMENT_SCREENING_CENTERS_MODEL_KEY, 
					this.placementReferralService
					.findAllReferralScreeningCentersByProgram(
							referralScreeningCenter.getProgramCategory(), 
							sex));
			map.put(SCREENING_ITEM_LIST_MODEL_KEY, list);
		}
		return map;
	}
	
	/** Prepares prerelease screening table map.
	 * @param index index.
	 * @param sex sex.
	 * @return map map. */
	protected ModelMap preparePrereleaseScreeningItemMap(final Integer index, 
			final Sex sex) {
		ModelMap map = new ModelMap();
		map.put(PRERELEASE_SCREENING_CENTERS_MODEL_KEY, 
				this.placementReferralService
				.findAllReferralScreeningCentersByProgram(
						ProgramCategory.PRERELEASE, sex));
		map.put(INDEX_MODEL_KEY, index);
		map.put(PRERELEASE_SCREENING_ITEM_MODEL_KEY, 
				new PrereleaseScreeningItem(index + 1,
						ScreeningItemOperation.CREATE));
		return map;
	}
	
	/** Prepares treatment screening table map.
	 * @param index index.
	 * @param programCategory program category.
	 * @param sex sex.
	 * @return map map. */
	protected ModelMap prepareTreatmentScreeningItemMap(final Integer index,
			final ProgramCategory programCategory, final Sex sex) {
		ModelMap map = new ModelMap();
		
		List<ReferralScreeningCenter> rscs = 
				this.placementReferralService
				.findAllReferralScreeningCentersByProgram(
				programCategory, sex);
		
		TreatmentScreeningItem[] list = 
				new TreatmentScreeningItem[rscs.size()];
		for (int x = 0; x < rscs.size(); x++) {
			ReferralScreeningCenter rsc = rscs.get(x);
			TreatmentScreeningItem temp = new TreatmentScreeningItem();
			temp.setReferralScreeningCenter(rsc);
			temp.setItemOperation(ScreeningItemOperation.CREATE);
			temp.setOrder(index + x + 1);
			
			list[x] = temp;
		}
		
		map.put(TREATMENT_SCREENING_CENTERS_MODEL_KEY, 
				this.placementReferralService
				.findAllReferralScreeningCentersByProgram(
						programCategory, 
						sex));
		map.put(SCREENING_ITEM_LIST_MODEL_KEY, list);
		map.put(INDEX_MODEL_KEY, index);
		map.put(TREATMENT_SCREENING_ITEM_MODEL_KEY, 
				new TreatmentScreeningItem(index + 1, 
						ScreeningItemOperation.CREATE));
		return map;
	}
	
	/** Prepares screenings. 
	 * @param offender offender.
	 * @param placementReferralForm placement referral form.
	 * @return program referral. 
	 * @throws DuplicateEntityFoundException duplicate entity found. 
	 * @throws ReferralDateCategoryConflictException screening done before
	 * minimum allowable re screening. */
	protected PlacementReferral setUpPlacementScreening(
			final Offender offender, 
			final PlacementReferralForm placementReferralForm) 
		throws DuplicateEntityFoundException, 
		ReferralDateCategoryConflictException {
		
		PlacementReferral placementReferral = this.placementReferralService
				.referToPlacement(offender,
				placementReferralForm.getReferralDate(),
				placementReferralForm.getReferralScreeningCenter()
				.getFacility(),
				placementReferralForm.getReferringFacility(),
				placementReferralForm.getReferringUser().getUser(),
				placementReferralForm.getProgramCategory());
	
		this.preparePrereleaseScreenings(placementReferral,
				placementReferralForm.getPrereleaseScreeningItems());
		this.prepareTreatmentScreenings(placementReferral,
				placementReferralForm.getTreatmentScreeningItems());
		
		return placementReferral;
	}
	
	/** Prepares treatment list action menu.
	 * @return program map. */
	public ModelMap prepareTreatmentActionMenu() {
		ModelMap map = new ModelMap();
		map.put(PROGRAM_CATEGORIES_MODEL_KEY, 
				ProgramCategory.treatmentValues());
		
		return map;
	}
	
	private void preparePrereleaseScreenings(
			final PlacementReferral placementReferral,
			final List<PrereleaseScreeningItem> prereleaseScreeningItems) 
		throws DuplicateEntityFoundException {
		if (prereleaseScreeningItems != null) {
			for (PrereleaseScreeningItem item: prereleaseScreeningItems) {
				if (item.getItemOperation() 
						== ScreeningItemOperation.CREATE) {
					this.placementReferralService.queueLocalScreening(
							item.getOrder(),
							placementReferral, 
							item.getReferralScreeningCenter());
				} else if (item.getItemOperation()
						== ScreeningItemOperation.UPDATE) {
					this.placementReferralService.updateOrderOfScreening(
							item.getReferralScreening(), item.getOrder());
				} else if (item.getItemOperation()
						== ScreeningItemOperation.REMOVE) {
					if (item.getReferralScreening() != null) {
						this.placementReferralService.dequeueLocalScreening(
								item.getReferralScreening());
					}
				}
			}
		}
	}
	
	private void prepareTreatmentScreenings(
			final PlacementReferral placementReferral,
			final List<TreatmentScreeningItem> treatmentScreeningItems)
		throws DuplicateEntityFoundException {
		if (treatmentScreeningItems != null) {
			for (TreatmentScreeningItem item: treatmentScreeningItems) {
				if (item.getScreeningItemOperation()
						== ScreeningItemOperation.CREATE) {
					this.placementReferralService.queueLocalScreening(
							item.getOrder(),
							placementReferral,
							item.getReferralScreeningCenter());
				}
			}
		}
	}
					
	
	/** Gets the current logged in user account.
	 * @return userAccount. */
	private UserAccount getUserAccount() {
		return this.placementReferralService.findUserByUsername(
				SecurityContextHolder.getContext().getAuthentication()
				.getName());
	}
}
