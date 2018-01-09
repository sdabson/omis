/**
 * 
 */
package omis.offenderrelationship.web.controller;

import omis.beans.factory.PropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offenderrelationship.web.controller.delegate.OffenderRelationshipSummaryModelDelegate;
import omis.person.domain.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/*
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import omis.offender.domain.Offender;
import omis.offenderrelationship.web.form.CreateRelationshipsForm;
import omis.person.domain.Person;
*/
/**
 * @author cib168
 *
 */
@Controller
@RequestMapping("/offenderRelationship")
@PreAuthorize("hasRole('ADMIN') or hasRole('APP_DEV') or (hasRole('USER') " 
		+ "and hasRole('OFFENDER_RELATIONSHIP_MODULE'))")
public class OffenderRelationshipProfileController {	
	/* View Names */
	
	private static final String VIEW_NAME = "offenderReleationship/profile";
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("offenderRelationshipSummaryModelDelegate")
	private OffenderRelationshipSummaryModelDelegate 
		offenderRelationshipSummaryModelDelegate;
	
	/* Property Editor Factories */
	
	@Autowired
	@Qualifier("personPropertyEditorFactory")
	private PropertyEditorFactory personPropertyEditorFactory;
		
	/* Constructors */

	/**
	 * Instantiates controller to manage offender relations.
	 */
	public OffenderRelationshipProfileController() {
		// Default instantiation.
	}
	
	/* URL Invokable Methods */
		
	/**
	 * show profile items.
	 * 
	 * @param secondPerson second person
	 * @return model and view to display profile items
	 */
	@RequestMapping(value = "/showProfile.html", method = RequestMethod.GET)
	public ModelAndView showProfile(
			@RequestParam(value = "secondPerson", required = true)
				final Offender secondPerson) {
		ModelAndView mav = new ModelAndView(VIEW_NAME);
		/*this.offenderRelationshipSummaryModelDelegate.add(
				mav.getModelMap(), relation);*/
		return mav;
	}
	
/* Init Binders */
	
	/**
	 * Registers property editors.
	 * 
	 * @param binder binder
	 */
	@InitBinder
	protected void registerPropertyEditors(final WebDataBinder binder) {
		binder.registerCustomEditor(Person.class,
				this.personPropertyEditorFactory.createPropertyEditor());
	}
}
