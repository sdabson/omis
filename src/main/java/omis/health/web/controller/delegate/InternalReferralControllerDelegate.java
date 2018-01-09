package omis.health.web.controller.delegate;

import omis.health.service.InternalReferralScheduler;

import org.springframework.ui.ModelMap;


/** Internal Referral delegate.
 * @author Ryan Johns
 * @author Joel Norris
 * @version 0.1.0 (May 20, 2014)
 * @since OMIS 3.0 */
public class InternalReferralControllerDelegate {
	
	/* View names. */
	
	private static final String SCHEDULE_INTERNAL_REFERRAL_FORM_VIEW_NAME =
			"/health/referral/internal/schedule/edit";
	
	private static final String LAB_WORK_ITEM_VIEW =
			"/health/labWork/includes/labWorkAppointmentSchedulerTableRow";

	/* Model keys. */
	
	private static final String SCHEDULE_INTERNAL_REFERRAL_FORM_MODEL_KEY 
		= "scheduleForm";
	
	private static final String LABS_MODEL_KEY = "labs";
	
	private static final String LAB_WORK_CATEGORIES_MODEL_KEY
		= "labWorkCategories";

	private static final String LAB_WORK_INDEX_MODEL_KEY = "labWorkIndex";
	
	private static final String CURRENT_LAB_WORK_INDEX_MODEL_KEY
		= "currentLabWorkIndex";
	
	/* Services. */
	
	private InternalReferralScheduler internalReferralScheduler;
	
	/**
	 * Instantiates an internal referral controller delegate with the specified
	 * service.
	 * 
	 * @param internalReferralScheduler internal referral scheduler
	 */
	public InternalReferralControllerDelegate(
			final InternalReferralScheduler internalReferralScheduler) {
		this.internalReferralScheduler = internalReferralScheduler;
	}
	
	/** Gets internal referral schedule form view.
	 * @return scheduleInternalReferralFormViewName. */
	public String getScheduleInternalReferralFormViewName() {
		return SCHEDULE_INTERNAL_REFERRAL_FORM_VIEW_NAME;
	}

	/** Gets internal referral schedule form model key.
	 * @return scheduleInternalReferralFormModelKey. */
	public String getScheduleInternalReferralFormModelKey() {
		return SCHEDULE_INTERNAL_REFERRAL_FORM_MODEL_KEY;
	}
	
	/**
	 * Returns the current lab work index model key.
	 * 
	 * @return current lab work index model key
	 */
	public String getCurrentLabWorkIndexModelKey() {
		return CURRENT_LAB_WORK_INDEX_MODEL_KEY;
	}
	
	/**
	 * Returns the labs model key.
	 * 
	 * @return labs model key
	 */
	public String getLabsModelKey() {
		return LABS_MODEL_KEY;
	}
	
	/**
	 * Returns the lab work categories model key.
	 * 
	 * @return lab work categories model key
	 */
	public String getLabWorkCategoriesModelKey() {
		return LAB_WORK_CATEGORIES_MODEL_KEY;
	}
	
	/**
	 * Returns the lab work item view name.
	 * 
	 * @return lab work item view name
	 */
	public String getLabWorkItemViewName() {
		return LAB_WORK_ITEM_VIEW;
	}
	
	/**
	 * Returns the lab work index model key.
	 * 
	 * @return lab work index model key
	 */
	public String getLabWorkIndexModelKey() {
		return LAB_WORK_INDEX_MODEL_KEY;
	}
	
	/**
	 * Prepares additional information needed to display a lab work item
	 * row.
	 * 
	 * @param map model map
	 * @return model map with needed closed text options for lab work row 
	 * items
	 */
	public ModelMap prepareLabWorkItemRow(final ModelMap map) {
		map.addAttribute(LABS_MODEL_KEY, this
				.internalReferralScheduler.findLabs());
		map.addAttribute(LAB_WORK_CATEGORIES_MODEL_KEY,
				this.internalReferralScheduler.findLabWorkCategories());
		return map;
	}
}