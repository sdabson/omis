package omis.alert.web.profile;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import omis.alert.report.OffenderAlertProfileItemReportService;
import omis.alert.report.OffenderAlertProfileItemSummary;
import omis.offender.domain.Offender;
import omis.user.domain.UserAccount;
import omis.web.profile.AbstractProfileItem;
import omis.web.profile.ProfileItem;
import omis.web.profile.ProfileItemRegistry;

/** Profile item for alerts.
 * @author Ryan Johns
 * @author Trevor Isles
 * @verion 0.1.0 (Mar 10, 2016)
 * @since OMIS 3.0 */
public class OffenderAlertProfileItem 
	extends AbstractProfileItem 
	implements ProfileItem {
	private static final long serialVersionUID = 1L;
	
	private static final String ACTIVE_ALERTS_COUNT_MODEL_KEY 
		= "activeAlertCount";
	private static final String INACTIVE_ALERTS_COUNT_MODEL_KEY
		= "inactiveAlertCount";
	
	private final OffenderAlertProfileItemReportService 
		offenderAlertProfileItemReportService;
		
	
	/** constructor. 
	 * @param requiredAuthorizations - required authorizations.
	 * @param includePage - include page.
	 * @param name - name.
	 * @param profileItemRegistry - profile item registry.
	 * @param offenderAlertProfileItemReportService - offender alert profile
	 * item report service.
	 * @param enabled - whether enabled */
	public OffenderAlertProfileItem(final Set<String> requiredAuthorizations,
			final String includePage,
			final String name,
			final ProfileItemRegistry profileItemRegistry,
			final OffenderAlertProfileItemReportService 
				offenderAlertProfileItemReportService,
			final Boolean enabled) {
		super(requiredAuthorizations, includePage, name, profileItemRegistry,
				enabled);
		this.offenderAlertProfileItemReportService 
			= offenderAlertProfileItemReportService;
	}
	
	/** {@inhertiDoc} */
	@Override
	public void build(final Map<String, Object> map,
			final Offender offender, final UserAccount userAccount,
			final Date date) {
		final OffenderAlertProfileItemSummary oapis = 
					this.offenderAlertProfileItemReportService
					.findOffenderAlertProfileItemSummaryByOffenderAndDate(
							offender, date);
		map.put(ACTIVE_ALERTS_COUNT_MODEL_KEY, oapis.getActiveAlertCount());
		map.put(INACTIVE_ALERTS_COUNT_MODEL_KEY, oapis.getInactiveAlertCount());
	}		
}
