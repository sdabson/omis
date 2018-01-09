package omis.supervisionfee.web.profile;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import omis.offender.domain.Offender;
import omis.supervisionfee.report.SupervisionFeeProfileItemReportService;
import omis.user.domain.UserAccount;
import omis.web.profile.AbstractProfileItem;
import omis.web.profile.ProfileItem;
import omis.web.profile.ProfileItemRegistry;

/** Profile item for supervision fee.
 * @author Ryan Johns
 * @author Trevor Isles
 * @version 0.1.0 (Mar 17, 2016)
 * @since OMIS 3.0 */
public class SupervisionFeeProfileItem 
	extends AbstractProfileItem 
	implements ProfileItem {
	private static final long serialVersionUID = 1L;
	private static final String SUPERVISION_FEE_COUNT_MODEL_KEY 
		= "supervisionFeeCount";
	
	private final SupervisionFeeProfileItemReportService 
		supervisionFeeProfileItemReportService;
	
	/** Constructor.
	 * @param requiredAuthorizations - required authorizations.
	 * @param includePage - include page.
	 * @param name - name.
	 * @param profileItemRegistry - profile item registry.
	 * @param supervisionFeeProfileItemReportService - supervision fee profile
	 * item report service. 
	 * @param enabled - whether enabled */
	public SupervisionFeeProfileItem(final Set<String> requiredAuthorizations, 
			final String includePage, 
			final String name,
			final ProfileItemRegistry profileItemRegistry, 
			final SupervisionFeeProfileItemReportService 
				supervisionFeeProfileItemReportService,
			final Boolean enabled) { 
		super(requiredAuthorizations, includePage, name, profileItemRegistry,
				enabled);
		this.supervisionFeeProfileItemReportService 
			= supervisionFeeProfileItemReportService;
	}
	
	/** {@inheritDoc} */
	@Override
	public void build(final Map<String, Object> map, 
			final Offender offender, final UserAccount userAccount, 
			final Date date) {
		map.put(SUPERVISION_FEE_COUNT_MODEL_KEY, 
				this.supervisionFeeProfileItemReportService
					.findSupervisionFeeCountByOffenderAndDate(offender, date));
	}
}
