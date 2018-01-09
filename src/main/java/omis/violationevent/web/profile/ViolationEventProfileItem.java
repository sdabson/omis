package omis.violationevent.web.profile;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import omis.offender.domain.Offender;
import omis.user.domain.UserAccount;
import omis.violationevent.report.ViolationEventProfileItemService;
import omis.web.profile.AbstractProfileItem;
import omis.web.profile.ProfileItem;
import omis.web.profile.ProfileItemRegistry;

/**
 * Violation event profile item.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 13, 2017)
 * @since OMIS 3.0
 */
public class ViolationEventProfileItem  extends AbstractProfileItem implements ProfileItem {

	private static final long serialVersionUID = 1L;
	
	private static final String VIOLATION_EVENT_COUNT_MODEL_KEY
		= "violationEventCount";
	
	private final ViolationEventProfileItemService violationEventProfileItemService;

	/** Instantiates an implementation of ViolationEventProfileItem */
	public ViolationEventProfileItem(final Set<String> requiredAuthorizations, 
			final String includePage, 
			final String name,
			final ProfileItemRegistry profileItemRegistry, 
			final ViolationEventProfileItemService 
				violationEventProfileItemService,
			final Boolean enabled) {
		super(requiredAuthorizations, includePage, name, profileItemRegistry,
				enabled);
		this.violationEventProfileItemService 
			= violationEventProfileItemService;
	}

	/** {@inheritDoc} */
	@Override
	public void build(final Map<String, Object> modelMap, 
			final Offender offender, final UserAccount userAccount, Date date) {
		modelMap.put(VIOLATION_EVENT_COUNT_MODEL_KEY, 
				this.violationEventProfileItemService
					.findViolationEventCountByOffender(offender));
	}
}