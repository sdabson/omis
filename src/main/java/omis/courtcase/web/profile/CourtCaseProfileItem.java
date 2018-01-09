package omis.courtcase.web.profile;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import omis.courtcase.report.CourtCaseProfileItemService;
import omis.offender.domain.Offender;
import omis.user.domain.UserAccount;
import omis.web.profile.AbstractProfileItem;
import omis.web.profile.ProfileItem;
import omis.web.profile.ProfileItemRegistry;

/** Court case profile item.
 * @author Ryan Johns
 * @author Stephen Abson
 * @version 0.1.0 (Mar 14, 2016)
 * @since OMIS 3.0 */
public class CourtCaseProfileItem 
	extends AbstractProfileItem 
	implements ProfileItem {
	private final static long serialVersionUID = 1L;
	private final static String COURT_CASE_COUNT_MODEL_KEY
		= "courtCaseCount";
	
	private final CourtCaseProfileItemService courtCaseProfileItemService;
	
	/** Constructor.
	 * @param requireAuthorizations - require authorizations.
	 * @param includePage - include page.
	 * @param name - name.
	 * @param profileItemRegistry - profile item registry.
	 * @param courtCaseProfileItemService - court case profile item service
	 * @param enabled whether enabled
	 */
	public CourtCaseProfileItem(final Set<String> requiredAuthorizations,
			final String includePage,
			final String name,
			final ProfileItemRegistry profileItemRegistry,
			final CourtCaseProfileItemService courtCaseProfileItemService,
			final Boolean enabled) {
		super(requiredAuthorizations, includePage, name, profileItemRegistry,
				enabled);
		this.courtCaseProfileItemService = courtCaseProfileItemService;
	}

	/** {@inheritDoc} */
	@Override
	public void build(final Map<String, Object> map, 
			final Offender offender, final UserAccount userAccount, 
			final Date date) {
		map.put(COURT_CASE_COUNT_MODEL_KEY, 
				this.courtCaseProfileItemService
				.findCourtCaseCountByOffender(offender));
	}
}
