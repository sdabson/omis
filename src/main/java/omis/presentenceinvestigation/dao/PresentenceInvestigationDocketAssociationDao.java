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
package omis.presentenceinvestigation.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.docket.domain.Docket;
import omis.presentenceinvestigation.domain.PresentenceInvestigationDocketAssociation;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;

/**
 * Presentence investigation docket association data access object.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Oct 25, 2018)
 * @since OMIS 3.0
 */
public interface PresentenceInvestigationDocketAssociationDao 
		extends GenericDao<PresentenceInvestigationDocketAssociation> {

	/**
	 * Returns the presentence investigation docket association with the 
	 * specified presentence investigation request and docket.
	 * 
	 * @param presentenceInvestigationRequest presentence investigation request
	 * @param docket docket
	 * @return presentence investigation docket association
	 */
	PresentenceInvestigationDocketAssociation find(
			PresentenceInvestigationRequest presentenceInvestigationRequest,
			Docket docket);

	/**
	 * Returns a list of presentence investigation docket associations for the 
	 * specified presentence investigation request.
	 * 
	 * @param presentenceInvestigationRequest presentence investigation request
	 * @return list of presentence investigation docket associations
	 */
	List<PresentenceInvestigationDocketAssociation> 
			findByPresentenceInvestigationRequest(
					PresentenceInvestigationRequest 
						presentenceInvestigationRequest);
}