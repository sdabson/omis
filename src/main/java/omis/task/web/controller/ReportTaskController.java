package omis.task.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import omis.beans.factory.PropertyEditorFactory;
import omis.task.domain.Task;
import omis.task.report.TaskReportService;
import omis.task.report.TaskSummary;

/**
 * Controller to report tasks.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Controller
@RequestMapping(value = "/task")
@PreAuthorize("hasRole('USER')")
public class ReportTaskController {
	
	/* Views. */
	
	private static final String VIEW_NAME = "task/list";
	
	private static final String ACTION_MENU_VIEW_NAME
		= "task/includes/tasksActionMenu";
	
	/* Model keys. */
	
	private static final String TASK_SUMMAIRES_MODEL_KEY = "taskSummaries";

	private static final String TASK_MODEL_KEY = "task";
	
	/* Report service. */
	
	@Autowired
	@Qualifier("taskReportService")
	private TaskReportService taskReportService;
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("taskPropertyEditorFactory")
	private PropertyEditorFactory taskPropertyEditorFactory;
	
	/* Constructors. */
	
	/** Instantiates controller to report tasks. */
	public ReportTaskController() {
		// Default instantiation
	}
	
	/* URL invokable methods. */
	
	/**
	 * Shows tasks.
	 * 
	 * @return tasks
	 */
	@PreAuthorize("hasRole('TASK_LIST') or hasRole('ADMIN')")
	@RequestMapping("/list.html")
	public ModelAndView list() {
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		List<TaskSummary> taskSummaries
			= this.taskReportService.summarizeBySourceAccountUsername(
				username);
		ModelAndView mav = new ModelAndView(VIEW_NAME);
		mav.addObject(TASK_SUMMAIRES_MODEL_KEY, taskSummaries);
		return mav;
	}
	
	/* Action menus. */
	
	/**
	 * Shows action menu.
	 * 
	 * @param task task
	 * @return action menu
	 */
	@RequestMapping("/tasksActionMenu.html")
	public ModelAndView showActionMenu(
			@RequestParam(value = "task", required = true)
				final Task task) {
		ModelAndView mav = new ModelAndView(ACTION_MENU_VIEW_NAME);
		mav.addObject(TASK_MODEL_KEY, task);
		return mav;
	}
	
	/* Helper methods. */
	
	/**
	 * Registers property editor factories.
	 * 
	 * @param binder binder
	 */
	@InitBinder
	protected void registerPropertyEditors(final WebDataBinder binder) {
		binder.registerCustomEditor(Task.class, this.taskPropertyEditorFactory
				.createPropertyEditor());
	}
}