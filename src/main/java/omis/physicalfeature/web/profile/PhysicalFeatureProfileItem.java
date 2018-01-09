package omis.physicalfeature.web.profile;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import omis.offender.domain.Offender;
import omis.physicalfeature.report.PhysicalFeatureProfileItemReportService;
import omis.user.domain.UserAccount;
import omis.web.profile.AbstractProfileItem;
import omis.web.profile.ProfileItem;
import omis.web.profile.ProfileItemRegistry;

/** Profile item for physical feature.
 * @author Ryan Johns
 * @author Trevor Isles
 * @version 0.1.0 (Mar 16, 2016)
 * @since OMIS 3.0 */
public class PhysicalFeatureProfileItem 
	extends AbstractProfileItem 
	implements ProfileItem {
	private static final long serialVersionUID = 1L;
	private static final String PHYSICAL_FEATURE_COUNT_MODEL_KEY 
		= "physicalFeatureCount";
	
	private final PhysicalFeatureProfileItemReportService 
		physicalFeatureProfileItemReportService;
	
	/** Constructor.
	 * @param requiredAuthorizations - required authorizations.
	 * @param includePage - include page.
	 * @param name - name.
	 * @param profileItemRegistry - profile item registry.
	 * @param PhysicalFeatureProfileItemReportService - physical feature 
	 * profile item report service. 
	 * @param enabled - whether enabled */
	public PhysicalFeatureProfileItem(
			final Set<String> requiredAuthorizations, 
			final String includePage, 
			final String name,
			final ProfileItemRegistry profileItemRegistry, 
			final PhysicalFeatureProfileItemReportService 
				physicalFeatureProfileItemReportService,
			final Boolean enabled) { 
		super(requiredAuthorizations, includePage, name, profileItemRegistry,
				enabled);
		this.physicalFeatureProfileItemReportService 
			= physicalFeatureProfileItemReportService;
	}
	
	/** {@inheritDoc} */
	@Override
	public void build(final Map<String, Object> map, 
			final Offender offender, final UserAccount userAccount, 
			final Date date) {
		map.put(PHYSICAL_FEATURE_COUNT_MODEL_KEY, 
				this.physicalFeatureProfileItemReportService
					.findPhysicalFeatureCountByOffender(offender));
	}
	
}
