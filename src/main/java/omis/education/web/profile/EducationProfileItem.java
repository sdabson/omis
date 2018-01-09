package omis.education.web.profile;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import omis.education.report.EducationProfileItemReportService;
import omis.offender.domain.Offender;
import omis.user.domain.UserAccount;
import omis.web.profile.AbstractProfileItem;
import omis.web.profile.ProfileItem;
import omis.web.profile.ProfileItemRegistry;

/** Profile item for education item.
 * @author Ryan Johns
 * @author Trevor Isles
 * @version 0.1.0 (Aug 30, 2016)
 * @since OMIS 3.0 */
public class EducationProfileItem 
	extends AbstractProfileItem 
	implements ProfileItem {
	private static final long serialVersionUID = 1L;
	private static final String EDUCATION_PROFILE_SUMMARY_MODEL_KEY =
			"educationProfileSummaries";
	private final EducationProfileItemReportService 
		educationProfileItemReportService;
	
	/** Constructor.
	 * @param requiredAuthorizations - required authorizations.
	 * @param includePage - include page.
	 * @param name - name.
	 * @param profileItemRegistry - profile item registry.
	 * @param educationProfileItemReportService - education profile item report service. 
	 * @param enabled - whether enabled */
	public EducationProfileItem(final Set<String> requiredAuthorizations,
			final String includePage,
			final String name,
			final ProfileItemRegistry profileItemRegistry,
			final EducationProfileItemReportService educationProfileItemReportService,
			final Boolean enabled) {
		super(requiredAuthorizations, includePage, name, profileItemRegistry,
				enabled);
		this.educationProfileItemReportService = educationProfileItemReportService;
	}
	
	/** {@inheritDoc} */
	@Override
	public void build(final Map<String, Object> map,
			final Offender offender, final UserAccount userAccount, final Date date) {
		map.put(EDUCATION_PROFILE_SUMMARY_MODEL_KEY, 
				this.educationProfileItemReportService
				.findEducationSummaryByOffender(offender));
	}
}
