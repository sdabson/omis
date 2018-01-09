package omis.presentenceinvestigation.web.controller;

import java.security.InvalidParameterException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.bedplacement.domain.BedPlacement;
import omis.bedplacement.exception.BedOccupiedException;
import omis.bedplacement.exception.BedPlacementDateConflictException;
import omis.content.RequestContentMapping;
import omis.content.RequestContentType;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.service.AlternativeOffenderNameService;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.person.domain.AlternativeNameAssociation;
import omis.person.domain.PersonName;
import omis.presentenceinvestigation.domain.BiographicAndContactSection;
import omis.presentenceinvestigation.service.BiographicAndContactSectionService;
import omis.presentenceinvestigation.service.PresentenceInvestigationRequestService;
import omis.presentenceinvestigation.web.form.BiographicAndContactSectionForm;
import omis.presentenceinvestigation.web.validator.BiographicAndContactSectionFormValidator;
import omis.residence.report.ResidenceReportService;
import omis.residence.report.ResidenceSummary;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

/**
 * Controller for biographic and contact section..
 * 
 * @author Jonny Santy
 * @version 0.1.0 (Oct, 31, 2016  Happy Halloween!)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/presentenceInvestigation/bio/")
@PreAuthorize("hasRole('ADMIN') or hasRole('APP_DEV') or (hasRole('USER') " 
		+ "and hasRole('BIOGRAPHIC_AND_CONTACT_SECTION_MODULE'))")
public class BiographicAndContactSectionController {
	
	/* View names. */
	
	private static final String LIST_VIEW_NAME = "presentenceInvestigation/bio/list";
	
	private static final String EDIT_VIEW_NAME = "presentenceInvestigation/bio/edit";
	
	/* Redirect URLs. */
	
	private static final String LIST_REDIRECT_URL 
		= "redirect:edit.html?offender=";
	
	/* Model Keys. */


	private static final String BIOGRAPHIC_AND_CONTACT_SECTION_FORM_MODEL_KEY = "biographicAndContactSectionForm";
	
	/* Message keys. */
	
	
	/* Bundles. */
	
	/* Services. */

	@Autowired
	@Qualifier("biographicAndContactSectionService")
	private BiographicAndContactSectionService biographicAndContactSectionService;

	@Autowired
    private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	

	@Autowired
	@Qualifier("presentenceInvestigationRequestService")
	private PresentenceInvestigationRequestService
	presentenceInvestigationRequestService;
	
	/* Property Editors. */
	
	@Autowired
	private CustomDateEditorFactory customDateEditorFactory;
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	@Qualifier("biographicAndContactSectionPropertyEditorFactory")
	private PropertyEditorFactory biographicAndContactSectionPropertyEditorFactory;
	
	/* Validators. */
	
	@Autowired
	@Qualifier("biographicAndContactSectionFormValidator")
	private BiographicAndContactSectionFormValidator biographicAndContactSectionFormValidator;
	
	/* Helpers. */
	@Autowired
	@Qualifier("businessExceptionHandlerDelegate")
	private BusinessExceptionHandlerDelegate businessExceptionHandlerDelegate;

	@Autowired
	@Qualifier("residenceReportService")
	private ResidenceReportService residenceReportService;

	@Autowired
	@Qualifier("alternativeOffenderNameService")
	private AlternativeOffenderNameService alternativeOffenderNameService;

	
	/** Instantiates a default instance of Biographic and Contact Section controller. */
	public BiographicAndContactSectionController() {
		super();
		//empty constructor
	}
	
	/**
	 * TODO do this when I get the design for it.
	 * 
	 * @param offender offender
	 * @return model and view
	 */
	@RequestContentMapping(nameKey = "bedPlacementListScreenName",
			descriptionKey = "bedPlacementListScreenDescription",
			messageBundle = "omis.placement.msgs.bedPlacement",
			screenType = RequestContentType.LISTING_SCREEN)
	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('BIOGRAPHIC_AND_CONTACT_SECTION_LIST') or hasRole('ADMIN')")
	public ModelAndView list(@RequestParam(value = "offender", required = true)
			final Offender offender) {
		ModelMap map = new ModelMap();
		this.offenderSummaryModelDelegate.add(map, offender);
		return new ModelAndView(LIST_VIEW_NAME, map);
	}
	
	/**
	 * Displays the bed placement form for viewing/editing a
	 * specified bed placement.
	 * 
	 * @param bedPlacement bed placement
	 * @return model and view
	 */
	@RequestContentMapping(nameKey = "biographicAndContactSectionEditScreenName",
			descriptionKey = "biographicAndContactSectionEditScreenDescription",
			messageBundle = "omis.presentenceinvestigation.msgs.biographicAndContactSection",
			screenType = RequestContentType.DETAIL_SCREEN)
	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('BIOGRAPHIC_AND_CONTACT_SECTION_EDIT') or "
			+ "hasRole('BIOGRAPHIC_AND_CONTACT_SECTION_VIEW') or hasRole('ADMIN')")
	public ModelAndView edit(@RequestParam(value = "offender",
			required = true) final Offender offender,
			@RequestParam(value = "bioSection",
			required = false) final BiographicAndContactSection biographicAndContactSection) {
		
		ModelMap map = new ModelMap();

		this.offenderSummaryModelDelegate.add(map, offender);//offender header
		
		BiographicAndContactSectionForm form = new BiographicAndContactSectionForm();
		boolean weAreUpdatingAnExistingBio = biographicAndContactSection!=null;
		
		if(weAreUpdatingAnExistingBio)
		{
			///SET NAME///
			form.setName(biographicAndContactSection.getName());
			form.setNameId(offender.getId());

			///SET SENTENCE DATE///
			form.setDateOfSentence(biographicAndContactSection.getDateOfSentence());
			
			///SET ADDRESS///
			List<ResidenceSummary> residenceSummaries = this.residenceReportService
					.findByOffender(offender, new Date());
			form.setAddress(biographicAndContactSection.getAddress());
			if(!residenceSummaries.isEmpty())
			{
				for (ResidenceSummary residence : residenceSummaries) {
					if(extractAddressStringFromResidence(residence)
							.equals(biographicAndContactSection.getAddress())){
						form.setAddressId(residence.getId());
					}
				}
			}
			else
			{
				throw new InvalidParameterException("This offender must have a valid address before you go to this screen.");
			}
			
			///SET AKA///
			//This is done below the if block
			
			///SET PHONE///
			form.setPhone(biographicAndContactSection.getPhoneNumber());
			
			///SET CELL PHONE///
			form.setCellPhone(biographicAndContactSection.getCellPhoneNumber());
		}
		else
		{
			///SET NAME///
			PersonName pname = offender.getName();
			//Concatenate name components if they're not null:
			String name = pname.getFirstName()==null?"":pname.getFirstName();
					name += pname.getMiddleName()==null?"":" "+pname.getMiddleName();
					name += pname.getLastName()==null?"":" "+pname.getLastName();
					name += pname.getSuffix()==null?"":" "+pname.getSuffix() ;
					form.setName(name);
					form.setNameId(offender.getId());
			
	
			List<ResidenceSummary> residenceSummaries = this.residenceReportService
					.findByOffender(offender, new Date());
			if(!residenceSummaries.isEmpty())
			{
				for (ResidenceSummary residence : residenceSummaries) {
					if(residence.getActive())
					{
						form.setAddress(extractAddressStringFromResidence(residence));
						form.setAddressId(residence.getId());
					}
				}
			}
			else
			{
				throw new InvalidParameterException("This offender must have a valid address before you go to this screen.");
			}
			
			///SET SENTENCE DATE///
			//SENTENCE DATE DOES NOT DEFAULT ON NEW BIO/CONTACT PAGE
	
			///SET AKA///
			List<AlternativeNameAssociation> associations
				= this.alternativeOffenderNameService.findAssociations(offender);
			HashMap<Long,String> akas = new HashMap<Long,String>();
			for (AlternativeNameAssociation alternativeNameAssociation : associations) {
				form.addAlternativeName(alternativeNameAssociation.getName());
	
				PersonName pnAlias = alternativeNameAssociation.getName();
				String alias ;
				
				//Concatenate active address components if they're not null:
				alias =  pnAlias.getFirstName()==null?"":pnAlias.getFirstName();
				alias +=  pnAlias.getMiddleName()==null?"":" "+pnAlias.getMiddleName();
				alias +=  pnAlias.getLastName()==null?"":" "+pnAlias.getLastName();
				alias +=  pnAlias.getSuffix()==null?"":" "+pnAlias.getSuffix();
				
				akas.put(alternativeNameAssociation.getId(), alias);
			}
			form.setAlternativeNames(akas);
		}
		map.addAttribute(BIOGRAPHIC_AND_CONTACT_SECTION_FORM_MODEL_KEY, form);
		return new ModelAndView(EDIT_VIEW_NAME, map);
	}
	
	private String extractAddressStringFromResidence(ResidenceSummary residence) {

		
		String address ;
		
		//Concatenate active address components if they're not null:
		address =  residence.getAddressValue()==null?"":residence.getAddressValue();
		address += residence.getAddressCityName()==null?"":", "+residence.getAddressCityName();
		address += residence.getAddressStateName()==null?"":" "+residence.getAddressStateName();
		address += residence.getAddressZipCodeValue()==null?"":" "+residence.getAddressZipCodeValue();
		
		return address;
	}

	/**
	 * Submits the bed placement form and updates the specified bed placement
	 * with values from the edit screen.
	 * 
	 * @param bedPlacement bed placement
	 * @param form form
	 * @param result binding result
	 * @return model and view
	 * @throws DuplicateEntityFoundException thrown when a duplicate bed
	 * placement, other than the specified bed placement, is found while
	 * updating
	 * @throws BedOccupiedException thrown when the specified bed placement's
	 * bed is already occupied by another offender's bed placement
	 * @throws BedPlacementDateConflictException thrown when the bed placement to be updated
	 * is flagged as confirmed, and another confirmed bed placement is found
	 * within the declared date range.
	 * 
	 * biographicAndContactSectionEditScreenName
	 */
	@RequestContentMapping(nameKey = "biographicAndContactSectionEditName",
			descriptionKey = "biographicAndContactSectionEditDescription",
			messageBundle = "omis.presentenceinvestigation.msgs.BiographicAndContactSectionEdit",
			screenType = RequestContentType.FORM_SUBMIT)
	@RequestMapping(value = "/edit.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('BIOGRAPHIC_AND_CONTACT_SECTION_EDIT') or hasRole('ADMIN')")
	public ModelAndView updateBiographicAndContactSection(@RequestParam(
			value = "offender", required = true) 
				final Offender offender, final BiographicAndContactSectionForm form,
			final BindingResult result) throws DuplicateEntityFoundException, 
			BedPlacementDateConflictException, BedOccupiedException {
	
		return new ModelAndView(LIST_REDIRECT_URL + offender.getId());
	}
	
	/**
	 * 
	 * Removes the specified bed placement and returns to the bed placement
	 * list screen.
	 * 
	 * @param bedPlacement bed placement
	 * @return model and view redirect to bed placement list screen
	 */
	@RequestContentMapping(nameKey = "bedPlacementRemoveName",
			descriptionKey = "bedPlacementRemoveDescription",
			messageBundle = "omis.placement.msgs.bedPlacement",
			screenType = RequestContentType.FORM_SUBMIT)
	@RequestMapping(value = "/remove.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('BED_PLACEMENT_REMOVE') or hasRole('ADMIN')")
	public ModelAndView removeBedPlacement(@RequestParam(value = "bedPlacement",
			required = true) final BedPlacement bedPlacement) {
//		this.bedPlacementService.remove(bedPlacement);
		return new ModelAndView(LIST_REDIRECT_URL 
				+ bedPlacement.getOffender().getId());
	}
	
	/* Helper methods. */
	
	
	
	/**
	 * Sets up and registers property editors.
	 * 
	 * @param binder binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(BiographicAndContactSection.class, 
				this.biographicAndContactSectionPropertyEditorFactory
				.createPropertyEditor());
		
		binder.registerCustomEditor(
				Offender.class,
				this.offenderPropertyEditorFactory
				.createOffenderPropertyEditor());
		
		binder.registerCustomEditor(
				Date.class,
				this.customDateEditorFactory
				.createCustomDateOnlyEditor(true));
		
		binder.registerCustomEditor(
				Date.class, "startTime",
				this.customDateEditorFactory
				.createCustomTimeOnlyEditor(true));
		
		binder.registerCustomEditor(
				Date.class, "endTime",
				this.customDateEditorFactory
				.createCustomTimeOnlyEditor(true));
	}
}