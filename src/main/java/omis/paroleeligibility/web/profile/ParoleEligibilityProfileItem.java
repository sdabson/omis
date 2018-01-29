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
package omis.paroleeligibility.web.profile;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import omis.offender.domain.Offender;
import omis.paroleeligibility.report.ParoleEligibilityProfileItemReportService;
import omis.user.domain.UserAccount;
import omis.web.profile.AbstractProfileItem;
import omis.web.profile.ProfileItem;
import omis.web.profile.ProfileItemRegistry;

/**
 * Parole eligibility profile item.
 *
 * @author Trevor Isles
 * @version 0.1.0 (Jan 9, 2018)
 * @since OMIS 3.0
 */
public class ParoleEligibilityProfileItem extends AbstractProfileItem
	implements ProfileItem {

	private static final long serialVersionUID = 1L;
	
	private static final String COUNT_MODEL_KEY = "paroleEligibilityCount";
	
	private final ParoleEligibilityProfileItemReportService 
		paroleEligibilityProfileItemReportService;
	
	/** Constructor.
	 * @param requiredAuthorizations - required authorizations
	 * @param includePage - include page
	 * @param name - name
	 * @param profileItemRegistry - profile item registry
	 * @param paroleEligibilityProfileItemReportService - parole eligibility
	 * profile item report service
	 * @param enabled - whether enabled
	 */
	public ParoleEligibilityProfileItem(
			final Set<String> requiredAuthorizations,
			final String includePage,
			final String name,
			final ProfileItemRegistry profileItemRegistry,
			final ParoleEligibilityProfileItemReportService
				paroleEligibilityProfileItemReportService,
			final Boolean enabled) {
		super(requiredAuthorizations, includePage, name, profileItemRegistry,
				enabled);
		this.paroleEligibilityProfileItemReportService 
			= paroleEligibilityProfileItemReportService;
	}
	
	/** {@inheritDoc} */
	@Override
	public void build(final Map<String, Object> map, final Offender offender,
			final UserAccount userAccount, final Date date) {
		Long count = this.paroleEligibilityProfileItemReportService
				.findParoleEligibilityCountByEligibilities(offender);
		map.put(COUNT_MODEL_KEY, count);
	}
	
}
