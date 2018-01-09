package omis.staff.web.controller;

import java.util.List;

import omis.beans.factory.PropertyEditorFactory;
import omis.exception.DuplicateEntityFoundException;
import omis.staff.domain.StaffTitle;
import omis.staff.service.StaffTitleService;
import omis.staff.web.form.StaffTitleForm;
import omis.staff.web.validator.StaffTitleFormValidator;

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

/**
 * Controller for staff titles.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Mar 6, 2014)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/staff/title")
@PreAuthorize("hasRole('STAFF_MODULE') or hasRole('ADMIN')")
public class StaffTitleController {

	/* View names. */
	
	private static final String LIST_VIEW_NAME = "staff/title/list";
	
	private static final String EDIT_VIEW_NAME = "staff/title/edit";

	/* Redirects. */
	
	private static final String LIST_REDIRECT
		= "redirect:/staff/title/list.html";
	
	/* Model keys. */
	
	private static final String STAFF_TITLES_MODEL_KEY = "staffTitles";

	private static final String STAFF_TITLE_FORM_MODEL_KEY = "staffTitleForm";
	
	private static final String STAFF_TITLE_MODEL_KEY = "staffTitle";
	
	/* Services. */
	
	@Autowired
	@Qualifier("staffTitleService")
	private StaffTitleService staffTitleService;

	/* Property editor factories. */
	
	@Autowired
	@Qualifier("staffTitlePropertyEditorFactory")
	private PropertyEditorFactory staffTitlePropertyEditorFactory;
	
	/* Validator. */
	
	@Autowired
	@Qualifier("staffTitleFormValidator")
	private StaffTitleFormValidator staffTitleFormValidator;
	
	/* Constructors. */
	
	/** Instantiates a controller for staff titles. */
	public StaffTitleController() {
		// Default instantiation
	}
	
	/* URL invokable methods. */
	
	/**
	 * Shows screen to list staff titles.
	 * 
	 * @return screen to list staff titles
	 */
	@PreAuthorize("hasRole('STAFF_TITLE_LIST') or hasRole('ADMIN')")
	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView(LIST_VIEW_NAME);
		List<StaffTitle> staffTitles = this.staffTitleService.findAll();
		mav.addObject(STAFF_TITLES_MODEL_KEY, staffTitles);
		return mav;
	}
	
	/**
	 * Shows screen to create new staff title.
	 * 
	 * @return screen to create new staff title
	 */
	@PreAuthorize("hasRole('STAFF_TITLE_CREATE') or hasRole('ADMIN')")
	@RequestMapping(value = "/create.html", method = RequestMethod.GET)
	public ModelAndView create() {
		StaffTitleForm staffTitleForm = new StaffTitleForm();
		short nextSortOrder = (short)
				(this.staffTitleService.findHighestSortOrder() + 1);
		staffTitleForm.setSortOrder(nextSortOrder);
		staffTitleForm.setValid(true);
		return this.prepareEditMav(staffTitleForm);
	}

	/**
	 * Shows screen to update existing staff title.
	 * 
	 * @param staffTitle staff title
	 * @return screen to update existing staff title
	 */
	@PreAuthorize("hasRole('STAFF_TITLE_VIEW')"
			+ " or hasRole('STAFF_TITLE_EDIT') or hasRole('ADMIN')")
	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	public ModelAndView edit(
			@RequestParam(value = "staffTitle", required = true)
				final StaffTitle staffTitle) {
		StaffTitleForm staffTitleForm = new StaffTitleForm();
		staffTitleForm.setName(staffTitle.getName());
		staffTitleForm.setSortOrder(staffTitle.getSortOrder());
		staffTitleForm.setValid(staffTitle.getValid());
		ModelAndView mav = this.prepareEditMav(staffTitleForm);
		mav.addObject(STAFF_TITLE_MODEL_KEY, staffTitle);
		return mav;
	}
	
	/**
	 * Saves a new staff title.
	 * 
	 * @param staffTitleForm form for staff title
	 * @param result binding result
	 * @return screen to list staff titles
	 * @throws DuplicateEntityFoundException if an attempt to save a duplication
	 * is made
	 */
	@PreAuthorize("hasRole('STAFF_TITLE_CREATE') or hasRole('ADMIN')")
	@RequestMapping(value = "/create.html", method = RequestMethod.POST)
	public ModelAndView save(
			final StaffTitleForm staffTitleForm,
			final BindingResult result) throws DuplicateEntityFoundException {
		this.staffTitleFormValidator.validate(staffTitleForm, result);
		if (result.hasErrors()) {
			return this.prepareRedisplayMav(staffTitleForm, result);
		}
		this.staffTitleService.create(staffTitleForm.getName(),
				staffTitleForm.getSortOrder(), staffTitleForm.getValid());
		return new ModelAndView(LIST_REDIRECT);
	}

	/**
	 * Updates an existing staff title.
	 * 
	 * @param staffTitle existing staff title to update
	 * @param staffTitleForm form for staff title
	 * @param result binding result
	 * @return screen to list staff titles
	 * @throws DuplicateEntityFoundException if an attempt to update a
	 * duplication is made
	 */
	@PreAuthorize("hasRole('STAFF_TITLE_EDIT') or hasRole('ADMIN')")
	@RequestMapping(value = "/edit.html", method = RequestMethod.POST)
	public ModelAndView update(
			@RequestParam(value = "staffTitle", required = true)
				final StaffTitle staffTitle,
			final StaffTitleForm staffTitleForm,
			final BindingResult result) throws DuplicateEntityFoundException {
		this.staffTitleFormValidator.validate(staffTitleForm, result);
		if (result.hasErrors()) {
			ModelAndView mav = this.prepareRedisplayMav(staffTitleForm,
					result);
			mav.addObject(STAFF_TITLE_MODEL_KEY, staffTitle);
			return mav;
		}
		this.staffTitleService.update(staffTitle,
				staffTitleForm.getName(), staffTitleForm.getSortOrder(),
				staffTitleForm.getValid());
		return new ModelAndView(LIST_REDIRECT);
	}
	
	/**
	 * Removes a staff title.
	 * 
	 * @param staffTitle staff title to remove
	 * @return redirect to list staff titles
	 */
	@PreAuthorize("hasRole('STAFF_TITLE_REMOVE') or hasRole('ADMIN')")
	@RequestMapping(value = "/remove.html", method = RequestMethod.GET)
	public ModelAndView remove(
			@RequestParam(value = "staffTitle", required = true)
				final StaffTitle staffTitle) {
		this.staffTitleService.remove(staffTitle);
		return new ModelAndView(LIST_REDIRECT);
	}
	
	/* Helper methods. */
	
	// Prepares edit screen
	private ModelAndView prepareEditMav(
			final StaffTitleForm staffTitleForm) {
		ModelAndView mav = new ModelAndView(EDIT_VIEW_NAME);
		mav.addObject(STAFF_TITLE_FORM_MODEL_KEY, staffTitleForm);
		return mav;
	}
	
	// Prepares redisplayed edit screen
	private ModelAndView prepareRedisplayMav(
			final StaffTitleForm staffTitleForm, final BindingResult result) {
		ModelAndView mav = this.prepareEditMav(staffTitleForm);
		mav.addObject(
				BindingResult.MODEL_KEY_PREFIX + STAFF_TITLE_FORM_MODEL_KEY,
					result);
		return mav;
	}
	
	/* Init binder. */
	
	/**
	 * Sets up init binders.
	 * 
	 * @param binder init binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(StaffTitle.class,
				this.staffTitlePropertyEditorFactory.createPropertyEditor());
	}
}