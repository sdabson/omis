package omis.schedule.web.controller;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.person.domain.Person;
import omis.schedule.domain.TimeBlock;
import omis.schedule.service.TimeBlockService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for time blocks.
 * @author Stephen Abson
 * @version 0.1.0 (Apr 6, 2013)
 * @since OMIS 3.0
 */
@Controller
@PreAuthorize(
		"(hasRole('USER') and hasRole('SCHEDULE_MODULE')) or hasRole('ADMIN')")
@RequestMapping("/schedule/timeBlock")
public class TimeBlockController {
	
	/* Services. */
	
	@Autowired
	private TimeBlockService timeBlockService;
	
	/* Property editor factories. */
	
	@Autowired
	private CustomDateEditorFactory customDateEditorFactory;
	
	@Autowired
	private PropertyEditorFactory personPropertyEditorFactory;
	
	/** Instantiates a default time block controller. */
	public TimeBlockController() {
		// Default instantiation
	}

	/**
	 * Returns a drop down list of free time blocks for the attendees on
	 * the specified date.
	 * 
	 * @param date date
	 * @param attendees attendees
	 * @return model and view to show list of free time blocks for attendees
	 * on date
	 */
	@RequestMapping("/findFreeForAttendeesOnDate.html")
	public ModelAndView findFreeForAttendeesOnDate(
			@RequestParam (value = "date", required = true)
				final Date date,
			@RequestParam (value = "attendee", required = true)
				final Collection<Person> attendees) {
		List<TimeBlock> timeBlocks =
				this.timeBlockService.findFreeTimeBlocksForAttendeesOnDate(
						date, attendees.toArray(new Person[] { }));
		ModelAndView mav = new ModelAndView(
				"schedule/timeBlock/includes/selectOptions");
		mav.addObject("timeBlocks", timeBlocks);
		return mav;
	}
	
	/**
	 * Initializes and binds property editors.
	 * 
	 * @param binder web binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(Date.class,
				this.customDateEditorFactory.createCustomDateOnlyEditor(false));
		binder.registerCustomEditor(Person.class, 
				this.personPropertyEditorFactory.createPropertyEditor());
	}
}