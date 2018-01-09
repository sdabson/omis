package omis.relationship.web.profile;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import omis.offender.domain.Offender;
import omis.relationship.report.RelationshipProfileItemReportService;
import omis.user.domain.UserAccount;
import omis.web.profile.AbstractProfileItem;
import omis.web.profile.ProfileItem;
import omis.web.profile.ProfileItemRegistry;

/** Profile item for relationship.
 * @author Ryan Johns
 * @author Trevor Isles
 * @version 0.1.0 (Mar 17, 2016)
 * @since OMIS 3.0 */
public class RelationshipProfileItem 
	extends AbstractProfileItem 
	implements ProfileItem {
	private static final long serialVersionUID = 1L;
	private static final String RELATIONSHIP_COUNT_MODEL_KEY 
		= "relationshipCount";
	
	private final RelationshipProfileItemReportService 
		relationshipProfileItemReportService;

	/** Constructor.
	 * @param requiredAuthorizations - required authorizations.
	 * @param includePage - include page.
	 * @param name - name.
	 * @param profileItemRegistry - profile item registry.
	 * @param relationshipProfileItemReportService - relationship profile item 
	 * report service. 
	 * @param enabled - whether enabled */
	public RelationshipProfileItem(final Set<String> requiredAuthorizations, 
			final String includePage, 
			final String name,
			final ProfileItemRegistry profileItemRegistry, 
			final RelationshipProfileItemReportService 
				relationshipProfileItemReportService,
			final Boolean enabled) {
		super(requiredAuthorizations, includePage, name, profileItemRegistry,
				enabled);
		this.relationshipProfileItemReportService 
			= relationshipProfileItemReportService;
	}
	
	/** {@inheritDoc} */
	@Override
	public void build(final Map<String, Object> map, 
			final Offender offender, final UserAccount userAccount, 
			final Date date) {
		map.put(RELATIONSHIP_COUNT_MODEL_KEY, 
				this.relationshipProfileItemReportService
				.findRelationshipCountByOffender(offender));
	}
}
