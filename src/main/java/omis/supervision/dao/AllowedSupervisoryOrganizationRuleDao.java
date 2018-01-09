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
package omis.supervision.dao;

import omis.dao.GenericDao;
import omis.supervision.domain.AllowedSupervisoryOrganizationRule;
import omis.supervision.domain.CorrectionalStatus;
import omis.supervision.domain.SupervisoryOrganization;

/**
 * Data access object for rule allowing supervisory organizations for
 * correctional statuses. 
 *
 * @author Stephen Abson
 * @version 0.0.1 (Dec 4, 2014)
 * @since OMIS 3.0
 */
public interface AllowedSupervisoryOrganizationRuleDao
		extends GenericDao<AllowedSupervisoryOrganizationRule> {

	/**
	 * Returns rule to allow supervisory organization for correctional
	 * status if such a rule exists.
	 * 
	 * <p>If such a rule does not exist, returns {@code null}.
	 * 
	 * @param correctionalStatus correctional status
	 * @param supervisoryOrganization supervisory organization
	 * @return rule to allow supervisory organization for correctional status
	 * if such a rule exists; {@code null} otherwise
	 */
	AllowedSupervisoryOrganizationRule find(
			CorrectionalStatus correctionalStatus,
			SupervisoryOrganization supervisoryOrganization);
}