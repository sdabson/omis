package omis.custody.web.profile;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import omis.custody.report.CustodyProfileItemReportService;
import omis.offender.domain.Offender;
import omis.user.domain.UserAccount;
import omis.web.profile.AbstractProfileItem;
import omis.web.profile.ProfileItem;
import omis.web.profile.ProfileItemRegistry;

/** Profile item for custody review.
 * @author Ryan Johns
 * @author Trevor Isles
 * @version 0.1.0 (Mar 14, 2016)
 * @since OMIS 3.0 */
public class CustodyReviewProfileItem 
	extends AbstractProfileItem 
	implements ProfileItem {
	private static final long serialVersionUID = 1L;
	private static final String CUSTODY_COUNT_MODEL_KEY = "custodyCount";
	
	private final CustodyProfileItemReportService 
		custodyProfileItemReportService;
	
	/** Constructor.
	 * @param requiredAuthorizations - required authorizations.
	 * @param includePage - include page.
	 * @param name - name.
	 * @param profileItemRegistry - profile item registry.
	 * @param custodyProfileItemService - custody profile item service. 
	 * @param enabled whether enabled */
	public CustodyReviewProfileItem(final Set<String> requiredAuthorizations,
			final String includePage,
			final String name,
			final ProfileItemRegistry profileItemRegistry,
			final CustodyProfileItemReportService 
				custodyProfileItemReportService,
			final Boolean enabled) {
		super(requiredAuthorizations, includePage, name, profileItemRegistry,
				enabled);
		this.custodyProfileItemReportService = custodyProfileItemReportService;
	}

	/** {@inheritDoc} */
	@Override
	public void build(final Map<String, Object> map, 
			final Offender offender, final UserAccount userAccount, 
			final Date date) {
		map.put(CUSTODY_COUNT_MODEL_KEY, 
				this.custodyProfileItemReportService
				.findCustodyCountByOffender(
						offender));
	}
}
