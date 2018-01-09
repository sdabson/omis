package omis.hearing.web.profile;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import omis.hearing.report.HearingProfileItemService;
import omis.offender.domain.Offender;
import omis.user.domain.UserAccount;
import omis.web.profile.AbstractProfileItem;
import omis.web.profile.ProfileItem;
import omis.web.profile.ProfileItemRegistry;

/**
 * Hearing profile item.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jun 26, 2017)
 * @since OMIS 3.0
 */
public class HearingProfileItem extends AbstractProfileItem 
	implements ProfileItem {

	private static final long serialVersionUID = 1L;
	
	private static final String HEARING_COUNT_MODEL_KEY = "hearingProfileItemSummary";
	
	private final HearingProfileItemService hearingProfileItemService;

	/** Instantiates an implementation of HearingProfileItem */
	public HearingProfileItem(final Set<String> requiredAuthorizations, 
			final String includePage, final String name,
			final ProfileItemRegistry profileItemRegistry,
			final HearingProfileItemService hearingProfileItemService,
			final Boolean enabled) {
		super(requiredAuthorizations, includePage, name, profileItemRegistry,
				enabled);
		this.hearingProfileItemService = hearingProfileItemService;
	}

	/** {@inheritDoc} */
	@Override
	public void build(final Map<String, Object> map, 
			final Offender offender, final UserAccount userAccount, 
			final Date date) {
		map.put(HEARING_COUNT_MODEL_KEY, 
				this.hearingProfileItemService
				.findHearingProfileItemSummaryByOffender(offender));
	}
}