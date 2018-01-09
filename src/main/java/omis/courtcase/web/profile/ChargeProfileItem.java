package omis.courtcase.web.profile;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import omis.courtcase.report.ChargeProfileItemService;
import omis.offender.domain.Offender;
import omis.user.domain.UserAccount;
import omis.web.profile.AbstractProfileItem;
import omis.web.profile.ProfileItem;
import omis.web.profile.ProfileItemRegistry;

/** 
 * Charge profile item.
 *
 * @author Josh Divine
 * @version 0.1.0 (Aug 16, 2017)
 * @since OMIS 3.0 
 */
public class ChargeProfileItem 
	extends AbstractProfileItem 
	implements ProfileItem {
	private final static long serialVersionUID = 1L;
	private final static String CHARGE_COUNT_MODEL_KEY = "chargeCount";
	
	private final ChargeProfileItemService chargeProfileItemService;
	
	/** 
	 * Constructor.
	 * 
	 * @param requireAuthorizations require authorizations
	 * @param includePage include page
	 * @param name name
	 * @param profileItemRegistry profile item registry
	 * @param chargeProfileItemService charge profile item service
	 * @param enabled whether enabled
	 */
	public ChargeProfileItem(final Set<String> requiredAuthorizations,
			final String includePage,
			final String name,
			final ProfileItemRegistry profileItemRegistry,
			final ChargeProfileItemService chargeProfileItemService,
			final Boolean enabled) {
		super(requiredAuthorizations, includePage, name, profileItemRegistry,
				enabled);
		this.chargeProfileItemService = chargeProfileItemService;
	}

	/** {@inheritDoc} */
	@Override
	public void build(final Map<String, Object> map, 
			final Offender offender, final UserAccount userAccount, 
			final Date date) {
		map.put(CHARGE_COUNT_MODEL_KEY, 
				this.chargeProfileItemService
				.findChargeCountByOffender(offender));
	}
}
