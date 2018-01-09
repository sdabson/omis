package omis.hearing.web.profile;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import omis.hearing.report.ViolationProfileItemService;
import omis.offender.domain.Offender;
import omis.user.domain.UserAccount;
import omis.web.profile.AbstractProfileItem;
import omis.web.profile.ProfileItem;
import omis.web.profile.ProfileItemRegistry;

/**
 * Violation profile item.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 19, 2017)
 * @since OMIS 3.0
 */
public class ViolationProfileItem extends AbstractProfileItem
	implements ProfileItem {

	private static final long serialVersionUID = 1L;
	
	private static final String VIOLATION_COUNT_MODEL_KEY 
		= "violationProfileItemSummary";
	
	private final ViolationProfileItemService violationProfileItemService;
	

	/** Instantiates an implementation of ViolationProfileItem */
	public ViolationProfileItem(final Set<String> requiredAuthorizations, 
			final String includePage, final String name,
			final ProfileItemRegistry profileItemRegistry,
			final ViolationProfileItemService violationProfileItemService,
			final Boolean enabled) {
		super(requiredAuthorizations, includePage, name, profileItemRegistry,
				enabled);
		this.violationProfileItemService = violationProfileItemService;
	}

	/** {@inheritDoc} */
	@Override
	public void build(Map<String, Object> modelMap, 
			final Offender offender, final UserAccount userAccount, 
			final Date date) {
		modelMap.put(VIOLATION_COUNT_MODEL_KEY, 
				this.violationProfileItemService
				.findViolationSummaryByOffender(offender));
	}
}