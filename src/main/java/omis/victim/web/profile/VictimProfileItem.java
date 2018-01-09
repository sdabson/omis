package omis.victim.web.profile;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import omis.offender.domain.Offender;
import omis.user.domain.UserAccount;
import omis.victim.report.VictimProfileItemReportService;
import omis.web.profile.AbstractProfileItem;
import omis.web.profile.ProfileItem;
import omis.web.profile.ProfileItemRegistry;

/** Profile item for victim.
 * @author Ryan Johns
 * @author Trevor Isles
 * @version 0.1.0 (Mar 18, 2016)
 * @since OMIS 3.0 */
public class VictimProfileItem 
	extends AbstractProfileItem 
	implements ProfileItem {
	private static final long serialVersionUID = 1L;
	private static final String VICTIM_COUNT_MODEL_KEY = "victimCount";
	
	private final VictimProfileItemReportService 
		victimProfileItemReportService;
	
	/** Constructor.
	 * @param requiredAuthorizations - required authorizations.
	 * @param includePage - include page.
	 * @param name - name.
	 * @param profileItemRegistry - profile item registry.
	 * @param victimProfileItemReportService - victim profile item report 
	 * service.
	 * @param enabled - whether enabled
	 */
	public VictimProfileItem(final Set<String> requiredAuthorizations, 
			final String includePage, 
			final String name,
			final ProfileItemRegistry profileItemRegistry, 
			final VictimProfileItemReportService 
				victimProfileItemReportService,
			final Boolean enabled) {
		super(requiredAuthorizations, includePage, name, profileItemRegistry,
				enabled);
		this.victimProfileItemReportService = victimProfileItemReportService;
	}
	
	/** {@inheritDoc} */
	@Override
	public void build(final Map<String, Object> map, final Offender offender,
			final UserAccount userAccount, final Date date) {
		map.put(VICTIM_COUNT_MODEL_KEY, this.victimProfileItemReportService
				.findVictimCountByOffender(offender));
	}
}
