package omis.workrestriction.web.profile;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import omis.offender.domain.Offender;
import omis.user.domain.UserAccount;
import omis.web.profile.AbstractProfileItem;
import omis.web.profile.ProfileItem;
import omis.web.profile.ProfileItemRegistry;
import omis.workrestriction.report.WorkRestrictionProfileSummaryReportService;

/** Profile item for work restrictions.
 * @author Ryan Johns
 * @author Trevor Isles
 * @version 0.1.0 (Sep 2, 2016)
 * @since OMIS 3.0 */
public class WorkRestrictionsProfileItem 
	extends AbstractProfileItem 
	implements ProfileItem {
	private static final long serialVersionUID = 1L;
	private static final String WORK_RESTRICTION_COUNT_MODEL_KEY 
		= "workRestrictionCount";
	private final WorkRestrictionProfileSummaryReportService 
		workRestrictionProfileSummaryReportService;
	
	/** Constructor. 
	 * @param requiredAuthorizations - required authorizations.
	 * @param includePage - include page.
	 * @param name - name..
	 * @param profileItemRegistry - profile item registry.
	 * @param workRestrictionProfileSummaryReportService - work restriction 
	 * profile summary report service.
	 * @param enabled - whether enabled */
	public WorkRestrictionsProfileItem(
			final Set<String> requiredAuthorizations, 
			final String includePage, final String name,
			final ProfileItemRegistry profileItemRegistry, 
			final WorkRestrictionProfileSummaryReportService 
				workRestrictionProfileSummaryReportService,
			final Boolean enabled) {
		super(requiredAuthorizations, includePage, name, profileItemRegistry,
				enabled);
		this.workRestrictionProfileSummaryReportService 
			= workRestrictionProfileSummaryReportService;
	}
	
	/** {@inheritDoc} */
	@Override
	public void build(final Map<String,Object>map, final Offender offender, 
			final UserAccount userAccount, final Date date) {
		map.put(WORK_RESTRICTION_COUNT_MODEL_KEY, 
				this.workRestrictionProfileSummaryReportService
					.findCountByOffender(offender));
	}
}
