package omis.caseload.web.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import omis.caseload.domain.CaseWorkerAssignment;
import omis.caseload.service.CaseloadService;
import omis.person.domain.Person;

/** Controller for supervisory caseload related operations.
 * @author Ryan Johns
 * @version 0.1.0 (May 17, 2017)
 * @since OMIS 3.0 */
@Controller
@RequestMapping("/caseload/supervisory")
@PreAuthorize("hasRole('USER')")
public class SupervisoryCaseloadController {
	/* View names. */
	private static final String LIST_VIEW = "/caseload/myCaseloadList";
	/*private static final String EDIT_VIEW = "/caseload/edit";*/
	/* Model keys. */
/*	private static final String FORM_MODEL_KEY = "form";*/
	/* Redirects. */
	
	private static final String SUPERVISORY_CASELOAD_CREATE_REDIRECT 
					= "redirect:/caseload/supervisory/edit.html?worker=%d";
	
	/* Services. */
	private final CaseloadService caseloadService;
	
	/** Constructor.
	 * @param caseloadService - caseload service. */
	@Autowired
	public SupervisoryCaseloadController(
					@Qualifier("caseloadService") 
					final CaseloadService caseloadService) {
		this.caseloadService = caseloadService;
	}
	
	/** displays users caseload.
	 * @param person - optional parameter of whose caseload to view.
	 * @return displays caseworkers caseload unless not provide in which case 
	 * displays current users caseload.  */
	@RequestMapping("list.html")
	@PreAuthorize("hasRole('ADMIN') or hasRole('CASELOAD_SUPERVISORY_LIST')")
	public ModelAndView myList(
					@RequestParam(value = "person", required = false)
					final Person person) {
		final Person worker;
		final Date effectiveDate = new Date();
		
		if (person == null) {
			worker = this.caseloadService.findUserAccountByUsername(
						SecurityContextHolder.getContext()
						.getAuthentication().getName()).getUser();
		} else {
			worker = person;
		}
		
		final CaseWorkerAssignment caseWorkerAssignment = this.caseloadService
						.findSupervisoryCaseWorkerAssignmentBy(worker, 
								effectiveDate);
		
		final ModelAndView mav;
		if (caseWorkerAssignment == null) {
			mav = new ModelAndView(String.format(
					SUPERVISORY_CASELOAD_CREATE_REDIRECT, worker.getId()));
		} else {
			mav = new ModelAndView(LIST_VIEW);
		}
		return mav;
	}
	
	/** creates supervisory caseload.
	 * @param person - person.
	 * @return displays form to create caseload. */
	@RequestMapping("create.html")
	@PreAuthorize("hasRole('ADMIN') or hasRole('CASELOAD_SUPERVISORY_VIEW')")
	public ModelAndView create(
					@RequestParam(value = "worker", required = true)
					final Person person) {
			return null;
	}
}
