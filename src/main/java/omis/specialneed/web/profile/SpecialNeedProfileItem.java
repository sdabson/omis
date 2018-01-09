package omis.specialneed.web.profile;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import omis.offender.domain.Offender;
import omis.specialneed.report.SpecialNeedProfileItemReportService;
import omis.user.domain.UserAccount;
import omis.web.profile.AbstractProfileItem;
import omis.web.profile.ProfileItem;
import omis.web.profile.ProfileItemRegistry;

/** Profile item for special needs.
 * @author Ryan Johns
 * @author Trevor Isles
 * @version 0.1.0 (Mar 17, 2016)
 * @since OMIS 3.0 */
public class SpecialNeedProfileItem 
	extends AbstractProfileItem 
	implements ProfileItem {
	private static final long serialVersionUID = 1L;
	private static final String SPECIAL_NEED_COUNT_MODEL_KEY 
		= "specialNeedCount";
	
	private final SpecialNeedProfileItemReportService 
		specialNeedProfileItemReportService;
	
	/** Constructor.
	 * @param requiredAuthorizations - require authorizations.
	 * @param includePage - include page.
	 * @param name - name.
	 * @param profileItemRegistry - profile Item registry.
	 * @param specialNeedProfileItemReportService - special need profile item 
	 * report service. 
	 * @param enabled - whether enabled */
	public SpecialNeedProfileItem(final Set<String> requiredAuthorizations, 
			final String includePage, 
			final String name,
			final ProfileItemRegistry profileItemRegistry,
			final SpecialNeedProfileItemReportService 
				specialNeedProfileItemReportService,
			final Boolean enabled) {
		super(requiredAuthorizations, includePage, name, profileItemRegistry,
				enabled);
		this.specialNeedProfileItemReportService 
			= specialNeedProfileItemReportService;
	}
	
	/** {@inhetiDoc} */
	@Override
	public void build(final Map<String, Object> map, final Offender offender,
			final UserAccount userAccount, final Date date) {
		map.put(SPECIAL_NEED_COUNT_MODEL_KEY, 
				this.specialNeedProfileItemReportService
					.findSpecialNeedCountByOffenderAndDate(offender, date));
	}
}
