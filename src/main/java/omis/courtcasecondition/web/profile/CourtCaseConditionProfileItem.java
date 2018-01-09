package omis.courtcasecondition.web.profile;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import omis.courtcasecondition.report.CourtCaseConditionProfileItemReportService;
import omis.offender.domain.Offender;
import omis.user.domain.UserAccount;
import omis.web.profile.AbstractProfileItem;
import omis.web.profile.ProfileItem;
import omis.web.profile.ProfileItemRegistry;

/**
 * CourtCaseConditionProfileItem.java
 *
 *@author Trevor Isles
 *@version 0.1.0 (August 3, 2017)
 *@since OMIS 3.0
 *
 */
public class CourtCaseConditionProfileItem extends AbstractProfileItem 
	implements ProfileItem {

	private static final long serialVersionUID = 1L;
	
	private static final String COUNT_MODEL_KEY = "courtCaseConditionCount";

	private final CourtCaseConditionProfileItemReportService 
		courtCaseConditionProfileItemReportService;
	
	/**
	 * Constructor
	 * @param requiredAuthorizations - required authorizations.
	 * @param includePage - include page.
	 * @param name - name
	 * @param profileItemRegistry - profile item registry.
	 * @param courtCaseConditionProfileItemReportService - court case condition 
	 * profile item report service
	 * @param enabled - whether enabled
	 */
	public CourtCaseConditionProfileItem(final Set<String> requiredAuthorizations,
			final String includePage,
			final String name,
			final ProfileItemRegistry profileItemRegistry,
			final CourtCaseConditionProfileItemReportService 
				courtCaseConditionProfileItemReportService,
			final Boolean enabled) {
		super(requiredAuthorizations, includePage, name, profileItemRegistry,
				enabled);
		this.courtCaseConditionProfileItemReportService 
			= courtCaseConditionProfileItemReportService;
	}
	
	/** {@inheritDoc} */
	@Override
	public void build(final Map<String, Object> map, final Offender offender,
			final UserAccount userAccount, final Date date) {
		map.put(COUNT_MODEL_KEY, this.courtCaseConditionProfileItemReportService
				.findCourtCaseConditionsByOffenderAndEffectiveDate(offender, date));
	}
	
}
