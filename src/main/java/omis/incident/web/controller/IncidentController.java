package omis.incident.web.controller;

import omis.beans.factory.PropertyEditorFactory;
import omis.incident.domain.Jurisdiction;
import omis.incident.report.JurisdictionSummaryService;
import omis.incident.service.IncidentService;
import omis.media.domain.Photo;
import omis.media.io.PhotoRetriever;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
 * Incident controller.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Nov 3, 2015)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/incident")
@PreAuthorize("hasRole('USER')")
public class IncidentController {
	
	/* View names. */
	
	private static final String INDEX_VIEW_NAME = "/incident/index";
	
	/* Model keys. */
	
	private static final String JURISDICTION_SUMMARIES_MODEL_KEY
		= "jurisdictionSummaries";
	
	/* Services. */
	
	@Autowired
	@Qualifier("incidentService")
	private IncidentService incidentService;
	
	@Autowired
	@Qualifier("jurisdictionSummaryService")
	private JurisdictionSummaryService jurisdictionSummaryService;
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("jurisdictionPhotoRetriever")
	private PhotoRetriever jurisdictionPhotoRetriever;
	
	/* Property editors. */
	
	@Autowired
	@Qualifier("jurisdictionPropertyEditorFactory")
	PropertyEditorFactory jurisdictionPropertyEditorFactory;
	
	/* Constructors. */

	/**
	 * Instantiates a default instance of incident controller.
	 */
	public IncidentController() {
		//Default constructor.
	}
	
	/* URL mapped methods. */
	
	/**
	 * Returns the incident management center index model and view.
	 * 
	 * @return model and view for incident management center index
	 */
	@RequestMapping(value = "index.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('INCIDENT_MODULE')")
	public ModelAndView index() {
		ModelMap map = new ModelMap();
		map.addAttribute(JURISDICTION_SUMMARIES_MODEL_KEY,
				this.jurisdictionSummaryService.summarizeJurisdictions());
		return new ModelAndView(INDEX_VIEW_NAME, map);
	}
	
	/**
	 * Returns the photo data for the specified jurisdiction.
	 * 
	 * @param jurisdiction jurisdiction
	 * @param contentType content type
	 * @return photo data as a byte array 
	 */
	@RequestMapping("/displayJurisdictionPhoto.html")
	public ResponseEntity<byte []> displayPhoto(
			@RequestParam(value = "jurisdiction", required = true)
			final Jurisdiction jurisdiction,
			@RequestParam(value = "contentType", required = true)
			final String contentType) {
		Photo photo = jurisdiction.getPhoto();
		byte[] photoData;
		if (photo != null) {
			photoData = this.jurisdictionPhotoRetriever.retrieve(photo);
		} else {
			photoData = this.jurisdictionPhotoRetriever.retrieve("NoPhoto.jpg");
		}
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", contentType);
		return new ResponseEntity<byte[]>(photoData, headers, HttpStatus.OK);
	}
	
	/* Helper methods. */
	
	/**
	 * Sets up and registers property editors.
	 * 
	 * @param binder web binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(
				Jurisdiction.class,
				this.jurisdictionPropertyEditorFactory
				.createPropertyEditor());
	}
}