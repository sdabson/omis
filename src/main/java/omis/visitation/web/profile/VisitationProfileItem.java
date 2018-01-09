package omis.visitation.web.profile;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import omis.offender.domain.Offender;
import omis.user.domain.UserAccount;
import omis.visitation.report.VisitationProfileItemReportService;
import omis.web.profile.AbstractProfileItem;
import omis.web.profile.ProfileItem;
import omis.web.profile.ProfileItemRegistry;

/** Profile item for visitation.
 * @author Ryan Johns
 * @author Trevor Isles
 * @version 0.1.0 (Mar 21, 2016)
 * @since OMIS 3.0 */
public class VisitationProfileItem 
	extends AbstractProfileItem 
	implements ProfileItem {
	private static final long serialVersionUID = 1L;
	private static final String VISITOR_COUNT_MODEL_KEY = "visitorCount";
	private static final String VISIT_COUNT_MODEL_KEY = "visitCount";
	
	private final VisitationProfileItemReportService 
		visitationProfileItemReportService;
	
	/** Constructor.
	 * @param requiredAuthorizations - required authorizations.
	 * @param includePage - include page.
	 * @param name - name.
	 * @param profileItemRegistry - profile item registry.
	 * @param visitationProfileItemReportService - visitation profile item 
	 * report service.
	 * @param enabled - whether enabled */
	public VisitationProfileItem(final Set<String> requiredAuthorizations, 
			final String includePage, 
			final String name,
			final ProfileItemRegistry profileItemRegistry, 
			final VisitationProfileItemReportService 
				visitationProfileItemReportService,
			final Boolean enabled) { 
		super(requiredAuthorizations, includePage, name, profileItemRegistry,
				enabled);
		this.visitationProfileItemReportService 
			= visitationProfileItemReportService; 
	}
	
	/** {@inheritDoc} */
	@Override
	public void build(final Map<String, Object> map, final Offender offender,
			final UserAccount userAccount, final Date date) {
		map.put(VISITOR_COUNT_MODEL_KEY, this
				.visitationProfileItemReportService
				.findVisitorCountByOffenderAndDate(offender, date));
		map.put(VISIT_COUNT_MODEL_KEY, this.visitationProfileItemReportService
				.findVisitCountByOffender(offender));
	}
}
