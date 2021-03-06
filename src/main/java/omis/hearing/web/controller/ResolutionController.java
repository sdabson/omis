/*
 * OMIS - Offender Management Information System
 * Copyright (C) 2011 - 2017 State of Montana
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package omis.hearing.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import omis.condition.domain.Condition;
import omis.disciplinaryCode.domain.DisciplinaryCode;
import omis.exception.DuplicateEntityFoundException;
import omis.hearing.domain.DispositionCategory;
import omis.hearing.domain.Hearing;
import omis.hearing.domain.HearingStatus;
import omis.hearing.domain.HearingStatusCategory;
import omis.hearing.domain.ImposedSanction;
import omis.hearing.domain.Infraction;
import omis.hearing.domain.InfractionPlea;
import omis.hearing.domain.ResolutionClassificationCategory;
import omis.hearing.domain.UserAttendance;
import omis.hearing.domain.component.Resolution;
import omis.hearing.exception.HearingExistsException;
import omis.hearing.exception.HearingStatusExistsException;
import omis.hearing.exception.InfractionExistsException;
import omis.hearing.exception.UserAttendanceExistsException;
import omis.hearing.report.HearingSummaryReportService;
import omis.hearing.report.ViolationSummary;
import omis.hearing.report.ViolationSummaryReportService;
import omis.hearing.service.HearingService;
import omis.hearing.service.ResolutionService;
import omis.hearing.web.form.GoToOption;
import omis.hearing.web.form.ItemOperation;
import omis.hearing.web.form.ResolutionForm;
import omis.hearing.web.form.UserAttendanceItem;
import omis.hearing.web.form.ViolationItem;
import omis.hearing.web.form.ViolationSelectionItem;
import omis.hearing.web.form.ViolationsSelectForm;
import omis.hearing.web.validator.ResolutionFormValidator;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.person.domain.Person;
import omis.supervision.domain.SupervisoryOrganization;
import omis.user.domain.UserAccount;
import omis.util.DateManipulator;
import omis.util.StringUtility;
import omis.violationevent.domain.ConditionViolation;
import omis.violationevent.domain.DisciplinaryCodeViolation;
import omis.violationevent.domain.ViolationEvent;
import omis.violationevent.domain.ViolationEventCategory;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

/**
 * Resolution Controller.
 * 
 * @author Annie Wahl
 * @author Josh Divine
 * @author Ryan Johns
 * @version 0.1.5 (July 6, 2018)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/hearing/resolution/")
@PreAuthorize("hasRole('USER')")
public class ResolutionController {
	
	/* View Names */
	
	private static final String EDIT_VIEW_NAME = "/hearing/resolution/edit";
	
	private static final String RESOLUTION_ACTION_MENU_VIEW_NAME =
			"/hearing/resolution/includes/resolutionActionMenu";
	
	private static final String USER_ATTENDANCE_ITEMS_ACTION_MENU_VIEW_NAME =
			"/hearing/resolution/includes/userAttendanceItemsActionMenu";
	
	private static final String USER_ATTENDANCE_ITEM_ROW_VIEW_NAME =
			"/hearing/resolution/includes/userAttendanceItemTableRow";
	
	private static final String VIOLATIONS_LIST_REDIRECT =
			"redirect:/hearing/violations/list.html?offender=%d";
	
	private static final String HEARINGS_LIST_REDIRECT =
			"redirect:/hearing/list.html?offender=%d";
	
	private static final String VIOLATIONS_SELECT_REDIRECT =
			"redirect:/hearing/violations/select.html?offender=%d&hearing=%d"
			+ "&resolutionCategory=%s&violationCategory=%s&goToOption=%s";
	
	/* Model Keys */
	
	private static final String HEARING_MODEL_KEY = "hearing";
	
	private static final String OFFENDER_MODEL_KEY = "offender";
	
	private static final String HEARING_SUMMARY_MODEL_KEY = "hearingSummary";
	
	private static final String HEARING_STATUS_CATEGORIES_MODEL_KEY =
			"hearingStatusCategories";
	
	private static final String DISPOSITION_CATEGORIES_MODEL_KEY =
			"dispositionCategories";
	
	private static final String RESOLUTION_CATEGORY_MODEL_KEY =
			"resolutionCategory";
	
	private static final String INFRACTION_PLEAS_MODEL_KEY = "infractionPleas";
	
	private static final String USER_ATTENDANCE_ITEM_MODEL_KEY =
			"userAttendanceItem";
	
	private static final String USER_ATTENDANCE_ITEM_INDEX_MODEL_KEY =
			"userAttendanceItemIndex";
	
	private static final String RESOLUTION_FORM_MODEL_KEY =
			"resolutionForm";

	private static final String DISCIPLINARY_VIOLATION_SUMMARY_MAP_MODEL_KEY =
			"disciplinaryViolationSummaryMap";
	
	private static final String SUPERVISORY_VIOLATION_SUMMARY_MAP_MODEL_KEY =
			"supervisoryViolationSummaryMap";

	private static final String VIOLATION_SUMMARY_MODEL_KEY =
			"violationSummary";
	
	/* Message Keys */
	
	private static final String ENTITY_EXISTS_MESSAGE_KEY =
			"entity.resolution.exists";
	
	private static final String HEARING_EXISTS_MESSAGE_KEY
		= "hearing.exists";
	
	private static final String INFRACTION_EXISTS_MESSAGE_KEY
		= "infraction.exists";
	
	private static final String HEARING_STATUS_EXISTS_MESSAGE_KEY
		= "hearingStatus.exists";
	
	private static final String USER_ATTENDANCE_EXISTS_MESSAGE_KEY
		= "userAttendance.exists";
	
	/* Bundles */
	
	private static final String ERROR_BUNDLE_NAME =
			"omis.hearing.msgs.form";
	
	/* Services */
	
	@Autowired
	@Qualifier("resolutionService")
	private ResolutionService resolutionService;
	
	@Autowired
	@Qualifier("hearingService")
	private HearingService hearingService;

	
	@Autowired
	@Qualifier("violationSummaryReportService")
	private ViolationSummaryReportService violationSummaryReportService;
	
	@Autowired
	@Qualifier("hearingSummaryReportService")
	private HearingSummaryReportService hearingSummaryReportService;
	
	/* Property Editor Factories */
	
	@Autowired
	@Qualifier("disciplinaryCodeViolationPropertyEditorFactory")
	private PropertyEditorFactory
		disciplinaryCodeViolationPropertyEditorFactory;

	@Autowired
	@Qualifier("conditionViolationPropertyEditorFactory")
	private PropertyEditorFactory conditionViolationPropertyEditorFactory;

	@Autowired
	@Qualifier("infractionPropertyEditorFactory")
	private PropertyEditorFactory infractionPropertyEditorFactory;
	
	@Autowired
	@Qualifier("infractionPleaPropertyEditorFactory")
	private PropertyEditorFactory infractionPleaPropertyEditorFactory;
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	@Qualifier("hearingPropertyEditorFactory")
	private PropertyEditorFactory hearingPropertyEditorFactory;
	
	@Autowired
	@Qualifier("userAttendancePropertyEditorFactory")
	private PropertyEditorFactory userAttendancePropertyEditorFactory;
	
	@Autowired
	@Qualifier("userAccountPropertyEditorFactory")
	private PropertyEditorFactory userAccountPropertyEditorFactory;
	
	@Autowired
	@Qualifier("personPropertyEditorFactory")
	private PropertyEditorFactory personPropertyEditorFactory;
	
	@Autowired
	@Qualifier("disciplinaryCodePropertyEditorFactory")
	private PropertyEditorFactory disciplinaryCodePropertyEditorFactory;

	@Autowired
	@Qualifier("conditionPropertyEditorFactory")
	private PropertyEditorFactory conditionPropertyEditorFactory;
	
	@Autowired
	private CustomDateEditorFactory customDateEditorFactory;
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("businessExceptionHandlerDelegate")
	private BusinessExceptionHandlerDelegate businessExceptionHandlerDelegate;
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	/* Validator */
	
	@Autowired
	@Qualifier("resolutionFormValidator")
	private ResolutionFormValidator resolutionFormValidator;
	
	
	
	/**
	 * Default Constructor for ResolutionController.
	 */
	public ResolutionController() {
	}
	
	/**
	 * Returns the ModelAndView for creating Infractions.
	 * @param offender - Offender
	 * @param hearing - Hearing
	 * @param resolutionCategory - ResolutionClassificationCategory
	 * @param violationCategory - ViolationEventCategory
	 * @param selectForm - ViolationSelectForm, carried over from the
	 * Violations Select screen
	 * @return ModelAndView for creating Infractions
	 */
	@RequestMapping(value = "/create.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole ('VIOLATION_CREATE')")
	public ModelAndView create(
			@RequestParam(value = "offender", required = true)
				final Offender offender,
			@RequestParam(value = "hearing", required = false)
				final Hearing hearing,
			@RequestParam(value = "resolutionCategory", required = true)
				final ResolutionClassificationCategory resolutionCategory,
			@RequestParam(value = "violationCategory", required = true)
				final ViolationEventCategory violationCategory,
			final ViolationsSelectForm selectForm) {
		
		switch (resolutionCategory) {
			case FORMAL:
				if (!this.resolutionService.findInfractionsByHearing(hearing)
						.isEmpty()) {
					return this.prepareResolutionMav(hearing,
							resolutionCategory, this.prepareForm(hearing,
								violationCategory, resolutionCategory),
							new ModelMap());
				} else {
					return new ModelAndView(String.format(
							VIOLATIONS_SELECT_REDIRECT,
							offender.getId(), hearing.getId(),
							resolutionCategory, violationCategory,
							GoToOption.ADJUDICATE));
				}
			case INFORMAL:
			case DISMISSED:
				return this.prepareResolutionMav(offender, resolutionCategory,
						this.prepareForm(selectForm, violationCategory,
								resolutionCategory),
						new ModelMap());
			default :
				throw new UnsupportedOperationException(
						"Resolution Category Not Supported");
		}
	}
	
	/**
	 * Creates Infractions/ImposedSanctions/HearingStatus and returns to
	 * either the Hearings List or the Violations List.
	 * @param offender - Offender
	 * @param hearing - Hearing
	 * @param resolutionCategory - ResolutionClassificationCategory
	 * @param violationCategory - ViolationEventCategory
	 * @param form - ResolutionForm
	 * @param bindingResult - BindingResult
	 * @return ModelAndView - Redirects to either the
	 * Hearings List or the Violations List (dependent on resolutionCategory)
	 * Or back to the resolution creation screen on form error
	 * @throws DuplicateEntityFoundException - Could occur when a HearingStatus
	 * already exists with the supplied Date and Category for the given hearing
	 * @throws HearingExistsException - When another Hearing already exists
	 * with the given properties.
	 * @throws HearingStatusExistsException - When another Hearing Status
	 * already exists with the given properties.
	 * @throws InfractionExistsException - When another Infraction already
	 * exists with the given properties.
	 * @throws UserAttendanceExistsException - When a User Attendance
	 * already exists with the given properties.
	 */
	@RequestMapping(value = "/create.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN') or hasRole ('VIOLATION_CREATE')")
	public ModelAndView save(
			@RequestParam(value = "offender", required = true)
				final Offender offender,
			@RequestParam(value = "hearing", required = false)
				final Hearing hearing,
			@RequestParam(value = "resolutionCategory", required = true)
				final ResolutionClassificationCategory resolutionCategory,
			@RequestParam(value = "violationCategory", required = true)
				final ViolationEventCategory violationCategory,
			final ResolutionForm form,
			final BindingResult bindingResult)
					throws InfractionExistsException, HearingExistsException,
					HearingStatusExistsException, UserAttendanceExistsException,
					DuplicateEntityFoundException {
		this.resolutionFormValidator.validate(form, bindingResult,
				resolutionCategory);
		if (bindingResult.hasErrors()) {
			switch (resolutionCategory) {
				case FORMAL:
					return this.prepareResolutionMav(hearing,
							resolutionCategory, form, new ModelMap());
				case INFORMAL:
				case DISMISSED:
					return this.prepareResolutionMav(offender,
							resolutionCategory, form, new ModelMap());
				default :
					throw new UnsupportedOperationException(
							"Resolution Category Not Supported");
			}
		} else {
			return processInfractions(offender, hearing, resolutionCategory,
					form);
		}
	}

	
	
	/**
	 * Returns the ModelAndView for editing an Infraction.
	 * @param infraction - Infraction to be edited
	 * @return ModelAndView for editing an Infraction
	 */
	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole ('VIOLATION_VIEW')")
	public ModelAndView edit(
			@RequestParam(value = "infraction", required = true)
				final Infraction infraction) {
		return this.prepareResolutionMav(infraction,
				this.prepareForm(infraction));
	}
	
	/**
	 * Updates an Infraction and returns to the list screen.
	 * @param infraction - Infraction being updated
	 * @param form - Resolution Form
	 * @param bindingResult - Binding Result
	 * @return ModelAndView - Redirects to the list screen or back to the
	 * Infraction edit screen on form error
	 * @throws DuplicateEntityFoundException - Could occur when a HearingStatus
	 * already exists with the supplied Date and Category for the given hearing
	 * @throws HearingExistsException - When another Hearing already exists
	 * with the given properties.
	 * @throws HearingStatusExistsException - When another Hearing Status
	 * already exists with the given properties.
	 * @throws InfractionExistsException - When another Infraction already
	 * exists with the given properties.
	 * @throws UserAttendanceExistsException - When a User Attendance
	 * already exists with the given properties.
	 */
	@RequestMapping(value = "/edit.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN') or hasRole ('VIOLATION_EDIT')")
	public ModelAndView update(
			@RequestParam(value = "infraction", required = true)
				final Infraction infraction,
				final ResolutionForm form, final BindingResult bindingResult)
					throws InfractionExistsException, HearingExistsException,
					HearingStatusExistsException, UserAttendanceExistsException,
					DuplicateEntityFoundException {
		
		this.resolutionFormValidator.validate(form, bindingResult,
				infraction.getResolution().getCategory());
		
		if (bindingResult.hasErrors()) {
			return this.prepareResolutionMav(infraction, form);
		} else {
			Offender offender;
			if (infraction.getConditionViolation() != null) {
				offender = infraction.getConditionViolation()
						.getViolationEvent().getOffender();
			} else {
				offender = infraction.getDisciplinaryCodeViolation()
						.getViolationEvent().getOffender();
			}
			ResolutionClassificationCategory resolutionCategory =
					infraction.getResolution().getCategory();
			
			return this.processInfractions(offender, infraction.getHearing(),
					resolutionCategory, form);
		}
	}
	
	/**
	 * Removes specified infraction and returns to the violations list screen.
	 * @param infraction - Infraction to remove
	 * @return ModelAndView - Violations List screen
	 */
	@RequestMapping(value = "/remove.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('VIOLATION_REMOVE') or hasRole('ADMIN')")
	public ModelAndView remove(@RequestParam(
			value = "infraction", required = true)
			final Infraction infraction) {
		Long offenderId = null;
		if (infraction.getDisciplinaryCodeViolation() != null) {
			offenderId = infraction.getDisciplinaryCodeViolation()
					.getViolationEvent().getOffender().getId();
		} else if (infraction.getConditionViolation() != null) {
			offenderId = infraction.getConditionViolation()
					.getViolationEvent().getOffender().getId();
		}
		
		ImposedSanction imposedSanction = this.hearingService
				.findImposedSanctionByInfraction(infraction);
		if (imposedSanction != null) {
			this.hearingService.removeImposedSanction(imposedSanction);
		}
		this.hearingService.removeInfraction(infraction);
		return new ModelAndView(String.format(VIOLATIONS_LIST_REDIRECT,
				offenderId));
	}
	
	/**
	 * Displays the Resolution Action Menu.
	 * @param offender - Offender
	 * @return ModelAndView - Resolution Action Menu
	 */
	@RequestMapping(value = "/resolutionActionMenu.html", 
			method = RequestMethod.GET)
	public ModelAndView displayResolutionActionMenu(@RequestParam
			(value = "offender", required = true) final Offender offender) {
		ModelMap map = new ModelMap();
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		
		return new ModelAndView(RESOLUTION_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Displays a user attendance item row.
	 * @param userAttendanceItemIndex - Integer
	 * @return ModelAndView - view for user attendance item row
	 */
	@RequestMapping(value = "createUserAttendanceItem.html",
			method = RequestMethod.GET)
	public ModelAndView displayUserAttendanceItemRow(@RequestParam(
			value = "userAttendanceItemIndex", required = true)
			final Integer userAttendanceItemIndex) {
		ModelMap map = new ModelMap();
		UserAttendanceItem userAttendanceItem = new UserAttendanceItem();
		userAttendanceItem.setItemOperation(ItemOperation.CREATE);
		map.addAttribute(USER_ATTENDANCE_ITEM_MODEL_KEY, userAttendanceItem);
		map.addAttribute(USER_ATTENDANCE_ITEM_INDEX_MODEL_KEY,
				userAttendanceItemIndex);
		return new ModelAndView(USER_ATTENDANCE_ITEM_ROW_VIEW_NAME, map);
	}
	
	/**
	 * Returns a model and view of user attendance items action menu.
	 * @param userAttendanceItemIndex - user attendance item index
	 * @return ModelAndView - model and view of user attendance items
	 * action menu
	 */
	@RequestMapping(value = "userAttendanceItemsActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayUserAttendanceItemsActionMenu(@RequestParam(
			value = "userAttendanceItemIndex", required = true)
			final Integer userAttendanceItemIndex) {
		ModelMap map = new ModelMap();	
		map.addAttribute(USER_ATTENDANCE_ITEM_INDEX_MODEL_KEY,
				userAttendanceItemIndex);
		return new ModelAndView(
				USER_ATTENDANCE_ITEMS_ACTION_MENU_VIEW_NAME, map);
	}
	
	/* Helper Methods */
	
	/**
	 * Prepares a ModelAndView for Resolution creation with a Hearing.
	 * @param hearing - Hearing
	 * @param resolutionCategory - ResolutionClassificationCategory
	 * @param form - ResolutionForm
	 * @param map - ModelMap
	 * @return ModelAndView for Resolution creation with a Hearing
	 */
	private ModelAndView prepareResolutionMav(final Hearing hearing,
			final ResolutionClassificationCategory resolutionCategory,
			final ResolutionForm form, final ModelMap map) {
		
		List<HearingStatusCategory> hearingStatusCategories =
				new ArrayList<HearingStatusCategory>();
		hearingStatusCategories.add(HearingStatusCategory.HELD);
		if (this.hearingService.findLatestHearingStatus(hearing).getCategory()
				.getAdjudicated()) {
			hearingStatusCategories.add(HearingStatusCategory.UPHELD);
			hearingStatusCategories.add(HearingStatusCategory.MODIFIED);
		}
		map.addAttribute(HEARING_STATUS_CATEGORIES_MODEL_KEY,
				hearingStatusCategories);
		map.addAttribute(DISPOSITION_CATEGORIES_MODEL_KEY,
				DispositionCategory.values());
		map.addAttribute(HEARING_MODEL_KEY, hearing);
		map.addAttribute(USER_ATTENDANCE_ITEM_INDEX_MODEL_KEY,
				form.getUserAttendanceItems().size());
		map.addAttribute(HEARING_SUMMARY_MODEL_KEY,
				this.hearingSummaryReportService.summarize(hearing));
		return prepareResolutionMav(hearing.getSubject().getOffender(),
				resolutionCategory, form, map);
	}
	
	/**
	 * Prepares a ModelAndView for Resolution creation with no Hearing.
	 * @param offender - Offender
	 * @param resolutionCategory - ResolutionClassificationHearing
	 * @param form - ResolutionForm form
	 * @param map - ModelMap
	 * @return ModelAndView for Resolution creation with no Hearing
	 */
	private ModelAndView prepareResolutionMav(final Offender offender,
			final ResolutionClassificationCategory resolutionCategory,
			final ResolutionForm form, final ModelMap map) {
		Map<ViolationSummary, List<DisciplinaryCode>>
			disciplinaryViolationSummaryMap =
			new LinkedHashMap<ViolationSummary, List<DisciplinaryCode>>();
		Map<ViolationSummary, List<Condition>> supervisoryViolationSummaryMap =
				new LinkedHashMap<ViolationSummary, List<Condition>>();
		for (ViolationItem item : form.getViolationItems()) {
			if (item.getDisciplinaryCodeViolation() != null) {
				ViolationEvent violationEvent = item
						.getDisciplinaryCodeViolation().getViolationEvent();
				disciplinaryViolationSummaryMap.put(
						this.violationSummaryReportService.summarize(
								item.getDisciplinaryCodeViolation()),
						this.hearingService
							.findDisciplinaryCodesByJurisdictionAndEventDate(
								(SupervisoryOrganization) violationEvent
									.getJurisdiction(),
								violationEvent.getEvent().getDate()));
			} else if (item.getConditionViolation() != null) {
				ViolationEvent violationEvent = item
						.getConditionViolation().getViolationEvent();
				supervisoryViolationSummaryMap.put(
						this.violationSummaryReportService.summarize(
								item.getConditionViolation()),
						this.hearingService
							.findConditionsByOffenderAndEffectiveDate(
								offender, violationEvent.getEvent().getDate()));
			}
		}
		map.addAttribute(DISCIPLINARY_VIOLATION_SUMMARY_MAP_MODEL_KEY,
				disciplinaryViolationSummaryMap);
		map.addAttribute(SUPERVISORY_VIOLATION_SUMMARY_MAP_MODEL_KEY,
				supervisoryViolationSummaryMap);
		map.addAttribute(RESOLUTION_CATEGORY_MODEL_KEY, resolutionCategory);
		map.addAttribute(RESOLUTION_FORM_MODEL_KEY, form);
		map.addAttribute(INFRACTION_PLEAS_MODEL_KEY,
				this.resolutionService.findInfractionPleas());
		map.addAttribute(USER_ATTENDANCE_ITEM_INDEX_MODEL_KEY,
				form.getUserAttendanceItems().size());
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		this.offenderSummaryModelDelegate.add(map, offender);
		
		return new ModelAndView(EDIT_VIEW_NAME, map);
	}
	
	/**
	 * Prepares a ModelAndView for Resolution editing for an Infraction.
	 * @param infraction - Infraction to be edited
	 * @param form - ResolutionForm
	 * @return ModelAndView - prepared ModelAndView for Resolution editing for
	 * an Infraction
	 */
	private ModelAndView prepareResolutionMav(final Infraction infraction, 
			final ResolutionForm form) {
		ModelMap map = new ModelMap();
		if (infraction.getHearing() != null) {
			List<HearingStatusCategory> hearingStatusCategories =
					new ArrayList<HearingStatusCategory>();
			hearingStatusCategories.add(HearingStatusCategory.HELD);
			if (this.hearingService.findLatestHearingStatus(infraction
					.getHearing()).getCategory().getAdjudicated()) {
				hearingStatusCategories.add(HearingStatusCategory.UPHELD);
				hearingStatusCategories.add(HearingStatusCategory.MODIFIED);
			}
			map.addAttribute(HEARING_STATUS_CATEGORIES_MODEL_KEY,
					hearingStatusCategories);
			map.addAttribute(HEARING_MODEL_KEY, infraction.getHearing());
			map.addAttribute(HEARING_SUMMARY_MODEL_KEY,
					this.hearingSummaryReportService.summarize(
							infraction.getHearing()));
		}
		
		Offender offender;
		
		if (infraction.getConditionViolation() != null) {
			offender = infraction.getConditionViolation()
					.getViolationEvent().getOffender();
			map.addAttribute(VIOLATION_SUMMARY_MODEL_KEY,
					this.violationSummaryReportService.summarize(
					infraction.getConditionViolation()));
		} else {
			offender = infraction.getDisciplinaryCodeViolation()
					.getViolationEvent().getOffender();
			map.addAttribute(VIOLATION_SUMMARY_MODEL_KEY,
				this.violationSummaryReportService.summarize(
						infraction.getDisciplinaryCodeViolation()));
		}
		Map<ViolationSummary, List<DisciplinaryCode>>
			disciplinaryViolationSummaryMap =
			new LinkedHashMap<ViolationSummary, List<DisciplinaryCode>>();
		Map<ViolationSummary, List<Condition>> supervisoryViolationSummaryMap =
				new LinkedHashMap<ViolationSummary, List<Condition>>();
		for (ViolationItem item : form.getViolationItems()) {
			if (item.getDisciplinaryCodeViolation() != null) {
				ViolationEvent violationEvent = item.getInfraction()
						.getDisciplinaryCodeViolation().getViolationEvent();
				disciplinaryViolationSummaryMap.put(
						this.violationSummaryReportService.summarize(
								item.getDisciplinaryCodeViolation()),
						this.hearingService
							.findDisciplinaryCodesByJurisdictionAndEventDate(
								(SupervisoryOrganization) violationEvent
									.getJurisdiction(),
								violationEvent.getEvent().getDate()));
			} else if (item.getConditionViolation() != null) {
				ViolationEvent violationEvent = item.getInfraction()
						.getConditionViolation().getViolationEvent();
				supervisoryViolationSummaryMap.put(
						this.violationSummaryReportService.summarize(
								item.getConditionViolation()),
						this.hearingService
							.findConditionsByOffenderAndEffectiveDate(
								offender, violationEvent.getEvent().getDate()));
			}
		}
		map.addAttribute(DISCIPLINARY_VIOLATION_SUMMARY_MAP_MODEL_KEY,
				disciplinaryViolationSummaryMap);
		map.addAttribute(SUPERVISORY_VIOLATION_SUMMARY_MAP_MODEL_KEY,
				supervisoryViolationSummaryMap);
		map.addAttribute(DISPOSITION_CATEGORIES_MODEL_KEY,
				DispositionCategory.values());
		map.addAttribute(RESOLUTION_CATEGORY_MODEL_KEY, 
				infraction.getResolution().getCategory());
		map.addAttribute(RESOLUTION_FORM_MODEL_KEY, form);
		map.addAttribute(INFRACTION_PLEAS_MODEL_KEY,
				this.resolutionService.findInfractionPleas());
		map.addAttribute(USER_ATTENDANCE_ITEM_INDEX_MODEL_KEY,
				form.getUserAttendanceItems().size());
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		this.offenderSummaryModelDelegate.add(map, offender);
		
		return new ModelAndView(EDIT_VIEW_NAME, map);
	}
	
	/**
	 * Populates a ResolutionForm based on the given properties.
	 * @param hearing - Hearing
	 * @param violationCategory - ViolationEventCategory
	 * @param resolutionCategory - ResolutionClassificationCategory
	 * @return Populated ResolutionForm based on the given properties
	 */
	private ResolutionForm prepareForm(final Hearing hearing,
			final ViolationEventCategory violationCategory,
			final ResolutionClassificationCategory resolutionCategory) {
		ResolutionForm form = new ResolutionForm();
		List<Infraction> infractions = this.resolutionService
				.findInfractionsByHearing(hearing);
		List<ViolationItem> violationItems = new ArrayList<ViolationItem>();
		
		for (Infraction infraction : infractions) {
			ViolationItem item = new ViolationItem();
			if (ViolationEventCategory.DISCIPLINARY.equals(violationCategory)) {
				item.setDisciplinaryCodeViolation(infraction
						.getDisciplinaryCodeViolation());
			} else if (ViolationEventCategory.SUPERVISION
					.equals(violationCategory)) {
				item.setConditionViolation(infraction.getConditionViolation());
			}
			item.setInfraction(infraction);
			
			if (infraction.getResolution() != null) {
				item.setDecision(infraction.getResolution().getDecision());
				item.setDisposition(infraction.getResolution()
						.getDisposition());
				item.setReason(infraction.getResolution().getReason());
				item.setAppealDate(infraction.getResolution().getAppealDate());
				item.setAuthority(infraction.getResolution().getAuthority());
				item.setDate(infraction.getResolution().getDate());
				if (infraction.getResolution().getAdjustedCondition() != null
					|| infraction.getResolution().getAdjustedCode() != null) {
					item.setAdjusted(true);
					item.setAdjustedCondition(infraction.getResolution()
							.getAdjustedCondition());
					item.setAdjustedDisciplinaryCode(infraction.getResolution()
							.getAdjustedCode());
				}
			}
			item.setPlea(infraction.getPlea());
			ImposedSanction imposedSanction = this.resolutionService
					.findImposedSanctionByInfraction(infraction);
			
			if (imposedSanction != null) {
				item.setSanction(imposedSanction.getDescription());
			}
			
			violationItems.add(item);
		}
		
		List<UserAttendanceItem> userAttendanceItems =
				new ArrayList<UserAttendanceItem>();
		List<UserAttendance> userAttended =
				this.hearingService.findUserAttendedByHearing(hearing);
		for (UserAttendance user : userAttended) {
			UserAttendanceItem item = new UserAttendanceItem();
			item.setUserAttended(user);
			item.setUserAccount(user.getUserAccount());
			item.setItemOperation(ItemOperation.UPDATE);
			
			userAttendanceItems.add(item);
		}
		form.setViolationItems(violationItems);
		form.setDate(hearing.getDate());
		form.setTime(hearing.getDate());
		form.setInAttendance(hearing.getSubject().getInAttendance());
		form.setUserAttendanceItems(userAttendanceItems);
		form.setGroupEdit(false);
		
		HearingStatus latestStatus =
				this.hearingService.findLatestHearingStatus(hearing);
		if (latestStatus.getCategory().getAdjudicated()) {
			form.setCategory(latestStatus.getCategory());
			form.setStatusDescription(latestStatus.getDescription());
		} else {
			form.setCategory(HearingStatusCategory.HELD);
		}
		
		return form;
	}
	
	/**
	 * Populates a ResolutionForm based on the given properties, including
	 * the properties from a ViolationsSelectForm.
	 * @param selectForm - ViolationsSelectForm
	 * @param violationCategory - ViolationEventCategory
	 * @param resolutionCategory - ResolutionClassificationCategory
	 * @return Populated ResolutionForm based on the given properties
	 */
	private ResolutionForm prepareForm(final ViolationsSelectForm selectForm,
			final ViolationEventCategory violationCategory,
			final ResolutionClassificationCategory resolutionCategory) {
		ResolutionForm form = new ResolutionForm();
		List<ViolationSelectionItem> selectedItems =
				selectForm.getViolationSelectionItems();
		List<ViolationItem> violationItems = new ArrayList<ViolationItem>();
		
		for (ViolationSelectionItem selectedItem : selectedItems) {
			if (selectedItem.getSelected()) {
				if (ViolationEventCategory.DISCIPLINARY
						.equals(violationCategory)) {
					ViolationItem item = new ViolationItem();
					item.setDisciplinaryCodeViolation(selectedItem
							.getDisciplinaryCodeViolation());
					violationItems.add(item);
				} else if (ViolationEventCategory.SUPERVISION.equals(
						violationCategory)) {
					ViolationItem item = new ViolationItem();
					item.setConditionViolation(selectedItem
							.getConditionViolation());
					violationItems.add(item);
				} else {
					throw new UnsupportedOperationException(
							"Violation Category Not Supported");
				}
			}
		}
		form.setViolationItems(violationItems);
		form.setGroupEdit(false);
		
		return form;
	}
	
	/**
	 * Populates a ResolutionForm from an Infraction.
	 * @param infraction - Infraction
	 * @return ResolutionForm - prepared ResolutionForm
	 */
	private ResolutionForm prepareForm(final Infraction infraction) {
		ResolutionForm form = new ResolutionForm();
		
		if (infraction.getHearing() != null) {
			form.setCategory(this.hearingService.findLatestHearingStatus(
					infraction.getHearing()).getCategory());
			form.setDate(infraction.getHearing().getDate());
			form.setStatusDescription(this.hearingService
					.findLatestHearingStatus(infraction.getHearing())
					.getDescription());
			form.setInAttendance(infraction.getHearing().getSubject()
					.getInAttendance());
			List<UserAttendanceItem> userAttendanceItems =
					new ArrayList<UserAttendanceItem>();
			List<UserAttendance> userAttended =
					this.hearingService.findUserAttendedByHearing(
							infraction.getHearing());
			for (UserAttendance user : userAttended) {
				UserAttendanceItem item = new UserAttendanceItem();
				item.setUserAttended(user);
				item.setUserAccount(user.getUserAccount());
				item.setItemOperation(ItemOperation.UPDATE);
				
				userAttendanceItems.add(item);
			}
			form.setUserAttendanceItems(userAttendanceItems);
		}
		ViolationItem violation = new ViolationItem();
		
		if (infraction.getConditionViolation() != null) {
			violation.setConditionViolation(infraction.getConditionViolation());
		} else if (infraction.getDisciplinaryCodeViolation() != null) {
			violation.setDisciplinaryCodeViolation(
					infraction.getDisciplinaryCodeViolation());
		}
		if (this.resolutionService.findImposedSanctionByInfraction(infraction)
				!= null) {
			violation.setSanction(this.resolutionService
					.findImposedSanctionByInfraction(
							infraction).getDescription());
		}
		if (infraction.getResolution().getAdjustedCondition() != null
				|| infraction.getResolution().getAdjustedCode() != null) {
			violation.setAdjusted(true);
			violation.setAdjustedCondition(infraction.getResolution()
						.getAdjustedCondition());
			violation.setAdjustedDisciplinaryCode(infraction.getResolution()
						.getAdjustedCode());
		}
		violation.setAuthority(infraction.getResolution().getAuthority());
		violation.setDate(infraction.getResolution().getDate());
		violation.setPlea(infraction.getPlea());
		violation.setDecision(infraction.getResolution().getDecision());
		violation.setDisposition(infraction.getResolution().getDisposition());
		violation.setReason(infraction.getResolution().getReason());
		violation.setAppealDate(infraction.getResolution().getAppealDate());
		violation.setInfraction(infraction);
		List<ViolationItem> items = new ArrayList<ViolationItem>();
		items.add(violation);
		form.setViolationItems(items);
		
		return form;
	}
	
	/**
	 * Processes infractions and related entities for creation/updating as
	 * needed and returns either the violation or the hearing list screen.
	 * 
	 * @param offender - offender
	 * @param hearing - hearing
	 * @param resolutionCategory - resolution classification category
	 * @param form - resolution form
	 * @return Model And View for the violation status list screen, or the
	 * hearings list screen, depending on the type of infractions being made.
	 * @throws DuplicateEntityFoundException 
	 * @throws HearingExistsException - When another Hearing already exists
	 * with the given properties.
	 * @throws HearingStatusExistsException - When another Hearing Status
	 * already exists with the given properties.
	 * @throws InfractionExistsException - When another Infraction already
	 * exists with the given properties.
	 * @throws UserAttendanceExistsException - When a User Attendance
	 * already exists with the given properties.
	 */
	private ModelAndView processInfractions(final Offender offender,
			final Hearing hearing,
			final ResolutionClassificationCategory resolutionCategory,
			final ResolutionForm form)
			throws InfractionExistsException, HearingExistsException,
			HearingStatusExistsException, UserAttendanceExistsException,
			DuplicateEntityFoundException {
		if (ResolutionClassificationCategory.FORMAL
				.equals(resolutionCategory)) {
			this.resolutionService.createHearingStatus(hearing,
					form.getStatusDescription(),
					new Date(), form.getCategory());
			if (!(form.getDate().equals(hearing.getDate()))) {
				this.hearingService.updateHearing(hearing,
						hearing.getLocation(),
						hearing.getSubject().getInAttendance(),
						DateManipulator.getDateAtTimeOfDay(form.getDate(),
								form.getTime()), hearing.getCategory(),
						hearing.getOfficer());
			}
			if (!(form.getInAttendance().equals(
					hearing.getSubject().getInAttendance()))) {
				this.hearingService.updateHearing(hearing,
						hearing.getLocation(), form.getInAttendance(),
						hearing.getDate(), hearing.getCategory(),
						hearing.getOfficer());
			}
			this.processUserAttendanceItems(form.getUserAttendanceItems(),
					hearing);
		}
		for (ViolationItem item : form.getViolationItems()) {
			Resolution resolution = new Resolution();
			String sanctionDescription = null;
			InfractionPlea plea = null;
			Infraction infraction = item.getInfraction();
			ConditionViolation conditionViolation =
					item.getConditionViolation();
			DisciplinaryCodeViolation disciplinaryCodeViolation =
					item.getDisciplinaryCodeViolation();
			if (form.getGroupEdit() != null && form.getGroupEdit()) {
				switch (resolutionCategory) {
					case FORMAL:
						resolution.setDate(hearing.getDate());
						resolution.setAuthority(hearing.getOfficer()
								.getUser());
						resolution.setDisposition(form
							.getViolationItems().get(0).getDisposition());
						if (item.getAdjusted() != null
								&& item.getAdjusted()) {
							if (disciplinaryCodeViolation != null) {
								resolution.setAdjustedCode(
										item.getAdjustedDisciplinaryCode());
							} else if (conditionViolation != null) {
								resolution.setAdjustedCondition(
										item.getAdjustedCondition());
							}
						} else if (item.getAdjusted() == null
								|| !item.getAdjusted()) {
							if (infraction != null
									&& infraction.getResolution() != null
									&& (infraction.getResolution()
											.getAdjustedCode() != null
									|| infraction.getResolution()
									.getAdjustedCondition() != null)) {
								resolution.setAdjustedCode(null);
								resolution.setAdjustedCondition(null);
							}
						}
					case INFORMAL:
						sanctionDescription = form
						.getViolationItems().get(0).getSanction();
					case DISMISSED:
						plea = form.getViolationItems().get(0).getPlea();
						resolution.setCategory(resolutionCategory);
						resolution.setReason(form
								.getViolationItems().get(0).getReason());
						resolution.setAppealDate(form
								.getViolationItems().get(0).getAppealDate());
						if (form.getViolationItems().get(0)
								.getAppealDate() != null) {
							resolution.setAppealDate(form
									.getViolationItems().get(0
											).getAppealDate());
						}
						if (!ResolutionClassificationCategory.FORMAL.equals(
								resolutionCategory)) {
							resolution.setDecision(form
									.getViolationItems().get(0).getDecision());
							resolution.setAuthority(form
									.getViolationItems().get(0).getAuthority());
							resolution.setDate(form
									.getViolationItems().get(0).getDate());
						}
						break;
					default:
						throw new UnsupportedOperationException(
								"Resolution Category Not Supported");
				}
			} else {
				switch (resolutionCategory) {
					case FORMAL:
						resolution.setDate(hearing.getDate());
						resolution.setAuthority(hearing.getOfficer()
								.getUser());
						resolution.setDisposition(item.getDisposition());
						if (item.getAdjusted() != null
								&& item.getAdjusted()) {
							if (disciplinaryCodeViolation != null) {
								resolution.setAdjustedCode(
										item.getAdjustedDisciplinaryCode());
							} else if (conditionViolation != null) {
								resolution.setAdjustedCondition(
										item.getAdjustedCondition());
							}
						} else if (item.getAdjusted() == null
								|| !item.getAdjusted()) {
							if (infraction != null
									&& infraction.getResolution() != null
									&& (infraction.getResolution()
											.getAdjustedCode() != null
									|| infraction.getResolution()
									.getAdjustedCondition() != null)) {
								resolution.setAdjustedCode(null);
								resolution.setAdjustedCondition(null);
							}
						}
					case INFORMAL:
						sanctionDescription = item.getSanction();
					case DISMISSED:
						plea = item.getPlea();
						resolution.setCategory(resolutionCategory);
						resolution.setReason(item.getReason());
						resolution.setAppealDate(item.getAppealDate());
						if (!ResolutionClassificationCategory.FORMAL.equals(
								resolutionCategory)) {
							resolution.setDecision(item.getDecision());
							resolution.setAuthority(item.getAuthority());
							resolution.setDate(item.getDate());
						}
						break;
					default :
						throw new UnsupportedOperationException(
								"Resolution Category Not Supported");
				}
			}
			if (infraction == null) {
				infraction = this.resolutionService.createInfraction(
						hearing, conditionViolation,
						disciplinaryCodeViolation, resolution, plea);
			} else {
				this.resolutionService.updateInfraction(infraction,
						conditionViolation, disciplinaryCodeViolation,
						resolution, plea);
			}
			ImposedSanction imposedSanction = this.resolutionService
					.findImposedSanctionByInfraction(infraction);
			if (imposedSanction != null) {
				if (StringUtility.hasContent(sanctionDescription)) {
					this.resolutionService.updateImposedSanction(
						imposedSanction, offender, sanctionDescription);
				} else {
					this.resolutionService.removeImposedSanction(
							imposedSanction);
				}
			} else {
				if (StringUtility.hasContent(sanctionDescription)) {
					this.resolutionService.createImposedSanction(
						infraction, offender, sanctionDescription);
				}
			}
		}
		switch (resolutionCategory) {
			case INFORMAL:
			case DISMISSED:
				return new ModelAndView(String.format(
						VIOLATIONS_LIST_REDIRECT, offender.getId()));
			case FORMAL:
			default :
				return new ModelAndView(String.format(
						HEARINGS_LIST_REDIRECT, offender.getId()));
		}
	}
	
	/**
	 * Creates, updates, and removes user attendances based on their item
	 * operation.
	 * @param userAttendanceItems - list of userAttendanceItems to be 
	 * processed
	 * @param hearing - hearing for which the items are being processed
	 * @throws UserAttendanceExistsException - when a user attendance already
	 * exists for the hearing
	 */
	private void processUserAttendanceItems(
			final List<UserAttendanceItem> userAttendanceItems,
			final Hearing hearing) throws UserAttendanceExistsException {
		if (userAttendanceItems != null) {
			for (UserAttendanceItem item : userAttendanceItems) {
				if (ItemOperation.CREATE.equals(item.getItemOperation())) {
					this.hearingService.createUserAttendance(hearing,
							item.getUserAccount());
				} else if (ItemOperation.UPDATE.equals(
						item.getItemOperation())) {
					this.hearingService.updateUserAttendance(
							item.getUserAttended(), item.getUserAccount());
				} else if (ItemOperation.REMOVE.equals(
						item.getItemOperation())) {
					this.hearingService.removeUserAttendance(
							item.getUserAttended());
				}
			}
		}
	}
	
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
				ENTITY_EXISTS_MESSAGE_KEY, ERROR_BUNDLE_NAME, exception);
	}
	
	/**
	 * Handles hearing exists exceptions.
	 * 
	 * @param exception hearing exists exception
	 * @return model and view for displaying exception explanation
	 */
	@ExceptionHandler(HearingExistsException.class)
	public ModelAndView handleDuplicateEntityFoundException(
			final HearingExistsException exception) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
				HEARING_EXISTS_MESSAGE_KEY, ERROR_BUNDLE_NAME,  exception);
	}
	
	/**
	 * Handles hearing status exists exceptions.
	 * 
	 * @param exception hearing status exists exception
	 * @return model and view for displaying exception explanation
	 */
	@ExceptionHandler(HearingStatusExistsException.class)
	public ModelAndView handleDuplicateEntityFoundException(
			final HearingStatusExistsException exception) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
				HEARING_STATUS_EXISTS_MESSAGE_KEY, ERROR_BUNDLE_NAME,
					exception);
	}
	
	/**
	 * Handles infraction exists exceptions.
	 * 
	 * @param exception infraction exists exception
	 * @return model and view for displaying exception explanation
	 */
	@ExceptionHandler(InfractionExistsException.class)
	public ModelAndView handleDuplicateEntityFoundException(
			final InfractionExistsException exception) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
				INFRACTION_EXISTS_MESSAGE_KEY, ERROR_BUNDLE_NAME,  exception);
	}

	/**
	 * Handles user attendance exists exceptions.
	 * 
	 * @param exception user attendance exists exception
	 * @return model and view for displaying exception explanation
	 */
	@ExceptionHandler(UserAttendanceExistsException.class)
	public ModelAndView handleDuplicateEntityFoundException(
			final UserAttendanceExistsException exception) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
				USER_ATTENDANCE_EXISTS_MESSAGE_KEY, ERROR_BUNDLE_NAME,
				exception);
	}
	
	/* InitBinder */
	
	/**
	 * Sets up and registers property editors.
	 * 
	 * @param binder - web binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(
				Offender.class,
				this.offenderPropertyEditorFactory
				.createOffenderPropertyEditor());
		binder.registerCustomEditor(Date.class, this.customDateEditorFactory
				.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(
				DisciplinaryCodeViolation.class,
				this.disciplinaryCodeViolationPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				ConditionViolation.class,
				this.conditionViolationPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				Infraction.class,
				this.infractionPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				InfractionPlea.class,
				this.infractionPleaPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				Hearing.class, this.hearingPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				Person.class,
				this.personPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				UserAttendance.class, this.userAttendancePropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				UserAccount.class,
				this.userAccountPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(Date.class, "time", 
				this.customDateEditorFactory.createCustomTimeOnlyEditor(true));
		binder.registerCustomEditor(
				DisciplinaryCode.class,
				this.disciplinaryCodePropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				Condition.class,
				this.conditionPropertyEditorFactory
				.createPropertyEditor());
	}
}
