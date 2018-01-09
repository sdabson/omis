package omis.dna.web.profile;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import omis.dna.report.DnaProfileItemReportService;
import omis.offender.domain.Offender;
import omis.user.domain.UserAccount;
import omis.web.profile.AbstractProfileItem;
import omis.web.profile.ProfileItem;
import omis.web.profile.ProfileItemRegistry;

/** Profile item for dna.
 * @author Ryan Johns
 * @author Trevor Isles
 * @version 0.1.0 (Mar 15, 2016)
 * @since OMIS 3.0 */
public class DnaProfileItem 
	extends AbstractProfileItem 
	implements ProfileItem {
	private static final long serialVersionUID = 1L;
	
	private static final String DNA_COUNT_MODEL_KEY = "dnaCount";

	private final DnaProfileItemReportService dnaProfileItemReportService;
	
	/** Constructor.
	 * @param requiredAuthorizations - required authorizations.
	 * @param includePage - include page.
	 * @param name - name.
	 * @param profileItemRegistry - profile item registry.
	 * @param dnaProfileItemService - dna profile item service. 
	 * @param enabled - whether enabled */
	public DnaProfileItem(final Set<String> requiredAuthorizations, 
			final String includePage,
			final String name,
			final ProfileItemRegistry profileItemRegistry, 
			final DnaProfileItemReportService 
				dnaProfileItemReportService,
			final Boolean enabled) {
		super(requiredAuthorizations, includePage, name, profileItemRegistry,
				enabled);
		this.dnaProfileItemReportService = dnaProfileItemReportService;
	}
	
	/** {@inheritDoc} */
	@Override
	public void build(final Map<String, Object> map, 
			final Offender offender, final UserAccount userAccount, 
			final Date date) {
		map.put(DNA_COUNT_MODEL_KEY, this.dnaProfileItemReportService
				.findDnaCountByOffender(offender));
	}

}
