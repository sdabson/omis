package omis.user.web.controller;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import omis.beans.factory.PropertyEditorFactory;
import omis.instance.factory.InstanceFactory;
import omis.user.domain.UserGroup;
import omis.user.domain.UserRole;
import omis.user.service.UserRoleAdminService;
import omis.user.web.form.UserRoleForm;

/**
 * Controller for user role administration related operations.
 * 
 * <p>Management of groups associates with roles is not yet implemented.
 * 
 * @author Stephen Abson
 * @version 0.1.2 (Aug 8, 2016)
 * @since OMIS 3.0
 */
@Controller
@PreAuthorize("hasRole('USER')")
@RequestMapping("/user/admin/userRole")
public class UserRoleAdminController {
	
	/* Model keys. */
	
	private static final String ROLES_MODEL_KEY = "roles";
	
	private static final String USER_ROLE_FORM_MODEL_KEY = "userRoleForm";
	
	private static final String GROUPS_MODEL_KEY = "groups";
	
	/* View names. */
	
	private static final String USER_ROLE_LIST_VIEW_NAME
		= "user/admin/userRole/list";
	
	private static final String USER_ROLE_EDIT_VIEW_NAME
		= "user/admin/userRole/edit";
	
	private static final String USER_ROLE_LIST_SPANS_VIEW_NAME
	 	= "user/admin/userRole/includes/listSpans";
	
	/* Redirects. */
	
	private static final String USER_ROLE_LIST_REDIRECT = "redirect:list.html";
	
	/* Producers. */
	
	@Autowired
	private InstanceFactory<UserRole> userRoleInstanceFactory;
	
	@Autowired
	private PropertyEditorFactory userGroupPropertyEditorFactory;
	
	@Autowired
	private PropertyEditorFactory userRolePropertyEditorFactory;
	
	@Autowired
	private UserRoleAdminService userRoleService;

	/* Constructor. */
	
	/** Instantiates a default controller for user role administration. */
	public UserRoleAdminController() {
		// Default instantiation
	}
	
	/* URL invoked methods. */
	
	/**
	 * Shows a list of all user roles.
	 * 
	 * @return model and view to display a list of all user roles
	 */
	@PreAuthorize("hasRole('USER_ROLE_LIST') or hasRole('ADMIN')")
	@RequestMapping("/list.html")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView(USER_ROLE_LIST_VIEW_NAME);
		List<UserRole> roles = this.userRoleService.findAll();
		mav.addObject(ROLES_MODEL_KEY, roles);
		return mav;
	}

	/**
	 * Displays a form allowing the creation of a new user role.
	 * 
	 * @return model and view to show form allowing creation of new user role
	 */
	@PreAuthorize("hasRole('USER_ROLE_CREATE') or hasRole('ADMIN')")
	@RequestMapping(value = "/create.html", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView mav = new ModelAndView(USER_ROLE_EDIT_VIEW_NAME);
		UserRoleForm userRoleForm = new UserRoleForm();
		userRoleForm.setSortOrder(
				(short) (this.userRoleService.findMaxSortOrder() + 1));
		mav.addObject(USER_ROLE_FORM_MODEL_KEY, userRoleForm);
		List<UserGroup> groups = this.userRoleService.findGroups();
		mav.addObject(GROUPS_MODEL_KEY, groups);
		return mav;
	}
	
	/**
	 * Displays a form allowing the editing of an existing user role.
	 * 
	 * @param role user role to edit
	 * @return model and view to show form allowing update of existing user role
	 */
	@PreAuthorize("hasRole('USER_ROLE_VIEW') or hasRole('ADMIN')")
	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	public ModelAndView edit(
			@RequestParam(value = "role", required = true)
				final UserRole role) {
		ModelAndView mav = new ModelAndView(USER_ROLE_EDIT_VIEW_NAME);
		UserRoleForm userRoleForm = new UserRoleForm();
		userRoleForm.setName(role.getName());
		userRoleForm.setDescription(role.getDescription());
		userRoleForm.setSortOrder(role.getSortOrder());
		userRoleForm.setValid(role.getValid());
		userRoleForm.replaceGroups(
				this.userRoleService.findGroupsByRole(role));
		mav.addObject(USER_ROLE_FORM_MODEL_KEY, userRoleForm);
		List<UserGroup> groups = this.userRoleService.findGroups();
		mav.addObject(GROUPS_MODEL_KEY, groups);
		return mav;
	}

	/**
	 * Saves a new user role.
	 * 
	 * @param userRoleForm form from which to create new role
	 * @return model and view to redirect to listing screen
	 */
	@PreAuthorize("hasRole('USER_ROLE_CREATE') or hasRole('ADMIN')")
	@RequestMapping(value = "/create.html", method = RequestMethod.POST)
	public ModelAndView save(final UserRoleForm userRoleForm) {
		UserRole role = this.userRoleInstanceFactory.createInstance();
		role.setName(userRoleForm.getName());
		role.setDescription(userRoleForm.getDescription());
		role.setSortOrder(userRoleForm.getSortOrder());
		role.setValid(userRoleForm.getValid());
		if (userRoleForm.getGroups() != null) {
			this.replaceGroups(role, userRoleForm.getGroups());
		} else {
			this.userRoleService.clearGroups(role);
		}
		this.userRoleService.save(role);
		return new ModelAndView(USER_ROLE_LIST_REDIRECT);
	}
	
	/**
	 * Updates an existing user role.
	 * 
	 * @param role user role to update
	 * @param userRoleForm form from which to update the role
	 * @return model and view to redirect to listing screen
	 */
	@PreAuthorize("hasRole('USER_ROLE_EDIT') or hasRole('ADMIN')")
	@RequestMapping(value = "/edit.html", method = RequestMethod.POST)
	public ModelAndView update(
			@RequestParam(value = "role", required = true)
				final UserRole role,
			final UserRoleForm userRoleForm) {
		role.setName(userRoleForm.getName());
		role.setDescription(userRoleForm.getDescription());
		role.setSortOrder(userRoleForm.getSortOrder());
		role.setValid(userRoleForm.getValid());
		if (userRoleForm.getGroups() != null) {
			this.replaceGroups(role, userRoleForm.getGroups());
		} else {
			this.userRoleService.clearGroups(role);
		}
		return new ModelAndView(USER_ROLE_LIST_REDIRECT);
	}

	/**
	 * Removes a user role.
	 * 
	 * @param role user role to remove
	 * @return model and view to redirect to listing screen
	 */
	@PreAuthorize("hasRole('USER_ROLE_REMOVE') or hasRole('ADMIN')")
	@RequestMapping(value = "/remove.html", method = RequestMethod.GET)
	public ModelAndView remove(
			@RequestParam(value = "role", required = true)
				final UserRole role) {
		this.userRoleService.remove(role);
		return new ModelAndView(USER_ROLE_LIST_REDIRECT);
	}
	
	/**
	 * Displays a snippet of spans of user roles assigned to the specified
	 * groups.
	 * 
	 * @param groups groups the assigned roles of which to display
	 * @return model and view to display snippet of spans of roles
	 */
	@RequestMapping(value = "/findByUserGroups.html")
	public ModelAndView findByUserGroups(
			@RequestParam(value = "groups[]", required = false)
				final Collection<UserGroup> groups) {
		ModelAndView mav = new ModelAndView(USER_ROLE_LIST_SPANS_VIEW_NAME);
		List<UserRole> roles;
		if (groups != null) {
			roles = this.userRoleService.findByUserGroups(groups);
		} else {
			roles = null;
		}
		mav.addObject(ROLES_MODEL_KEY, roles);
		return mav;
	}
	
	/**
	 * Sets up and registers property editors.
	 * @param binder web binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(UserGroup.class, 
			this.userGroupPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(UserRole.class, 
			this.userRolePropertyEditorFactory
				.createPropertyEditor());
	}

	// Replaces role groups
	private void replaceGroups(
			final UserRole role, final Set<UserGroup> groups) {
		List<UserGroup> existingGroups
			= this.userRoleService.findGroupsByRole(role);
		for (UserGroup userGroup : existingGroups) {
			if (!groups.contains(userGroup)) {
				this.userRoleService.removeUserRoleAssignment(userGroup, role);
			}
		}
		for (UserGroup userGroup : groups) {
			if (!this.userRoleService.isAssignedUserRole(userGroup, role)) {
				this.userRoleService.assignUserRole(userGroup, role);
			}
		}
	}
}