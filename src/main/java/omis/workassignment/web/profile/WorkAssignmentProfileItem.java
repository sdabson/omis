package omis.workassignment.web.profile;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import omis.offender.domain.Offender;
import omis.user.domain.UserAccount;
import omis.web.profile.AbstractProfileItem;
import omis.web.profile.ProfileItem;
import omis.web.profile.ProfileItemRegistry;
import omis.workassignment.report.WorkAssignmentProfileItemReportService;

/**
 * Profile item for work assignments.
 *
 * @author Stephen Abson
 * @author Trevor Isles
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class WorkAssignmentProfileItem
		extends AbstractProfileItem
		implements ProfileItem {
	
	private static final long serialVersionUID = 1L;
	
	/* Report services. */
	
	private final WorkAssignmentProfileItemReportService
	workAssignmentProfileItemReportService;

	/* Model keys. */
	
	private static final String WORK_ASSIGNMENT_COUNT_MODEL_KEY
		= "workAssignmentCount";
	
	/* Constructors. */
	
	/**
	 * Instantiates profile item for work assignments.
	 * 
	 * @param requiredAuthorizations required authorizations
	 * @param includePage include page
	 * @param name name
	 * @param profileItemRegistry profile item registry
	 * @param workAssignmentProfileItemReportService service for work
	 * assignment profile items
	 * @param enabled whether enabled
	 */
	public WorkAssignmentProfileItem(
			final Set<String> requiredAuthorizations,
			final String includePage,
			final String name,
			final ProfileItemRegistry profileItemRegistry,
			final WorkAssignmentProfileItemReportService
				workAssignmentProfileItemReportService,
			final Boolean enabled) {
		super(requiredAuthorizations, includePage, name, profileItemRegistry,
				enabled);
		this.workAssignmentProfileItemReportService
			= workAssignmentProfileItemReportService;
	}

	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public void build(
			final Map<String, Object> modelMap,
			final Offender offender,
			final UserAccount userAccount,
			final Date date) {
		Integer count = this.workAssignmentProfileItemReportService
				.findWorkAssignmentCountForOffenderOnDate(offender, date);
		modelMap.put(WORK_ASSIGNMENT_COUNT_MODEL_KEY, count);
	}
}