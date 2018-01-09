package omis.task.web.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import omis.task.web.form.TaskAssignmentItem;
import omis.task.web.form.TaskItemOperation;

/**
 * TaskFieldsController.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jun 14, 2017)
 *@since OMIS 3.0
 *
 */
@Controller
@PreAuthorize("hasRole('USER')")
@RequestMapping("/task")
public class TaskFieldsController {
	
	/* View Names */
	
	private static final String TASK_ASSIGNMENT_ITEMS_ACTION_MENU_VIEW_NAME =
			"/task/includes/taskAssignmentItemsActionMenu";
	
	private static final String TASK_ASSIGNMENT_ITEM_ROW_VIEW_NAME = 
			"/task/includes/taskAssignmentItemTableRow";
	
	/* Model Keys */
	
	private static final String TASK_ASSIGNMENT_ITEM_MODEL_KEY =
			"taskAssignmentItem";
	
	private static final String TASK_ASSIGNMENT_ITEM_INDEX_MODEL_KEY =
			"taskAssignmentItemIndex";

	private static final String TASK_FIELDS_PROPERTY_NAME_MODEL_KEY =
			"taskFieldsPropertyName";
	
	/**
	 * Default Constructor for TaskFieldsController
	 */
	public TaskFieldsController() {
	}
	
	/**
	 * Displays the TaskAssignmentItem Row View
	 * @param taskAssignmentItemIndex - Integer
	 * @param taskFieldsPropertyName - String
	 * @return ModelAndView - TaskAssignmentItem Row View
	 */
	@RequestMapping(value = "createTaskAssignmentItem.html",
			method = RequestMethod.GET)
	public ModelAndView displayTaskAssignmentItem(@RequestParam(
			value = "taskAssignmentItemIndex", required = true)
				final Integer taskAssignmentItemIndex,
			@RequestParam(value = "taskFieldsPropertyName", required = true)
				final String taskFieldsPropertyName){
		ModelMap map = new ModelMap();
		TaskAssignmentItem item = new TaskAssignmentItem();
		item.setTaskItemOperation(TaskItemOperation.CREATE);
		map.addAttribute(TASK_ASSIGNMENT_ITEM_MODEL_KEY, item);
		map.addAttribute(TASK_ASSIGNMENT_ITEM_INDEX_MODEL_KEY,
				taskAssignmentItemIndex);
		map.addAttribute(TASK_FIELDS_PROPERTY_NAME_MODEL_KEY,
				taskFieldsPropertyName);
		return new ModelAndView(TASK_ASSIGNMENT_ITEM_ROW_VIEW_NAME, map);
	}
	
	/**
	 * Returns the TaskAssignmentItems Action Menu View
	 * @return ModelAndView - TaskAssignmentItems Action Menu View
	 */
	@RequestMapping(value="/taskAssignmentItemsActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayPsychologicalSectionSummaryNoteItemsActionMenu(){
		return new ModelAndView(TASK_ASSIGNMENT_ITEMS_ACTION_MENU_VIEW_NAME);
	}
}
