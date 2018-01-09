package omis.presentenceinvestigation.web.profile;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import omis.offender.domain.Offender;
import omis.presentenceinvestigation.report.PresentenceInvestigationRequestProfileItemReportService;
import omis.user.domain.UserAccount;
import omis.web.profile.AbstractProfileItem;
import omis.web.profile.ProfileItemRegistry;

/**
 * PresentenceInvestigationRequestProfileItem.java
 * 
 *@author Annie Jacques
 *@author Trevor Isles 
 *@version 0.1.0 (Dec 12, 2016)
 *@since OMIS 3.0
 *
 */
public class PresentenceInvestigationRequestProfileItem extends AbstractProfileItem {
	
	private static final long serialVersionUID = 1L;
	
	private static final String COUNT_MODEL_KEY =
			"presentenceInvestigationRequestCount";
	
	private final PresentenceInvestigationRequestProfileItemReportService
		presentenceInvestigationRequestProfileItemReportService;
	
	
	/**
	 * @param requiredAuthorizations
	 * @param includePage
	 * @param name
	 * @param profileItemRegistry
	 * @param presentenceInvestifationRequestProfileItemReportService
	 * @param enabled whether enabled
	 */
	public PresentenceInvestigationRequestProfileItem(
			final Set<String> requiredAuthorizations, final String includePage,
			final String name, ProfileItemRegistry profileItemRegistry,
			final PresentenceInvestigationRequestProfileItemReportService
				presentenceInvestigationRequestProfileItemReportService,
			final Boolean enabled) {
		super(requiredAuthorizations, includePage, name, profileItemRegistry,
				enabled);
		this.presentenceInvestigationRequestProfileItemReportService =
				presentenceInvestigationRequestProfileItemReportService;
	}

	/**{@inheritDoc} */
	@Override
	public void build(final Map<String, Object> modelMap,
			final Offender offender, final UserAccount userAccount,
			final Date date) {
		Integer count =
				this.presentenceInvestigationRequestProfileItemReportService
				.findPresentenceInvestigationRequestCountByOffender(offender);
		modelMap.put(COUNT_MODEL_KEY, count);
	}

}
