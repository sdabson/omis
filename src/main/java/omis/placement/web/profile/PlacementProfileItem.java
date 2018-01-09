package omis.placement.web.profile;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import omis.offender.domain.Offender;
import omis.placement.report.PlacementProfileItemReportService;
import omis.user.domain.UserAccount;
import omis.web.profile.AbstractProfileItem;
import omis.web.profile.ProfileItem;
import omis.web.profile.ProfileItemRegistry;

/** Profile item for placement.
 * @author Ryan Johns
 * @author Stephen Abson
 * @version 0.1.0 (Mar 16, 2016)
 * @since OMIS 3.0 */
public class PlacementProfileItem 
	extends AbstractProfileItem 
	implements ProfileItem {
	private static final long serialVersionUID = 1L;
	private static final String PLACEMENT_EXISTS_MODEL_KEY 
		= "placementExists";
	
	private final PlacementProfileItemReportService 
		placementProfileItemReportService;
	
	/** Constructor.
	 * @param requiredAuthorizations - required authorizations.
	 * @param includePage - include page.
	 * @param name - name.
	 * @param profileItemRegistry - profile item registry.
	 * @param PlacementProfileItemReportService - 
	 * placement profile item report service
	 * @param enabled whether enabled
	 */
	public PlacementProfileItem(final Set<String> requiredAuthorizations, 
			final String includePage, 
			final String name,
			final ProfileItemRegistry profileItemRegistry, 
			final PlacementProfileItemReportService 
				placementProfileItemReportService,
			final Boolean enabled) {
		super(requiredAuthorizations, includePage, name, profileItemRegistry,
				enabled);
		this.placementProfileItemReportService 
			= placementProfileItemReportService;
	}

	/** {@inheritDoc} */
	@Override
	public void build(final Map<String, Object> map, 
			final Offender offender, final UserAccount userAccount, 
			final Date date) {
		map.put(PLACEMENT_EXISTS_MODEL_KEY, 
				this.placementProfileItemReportService
				.findPlacementExistenceByOffenderAndDate(offender, date));
	}
}
