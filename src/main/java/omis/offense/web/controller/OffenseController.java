package omis.offense.web.controller;

import java.util.List;

import omis.beans.factory.PropertyEditorFactory;
import omis.content.RequestContentMapping;
import omis.content.RequestContentType;
import omis.instance.factory.InstanceFactory;
import omis.offense.domain.Offense;
import omis.offense.service.OffenseService;
import omis.offense.validator.OffenseFormValidator;
import omis.offense.web.form.OffenseForm;

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
 * Offense Controller.
 * 
 * @author Joel Norris
 * @version 0.1.0 (May 29, 2013)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/offense")
@PreAuthorize("hasRole('ADMIN') or hasRole('APP_DEV') or (hasRole('USER') " 
		+ "and hasRole('OFFENSE_MODULE'))")
public class OffenseController {
	
	@Autowired
	@Qualifier("offenseService")
	private OffenseService offenseService;
	
	@Autowired
	@Qualifier("offenseInstanceFactory")
	private InstanceFactory<Offense> offenseInstanceFactory;
	
	@Autowired
	@Qualifier("offenseFormValidator")
	private OffenseFormValidator offenseFormValidator;
	
	@Autowired
	@Qualifier("offensePropertyEditorFactory")
	private PropertyEditorFactory offensePropertyEditorFactory;
	
	/** Instantiates a default instance of offense controller. */
	public OffenseController() {
		//default constructor
	}
	
	/**
	 * Lists all the offenses in the offense list screen.
	 * 
	 * @return model and view
	 */
	@RequestContentMapping(nameKey = "offenseListScreenName",
			descriptionKey = "offenseListDescription",
			messageBundle = "omis.offense.msgs.offense",
			screenType = RequestContentType.LISTING_SCREEN)
	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('OFFENSE_LIST') or hasRole('ADMIN')")
	public ModelAndView list() {
		ModelMap map = new ModelMap();
		List<Offense> offenses = this.offenseService.findAll();
		map.addAttribute("offenses", offenses);
		return new ModelAndView("offense/list", map);
	}
	
	/**
	 * Instantiates a form for the creation of a new offense on the create
	 * screen.
	 * 
	 * @return model and view
	 */
	@RequestContentMapping(nameKey = "offenseCreateScreenName",
			descriptionKey = "offenseCreateDescription",
			messageBundle = "omis.offense.msgs.offense",
			screenType = RequestContentType.DETAIL_SCREEN)
	@RequestMapping(value = "/create.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('OFFENSE_CREATE') or hasRole('ADMIN')")
	public ModelAndView create() {
		ModelMap map = new ModelMap();
		OffenseForm offenseForm = new OffenseForm();
		map.addAttribute("offenseForm", offenseForm);
		return new ModelAndView("offense/edit", map);
	}
	
	/**
	 * Submits the offense form, checks for valid entries, and either 
	 * persists the offense, or returns to the offense create screen.
	 * 
	 * @param offenseForm offenseForm
	 * @param result result
	 * @return model and view
	 */
	@RequestContentMapping(nameKey = "offenseSaveName",
			descriptionKey = "offenseSaveDescription",
			messageBundle = "omis.offense.msgs.offense",
			screenType = RequestContentType.FORM_SUBMIT)
	@RequestMapping(value = "/create.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('OFFENSE_SAVE') or hasRole('ADMIN')")
	public ModelAndView save(final OffenseForm offenseForm, 
		final BindingResult result) {
		this.offenseFormValidator.validate(offenseForm, result);
		if (result.hasErrors()) {
			ModelMap map = new ModelMap();
			map.addAttribute("offenseForm", offenseForm);
			return new ModelAndView("offense/edit", map);
		}
		Offense offense = this.offenseInstanceFactory.createInstance();
		prepareOffense(offenseForm, offense);
		this.offenseService.save(offense);
		return new ModelAndView("redirect:list.html");
	}
	
	/**
	 * Prepares a form for editing with information from the specified offense.
	 * 
	 * @param offense offense
	 * @return model and view
	 */
	@RequestContentMapping(nameKey = "offenseEditScreenName",
			descriptionKey = "offenseEditDescription",
			messageBundle = "omis.offense.msgs.offense",
			screenType = RequestContentType.DETAIL_SCREEN)
	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('OFFENSE_EDIT') or hasRole('ADMIN')")
	public ModelAndView edit(@RequestParam(value = "offense", required = true)
		final Offense offense) {
		ModelMap map = new ModelMap();
		OffenseForm offenseForm = new OffenseForm();
		offenseForm.setName(offense.getName());
		offenseForm.setViolationCode(offense.getViolationCode());
		map.addAttribute("offenseForm", offenseForm);
		map.addAttribute("offense", offense);
		return new ModelAndView("offense/edit", map);
	}
	
	/**
	 * Submits the offense form, checks for valid entries, and either persists
	 * the information or returns to the offense edit screen.
	 *  
	 * @param offenseForm offenseForm
	 * @param offense offense
	 * @param result result
	 * @return model and view
	 */
	@RequestContentMapping(nameKey = "offenseUpdateName",
			descriptionKey = "offenseUpdateDescription",
			messageBundle = "omis.offense.msgs.offense",
			screenType = RequestContentType.FORM_SUBMIT)
	@RequestMapping(value = "/edit.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('OFFENSE_UPDATE') or hasRole('ADMIN')")
	public ModelAndView update(final OffenseForm offenseForm, @RequestParam(
		value = "offense", required = true) final Offense offense, 
		final BindingResult result) {
		this.offenseFormValidator.validate(offenseForm, result);
		if (result.hasErrors()) {
			ModelMap map = new ModelMap();
			map.addAttribute("offenseForm", offenseForm);
			map.addAttribute("offense", offense);
			return new ModelAndView("offense/edit", map);
		}
		prepareOffense(offenseForm, offense);
		this.offenseService.save(offense);
		return new ModelAndView("redirect:list.html");
	}
	
	/**
	 * Removes the specified offense and returns to the offense list screen.
	 * 
	 * @param offense offense
	 * @return redirect string
	 */
	@RequestContentMapping(nameKey = "offenseRemoveName",
			descriptionKey = "offenseRemoveDescription",
			messageBundle = "omis.offense.msgs.offense",
			screenType = RequestContentType.FORM_SUBMIT)
	@RequestMapping(value = "/remove.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('OFFENSE_REMOVE') or hasRole('ADMIN')")
	public String removeOffense(@RequestParam(value = "offense",
			required = true) final Offense offense) {
		this.offenseService.remove(offense);
		return "redirect:list.html";
	}
	
	//Prepares an offense with information from the offense form.
	private Offense prepareOffense(final OffenseForm form, 
			final Offense offense) {
		offense.setName(form.getName());
		offense.setViolationCode(form.getViolationCode());
		return offense;
	}
	
	/**
	 * Sets up and registers property editors.
	 * 
	 * @param binder binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(
				Offense.class,
				this.offensePropertyEditorFactory
				.createPropertyEditor());
		
		binder.registerCustomEditor(
				OffenseForm.class,
				this.offensePropertyEditorFactory
				.createPropertyEditor());
	}
}
