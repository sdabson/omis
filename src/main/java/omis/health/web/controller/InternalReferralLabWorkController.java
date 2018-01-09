package omis.health.web.controller;

import java.util.List;

import omis.beans.factory.PropertyEditorFactory;
import omis.health.domain.InternalReferral;
import omis.health.domain.LabWork;
import omis.health.domain.LabWorkRequirement;
import omis.health.service.InternalReferralScheduler;
import omis.health.web.form.LabWorkAppointmentItem;
import omis.health.web.form.ReferralLabWorkForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for internal referral lab work.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 6, 2014)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/health/referral/internal/labWork")
@PreAuthorize("hasRole('ADMIN')" +
		" or hasRole('HEALTH_ADMIN')" +
		" or (hasRole('USER') and hasRole('HEALTH_MODULE'))")
public class InternalReferralLabWorkController {

	/* Model keys. */
	
	private static final String REFERRAL_LAB_WORK_FORM_MODEL_KEY =
			"referralLabWorkForm";
	
	private static final String INTERNAL_REFERRAL_MODEL_KEY = 
			"internalReferral";
	
	/* View names. */
	
	private static final String EDIT_INTERNAL_REFERRAL_LAB_WORK_VIEW_NAME =
			"";
	
	/* Services. */
	
	@Autowired
	@Qualifier("internalReferralScheduler")
	InternalReferralScheduler internalReferralScheduler;
	
	/* Property Editors. */
	
	@Autowired
	@Qualifier("internalReferralPropertyEditorFactory")
	PropertyEditorFactory internalReferralPropertyEditorFactory;
	
	/* Constructor. */
	
	/**
	 * Instantiates a default instance of internal referral lab work 
	 * controller.
	 */
	public InternalReferralLabWorkController() {
		//Default constructor.
	}
	
	@RequestMapping(value = "edit.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')" +
			" or hasRole('HEALTH_ADMIN')" +
			" or hasRole('LAB_WORK_EDIT')")
	public ModelAndView editLabWork(@RequestParam(value = "internalReferral",
			required = true) final InternalReferral internalReferral) {
		ModelMap map = new ModelMap();
		ReferralLabWorkForm form = new ReferralLabWorkForm();
		List<LabWorkRequirement> labWorkRequirements = 
				this.internalReferralScheduler.findLabWorkRequirements(
						internalReferral);
		for (LabWorkRequirement requirement : labWorkRequirements) {
			LabWorkAppointmentItem item = new LabWorkAppointmentItem();
			LabWork labWork = requirement.getLabWork();
			item.setProcess(true);
			item.setLabWork(labWork);
			item.setDate(labWork.getOffenderAppointmentAssociation()
					.getAppointment().getDate());
			item.setSampleNotes(labWork.getSampleNotes());
			item.setLabWorkCategory(labWork.getLabWorkCategory());
			item.setResultsLab(labWork.getResults().getLab());
			item.setSampleLab(labWork.getSampleLab());
			form.getLabWork().add(item);
		}
		
		map.addAttribute(INTERNAL_REFERRAL_MODEL_KEY, internalReferral);
		map.addAttribute(REFERRAL_LAB_WORK_FORM_MODEL_KEY, form);
		return new ModelAndView(EDIT_INTERNAL_REFERRAL_LAB_WORK_VIEW_NAME, map);
	}
	
	/**
	 * Sets up and registers property editors.
	 * 
	 * @param binder web binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(
				InternalReferral.class,
				this.internalReferralPropertyEditorFactory
				.createPropertyEditor());
	}
}