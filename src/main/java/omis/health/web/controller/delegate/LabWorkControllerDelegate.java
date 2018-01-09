package omis.health.web.controller.delegate;

import java.util.Date;
import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.facility.domain.Facility;
import omis.health.domain.InternalReferral;
import omis.health.domain.LabWork;
import omis.health.domain.OffenderAppointmentAssociation;
import omis.health.domain.ProviderAssignment;
import omis.health.domain.component.LabWorkOrder;
import omis.health.domain.component.LabWorkResults;
import omis.health.domain.component.LabWorkSampleRestrictions;
import omis.health.service.InternalReferralScheduler;
import omis.health.service.LabWorkManager;
import omis.health.web.form.LabWorkAppointmentItem;
import omis.location.domain.Location;

import org.springframework.ui.ModelMap;

/**
 * Lab work controller delegate.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 11, 2014)
 * @since OMIS 3.0
 */
public class LabWorkControllerDelegate {

	/* View names. */
	
	private static final String LAB_WORK_ITEM_VIEW =
			"/health/labWork/includes/labWorkAppointmentItem";
	
	private static final String ORDERED_BY_OPTIONS_VIEW_NAME =
			"/health/labWork/includes/orderedByOptions";
	
	private static final String DEFAULT_ORDER_BY_OPTIONS_VIEW_NAME =
			"/health/labWork/includes/defaultOrderedByOptions";
	
	/* Model keys. */
	
	private static final String LABS_MODEL_KEY = "labs";
	
	private static final String LAB_WORK_CATEGORIES_MODEL_KEY
		= "labWorkCategories";
	
	private static final String LAB_WORK_INDEX_MODEL_KEY = "labWorkIndex";
	
	private static final String CURRENT_LAB_WORK_INDEX_MODEL_KEY
		= "currentLabWorkIndex";
	
	private static final String ORDERED_BY_PROVIDER_MODEL_KEY = 
			"orderedByProvider";
	
	private static final String PROVIDERS_MODEL_KEY =
			"providers";
	
	/* Services. */
	
	private LabWorkManager labWorkManager;
	
	private InternalReferralScheduler internalReferralScheduler;
	
	/**
	 * Instantiates a lab work controller delegate with the specified services.
	 * 
	 * @param labWorkManager lab work manager
	 * @param internalReferralScheduler internal referral scheduler
	 */
	public LabWorkControllerDelegate(final LabWorkManager labWorkManager,
			final InternalReferralScheduler internalReferralScheduler) {
		this.labWorkManager = labWorkManager;
		this.internalReferralScheduler = internalReferralScheduler;
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
	 * Returns the ordered by options view name.
	 * 
	 * @return ordered by options view name
	 */
	public String getOrderedByOptionsViewName() {
		return ORDERED_BY_OPTIONS_VIEW_NAME;
	}
	
	/**
	 * Returns the default ordered by options view name.
	 * 
	 * @return default ordered by options view name
	 */
	public String getDefaultOrderedByOptionsViewName() {
		return DEFAULT_ORDER_BY_OPTIONS_VIEW_NAME;
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
	 * Returns the ordered by provider model key.
	 * 
	 * @return ordered by provider model key
	 */
	public String getOrderedByProviderModelKey() {
		return ORDERED_BY_PROVIDER_MODEL_KEY;
	}
	
	/**
	 * Returns the providers model key.
	 * 
	 * @return providers model key
	 */
	public String getProvidersModelKey() {
		return PROVIDERS_MODEL_KEY;
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
				.labWorkManager.findLabs());
		map.addAttribute(LAB_WORK_CATEGORIES_MODEL_KEY,
				this.labWorkManager.findLabWorkCategories());
		return map;
	}
	
	/**
	 * Returns all lab works associated by a lab work requirement with the
	 * specified internal referral.
	 * 
	 * @param internalReferral internal referral
	 * @return list of required lab works
	 */
	public List<LabWork> findInternalReferralLabWorks(
			final InternalReferral internalReferral) {
		return this.internalReferralScheduler.findLabWorks(
						internalReferral);
	};
	
	/**
	 * Updates required lab work associated with a referral that is being
	 * assessed.
	 * 
	 * @param items lab work appointment items
	 * @param offenderAppointmentAssociation offenderAppointmentAssociation
	 * @throws DuplicateEntityFoundException duplicate entity found exception
	 */
	public void updateRequiredLabWork(final List<LabWorkAppointmentItem> items,
			final OffenderAppointmentAssociation 
			offenderAppointmentAssociation) 
		throws DuplicateEntityFoundException {
		for (LabWorkAppointmentItem item : items) {
			if (item.getProcess() != null && item.getProcess()) {
				if (item.getLabWork() != null) {
					this.labWorkManager.updateLabWork(item.getLabWork(), 
							item.getLabWorkCategory(), item.getSampleLab(), 
							item.getDate(), item.getSampleTaken(), 
							item.getSampleNotes(), 
							new LabWorkResults(item.getResultsLab(), 
									item.getResultsDate(), 
									item.getResultsNotes()), 
									new LabWorkOrder(item.getByProvider(), 
											item.getOrderDate()), 
									new LabWorkSampleRestrictions(
											item.getNothingPerOral(), 
											item.getNoLeaky(),
											item.getNoMeds()),
											item.getSchedulingNotes());
				} else {
					this.labWorkManager.scheduleLabWork(
							offenderAppointmentAssociation.getAppointment()
							.getFacility(), offenderAppointmentAssociation
							.getOffender(), item.getLabWorkCategory(), 
							item.getSampleLab(), item.getDate(), 
							item.getSampleTaken(), item.getSampleNotes(), 
							new LabWorkResults(
									item.getResultsLab(), 
									item.getResultsDate(), 
									item.getResultsNotes()), 
							new LabWorkOrder(item.getByProvider(), 
									item.getOrderDate()), 
							new LabWorkSampleRestrictions(
									item.getNothingPerOral(), 
									item.getNoLeaky(),
									item.getNoMeds()), 
									item.getSchedulingNotes());
				}
			} else {
				if (item.getLabWork() != null) {
					//FIXME: proper removal of a lab work associated with
					//a referral required
//					this.labWorkManager.removeLabWork(item.getLabWork(), 
//							offenderAppointmentAssociation);
				}
			}
		}
	};
	
	/**
	 * Schedules required lab work with information from the list of lab
	 * work appointment items with the specified offender appointment
	 * association.
	 *  
	 * @param items list of lab work appointment items
	 * @param offenderAppointmentAssociation offender appointment association
	 * @throws DuplicateEntityFoundException duplicate entity found exception
	 */
	public void scheduleRequiredLabWork(
			final List<LabWorkAppointmentItem> items,
			final OffenderAppointmentAssociation 
			offenderAppointmentAssociation) 
		throws DuplicateEntityFoundException {
		for (LabWorkAppointmentItem item : items) {
			this.labWorkManager.scheduleLabWork(
					offenderAppointmentAssociation.getAppointment()
					.getFacility(), offenderAppointmentAssociation
					.getOffender(), item.getLabWorkCategory(), 
					item.getSampleLab(), item.getDate(), item.getSampleTaken(),
					item.getSampleNotes(), new LabWorkResults(
							item.getResultsLab(), 
							item.getResultsDate(), 
							item.getResultsNotes()), 
					new LabWorkOrder(item.getByProvider(), 
							item.getOrderDate()), 
					new LabWorkSampleRestrictions(
							item.getNothingPerOral(), 
							item.getNoLeaky(),
							item.getNoMeds()), 
							item.getSchedulingNotes());
		}
	}
	
	/**
	 * Returns a list of provider assignment with the specified facility and
	 * whose date range includes the specified date.
	 * 
	 * @param facility facility
	 * @param date date
	 * @return list of provider assignments
	 */
	public List<ProviderAssignment> findProviders(final Facility facility, 
			final Date date) {
		return this.labWorkManager.findProviders(facility, date);
	}
	
	/**
	 * Returns the facility at the specified location.
	 * 
	 * @param location location
	 * @return facility
	 */
	public Facility findFacility(final Location location) {
		return this.labWorkManager.findFacility(location);
	}
}