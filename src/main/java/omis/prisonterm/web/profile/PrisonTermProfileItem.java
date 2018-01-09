package omis.prisonterm.web.profile;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import omis.offender.domain.Offender;
import omis.prisonterm.report.PrisonTermProfileItemReportService;
import omis.user.domain.UserAccount;
import omis.web.profile.AbstractProfileItem;
import omis.web.profile.ProfileItem;
import omis.web.profile.ProfileItemRegistry;

/**
 * Prison term profile item.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (March 17, 2017)
 * @since OMIS 3.0
 */

public class PrisonTermProfileItem extends AbstractProfileItem 
	implements ProfileItem {
	
	private static final long serialVersionUID = 1L;

	private static final String COUNT_MODEL_KEY = "prisonTermCount";
	
	private final PrisonTermProfileItemReportService 
		prisonTermProfileItemReportService;
	
	/** Constructor. 
	 * @param requiredAuthorizations - required authorizations.
	 * @param includePage - include page.
	 * @param name - name.
	 * @param profileItemRegistry - profile item registry.
	 * @param prisonTermProfileItemReportService - prison term profile item 
	 * report service. 
	 * @param enabled - whether enabled */
	public PrisonTermProfileItem(
			final Set<String> requiredAuthorizations, 
			final String includePage,
			final String name,
			final ProfileItemRegistry profileItemRegistry,
			final PrisonTermProfileItemReportService 
				prisonTermProfileItemReportService,
			final Boolean enabled) {
		super(requiredAuthorizations, includePage, name, profileItemRegistry,
				enabled);
		this.prisonTermProfileItemReportService 
			= prisonTermProfileItemReportService;
	}
	
	/** {@inheritDoc} */
	@Override
	public void build(final Map<String, Object> map, final Offender offender,
			final UserAccount userAccount, final Date date) {
		Integer count = this.prisonTermProfileItemReportService
				.findPrisonTermCountByPrisonTerms(offender);
		map.put(COUNT_MODEL_KEY, count);
	}

}
