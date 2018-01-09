package omis.supervisionfee.web.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.content.RequestContentMapping;
import omis.content.RequestContentType;
import omis.court.domain.Court;
import omis.datatype.DateRange;
import omis.exception.DateConflictException;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.person.domain.Person;
import omis.report.ReportFormat;
import omis.report.ReportRunner;
import omis.report.web.controller.delegate.ReportControllerDelegate;
import omis.supervisionfee.domain.MonthlySupervisionFee;
import omis.supervisionfee.domain.MonthlySupervisionFeeAuthorityCategory;
import omis.supervisionfee.domain.SupervisionFeeRequirement;
import omis.supervisionfee.domain.SupervisionFeeRequirementReason;
import omis.supervisionfee.report.SupervisionFeeSummary;
import omis.supervisionfee.report.SupervisionFeeSummaryReportService;
import omis.supervisionfee.service.SupervisionFeeManager;
import omis.supervisionfee.web.form.FeeRequirementItem;
import omis.supervisionfee.web.form.MonthlySupervisionFeeForm;
import omis.supervisionfee.web.form.SupervisionFeeRequirementItemAuthority;
import omis.supervisionfee.web.form.SupervisionFeeRequirementItemOperation;
import omis.supervisionfee.web.validator.MonthlySupervisionFeeFormValidator;

/**
 * Supervision fee controller for supervision fee related operations.
 * 
 * @author Sheronda Vaughn
 * @version 0.1.0 (Sep 3, 2014)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/supervisionFee")
@PreAuthorize("hasRole('USER')")
public class SupervisionFeeController {
	
	/* Redirect view names. */
	private static final String LIST_REDIRECT_VIEW_NAME 
					= "redirect:/supervisionFee/list.html?offender=%d";
	
	/* View names. */
	private static final String LIST_VIEW_NAME = "supervisionFee/list";
	private static final String EDIT_VIEW_NAME = "supervisionFee/edit";
	private static final String SUPERVISION_FEES_ACTION_MENU_VIEW_NAME
					= "supervisionFee/includes/supervisionFeesActionMenu";
	private static final String SUPERVISION_FEE_ACTION_MENU_VIEW_NAME
					= "supervisionFee/includes/supervisionFeeActionMenu";
	private static final String SUPERVISION_FEES_ROW_ACTION_MENU_VIEW_NAME
		= "supervisionFee/includes/supervisionFeesRowActionMenu";
	
	/* Model keys. */
	private static final String OFFENDER_MODEL_KEY = "offender";
	private static final String MONTHLY_SUPERVISION_FEE_FORM_MODEL_KEY 
					= "supervisionFeeForm";
	private static final String FEE_REQUIREMENT_INDEX_MODEL_KEY 
					= "feeRequirementIndex";	
	private static final String CURRENT_FEE_REQUIREMENT_INDEX_MODEL_KEY 
					= "currentFeeRequirementIndex";	
	private static final String EFFECTIVE_DATE_MODEL_KEY = "effectiveDate";
	private static final String FEE_REQUIREMENT_ITEM_MODEL_KEY 
					= "feeRequirement";
	private static final String COURTS_MODEL_KEY = "courts";
	private static final String REASONS_MODEL_KEY = "reasons";
	private static final String SUPERVISION_FEE_SUMMARIES_MODEL_KEY 
					= "supervisionFeeSummaries";
	private static final String MONTHLY_SUPERVISION_FEE_CATEGORY_MODEL_KEY
					= "authorityCategories";
	private static final String MONTHLY_SUPERVISION_FEE_MODEL_KEY
		= "monthlySupervisionFee";
	
	/* Services. */
	@Autowired
	@Qualifier("supervisionFeeSummaryReportService")
	private SupervisionFeeSummaryReportService 
		supervisionFeeSummaryReportService;
	
	@Autowired
	@Qualifier("supervisionFeeManager")
	private SupervisionFeeManager supervisionFeeManager;
		
	/* Helpers. */
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;

	/* Property editors. */
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;
	
	@Autowired
	@Qualifier("personPropertyEditorFactory")
	private PropertyEditorFactory personPropertyEditorFactory;
	
	@Autowired
	@Qualifier("monthlySupervisionFeePropertyEditorFactory")
	private PropertyEditorFactory monthlySupervisionFeePropertyEditorFactory;
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	@Qualifier("supervisionFeeRequirementReasonPropertyEditorFactory")
	private  PropertyEditorFactory 
					supervisionFeeRequirementReasonPropertyEditoryFactory;
	
	@Autowired
	@Qualifier("courtPropertyEditorFactory")
	private PropertyEditorFactory courtPropertyEditorFactory;
	
	@Autowired
	@Qualifier("supervisionFeeRequirementPropertyEditorFactory")
	private PropertyEditorFactory 
					supervisionFeeRequirementPropertyEditorFactory;
	
	/* Validators. */
	@Autowired
	@Qualifier("monthlySupervisionFeeFormValidator")
	private MonthlySupervisionFeeFormValidator 
					monthlySupervisionFeeFormValidator;
	
	/* Report names. */
	
	private static final String SUPERVISION_FEE_LISTING_REPORT_NAME 
		= "/Compliance/SupervisionFees/Supervision_Fees_Listing";

	private static final String SUPERVISION_FEE_DETAILS_REPORT_NAME 
		= "/Compliance/SupervisionFees/Supervision_Fees_Details";
	
	private static final String SUPERVISION_FEE_MODIFICATIONS_REPORT_NAME
	 	= "/Compliance/SupervisionFees/Supervision_Fee_Modifications";
	
	private static final String SUPERVISION_FEE_WAIVER_REQUEST_REPORT_NAME
 		= "/Compliance/SupervisionFees/Supervision_Fee_Waiver_Request";
	
	private static final String 
		SUPERVISION_FEE_OFFENDER_RIGHTS_RESPONSIBILITIES_REPORT_NAME
		= "/Compliance/SupervisionFees/Supervision_Fee_Offender_Rights_and_Responsibilities";

	/* Report parameter names. */
	
	private static final String SUPERVISION_FEE_LISTING_ID_REPORT_PARAM_NAME 
		= "DOC_ID";

	private static final String SUPERVISION_FEE_DETAILS_ID_REPORT_PARAM_NAME 
		= "SUP_FEE_ID";
	
	/* Report runners. */
	
	@Autowired
	@Qualifier("reportRunner")
	private ReportRunner reportRunner;
	
	/* Controller delegates. */
	
	@Autowired
	@Qualifier("reportControllerDelegate")
	private ReportControllerDelegate reportControllerDelegate;
	
	/* Constructors. */
	
	/** Instantiates a default controller for supervision fees. */
	public SupervisionFeeController() {
		//Default controller
	}
	
	/* Screens */

	/**
	 * Displays a list of monthly supervision fees for a specified offender.
	 * 
	 * @param offender offender
	 * @return model and view to a list of monthly supervision fees
	 */
	@RequestContentMapping(nameKey = "supervisionFeeListingScreenName",
					descriptionKey = "supervisionFeeListingScreenDescription",
					messageBundle = "omis.supervisionfee.msgs.supervisionFee",
					screenType = RequestContentType.LISTING_SCREEN)
	@RequestMapping("list.html")
	@PreAuthorize("hasRole('SUPERVISION_FEE_LIST') or hasRole('ADMIN')")
	public ModelAndView list(
			@RequestParam(value = "offender", required = true)
					final Offender offender) {
		List<SupervisionFeeSummary> supervisionFeeSummaries
						= this.supervisionFeeSummaryReportService
						.findByOffender(offender, new Date());
		ModelAndView mav = new ModelAndView(LIST_VIEW_NAME);		
		mav.addObject(SUPERVISION_FEE_SUMMARIES_MODEL_KEY, 
						supervisionFeeSummaries);
		mav.addObject(OFFENDER_MODEL_KEY, offender);
		mav.addObject(EFFECTIVE_DATE_MODEL_KEY, new Date());
		this.offenderSummaryModelDelegate.add(mav.getModelMap(), offender);
		return mav;
	}
	
	/**
	 * Displays a form allowing a new monthly supervision fee to be created.
	 * 
	 * @param offender offender
	 * @return model and view screen that allows a new monthly
	 * supervision fee to be created
	 */
	@RequestMapping(value = "create.html",
					method = RequestMethod.GET)
	@PreAuthorize("hasRole('SUPERVISION_FEE_CREATE') or hasRole('ADMIN')")
	public ModelAndView create(
			@RequestParam(value = "offender", required = true)
					final Offender offender) {
		int feeRequirementIndex = 0;
		
		MonthlySupervisionFeeForm supervisionFeeForm = new 
						MonthlySupervisionFeeForm();		
		ModelAndView mav = this.prepareMav(
						supervisionFeeForm, offender, feeRequirementIndex);
		return mav;
	}
	
	/**
	 * Updates the specified monthly supervision fee with the values from the 
	 * the current form view.
	 * 
	 * @param monthlySupervisionFee monthly supervision fee 
	 * @param supervisionFeeForm edit monthly supervision fee form
	 * @param result binding result
	 * @return model and view to redirect to list URL
	 * @throws DateConflictException if an attempt to save a monthly 
	 * supervision fee within the date range of an existing one
	 */
	@RequestMapping(value = "edit.html",
					method = RequestMethod.POST)
	@PreAuthorize("hasRole('SUPERVISION_FEE_EDIT') or hasRole('ADMIN')")
	public ModelAndView update(
			@RequestParam(value = "supervisionFeeSummaries", required = true)
						final MonthlySupervisionFee monthlySupervisionFee,
						final MonthlySupervisionFeeForm supervisionFeeForm,
						final BindingResult result) 
								throws DateConflictException {
		this.monthlySupervisionFeeFormValidator.validate(supervisionFeeForm, 
						result);
		if (result.hasErrors()) {
			ModelAndView mav = this.prepareRedisplayMav(
							supervisionFeeForm, result, 
							monthlySupervisionFee.getOffender());
			return mav; 
		}
		MonthlySupervisionFee updatedFee = this.supervisionFeeManager
						.updateMonthlySupervisionFee(monthlySupervisionFee, 
						 supervisionFeeForm.getFee(), 
						 supervisionFeeForm.getStartDate(), 
						 supervisionFeeForm.getEndDate(),
						 supervisionFeeForm.getComment());
		for (FeeRequirementItem feeRequirementItem 
							: supervisionFeeForm.getFeeRequirements()) {
			if (SupervisionFeeRequirementItemOperation.UPDATE.equals(
							feeRequirementItem.getOperation())) {
				this.populateUpdateFeeRequirementItem(
									feeRequirementItem, 
									feeRequirementItem.getFeeRequirement());	
			} else if (SupervisionFeeRequirementItemOperation.CREATE
							.equals(
									feeRequirementItem.getOperation())) {
				this.populateImposeSupervisionFeeRequirement(
									feeRequirementItem.getFeeRequirement(), 
									updatedFee, feeRequirementItem);
			} else if (SupervisionFeeRequirementItemOperation.REMOVE
							.equals(
									feeRequirementItem.getOperation())) {
				this.supervisionFeeManager
								.removeSupervisionFeeRequirement(
									feeRequirementItem.getFeeRequirement());
			} else {
				throw new UnsupportedOperationException(
							"Operation not supported: " 
									+ feeRequirementItem.getOperation());
			}
		}
		return new ModelAndView(String.format(LIST_REDIRECT_VIEW_NAME, 
				updatedFee.getOffender().getId()));
	}
	
	/**
	 * Displays a form allowing an existing monthly supervision 
	 * fee to be edited.
	 * 
	 * @param monthlySupervisionFee monthly supervision fee 
	 * @return model and view screen that allows an existing monthly
	 * supervision fee to be edited
	 */
	@RequestMapping(value = "edit.html",
					method = RequestMethod.GET)
	@PreAuthorize("hasRole('SUPERVISION_FEE_VIEW') or hasRole('ADMIN')")
	public ModelAndView edit(
			@RequestParam(value = "supervisionFeeSummaries", 
					required = true)			
					final MonthlySupervisionFee monthlySupervisionFee) {
		MonthlySupervisionFeeForm supervisionFeeForm = 
						new MonthlySupervisionFeeForm();
		supervisionFeeForm.setFee(monthlySupervisionFee.getFee());
		supervisionFeeForm.setStartDate(
						monthlySupervisionFee.getDateRange().getStartDate());
		supervisionFeeForm.setEndDate(
						monthlySupervisionFee.getDateRange().getEndDate());
		supervisionFeeForm.setAuthorityCategory(
						monthlySupervisionFee.getAuthorityCategory());
		supervisionFeeForm.setComment(monthlySupervisionFee.getComment());
		int currentFeeRequirementIndex = 0;
		List<FeeRequirementItem> feeRequirementItems = 
						new ArrayList<FeeRequirementItem>();
		List<SupervisionFeeRequirement> requirements = 
						this.supervisionFeeManager
						.findSupervisionFeeRequirementsByMonthlySupervisionFee(
						monthlySupervisionFee);
		for (SupervisionFeeRequirement requirement : requirements) {
			FeeRequirementItem feeRequirementItem = new FeeRequirementItem();
			if (requirement.getOfficer() != null) {
				feeRequirementItem.setItemAuthority(
								SupervisionFeeRequirementItemAuthority.OFFICER);
				feeRequirementItem.setOfficer(requirement.getOfficer());	
			} else if (requirement.getCourt() != null) {
				feeRequirementItem.setItemAuthority(
								SupervisionFeeRequirementItemAuthority.COURT);
				feeRequirementItem.setCourt(requirement.getCourt());
			} else {
				throw new IllegalStateException(
						"Illegal authority");
			}			
			feeRequirementItem.setOperation(
					  SupervisionFeeRequirementItemOperation.UPDATE);
			this.populateEditFeeRequirementItem(
					  feeRequirementItem, requirement);
			feeRequirementItems.add(currentFeeRequirementIndex, 
					  feeRequirementItem);		
			currentFeeRequirementIndex++;
		}
		supervisionFeeForm.setFeeRequirements(feeRequirementItems);		
		ModelAndView mav = this.prepareMav(
						supervisionFeeForm, monthlySupervisionFee.getOffender(),
						currentFeeRequirementIndex);
		mav.addObject(MONTHLY_SUPERVISION_FEE_MODEL_KEY, monthlySupervisionFee);
		return mav;		
	}
	
	/**
	 * Saves a new monthly supervision fee.
	 * 
	 * @param offender offender 
	 * @param supervisionFeeForm supervision fee form
	 * @param result result
	 * @return model and view to redirect to list URL
	 * @throws DateConflictException if an attempt to save a monthly 
	 * supervision fee within the date range of an existing one
	 */
	@RequestContentMapping(nameKey = "supervisionFeeCreateSubmitName",
					descriptionKey = "supervisionFeeCreateSubmitDescription",
					messageBundle = "omis.supervisionFee.msgs.supervisionFee",
					screenType = RequestContentType.FORM_SUBMIT)
	@RequestMapping(value = "create.html",
					method = RequestMethod.POST)
	@PreAuthorize("hasRole('SUPERVISION_FEE_CREATE') or hasRole('ADMIN')")
	public ModelAndView save(
			@RequestParam(value = "offender", required = true)			
					final Offender offender,
					final MonthlySupervisionFeeForm supervisionFeeForm,
					final BindingResult result) throws DateConflictException {
		this.monthlySupervisionFeeFormValidator.validate(supervisionFeeForm, 
						result);
		if (result.hasErrors()) {					
			ModelAndView mav = this.prepareRedisplayMav(
							supervisionFeeForm, result, offender);	
			return mav; 
		}

		MonthlySupervisionFee imposedMonthlySupervisionFee = 
						this.supervisionFeeManager.imposeMonthlySupervisionFee(
						offender, supervisionFeeForm.getFee(), 
						supervisionFeeForm.getStartDate(), 
						supervisionFeeForm.getEndDate(),
						supervisionFeeForm.getAuthorityCategory(),
						supervisionFeeForm.getComment());
		for (FeeRequirementItem feeRequirementItem 
						: supervisionFeeForm.getFeeRequirements()) {	
			if (SupervisionFeeRequirementItemOperation.CREATE.equals(
							feeRequirementItem.getOperation())) {
				this.populateImposeSupervisionFeeRequirement(
								feeRequirementItem.getFeeRequirement(),
								imposedMonthlySupervisionFee, 
								feeRequirementItem);
			} else {
				throw new UnsupportedOperationException(
						"Operation not supported: " 
								+ feeRequirementItem.getOperation());
			}
		}
		return new ModelAndView(String.format(LIST_REDIRECT_VIEW_NAME, 
		imposedMonthlySupervisionFee.getOffender().getId()));  	
	}	
			
	/**
	 * Removes a monthly supervision fee and associated 
	 * supervision fee requirements.
	 *  
	 * @param monthlySupervisionFee monthly supervision fee
	 * @return model and view to redirect to list URL
	 */
	@RequestMapping(value = "remove.html",
					method = RequestMethod.GET)	
	@PreAuthorize("hasRole('SUPERVISION_FEE_REMOVE') or hasRole('ADMIN')")
	public ModelAndView remove(
			@RequestParam(value = "supervisionFeeSummaries", required = true)
					final MonthlySupervisionFee monthlySupervisionFee) {
		Offender offender = monthlySupervisionFee.getOffender();
		List<SupervisionFeeRequirement> requirements = 
						this.supervisionFeeManager
						.findSupervisionFeeRequirementsByMonthlySupervisionFee(
						monthlySupervisionFee);
		for (SupervisionFeeRequirement requirement : requirements) {
			this.supervisionFeeManager.removeSupervisionFeeRequirement(
							requirement);
		}
		this.supervisionFeeManager.removeMonthlySupervisionFee(
						monthlySupervisionFee);
		return new ModelAndView(String.format(LIST_REDIRECT_VIEW_NAME,
				offender.getId()));
	}
	
	/**
	 * Displays a table row of officer fee requirement inputs.
	 * 
	 * @param feeRequirementIndex index of fee requirement row for input
	 * @return table row of fee requirement inputs
	 * @throws DateConflictException date conflict exception
	 */
	@RequestContentMapping(nameKey = "addOfficerFeeRequirementName",
					descriptionKey = "addOfficerFeeRequirementDescription",
					messageBundle = "omis.supervisionFee.msgs.supervisionFee",
					screenType = RequestContentType.AJAX_REQUEST)
	@RequestMapping(value = "addOfficerFeeRequirement.html", 
					method = RequestMethod.GET)
	public ModelAndView addOfficerFeeRequirement(
			@RequestParam(value = "feeRequirementIndex", required = true)
					final int feeRequirementIndex) 
					throws DateConflictException {
		FeeRequirementItem item = new FeeRequirementItem();
		item.setOperation(SupervisionFeeRequirementItemOperation.CREATE);
		item.setItemAuthority(SupervisionFeeRequirementItemAuthority.OFFICER);
		ModelAndView mav = new ModelAndView(
						"supervisionFee/includes/feeRequirementTableRow");
		mav.addObject(FEE_REQUIREMENT_INDEX_MODEL_KEY, feeRequirementIndex);
		mav.addObject(FEE_REQUIREMENT_ITEM_MODEL_KEY, item);
		mav.addObject(REASONS_MODEL_KEY, this.supervisionFeeManager
							.findAllSupervisionFeeRequirementReasons());
		return mav;	
	}
	
	/**
	 * Displays a table row of court fee requirement inputs.
	 * 
	 * @param feeRequirementIndex index of fee requirement row for input
	 * @return table row of fee requirement inputs
	 * @throws DateConflictException date conflict exception
	 */
	@RequestContentMapping(nameKey = "addCourtFeeRequirementName",
					descriptionKey = "addCourtFeeRequirementDescription",
					messageBundle = "omis.supervisionFee.msgs.supervisionFee",
					screenType = RequestContentType.AJAX_REQUEST)
	@RequestMapping(value = "addCourtFeeRequirement.html", 
					method = RequestMethod.GET)
	public ModelAndView addCourtFeeRequirement(
			@RequestParam(value = "feeRequirementIndex", required = true)
					final int feeRequirementIndex) 
					throws DateConflictException {	
		FeeRequirementItem item = new FeeRequirementItem();
		item.setOperation(SupervisionFeeRequirementItemOperation.CREATE);
		item.setItemAuthority(SupervisionFeeRequirementItemAuthority.COURT);
		ModelAndView mav = new ModelAndView(
						"supervisionFee/includes/feeRequirementTableRow");
		mav.addObject(FEE_REQUIREMENT_INDEX_MODEL_KEY, 
						feeRequirementIndex);
		mav.addObject(FEE_REQUIREMENT_ITEM_MODEL_KEY, item);
		mav.addObject(COURTS_MODEL_KEY, 
						this.supervisionFeeManager.findAllCourts());
		mav.addObject(REASONS_MODEL_KEY, this.supervisionFeeManager
						.findAllSupervisionFeeRequirementReasons());
		return mav;	
	}
	
	/**
	 * Returns a view for a supervision fees action menu pertaining to the 
	 * specified offender.
	 * 
	 * @param offender person
	 * @return model and view for supervision fees action menu
	 */
	@RequestMapping(value = "/supervisionFeesActionMenu.html",
					method = RequestMethod.GET)
	public ModelAndView supervisionFeesActionMenu(
			@RequestParam(value = "offender", required = true) 
					final Person offender) {
		ModelMap map = new ModelMap();
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		return new ModelAndView(SUPERVISION_FEES_ACTION_MENU_VIEW_NAME, map);
	}

	/**
	 * Returns a view for a supervision fee action menu pertaining to the 
	 * specified offender.
	 * 
	 * @param offender person
	 * @return model and view for supervision fee action menu
	 */
	@RequestMapping(value = "/supervisionFeeActionMenu.html",
					method = RequestMethod.GET)
	public ModelAndView supervisionFeeActionMenu(
			@RequestParam(value = "offender", required = true) 
					final Person offender) {
		ModelMap map = new ModelMap();
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		return new ModelAndView(SUPERVISION_FEE_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Returns a view for a supervision fees row action menu pertaining to the 
	 * specified supervision fee.
	 * 
	 * @param monthlySupervisionFee person
	 * @return model and view for supervision fee action menu
	 */
	@RequestMapping(value = "/supervisionFeesRowActionMenu.html",
					method = RequestMethod.GET)
	public ModelAndView supervisionFeesRowActionMenu(
			@RequestParam(value = "supervisionFeeSummaries", required = true) 
					final MonthlySupervisionFee monthlySupervisionFee) {
		ModelMap map = new ModelMap();
		map.addAttribute(MONTHLY_SUPERVISION_FEE_MODEL_KEY, monthlySupervisionFee);
		return new ModelAndView(SUPERVISION_FEES_ROW_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Populates the supervision fee requirement with the specified values from
	 * the fee requirement item. 
	 * 
	 * @param feeRequirementItem fee requirement item
	 * @param requirement supervision fee requirement
	 * @return fee requirement item
	 */
	private FeeRequirementItem populateEditFeeRequirementItem(
					final FeeRequirementItem feeRequirementItem, 
					final SupervisionFeeRequirement requirement) {		
		feeRequirementItem.setFee(requirement.getFee());
		DateRange dateRange = requirement.getDateRange();
		feeRequirementItem.setStartDate(dateRange.getStartDate());
		feeRequirementItem.setEndDate(dateRange.getEndDate());			
		feeRequirementItem.setFeeRequirementId(requirement.getId());
		feeRequirementItem.setComment(requirement.getComment());
		feeRequirementItem.setReason(requirement.getReason());
		feeRequirementItem.setFeeRequirement(requirement);
		return feeRequirementItem;		
	}
	
	/**
	 * Populates the updated supervision requirement with the specified values
	 * from the fee requirement item. 
	 * 
	 * @param feeRequirementItem fee requirement item
	 * @param requirement supervision fee requirement
	 * @return fee requirement item
	 * @throws DateConflictException
	 */
	private FeeRequirementItem populateUpdateFeeRequirementItem(
					final FeeRequirementItem feeRequirementItem, 
					final SupervisionFeeRequirement requirement) 
					throws DateConflictException {
		this.supervisionFeeManager.updateSupervisionFeeRequirement(
						feeRequirementItem.getFeeRequirement(),
						feeRequirementItem.getFee(), 
						feeRequirementItem.getReason(),
						feeRequirementItem.getStartDate(), 
						feeRequirementItem.getEndDate(), 
						feeRequirementItem.getComment());
		return feeRequirementItem;
	}
	
	/**
	 * Model and view of the supervision fee form and its fee requirements.
	 * 
	 * @param supervisionFeeForm supervision fee form
	 * @param offender offender
	 * @param feeRequirementIndex fee requirement index
	 * @return model and view
	 */
	private ModelAndView prepareMav(
					final MonthlySupervisionFeeForm supervisionFeeForm,
					final Offender offender, final int feeRequirementIndex) {
		
		ModelAndView mav = new ModelAndView(EDIT_VIEW_NAME);
		mav.addObject(MONTHLY_SUPERVISION_FEE_CATEGORY_MODEL_KEY, 
					MonthlySupervisionFeeAuthorityCategory.values());
		mav.addObject(MONTHLY_SUPERVISION_FEE_FORM_MODEL_KEY, 
						supervisionFeeForm);
		mav.addObject(CURRENT_FEE_REQUIREMENT_INDEX_MODEL_KEY, 
							feeRequirementIndex);
		mav.addObject(REASONS_MODEL_KEY, this.supervisionFeeManager
						.findAllSupervisionFeeRequirementReasons());
		mav.addObject(COURTS_MODEL_KEY, this.supervisionFeeManager
						.findAllCourts());
		mav.addObject(OFFENDER_MODEL_KEY, offender);
		this.offenderSummaryModelDelegate.add(mav.getModelMap(), offender);		
		
		return mav;
	}	
	
	/**
	 * Model and view of the supervision fee form with binding results.
	 * 
	 * @param supervisionFeeForm supervision fee form
	 * @param result binding results
	 * @param offender offender
	 * @return model and view
	 */
	private ModelAndView prepareRedisplayMav(
					final MonthlySupervisionFeeForm supervisionFeeForm, 
					final BindingResult result, final Offender offender) {
						
		int feeRequirementCount;
		if (supervisionFeeForm.getFeeRequirements() == null) {
			feeRequirementCount = 0;
		} else {
			feeRequirementCount = supervisionFeeForm
						.getFeeRequirements().size();
		}
		
		ModelAndView mav = this.prepareMav(
						supervisionFeeForm, offender, feeRequirementCount);
		mav.addObject(BindingResult.MODEL_KEY_PREFIX 
						+ MONTHLY_SUPERVISION_FEE_FORM_MODEL_KEY, result);		
		return mav;
	}
	
	/**
	 * Populate an imposed supervision fee requirement per the authority
	 * imposing it.
	 * 
	 * @param supervisionFeeRequirement supervision fee requirement
	 * @param monthlySupervisionFee monthly supervision fee
	 * @param feeRequirementItem fee requirement item
	 * @return supervision fee requirement
	 * @throws DateConflictException
	 */
	private SupervisionFeeRequirement populateImposeSupervisionFeeRequirement(
					final SupervisionFeeRequirement supervisionFeeRequirement,
					final MonthlySupervisionFee monthlySupervisionFee, 
					final FeeRequirementItem feeRequirementItem)
		throws DateConflictException {
		if (SupervisionFeeRequirementItemAuthority.OFFICER.equals(
						feeRequirementItem.getItemAuthority())) {
			this.supervisionFeeManager.imposeSupervisionFeeRequirement(
							monthlySupervisionFee, 
							feeRequirementItem.getFee(), 
							feeRequirementItem.getOfficer(), 
							feeRequirementItem.getStartDate(), 
							feeRequirementItem.getEndDate(), 
							feeRequirementItem.getComment(), 
							feeRequirementItem.getReason());					
		} else if (SupervisionFeeRequirementItemAuthority.COURT.equals(
							feeRequirementItem.getItemAuthority())) {
			this.supervisionFeeManager.imposeSupervisionFeeRequirement(
							monthlySupervisionFee, 
							feeRequirementItem.getFee(),
							feeRequirementItem.getCourt(), 
							feeRequirementItem.getStartDate(),
							feeRequirementItem.getEndDate(),
							feeRequirementItem.getComment(),
							feeRequirementItem.getReason());
		} else {
			throw new UnsupportedOperationException(
					"Unsuppported Authority" 
							+ feeRequirementItem.getItemAuthority());
		}
		return supervisionFeeRequirement; 
	}			
	
/* Reports. */
	
	/**
	 * Returns the report for the specified offenders supervision fees.
	 * 
	 * @param offender offender
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/supervisionFeeListingReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('SUPERVISION_FEE_LIST') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportSupervisionFeeListing(@RequestParam(
			value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(SUPERVISION_FEE_LISTING_ID_REPORT_PARAM_NAME,
				Long.toString(offender.getOffenderNumber()));
		byte[] doc = this.reportRunner.runReport(
				SUPERVISION_FEE_LISTING_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/**
	 * Returns the report for the specified supervision fee.
	 * 
	 * @param monthlySupervisionFee monthly supervision fee
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/supervisionFeeDetailsReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('SUPERVISION_FEE_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportSupervisionFeeDetails(@RequestParam(
			value = "monthlySupervisionFee", required = true)
			final MonthlySupervisionFee monthlySupervisionFee,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(SUPERVISION_FEE_DETAILS_ID_REPORT_PARAM_NAME,
				Long.toString(monthlySupervisionFee.getId()));
		byte[] doc = this.reportRunner.runReport(
				SUPERVISION_FEE_DETAILS_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/**
	 * Returns the modification report for the specified supervision fee.
	 * 
	 * @param monthlySupervisionFee monthly supervision fee
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/supervisionFeeModificationsReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('SUPERVISION_FEE_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportSupervisionFeeModifications(
			@RequestParam(value = "monthlySupervisionFee", required = true)
			final MonthlySupervisionFee monthlySupervisionFee,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(SUPERVISION_FEE_DETAILS_ID_REPORT_PARAM_NAME,
				Long.toString(monthlySupervisionFee.getId()));
		byte[] doc = this.reportRunner.runReport(
				SUPERVISION_FEE_MODIFICATIONS_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/**
	 * Returns the fee waiver report for the specified supervision fee.
	 * 
	 * @param monthlySupervisionFee monthly supervision fee
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/supervisionFeeWaiverRequestReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('SUPERVISION_FEE_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportSupervisionFeeWaiverRequest(
			@RequestParam(value = "monthlySupervisionFee", required = true)
			final MonthlySupervisionFee monthlySupervisionFee,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(SUPERVISION_FEE_DETAILS_ID_REPORT_PARAM_NAME,
				Long.toString(monthlySupervisionFee.getId()));
		byte[] doc = this.reportRunner.runReport(
				SUPERVISION_FEE_WAIVER_REQUEST_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/**
	 * Returns the offender rights and responsibilities report for the specified 
	 * supervision fee.
	 * 
	 * @param monthlySupervisionFee monthly supervision fee
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(
			value = "/supervisionFeeOffenderRightsResponsibilitiesReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('SUPERVISION_FEE_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> 
		reportSupervisionFeeOffenderRightsResponsibilities(@RequestParam(
			value = "monthlySupervisionFee", required = true)
			final MonthlySupervisionFee monthlySupervisionFee,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(SUPERVISION_FEE_DETAILS_ID_REPORT_PARAM_NAME,
				Long.toString(monthlySupervisionFee.getId()));
		byte[] doc = this.reportRunner.runReport(
				SUPERVISION_FEE_OFFENDER_RIGHTS_RESPONSIBILITIES_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	/* Init binder. */
	
	/**
	 * Sets up and registers property editors.
	 * 
	 * @param binder web binder
	 */
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(Offender.class, 
						this.offenderPropertyEditorFactory
						.createOffenderPropertyEditor());
		binder.registerCustomEditor(Person.class, 
						this.personPropertyEditorFactory
						.createPropertyEditor());
		binder.registerCustomEditor(MonthlySupervisionFee.class, 
						this.monthlySupervisionFeePropertyEditorFactory
						.createPropertyEditor());
		binder.registerCustomEditor(Date.class,
						this.customDateEditorFactory
						.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(SupervisionFeeRequirementReason.class, 
					this.supervisionFeeRequirementReasonPropertyEditoryFactory
						.createPropertyEditor());
		binder.registerCustomEditor(Court.class, 
						this.courtPropertyEditorFactory
						.createPropertyEditor());
		binder.registerCustomEditor(SupervisionFeeRequirement.class, 
						this.supervisionFeeRequirementPropertyEditorFactory
						.createPropertyEditor());
	}
}
