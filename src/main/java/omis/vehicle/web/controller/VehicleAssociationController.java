package omis.vehicle.web.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.report.OffenderReportService;
import omis.offender.report.OffenderSummary;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.person.domain.Person;
import omis.person.domain.PersonName;
import omis.region.domain.State;
import omis.vehicle.domain.VehicleAssociation;
import omis.vehicle.domain.VehicleColor;
import omis.vehicle.domain.VehicleLicense;
import omis.vehicle.domain.VehicleMake;
import omis.vehicle.domain.VehicleModel;
import omis.vehicle.domain.VehicleOwnerAssociation;
import omis.vehicle.service.OffenderVehicleManager;
import omis.vehicle.web.form.VehicleAssociationForm;
import omis.vehicle.web.validator.VehicleAssociationFormValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/** Controller for vehicle association related operations.
 * @author Ryan Johns
 * @author Yidong Li
 * @version 0.1.1 (Jun 1, 2016)
 * @since OMIS 3.0 */
@Controller
@RequestMapping("/vehicle")
@PreAuthorize("hasRole('USER')")
public class VehicleAssociationController {
	/* views */
	
	private static final String EDIT_VIEW_NAME = "vehicle/edit";
	
	private static final String VEHICLE_MODELS_VIEW_NAME 
		= "vehicle/includes/vehicleModelOptions";
	
	private static final String VEHICLE_MODEL_VIEW_NAME 
		= "vehicle/includes/vehicleModelOptionsEdit";
	
	private static final String VEHICLE_ACTION_MENU_VIEW_NAME
		= "vehicle/includes/vehicleActionMenu";
	private static final String OWNER_DESCRIPTION_FORMAT = "%1$s %2$s";
	
	/* Redirects. */
	
	private static final String LIST_REDIRECT
		= "redirect:/vehicle/list.html?offender=%d";
	
	/* Property editor. */
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	@Qualifier("vehicleAssociationPropertyEditorFactory")
	private PropertyEditorFactory vehicleAssociationPropertyEditorFactory;
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory datePropertyEditorFactory;
	
	@Autowired
	@Qualifier("vehicleMakePropertyEditorFactory")
	private PropertyEditorFactory vehicleMakePropertyEditorFactory;
	
	@Autowired
	@Qualifier("vehicleModelPropertyEditorFactory")
	private PropertyEditorFactory vehicleModelPropertyEditorFactory;
	
	@Autowired
	@Qualifier("vehicleColorPropertyEditorFactory")
	private PropertyEditorFactory vehicleColorPropertyEditorFactory;
	
	@Autowired
	@Qualifier("vehicleLicensePropertyEditorFactory")
	private PropertyEditorFactory vehicleLicensePropertyEditorFactory;
	
	@Autowired
	@Qualifier("vehicleOwnerAssociationPropertyEditorFactory")
	private PropertyEditorFactory vehicleOwnerAssociationPropertyEditorFactory;
	
	@Autowired
	@Qualifier("statePropertyEditorFactory")
	private PropertyEditorFactory statePropertyEditorFactory;
	
	@Autowired
	@Qualifier("personPropertyEditorFactory")
	private PropertyEditorFactory personPropertyEditorFactory;
		
	/* model keys */
	
	private static final String VEHICLE_ASSOCIATION_FORM_MODEL_KEY 
		= "vehicleAssociationForm";
	
	private static final String VEHICLE_ASSOCIATION_MODEL_KEY 
		= "vehicleAssociation";
	
	private static final String VEHICLE_MODELS_MODEL_KEY = "vehicleModels";
	
	private static final String VEHICLE_MODEL_MODEL_KEY ="selectedVehicleModel";
	
	private static final String VEHICLE_COLORS_MODEL_KEY = "vehicleColors";
	
	private static final String VEHICLE_MAKES_MODEL_KEY = "vehicleMakes";
	
	private static final String VEHICLE_STATES_MODEL_KEY = "vehicleStates";
	
	private static final String OFFENDER_MODEL_KEY = "offender";
	
	private static final String OFFENDER_SUMMARY_MODEL_KEY = "offenderSummary";
	
	private static final String CURRENT_YEAR_MODEL_KEY = "currentYear";
	
	private static final String START_YEAR_MODEL_KEY = "startYear";
	
	private static final int startYear = 1900; 
	
	/* Services. */
	
	@Autowired
	@Qualifier("offenderVehicleManager")
	private OffenderVehicleManager offenderVehicleManager; 
	
	/* Delegate */
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	/* Validator */
	
	@Autowired
	@Qualifier("vehicleAssociationFormValidator")
	private VehicleAssociationFormValidator vehicleAssociationFormValidator;
	
	@Autowired
	@Qualifier("offenderReportService")
	private OffenderReportService offenderReportService;
	
	/* Constructor. */
	
	/** Instantiates a default vehicle association controller. */
	public VehicleAssociationController() {
		// Default instantiation
	}
	
	/**
	 * Displays screen to create new vehicle association.
	 * 
	 * @param offender offender for whom to create vehicle association
	 * @return model and view to create vehicle association
	 */
	@RequestMapping(value = "/create.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('VEHICLE_CREATE') or hasRole('ADMIN')")
	public ModelAndView create(
		@RequestParam(value = "offender", required = true)
			final Offender offender) {
				VehicleAssociationForm vehicleAssociationForm 
					= new VehicleAssociationForm();
				PersonName suggestedName = offender.getName();
				vehicleAssociationForm.setOwnerDescription(
						String.format(OWNER_DESCRIPTION_FORMAT,
								suggestedName.getFirstName(),
								suggestedName.getLastName()));
				return this.prepareEditMav(vehicleAssociationForm,offender);
	}
	
	/** Edit offender vehicle association. 
	 * @param vehicleAssociation vehicle association.
	 * @return edited vehicle association view. */
	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('VEHICLE_VIEW') or hasRole('ADMIN')")
	public ModelAndView edit(
		@RequestParam(value = "vehicleAssociation", required = true)
		final VehicleAssociation vehicleAssociation) {
			VehicleAssociationForm vehicleAssociationForm 
				= new VehicleAssociationForm();

			vehicleAssociationForm.setComments(vehicleAssociation.getComments());
			if(vehicleAssociation.getDateRange().getEndDate()!=null){
				vehicleAssociationForm.setEndDate(vehicleAssociation
					.getDateRange().getEndDate());
			}
			vehicleAssociationForm.setStartDate(vehicleAssociation.getDateRange()
				.getStartDate());
			vehicleAssociationForm.setYear(vehicleAssociation.getYear());
			vehicleAssociationForm.setVehicleMake(vehicleAssociation
				.getVehicleMake());
			vehicleAssociationForm.setVehicleModel(vehicleAssociation
				.getVehicleModel());
			vehicleAssociationForm.setVehicleColor(vehicleAssociation
				.getVehicleColor());
			if(offenderVehicleManager
					.findLicenseByVehicleAssociation(vehicleAssociation)!=null){
				vehicleAssociationForm.setPlateNumber(offenderVehicleManager
					.findLicenseByVehicleAssociation(vehicleAssociation)
					.getPlateNumber());
				vehicleAssociationForm.setState(offenderVehicleManager
					.findLicenseByVehicleAssociation(vehicleAssociation)
					.getState());
			}
			if(offenderVehicleManager
				.findOwnerByVehicleAssociation(vehicleAssociation)!=null){
					vehicleAssociationForm.setOwnerDescription(offenderVehicleManager
						.findOwnerByVehicleAssociation(vehicleAssociation)
						.getOwnerDescription());
			}

			ModelAndView mav = prepareEditMav(vehicleAssociationForm, 
				vehicleAssociation.getOffender()); 
			mav.addObject(VEHICLE_ASSOCIATION_MODEL_KEY, vehicleAssociation);
			return mav; 
	}
	
	/**
	 * Saves a new created vehicle association.
	 * 
	 * @param offender offender
	 * @param vehicleAssociationForm vehicle association form
	 * @param result binding result
	 * @return redirect to list vehicle association by offender
	 * @throws DuplicateEntityFoundException if the vehicle association exists
	 */
	@RequestMapping(value = "/create.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('VEHICLE_CREATE') or hasRole('ADMIN')")
	public ModelAndView save(
		@RequestParam(value = "offender", required = true)
			final Offender offender,
			final VehicleAssociationForm vehicleAssociationForm,
			final BindingResult result) throws DuplicateEntityFoundException {
			this.vehicleAssociationFormValidator.validate(
				vehicleAssociationForm, result);
			if (result.hasErrors()) {
				return this.prepareRedisplayEditMav(
					offender, vehicleAssociationForm, result);
			} 

		this.offenderVehicleManager.associateVehicle(offender, 
			vehicleAssociationForm.getVehicleModel(),	
			vehicleAssociationForm.getVehicleColor(), 
			vehicleAssociationForm.getYear(), 
			vehicleAssociationForm.getOwnerDescription(),
			new DateRange(vehicleAssociationForm.getStartDate(),
				vehicleAssociationForm.getEndDate()), 
			vehicleAssociationForm.getComments(), 
			vehicleAssociationForm.getPlateNumber(),
			vehicleAssociationForm.getState(),
			vehicleAssociationForm.getVehicleMake());
		return new ModelAndView(String.format(LIST_REDIRECT, offender.getId()));
	}
	
	/**
	 * Updates an existing vehicle association.
	 * 
	 * @param vehicleAssociation vehicle association to update
	 * @param vehicleAssociationForm vehicle association form
	 * @param result binding result
	 * @return redirect to list vehicle association
	 * @throws DuplicateEntityFoundException
	 */
	@RequestMapping(value = "/edit.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('VEHICLE_EDIT') or hasRole('ADMIN')")
	public ModelAndView update(
		@RequestParam(value = "vehicleAssociation", required = true)
			final VehicleAssociation vehicleAssociation,
			final VehicleAssociationForm vehicleAssociationForm,
			final BindingResult result) throws DuplicateEntityFoundException {	
			this.vehicleAssociationFormValidator.validate(
				vehicleAssociationForm, result);
			if (result.hasErrors()) {
				ModelAndView mav = this.prepareRedisplayEditMav(
						vehicleAssociation.getOffender(), 
						vehicleAssociationForm, result);
				mav.addObject(VEHICLE_ASSOCIATION_MODEL_KEY, vehicleAssociation);
			return mav;
			}

		this.offenderVehicleManager.updateVehicle(vehicleAssociation, 
			vehicleAssociationForm.getVehicleModel(), 
			vehicleAssociationForm.getVehicleColor(), 
			vehicleAssociationForm.getYear(), 
			new DateRange(vehicleAssociationForm.getStartDate(),
			vehicleAssociationForm.getEndDate()), 
			vehicleAssociationForm.getComments(),
			vehicleAssociationForm.getVehicleMake(),
			vehicleAssociationForm.getState(),
			vehicleAssociationForm.getPlateNumber(),
			vehicleAssociationForm.getOwnerDescription(),
			vehicleAssociation.getOffender());
		return new ModelAndView(String.format(LIST_REDIRECT,
			vehicleAssociation.getOffender().getId()));
	}
	
	/**
	 * Removes an existing vehicle association.
	 * 
	 * @param vehicleAssociation vehicle association to remove
	 * @return redirect to list religious preferences
	 */
	@RequestMapping("/remove.html")
	@PreAuthorize("hasRole('VEHICLE_REMOVE') or hasRole('ADMIN')")
	public ModelAndView remove(
		@RequestParam(value = "vehicleAssociation", required = true)
			final VehicleAssociation vehicleAssociation) {
		Offender offender = vehicleAssociation.getOffender();
		this.offenderVehicleManager.remove(vehicleAssociation);
		return new ModelAndView(String.format(LIST_REDIRECT, offender.getId()));
	}
	
	/**
	 * Returns a view for an vehicle action menu pertaining to the specified
	 * offender.
	 * 
	 * @param offender offender
	 * @return view for vehicle action menu
	 */
	@RequestMapping(value = "/vehicleActionMenu.html",method =RequestMethod.GET)
	public ModelAndView vehicleActionMenu(@RequestParam(value = "offender",
		required = true) final Offender offender) {
		ModelMap map = new ModelMap();
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		return new ModelAndView(VEHICLE_ACTION_MENU_VIEW_NAME, map);
	}
	
	private ModelAndView prepareEditMav(
		final VehicleAssociationForm vehicleAssociationForm, 
		final Offender offender) {
			ModelAndView mav = new ModelAndView(EDIT_VIEW_NAME);
			mav.addObject(VEHICLE_ASSOCIATION_FORM_MODEL_KEY, 
				vehicleAssociationForm);
			List<VehicleMake> vehicleMakes = this.offenderVehicleManager
				.findVehicleMake();
			mav.addObject(VEHICLE_MAKES_MODEL_KEY, vehicleMakes);
			List<VehicleColor> vehicleColors = this.offenderVehicleManager
				.findColors();
			mav.addObject(VEHICLE_COLORS_MODEL_KEY, vehicleColors);
			List<State> vehicleStates = this.offenderVehicleManager.findStates();
			mav.addObject(VEHICLE_STATES_MODEL_KEY, vehicleStates);
			VehicleModel selectedVehicleModel = vehicleAssociationForm
				.getVehicleModel();
			mav.addObject(VEHICLE_MODEL_MODEL_KEY, selectedVehicleModel);
			List<VehicleModel> vehicleModels = this.offenderVehicleManager
				.findVehicleModelsByMake(vehicleAssociationForm
				.getVehicleMake());
			mav.addObject(VEHICLE_MODELS_MODEL_KEY, vehicleModels);
			OffenderSummary offenderSummary = this.offenderReportService
					.summarizeOffender(offender);
			mav.addObject(OFFENDER_SUMMARY_MODEL_KEY, offenderSummary);
			this.offenderSummaryModelDelegate.add(mav.getModelMap(), offender);
			
			int nextYear = Calendar.getInstance().get(Calendar.YEAR)+ 1;
			mav.addObject(START_YEAR_MODEL_KEY, startYear);
			mav.addObject(CURRENT_YEAR_MODEL_KEY, nextYear);
			
			return mav;
	}	
	
	// Prepares redisplay edit model and view
	private ModelAndView prepareRedisplayEditMav(
		final Offender offender,
		final VehicleAssociationForm vehicleAssociationForm,
		final BindingResult result) {
		ModelAndView mav = this.prepareEditMav(
			vehicleAssociationForm, offender);
		mav.addObject(BindingResult.MODEL_KEY_PREFIX
			+ VEHICLE_ASSOCIATION_FORM_MODEL_KEY, result);
		return mav;
	}	

	/**
	 * List model options by make 
	 * 
	 * @param  vehicleMake vehicle make
	 * @return redirect to list vehicle models corresponding to vehicle make
	 */
	@RequestMapping("listModelsByMake.html")
	public ModelAndView listModelsByMake(
		@RequestParam(value = "vehicleMake", required = false)
		final VehicleMake vehicleMake){
		ModelMap map = new ModelMap();
		
		if(vehicleMake!=null){
			List<VehicleModel> vehicleModels = this.offenderVehicleManager
				.findVehicleModelsByMake(vehicleMake);
			map.addAttribute(VEHICLE_MODELS_MODEL_KEY, vehicleModels); 
		} 
			
		return new ModelAndView(VEHICLE_MODELS_VIEW_NAME, map); 
	}
	
	/**
	 * List model options by make at the beginning of edit vehicle association
	 * 
	 * @param  vehicleMake vehicle make
	 * @return redirect to list vehicle models corresponding to vehicle make
	 */
	@RequestMapping("editModelsByMake.html")
	public ModelAndView editModelsByMake(
		@RequestParam(value = "vehicleModel", required = false)
		final VehicleModel vehicleModel){
		ModelMap map = new ModelMap();
		map.addAttribute(VEHICLE_MODEL_MODEL_KEY, vehicleModel); 
		return new ModelAndView(VEHICLE_MODEL_VIEW_NAME, map); 
	}
	
	/**
	 * Sets up and registers property editors.
	 * 
	 * @param binder web binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(VehicleMake.class,
			this.vehicleMakePropertyEditorFactory.createPropertyEditor());
 		binder.registerCustomEditor(Offender.class,
			this.offenderPropertyEditorFactory.createOffenderPropertyEditor());
		binder.registerCustomEditor(Date.class,
			this.datePropertyEditorFactory.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(VehicleAssociation.class,
			this.vehicleAssociationPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(VehicleModel.class,
			this.vehicleModelPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(VehicleColor.class,
			this.vehicleColorPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(VehicleOwnerAssociation.class,
			this.vehicleOwnerAssociationPropertyEditorFactory
			.createPropertyEditor());  
		binder.registerCustomEditor(VehicleLicense.class,
			this.vehicleLicensePropertyEditorFactory
			.createPropertyEditor());  
		binder.registerCustomEditor(State.class,
			this.statePropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(Person.class,
			this.personPropertyEditorFactory.createPropertyEditor());
	}
}
