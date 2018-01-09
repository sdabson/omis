package omis.offenderdocument.profile;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import omis.offender.domain.Offender;
import omis.offenderdocument.service.DocumentProfileItemService;
import omis.user.domain.UserAccount;
import omis.web.profile.AbstractProfileItem;
import omis.web.profile.ProfileItem;
import omis.web.profile.ProfileItemRegistry;

/** Document profile item.
 * @author Ryan Johns
 * @author Trevor Isles
 * @version 0.1.0 (Mar 15, 2016)
 * @since OMIS 3.0 */
public class DocumentProfileItem 
	extends AbstractProfileItem 
	implements ProfileItem {
	private static final long serialVersionUID = 1L;
	private static final String DOCUMENT_COUNT_MODEL_KEY 
		= "documentCount";

	private final DocumentProfileItemService documentProfileItemService;
	
	/** Constructor.
	 * @param requiredAuthorizations - required authorizations.
	 * @param includePage - include page.
	 * @param name - name.
	 * @param profileItemRegistry - profileItemRegistry.
	 * @param DocumentProfileItemService - documentProfileItemService. 
	 * @param enabled - whether enabled */
	public DocumentProfileItem(final Set<String> requiredAuthorizations, 
			final String includePage, 
			final String name,
			final ProfileItemRegistry profileItemRegistry, 
			final DocumentProfileItemService documentProfileItemService,
			final Boolean enabled) {
		super(requiredAuthorizations, includePage, name, profileItemRegistry,
				enabled);
		this.documentProfileItemService = documentProfileItemService;
	}
	
	/** {@inheritDoc} */
	@Override
	public void build(final Map<String, Object> map, 
			final Offender offender, final UserAccount userAccount, 
			final Date date) {
		map.put(DOCUMENT_COUNT_MODEL_KEY, this.documentProfileItemService
				.findDocumentCountByOffender(offender));
	}
}
