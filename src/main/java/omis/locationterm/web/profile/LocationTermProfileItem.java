package omis.locationterm.web.profile;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import omis.locationterm.report.LocationTermProfileItemReportService;
import omis.offender.domain.Offender;
import omis.user.domain.UserAccount;
import omis.web.profile.AbstractProfileItem;
import omis.web.profile.ProfileItem;
import omis.web.profile.ProfileItemRegistry;

/**
 * Location term profile item.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class LocationTermProfileItem
		extends AbstractProfileItem
		implements ProfileItem {
	
	private static final long serialVersionUID = 1L;

	/* Model keys. */
	
	private static final String LOCATION_TERM_EXISTS_MODEL_KEY
		= "locationTermExists";
	
	/* Report services. */
	
	private final LocationTermProfileItemReportService
	locationTermProfileItemReportService;
	
	/* Constructors. */
	
	/**
	 * Instantiates location term profile item.
	 * 
	 * @param requiredAuthorizations required auhorizations
	 * @param includePage include page
	 * @param name name
	 * @param profileItemRegistry profile item registry
	 * @param locationTermProfileItemReportService service
	 * @param enabled whether enabled
	 */
	public LocationTermProfileItem(
			final Set<String> requiredAuthorizations,
			final String includePage,
			final String name,
			final ProfileItemRegistry profileItemRegistry,
			final LocationTermProfileItemReportService
				locationTermProfileItemReportService,
			final Boolean enabled) {
		super(requiredAuthorizations, includePage, name, profileItemRegistry,
				enabled);
		this.locationTermProfileItemReportService
			= locationTermProfileItemReportService;
	}
	
	/* Method implementations. */

	/** {@inheritDoc} */
	@Override
	public void build(
			final Map<String, Object> modelMap,
			final Offender offender,
			final UserAccount userAccount,
			final Date date) {
		boolean locationTermExists
			 = this.locationTermProfileItemReportService
			 	.findLocationExistenceByOffenderAndDate(offender, date);
		modelMap.put(LOCATION_TERM_EXISTS_MODEL_KEY, locationTermExists);
	}
}