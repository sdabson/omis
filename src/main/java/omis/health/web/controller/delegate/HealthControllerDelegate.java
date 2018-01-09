package omis.health.web.controller.delegate;

import java.beans.PropertyEditor;
import java.util.Date;

import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.facility.domain.Facility;
import omis.health.domain.HealthAppointmentStatus;
import omis.health.web.form.ReferralCenterFilterForm;
import omis.health.web.form.ReferralType;
import omis.offender.domain.Offender;

/** Health controller delegate.
 * @author Ryan Johns
 * @author Stephen Abson
 * @version 0.1.0 (May 5, 2014)
 * @since OMIS 3.0 */
public class HealthControllerDelegate {
	
	private static final String REFERRAL_CENTER_REDIRECT
			= "redirect:/health/referral/referralCenter.html"
			+ "?facility=%1$d&filterByReferralType=%2$s";
	
	private static final String FILTER_PARAMS
			= "filterByOffender=%s&filterByStartDate=%s"
			+ "&filterByEndDate=%s&filterByReferralType=%s"
			+ "&filterByAppointmentStatus=%s";
	
	private static final String REFERRAL_CENTER_WITH_FILTER_REDIRECT
			= "redirect:/health/referral/referralCenter.html?facility=%d"
			+ "&" + FILTER_PARAMS;
	
	private final CustomDateEditorFactory customDateEditorFactory;
	
	/**
	 * Instantiates a health controller delegate.
	 * 
	 * @param customDateEditorFactory date editor factory
	 */
	public HealthControllerDelegate(
			final CustomDateEditorFactory customDateEditorFactory) {
		this.customDateEditorFactory = customDateEditorFactory;
	}

	/** Returns referral center redirect.
	 * @param facility facility.
	 * @return String redirect. */
	@Deprecated
	public String prepareReferralCenterRedirect(final Facility facility) {
		return String.format(REFERRAL_CENTER_REDIRECT, facility.getId(),
				ReferralType.ALL.getName());
	}

	/** Returns referral center.
	 * @param facility facility.
	 * @param referralType referralType
	 * @return string redirect. */
	@Deprecated
	public String prepareReferralCenterRedirect(final Facility facility,
			final ReferralType referralType) {
		return String.format(REFERRAL_CENTER_REDIRECT, facility.getId(),
				referralType.getName());
	}

	/**
	 * Returns redirect to facility referral center with filter form.
	 * 
	 * @param facility facility
	 * @param referralCenterFilterForm form containing filters
	 * @return redirect to facility referral center with filter
	 */
	public String prepareFacilityCenterRedirectWithFilterForm(
			final Facility facility,
			final ReferralCenterFilterForm referralCenterFilterForm) {
		return this.prepareFacilityCenterRedirectWithParameter(facility,
					referralCenterFilterForm.getFilterByOffender(),
					referralCenterFilterForm.getFilterByStartDate(),
					referralCenterFilterForm.getFilterByEndDate(),
					referralCenterFilterForm.getFilterByReferralType(),
					referralCenterFilterForm.getFilterByAppointmentStatus());
	}
		
	/**
	 * Returns redirect to facility referral center with filter parameters.
	 * 
	 * @param facility facility
	 * @param offender offender
	 * @param startDate start date
	 * @param endDate end date
	 * @param referralType referral type
	 * @param appointmentStatus appointment status
	 * @return redirect to facility referral center
	 */
	public String prepareFacilityCenterRedirectWithParameter(
			final Facility facility, final Offender offender,
			final Date startDate, final Date endDate,
			final ReferralType referralType,
			final HealthAppointmentStatus appointmentStatus) {
		String filterByOffender;
		if (offender != null) {
			filterByOffender = offender.getId().toString();
		} else {
			filterByOffender = "";
		}
		String filterByStartDate;
		if (startDate != null) {
			PropertyEditor propertyEditor = this.customDateEditorFactory
					.createCustomDateOnlyEditor(true);
			propertyEditor.setValue(startDate);
			filterByStartDate = propertyEditor.getAsText();
		} else {
			filterByStartDate = "";
		}
		String filterByEndDate;
		if (endDate != null) {
			PropertyEditor propertyEditor = this.customDateEditorFactory
					.createCustomDateOnlyEditor(true);
			propertyEditor.setValue(endDate);
			filterByEndDate = propertyEditor.getAsText();
		} else {
			filterByEndDate = "";
		}
		String filterByReferralType;
		if (referralType != null) {
			filterByReferralType = referralType.getName(); 
		} else {
			filterByReferralType = "";
		}
		String filterByAppointmentStatus;
		if (appointmentStatus != null) {
			filterByAppointmentStatus = appointmentStatus.getName();
		} else {
			filterByAppointmentStatus = "";
		}
		return String.format(REFERRAL_CENTER_WITH_FILTER_REDIRECT,
				facility.getId(), filterByOffender, filterByStartDate,
				filterByEndDate, filterByReferralType, 
				filterByAppointmentStatus);
	}
}
