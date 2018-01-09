package omis.health.web.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.content.RequestContentMapping;
import omis.content.RequestContentType;
import omis.datatype.DateRange;
import omis.datatype.DayOfWeek;
import omis.exception.BusinessException;
import omis.health.domain.IrregularScheduleDay;
import omis.health.domain.ProviderAssignment;
import omis.health.domain.ProviderSchedule;
import omis.health.service.ProviderScheduleManager;
import omis.health.validator.ProviderScheduleFormValidator;
import omis.health.web.controller.delegate.ProviderScheduleDelegate;
import omis.health.web.form.InternalProviderScheduleDayItem;
import omis.health.web.form.IrregularScheduleDayItemForm;
import omis.health.web.form.ProviderScheduleForm;

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

/**
 * Controller for provider schedules.
 *
 * @author Stephen Abson
 * @author Ryan Johns
 * @version 0.1.0 (Apr 22, 2014)
 * @since OMIS 3.0
 */
@Controller
@PreAuthorize("hasRole('ADMIN')"
		+ " or (hasRole('USER') and hasRole('HEALTH_MODULE'))")
@RequestMapping("/health/provider/schedule")
public class ProviderScheduleController {

	/* View names. */

	private static final String INTERNAL_BODY_CONTENT_VIEW_NAME
		= "health/provider/schedule/internal/includes/weeklyTable";

	private static final String IRREGULAR_SCHEDULE_DAY_ITEM_VIEW_NAME
		= "health/provider/schedule/internal/includes/"
		+ "irregularScheduleTableBodyContent";

	private static final String IRREGULAR_SCHEDULE_DAY_ITEMS_VIEW_NAME
	= "health/provider/schedule/internal/includes/irregularScheduleTableBodyRows";

	private static final String PROVIDER_SCHEDULE_EDIT_VIEW_NAME =
			"health/provider/schedule/internal/edit";

	private static final String PROVIDER_LIST_REDIRECT_NAME =
			"redirect:/health/provider/list.html?facility=%1$d";

	/* Model keys. */

	private static final String END_DATE_MODEL_KEY = "endDate";

	private static final String FACILITY_MODEL_KEY = "facility";

	private static final String INDEX_MODEL_KEY = "mindex";
	
	private static final String DAYS_INDEX_MODEL_KEY = "index";

	private static final String INTERNAL_PROVIDER_SCHEDULE_DAY_ITEMS_MODEL_KEY
		= "internalProviderScheduleDayItems";

	private static final String IRREGULAR_SCHEDULE_DAY_ITEM_FORM_KEY
	    = "irregularScheduleDayItem";

	private static final String IRREGULAR_SCHEDULE_DAY_ITEMS_FORM_KEY =
			"irregularScheduleDays";

	private static final String PROVIDER_ASSIGNMENT_KEY
		= "providerAssignment";

	private static final String PROVIDER_SCHEDULE_FORM_KEY = "providerSchedule";

	private static final String SCHEDULE_DATE_MODEL_KEY = "scheduleDate";

	private static final String START_DATE_MODEL_KEY = "startDate";

	/* Property editor factories. */

	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;

	@Autowired
	private PropertyEditorFactory irregularScheduleDayPropertyEditorFactory;

	@Autowired
	@Qualifier("providerAssignmentPropertyEditorFactory")
	private PropertyEditorFactory providerAssignmentPropertyEditorFactory;

	/* Validators. */
	@Autowired
	private ProviderScheduleFormValidator providerScheduleFormValidator;

	/* Helpers. */

	@Autowired
	@Qualifier("providerScheduleDelegate")
	private ProviderScheduleDelegate providerScheduleDelegate;

	@Autowired
	private ProviderScheduleManager providerScheduleManager;

	/* Constructors. */

	/** Instantiates controller for provider schedules. */
	public ProviderScheduleController() {
		// Default instantiation
	}

	/** Creates a new  provider weekly schedule.
	 * @param providerAssignment provider assignment to schedule.
	 * @return create provider schedule view. */
	@RequestContentMapping(nameKey = "createProviderScheduleScreenName",
			descriptionKey = "createProviderScheduleScreenDescription",
			messageBundle = "omis.health.msgs.health",
			screenType = RequestContentType.DETAIL_SCREEN)
	@RequestMapping(value = "/internal/create.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('PROVIDER_SCHEDULE_CREATE')")
	public ModelAndView create(
			@RequestParam(value = "providerAssignment", required = true)
			final ProviderAssignment providerAssignment) {
		final ModelMap map = new ModelMap();

		map.put(FACILITY_MODEL_KEY, providerAssignment.getFacility());
		map.put(PROVIDER_ASSIGNMENT_KEY, providerAssignment);
		map.put(PROVIDER_SCHEDULE_FORM_KEY, new ProviderScheduleForm());

		return new ModelAndView(PROVIDER_SCHEDULE_EDIT_VIEW_NAME, map);
	}

	/** Saves a new provider weekly schedule.
	 * @param providerAssignment provider assignment to schedule.
	 * @param providerScheduleForm daily schedule.
	 * @return provider listing view. */
	@RequestContentMapping(nameKey = "saveProviderScheduleScreenName",
			descriptionKey = "saveProviderScheduleScreenDescription",
			messageBundle = "omis.health.msgs.health",
			screenType = RequestContentType.LISTING_SCREEN)
	@RequestMapping(value = {"/internal/create.html", "/internal/edit.html" },
		method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN') or hasRole('PROVIDER_SCHEDULE_SAVE')")
	public ModelAndView saveOrUpdate(
			@RequestParam(value = "providerAssignment", required = true)
			final ProviderAssignment providerAssignment,
			final ProviderScheduleForm providerScheduleForm,
			final BindingResult result)
					throws BusinessException {
		final ModelAndView mav;

		this.providerScheduleFormValidator.validate(providerScheduleForm,
				result);

		if (result.hasErrors()) {
			mav = this.prepareRedisplayMav(providerScheduleForm,
					providerAssignment, result);
		} else {
			this.scheduleFromForm(providerScheduleForm, providerAssignment);
			this.providerScheduleManager.saveIrregularScheduleDayItems(
					providerAssignment,
					providerScheduleForm.getIrregularScheduleDays());

			mav = new ModelAndView(String.format(PROVIDER_LIST_REDIRECT_NAME,
					providerAssignment.getFacility().getId()));
		}

		return mav;
	}

	/** Edits an existing provider weekly schedule.
	 * @param providerAssignment provider assignment.
	 * @return edit provider schedule view. */
	@RequestContentMapping(nameKey = "editProviderScheduleScreenName",
			descriptionKey = "editProviderScheduleScreenDescription",
			messageBundle = "omis.health.msgs.health",
			screenType = RequestContentType.DETAIL_SCREEN)
	@RequestMapping(value = "/internal/edit.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('PROVIDER_SCHEDULE_EDIT')")
	public ModelAndView edit(
			@RequestParam(value = "providerAssignment", required = true)
			final ProviderAssignment providerAssignment,
			@RequestParam(value = "startDate", required = false)
			final Date startDate,
			@RequestParam(value = "endDate", required = false)
			final Date endDate) {
		final ProviderScheduleForm providerScheduleForm =
				new ProviderScheduleForm();
		final DateRange  dateRange =  new DateRange(startDate,endDate);

		if (dateRange.getStartDate() == null) {
			final Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_WEEK, -(cal.get(Calendar.DAY_OF_WEEK)-1));
			dateRange.setStartDate(cal.getTime());
		}

		this.formFromSchedule(providerScheduleForm, providerAssignment);
		final ModelMap map = new ModelMap();
		providerScheduleForm.setIrregularScheduleDays(
				this.formFromIrregularDaySchedules(this.providerScheduleManager
				.findIrregularScheduleDaysByProviderAndDateRange(
						providerAssignment,dateRange.getStartDate() ,
						dateRange.getEndDate())));

		map.put(FACILITY_MODEL_KEY, providerAssignment.getFacility());
		map.put(PROVIDER_ASSIGNMENT_KEY, providerAssignment);
		map.put(PROVIDER_SCHEDULE_FORM_KEY, providerScheduleForm);
		map.put(START_DATE_MODEL_KEY, dateRange.getStartDate());
		map.put(END_DATE_MODEL_KEY, dateRange.getEndDate());

		return new ModelAndView(PROVIDER_SCHEDULE_EDIT_VIEW_NAME, map);
	}

	/* AJAX invokable methods. */

	/**
	 * Returns internal weekly schedule for assigned on date.
	 *
	 * @param providerAssignment provider assignment
	 * @param scheduleDate date
	 * @return content of internal weekly schedule
	 */
	@RequestMapping(value = "/internal/showWeekly.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')"
			+ " or hasRole('HEALTH_ADMIN')"
			+ " or hasRole('HEALTH_PROVIDER_SCHEDULE_VIEW')")
	public ModelAndView showInternalWeekly(
			@RequestParam(value = "providerAssignment", required = false)
				final ProviderAssignment providerAssignment,
			@RequestParam(value = "scheduleDate", required = true)
				final Date scheduleDate) {
		final DateRange dateRange = DateRange.findWeeklyRange(scheduleDate);
		final List<InternalProviderScheduleDayItem>
		internalProviderScheduleDayItems
			= this.providerScheduleDelegate
				.findInternalProviderScheduleDayItems(
						providerAssignment, dateRange);
		final ModelAndView mav =
				new ModelAndView(INTERNAL_BODY_CONTENT_VIEW_NAME);
		mav.addObject(INTERNAL_PROVIDER_SCHEDULE_DAY_ITEMS_MODEL_KEY,
				internalProviderScheduleDayItems);
		mav.addObject(START_DATE_MODEL_KEY, dateRange.getStartDate());
		mav.addObject(END_DATE_MODEL_KEY, dateRange.getEndDate());
		mav.addObject(SCHEDULE_DATE_MODEL_KEY, scheduleDate);
		return mav;
	}

	/** Returns irregular schedule day item.
	 * @param index index.
	 * @return irregular schedule day item view.  */
	@RequestContentMapping(nameKey = "addIrregularScheduleDayItemName",
			descriptionKey = "addIrregularScheduleDayItemDescription",
			messageBundle = "omis.health.msgs.health",
			screenType = RequestContentType.AJAX_REQUEST)
	@RequestMapping(value = "/internal/addIrregularScheduleDay.html",
		method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('PROVIDER_SCHEDULE_EDIT')")
	public ModelAndView addIrregularScheduleDayItem(
			@RequestParam(value = "index", required = true) final
			Integer index) {
		final ModelMap map = new ModelMap();
		final IrregularScheduleDayItemForm irregularScheduleDayItemForm
			= new IrregularScheduleDayItemForm();

		map.put(INDEX_MODEL_KEY, index);
		map.addAttribute(
				IRREGULAR_SCHEDULE_DAY_ITEM_FORM_KEY,
				irregularScheduleDayItemForm);
		return new ModelAndView(IRREGULAR_SCHEDULE_DAY_ITEM_VIEW_NAME,map);
	}

	/** Returns irregular schedule day item.
	 * @param index index.
	 * @return irregular schedule day item view.  */
	@RequestContentMapping(nameKey = "addIrregularScheduleDayItemName",
			descriptionKey = "addIrregularScheduleDayItemDescription",
			messageBundle = "omis.health.msgs.health",
			screenType = RequestContentType.AJAX_REQUEST)
	@RequestMapping(value = "/internal/addIrregularScheduleDays.html",
		method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('PROVIDER_SCHEDULE_EDIT')")
	public ModelAndView addIrregularScheduleDayItems(
			@RequestParam(value = "index", required = true) final
			Integer index,
			@RequestParam(value = "startDate", required = true) final Date startDate,
			@RequestParam(value = "endDate", required = true) final Date endDate) {
		final ModelMap map = new ModelMap();

		final List<IrregularScheduleDayItemForm> days =
				new ArrayList<IrregularScheduleDayItemForm>();

		final Calendar start = Calendar.getInstance();
		start.setTime(startDate);
		final Calendar end = Calendar.getInstance();
		end.setTime(endDate);

		while (!start.after(end)) {
			final IrregularScheduleDayItemForm irregularScheduleDay =
					new IrregularScheduleDayItemForm();

			irregularScheduleDay.setDay(start.getTime());
			days.add(irregularScheduleDay);
			start.add(Calendar.DATE, 1);
		}


		map.put(DAYS_INDEX_MODEL_KEY, index);
		map.addAttribute(
				IRREGULAR_SCHEDULE_DAY_ITEMS_FORM_KEY,
				days);
		return new ModelAndView(IRREGULAR_SCHEDULE_DAY_ITEMS_VIEW_NAME,map);
	}


	private void scheduleFromForm(
			final ProviderScheduleForm providerScheduleForm,
			final ProviderAssignment providerAssignment) {
		this.providerScheduleManager.schedule(providerAssignment,
				DayOfWeek.MONDAY, new DateRange(
						providerScheduleForm.getMondayStartTime(),
						providerScheduleForm.getMondayEndTime()));
		this.providerScheduleManager.schedule(providerAssignment,
				DayOfWeek.TUESDAY, new DateRange(
						providerScheduleForm.getTuesdayStartTime(),
						providerScheduleForm.getTuesdayEndTime()));
		this.providerScheduleManager.schedule(providerAssignment,
				DayOfWeek.WEDNESDAY, new DateRange(
						providerScheduleForm.getWednesdayStartTime(),
						providerScheduleForm.getWednesdayEndTime()));
		this.providerScheduleManager.schedule(providerAssignment,
				DayOfWeek.THURSDAY, new DateRange(
						providerScheduleForm.getThursdayStartTime(),
						providerScheduleForm.getThursdayEndTime()));
		this.providerScheduleManager.schedule(providerAssignment,
				DayOfWeek.FRIDAY, new DateRange(
						providerScheduleForm.getFridayStartTime(),
						providerScheduleForm.getFridayEndTime()));
		this.providerScheduleManager.schedule(providerAssignment,
				DayOfWeek.SATURDAY, new DateRange(
						providerScheduleForm.getSaturdayStartTime(),
						providerScheduleForm.getSaturdayEndTime()));
		this.providerScheduleManager.schedule(providerAssignment,
				DayOfWeek.SUNDAY, new DateRange(
						providerScheduleForm.getSundayStartTime(),
						providerScheduleForm.getSundayEndTime()));
	}

	private void formFromSchedule(
			final ProviderScheduleForm providerScheduleForm,
			final ProviderAssignment providerAssignment) {
		final ProviderSchedule providerSchedule =
				this.providerScheduleManager
				.findRegularScheduleByProvider(providerAssignment);
		if (providerSchedule != null) {
			if (providerSchedule.getSundayTimeRange() != null) {
				if (providerSchedule.getSundayTimeRange().getStartDate() != null) {
					providerScheduleForm.setSundayStartTime(providerSchedule
						.getSundayTimeRange().getStartDate());
				}
				if (providerSchedule.getSundayTimeRange().getEndDate() != null) {
					providerScheduleForm.setSundayEndTime(providerSchedule
						.getSundayTimeRange().getEndDate());
				}
			}
			if (providerSchedule.getMondayTimeRange() != null) {
				if (providerSchedule.getMondayTimeRange().getStartDate() != null) {
					providerScheduleForm.setMondayStartTime(providerSchedule
							.getMondayTimeRange().getStartDate());
				}
				if (providerSchedule.getMondayTimeRange().getEndDate() != null) {
					providerScheduleForm.setMondayEndTime(providerSchedule
							.getMondayTimeRange().getEndDate());
				}
			}
			if (providerSchedule.getTuesdayTimeRange() != null) {
				if (providerSchedule.getTuesdayTimeRange().getStartDate() != null) {
					providerScheduleForm.setTuesdayStartTime(providerSchedule
							.getTuesdayTimeRange().getStartDate());
				}
				if (providerSchedule.getTuesdayTimeRange().getEndDate() != null) {
					providerScheduleForm.setTuesdayEndTime(providerSchedule
							.getTuesdayTimeRange().getEndDate());
				}
			}
			if (providerSchedule.getWednesdayTimeRange() != null) {
				if (providerSchedule.getWednesdayTimeRange().getStartDate() != null)
				{
					providerScheduleForm.setWednesdayStartTime(providerSchedule
							.getWednesdayTimeRange().getStartDate());
				}

				if (providerSchedule.getWednesdayTimeRange().getEndDate() != null) {
					providerScheduleForm.setWednesdayEndTime(providerSchedule
							.getWednesdayTimeRange().getEndDate());
				}
			}
			if (providerSchedule.getThursdayTimeRange() != null) {
				if (providerSchedule.getThursdayTimeRange().getStartDate() != null)
				{
					providerScheduleForm.setThursdayStartTime(providerSchedule
							.getThursdayTimeRange().getStartDate());
				}
				if (providerSchedule.getThursdayTimeRange().getEndDate() != null) {
					providerScheduleForm.setThursdayEndTime(providerSchedule
							.getThursdayTimeRange().getEndDate());
				}
			}
			if (providerSchedule.getFridayTimeRange() != null) {
				if (providerSchedule.getFridayTimeRange().getStartDate() != null) {
					providerScheduleForm.setFridayStartTime(providerSchedule
							.getFridayTimeRange().getStartDate());
				}
				if (providerSchedule.getFridayTimeRange().getEndDate() != null) {
					providerScheduleForm.setFridayEndTime(providerSchedule
							.getFridayTimeRange().getEndDate());
				}
			}
			if (providerSchedule.getSaturdayTimeRange() != null) {
				if (providerSchedule.getSaturdayTimeRange().getStartDate() != null) {
					providerScheduleForm.setSaturdayStartTime(providerSchedule
							.getSaturdayTimeRange().getStartDate());
				}
				if (providerSchedule.getSaturdayTimeRange().getEndDate() != null) {
					providerScheduleForm.setSaturdayEndTime(providerSchedule
							.getSaturdayTimeRange().getEndDate());
				}
			}
		}
	}

	private List<IrregularScheduleDayItemForm> formFromIrregularDaySchedules(
			final List<IrregularScheduleDay> irregularScheduleDays) {
		final List<IrregularScheduleDayItemForm> list =
				new ArrayList<IrregularScheduleDayItemForm>();

		final Iterator<IrregularScheduleDay> i =
				irregularScheduleDays.iterator();

		while(i.hasNext()) {
			list.add(this.formFromIrregularDaySchedule(i.next()));
		}

		return list;
	}

	private IrregularScheduleDayItemForm formFromIrregularDaySchedule(
			final IrregularScheduleDay irregularScheduleDay) {
		final IrregularScheduleDayItemForm irregularScheduleDayItemForm =
				new IrregularScheduleDayItemForm();

		irregularScheduleDayItemForm.setDay(irregularScheduleDay.getDay());
		irregularScheduleDayItemForm.setIrregularScheduleDayItem(
				irregularScheduleDay);
		if (irregularScheduleDay.getTimeRange() != null) {
			irregularScheduleDayItemForm.setStartTime(irregularScheduleDay
					.getTimeRange().getStartDate());

			irregularScheduleDayItemForm.setEndTime(irregularScheduleDay
					.getTimeRange().getEndDate());
		}

		return irregularScheduleDayItemForm;
	}

	private ModelAndView prepareRedisplayMav(
			final ProviderScheduleForm providerScheduleForm,
			final ProviderAssignment providerAssignment,
			final BindingResult result) {
		final ModelMap map = new ModelMap();

		map.put(FACILITY_MODEL_KEY, providerAssignment.getFacility());
		map.put(PROVIDER_ASSIGNMENT_KEY, providerAssignment);
		map.put(PROVIDER_SCHEDULE_FORM_KEY, providerScheduleForm);
		map.put(BindingResult.MODEL_KEY_PREFIX + PROVIDER_SCHEDULE_FORM_KEY,
				result);

		return new ModelAndView(PROVIDER_SCHEDULE_EDIT_VIEW_NAME, map);

	}


	/* Init binder. */

	/**
	 * Binds property editors.
	 *
	 * @param binder binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(Date.class,
				this.customDateEditorFactory.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(ProviderAssignment.class,
				this.providerAssignmentPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(Date.class, "sundayStartTime",
				this.customDateEditorFactory.createCustomTimeOnlyEditor(true));
		binder.registerCustomEditor(Date.class, "mondayStartTime",
				this.customDateEditorFactory.createCustomTimeOnlyEditor(true));
		binder.registerCustomEditor(Date.class, "tuesdayStartTime",
				this.customDateEditorFactory.createCustomTimeOnlyEditor(true));
		binder.registerCustomEditor(Date.class, "wednesdayStartTime",
				this.customDateEditorFactory.createCustomTimeOnlyEditor(true));
		binder.registerCustomEditor(Date.class, "thursdayStartTime",
				this.customDateEditorFactory.createCustomTimeOnlyEditor(true));
		binder.registerCustomEditor(Date.class, "fridayStartTime",
				this.customDateEditorFactory.createCustomTimeOnlyEditor(true));
		binder.registerCustomEditor(Date.class, "saturdayStartTime",
				this.customDateEditorFactory.createCustomTimeOnlyEditor(true));
		binder.registerCustomEditor(Date.class, "sundayEndTime",
				this.customDateEditorFactory.createCustomTimeOnlyEditor(true));
		binder.registerCustomEditor(Date.class, "mondayEndTime",
				this.customDateEditorFactory.createCustomTimeOnlyEditor(true));
		binder.registerCustomEditor(Date.class, "tuesdayEndTime",
				this.customDateEditorFactory.createCustomTimeOnlyEditor(true));
		binder.registerCustomEditor(Date.class, "wednesdayEndTime",
				this.customDateEditorFactory.createCustomTimeOnlyEditor(true));
		binder.registerCustomEditor(Date.class, "thursdayEndTime",
				this.customDateEditorFactory.createCustomTimeOnlyEditor(true));
		binder.registerCustomEditor(Date.class, "fridayEndTime",
				this.customDateEditorFactory.createCustomTimeOnlyEditor(true));
		binder.registerCustomEditor(Date.class, "saturdayEndTime",
				this.customDateEditorFactory.createCustomTimeOnlyEditor(true));
		binder.registerCustomEditor(Date.class,
				"irregularScheduleDays.startTime",
				this.customDateEditorFactory.createCustomTimeOnlyEditor(true));
		binder.registerCustomEditor(Date.class,
				"irregularScheduleDays.endTime",
				this.customDateEditorFactory.createCustomTimeOnlyEditor(true));
		binder.registerCustomEditor(Date.class,
				"irregularScheduleDays.day",
				this.customDateEditorFactory.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(IrregularScheduleDay.class,
				"irregularScheduleDays.irregularScheduleDayItem",
				this.irregularScheduleDayPropertyEditorFactory
				.createPropertyEditor());
	}
}