package omis.identificationnumber.web.profile;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import omis.identificationnumber.report.IdentificationNumberProfileItemReportService;
import omis.offender.domain.Offender;
import omis.user.domain.UserAccount;
import omis.web.profile.AbstractProfileItem;
import omis.web.profile.ProfileItem;
import omis.web.profile.ProfileItemRegistry;

/**
 * Profile item for identification numbers.
 *
 * @author Stephen Abson
 * @author Trevor Isles
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class IdentificationNumberProfileItem
		extends AbstractProfileItem
		implements ProfileItem {

	private static final long serialVersionUID = 1L;
	
	private static final String IDENTIFICATION_NUMBER_COUNT_MODEL_KEY
		= "identificationNumberCount";
	
	private final IdentificationNumberProfileItemReportService
	identificationNumberProfileItemReportService;
	
	/**
	 * Instantiates profile item for identification numbers.
	 * 
	 * @param requiredAuthorizations required authorizations
	 * @param includePage include page
	 * @param name name
	 * @param profileItemRegistry profile item registry
	 * @param identificationNumberProfileItemReportService report service
	 * for identification number profile items
	 * @param enabled whether enabled
	 */
	public IdentificationNumberProfileItem(
			final Set<String> requiredAuthorizations,
			final String includePage,
			final String name,
			final ProfileItemRegistry profileItemRegistry,
			final IdentificationNumberProfileItemReportService
			identificationNumberProfileItemReportService,
			final Boolean enabled) {
		super(requiredAuthorizations, includePage, name, profileItemRegistry,
				enabled);
		this.identificationNumberProfileItemReportService
			= identificationNumberProfileItemReportService;
	}

	/** {@inheritDoc} */
	@Override
	public void build(
			final Map<String, Object> modelMap,
			final Offender offender,
			final UserAccount userAccount,
			final Date date) {
		Integer count = this.identificationNumberProfileItemReportService
				.countForOffenderOnDate(offender, date);
		modelMap.put(IDENTIFICATION_NUMBER_COUNT_MODEL_KEY, count);
	}
}