package omis.victim.web.controller;

import java.util.List;

import omis.victim.report.VictimReportService;
import omis.victim.report.VictimSummary;
import omis.victim.web.form.VictimSearchForm;
import omis.victim.web.form.VictimSearchType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller to search for victims.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Jun 4, 2015)
 * @since OMIS 3.0
 */
@Controller
@PreAuthorize("hasRole('USER')")
@RequestMapping("/victim")
public class VictimSearchController {
	
	/* View names. */
	
	private static final String VIEW_NAME = "victim/search";

	/* Model keys. */
	
	private static final String VICTIM_SUMMARIES_MODEL_KEY = "victimSummaries";

	private static final String VICTIM_SEARCH_FORM_MODEL_KEY
		= "victimSearchForm";

	/* Report services. */
	
	@Autowired
	@Qualifier("victimReportService")
	private VictimReportService victimReportService;
	
	/* Constructors. */
	
	/** Instantiates victim search controller. */
	public VictimSearchController() {
		// Default instantiation
	}

	/* URL invokable methods. */
	
	/**
	 * Displays form to search for victim.
	 * 
	 * @param defaultType default search type
	 * @return form to search for victim
	 */
	@PreAuthorize("hasRole('VICTIM_ASSOCIATION_LIST') or hasRole('ADMIN')")
	@RequestMapping(value = "/search.html", method = RequestMethod.GET)
	public ModelAndView search(
			@RequestParam(value = "defaultType", required = false)
				final VictimSearchType defaultType) {
		VictimSearchForm victimSearchForm = new VictimSearchForm();
		if (defaultType != null) {
			victimSearchForm.setType(defaultType);
		} else {
			victimSearchForm.setType(VictimSearchType.NAME);
		}
		return this.prepareMav(victimSearchForm);
	}
	
	/**
	 * Searches for victim.
	 * 
	 * @param victimSearchForm form to search for victim
	 * @return form to search for victim and search results
	 */
	@PreAuthorize("hasRole('VICTIM_ASSOCIATION_LIST') or hasRole('ADMIN')")
	@RequestMapping(value = "/search.html", method = RequestMethod.POST)
	public ModelAndView performSearch(
			final VictimSearchForm victimSearchForm) {
		List<VictimSummary> victimSummaries;
		if (VictimSearchType.NAME.equals(victimSearchForm.getType())) {
			victimSummaries = this.victimReportService
				.findByName(victimSearchForm.getLastName(),
							victimSearchForm.getFirstName());
		} else {
			throw new UnsupportedOperationException(
					String.format("Unsupported search type : %s",
							victimSearchForm.getType()));
		}
		ModelAndView mav = this.prepareMav(victimSearchForm);
		mav.addObject(VICTIM_SUMMARIES_MODEL_KEY, victimSummaries);
		return mav;
	}
	
	/* Helper methods. */
	
	// Returns model and view to search for victim
	private ModelAndView prepareMav(final VictimSearchForm victimSearchForm) {
		ModelAndView mav = new ModelAndView(VIEW_NAME);
		mav.addObject(VICTIM_SEARCH_FORM_MODEL_KEY, victimSearchForm);
		return mav;
	}
}