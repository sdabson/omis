package omis.schedule.web.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


/**
 * Controller for event scheduler.
 * 
 * <p>This controller handles requests for functionality related to the event
 * scheduler such as reading an a list of events ({@code events()}) and
 * creating, reading, updating and deleting events. The controller should also
 * be used to display forms for manipulating scheduled events and output for
 * processes such as validation and notification.
 * 
 * @author Stephen Abson
 * @version 0.1.2 (Sep 21, 2012)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/schedule")
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
public class EventSchedulerController {
	
	/**
	 * Returns a model and view to display a recurring schedule builder
	 * properties. A builder type must be specified, from this it is determined
	 * what type of recurring schedule should be built (daily, weekly, monthly).
	 * 
	 * @param builderType type of recurring schedule builder form
	 * @return model and view to display a recurring schedule builder
	 */
	@RequestMapping(value = "/showRecurringBuilderForm.html")
	public ModelAndView showRecurringBuilderForm(
			@RequestParam(value = "builderType", required = true)
				final String builderType) {
		ModelAndView mav = new ModelAndView(
				"schedule/recurring-schedule-builder");
		RecurringScheduleBuilderForm recurringScheduleBuilderForm =
				new RecurringScheduleBuilderForm();
		if ("daily".equals(builderType)) {
			recurringScheduleBuilderForm.setRecurringScheduleBuilderTypeForm(
					new DailyRecurringScheduleBuilderTypeForm());
		} else if ("weekly".equals(builderType)) {
			recurringScheduleBuilderForm.setRecurringScheduleBuilderTypeForm(
					new WeeklyRecurringScheduleBuilderTypeForm());
		} else if ("monthly".equals(builderType)) {
			recurringScheduleBuilderForm.setRecurringScheduleBuilderTypeForm(
					new MonthlyRecurringScheduleBuilderTypeForm());
		} else {
			throw new IllegalArgumentException(
					"Unknown builder type: " + builderType);
		}
		
		// Set the builderType form property in the most vulgar possible manner
		recurringScheduleBuilderForm.setBuilderType(builderType);
		
		mav.addObject("recurringScheduleBuilderForm",
				recurringScheduleBuilderForm);
		return mav;
	}
	
	/**
	 * Outputs options for building recurring schedule. 
	 * @param builderType builder type
	 * @return model and view which displays options for building recurring
	 * schedule
	 */
	@RequestMapping(value = "/getRecurringScheduleBuilderTypeOptions.html")
	public ModelAndView getRecurringScheduleBuilderTypeOptions(
			@RequestParam(value = "builderType", required = true)
				final String builderType) {
		if (!"daily".equals(builderType) && !"weekly".equals(builderType)
				&& !"monthly".equals(builderType)) {
			throw new IllegalArgumentException(
					"Unknown builder type: " + builderType);
		}
		ModelAndView mav = new ModelAndView(
				"schedule/recurring-schedule-builder-type");
		mav.addObject("builderType", builderType);
		return mav;
	}
}