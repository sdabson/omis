/*
 * OMIS - Offender Management Information System
 * Copyright (C) 2011 - 2017 State of Montana
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package omis.supervision.web.profile;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import omis.offender.domain.Offender;
import omis.supervision.report.PlacementTermProfileItemReportService;
import omis.user.domain.UserAccount;
import omis.web.profile.AbstractProfileItem;
import omis.web.profile.ProfileItem;
import omis.web.profile.ProfileItemRegistry;

/** Profile item for placement term.
 * @author Ryan JOhns
 * @author Stephen Abson
 * @version 0.1.0 (Mar 17, 2016)
 * @since OMIS 3.0 */
public class PlacementTermProfileItem 
	extends AbstractProfileItem 
	implements ProfileItem {
	private static final long serialVersionUID = 1L;
	private static final String PLACEMENT_TERM_COUNT_MODEL_KEY 
		= "placementTermExists";
	
	private final PlacementTermProfileItemReportService 
		placementTermProfileItemReportService;
	
	/**
	 * Constructor.
	 * 
	 * @param requiredAuthorizations - required authorizations.
	 * @param includePage - include page.
	 * @param name - name.
	 * @param pofileItemRegistry - profile item registry.
	 * @param placementTermProfileItemReportService - placement term 
	 * profile item report service.
	 * @param enabled whether enabled
	 */
	public PlacementTermProfileItem(final Set<String> requiredAuthorizations, 
			final String includePage, 
			final String name,
			final ProfileItemRegistry profileItemRegistry, 
			final PlacementTermProfileItemReportService 
				placementTermProfileItemReportService,
			final Boolean enabled) { 
		super(requiredAuthorizations, includePage, name, profileItemRegistry,
				enabled);
		this.placementTermProfileItemReportService
			= placementTermProfileItemReportService;
	}
	
	/** {@inhertiDoc} */
	@Override
	public void build(final Map<String, Object> map, 
			final Offender offender, final UserAccount userAccount, 
			final Date date) {
		map.put(PLACEMENT_TERM_COUNT_MODEL_KEY, 
				this.placementTermProfileItemReportService
				.findPlacementTermExistsByOffenderOnDate(offender, date));
	}
}
