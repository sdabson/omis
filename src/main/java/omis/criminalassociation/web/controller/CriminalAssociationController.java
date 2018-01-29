/*
 * OMIS - Offender Management Information System
 * Copyright (C) 2011 - 2017 State of Montana
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package omis.criminalassociation.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.criminalassociation.domain.CriminalAssociation;
import omis.criminalassociation.domain.CriminalAssociationCategory;
import omis.criminalassociation.domain.component.CriminalAssociationFlags;
import omis.criminalassociation.exception.CriminalAssociationExistsException;
import omis.criminalassociation.service.CriminalAssociationService;
import omis.criminalassociation.validator.CriminalAssociationFormValidator;
import omis.criminalassociation.web.form.CriminalAssociationForm;
import omis.datatype.DateRange;
import omis.instance.factory.InstanceFactory;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.report.OffenderReportService;
import omis.offender.report.OffenderSummary;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.relationship.exception.ReflexiveRelationshipException;
import omis.report.ReportFormat;
import omis.report.ReportRunner;
import omis.report.web.controller.delegate.ReportControllerDelegate;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

/**
 * Controller for criminal association.
 * 
 * @author Joel Norris
 * @author Yidong Li
 * @author Sheronda Vaughn
 * @version 0.1.1 (Apr, 16 2015)
 * @since OMIS 3.0
 */

@Controller
@RequestMapping("/criminalAssociation")
@PreAuthorize("hasRole('USER')")
public class CriminalAssociationController {

  /* Redirect URLs. */

	private static final String LIST_REDIRECT
      = "redirect:/criminalAssociation/list.html?offender=%d";

  /* View names. */

	private static final String LIST_VIEW_NAME = "criminalAssociation/list";

	private static final String EDIT_VIEW_NAME = "criminalAssociation/edit";

	private static final String CRIMINAL_ASSOCIATION_ACTION_MENU_VIEW_NAME
      = "criminalAssociation/includes/criminalAssociationActionMenu";

	private static final String CRIMINAL_ASSOCIATIONS_ACTION_MENU_VIEW_NAME
      = "criminalAssociation/includes/criminalAssociationsActionMenu";

	private static final String CRIMINAL_ASSOCIATIONS_ROW_ACTION_MENU_VIEW_NAME
      = "criminalAssociation/includes/criminalAssociationsRowActionMenu";

  /* Model Keys. */

	private static final String CRIMINAL_ASSOCIATIONS_MODEL_KEY 
      = "criminalAssociations";

	private static final String CRIMINAL_ASSOCIATION_MODEL_KEY 
      = "criminalAssociation";

	private static final String OFFENDER_NUMBER_MODEL_KEY = "offenderNumber";

	private static final String OFFENDER_MODEL_KEY = "offender";

	private static final String OFFENDER_SUMMARY_MODEL_KEY = "offenderSummary";

	private static final String CATEGORIES_MODEL_KEY = "categories";

	private static final String CRIMINAL_ASSOCIATION_FORM_MODEL_KEY 
      = "criminalAssociationForm";

	private static final String ASSOCIATION_OFFENDER_NUMBER_MODEL_KEY 
      = "associationOffenderNumber";
  
  /* Message Keys. */
  
	private static final String ASSOCIATION_EXISTS_MESSAGE_KEY
		= "criminalAssociation.exists";
  
	private static final String REFLEXIVE_RELATIONSHIP_EXISTS_MESSAGE_KEY
  		= "reflexive.relationship.exists";
  
  /* Message bundles. */
  
	private static final String ERROR_BUNDLE_NAME
		= "omis.criminalassociation.msgs.form";
	
	/* Report names. */
	
	private static final String CRIMINAL_ASSOCIATION_LISTING_REPORT_NAME 
		= "/Relationships/CriminalAssociates/Criminal_Associates_Listing";

	private static final String CRIMINAL_ASSOCIATION_DETAILS_REPORT_NAME 
		= "/Relationships/CriminalAssociates/Criminal_Associates_Details";

	/* Report parameter names. */
	
	private static final String 
		CRIMINAL_ASSOCIATION_LISTING_ID_REPORT_PARAM_NAME 
		= "DOC_ID";

	private static final String 
		CRIMINAL_ASSOCIATION_DETAILS_ID_REPORT_PARAM_NAME 
		= "CRIMINAL_ASSOC_ID";

	@Autowired
	@Qualifier("criminalAssociationService")
	private CriminalAssociationService criminalAssociationService;

	@Autowired
	private CustomDateEditorFactory customDateEditorFactory;

	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;

	@Autowired
	@Qualifier("criminalAssociationPropertyEditorFactory")
	private PropertyEditorFactory criminalAssociationPropertyEditorFactory;

	@Autowired
	@Qualifier("criminalAssociationCategoryPropertyEditorFactory")
	private PropertyEditorFactory 
      criminalAssociationCategoryPropertyEditorFactory;

	@Autowired
	@Qualifier("personPropertyEditorFactory")
	private PropertyEditorFactory personPropertyEditorFactory;

	@Autowired
	@Qualifier("addressPropertyEditorFactory")
	private PropertyEditorFactory addressPropertyEditorFactory;

	@Autowired
	@Qualifier("zipCodePropertyEditorFactory")
	private PropertyEditorFactory zipCodePropertyEditorFactory;

	@Autowired
	@Qualifier("residenceTermPropertyEditorFactory")
	private PropertyEditorFactory residenceTermPropertyEditorFactory;

	@Autowired
	@Qualifier("offenderReportService")
	private OffenderReportService offenderReportService;


	@Autowired
	@Qualifier("criminalAssociationInstanceFactory")
	private InstanceFactory<CriminalAssociation> 
      criminalAssociationInstanceFactory;

	@Autowired
	@Qualifier("criminalAssociationCategoryInstanceFactory")
	private InstanceFactory<CriminalAssociationCategory> 
      criminalAssociationCategoryInstanceFactory;

	@Autowired
	@Qualifier("criminalAssociationFormValidator")
	private CriminalAssociationFormValidator 
	    criminalAssociationFormValidator;
   
  /* Delegate */
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
  
	@Autowired
	@Qualifier("businessExceptionHandlerDelegate")
	private BusinessExceptionHandlerDelegate businessExceptionHandlerDelegate;
	
	/* Report runners. */
	
	@Autowired
	@Qualifier("reportRunner")
	private ReportRunner reportRunner;
	
	/* Controller delegates. */
	
	@Autowired
	@Qualifier("reportControllerDelegate")
	private ReportControllerDelegate reportControllerDelegate;

  /** Instantiates a default instance of association controller. */
	public CriminalAssociationController() {
    //empty constructor
	}

  /* URL mapped methods. */

  /**
   * Lists the criminal associations for the specified offender.
   * 
   * @param offender offender
   * @return ModelAndView
   */
	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('CRIMINAL_ASSOCIATION_LIST') or hasRole('ADMIN')")
	  public ModelAndView list(@RequestParam(value = "offender", 
	      required = true) final Offender offender) { 
	    ModelAndView mav = new ModelAndView(LIST_VIEW_NAME);
	    List<CriminalAssociation> criminalAssociations = 
	        this.criminalAssociationService.findByOffender(offender);
	    mav.addObject(CRIMINAL_ASSOCIATIONS_MODEL_KEY, criminalAssociations);
	    mav.addObject(OFFENDER_NUMBER_MODEL_KEY, String.valueOf(offender
	        .getOffenderNumber()));
	    mav.addObject(OFFENDER_MODEL_KEY, offender);
	    OffenderSummary offenderSummary = this.offenderReportService
	        .summarizeOffender(offender);
	    mav.addObject(OFFENDER_SUMMARY_MODEL_KEY, offenderSummary);
	    this.offenderSummaryModelDelegate.add(mav.getModelMap(), offender);
	    return mav;
	}
  
  /**
   * Displays the criminal association form for viewing/editing a specified
   * criminal association.
   * 
   * @param criminalAssociation criminal association
   * @param sourceOffender source offender
   * @return model and view
   */
	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('CRIMINAL_ASSOCIATION_VIEW') or hasRole('ADMIN')")
	  public ModelAndView editAssociation(@RequestParam(value = "association",
	      required = true) final CriminalAssociation criminalAssociation,
	      @RequestParam(value = "sourceOffender", required = true) 
	      final Offender sourceOffender) {
	    ModelMap map = new ModelMap();
	    map.addAttribute(CRIMINAL_ASSOCIATION_MODEL_KEY, criminalAssociation);
	    map.addAttribute(ASSOCIATION_OFFENDER_NUMBER_MODEL_KEY, 
	         String.valueOf(((Offender) criminalAssociation.getRelationship()
	        .getFirstPerson()).getOffenderNumber()));
	    CriminalAssociationForm form = new CriminalAssociationForm();
	    this.prepareAssociationEditForm(form, criminalAssociation);
	    return this.prepareEditMav(sourceOffender, form, map);
	}

  /**
   * Submits the update to a criminal association from information in the edit
   * criminal association screen.
   * 
   * @param form criminal association form
   * @param criminalAssociation criminal association
   * @param sourceOffender source offender
   * @param result binding result
   * @return model and view
   * @throws CriminalAssociationExistsException criminal association exists
   * exception
   */
	@RequestMapping(value = "/edit.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('CRIMINAL_ASSOCIATION_EDIT') or hasRole('ADMIN')")
	  public ModelAndView updateAssociation(
	      @RequestParam(value = "association", required = true)
	      final CriminalAssociation criminalAssociation, 
	      @RequestParam(value = "sourceOffender", required = true)
	      final Offender sourceOffender, final CriminalAssociationForm form,
	      final BindingResult result) 
	      throws CriminalAssociationExistsException {
	    this.criminalAssociationFormValidator.validate(form, result);
	    if (result.hasErrors()) {
	    	ModelMap map = new ModelMap();
	    	map.addAttribute(BindingResult.MODEL_KEY_PREFIX 
	    			+ CRIMINAL_ASSOCIATION_FORM_MODEL_KEY, result);
	    	map.addAttribute(CRIMINAL_ASSOCIATION_MODEL_KEY,
	    			criminalAssociation);
	    	map.addAttribute(ASSOCIATION_OFFENDER_NUMBER_MODEL_KEY, 
	          String.valueOf(((Offender) criminalAssociation.getRelationship()
	          .getFirstPerson()).getOffenderNumber()));
	    	return this.prepareEditMav(sourceOffender, form, map);
	    }
	    CriminalAssociationFlags criminalAssociationFlags 
	        = new CriminalAssociationFlags();
	    criminalAssociationFlags.setWitness(form.getWitness());
	    this.criminalAssociationService.update(criminalAssociation,
	        new DateRange(form.getStartDate(), form.getEndDate()), 
	        form.getCriminalAssociationCategory(), criminalAssociationFlags);
	    return new ModelAndView(String.format(LIST_REDIRECT,
	        sourceOffender.getId()));
	}

  /**
   * Displays the form for the creation of a new criminal association. 
   * 
   * @param offender offender
   * @return model and view
   */
	@RequestMapping(value = "/create.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('CRIMINAL_ASSOCIATION_CREATE') or hasRole('ADMIN')")
	public ModelAndView createAssociation(@RequestParam(value = "offender", 
      required = true) final Offender offender) {
	    ModelMap map = new ModelMap();
	    CriminalAssociationForm form = new CriminalAssociationForm();
	    return this.prepareEditMav(offender, form, map);
	}

  /**
   * Submits the creation of a new criminal association for the specified 
   * offender.
   * 
   * @param form criminal association form
   * @param offender offender
   * @param result binding result
   * @return model and view
   * @throws CriminalAssociationExistsException criminal association exists
   * exception
   * @throws ReflexiveRelationshipException reflexive relationship exception
   */
	@RequestMapping(value = "/create.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('CRIMINAL_ASSOCIATION_CREATE') or hasRole('ADMIN')")
	public ModelAndView saveAssociation(
      @RequestParam(value = "offender", required = true)
      final Offender offender, 
      final CriminalAssociationForm form,
      final BindingResult result) 
    		  throws CriminalAssociationExistsException, 
    		  ReflexiveRelationshipException {
	    this.criminalAssociationFormValidator.validate(form, result);
	    if (result.hasErrors()) {
	    	ModelMap map = new ModelMap();
	    	map.addAttribute(BindingResult.MODEL_KEY_PREFIX 
	    			+ CRIMINAL_ASSOCIATION_FORM_MODEL_KEY, result);
	    	return this.prepareEditMav(offender, form, map);
	    }
	    CriminalAssociationFlags criminalAssociationFlags 
	        = new CriminalAssociationFlags();
	    criminalAssociationFlags.setWitness(form.getWitness());
	    this.criminalAssociationService.associate(offender, 
	        form.getOtherOffender(), new DateRange(form.getStartDate(), 
	        form.getEndDate()), form.getCriminalAssociationCategory(), 
	        criminalAssociationFlags);
	    return new ModelAndView(String.format(LIST_REDIRECT, offender.getId()));
	}

  /**
   * Removes the specified criminal association.
   * 
   * @param criminalAssociation criminal association
   * @param offender offender
   * @return redirect string
   */
	@RequestMapping(value = "/remove.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('CRIMINAL_ASSOCIATION_REMOVE') or hasRole('ADMIN')")
	public ModelAndView removeAssociation(@RequestParam(value = "association",
      required = true) final CriminalAssociation criminalAssociation, 
      @RequestParam(value = "offender", required = true)
      final Offender offender) {
		this.criminalAssociationService.remove(criminalAssociation);
		return new ModelAndView(String.format(LIST_REDIRECT, offender.getId()));
	}


  /**
   * Returns a view for a criminal association action menu pertaining to the 
   * specified offender.
   * 
   * @param offender offender
   * @return view for criminal association action menu
   */
	@RequestMapping(value = "/criminalAssociationActionMenu.html", 
      method = RequestMethod.GET)
  public ModelAndView criminalAssociationActionMenu(@RequestParam(
      value = "offender", required = true) final Offender offender) {
	    ModelMap map = new ModelMap();
	    map.addAttribute(OFFENDER_MODEL_KEY, offender);
	    return new ModelAndView(
          CRIMINAL_ASSOCIATION_ACTION_MENU_VIEW_NAME, map);
	}

  /**
   * Returns a view for all criminal associations action menu 
   * pertaining to the specified offender.
   * 
   * @param offender offender
   * @return model and view for criminal associations action menu
   */
	@RequestMapping(value = "/criminalAssociationsActionMenu.html",
      method = RequestMethod.GET)
	public ModelAndView criminalAssociationsActionMenu(
			@RequestParam(value = "offender", required = true) 
			final Offender offender) {
	    ModelMap map = new ModelMap();
	    map.addAttribute(OFFENDER_MODEL_KEY, offender);
	    return new ModelAndView(
      CRIMINAL_ASSOCIATIONS_ACTION_MENU_VIEW_NAME, map);
	}

  /**
   * Returns a model and view of the criminal association row action menu.
   * 
   * @param criminalAssociation criminal association
   * @return model and view
   */
	@RequestMapping(value = "/criminalAssociationsRowActionMenu.html",
      method = RequestMethod.GET)
	public ModelAndView criminalAssociationsRowActionMenu(
      @RequestParam(value = "associationSummaries", required = true)
      final CriminalAssociation criminalAssociation) {
		ModelMap map = new ModelMap();
		map.addAttribute(CRIMINAL_ASSOCIATION_MODEL_KEY, criminalAssociation);
		return new ModelAndView(
       CRIMINAL_ASSOCIATIONS_ROW_ACTION_MENU_VIEW_NAME, map);
	}

	/**
	 * Returns the report for the specified offenders criminal association.
	 * 
	 * @param offender offender
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/criminalAssociationListingReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('CRIMINAL_ASSOCIATION_LIST') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportCriminalAssociatonListing(
			@RequestParam(value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(CRIMINAL_ASSOCIATION_LISTING_ID_REPORT_PARAM_NAME,
				Long.toString(offender.getOffenderNumber()));
		byte[] doc = this.reportRunner.runReport(
				CRIMINAL_ASSOCIATION_LISTING_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/**
	 * Returns the report for the specified criminal association.
	 * 
	 * @param criminalAssociation criminal association
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/criminalAssociationDetailsReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('CRIMINAL_ASSOCIATION_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportCriminalAssociatonDetails(
			@RequestParam(value = "criminalAssociation", required = true)
			final CriminalAssociation criminalAssociation,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(CRIMINAL_ASSOCIATION_DETAILS_ID_REPORT_PARAM_NAME,
				Long.toString(criminalAssociation.getId()));
		byte[] doc = this.reportRunner.runReport(
				CRIMINAL_ASSOCIATION_DETAILS_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
  /* Helper methods. */

  /*
   * Prepares a model and view object with attributes needed to display the 
   * edit screen for the creation of a criminal association.
   * 
   * @param offender offender
   * @param form criminal association form
   * @param map model map
   * @return prepared model and view for editing a criminal association
   */
	private ModelAndView prepareEditMav(final Offender offender, 
		      final CriminalAssociationForm form,
		      final ModelMap map) {
		map.addAttribute(CATEGORIES_MODEL_KEY, this.criminalAssociationService
				.findCategories());
		map.addAttribute(CRIMINAL_ASSOCIATION_FORM_MODEL_KEY, form);
		map.addAttribute(OFFENDER_SUMMARY_MODEL_KEY, this.offenderReportService
				.summarizeOffender(offender));
		this.offenderSummaryModelDelegate.add(map, offender);
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		return new ModelAndView(EDIT_VIEW_NAME, map);
	}

  /* 
   * Prepares the values of the association form with the values of the 
   * specified association.
   */
	private void prepareAssociationEditForm(
      final CriminalAssociationForm criminalAssociationForm,
      final CriminalAssociation criminalAssociation) {
		criminalAssociationForm.setCriminalAssociationCategory(
				criminalAssociation.getCriminalAssociationCategory());
		if (criminalAssociation.getDateRange() != null) {
			if (criminalAssociation.getDateRange().getStartDate() == null) {
				criminalAssociationForm.setStartDate(null);
			} else {
				criminalAssociationForm.setStartDate(criminalAssociation
						.getDateRange().getStartDate());
			}
			if (criminalAssociation.getDateRange().getEndDate() == null) {
				criminalAssociationForm.setEndDate(null);
			} else {
				criminalAssociationForm.setEndDate(criminalAssociation
						.getDateRange().getEndDate());
			} 
		} else {
			criminalAssociationForm.setStartDate(null);
			criminalAssociationForm.setEndDate(null);
		}
		criminalAssociationForm.setOtherOffender(((Offender)
				criminalAssociation.getRelationship().getSecondPerson()));
		if (criminalAssociation.getCriminalAssociationFlags() != null) {
			criminalAssociationForm.setWitness(criminalAssociation
					.getCriminalAssociationFlags().getWitness());
		} else {
			criminalAssociationForm.setWitness(null);
		}
	}
  
	/**
	 * Handles criminal association exists exception.
	 * 
	 * @param exception exception
	 * @return business exception error
	 */
	@ExceptionHandler(CriminalAssociationExistsException.class)
	public ModelAndView handleCriminalAssociationExistsException(
			final CriminalAssociationExistsException exception) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
				ASSOCIATION_EXISTS_MESSAGE_KEY, ERROR_BUNDLE_NAME, 
				exception); 
	}

	/**
	 * Reflexive relationship found exception.
	 * 
	 * @param exception exception
	 * @return business exception error
	 */
	@ExceptionHandler(ReflexiveRelationshipException.class)
  	public ModelAndView handleReflexiveRelationshipFoundException(
  			final ReflexiveRelationshipException exception) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
				REFLEXIVE_RELATIONSHIP_EXISTS_MESSAGE_KEY, ERROR_BUNDLE_NAME, 
				exception); 
	}
  
  /**
   * Sets up and registers property editors.
   * 
   * @param binder web binder
   */
	@InitBinder
	  protected void initBinder(final WebDataBinder binder) {
	    binder.registerCustomEditor(CriminalAssociation.class,
	        this.criminalAssociationPropertyEditorFactory
	          .createPropertyEditor());
	    binder.registerCustomEditor(CriminalAssociationCategory.class,
	        this.criminalAssociationCategoryPropertyEditorFactory
	           .createPropertyEditor());
	    binder.registerCustomEditor(Offender.class,
	        this.offenderPropertyEditorFactory.createOffenderPropertyEditor());
	    binder.registerCustomEditor(Date.class, "startDate",
	        this.customDateEditorFactory.createCustomDateOnlyEditor(true));
	    binder.registerCustomEditor(Date.class, "endDate", 
	        this.customDateEditorFactory.createCustomDateOnlyEditor(true));
	}  
}