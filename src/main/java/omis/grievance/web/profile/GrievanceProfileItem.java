package omis.grievance.web.profile;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import omis.grievance.report.GrievanceProfileItemReportService;
import omis.offender.domain.Offender;
import omis.user.domain.UserAccount;
import omis.web.profile.AbstractProfileItem;
import omis.web.profile.ProfileItem;
import omis.web.profile.ProfileItemRegistry;

/** Profile item for grievance.
 * @author Ryan Johns
 * @author Trevor Isles
 * @version 0.1.0 (Mar 16, 2016)
 * @since OMIS 3.0 */
public class GrievanceProfileItem 
	extends AbstractProfileItem 
	implements ProfileItem {
	private static final long serialVersionUID = 1L;
	private static final String GRIEVANCE_COUNT_MODEL_KEY 
		= "grievanceCount";
	
	private final GrievanceProfileItemReportService 
		grievanceProfileItemReportService;
	
	/** Constructor.
	 * @param requiredAuthorizations - required authorizations.
	 * @param includePage - include page.
	 * @param name - name.
	 * @param profileItemRegistry - profile item registry.
	 * @param grievanceProfileItemReportService - grievance profile item
	 * report service. 
	 * @param enabled - whether enabled*/
	public GrievanceProfileItem(final Set<String> requiredAuthorizations, 
			final String includePage,
			final String name,
			final ProfileItemRegistry profileItemRegistry,
			final GrievanceProfileItemReportService 
				grievanceProfileItemReportService,
			final Boolean enabled) {
		super(requiredAuthorizations, includePage, name, profileItemRegistry,
				enabled);
		this.grievanceProfileItemReportService 
			= grievanceProfileItemReportService;
	}
	
	/** {@inheritDoc} */
	@Override
	public void build(final Map<String, Object> map, 
			final Offender offender, final UserAccount userAccount, 
			final Date date) {
		map.put(GRIEVANCE_COUNT_MODEL_KEY, this
				.grievanceProfileItemReportService
				.findGrievanceCountByOffenderAndDate(offender, date));
	}
		
}
