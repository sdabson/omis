package omis.program.web.profile;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import omis.offender.domain.Offender;
import omis.program.report.ProgramPlacementProfileItemService;
import omis.user.domain.UserAccount;
import omis.web.profile.AbstractProfileItem;
import omis.web.profile.ProfileItem;
import omis.web.profile.ProfileItemRegistry;

/**
 * Profile item for program placement.
 *
 * @author Josh Divine
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class ProgramPlacementProfileItem 
	extends AbstractProfileItem 
	implements ProfileItem {

	private static final long serialVersionUID = 1L;
	
	private static final String PROGRAM_PLACEMENT_EXISTS_MODEL_KEY 
		= "programPlacementExists";
	
	private final ProgramPlacementProfileItemService 
		programPlacementProfileItemService;
	
	/**
	 * Constructor.
	 * 
	 * @param requiredAuthorizations required authorizations
	 * @param includePage include page
	 * @param name name
	 * @param profileItemRegistry profile item registry
	 * @param offenseTermProfileItemService offense term profile item service
	 * @param enabled whether enabled
	 */
	public ProgramPlacementProfileItem(final Set<String> requiredAuthorizations, 
			final String includePage, final String name,
			final ProfileItemRegistry profileItemRegistry,
			final ProgramPlacementProfileItemService 
				programPlacementProfileItemService,
			final Boolean enabled) {
		super(requiredAuthorizations, includePage, name, profileItemRegistry,
				enabled);
		this.programPlacementProfileItemService 
			= programPlacementProfileItemService;
	}

	/** {@inheritDoc} */
	@Override
	public void build(final Map<String, Object> modelMap, 
			final Offender offender, final UserAccount userAccount, 
			final Date date) {
		modelMap.put(PROGRAM_PLACEMENT_EXISTS_MODEL_KEY, 
				this.programPlacementProfileItemService
				.findProgramPlacementExistenceByOffenderOnDate(offender, date));
	}
}
