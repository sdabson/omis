package omis.adaaccommodation.web.profile;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import omis.adaaccommodation.report.AdaProfileItemReportService;
import omis.offender.domain.Offender;
import omis.user.domain.UserAccount;
import omis.web.profile.AbstractProfileItem;
import omis.web.profile.ProfileItem;
import omis.web.profile.ProfileItemRegistry;

/** ADA profile item.
 * @author Ryan JOhns
 * @version 0.1.0 (Mar 10, 2016)
 * @since OMIS 3.0 */
public class AdaProfileItem 
	extends AbstractProfileItem 
	implements ProfileItem {
	private static final long serialVersionUID = 1L;
	
	private static final String ADA_ACCOMMODATION_COUNT_MODEL_KEY 
		= "adaCount";
	
	
	private final AdaProfileItemReportService adaProfileItemReportService;
	
	/** Constructor. 
	 * @param requiredAuthorizations - required authorizations.
	 * @param includePage - include page.
	 * @param profileItemRegistry - profile item registry.
	 * @param name - name.
	 * @param adaProfileItemReportService - ada profile item report service.
	 * @param enabled whether enabled */
	public AdaProfileItem(final Set<String> requiredAuthorizations, 
			final String includePage, 
			final ProfileItemRegistry profileItemRegistry,
			final String name,
			final AdaProfileItemReportService adaProfileItemReportService,
			final Boolean enabled) {
		super(requiredAuthorizations, includePage, name, profileItemRegistry,
				enabled);
		this.adaProfileItemReportService = adaProfileItemReportService;
	}
	
	public void build(final Map<String, Object> map, 
			final Offender offender, final UserAccount userAccount, 
			final Date date) {
		map.put(ADA_ACCOMMODATION_COUNT_MODEL_KEY, this
				.adaProfileItemReportService
				.findCountByOffenderAndDate(offender, date));
	}
}
