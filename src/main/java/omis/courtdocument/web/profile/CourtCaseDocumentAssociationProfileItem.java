package omis.courtdocument.web.profile;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import omis.courtdocument.report.CourtCaseDocumentAssociationProfileItemReportService;
import omis.offender.domain.Offender;
import omis.user.domain.UserAccount;
import omis.web.profile.AbstractProfileItem;
import omis.web.profile.ProfileItem;
import omis.web.profile.ProfileItemRegistry;

/** Court Case document association controller.
 * @author Ryan Johns
 * @version 0.1.0 (Dec 5, 2017)
 * @since OMIS 3.0 */
public class CourtCaseDocumentAssociationProfileItem
	extends AbstractProfileItem 
	implements ProfileItem {
	private static final long serialVersionUID = 1L;
	private static final String COUNT_MODEL_KEY = "courtDocumentCount";
	private final CourtCaseDocumentAssociationProfileItemReportService 
		courtCaseDocumentAssociationProfileItemReportService;
	
	/** Constructor.
	 * @param requiredAuthorizations - required authorizations.
	 * @param includePage - include page.
	 * @param name - name.
	 * @param profileItemRegistry - profile item registry.
	 * @param courtCaseDocumentAssociationProfileItemReportService - court case document
	 * association profile item report service. 
	 * @param enabled whether enabled */
	public CourtCaseDocumentAssociationProfileItem(
			final Set<String> requiredAuthorizations,
			final String includePage, 
			final String name,
			final ProfileItemRegistry profileItemRegistry,
			final CourtCaseDocumentAssociationProfileItemReportService 
				courtCaseDocumentAssociationProfileItemReportService,
			final Boolean enabled) {
		super(requiredAuthorizations, includePage, name, profileItemRegistry,
				enabled);
		this.courtCaseDocumentAssociationProfileItemReportService 
			= courtCaseDocumentAssociationProfileItemReportService;
	}
	
	/** {@inheritDoc} */
	public void build(final Map<String, Object> map, final Offender offender, 
			final UserAccount userAccount, final Date date) {
		map.put(COUNT_MODEL_KEY, 
				this.courtCaseDocumentAssociationProfileItemReportService
				.findCourtCaseDocumentCountByOffender(offender));
	}
}
