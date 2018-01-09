package omis.detainernotification.web.profile;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import omis.detainernotification.report.DetainerNotificationProfileItemReportService;
import omis.offender.domain.Offender;
import omis.user.domain.UserAccount;
import omis.web.profile.AbstractProfileItem;
import omis.web.profile.ProfileItemRegistry;

/**
 * DetainerNotificationProfileItem.java
 * 
 *@author Annie Jacques 
 *@author Trevor Isles
 *@version 0.1.0 (Dec 9, 2016)
 *@since OMIS 3.0
 *
 */
public class DetainerNotificationProfileItem extends AbstractProfileItem{

	private static final long serialVersionUID = 1L;
	
	private static final String COUNT_MODEL_KEY = "detainerNotificationCount";
	
	private final DetainerNotificationProfileItemReportService
		detainerNotificationProfileItemReportService;

	/**
	 * @param requiredAuthorizations
	 * @param includePage
	 * @param name
	 * @param profileItemRegistry
	 * @param detainerNotificationProfileItemReportService
	 * @param enabled whether enabled
	 */
	public DetainerNotificationProfileItem(
			final Set<String> requiredAuthorizations,
			final String includePage,
			final String name,
			final ProfileItemRegistry profileItemRegistry,
			final DetainerNotificationProfileItemReportService 
				detainerNotificationProfileItemReportService,
			final Boolean enabled) {
		super(requiredAuthorizations, includePage, name, profileItemRegistry,
				enabled);
		this.detainerNotificationProfileItemReportService =
				detainerNotificationProfileItemReportService;
	}

	/**{@inheritDoc} */
	@Override
	public void build(final Map<String, Object> modelMap,
			final Offender offender, final UserAccount userAccount,
			final Date date) {
		Integer count = this.detainerNotificationProfileItemReportService
				.findDetainerNotificationCountByOffender(offender);
		modelMap.put(COUNT_MODEL_KEY, count);
	}

}
