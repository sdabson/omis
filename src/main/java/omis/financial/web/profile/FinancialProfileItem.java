package omis.financial.web.profile;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import omis.financial.report.FinancialProfileItemReportService;
import omis.offender.domain.Offender;
import omis.user.domain.UserAccount;
import omis.web.profile.AbstractProfileItem;
import omis.web.profile.ProfileItem;
import omis.web.profile.ProfileItemRegistry;

/** Profile item for profile item.
 * @author Ryan Johns
 * @author Trevor Isles
 * @version 0.1.1 (Apr 18, 2017)
 * @since OMIS 3.0 */
public class FinancialProfileItem 
	extends AbstractProfileItem 
	implements ProfileItem {
	private static final long serialVersionUID = 1L;
	
	private static final String FINANCIAL_PROFILE_MODEL_KEY = 
			"financialProfileItemSummary";
	
	private final FinancialProfileItemReportService financialProfileItemReportService;
	
	/** Constructor.
	 * @param requiredAuthorizations - required authorization.
	 * @param includePage - include page.
	 * @param profileItemRegistry - profile item registry.
	 * @param name - name.
	 * @param financialProfileItemReportService - financial profile item report service. 
	 * @param enabled - whether enabled */
	public FinancialProfileItem(final Set<String> requiredAuthorizations,
			final String includePage,
			final ProfileItemRegistry profileItemRegistry,
			final String name,
			final FinancialProfileItemReportService 
				financialProfileItemReportService,
			final Boolean enabled) {
		super(requiredAuthorizations, includePage, name, profileItemRegistry,
				enabled);
		this.financialProfileItemReportService = financialProfileItemReportService;
	}
	
	/** {@inheritDoc} */
	@Override
	public void build(final Map<String, Object> map,
			final Offender offender, final UserAccount userAccount,
			final Date date) {
		map.put(FINANCIAL_PROFILE_MODEL_KEY, 
				this.financialProfileItemReportService
				.findProfileItemSummaryByOffender(offender));
	}

}
