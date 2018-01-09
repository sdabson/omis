package omis.substancetest.web.profile;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import omis.offender.domain.Offender;
import omis.substancetest.report.SubstanceTestProfileItemReportService;
import omis.user.domain.UserAccount;
import omis.web.profile.AbstractProfileItem;
import omis.web.profile.ProfileItem;
import omis.web.profile.ProfileItemRegistry;

/** Profile item for substance test.
 * @author Ryan Johns
 * @author Trevor Isles
 * @version 0.1.0 (Mar 17, 2016)
 * @since OMIS 3.0 */
public class SubstanceTestProfileItem 
	extends AbstractProfileItem 
	implements ProfileItem {
	private static final long serialVersionUID = 1L;
	private static final String SUBSTANCE_TEST_LAST_TESTED_MODEL_KEY 
		= "lastSubstanceTest";
	
	private final SubstanceTestProfileItemReportService 
		substanceTestProfileItemReportService;
	
	/** Constructor.
	 * @param requiredAuthorizations - required authorizations.
	 * @param includePage - include page.
	 * @param name - name.
	 * @param profileItemRegistry - profile item registry.
	 * @param SubstanceTestProfileItemReportService - substance test profile 
	 * item report service. 
	 * @param enabled - whether enabled */
	public SubstanceTestProfileItem(final Set<String> requiredAuthorizations, 
			final String includePage, final String name,
			final ProfileItemRegistry profileItemRegistry, 
			final SubstanceTestProfileItemReportService 
				substanceTestProfileItemReportService,
			final Boolean enabled) { 
		super(requiredAuthorizations, includePage, name, profileItemRegistry,
				enabled);
		this.substanceTestProfileItemReportService 
			= substanceTestProfileItemReportService;
	}
	
	/** {@inheritDoc} */
	@Override
	public void build(final Map<String, Object> map, 
			final Offender offender, final UserAccount userAccount, 
			final Date date) { 
		map.put(SUBSTANCE_TEST_LAST_TESTED_MODEL_KEY, 
				this.substanceTestProfileItemReportService
					.findLastSubstanceTestDateByOffender(offender)); 
	}
}
