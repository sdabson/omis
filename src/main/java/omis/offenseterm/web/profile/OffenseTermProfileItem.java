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
 * Offense term profile item.
 * 
 * @author Josh Divine
 * @author Stephen Abson
 * @version 0.1.0 (May 16, 2017)
 * @since OMIS 3.0 
 */
public class OffenseTermProfileItem 
extends AbstractProfileItem 
implements ProfileItem {

	private static final long serialVersionUID = 1L;
	
	private static final String OFFENSE_TERM_COUNT_MODEL_KEY 
		= "offenseTermCount";
	
	private final OffenseTermProfileItemService offenseTermProfileItemService;

	/**
	 * Constructor.
	 * 
	 * @param requiredAuthorizations required authorizations
	 * @param includePage include page
	 * @param name name
	 * @param profileItemRegistry profile item registry
	 * @param offenseTermProfileItemService offense term profile item service
	 * @param enabled whether enabled
	 */
	public OffenseTermProfileItem(final Set<String> requiredAuthorizations, 
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
		modelMap.put(OFFENSE_TERM_COUNT_MODEL_KEY, 
				this.offenseTermProfileItemService
				.findOffenseTermCountByPerson(offender));
	}
}
