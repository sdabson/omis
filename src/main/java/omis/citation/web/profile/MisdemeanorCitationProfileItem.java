package omis.citation.web.profile;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import omis.citation.report.MisdemeanorCitationProfileItemReportService;
import omis.offender.domain.Offender;
import omis.user.domain.UserAccount;
import omis.web.profile.AbstractProfileItem;
import omis.web.profile.ProfileItem;
import omis.web.profile.ProfileItemRegistry;

/**
 * Misdemeanor citation profile item.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (Aug 16, 2016)
 * @since OMIS 3.0
 */

public class MisdemeanorCitationProfileItem extends AbstractProfileItem 
	implements ProfileItem {
	
	private static final long serialVersionUID = 1L;

	private static final String COUNT_MODEL_KEY = "citationCount";
	
	private final MisdemeanorCitationProfileItemReportService
		misdemeanorCitationProfileItemReportService;
	
	/** Constructor. 
	 * @param requiredAuthorizations - required authorizations.
	 * @param includePage - include page.
	 * @param name - name.
	 * @param profileItemRegistry - profile item registry.
	 * @param misdemeanorCitationProfileItemReportService - misdemeanor citation 
	 * profile item report service. 
	 * @param enabled - whether enabled */
	public MisdemeanorCitationProfileItem(
			final Set<String> requiredAuthorizations, 
			final String includePage,
			final String name,
			final ProfileItemRegistry profileItemRegistry,
			final MisdemeanorCitationProfileItemReportService 
				misdemeanorCitationProfileItemReportService,
			final Boolean enabled) {
		super(requiredAuthorizations, includePage, name, profileItemRegistry,
				enabled);
		this.misdemeanorCitationProfileItemReportService 
			= misdemeanorCitationProfileItemReportService;
	}
	
	/** {@inheritDoc} */
	@Override
	public void build(final Map<String, Object> map, final Offender offender,
			final UserAccount userAccount, final Date date) {
		Integer count = this.misdemeanorCitationProfileItemReportService
				.findMisdemeanorCitationCountByCitations(offender);
		map.put(COUNT_MODEL_KEY, count);
	}
}