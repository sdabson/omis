package omis.religion.web.profile;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import omis.offender.domain.Offender;
import omis.religion.report.ReligionProfileItemReportService;
import omis.user.domain.UserAccount;
import omis.web.profile.AbstractProfileItem;
import omis.web.profile.ProfileItem;
import omis.web.profile.ProfileItemRegistry;

/** Profile item for religion.
 * @author Ryan Johns
 * @author Trevor Isles
 * @version 0.1.0 (Mar 16, 2016)
 * @since OMIS 3.0 */
public class ReligionProfileItem 
	extends AbstractProfileItem 
	implements ProfileItem {
	private static final long serialVersionUID = 1L;
	private static final String RELIGIOUS_PREFERENCE_COUNT_MODEL_KEY
		= "religousPreferenceCount";
	
	private final ReligionProfileItemReportService 
		religionProfileItemReportService;
	
	/** Constructor.
	 * @param requiredAuthorizations - required authorizations.
	 * @param includePage - include page.
	 * @param name - name.
	 * @param profileItemRegistry - profile item registry.
	 * @param religionProfileItemReportService - religion profile item report 
	 * service. 
	 * @param enabled - whether enabled */
	public ReligionProfileItem(final Set<String> requiredAuthorizations, 
			final String includePage, 
			final String name,
			final ProfileItemRegistry profileItemRegistry, 
			final ReligionProfileItemReportService 
				religionProfileItemReportService,
			final Boolean enabled) {
		super(requiredAuthorizations, includePage, name, profileItemRegistry,
				enabled);
		this.religionProfileItemReportService 
			= religionProfileItemReportService;
	}
	
	/** {@inheritDoc} */
	@Override
	public void build(final Map<String, Object> map, 
			final Offender offender, final UserAccount userAccount, 
			final Date date) {
		map.put(RELIGIOUS_PREFERENCE_COUNT_MODEL_KEY, 
				this.religionProfileItemReportService
					.findReligiousPreferenceCountByOffenderAndDate(offender, 
							date));
	}
}
