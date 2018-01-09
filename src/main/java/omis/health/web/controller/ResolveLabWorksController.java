package omis.health.web.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.exception.BusinessException;
import omis.facility.domain.Facility;
import omis.facility.service.FacilityService;
import omis.health.domain.Lab;
import omis.health.domain.LabWork;
import omis.health.domain.component.LabWorkResults;
import omis.health.service.LabWorkResultsService;
import omis.health.validator.ResolveLabWorkFormValidator;
import omis.health.web.controller.delegate.HealthControllerDelegate;
import omis.health.web.form.ReferralType;
import omis.health.web.form.ResolveLabWorkForm;
import omis.health.web.form.ResolveLabWorkItem;
import omis.health.web.form.ResolveLabWorkItemOperation;
import omis.health.web.form.SearchLabWorkForm;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;

/**
 * Controller for resolve lab work .
 * 
 * @author Yidong Li
 * @version 0.1.0 (Nov. 21, 2014)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/health/labWork")
@PreAuthorize("(hasRole('USER') and hasRole('HEALTH_MODULE')) or "
		+ "hasRole('ADMIN') or hasRole('HEALTH_ADMIN')")
public class ResolveLabWorksController {
	
	/* View names. */
	private static final String EDIT_VIEW_NAME
		= "/health/labWork/resolve/edit";

	/* Model keys. */
	private static final String OFFENDER_MODEL_KEY
		= "offender";
	
	private static final String RESULTS_LAB_MODEL_KEY
		= "resultsLab";
	
	private static final String RESOLVE_LAB_WORK_FORM_MODEL_KEY
		="resolveLabWorkForm";
	
	private static final String SEARCH_LAB_WORK_FORM_MODEL_KEY
		="searchLabWorkForm";

	private static final String TOTAL_MODEL_KEY
		="total";
	
	private static final String FACILITY_MODEL_KEY = "facility";
	
	/* Services. */
	@Autowired
	@Qualifier("labWorkResultsService")
	private LabWorkResultsService  labWorkResultsService;
	
	@Autowired
	@Qualifier("facilityService")
	private FacilityService facilityService;
	
	@Autowired
	@Qualifier("resolveLabWorkFormValidator")
	private ResolveLabWorkFormValidator resolveLabWorkFormValidator;
	
	/* Report services. */
	
	/* Property editor factories. */
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	@Qualifier("facilityPropertyEditorFactory")
	private PropertyEditorFactory facilityPropertyEditorFactory;
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;
	
	@Autowired
	@Qualifier("labPropertyEditorFactory")
	private PropertyEditorFactory labPropertyEditorFactory;
	
	@Autowired
	@Qualifier("labWorkPropertyEditorFactory")
	private PropertyEditorFactory labWorkPropertyEditorFactory;
	
	/* Helpers. */
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	@Autowired
	@Qualifier("healthControllerDelegate")
	private HealthControllerDelegate healthControllerDelegate;
	
	/* Constructors. */
	
	/** Instantiates a controller to resolve lab work. */
	public ResolveLabWorksController() {
		// Default instantiation
	}
	
	/* URL invokable methods. */
	
	/**
	 * Displays a list of lab work results.
	 * 
	 * @param offender offender
	 * @param facility facility
	 * @param filterByEndDate end date
	 * @param filterByStartDate start date
	 * @param sampleTaken sample taken
	 * @return view to display the list of lab works
	 */
	
	@RequestMapping(value="/resolve/resolve.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('LAB_WORK_EDIT') or hasRole('HEALTH_ADMIN') or "
			+ "hasRole('ADMIN')")
	public ModelAndView resolve(@RequestParam(value = "facility",required =true) 
			final Facility facility,
		@RequestParam(value = "offender", required = false) 
			final Offender offender,
		@RequestParam(value = "filterByEndDate", required = false) 
			final Date endDate,
		@RequestParam(value = "filterByStartDate", required = false) 
			final Date startDate,
		@RequestParam(value = "sampleTaken", required = false)
			final Boolean sampleTaken, ResolveLabWorkForm resolveLabWorkForm, 
			final BindingResult result) {
		SearchLabWorkForm searchLabWorkForm = new SearchLabWorkForm();
		List<LabWork> labWorks;
		boolean tempSampleTaken=false;
		
		if (offender != null) {
			if(sampleTaken==null){
				tempSampleTaken=false;
			}
			else {
				tempSampleTaken=sampleTaken;
			}
			labWorks = this.labWorkResultsService.findIncompleteByOffender(
					offender, facility, startDate, endDate, tempSampleTaken);
		} else {
			if(sampleTaken==null){
				tempSampleTaken=false;
			}
			else {
				tempSampleTaken=sampleTaken;
			}
			labWorks = this.labWorkResultsService.findIncompleteByFacility(
					facility, startDate, endDate, tempSampleTaken);
		}

		for(LabWork labWork:labWorks) {
			ResolveLabWorkItem resolveLabWorkItem = new ResolveLabWorkItem();
			resolveLabWorkItem.setLabWork(labWork);
			resolveLabWorkItem.setTaken(labWork.getSampleTaken());
			resolveLabWorkItem.setSampleNotes(labWork.getSampleNotes());   
			resolveLabWorkItem.setOperation(ResolveLabWorkItemOperation.UPDATE);
			if (labWork.getResults() != null) {
			 resolveLabWorkItem.setResultsDate(labWork.getResults().getDate());    
			 resolveLabWorkItem.setResultNotes(labWork.getResults().getNotes());
			 resolveLabWorkItem.setResultsLab(labWork.getResults().getLab());
			}
			resolveLabWorkForm.getLabWorkItems().add(resolveLabWorkItem);
		}
			
		int total = labWorks.size(); 
		searchLabWorkForm.setOffender(offender);
		searchLabWorkForm.setFilterByStartDate(startDate);
		searchLabWorkForm.setFilterByEndDate(endDate);   
		searchLabWorkForm.setSampleTaken(false);
		ModelAndView mav = prepareEditMav(
			resolveLabWorkForm, searchLabWorkForm, offender, facility, total); 
		
		return mav;
	}

	/**
	 * Updates the displayed lab work list based on update and removal of 
	 * existing lab work.
	 * 
	 * @param resolveLabWorkForm resolveLabWorkForm
	 * @param facility facility
	 * @param offender offender
	 * @param startDate start date
	 * @param endDate end date
	 * @return redirect to referral center
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/resolve/save.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('LAB_WORK_EDIT') or hasRole('HEALTH_ADMIN') or "
			+ "hasRole('ADMIN')")
	public ModelAndView save(
		@RequestParam(value = "facility", required = true)
			final Facility facility,
		@RequestParam(value = "offender", required = true)
			final Offender offender,
		@RequestParam(value = "startDate", required = false)
			final Date startDate,
		@RequestParam(value = "endDate", required = false)
			final Date endDate, final ResolveLabWorkForm resolveLabWorkForm, 
			final BindingResult result, 
			final SearchLabWorkForm searchLabWorkForm) 
					throws BusinessException {	
		this.resolveLabWorkFormValidator.validate(resolveLabWorkForm, result);
		List<LabWork> labWorks = this.labWorkResultsService
				.findIncompleteByOffender(
						offender, facility, startDate, endDate, true);
		if (result.hasErrors()) {
			int total = labWorks.size();
			ModelAndView mav = prepareEditMav(
					resolveLabWorkForm, searchLabWorkForm, offender, 
					facility, total); 
			return mav;
		}
		List<ResolveLabWorkItem> labWorkItems = 
				resolveLabWorkForm.getLabWorkItems();
		for(ResolveLabWorkItem labWorkItem:labWorkItems) {
			if(labWorkItem.getOperation() == ResolveLabWorkItemOperation.REMOVE)
			{
				LabWork labWork1= labWorkItem.getLabWork();
				this.labWorkResultsService.remove(labWork1);
			} 
			else if (labWorkItem.getOperation() 
					== ResolveLabWorkItemOperation.UPDATE)  
			{  
				if ((labWorkItem.getTaken()	!=null && labWorkItem.getTaken())
						||(labWorkItem.getLabWork().getSampleTaken() != null
						&& labWorkItem.getLabWork().getSampleTaken()==true))
				{
					if (labWorkItem.getLabWork().getSampleTaken() != null
						&& labWorkItem.getLabWork().getSampleTaken()==true) {
						this.labWorkResultsService.updateSampleNotes(
						labWorkItem.getLabWork(), labWorkItem.getSampleNotes());
					} else {
						this.labWorkResultsService.takeSample(
						labWorkItem.getLabWork(), labWorkItem.getSampleNotes());
					}
					this.labWorkResultsService.updateResults(
						labWorkItem.getLabWork(), new LabWorkResults(
						labWorkItem.getResultsLab(), 
						labWorkItem.getResultsDate(), 
						labWorkItem.getResultNotes()));
				}
			}			
		}

		return new ModelAndView(this.healthControllerDelegate
			.prepareFacilityCenterRedirectWithParameter(
			facility, offender, startDate, endDate, ReferralType.LAB, null));
	}
	
	private ModelAndView prepareEditMav(
			final ResolveLabWorkForm resolveLabWorkForm, 
			final SearchLabWorkForm searchLabWorkForm,
			final Offender offender,
			final Facility facility,
			final int total) {
		
		ModelAndView mav = new ModelAndView(EDIT_VIEW_NAME);
		mav.addObject(SEARCH_LAB_WORK_FORM_MODEL_KEY, searchLabWorkForm);
		mav.addObject(RESOLVE_LAB_WORK_FORM_MODEL_KEY, resolveLabWorkForm);
		mav.addObject(OFFENDER_MODEL_KEY, offender);
		List<Lab> resultsLab = this.labWorkResultsService.findResultsLabs();
		mav.addObject(RESULTS_LAB_MODEL_KEY, resultsLab);
		mav.addObject(TOTAL_MODEL_KEY, total);
		mav.addObject(FACILITY_MODEL_KEY, facility);

		if (offender != null) {
			this.offenderSummaryModelDelegate.add(mav.getModelMap(), offender);
		}
		return mav;
	}	
	
	/**
	 * Sets up binder.
	 * 
	 * @param binder web binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(Offender.class,
			this.offenderPropertyEditorFactory
					.createOffenderPropertyEditor());
		binder.registerCustomEditor(Facility.class,
			this.facilityPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(Date.class,
			this.customDateEditorFactory
					.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(Lab.class,
			this.labPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(LabWork.class,
			this.labWorkPropertyEditorFactory
					.createPropertyEditor());
	}
}