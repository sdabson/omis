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
package omis.courtdocument.web.profile;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import omis.courtdocument.report.CourtDocumentAssociationProfileItemReportService;
import omis.offender.domain.Offender;
import omis.user.domain.UserAccount;
import omis.web.profile.AbstractProfileItem;
import omis.web.profile.ProfileItem;
import omis.web.profile.ProfileItemRegistry;

/** 
 * Court document association profile item.
 * 
 * @author Ryan Johns
 * @author Josh Divine
 * @version 0.1.1 (Aug 9, 2018)
 * @since OMIS 3.0
 */
public class CourtDocumentAssociationProfileItem
	extends AbstractProfileItem implements ProfileItem {
	
	private static final long serialVersionUID = 1L;
	
	private static final String COUNT_MODEL_KEY = "courtDocumentCount";
	
	private final CourtDocumentAssociationProfileItemReportService 
		courtDocumentAssociationProfileItemReportService;
	
	/** 
	 * Constructor.
	 * 
	 * @param requiredAuthorizations required authorizations
	 * @param includePage include page
	 * @param name name
	 * @param profileItemRegistry profile item registry
	 * @param courtDocumentAssociationProfileItemReportService court document
	 * association profile item report service. 
	 * @param enabled whether enabled
	 */
	public CourtDocumentAssociationProfileItem(
			final Set<String> requiredAuthorizations,
			final String includePage, 
			final String name,
			final ProfileItemRegistry profileItemRegistry,
			final CourtDocumentAssociationProfileItemReportService 
				courtDocumentAssociationProfileItemReportService,
			final Boolean enabled) {
		super(requiredAuthorizations, includePage, name, profileItemRegistry,
				enabled);
		this.courtDocumentAssociationProfileItemReportService 
			= courtDocumentAssociationProfileItemReportService;
	}
	
	/** {@inheritDoc} */
	public void build(final Map<String, Object> map, final Offender offender, 
			final UserAccount userAccount, final Date date) {
		map.put(COUNT_MODEL_KEY, 
				this.courtDocumentAssociationProfileItemReportService
				.findCourtDocumentAssociationCountByOffender(offender));
	}
}