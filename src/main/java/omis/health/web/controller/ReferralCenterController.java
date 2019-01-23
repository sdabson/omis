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

package omis.health.web.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.config.util.FeatureToggles;
import omis.content.RequestContentMapping;
import omis.content.RequestContentType;
import omis.facility.domain.Facility;
import omis.health.domain.ExternalReferral;
import omis.health.domain.ExternalReferralAuthorization;
import omis.health.domain.ExternalReferralAuthorizationRequest;
import omis.health.domain.HealthAppointmentStatus;
import omis.health.domain.HealthRequest;
import omis.health.domain.InternalReferral;
import omis.health.report.AuthorizedReferralSummary;
import omis.health.report.AuthorizedReferralSummaryReportService;
import omis.health.report.HealthFacilityReportService;
import omis.health.report.HealthRequestReportService;
import omis.health.report.HealthRequestSummary;
import omis.health.report.PendingReferralAuthorizationSummary;
import omis.health.report.PendingReferralAuthorizationSummaryReportService;
import omis.health.report.ReferralSummary;
import omis.health.report.ReferralSummaryReportService;
import omis.health.service.HealthReportService;
import omis.health.service.ReferralCenterService;
import omis.health.web.form.ReferralCenterFilterForm;
import omis.health.web.form.ReferralType;
import omis.health.web.form.ScheduleInternalReferralForm;
import omis.health.web.form.SearchResultType;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.report.ReportFormat;
import omis.user.domain.UserAccount;
import omis.web.http.SpringHeadersFactory;

/** Referral Center for referral related operations.
 * @author Ryan Johns
 * @author Stephen Abson
 * @author Joel Norris
 * @author Yidong Li
 * @version 0.1.0 (Nov. 26, 2018)
 * @since OMIS 3.0 */
@Controller
@RequestMapping("/health/referral/")
@PreAuthorize("hasRole('ADMIN') or hasRole('HEALTH_ADMIN')"
    + " or (hasRole('USER') and hasRole('HEALTH_MODULE'))")
public class ReferralCenterController {
	/* Global vari */
	private static final long COUNT_LIMIT = 30;
	   
	/* View names. */
	private static final String FACILITY_REFERRAL_CENTER_VIEW =
		"/health/referral/referralCenter";
	
	private static final String FACILITY_SELECT_VIEW =
		"/health/referral/selectFacility";
	
	private static final String FACILITY_ACTION_MENU_VIEW =
		"/health/referral/includes/facilityActionMenu";
	
	private static final String FACILITY_REFERRAL_CENTER_ACTION_MENU_VIEW =
		"/health/referral/includes/actionMenu";
	
	private static final String FACILITY_SELECT_LIST_VIEW =
		"/health/referral/includes/facilityList";
	
	private static final String PENDING_REFERRALS_ACTION_MENU_VIEW =
	    "/health/referral/includes/pendingActionMenu";
	
	private static final String OPEN_REFERRALS_ACTION_MENU_VIEW =
	    "/health/referral/includes/openReferralActionMenu";
	
	private static final String SCHEDULE_REFERRAL_VIEW =
		"/health/referral/internal/schedule";
	  
	private static final String RESOLVED_INTERNAL_REFERRALS_ACTION_MENU_VIEW =
		"/health/referral/internal/includes/resolvedActionMenu";
	
	private static final String RESOLVED_REFERRALS_ACTION_MENU_VIEW =
	    "/health/referral/includes/resolvedActionMenu";
	  
	private static final String SCHEDULED_INTERNAL_REFERRALS_ACTION_MENU_VIEW =
	    "/health/referral/internal/includes/scheduledActionMenu";
	
	private static final String SCHEDULED_REFERRALS_ACTION_MENU_VIEW =
	    "/health/referral/includes/scheduledActionMenu";
	
	private static final String AUTHORIZED_REFERRALS_ACTION_MENU_VIEW =
	    "/health/referral/includes/authorizedActionMenu";
	
	private static final String PENDING_AUTHORIZATIONS_ACTION_MENU_VIEW =
	    "/health/referral/includes/pendingAuthorizationsActionMenu";
	
	private static final String INTERNAL_SCHEDULED_ROW_ACTION_MENU_VIEW =
	    "/health/referral/includes/internalScheduledRowActionMenu";
	
	private static final String EXTERNAL_SCHEDULED_ROW_ACTION_MENU_VIEW =
	    "/health/referral/includes/externalScheduledRowActionMenu";
	
	private static final String PENDING_REFERRAL_ROW_ACTION_MENU_VIEW =
	    "/health/request/includes/pendingRowActionMenu";
	
	private static final String EXTERNAL_RESOLVED_ROW_ACTION_MENU_VIEW =
	    "/health/referral/includes/externalResolvedRowActionMenu";
	
	private static final String INTERNAL_RESOLVED_ROW_ACTION_MENU_VIEW =
	    "/health/referral/includes/internalResolvedRowActionMenu";
	
	private static final String EXTERNAL_AUTHORIZED_ROW_ACTION_MENU_VIEW = 
	    "/health/referral/includes/externalAuthorizedRowActionMenu";
	
	/* Model keys. */
	
	private static final String FACILITY_MODEL_KEY = "facility";
	
	private static final String REFERRAL_TYPE_MODEL_KEY = "referralType";
	
	private static final String OFFENDER_MODEL_KEY = "offender";
	
	private static final String FILTER_BY_START_DATE_MODEL_KEY
		= "filterByStartDate";
	
	private static final String FILTER_BY_END_DATE_MODEL_KEY
		= "filterByEndDate";
	
	private static final String COUNT_LIMIT_MODEL_KEY
		= "countLimit";
	
	private static final String REQUEST_MODEL_KEY
		= "request";
	
	/* Property editor factories. */
	
	@Autowired
	private PropertyEditorFactory facilityPropertyEditorFactory;
	
	@Autowired
	private PropertyEditorFactory healthRequestPropertyEditorFactory;
	
	@Autowired
	private PropertyEditorFactory reportFormatPropertyEditorFactory;
	
	@Autowired
	private CustomDateEditorFactory customDateEditorFactory;
	
	@Autowired
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	@Qualifier("externalReferralPropertyEditorFactory")
	private PropertyEditorFactory externalReferralPropertyEditorFactory;
	
	@Autowired
	@Qualifier("internalReferralPropertyEditorFactory")
	private PropertyEditorFactory internalReferralPropertyEditorFactory;
	
	@Autowired
	@Qualifier("externalReferralAuthorizationRequestPropertyEditorFactory")
	private PropertyEditorFactory
		externalReferralAuthorizationRequestPropertyEditorFactory;
	
	@Autowired
	@Qualifier("externalReferralAuthorizationPropertyEditorFactory")
	private PropertyEditorFactory
		externalReferralAuthorizationPropertyEditorFactory;
	
	/* Report services. */
	
	@Autowired
	private HealthFacilityReportService healthFacilityReportService;
	
	@Autowired
	private HealthReportService healthReportService;
	
	@Autowired
	private HealthRequestReportService healthRequestReportService;
	
	@Autowired
	private ReferralSummaryReportService referralSummaryReportService;
	
	@Autowired
	private AuthorizedReferralSummaryReportService
		authorizedReferralSummaryReportService;
	
	@Autowired
	private PendingReferralAuthorizationSummaryReportService
	pendingReferralAuthorizationSummaryReportService;
	
	@Autowired
	@Qualifier("featureToggles")
	private FeatureToggles featureToggles;
	
	/* Services. */
	
	@Autowired
	private ReferralCenterService referralCenterService;

	/** Home for referral center.
	 * @param selectedFacility facility.
	 * @param referralCenterFilterForm form to filter referrals
	 * @return referral center. */
	@RequestContentMapping(nameKey = "referralCenterScreenName",
		descriptionKey = "referralCenterScreenDescription",
		messageBundle = "omis.health.msgs.health",
	    screenType = RequestContentType.DETAIL_SCREEN)
	@RequestMapping(value = "referralCenter.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('HEALTH_ADMIN')"
		+ " or hasRole('REFERRAL_CENTER')")
	public ModelAndView referralCenterHome(
		    @RequestParam(value = "facility", required = false)
		    final Facility selectedFacility,
		    final ReferralCenterFilterForm referralCenterFilterForm) {
		SearchResultType exceededSearchResultType = null;
		Facility facility = selectedFacility;
		final ModelMap map = new ModelMap();
		ReferralType filterByReferralType = referralCenterFilterForm
		    .getFilterByReferralType();
		Date filterByStartDate = referralCenterFilterForm.getFilterByStartDate();
		if (facility == null) {
			final List<Facility> facilities =
				this.healthFacilityReportService.findHealthFacilitiesForStaff(
					this.getUserAccount().getUser(), new Date());
		
			if (facilities.size() > 1 || facilities.isEmpty()) {
				return this.selectFacility(facilities,
					filterByReferralType, filterByStartDate);
			} else {
		    if (facilities.size() == 1) {
		    	facility = facilities.get(0);
		    }
		  }
		}
		Date filterByEndDate = referralCenterFilterForm
			.getFilterByEndDate();
		Offender filterByOffender = referralCenterFilterForm
		    .getFilterByOffender();
		HealthAppointmentStatus filterByAppointmentStatus
		    = referralCenterFilterForm.getFilterByAppointmentStatus();
		if (filterByReferralType != null || filterByStartDate != null
			|| filterByEndDate != null || filterByOffender != null
		    || filterByAppointmentStatus != null) {
			final HealthAppointmentStatus[] resolvedStatuses;
			if (filterByAppointmentStatus != null) {
				resolvedStatuses = new HealthAppointmentStatus[] {
		        filterByAppointmentStatus
		    };
		} else {
			resolvedStatuses = HealthAppointmentStatus.values();
		}
		final Date currentDate = new Date();
		final List<HealthRequestSummary> pendingRequestSummaries;
		if (filterByOffender != null) {
			pendingRequestSummaries
		    	= this.healthRequestReportService.findOpenByOffender(
		    	filterByOffender, currentDate);
		    map.put("pendingRequestSummaries", pendingRequestSummaries);
		} else {
		    pendingRequestSummaries
		    	= this.healthRequestReportService.findOpen(facility, currentDate);
		    map.put("pendingRequestSummaries", pendingRequestSummaries);
		}
		  
		final List<ReferralSummary> scheduledReferrals;
		final List<ReferralSummary> resolvedReferrals;
		final omis.health.report.ReferralType[] reportReferralTypes;
		if (ReferralType.INTERNAL_MEDICAL.equals(filterByReferralType)) {
			reportReferralTypes = new omis.health.report.ReferralType[] {
				omis.health.report.ReferralType.INTERNAL_MEDICAL
		    };
		} else if (ReferralType.EXTERNAL_MEDICAL.equals(filterByReferralType)) {
		    reportReferralTypes = new omis.health.report.ReferralType[] {
		    	omis.health.report.ReferralType.EXTERNAL_MEDICAL
		    };
		} else if (ReferralType.LAB.equals(filterByReferralType)) {
			reportReferralTypes = new omis.health.report.ReferralType[] {
				omis.health.report.ReferralType.LAB
			};
		} else if (ReferralType.ALL.equals(filterByReferralType)
			|| filterByReferralType == null) {
			reportReferralTypes = omis.health.report.ReferralType.values();
		} else {
		    throw new UnsupportedOperationException(
		    	"Unknown referral type: " + filterByReferralType.getName());
		}
		
		if (filterByOffender != null) {
			if (this.featureToggles.get("health", "enableSearchResultCountLimit")) {
				long count = this.referralSummaryReportService  
					.countByOffender(filterByOffender, filterByStartDate,
					filterByEndDate, reportReferralTypes,
					new HealthAppointmentStatus[] { null });
				if (count > COUNT_LIMIT) {
					if (exceededSearchResultType == null) {
						exceededSearchResultType = SearchResultType.REFERRAL_REQUEST;
					}
				} else {
					if (exceededSearchResultType == null) {
						scheduledReferrals = this.referralSummaryReportService
							.findByOffender(filterByOffender, filterByStartDate,
							filterByEndDate, reportReferralTypes,
							new HealthAppointmentStatus[] { null },
							currentDate);
						map.put("scheduledReferrals", scheduledReferrals);
					}
				}
		
				count = this.referralSummaryReportService 
					.countByOffender(filterByOffender, filterByStartDate,
						filterByEndDate, reportReferralTypes, resolvedStatuses);
				if (count > COUNT_LIMIT) {
					if (exceededSearchResultType == null) {
						exceededSearchResultType = SearchResultType.REFERRAL_REQUEST;
					}
				} else {
					if (exceededSearchResultType == null) {
						resolvedReferrals = this.referralSummaryReportService
							.findByOffender(filterByOffender, filterByStartDate,
							filterByEndDate, reportReferralTypes,
							resolvedStatuses, currentDate);
						map.put("resolvedReferrals", resolvedReferrals);
					}
				}
		    } else { 
		    	scheduledReferrals = this.referralSummaryReportService
		    		.findByOffender(filterByOffender, filterByStartDate,
		    			filterByEndDate, reportReferralTypes,
		    			new HealthAppointmentStatus[] { null },
		    			currentDate);
		    	resolvedReferrals = this.referralSummaryReportService
		    		.findByOffender(filterByOffender, filterByStartDate,
					filterByEndDate, reportReferralTypes,
					resolvedStatuses, currentDate);
		    		
		    	map.put("resolvedReferrals", resolvedReferrals);
		    	map.put("scheduledReferrals", scheduledReferrals);
		    }
		  } else {
			  	if (this.featureToggles.get("health",
			  		"enableSearchResultCountLimit")) {
				long count = this.referralSummaryReportService
					.countByFacility(facility, filterByStartDate,
						filterByEndDate, reportReferralTypes,
						new HealthAppointmentStatus[] { null });
				if (count > COUNT_LIMIT) {
					if (exceededSearchResultType == null) {
						exceededSearchResultType 
							= SearchResultType.REFERRAL_REQUEST;
					}
				} else {
					if (exceededSearchResultType == null) {
						scheduledReferrals = this.referralSummaryReportService
							.findByFacility(facility, filterByStartDate,
							filterByEndDate, reportReferralTypes,
							new HealthAppointmentStatus[] { null }, currentDate);
						map.put("scheduledReferrals", scheduledReferrals);
					}
				}
		
				count = this.referralSummaryReportService 
					.countByFacility(facility, filterByStartDate, filterByEndDate,
					reportReferralTypes, resolvedStatuses);
				if (count > COUNT_LIMIT) {
					if (exceededSearchResultType == null) {
						exceededSearchResultType = SearchResultType.REFERRAL_REQUEST;
					}
				} else {
					if (exceededSearchResultType != null) {
						resolvedReferrals = this.referralSummaryReportService
							.findByFacility(facility, filterByStartDate,
							filterByEndDate, reportReferralTypes, resolvedStatuses,
							currentDate);
						map.put("resolvedReferrals", resolvedReferrals);
					}
				}
			 } else {
				 scheduledReferrals =
					this.referralSummaryReportService.findByFacility(facility,
						filterByStartDate, filterByEndDate, reportReferralTypes,
							new HealthAppointmentStatus[] { null }, currentDate);
				 resolvedReferrals = this.referralSummaryReportService
					.findByFacility(facility, filterByStartDate,
					filterByEndDate, reportReferralTypes, resolvedStatuses,
					currentDate);
				 map.put("resolvedReferrals", resolvedReferrals);
				 map.put("scheduledReferrals", scheduledReferrals);
			 }
		  }
		}
		map.put("facility", facility);
		map.put("referralType", filterByReferralType);
		
		if (ReferralType.ALL.equals(filterByReferralType)
			|| ReferralType.EXTERNAL_MEDICAL.equals(filterByReferralType)) {
			List<AuthorizedReferralSummary> authorizedReferrals;
			List<PendingReferralAuthorizationSummary> pendingAuthorizations;
			if (filterByOffender != null) {
				if (this.featureToggles.get("health", "enableSearchResultCountLimit")) {
					long count = this.authorizedReferralSummaryReportService
						.countUnscheduledByOffender(filterByOffender); 
					if (count > COUNT_LIMIT) {
						if (exceededSearchResultType == null) {
							exceededSearchResultType
								= SearchResultType.AUTHORIZED_REFERRAL_REQUEST;
						}
					} else {
						if (exceededSearchResultType == null) {
							authorizedReferrals
								= this.authorizedReferralSummaryReportService
								.findUnscheduledByOffender(filterByOffender);
							map.put("authorizedReferrals", authorizedReferrals);
						} 
					}
		
					count = this.pendingReferralAuthorizationSummaryReportService
						.countByOffender(filterByOffender);
					if (count > COUNT_LIMIT) {
						if (exceededSearchResultType == null) {
							exceededSearchResultType 
								= SearchResultType.PENDING_REFERRAL_AUTHORIZATION_REQUEST;
						}
					} else {
						if (exceededSearchResultType == null) {
							pendingAuthorizations
								= this.pendingReferralAuthorizationSummaryReportService
								.findByOffender(filterByOffender);
							map.put("pendingAuthorizations", pendingAuthorizations);
						}
					}
				} else {
					authorizedReferrals
						= this.authorizedReferralSummaryReportService
						.findUnscheduledByOffender(filterByOffender);
					map.put("authorizedReferrals", authorizedReferrals);
					pendingAuthorizations
						= this.pendingReferralAuthorizationSummaryReportService
						.findByOffender(filterByOffender);
					map.put("pendingAuthorizations", pendingAuthorizations);
				}
			} else {
				if (this.featureToggles.get("health", "enableSearchResultCountLimit")) {
					long count = this.authorizedReferralSummaryReportService
						.countUnscheduledByFacility(facility); 
					if (count > COUNT_LIMIT) {
						if (exceededSearchResultType == null) {
							exceededSearchResultType 
								= SearchResultType.AUTHORIZED_REFERRAL_REQUEST;
						}
					} else {
						if (exceededSearchResultType == null) {
							authorizedReferrals
								= this.authorizedReferralSummaryReportService
							.findUnscheduledByFacility(facility);
							map.put("authorizedReferrals", authorizedReferrals);
						}
					}
					count = this.pendingReferralAuthorizationSummaryReportService
						.countByFacility(facility);
					if (count > COUNT_LIMIT) {
						if (exceededSearchResultType == null) {
							exceededSearchResultType 
								= SearchResultType.PENDING_REFERRAL_AUTHORIZATION_REQUEST;
						}
					} else {
						if (exceededSearchResultType == null) {
							pendingAuthorizations
								= this.pendingReferralAuthorizationSummaryReportService
								.findByFacility(facility);
							map.put("pendingAuthorizations",  pendingAuthorizations);
						}
					}
				} else { 
					authorizedReferrals
						= this.authorizedReferralSummaryReportService
						.findUnscheduledByFacility(facility);
					map.put("authorizedReferrals", authorizedReferrals);
					pendingAuthorizations
						= this.pendingReferralAuthorizationSummaryReportService
						.findByFacility(facility);
					map.put("pendingAuthorizations", pendingAuthorizations);
				}
			}
		}  
		
		map.put("referralTypes", ReferralType.supportedValues());
		map.put("appointmentStatuses", HealthAppointmentStatus.values());
		map.put("filterByStartDate", filterByStartDate);
		map.put("filterByEndDate", filterByEndDate);
		map.put("filterByOffender", filterByOffender);
		map.put("filterByAppointmentStatus", filterByAppointmentStatus);
		map.put("enableSearchResultCountLimit", this.getEnableSearchResultLimit());
		map.put("exceededSearchResultType", exceededSearchResultType);
		map.put(COUNT_LIMIT_MODEL_KEY, COUNT_LIMIT);
		map.put(REQUEST_MODEL_KEY, exceededSearchResultType);
		return new ModelAndView(FACILITY_REFERRAL_CENTER_VIEW, map);
	}
	
	/** Schedule request.
	 * @param healthRequest health request.
	 * @return request schedule form. */
	@RequestContentMapping(nameKey = "scheduleRequestScreenName",
		descriptionKey = "scheduleRequestScreenDescription",
		messageBundle = "omis.health.msgs.health",
		screenType = RequestContentType.DETAIL_SCREEN)
	@RequestMapping(value = "scheduleRequest.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')" +
		" or hasRole('HEALTH_ADMIN')" +
		" or hasRole('REFERRAL_CENTER')")
	public ModelAndView scheduleRequest(
			@RequestParam(value = "request", required = true)
			final HealthRequest healthRequest) {
		final ModelMap map = new ModelMap();
		map.put("healthRequest", healthRequest);
		final ScheduleInternalReferralForm form =
			new ScheduleInternalReferralForm();
		map.put("scheduleRequestForm", form);
		return new ModelAndView(SCHEDULE_REFERRAL_VIEW, map);
	}
	
	/** Action menu for referral center.
	 * @param facility facility.
	 * @param referralType type of referral
	 * @return actions view. */
	@RequestContentMapping(nameKey = "referralCenterActionMenuName",
		descriptionKey = "referralCenterActionMenuDescription",
		messageBundle = "omis.health.msgs.health",
		screenType = RequestContentType.AJAX_REQUEST)
	@RequestMapping(value = "actionMenu.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')" +
		" or hasRole('HEALTH_ADMIN')" +
		" or hasRole('REFERRAL_CENTER')")
	public ModelAndView actionMenu(
			@RequestParam(value = "facility", required = true)
			final Facility facility,
			@RequestParam(value = "referralType", required = false)
			final ReferralType referralType) {
		final ModelMap map = new ModelMap();
		map.put("facility", facility);
		if (referralType != null) {
			map.put("referralType", referralType);
		}
		return new ModelAndView(FACILITY_REFERRAL_CENTER_ACTION_MENU_VIEW, map);
	}
	
	/** Action menu for facility/referral related operations.
	 * @return actions view. */
	@RequestContentMapping(nameKey = "referralCenterFacilityActionMenuName",
		descriptionKey = "referralCenterFacilityActionMenuDescription",
		messageBundle = "omis.health.msgs.health",
		screenType = RequestContentType.AJAX_REQUEST)
	@RequestMapping(value = "facility/actionMenu.html",
		method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')" +
		" or hasRole('HEALTH_ADMIN')" +
		" or hasRole('REFERRAL_CENTER')")
	public ModelAndView referralFacilityActionMenu() {
		return new ModelAndView(FACILITY_ACTION_MENU_VIEW);
	}
	
	/** Action menu for facility/referral related operations.
	 * 
	 * @param facility facility
	 * @param referralType referral type
	 * @param offender offender
	 * @return actions view. */
	@RequestContentMapping(nameKey = "referralCenterFacilityActionMenuName",
		descriptionKey = "referralCenterFacilityActionMenuDescription",
		messageBundle = "omis.health.msgs.health",
		screenType = RequestContentType.AJAX_REQUEST)
	@RequestMapping(value = "pending/actionMenu.html",
		method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')" +
		" or hasRole('HEALTH_ADMIN')" +
		" or hasRole('REFERRAL_CENTER')")
	public ModelAndView pendingReferralsActionMenu(
			@RequestParam(value = "facility", required = true)
			final Facility facility,
			@RequestParam(value = "referralType", required = true)
			final ReferralType referralType,
			@RequestParam(value = "offender", required = false)
			final Offender offender) {
		ModelAndView mav = new ModelAndView(PENDING_REFERRALS_ACTION_MENU_VIEW);
		mav.addObject(FACILITY_MODEL_KEY, facility);
		mav.addObject(OFFENDER_MODEL_KEY, offender);
		mav.addObject(REFERRAL_TYPE_MODEL_KEY, referralType);
		return mav;
	}
	
	/** Action menu for resolved internal referrals.
	 * @return actions view. */
	@RequestContentMapping(nameKey = "referralCenterFacilityActionMenuName",
		descriptionKey = "referralCenterFacilityActionMenuDescription",
		messageBundle = "omis.health.msgs.health",
		screenType = RequestContentType.AJAX_REQUEST)
	@RequestMapping(value = "/internal/resolved/actionMenu.html",
		method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')" +
		" or hasRole('HEALTH_ADMIN')" +
		" or hasRole('REFERRAL_CENTER')")
	public ModelAndView resolvedInternalReferralsActionMenu() {
		return new ModelAndView(RESOLVED_INTERNAL_REFERRALS_ACTION_MENU_VIEW);
	}
	
	/**
	 * Action menu for resolved referrals.
	 *
	 * @param offender offender
	 * @param filterBy
	 * @return actions view.
	 */
	@RequestContentMapping(nameKey = "referralCenterFacilityActionMenuName",
			descriptionKey = "referralCenterFacilityActionMenuDescription",
			messageBundle = "omis.health.msgs.health",
			screenType = RequestContentType.AJAX_REQUEST)
	@RequestMapping(value = "/resolved/actionMenu.html",
		method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')" +
		" or hasRole('HEALTH_ADMIN')" +
		" or hasRole('REFERRAL_CENTER')")
	public ModelAndView resolvedReferralsActionMenu(
			@RequestParam(value = "facility", required = false)
			final Facility facility,
			@RequestParam(value = "offender", required = false)
			final Offender offender,
			@RequestParam(value = "filterByStartDate", required = false)
			final Date filterByStartDate,
			@RequestParam(value = "filterByEndDate", required = false)
			final Date filterByEndDate) {
		ModelAndView mav
			= new ModelAndView(RESOLVED_REFERRALS_ACTION_MENU_VIEW);
		mav.addObject(FACILITY_MODEL_KEY, facility);
		mav.addObject(OFFENDER_MODEL_KEY, offender);
		mav.addObject(FILTER_BY_START_DATE_MODEL_KEY, filterByStartDate);
		mav.addObject(FILTER_BY_END_DATE_MODEL_KEY, filterByEndDate);
		return mav;
	}	
	
	/**
	 * Scheduled internal referral action menu.
	 *
	 * @param facility facility
	 * @return model and view action menu
	 */
	@RequestContentMapping(nameKey = "scheduledActionMenuScreenName",
		descriptionKey = "scheduledActionMenuScreenDescription",
		messageBundle = "omis.health.msgs.health",
		screenType = RequestContentType.AJAX_REQUEST)
	@RequestMapping(value = "/internal/scheduled/actionMenu.html",
		method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')" +
		" or hasRole('HEALTH_ADMIN')" +
		" or hasRole('REFERRAL_CENTER')")
	public ModelAndView scheduledInternalReferralActionMenu(
			@RequestParam (value = "facility", required = true)
			final Facility facility) {
		final ModelMap map = new ModelMap();
		map.addAttribute(FACILITY_MODEL_KEY, facility);
		return new ModelAndView(SCHEDULED_INTERNAL_REFERRALS_ACTION_MENU_VIEW,
		map);
	}
	
	/**
	 * Scheduled referral action menu.
	 *
	 * @param facility facility
	 * @param offender offender
	 * @return model and view action menu
	 */
	@RequestContentMapping(nameKey = "scheduledActionMenuScreenName",
		descriptionKey = "scheduledActionMenuScreenDescription",
		messageBundle = "omis.health.msgs.health",
		screenType = RequestContentType.AJAX_REQUEST)
	@RequestMapping(value = "/scheduled/actionMenu.html",
		method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')" +
		" or hasRole('HEALTH_ADMIN')" +
		" or hasRole('REFERRAL_CENTER')")
	public ModelAndView scheduledReferralActionMenu(
			@RequestParam (value = "facility", required = true)
			final Facility facility,
			@RequestParam(value = "offender", required = false)
			final Offender offender,
			@RequestParam(value = "referralType", required = true)
			final ReferralType referralType,
			@RequestParam(value = "filterByStartDate", required = false)
			final Date filterByStartDate,
			@RequestParam(value = "filterByEndDate", required = false)
			final Date filterByEndDate) {
		final ModelMap map = new ModelMap();
		map.addAttribute(FACILITY_MODEL_KEY, facility);
		if (offender != null) {
			map.addAttribute(OFFENDER_MODEL_KEY, offender);
		}
		map.addAttribute(REFERRAL_TYPE_MODEL_KEY, referralType);
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		map.addAttribute(FILTER_BY_START_DATE_MODEL_KEY, filterByStartDate);
		map.addAttribute(FILTER_BY_END_DATE_MODEL_KEY, filterByEndDate);
		return new ModelAndView(SCHEDULED_REFERRALS_ACTION_MENU_VIEW, map);
	}
	
	/** Change facility action center.
	 * @param facility current facility.
	 * @param referralType referral type
	 * @return action view. */
	@RequestContentMapping(nameKey = "changeFacilityActionMenuName",
		descriptionKey = "changeFacilityActionMenuName",
		messageBundle = "omis.health.msgs.health",
		screenType = RequestContentType.AJAX_REQUEST)
	@RequestMapping(value = "changeFacilityMenu.html",
		method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')" +
		" or hasRole('HEALTH_ADMIN')" +
		" or hasRole('REFERRAL_CENTER')")
	public ModelAndView changeFacilityActionMenu(
			@RequestParam(value = "facility", required = false)
			final Facility facility,
			@RequestParam(value = "referralType", required = false)
			final ReferralType referralType) {
		final List<Facility> facilities
			= this.healthFacilityReportService
			.findHealthFaciliites();
		final ModelMap map = new ModelMap();
		map.put("facilities", facilities);
		map.put("referralType", referralType);
		return new ModelAndView(FACILITY_SELECT_LIST_VIEW, map);
	
	}
	
	/**
	 * Displays action menu for authorized referrals.
	 * 
	 * @param facility facility
	 * @param offender offender
	 * @param referralType referral type
	 * @return action menu for authorized referrals
	 */
	@RequestContentMapping(nameKey = "authorizedReferralActionMenuName",
		descriptionKey = "authorizedReferralActionMenuName",
		messageBundle = "omis.health.msgs.health",
		screenType = RequestContentType.AJAX_REQUEST)
	@RequestMapping(value = "authorized/actionMenu.html",
		method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')" +
		" or hasRole('HEALTH_ADMIN')" +
		" or hasRole('REFERRAL_CENTER')")
	public ModelAndView authorizedReferralsActionMenu(
			@RequestParam (value = "facility", required = true)
			final Facility facility,
			@RequestParam(value = "offender", required = false)
			final Offender offender,
			@RequestParam(value = "referralType", required = false)
			final ReferralType referralType) {
		ModelMap map = new ModelMap();
		map.put("facility", facility);
		map.put("referralType", referralType);
		map.put("offender", offender);
		return new ModelAndView(AUTHORIZED_REFERRALS_ACTION_MENU_VIEW, map);
	}
	
	/**
	 * Displays action menu for pending referral authorizations.
	 * 
	 * @param facility facility
	 * @param offender offender
	 * @param referralType referral type
	 * @return action menu for pending referral authorizations
	 */
	@RequestContentMapping(nameKey = "authorizedReferralActionMenuName",
		descriptionKey = "authorizedReferralActionMenuName",
		messageBundle = "omis.health.msgs.health",
		screenType = RequestContentType.AJAX_REQUEST)
	@RequestMapping(value = "pendingAuthorization/actionMenu.html",
		method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')" +
		" or hasRole('HEALTH_ADMIN')" +
		" or hasRole('REFERRAL_CENTER')")
	public ModelAndView pendingReferralAuthorizationsActionMenu(
			@RequestParam (value = "facility", required = true)
			final Facility facility,
			@RequestParam(value = "offender", required = false)
			final Offender offender,
			@RequestParam(value = "referralType", required = false)
			final ReferralType referralType) {
		ModelMap map = new ModelMap();
		map.put("facility", facility);
		map.put("referralType", referralType);
		map.put("offender", offender);
		return new ModelAndView(PENDING_AUTHORIZATIONS_ACTION_MENU_VIEW, map);
	}
	
	/** Health request action menu.
	 * @return action menu. */
	@RequestContentMapping(nameKey = "healthRequestActionMenuName",
		descriptionKey = "healthRequestActionMenuDescription",
		messageBundle = "omis.health.msgs.health",
		screenType = RequestContentType.AJAX_REQUEST)
	@RequestMapping(value = "healthRequestActionMenu.html",
		method = RequestMethod.GET)
	public ModelAndView healthRequestActionMenu() {
		final ModelMap map = new ModelMap();
		return new ModelAndView(OPEN_REFERRALS_ACTION_MENU_VIEW, map);
	}
	
	/**
	 * Displays action menu for internal scheduled rows.
	 * 
	 * @param facility facility
	 * @param internalReferral internal Referral
	 * @return action menu for internal scheduled rows
	 */
	@RequestContentMapping(nameKey = "internalScheduledRowActionMenuName",
		descriptionKey = "internalScheduledRowActionMenuName",
		messageBundle = "omis.health.msgs.health",
		screenType = RequestContentType.AJAX_REQUEST)
	@RequestMapping(value = "internal/scheduledRowActionMenu.html",
		method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')" +
		" or hasRole('HEALTH_ADMIN')" +
		" or hasRole('REFERRAL_CENTER')")
	public ModelAndView internalScheduledRowActionMenu(
			@RequestParam(value = "internalReferral", required = true)
			final InternalReferral internalReferral,
			@RequestParam (value = "facility", required = false)
			final Facility facility) {
		ModelMap map = new ModelMap();
		map.put("facility", facility);
		map.put("scheduled", internalReferral);
		return new ModelAndView(INTERNAL_SCHEDULED_ROW_ACTION_MENU_VIEW, map);
	}
	
	/**
	 * Displays action menu for external scheduled rows.
	 * 
	 * @param facility facility
	 * @param externalReferral external Referral
	 * @return action menu for external scheduled rows
	 */
	@RequestContentMapping(nameKey = "externalScheduledRowActionMenuName",
		descriptionKey = "externalScheduledRowActionMenuName",
		messageBundle = "omis.health.msgs.health",
		screenType = RequestContentType.AJAX_REQUEST)
	@RequestMapping(value = "external/scheduledRowActionMenu.html",
		method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')" +
		" or hasRole('HEALTH_ADMIN')" +
		" or hasRole('REFERRAL_CENTER')")
	public ModelAndView externalScheduledRowActionMenu(
			@RequestParam(value = "externalReferral", required = true)
			final ExternalReferral externalReferral,
			@RequestParam (value = "facility", required = false)
			final Facility facility) {
		ModelMap map = new ModelMap();
		map.put("facility", facility);
		map.put("scheduled", externalReferral);
		return new ModelAndView(EXTERNAL_SCHEDULED_ROW_ACTION_MENU_VIEW, map);
	}
	
	/**
	 * Displays action menu for pending referral rows.
	 * 
	 * @param healthRequest health request
	 * @param authorizationRequest external referral authorization request
	 * @param authorization external referral authorization
	 * @return action menu for external scheduled rows
	 */
	@RequestContentMapping(nameKey = "externalScheduledRowActionMenuName",
		descriptionKey = "externalScheduledRowActionMenuName",
		messageBundle = "omis.health.msgs.health",
		screenType = RequestContentType.AJAX_REQUEST)
	@RequestMapping(value = "pendingRowActionMenu.html",
		method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')" +
		" or hasRole('HEALTH_ADMIN')" +
		" or hasRole('REFERRAL_CENTER')")
	public ModelAndView pendingReferralRowActionMenu(
			@RequestParam(value = "healthRequest", required = true)
			final HealthRequest healthRequest,
			@RequestParam (value = "authorizationRequest", required = false)
			final ExternalReferralAuthorizationRequest authorizationRequest,
			@RequestParam (value = "authorization", required = false)	
			final ExternalReferralAuthorization authorization,
			@RequestParam (value = "authorized", required = false)	
			final Boolean authorized) {
		ModelMap map = new ModelMap();
		map.put("authorizationRequest", authorizationRequest);
		map.put("pending", healthRequest);
		map.put("authorization", authorization);
		map.put("authorized", authorized);
		return new ModelAndView(PENDING_REFERRAL_ROW_ACTION_MENU_VIEW, map);
	}
	
	/**
	 * Displays action menu for resolved internal referral rows.
	 * 
	 * @param referral referral
	 * @return action menu for resolved internal referral rows
	 */
	@RequestContentMapping(nameKey = "internalResolvedRowActionMenuName",
		descriptionKey = "internalResolvedRowActionMenuName",
		messageBundle = "omis.health.msgs.health",
		screenType = RequestContentType.AJAX_REQUEST)
	@RequestMapping(value = "internal/resolvedRowActionMenu.html",
		method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')" +
		" or hasRole('HEALTH_ADMIN')" +
		" or hasRole('REFERRAL_CENTER')")
	public ModelAndView intneralResolvedReferralRowActionMenu(
			@RequestParam(value = "referral", required = true)
			final InternalReferral referral) {
		ModelMap map = new ModelMap();
		map.put("resolved", referral);
		return new ModelAndView(INTERNAL_RESOLVED_ROW_ACTION_MENU_VIEW, map);
	}
	
	/**
	 * Displays action menu for resolved external referral rows.
	 * 
	 * @param referral referral
	 * @return action menu for resolved external referral rows
	 */
	@RequestContentMapping(nameKey = "externalResolvedRowActionMenuName",
		descriptionKey = "externalResolvedRowActionMenuName",
		messageBundle = "omis.health.msgs.health",
		screenType = RequestContentType.AJAX_REQUEST)
	@RequestMapping(value = "external/resolvedRowActionMenu.html",
		method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')" +
		" or hasRole('HEALTH_ADMIN')" +
		" or hasRole('REFERRAL_CENTER')")
	public ModelAndView externalResolvedReferralRowActionMenu(
			@RequestParam(value = "referral", required = true)
			final ExternalReferral referral) {
		ModelMap map = new ModelMap();
		map.put("resolved", referral);
		return new ModelAndView(EXTERNAL_RESOLVED_ROW_ACTION_MENU_VIEW, map);
	}
	
	/**
	 * Displays action menu for authorized external referral rows.
	 * 
	 * @param authorization authorization
	 * @return action menu for authorized external referral rows
	 */
	@RequestContentMapping(nameKey = "externalResolvedRowActionMenuName",
		descriptionKey = "externalResolvedRowActionMenuName",
		messageBundle = "omis.health.msgs.health",
		screenType = RequestContentType.AJAX_REQUEST)
	@RequestMapping(value = "external/authorizedRowActionMenu.html",
		method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')" +
		" or hasRole('HEALTH_ADMIN')" +
		" or hasRole('REFERRAL_CENTER')")
	public ModelAndView externalAuthorizedReferralRowActionMenu(
			@RequestParam(value = "authorization", required = true)
			final ExternalReferralAuthorization authorization) {
		ModelMap map = new ModelMap();
		map.put("authorized", authorization);
		return new ModelAndView(EXTERNAL_AUTHORIZED_ROW_ACTION_MENU_VIEW, map);
	}
	
	/** Call out list report.
	 * @return report. */
	@RequestMapping(value = "callOutList.{extension:pdf|xls}",
		method = RequestMethod.GET)
	public ResponseEntity<byte[]> callOutListReport(
			@RequestParam(value = "startDate", required = true)
			final Date startDate,
			@RequestParam(value="endDate", required = true)
			final Date endDate,
			@PathVariable("extension")
			final ReportFormat reportFormat) {
		final byte[] report = this.healthReportService.runCallOutList(startDate,
			endDate, reportFormat);
	
		final HttpHeaders headers = SpringHeadersFactory
			.createReportResponseHeaders(reportFormat);
	
		return new ResponseEntity<byte[]>(report, headers, HttpStatus.OK);
	}
	
	/* Helper methods. */
	
	/** Gets the current logged in user account.
	 * @return userAccount. */
	private UserAccount getUserAccount() {
		return this.referralCenterService.findUserAccountByUsername(
			SecurityContextHolder.getContext().getAuthentication()
			.getName());
	}
	
	private ModelAndView selectFacility(final List<Facility> facilities,
			final ReferralType referralType, final Date filterByStartDate) {
		final ModelMap map = new ModelMap();
	
		if (facilities.isEmpty()) {
			map.put("facilities", this.healthFacilityReportService
				.findHealthFaciliites());
		} else {
			map.put("facilities", facilities);
		}
		map.addAttribute("referralType", referralType);
		map.addAttribute("filterByStartDate", filterByStartDate);
		return new ModelAndView(FACILITY_SELECT_VIEW, map);
	}
	
	// Returns whether search result count limit enabled. 
	private boolean getEnableSearchResultLimit() {
		return this.featureToggles.get("health",
		"enableSearchResultCountLimit");
	}
	
	/* Init binder. */
	
	/** Init Binder.
	 * @param binder binder. */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(Facility.class,
			this.facilityPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(HealthRequest.class,
			this.healthRequestPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(Date.class,
			this.customDateEditorFactory.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(ReportFormat.class,
			this.reportFormatPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(Offender.class,
			this.offenderPropertyEditorFactory.createOffenderPropertyEditor());
		binder.registerCustomEditor(InternalReferral.class,
			this.internalReferralPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(ExternalReferral.class,
			this.externalReferralPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(ExternalReferralAuthorizationRequest.class,
			this.externalReferralAuthorizationRequestPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(ExternalReferralAuthorization.class,
			this.externalReferralAuthorizationPropertyEditorFactory
				.createPropertyEditor());
	}
}