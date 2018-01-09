package omis.offenderphoto.web.profile;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import omis.offender.domain.Offender;
import omis.offenderphoto.report.OffenderPhotoAssociationProfileItemReportService;
import omis.user.domain.UserAccount;
import omis.web.profile.AbstractProfileItem;
import omis.web.profile.ProfileItem;
import omis.web.profile.ProfileItemRegistry;

/** Profile item for offender photo.
 * @author Ryan Johns
 * @author Trevor Isles
 * @version 0.1.0 (Apr 12, 2016)
 * @since OMIS 3.0 */
public class OffenderPhotoProfileItem 
	extends AbstractProfileItem 
	implements ProfileItem {
	private static final long serialVersionUID = 1L;
	private static final String OFFENDER_PHOTO_ASSOCIATION_COUNT_MODEL_KEY
		= "offenderPhotoAssociationCount";
	
	private final OffenderPhotoAssociationProfileItemReportService 
		offenderPhotoAssociationProfileItemReportService;
	
	/** Constructor.
	 * @param requiredAuthorizations - requried authorization.s
	 * @param includePage - include page.
	 * @param name - name.
	 * @param profileItemRegistry - profile item registry.
	 * @param offenderPhotoProfileItemReportService - offender photo profile 
	 * item report service. 
	 * @param enabled - whether enabled*/
	public OffenderPhotoProfileItem(final Set<String> requiredAuthorizations,
			final String includePage, final String name, 
			final ProfileItemRegistry profileItemRegistry,
			final OffenderPhotoAssociationProfileItemReportService 
				offenderPhotoAssociationProfileItemReportService,
			final Boolean enabled) {
		super(requiredAuthorizations, includePage, name, profileItemRegistry,
				enabled);
		this.offenderPhotoAssociationProfileItemReportService 
			= offenderPhotoAssociationProfileItemReportService;
	}
	
	/** {@inheritDoc} */
	@Override
	public void build(final Map<String, Object> map, final Offender offender,
			final UserAccount userAccount, final Date date) {
		map.put(OFFENDER_PHOTO_ASSOCIATION_COUNT_MODEL_KEY, 
				this.offenderPhotoAssociationProfileItemReportService
				.findOffenderPhotoAssociationCountByOffender(offender));
	}
}
