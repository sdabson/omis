package omis.location.web.profile;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import omis.location.report.LocationProfileItemReportService;
import omis.offender.domain.Offender;
import omis.user.domain.UserAccount;
import omis.web.profile.AbstractProfileItem;
import omis.web.profile.ProfileItem;
import omis.web.profile.ProfileItemRegistry;

/** Profile item for location.
 * @author Ryan Johns
 * @author Trevor Isles
 * @version 0.1.0 (Mar 16, 2016)
 * @since OMIS 3.0 */
public class LocationProfileItem 
	extends AbstractProfileItem 
	implements ProfileItem {
	private static final long serialVersionUID = 1L;
	private static final String LOCATION_EXISTS_MODEL_KEY 
		= "locationTermExists";
	
	private final LocationProfileItemReportService 
		locationProfileItemReportService;
	
	/** Constructor.
	 * @param requiredAuthorizations - required authorizations.
	 * @param includePage - include page.
	 * @param name - name.
	 * @param profileItemRegistry - profile item registry.
	 * @param locationProfileItemReportService - 
	 * location profile item report service. 
	 * @param enabled - whether enabled */
	public LocationProfileItem(final Set<String> requiredAuthorizations, 
			final String includePage, 
			final String name,
			final ProfileItemRegistry profileItemRegistry,
			final LocationProfileItemReportService 
				locationProfileItemReportService,
			final Boolean enabled) {
		super(requiredAuthorizations, includePage, name, profileItemRegistry,
				enabled);
		this.locationProfileItemReportService = 
				locationProfileItemReportService;
	}
	
	/** {@inhritDoc} */
	@Override
	public void build(final Map<String, Object> map, final Offender offender,
			final UserAccount userAccount, final Date date) { 
		map.put(LOCATION_EXISTS_MODEL_KEY, 
				this.locationProfileItemReportService
				.findLocationTermExistenceByOffenderAndDate(offender, date)); 
	}

}
