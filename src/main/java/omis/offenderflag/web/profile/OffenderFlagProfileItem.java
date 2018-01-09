package omis.offenderflag.web.profile;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import omis.offender.domain.Offender;
import omis.offenderflag.report.OffenderFlagProfileItemReportService;
import omis.user.domain.UserAccount;
import omis.web.profile.AbstractProfileItem;
import omis.web.profile.ProfileItem;
import omis.web.profile.ProfileItemRegistry;

/** Offender flag profile item.
 * @author Ryan Johns
 * @author Trevor Isles
 * @version 0.1.0 (Mar 15, 2016)
 * @since OMIS 3.0 */
public class OffenderFlagProfileItem 
	extends AbstractProfileItem 
	implements ProfileItem {
	private final static long serialVersionUID = 1L;
	private final String ACTIVE_OFFENDER_FLAG_COUNT_MODEL_KEY 
		= "activeOffenderFlagCount";
	
	private final OffenderFlagProfileItemReportService 
		offenderFlagProfileItemReportService;
	
	/** Constructor.
	 * @param requiredAuthorizations - required authorizations.
	 * @param includePage - include page.
	 * @param name - name.
	 * @param profileItemRegistry - profile item registry.
	 * @param offenderFlagProfileItemReportService - offender flag profile
	 * item report service. 
	 * @param enabled - whether enabled */
	public OffenderFlagProfileItem(final Set<String> requiredAuthorizations,
			final String includePage, 
			final String name,
			final ProfileItemRegistry profileItemRegistry, 
			final OffenderFlagProfileItemReportService 
				offenderFlagProfileItemReportService,
			final Boolean enabled) {
		super(requiredAuthorizations, includePage, name, profileItemRegistry,
				enabled);
		this.offenderFlagProfileItemReportService 
			= offenderFlagProfileItemReportService;
	}

	
	/** {@inheritDoc} */
	@Override
	public void build(final Map<String, Object> map, final Offender offender,
			final UserAccount userAccount, final Date date) {
		map.put(ACTIVE_OFFENDER_FLAG_COUNT_MODEL_KEY, 
				this.offenderFlagProfileItemReportService
				.findOffenderFlagCountByOffender(offender)); 
	}
}
