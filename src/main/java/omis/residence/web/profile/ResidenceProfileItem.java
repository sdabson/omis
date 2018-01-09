package omis.residence.web.profile;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import omis.offender.domain.Offender;
import omis.residence.report.ResidenceProfileItemReportService;
import omis.user.domain.UserAccount;
import omis.web.profile.AbstractProfileItem;
import omis.web.profile.ProfileItem;
import omis.web.profile.ProfileItemRegistry;

/** Profile item for residence.
 * @author Ryan Johns
 * @author Trevor Isles
 * @version 0.1.0 (Mar 17, 2016)
 * @since OMIS 3.0 */
public class ResidenceProfileItem 
	extends AbstractProfileItem 
	implements ProfileItem {
	private static final long serialVersionUID = 1L;
	private static final String RESIDENCE_COUNT_MODEL_KEY 
		= "residenceCount";
	
	private final ResidenceProfileItemReportService 
		residenceProfileItemReportService;
	
	/** Constructor.
	 * @param requiredAuthorizations - required authorizations.
	 * @param includePage - include page.
	 * @param name - name.
	 * @param profileItemRegistry - profile item registry.
	 * @param residenceProfileItemReportService - residence profile item 
	 * report service. 
	 * @param enabled - whether enabled */
	public ResidenceProfileItem(final Set<String> requiredAuthorizations, 
			final String includePage, 
			final String name,
			final ProfileItemRegistry profileItemRegistry, 
			final ResidenceProfileItemReportService 
				residenceProfileItemReportService,
			final Boolean enabled) {
		super(requiredAuthorizations, includePage, name, profileItemRegistry,
				enabled);
		this.residenceProfileItemReportService 
			= residenceProfileItemReportService;
	}
	
	/** {@inheritDoc} */
	@Override
	public void build(final Map<String, Object> map, 
			final Offender offender, final UserAccount userAccount, 
			final Date date) {
		map.put(RESIDENCE_COUNT_MODEL_KEY, 
				this.residenceProfileItemReportService
					.findResidenceCountByOffenderAndDate(offender, date));
	}
}
