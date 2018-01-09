package omis.vehicle.web.profile;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import omis.offender.domain.Offender;
import omis.user.domain.UserAccount;
import omis.vehicle.report.VehicleProfileItemReportService;
import omis.web.profile.AbstractProfileItem;
import omis.web.profile.ProfileItem;
import omis.web.profile.ProfileItemRegistry;

/** Profile item for vehicle.
 * @author Ryan Johns
 * @author Trevor Isles
 * @version 0.1.0 (Mar 18, 2016)
 * @since OMIS 3.0 */
public class VehicleProfileItem 
	extends AbstractProfileItem 
	implements ProfileItem {
	private static final long serialVersionUID = 1L;
	private static final String VEHICLE_COUNT_MODEL_KEY 
		= "vehicleAssociationCount";
	
	private final VehicleProfileItemReportService 
		vehicleProfileItemReportService;
	
	/** Constructor.
	 * @param requiredAuthorizations - required authorizations.
	 * @param includePage - include page.
	 * @param name - name.
	 * @param profileItemRegistry - profile item registry.
	 * @param vehicleProfileItemReportService - vehicle profile item report 
	 * service. 
	 * @param enabled - whether enabled */
	public VehicleProfileItem(final Set<String> requiredAuthorizations, 
			final String includePage, 
			final String name,
			final ProfileItemRegistry profileItemRegistry, 
			final VehicleProfileItemReportService 
				vehicleProfileItemReportService,
			final Boolean enabled) {
		super(requiredAuthorizations, includePage, name, profileItemRegistry,
				enabled);
		this.vehicleProfileItemReportService = vehicleProfileItemReportService;
	}
	
	/** {@inheritDoc} */
	@Override
	public void build(final Map<String, Object> map, 
			final Offender offender, final UserAccount userAccount, 
			final Date date) {
		map.put(VEHICLE_COUNT_MODEL_KEY, 
				this.vehicleProfileItemReportService
					.findVehicleCountByOffenderAndDate(offender, date));
	}
}
