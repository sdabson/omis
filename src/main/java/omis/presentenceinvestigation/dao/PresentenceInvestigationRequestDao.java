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

import java.util.Date;

import omis.dao.GenericDao;
import omis.person.domain.Person;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;

/**
 * Presentence investigation request data access object.
 * 
 * @author Joel Norris
 * @author Annie Wahl
 * @author Josh Divine
 * @version 0.1.2 (Oct 25, 2018)
 * @since OMIS 3.0
 */
public interface PresentenceInvestigationRequestDao 
	extends GenericDao<PresentenceInvestigationRequest>{

	/**
	 * Returns the presentence investigation request with the specified person
	 * 
	 * @param person person
	 * @param requestDate request date
	 * @return presentence investigation request
	 */
	PresentenceInvestigationRequest find(Person person, Date requestDate);
	
	/** 
	 * Returns the presentence investigation request with the specified person,
	 * excluding the specified presentence investigation request.
	 * 
	 * @param pressentenceInvestigationRequest presentence investigation request
	 * @param person person
	 * @param requestDate request date
	 * @return presentence investigation request
	 */
	PresentenceInvestigationRequest findExcluding(
			PresentenceInvestigationRequest pressentenceInvestigationRequest,
			Person person, Date requestDate);
}