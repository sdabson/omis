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
package omis.probationparole.web.profile;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import omis.offender.domain.Offender;
import omis.probationparole.report.ProbationParoleDocumentProfileItemService;
import omis.user.domain.UserAccount;
import omis.web.profile.AbstractProfileItem;
import omis.web.profile.ProfileItem;
import omis.web.profile.ProfileItemRegistry;

/**
 * Probation Parole Document Profile Item.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Dec 31, 2018)
 *@since OMIS 3.0
 *
 */
public class ProbationParoleDocumentProfileItem extends AbstractProfileItem
		implements ProfileItem {
	
	private static final long serialVersionUID = 1L;
	
	private static final String P_AND_P_DOCUMENT_COUNT_MODEL_KEY =
			"probationParoleDocumentCount";
	
	private final ProbationParoleDocumentProfileItemService
			probationParoleDocumentProfileItemService;
	
	/**
	 * Constructor for Probation Parole Document Profile Item.
	 * 
	 * @param requiredAuthorizations Required Authorizations
	 * @param includePage Include Page
	 * @param name Name
	 * @param profileItemRegistry Profile Item Registry
	 * @param enabled Enabled
	 * @param probationParoleDocumentProfileItemService Probation Parole
	 * Document Profile Item Service
	 */
	public ProbationParoleDocumentProfileItem(
			final Set<String> requiredAuthorizations,
			final String includePage, final String name,
			final ProfileItemRegistry profileItemRegistry,
			final Boolean enabled,
			final ProbationParoleDocumentProfileItemService
			probationParoleDocumentProfileItemService) {
		super(requiredAuthorizations, includePage, name,
				profileItemRegistry, enabled);
		this.probationParoleDocumentProfileItemService =
				probationParoleDocumentProfileItemService;
	}

	/** {@inheritDoc} */
	@Override
	public void build(final Map<String, Object> map,
			final Offender offender,
			final UserAccount userAccount, final Date date) {
		map.put(P_AND_P_DOCUMENT_COUNT_MODEL_KEY,
				this.probationParoleDocumentProfileItemService
				.findProbationParoleDocumentAssociationCountByOffender(
						offender));
	}

}
