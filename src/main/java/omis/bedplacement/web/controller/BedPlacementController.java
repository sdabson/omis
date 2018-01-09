package omis.bedplacement.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.bedplacement.domain.BedPlacement;
import omis.bedplacement.domain.BedPlacementReason;
import omis.bedplacement.exception.BedOccupiedException;
import omis.bedplacement.exception.BedPlacementDateConflictException;
import omis.bedplacement.report.BedPlacementSummary;
import omis.bedplacement.service.BedPlacementService;
import omis.bedplacement.web.form.BedPlacementForm;
import omis.bedplacement.web.validator.BedPlacementFormValidator;
import omis.content.RequestContentMapping;
import omis.content.RequestContentType;
import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
import omis.facility.domain.Bed;
import omis.facility.domain.Complex;
import omis.facility.domain.Facility;
import omis.facility.domain.Level;
import omis.facility.domain.Room;
import omis.facility.domain.Section;
import omis.facility.domain.Unit;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.report.ReportFormat;
import omis.report.ReportRunner;
import omis.report.web.controller.delegate.ReportControllerDelegate;
import omis.util.DateManipulator;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

/**
 * Controller for bed placement.
 * 
 * @author Joel Norris
 * @version 0.1.1 (February 6, 2015)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/bedPlacement")
@PreAuthorize("hasRole('USER')")
public class BedPlacementController {
	
	/* View names. */
	
	private static final String LIST_VIEW_NAME = "bedPlacement/list";
	
	private static final String EDIT_VIEW_NAME = "bedPlacement/edit";
	
	private static final String COMPLEX_SELECT_VIEW_NAME
		= "bedPlacement/includes/complexSelect";
	
	private static final String BED_PLACEMENTS_ACTION_MENU_VIEW_NAME
		= "bedPlacement/includes/bedPlacementsActionMenu";
	
	private static final String BED_PLACEMENT_ACTION_MENU_VIEW_NAME
		= "bedPlacement/includes/bedPlacementActionMenu";
	
	private static final String ROOM_OPTIONS_VIEW_NAME 
		= "bedPlacement/includes/roomOptions"; 
	
	private static final String BED_OPTIONS_VIEW_NAME
		= "bedPlacement/includes/bedOptions";
	
	private static final String LEVEL_OPTIONS_VIEW_NAME
		= "bedPlacement/includes/levelOptions";
	
	private static final String SECTION_OPTIONS_VIEW_NAME
		= "bedPlacement/includes/sectionOptions";
	
	private static final String UNIT_OPTIONS_VIEW_NAME
		= "bedPlacement/includes/unitOptions";
	
	private static final String BED_PLACEMENT_ROW_ACTION_MENU_VIEW_NAME
		= "bedPlacement/includes/bedPlacementRowActionMenu";
	
	/* Redirect URLs. */
	
	private static final String LIST_REDIRECT_URL 
		= "redirect:list.html?offender=";
	
	/* Model Keys. */
	
	private static final String BED_PLACEMENTS_MODEL_KEY = "bedPlacements";
	
	private static final String OFFENDER_MODEL_KEY = "offender";
	
	private static final String BED_PLACEMENT_FORM_MODEL_KEY 
		= "bedPlacementForm";

	private static final String MATCHING_COMPLEXES_MODEL_KEY =
			"matchingComplexes";
	
	private static final String MATCHING_UNITS_MODEL_KEY = "matchingUnits";
	
	private static final String MATCHING_LEVELS_MODEL_KEY = "matchingLevels";
	
	private static final String MATCHING_SECTIONS_MODEL_KEY 
		= "matchingSections";
	
	private static final String MATCHING_ROOMS_MODEL_KEY = "matchingRooms";
	
	private static final String MATCHING_BEDS_MODEL_KEY = "matchingBeds";
	
	private static final String BED_PLACEMENT_REASONS_MODEL_KEY 
		= "bedPlacementReasons";
	
	private static final String FACILITY_MODEL_KEY = "facility";
	
	private static final String BED_PLACEMENT_MODEL_KEY = "bedPlacement";
	
	private static final String ACTIVE_BED_PLACEMENT_SUMMARY = "activeBedPlacementSummary";
	
	/* Message keys. */
	
	private static final String BED_PLACEMENT_EXISTS_MESSAGE_KEY 
		= "bedPlacement.exists";
	
	private static final String BED_PLACEMENT_DATE_CONFLICT_MESSAGE_KEY
		= "bedPlacement.bedPlacementDate.conflict";
	
	private static final String BED_OCCUPIED_MESSAGE_KEY
		= "bedPlacement.bedOccupied";
	
	/* Bundles. */
	
	private static final String ERROR_BUNDLE_NAME
		= "omis.bedplacement.msgs.form";
	
	/* Services. */

	@Autowired
	@Qualifier("bedPlacementService")
	private BedPlacementService bedPlacementService;

	@Autowired
    private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	/* Property Editors. */
	
	@Autowired
	private CustomDateEditorFactory customDateEditorFactory;
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	@Qualifier("bedPlacementPropertyEditorFactory")
	private PropertyEditorFactory bedPlacementPropertyEditorFactory;
	
	@Autowired
	@Qualifier("bedPlacementReasonPropertyEditorFactory")
	private PropertyEditorFactory bedPlacementReasonPropertyEditorFactory;
	
	@Autowired
	@Qualifier("facilityPropertyEditorFactory")
	private PropertyEditorFactory facilityPropertyEditorFactory;
	
	@Autowired
	@Qualifier("complexPropertyEditorFactory")
	private PropertyEditorFactory complexPropertyEditorFactory;
	
	@Autowired
	@Qualifier("unitPropertyEditorFactory")
	private PropertyEditorFactory unitPropertyEditorFactory;
	
	@Autowired
	@Qualifier("levelPropertyEditorFactory")
	private PropertyEditorFactory levelPropertyEditorFactory;
	
	@Autowired
	@Qualifier("sectionPropertyEditorFactory")
	private PropertyEditorFactory sectionPropertyEditorFactory;
	
	@Autowired
	@Qualifier("roomPropertyEditorFactory")
	private PropertyEditorFactory roomPropertyEditorFactory;
	
	@Autowired
	@Qualifier("bedPropertyEditorFactory")
	private PropertyEditorFactory bedPropertyEditorFactory;
	
	/* Validators. */
	
	@Autowired
	@Qualifier("bedPlacementFormValidator")
	private BedPlacementFormValidator bedPlacementFormValidator;
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("businessExceptionHandlerDelegate")
	private BusinessExceptionHandlerDelegate businessExceptionHandlerDelegate;
	
	/* Report names. */
	
	private static final String BED_PLACEMENT_LISTING_REPORT_NAME 
		= "/Placement/BedAssignment/Bed_Placement_Listing";

	private static final String BED_PLACEMENT_DETAILS_REPORT_NAME 
		= "/Placement/BedAssignment/Bed_Placement_Details";

	/* Report parameter names. */
	
	private static final String BED_PLACEMENT_LISTING_ID_REPORT_PARAM_NAME 
		= "DOC_ID";

	private static final String BED_PLACEMENT_DETAILS_ID_REPORT_PARAM_NAME 
		= "BED_PLACEMENT_ID";
	
	/* Report runners. */
	
	@Autowired
	@Qualifier("reportRunner")
	private ReportRunner reportRunner;
	
	/* Controller delegates. */
	
	@Autowired
	@Qualifier("reportControllerDelegate")
	private ReportControllerDelegate reportControllerDelegate;
	
	/** Instantiates a default instance of bed placement controller. */
	public BedPlacementController() {
		//empty constructor
	}
	
	/**
	 * Lists the bed placements for the specified offender.
	 * 
	 * @param offender offender
	 * @return model and view
	 */
	@RequestContentMapping(nameKey = "bedPlacementListScreenName",
			descriptionKey = "bedPlacementListScreenDescription",
			messageBundle = "omis.placement.msgs.bedPlacement",
			screenType = RequestContentType.LISTING_SCREEN)
	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('BED_PLACEMENT_LIST') or hasRole('ADMIN')")
	public ModelAndView list(@RequestParam(value = "offender", required = true)
			final Offender offender) {
		ModelMap map = new ModelMap();
		map.addAttribute(BED_PLACEMENTS_MODEL_KEY, this.bedPlacementService
				.findByOffender(offender));
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		this.offenderSummaryModelDelegate.add(map, offender);
		return new ModelAndView(LIST_VIEW_NAME, map);
	}
	
	/**
	 * Displays the bed placement form for viewing/editing a
	 * specified bed placement.
	 * 
	 * @param bedPlacement bed placement
	 * @return model and view
	 */
	@RequestContentMapping(nameKey = "bedPlacementEditScreenName",
			descriptionKey = "bedPlacementEditScreenDescription",
			messageBundle = "omis.placement.msgs.bedPlacement",
			screenType = RequestContentType.DETAIL_SCREEN)
	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('BED_PLACEMENT_VIEW') or hasRole('ADMIN')")
	public ModelAndView editBedPlacement(@RequestParam(value = "bedPlacement",
			required = true) final BedPlacement bedPlacement) {
		ModelMap map = new ModelMap();
		BedPlacementForm form = new BedPlacementForm();
		this.prepareBedPlacementForm(form, bedPlacement);
		Facility facility = bedPlacement.getBed().getRoom().getFacility();
		this.prepareEditMap(map, facility, form.getComplex(), form.getUnit(), 
				form.getSection(), form.getLevel(), form.getRoom(), new Date(),
				bedPlacement, bedPlacement.getOffender());
		map.addAttribute(BED_PLACEMENT_FORM_MODEL_KEY, form);
		return new ModelAndView(EDIT_VIEW_NAME, map);
	}
	
	/**
	 * Submits the bed placement form and updates the specified bed placement
	 * with values from the edit screen.
	 * 
	 * @param bedPlacement bed placement
	 * @param form form
	 * @param result binding result
	 * @return model and view
	 * @throws DuplicateEntityFoundException thrown when a duplicate bed
	 * placement, other than the specified bed placement, is found while
	 * updating
	 * @throws BedOccupiedException thrown when the specified bed placement's
	 * bed is already occupied by another offender's bed placement
	 * @throws BedPlacementDateConflictException thrown when the bed placement to be updated
	 * is flagged as confirmed, and another confirmed bed placement is found
	 * within the declared date range.
	 */
	@RequestContentMapping(nameKey = "bedPlacementEditName",
			descriptionKey = "bedPlacementEditDescription",
			messageBundle = "omis.placement.msgs.bedPlacement",
			screenType = RequestContentType.FORM_SUBMIT)
	@RequestMapping(value = "/edit.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('BED_PLACEMENT_EDIT') or hasRole('ADMIN')")
	public ModelAndView updateBedPlacement(@RequestParam(
			value = "bedPlacement", required = true) 
				final BedPlacement bedPlacement, final BedPlacementForm form,
			final BindingResult result) throws DuplicateEntityFoundException, 
			BedPlacementDateConflictException, BedOccupiedException {
		this.bedPlacementFormValidator.validate(form, result);
		if (result.hasErrors()) {
			ModelMap map = new ModelMap();
			map.addAttribute(BindingResult.MODEL_KEY_PREFIX 
					+ BED_PLACEMENT_FORM_MODEL_KEY, result);
			this.prepareEditMap(map, form.getFacility(), form.getComplex(), 
					form.getUnit(), form.getSection(), form.getLevel(), 
					form.getRoom(), new Date(), bedPlacement, 
					bedPlacement.getOffender());
			map.addAttribute(BED_PLACEMENT_FORM_MODEL_KEY, form);
			return new ModelAndView(EDIT_VIEW_NAME, map);
		}
		Date endDate = form.getEndDate();
		if (form.getEndTime() != null) {
			endDate = DateManipulator.getDateAtTimeOfDay(form.getEndDate(),
					form.getEndTime());
		}
		this.bedPlacementService.update(bedPlacement, form.getBed(), 
				form.getConfirmed(), new DateRange(this.combineDateAndTime(
						form.getStartDate(), form.getStartTime()), 
						endDate), form.getDescription(), 
				form.getBedPlacementReason());
		return new ModelAndView(LIST_REDIRECT_URL + bedPlacement.getOffender()
				.getId());
	}
	
	/**
	 * Displays the bed placement form for the creation of a 
	 * new bed placement.
	 * 
	 * @param offender offender
	 * @return model and view
	 */
	@RequestContentMapping(nameKey = "bedPlacementCreateScreenName",
			descriptionKey = "bedPlacementCreateScreenDescription",
			messageBundle = "omis.placement.msgs.bedPlacement",
			screenType = RequestContentType.DETAIL_SCREEN)
	@RequestMapping(value = "/create.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('BED_PLACEMENT_CREATE') or hasRole('ADMIN')")
	public ModelAndView createBedPlacement(@RequestParam(value = "offender",
			required = true) final Offender offender) {
		ModelMap map = new ModelMap();
		Facility facility = this.bedPlacementService.findFacility(offender, 
				new Date());
		BedPlacementForm form = new BedPlacementForm();
		map.addAttribute(BED_PLACEMENT_FORM_MODEL_KEY, form);
		BedPlacement activeBedPlacement = this.bedPlacementService
				.findBedPlacementByOffenderOnDate(offender, new Date());
		if (activeBedPlacement != null) {
			BedPlacementSummary summary = this.populateBedPlacementSummary(activeBedPlacement);
			map.addAttribute(ACTIVE_BED_PLACEMENT_SUMMARY, summary);
		}
		form.setActiveBedPlacement(this.bedPlacementService
				.findBedPlacementByOffenderOnDate(offender, new Date()));
		this.prepareEditMap(map, facility, null, null, null, null, null, 
				new Date(), null, offender);
		return new ModelAndView(EDIT_VIEW_NAME, map);
	}
	
	/*
	 * Returns a bed placement summary populated with values from the specified bed placement.
	 * 
	 * @param placement bed placement
	 * @return populated bed placement summary.
	 */
	private BedPlacementSummary populateBedPlacementSummary(final BedPlacement placement) {
		final String sectionName;
		Section section = placement.getBed().getRoom().getSection();
		if(section != null) {
			sectionName = section.getName();
		} else {
			sectionName = null;
		}
		final String unitName;
		Unit unit = placement.getBed().getRoom().getUnit();
		if(unit != null) {
			unitName = unit.getName();
		} else {
			unitName = null;
		}
		final String levelName;
		Level level = placement.getBed().getRoom().getLevel();
		if(level != null) {
			levelName = level.getName();
		} else {
			levelName = null;
		}
		final String complexName;
		Complex complex = placement.getBed().getRoom().getComplex();
		if(complex != null) {
			complexName = complex.getName();
		} else {
			complexName = null;
		}
		return new BedPlacementSummary(placement.getId(), placement.getBed().getNumber(),
				placement.getBed().getRoom().getName(), sectionName, unitName, levelName, complexName,
				placement.getBed().getRoom().getFacility().getName());
	}
	
	/**
	 * Submits the creation of a new bed placement for the specified offender
	 * with values from the bed placement form on the bed placement create
	 * screen. 
	 * 
	 * @param form form 
	 * @param offender offender
	 * @param result binding result
	 * @return model and view
	 * @throws DuplicateEntityFoundException thrown when a duplicate bed
	 * placement is found while trying to save.
	 * @throws BedOccupiedException thrown when the form's bed is already 
	 * occupied by another bed placement
	 * @throws BedPlacementDateConflictException thrown when the specified
	 * start date is in range of an already existing bed placement for the
	 * desired offender 
	 */
	@RequestContentMapping(nameKey = "bedPlacementSaveName",
			descriptionKey = "bedPlacementSaveDescription",
			messageBundle = "omis.placement.msgs.bedPlacement",
			screenType = RequestContentType.FORM_SUBMIT)
	@RequestMapping(value = "/create.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('BED_PLACEMENT_CREATE') or hasRole('ADMIN')")
	public ModelAndView saveBedPlacement(@RequestParam(value = "offender", 
				required = true) final Offender offender,
				final BedPlacementForm form, 
			final BindingResult result) throws DuplicateEntityFoundException, 
			BedPlacementDateConflictException, BedOccupiedException {
		this.bedPlacementFormValidator.validate(form, result);
		if (result.hasErrors()) {
			ModelMap map = new ModelMap();
			map.addAttribute(BindingResult.MODEL_KEY_PREFIX 
					+ BED_PLACEMENT_FORM_MODEL_KEY, result);
			BedPlacement activeBedPlacement = this.bedPlacementService
					.findBedPlacementByOffenderOnDate(offender, new Date());
			if (activeBedPlacement != null) {
				BedPlacementSummary summary = this.populateBedPlacementSummary(activeBedPlacement);
				map.addAttribute(ACTIVE_BED_PLACEMENT_SUMMARY, summary);
			}
			this.prepareEditMap(map, form.getFacility(), form.getComplex(), 
					form.getUnit(), form.getSection(), form.getLevel(), 
					form.getRoom(), new Date(), null, offender);
			map.addAttribute(BED_PLACEMENT_FORM_MODEL_KEY, form);
			return new ModelAndView(EDIT_VIEW_NAME, map);
		}
		Date endDate = form.getEndDate();
		if (form.getEndTime() != null) {
			endDate = DateManipulator.getDateAtTimeOfDay(form.getEndDate(),
					form.getEndTime());
		}
		if (form.getActiveBedPlacement() != null && form.getEndActiveBedPlacement()) {
			this.bedPlacementService.endBedPlacement(form.getActiveBedPlacement(), 
					this.combineDateAndTime(form.getStartDate(), form.getStartTime()));
		}
		this.bedPlacementService.create(offender, form.getBed(), 
				form.getConfirmed(), new DateRange(this.combineDateAndTime(
							form.getStartDate(), form.getStartTime()), endDate),
						form.getDescription(), form.getBedPlacementReason());
		return new ModelAndView(LIST_REDIRECT_URL + offender.getId());
	}
	
	/**
	 * 
	 * Removes the specified bed placement and returns to the bed placement
	 * list screen.
	 * 
	 * @param bedPlacement bed placement
	 * @return model and view redirect to bed placement list screen
	 */
	@RequestContentMapping(nameKey = "bedPlacementRemoveName",
			descriptionKey = "bedPlacementRemoveDescription",
			messageBundle = "omis.placement.msgs.bedPlacement",
			screenType = RequestContentType.FORM_SUBMIT)
	@RequestMapping(value = "/remove.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('BED_PLACEMENT_REMOVE') or hasRole('ADMIN')")
	public ModelAndView removeBedPlacement(@RequestParam(value = "bedPlacement",
			required = true) final BedPlacement bedPlacement) {
		this.bedPlacementService.remove(bedPlacement);
		return new ModelAndView(LIST_REDIRECT_URL 
				+ bedPlacement.getOffender().getId());
	}
	
	/**
	 * Populates the drop down for selection of a complex within a facility.
	 * 
	 * @param offender offender
	 * @param facility facility
	 * @return model and view
	 */
	@RequestContentMapping(nameKey = "selectComplexAjaxName",
			descriptionKey = "selectComplexAjaxDescription",
			messageBundle = "omis.placement.msgs.bedPlacement",
			screenType = RequestContentType.AJAX_REQUEST)
	@RequestMapping(value = "/complexSelect.html"
			, method = RequestMethod.GET)
	public ModelAndView listComplexes(@RequestParam(
			value = "offender", required = true) 
			final Offender offender, @RequestParam(value = 
			"facility", required = true) final Facility facility) {
		ModelMap map = new ModelMap();
		map.addAttribute(MATCHING_COMPLEXES_MODEL_KEY, this.bedPlacementService
				.findComplexes(facility));
		return new ModelAndView(COMPLEX_SELECT_VIEW_NAME, map);
	}
	
	/**
	 * Populates the drop down for selection of a bed within a room.
	 * 
	 * @param offender offender
	 * @param room room
	 * @return model and view
	 */
	@RequestContentMapping(nameKey = "selectBedAjaxName",
			descriptionKey = "selectBedAjaxDescription",
			messageBundle = "omis.placement.msgs.bedPlacement",
			screenType = RequestContentType.AJAX_REQUEST)
	@RequestMapping(value = "/bedSelect.html"
			, method = RequestMethod.GET)
	public ModelAndView listBeds(@RequestParam(value = "offender",
			required = true) final Offender offender, @RequestParam(
					value = "room", required = true) 
					final Room room) {
		ModelMap map = new ModelMap();
		List<Bed> matchingBeds = this.bedPlacementService.findBeds(room);
		map.addAttribute(MATCHING_BEDS_MODEL_KEY, matchingBeds);
		return new ModelAndView(BED_OPTIONS_VIEW_NAME, map);
	}
	
	/**
	 * Updates the specified bed placement to show that it is confirmed. If 
	 * another, on going, bed placement is present for the offender it will be
	 * ended so it does not cause a conflict of date ranges for that offender's
	 * bed placements.
	 * 
	 * @param bedPlacement bed placement
	 * @return model and view
	 * @throws BedOccupiedException thrown when the specified bed placement's
	 * bed is already occupied during the effective date
	 * @throws BedPlacementDateConflictException thrown when the specified bed
	 * placement's start date conflicts with another bed placement for the 
	 * offender of the specified bed placement
	 */
	@RequestContentMapping(nameKey = "completeMoveAjaxName",
			descriptionKey = "completeMoveAjaxDescription",
			messageBundle = "omis.placement.msgs.bedPlacement",
			screenType = RequestContentType.OTHER)
	@RequestMapping(value = "/completeMove.html"
			, method = RequestMethod.GET)
	@PreAuthorize("hasRole('BED_PLACEMENT_EDIT') or hasRole('ADMIN')")
	public ModelAndView confirmPlacement(@RequestParam(value = "bedPlacement",
				required = true)final BedPlacement bedPlacement, 
			@RequestParam(value = "effectiveDate", required = false)
				final Date effectiveDate) 
		throws BedPlacementDateConflictException, BedOccupiedException {
		final Date date;
		if (effectiveDate != null) {
			date = effectiveDate;
		} else {
			date = bedPlacement.getDateRange().getStartDate();
		}
		this.bedPlacementService.confirm(bedPlacement, date);
		return new ModelAndView(LIST_REDIRECT_URL 
				+ bedPlacement.getOffender().getId());
	}
	
	/**
	 * Returns the content for the bed placements action menu.
	 * 
	 * @param offender offender
	 * @return model and view to show the bed placements action menu
	 */
	@RequestMapping(value = "bedPlacementsActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView bedPlacementsActionMenu(
		@RequestParam(value = "offender", required = true)
			final Offender offender) {
		ModelMap map = new ModelMap();
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		return new ModelAndView(BED_PLACEMENTS_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Returns the content for the bed placement action menu.
	 * 
	 * @param offender offender
	 * @return model and view to show the bed placement action menu
	 */
	@RequestMapping(value = "bedPlacementActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView bedPlacementActionMenu(
			@RequestParam(value = "offender")final Offender offender) {
		ModelMap map = new ModelMap();
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		return new ModelAndView(BED_PLACEMENT_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Returns a model and view for the bed placement row action menu.
	 * 
	 * @param bedPlacement bed placement
	 * @return model and view for bed placement row action menu
	 */
	@RequestMapping(value = "bedPlacementRowActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView bedPlacementRowActionMenu(
			@RequestParam(value = "bedPlacement", required = false)
		final BedPlacement bedPlacement) {
		ModelMap map = new ModelMap();
		map.addAttribute(BED_PLACEMENT_MODEL_KEY, bedPlacement);
		return new ModelAndView(BED_PLACEMENT_ROW_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Returns a list of units appropriate to the specified facility layer.
	 * 
	 * @param filterOperation facility layer filter operation
	 * @param complex complex
	 * @return model and view for unit options
	 */
	@RequestMapping(value = "unitOptions.html", method=RequestMethod.GET)
	public ModelAndView filterUnits(
			@RequestParam(value = "facility", required = true)
				final Facility facility,
			@RequestParam(value = "complex", required = false) 
				final Complex complex) {
		ModelMap map = new ModelMap();
		map.addAttribute(MATCHING_UNITS_MODEL_KEY, this.bedPlacementService
				.findUnits(facility, complex));
		return new ModelAndView(UNIT_OPTIONS_VIEW_NAME, map);
	}
	
	/**
	 * Returns a list of levels appropriate to the specified facility layer.
	 * 
	 * @param filterOperation facility layer filter operation
	 * @param complex complex
	 * @param unit unit
	 * @param section section
	 * @return model and view for level options
	 */
	@RequestMapping(value = "levelOptions.html", method=RequestMethod.GET)
	public ModelAndView filterLevels(
			@RequestParam(value = "facility", required = true)
				final Facility facility,
			@RequestParam(value = "complex", required = false) 
				final Complex complex, 
			@RequestParam(value = "unit", required = false) final Unit unit,
			@RequestParam(value = "section", required = false) 
				final Section section) {
		ModelMap map = new ModelMap();
		map.addAttribute(MATCHING_LEVELS_MODEL_KEY, this.bedPlacementService
				.findLevels(facility, complex, unit, section));
		return new ModelAndView(LEVEL_OPTIONS_VIEW_NAME, map);
	}
	
	/**
	 * Returns a list of sections appropriate to the specified facility layer.
	 * 
	 * @param filterOperation facility layer filter operation
	 * @param complex complex
	 * @param unit unit
	 * @param level level
	 * @return model and view for section options
	 */
	@RequestMapping(value="sectionOptions.html", method = RequestMethod.GET)
	public ModelAndView filterSections(
			@RequestParam(value = "facility", required = true)
				final Facility facility,
			@RequestParam(value = "complex", required = false) 
				final Complex complex, 
			@RequestParam(value = "unit", required = false) final Unit unit,
			@RequestParam(value = "level", required = false) 
				final Level level) {
		ModelMap map = new ModelMap();
		map.addAttribute(MATCHING_SECTIONS_MODEL_KEY, this.bedPlacementService
				.findSections(facility, complex, unit, level));
		return new ModelAndView(SECTION_OPTIONS_VIEW_NAME, map);
	}
	
	/**
	 * Returns a list of rooms appropriate for the specified facility layer.
	 * 
	 * @param filterOperation facility layer filter operation
	 * @param complex complex
	 * @param unit unit
	 * @param section section
	 * @param level level
	 * @return model and view for room options
	 */
	@RequestMapping(value="roomOptions.html", method = RequestMethod.GET)
	public ModelAndView filterRooms(
			@RequestParam(value = "facility", required = false)
				final Facility facility,
			@RequestParam(value = "complex", required = false) 
				final Complex complex, 
			@RequestParam(value = "unit", required = false) final Unit unit,
			@RequestParam(value = "section", required = false) 
				final Section section,
			@RequestParam(value = "level", required = false) 
				final Level level) {
		ModelMap map = new ModelMap();
		map.addAttribute(MATCHING_ROOMS_MODEL_KEY, this.bedPlacementService
				.findRooms(facility, complex, unit, section, level));
		return new ModelAndView(ROOM_OPTIONS_VIEW_NAME, map);
	}
	
	/* Reports. */
	
	/**
	 * Returns the report for the specified offenders bed placements.
	 * 
	 * @param offender offender
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/bedPlacementListingReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('BED_PLACEMENT_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportBedPlacementListing(@RequestParam(
			value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(BED_PLACEMENT_LISTING_ID_REPORT_PARAM_NAME,
				Long.toString(offender.getOffenderNumber()));
		byte[] doc = this.reportRunner.runReport(
				BED_PLACEMENT_LISTING_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/**
	 * Returns the report for the specified bed placement.
	 * 
	 * @param bedPlacement bed placement
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/bedPlacementDetailsReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('BED_PLACEMENT_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportBedPlacementDetails(@RequestParam(
			value = "bedPlacement", required = true)
			final BedPlacement bedPlacement,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(BED_PLACEMENT_DETAILS_ID_REPORT_PARAM_NAME,
				Long.toString(bedPlacement.getId()));
		byte[] doc = this.reportRunner.runReport(
				BED_PLACEMENT_DETAILS_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/* Exception handler methods. */
	
	/**
	 * Handles duplicate entity found exceptions.
	 * 
	 * @param exception duplicate entity found exception
	 * @return model and view for displaying exception explanation
	 */
	@ExceptionHandler(DuplicateEntityFoundException.class)
	public ModelAndView handleDuplicateEntityFoundException(
			final DuplicateEntityFoundException exception) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
				BED_PLACEMENT_EXISTS_MESSAGE_KEY, ERROR_BUNDLE_NAME, 
				exception);
	}
	
	/**
	 * Handles bed placement date conflict exceptions.
	 * 
	 * @param exception bed placement date conflict exception
	 * @return model and view for displaying exception explanation
	 */
	@ExceptionHandler(BedPlacementDateConflictException.class)
	public ModelAndView handleBedPlacementDateConflictException(
			final BedPlacementDateConflictException exception) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
				BED_PLACEMENT_DATE_CONFLICT_MESSAGE_KEY, ERROR_BUNDLE_NAME, 
				exception);
	}
	
	/**
	 * Handles bed occupies exceptions.
	 * 
	 * @param exception bed occupied exception
	 * @return model and view for displaying exception explanation
	 */
	@ExceptionHandler(BedOccupiedException.class)
	public ModelAndView handleBedOccupiedException(
			final BedOccupiedException exception) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
				BED_OCCUPIED_MESSAGE_KEY, ERROR_BUNDLE_NAME, exception);
	}
	
	/* Helper methods. */
	
	/*
	 * Prepares a model map with closed text values needed to edit a bed 
	 * placement.
	 * 
	 * @param map model map
	 * @param facility facility
	 * @param complex complex
	 * @param unit unit
	 * @param section section
	 * @param level level
	 * @param room room
	 * @param date date
	 * @param bedPlacement bed placement
	 * @param offender offender
	 * @return model map
	 */
	private ModelMap prepareEditMap(final ModelMap map, final Facility facility, 
			final Complex complex, final Unit unit, final Section section, 
			final Level level, final Room room, final Date date, 
			final BedPlacement bedPlacement, final Offender offender) {
		if (facility != null) {
			map.addAttribute(MATCHING_COMPLEXES_MODEL_KEY, 
					this.bedPlacementService.findComplexes(facility));
			map.addAttribute(FACILITY_MODEL_KEY, facility);
			map.addAttribute(MATCHING_UNITS_MODEL_KEY,
					this.bedPlacementService.findUnits(facility, complex));
			map.addAttribute(MATCHING_SECTIONS_MODEL_KEY,
					this.bedPlacementService
					.findSections(facility, complex, unit, level));
			map.addAttribute(MATCHING_LEVELS_MODEL_KEY,
					this.bedPlacementService
					.findLevels(facility, complex, unit, section));
			map.addAttribute(MATCHING_ROOMS_MODEL_KEY, this.bedPlacementService
					.findRooms(facility, complex, unit, section, level));
		}
		if (room != null) {
			List<Bed> matchingBeds = this.bedPlacementService.findBeds(room);
			map.addAttribute(MATCHING_BEDS_MODEL_KEY, matchingBeds);
		}
		map.addAttribute(BED_PLACEMENT_MODEL_KEY, bedPlacement);
		//Set all other closed text values
		map.addAttribute(BED_PLACEMENT_REASONS_MODEL_KEY,
				this.bedPlacementService.findBedPlacementReasons());
		//Set Offender and display information
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		this.offenderSummaryModelDelegate.add(map, offender);
		return map;
	}
	
	/*
	 * Prepares the bed placement form with information
	 * from the specified bed placement.
	 * 
	 * @param form bed placement form
	 * @param bedPlacement bed placement
	 */
	private void prepareBedPlacementForm(final BedPlacementForm form,
			final BedPlacement bedPlacement) {
		Room room = bedPlacement.getBed().getRoom();
		form.setFacility(room.getFacility());
		form.setComplex(room.getComplex());
		form.setUnit(room.getUnit());
		form.setSection(room.getSection());
		form.setLevel(room.getLevel());
		form.setRoom(room);
		form.setBed(bedPlacement.getBed());
		form.setConfirmed(bedPlacement.getConfirmed());
		form.setStartDate(bedPlacement.getDateRange().getStartDate());
		form.setStartTime(bedPlacement.getDateRange().getStartDate());
		form.setEndDate(bedPlacement.getDateRange().getEndDate());
		form.setEndTime(bedPlacement.getDateRange().getEndDate());
		form.setDescription(bedPlacement.getDescription());
		form.setBedPlacementReason(bedPlacement.getBedPlacementReason());
	}
	
	/*
	 * Combine a date and time into a single date object by adding the time
	 * to the date.
	 * 
	 * @param date date
	 * @param time time
	 */
	private Date combineDateAndTime(final Date date, final Date time) {
		return DateManipulator.getDateAtTimeOfDay(date, time);
	}
	
	/**
	 * Sets up and registers property editors.
	 * 
	 * @param binder binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(
				BedPlacement.class,
				this.bedPlacementPropertyEditorFactory
				.createPropertyEditor());
		
		binder.registerCustomEditor(
				BedPlacementReason.class,
				this.bedPlacementReasonPropertyEditorFactory
				.createPropertyEditor());
		
		binder.registerCustomEditor(
				Facility.class,
				this.facilityPropertyEditorFactory
				.createPropertyEditor());
		
		binder.registerCustomEditor(
				Complex.class,
				this.complexPropertyEditorFactory
				.createPropertyEditor());
		
		binder.registerCustomEditor(
				Unit.class,
				this.unitPropertyEditorFactory
				.createPropertyEditor());
		
		binder.registerCustomEditor(
				Level.class,
				this.levelPropertyEditorFactory
				.createPropertyEditor());
		
		binder.registerCustomEditor(
				Section.class,
				this.sectionPropertyEditorFactory
				.createPropertyEditor());
		
		binder.registerCustomEditor(
				Room.class,
				this.roomPropertyEditorFactory
				.createPropertyEditor());
		
		binder.registerCustomEditor(
				Bed.class,
				this.bedPropertyEditorFactory
				.createPropertyEditor());
		
		binder.registerCustomEditor(
				Offender.class,
				this.offenderPropertyEditorFactory
				.createOffenderPropertyEditor());
		
		binder.registerCustomEditor(
				Date.class,
				this.customDateEditorFactory
				.createCustomDateOnlyEditor(true));
		
		binder.registerCustomEditor(
				Date.class, "startTime",
				this.customDateEditorFactory
				.createCustomTimeOnlyEditor(true));
		
		binder.registerCustomEditor(
				Date.class, "endTime",
				this.customDateEditorFactory
				.createCustomTimeOnlyEditor(true));
	}
}