package omis.offender.web.profile;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import omis.offender.domain.Offender;
import omis.user.domain.UserAccount;
import omis.web.profile.AbstractProfileItem;
import omis.web.profile.ProfileItem;
import omis.web.profile.ProfileItemRegistry;
/** Profile Item for offender details.
 * @author Ryan Johns
 * @author Trevor Isles
 * @version 0.1.0 (Apr 12, 2016)
 * @since OMIS 3.0 */
public class OffenderDetailsProfileItem 
	extends AbstractProfileItem 
	implements ProfileItem {
	private static final long serialVersionUID = 1L;
	/** Constructor.
	 * @param requiredAuthorizations - required authorizations.
	 * @param profileItemRegistry - profile item registry.
	 * @param name - name. 
	 * @param enabled - whether enabled */
	public OffenderDetailsProfileItem(
			final Set<String> requiredAuthorizations,
			final String includePage,
			final ProfileItemRegistry profileItemRegistry,
			final String name,
			final Boolean enabled) {
		super(requiredAuthorizations, includePage, name, profileItemRegistry,
				enabled);
	}
	
	
	/** {@inheritDoc} */
	@Override
	public void build(final Map<String, Object> map, final Offender offender,
			final UserAccount userAccount, final Date date) {
	}
}
