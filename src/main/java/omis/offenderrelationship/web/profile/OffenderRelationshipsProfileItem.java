package omis.offenderrelationship.web.profile;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import omis.offender.domain.Offender;
import omis.relationship.report.RelationshipProfileItemReportService;
import omis.user.domain.UserAccount;
import omis.web.profile.AbstractProfileItem;
import omis.web.profile.ProfileItemRegistry;

/**
 * Offender relationships profile item.
 * 
 * @author Joel Norris
 * @author Trevor Isles
 * @author Annie Jacques
 * @version 0.1.0 (Sep 7, 2017)
 * @since OMIS 3.0
 */
public class OffenderRelationshipsProfileItem 
	extends AbstractProfileItem {
	
	private static final long serialVersionUID = 1L;
	
	/* Services. */
	
	private final RelationshipProfileItemReportService 
		relationshipProfileItemReportService;

	/* Model keys. */
	
	private static final String RELATIONSHIPS_COUNT_MODEL_KEY 
		="relationshipsCount";
	
	/* Constructors. */
	
	/**
	 * Instantiates a profile item for offender relationships.
	 * 
	 * @param relationshipProfileItemReportService relationships profile item
	 * report service
	 * @param requiredAuthorizations required authorizations.
	 * @param includePage include page
	 * @param name - name.
	 * @param profileItemRegistry profile item registry
	 * @param enabled - whether enabled
	 */
	public OffenderRelationshipsProfileItem(
			final RelationshipProfileItemReportService 
				relationshipProfileItemReportService,
			final Set<String> requiredAuthorizations,
			final String includePage, 
			final String name,
			final ProfileItemRegistry profileItemRegistry,
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
		map.put(RELATIONSHIPS_COUNT_MODEL_KEY,
				this.relationshipProfileItemReportService
				.findRelationshipCountByOffender(offender));
	}
}