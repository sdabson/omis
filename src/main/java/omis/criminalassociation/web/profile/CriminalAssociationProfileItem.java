package omis.criminalassociation.web.profile;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import omis.criminalassociation.report.CriminalAssociationProfileItemService;
import omis.offender.domain.Offender;
import omis.user.domain.UserAccount;
import omis.web.profile.AbstractProfileItem;
import omis.web.profile.ProfileItem;
import omis.web.profile.ProfileItemRegistry;

/** Criminal Association profile item.
 * @author Ryan Johns
 * @author Trevor Isles
 * @version 0.1.0 (Mar 11, 2016)
 * @since OMIS 3.0 */
public class CriminalAssociationProfileItem 
	extends AbstractProfileItem implements ProfileItem {
	private static final long serialVersionUID = 1L;
	private static final String CRIMINAL_ASSOCIATES_COUNT_MODEL_KEY 
		= "criminalAssociatesCount";
	
	private final CriminalAssociationProfileItemService 
		criminalAssociationProfileItemService; 
	
	/** Constructor. 
	 * @param requiredAuthorizations - required authorizations.
	 * @param includePage - include page.
	 * @param name - name.
	 * @param profileItemRegistry - profile item registry.
	 * @param ciminalAssociationProfileItemService - criminal association 
	 * profile item service.
	 * @param enabled whether enabled */
	public CriminalAssociationProfileItem(
			final Set<String> requiredAuthorizations,
			final String includePage,
			final String name,
			final ProfileItemRegistry profileItemRegistry,
			final CriminalAssociationProfileItemService 
				criminalAssociationProfileItemService,
			final Boolean enabled) {
		super(requiredAuthorizations, includePage, name, profileItemRegistry,
				enabled);
		this.criminalAssociationProfileItemService 
			= criminalAssociationProfileItemService;
	}
	
	/** {@inhertiDoc} */
	@Override
	public void build(final Map<String, Object> map, final Offender offender,
			final UserAccount userAccount, final Date date) {
		map.put(CRIMINAL_ASSOCIATES_COUNT_MODEL_KEY, 
				this.criminalAssociationProfileItemService
					.findCriminalAssociationCountByOffenderAndDate(offender, 
							date));
	}	
}
