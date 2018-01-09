package omis.employment.web.profile;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import omis.employment.report.EmploymentProfileItemReportService;
import omis.offender.domain.Offender;
import omis.user.domain.UserAccount;
import omis.web.profile.AbstractProfileItem;
import omis.web.profile.ProfileItem;
import omis.web.profile.ProfileItemRegistry;

/** Profile item for employment item.
 * @author Ryan Johns
 * @author Trevor Isles
 * @version 0.1.0 (Mar 15, 2016)
 * @since OMIS 3.0 */
public class EmploymentProfileItem 
	extends AbstractProfileItem 
	implements ProfileItem {
	private static final long serialVersionUID = 1L;
	private static final String EMPLOYMENT_COUNT_MODEL_KEY 
		= "employmentCount";
	
	private final EmploymentProfileItemReportService 
		employmentProfileItemReportService;
	
	/** Constructor.
	 * @param requiredAuthorizations - required authorizations.
	 * @param includePage - include page.
	 * @param name - name.
	 * @param profileItemRegistry - profile item registry.
	 * @param employmentProfileItemReportService - employment profile 
	 * item report service. 
	 * @param enabled - whether enabled */
	public EmploymentProfileItem(final Set<String> requiredAuthorizations, 
			final String includePage,
			final String name,
			final ProfileItemRegistry profileItemRegistry, 
			final EmploymentProfileItemReportService 
				employmentProfileItemReportService,
			final Boolean enabled) {
		super(requiredAuthorizations, includePage, name, profileItemRegistry,
				enabled);
		this.employmentProfileItemReportService 
			= employmentProfileItemReportService;
	}
	
	/** {@inheritDoc} */
	@Override
	public void build(final Map<String, Object> map, 
			final Offender offender, final UserAccount userAccount, 
			final Date date) {
		map.put(EMPLOYMENT_COUNT_MODEL_KEY, 
				this.employmentProfileItemReportService
				.findEmploymentCountByOffenderAndDate(offender, date));
	}
}
