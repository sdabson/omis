package omis.user.web.controller;

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
import omis.user.service.UserGroupAdminService;
import omis.user.web.form.UserGroupForm;

/**
 * Controller for user group administration related operations.
 * 
 * <p>Management of roles and users associated with roles is not yet
 * implemented.
 * 
 * @author Stephen Abson
 * @version 0.1.2 (Aug 8, 2016)
 * @since OMIS 3.0
 */
@Controller
@PreAuthorize("hasRole('USER')")
@RequestMapping("/user/admin/userGroup")
public class UserGroupAdminController {
	
	/* Model keys. */
	
	private static final String GROUPS_MODEL_KEY = "groups";
	
	private static final String USER_GROUP_FORM_MODEL_KEY = "userGroupForm";
	
	private static final String ROLES_MODEL_KEY = "roles";
	
	/* View names. */
	
	private static final String USER_GROUP_LIST_VIEW_NAME
		= "user/admin/userGroup/list";
	
	private static final String EDIT_USER_GROUP_VIEW_NAME
		= "user/admin/userGroup/edit";
	
	/* Redirects. */
	
	private static final String USER_GROUP_LIST_REDIRECT = "redirect:list.html";
	
	/* Property editor factories. */
	
	@Autowired
	private PropertyEditorFactory userGroupPropertyEditorFactory;
	
	@Autowired
	private PropertyEditorFactory userRolePropertyEditorFactory;
	
	/* Services. */
	
	@Autowired
	private UserGroupAdminService userGroupService;
	
	@Autowired
	private InstanceFactory<UserGroup> userGroupInstanceFactory;
	
	/* Constructor. */
	
	/** Instantiates a default controller for user group administration. */
	public UserGroupAdminController() {
		// Default instantiation
	}
	
	/* URL invoked methods. */
	
	/**
	 * Shows a list of user groups.
	 * 
	 * @return model and view to show list of user groups
	 */
	@PreAuthorize("hasRole('USER_GROUP_LIST') or hasRole('ADMIN')")
	@RequestMapping(value = "/list.html")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView(USER_GROUP_LIST_VIEW_NAME);
		List<UserGroup> groups = this.userGroupService.findAll();
		mav.addObject(GROUPS_MODEL_KEY, groups);
		return mav;
	}
	
	/**
	 * Shows a form that allows the creation of a new user group.
	 * 
	 * @return model and view to form that allows creation of user group
	 */
	@PreAuthorize("hasRole('USER_GROUP_CREATE') or hasRole('ADMIN')")
	@RequestMapping(value = "/create.html", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView mav = new ModelAndView(EDIT_USER_GROUP_VIEW_NAME);
		UserGroupForm userGroupForm = new UserGroupForm();
		userGroupForm.setSortOrder(
				(short) (this.userGroupService.findMaxSortOrder() + 1));
		mav.addObject(USER_GROUP_FORM_MODEL_KEY, userGroupForm);
		List<UserRole> roles = this.userGroupService.findRoles();
		mav.addObject(ROLES_MODEL_KEY, roles);
		return mav;
	}
	
	/**
	 * Saves a new user group.
	 * 
	 * @param userGroupForm form fields with which to create user group
	 * @return model and view to redirect to user group listing screen
	 */
	@PreAuthorize("hasRole('USER_GROUP_CREATE') or hasRole('ADMIN')")
	@RequestMapping(value = "/create.html", method = RequestMethod.POST)
	public ModelAndView save(final UserGroupForm userGroupForm) {
		UserGroup userGroup = this.userGroupInstanceFactory.createInstance();
		userGroup.setName(userGroupForm.getName());
		userGroup.setDescription(userGroupForm.getDescription());
		userGroup.setSortOrder(userGroupForm.getSortOrder());
		userGroup.setValid(userGroupForm.getValid());
		if (userGroupForm.getRoles() != null) {
			this.replaceRoles(userGroup, userGroupForm.getRoles());
		}
		this.userGroupService.save(userGroup);
		return new ModelAndView(USER_GROUP_LIST_REDIRECT);
	}

	/**
	 * Shows a form that allows the editing of a user group.
	 * 
	 * @param group user group to edit
	 * @return model and view to form that allows editing of user group
	 */
	@PreAuthorize("hasRole('USER_GROUP_VIEW') or hasRole('ADMIN')")
	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	public ModelAndView edit(
			@RequestParam(value = "group", required = true)
				final UserGroup group) {
		ModelAndView mav = new ModelAndView(EDIT_USER_GROUP_VIEW_NAME);
		UserGroupForm userGroupForm = new UserGroupForm();
		userGroupForm.setName(group.getName());
		userGroupForm.setDescription(group.getDescription());
		userGroupForm.setSortOrder(group.getSortOrder());
		userGroupForm.setValid(group.getValid());
		userGroupForm.replaceRoles(
				this.userGroupService.findRolesByGroup(group));
		userGroupForm.replaceMembers(this.userGroupService.findMembers(group));
		mav.addObject(USER_GROUP_FORM_MODEL_KEY, userGroupForm);
		List<UserRole> roles = this.userGroupService.findRoles();
		mav.addObject(ROLES_MODEL_KEY, roles);
		return mav;
	}
	
	/**
	 * Updates an existing user group.
	 * 
	 * @param group user group to update
	 * @param userGroupForm form fields with which to update user group
	 * @return model and view to redirect to user group listing screen
	 */
	@PreAuthorize("hasRole('USER_GROUP_EDIT') or hasRole('ADMIN')")
	@RequestMapping(value = "/edit.html", method = RequestMethod.POST)
	public ModelAndView update(
			@RequestParam(value = "group", required = true)
				final UserGroup group,
			final UserGroupForm userGroupForm) {
		group.setName(userGroupForm.getName());
		group.setDescription(userGroupForm.getDescription());
		group.setSortOrder(userGroupForm.getSortOrder());
		group.setValid(userGroupForm.getValid());
		if (userGroupForm.getRoles() != null) {
			this.replaceRoles(group, userGroupForm.getRoles());
		} else {
			this.userGroupService.clearRoles(group);
		}
		return new ModelAndView(USER_GROUP_LIST_REDIRECT);
	}

	/**
	 * Removes a user group.
	 * 
	 * @param group user group to remove
	 * @return model and view to redirect to user group listing screen
	 */
	@PreAuthorize("hasRole('USER_GROUP_REMOVE') or hasRole('ADMIN')")
	@RequestMapping(value = "/remove.html")
	public ModelAndView remove(
			@RequestParam(value = "group", required = true)
				final UserGroup group) {
		this.userGroupService.remove(group);
		return new ModelAndView(USER_GROUP_LIST_REDIRECT);
	}
	
	/**
	 * Sets up and registers property editors.
	 * 
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
	
	// Replaces roles for group
	private void replaceRoles(final UserGroup userGroup,
			final Set<UserRole> roles) {
		List<UserRole> existingRoles = this.userGroupService
				.findRolesByGroup(userGroup);
		for (UserRole userRole : existingRoles) {
			if (!roles.contains(userRole)) {
				this.userGroupService
					.removeUserRoleAssignment(userGroup, userRole);
			}
		}
		for (UserRole userRole : roles) {
			if (!this.userGroupService
					.isAssignedUserRole(userGroup, userRole)) {
				this.userGroupService.assignUserRole(userGroup, userRole);
			}
		}
	}
}