package omis.stg.web.profile;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import omis.offender.domain.Offender;
import omis.stg.report.StgProfileItemReportService;
import omis.user.domain.UserAccount;
import omis.web.profile.AbstractProfileItem;
import omis.web.profile.ProfileItem;
import omis.web.profile.ProfileItemRegistry;

/** Profile for stg.
 * @author Ryan Johns
 * @author Trevor Isles
 * @version 0.1.0 (Mar 17, 2016)
 * @since OMIS 3.0 */
public class StgProfileItem 
	extends AbstractProfileItem 
	implements ProfileItem {
	private static final long serialVersionUID = 1L;
	private static final String STG_COUNT_MODEL_KEY = "stgCount";
	
	private StgProfileItemReportService stgProfileItemReportService;
	
	/** Constructor. 
	 * @param requiredAuthorizations - required authorizations.
	 * @param includePage - include page.
	 * @param name - name.
	 * @param profileItemRegistry - profile item registry.
	 * @param stgProfileItemReportService - stg profile item report service. 
	 * @param enabled - whether enabled */
	public StgProfileItem(final Set<String> requiredAuthorizations, 
			final String includePage, 
			final String name,
			final ProfileItemRegistry profileItemRegistry, 
			final StgProfileItemReportService stgProfileItemReportService,
			final Boolean enabled) { 
		super(requiredAuthorizations, includePage, name, profileItemRegistry,
				enabled);
		this.stgProfileItemReportService = stgProfileItemReportService;
	}

	/** {@inheritDoc} */
	@Override
	public void build(final Map<String, Object> map, 
			final Offender offender, final UserAccount userAccount, 
			final Date date) { 
		map.put(STG_COUNT_MODEL_KEY, this.stgProfileItemReportService
				.findStgAffiliationCountByOffenderAndDate(offender, date)); 
	}
}
