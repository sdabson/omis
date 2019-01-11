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
package omis.presentenceinvestigation.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.docket.domain.Docket;

/**
 * Presentence investigation docket association.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Oct 24, 2018)
 * @since OMIS 3.0
 */
public interface PresentenceInvestigationDocketAssociation
		extends Creatable, Updatable {

	/** 
	 * Returns the id.
	 * 
	 * @return id
	 */
	public Long getId();
	
	/** 
	 * Sets the id.
	 * 
	 * @param id id
	 */
	public void setId(Long id);
	
	/**
	 * Returns the docket.
	 * 
	 * @return docket
	 */
	public Docket getDocket();
	
	/**
	 * Sets the docket.
	 * 
	 * @param docket docket
	 */
	public void setDocket(Docket docket);
	
	/**
	 * Returns the presentence investigation request.
	 * 
	 * @return presentence investigation request
	 */
	public PresentenceInvestigationRequest getPresentenceInvestigationRequest();
	
	/**
	 * Sets the presentence investigation request.
	 * 
	 * @param presentenceInvestigationRequest presentence investigation request
	 */
	public void setPresentenceInvestigationRequest(
			PresentenceInvestigationRequest presentenceInvestigationRequest);
}