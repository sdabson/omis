package omis.offenseterm.web.profile;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import omis.offender.domain.Offender;
import omis.offenseterm.report.OffenseTermProfileItemService;
import omis.user.domain.UserAccount;
import omis.web.profile.AbstractProfileItem;
import omis.web.profile.ProfileItem;
import omis.web.profile.ProfileItemRegistry;

/** 
 * Current offense term profile item.
 * 
 * @author Josh Divine
 * @author Stephen Abson
 * @version 0.1.0 (May 16, 2017)
 * @since OMIS 3.0 
 */
public class CurrentOffenseTermProfileItem 
extends AbstractProfileItem 
implements ProfileItem{

	private static final long serialVersionUID = 1L;


	private static final String CURRENT_OFFENSE_TERM_COUNT_MODEL_KEY 
		= "currentOffenseTermCount";
	
	private final OffenseTermProfileItemService offenseTermProfileItemService;

	/**
	 * Instantiates profile item for current offense.
	 * 
	 * @param requiredAuthorizations required authorizations
	 * @param includePage include page
	 * @param name name
	 * @param profileItemRegistry registry
	 * @param offenseTermProfileItemService service
	 * @param enabled whether enabled
	 */
	public CurrentOffenseTermProfileItem(
			final Set<String> requiredAuthorizations, 
			final String includePage, final String name,
			final ProfileItemRegistry profileItemRegistry,
			final OffenseTermProfileItemService offenseTermProfileItemService,
			final Boolean enabled) {
		super(requiredAuthorizations, includePage, name, profileItemRegistry,
				enabled);
		this.offenseTermProfileItemService = offenseTermProfileItemService;
	}

	/** {@inheritDoc} */
	@Override
	public void build(final Map<String, Object> modelMap, 
			final Offender offender, final UserAccount userAccount, 
			final Date date) {
		modelMap.put(CURRENT_OFFENSE_TERM_COUNT_MODEL_KEY, 
				this.offenseTermProfileItemService
				.findCurrentOffenseTermCountByPerson(offender));
	}
}
