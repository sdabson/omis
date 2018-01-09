package omis.separationneed.web.profile;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import omis.offender.domain.Offender;
import omis.user.domain.UserAccount;
import omis.separationneed.report.SeparationNeedProfileItemReportService;
import omis.web.profile.AbstractProfileItem;
import omis.web.profile.ProfileItem;
import omis.web.profile.ProfileItemRegistry;

/** Profile item for separation need.
 * @author Ryan Johns
 * @author Trevor Isles
 * @version 0.1.0 (Mar 17, 2016)
 * @since OMIS 3.0 */
public class SeparationNeedProfileItem 
	extends AbstractProfileItem 
	implements ProfileItem {
	private static final long serialVersionUID = 1L;
	private static final String SEPARATION_NEED_PROFILE_ITEM_SUMMARY_MODEL_KEY 
		= "separationNeedProfileItemSummary";
	
	private SeparationNeedProfileItemReportService 
		separationNeedProfileItemReportService;
	
	/** Constructor.
	 * @param requiredAuthorization - required authorizations.
	 * @param includePage - include page.
	 * @param name - name.
	 * @param profileItemRegistry - profile item registry.
	 * @param seperationNeedProfileItemReportService - separation need profile 
	 * item report service. 
	 * @param enabled - whether enabled */
	public SeparationNeedProfileItem(final Set<String> requiredAuthorizations, 
			final String includePage, 
			final String name,
			final ProfileItemRegistry profileItemRegistry, 
			final SeparationNeedProfileItemReportService 
				separationNeedProfileItemReportService,
			final Boolean enabled) {
		super(requiredAuthorizations, includePage, name, profileItemRegistry,
				enabled);
		this.separationNeedProfileItemReportService 
			= separationNeedProfileItemReportService;
	}
	
	/** {@inheritDoc} */
	@Override
	public void build(final Map<String, Object> map, final Offender offender,
			final UserAccount userAccount, final Date date) {
		map.put(SEPARATION_NEED_PROFILE_ITEM_SUMMARY_MODEL_KEY, 
				this.separationNeedProfileItemReportService
				.findSeparationNeedProfileItemSummaryByOffenderAndDate(offender, date));
	}
}
