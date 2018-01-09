package omis.family.web.profile;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import omis.family.report.FamilyProfileItemReportService;
import omis.offender.domain.Offender;
import omis.user.domain.UserAccount;
import omis.web.profile.AbstractProfileItem;
import omis.web.profile.ProfileItem;
import omis.web.profile.ProfileItemRegistry;

/** Profile item for family.
 * @author Ryan Johns
 * @author Trevor Isles
 * @version 0.1.0 (Mar 15, 2016)
 * @since OMIS 3.0 */
public class FamilyProfileItem 
	extends AbstractProfileItem 
	implements ProfileItem {
	private static final long serialVersionUID = 1L;
	private static final String FAMILY_COUNT_MODEL_KEY 
		= "familyCount";
	
	private final FamilyProfileItemReportService 
		familyProfileItemReportService;
	
	/** Constructor.
	 * @param requiredAuthorizations - required authorizations.
	 * @param includePage - include page.
	 * @param name - name.
	 * @param profileItemRegistry - profile item registry.
	 * @param familyProfileItemReportService - 
	 * family profile item report service. 
	 * @param enabled - whether enabled */
	public FamilyProfileItem(final Set<String> requiredAuthorizations, 
			final String includePage, 
			final String name,
			final ProfileItemRegistry profileItemRegistry, 
			final FamilyProfileItemReportService 
				familyProfileItemReportService,
			final Boolean enabled) {
		super(requiredAuthorizations, includePage, name, profileItemRegistry,
				enabled);
		this.familyProfileItemReportService = familyProfileItemReportService;
	}
	
	/** {@inheritDoc} */
	@Override
	public void build(final Map<String, Object> map, 
			final Offender offender, final UserAccount userAccount, 
			final Date date) {
		map.put(FAMILY_COUNT_MODEL_KEY, this.familyProfileItemReportService
				.findFamilyCountByOffenderAndDate(offender, date));
	}

}
