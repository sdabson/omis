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

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.person.domain.Person;
import omis.user.domain.UserAccount;

/** 
 * Presentence Investigation Request.
 * 
 * @author Ryan Johns
 * @author Annie Wahl
 * @author Josh Divine
 * @version 0.1.4 (Oct 24, 2018)
 * @since OMIS 3.0
 */
public interface PresentenceInvestigationRequest 
	extends Creatable, Updatable {
	
	/** 
	 * Gets the id.
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
	 * Gets the request date.
	 *  
 	 * @return request date 
 	 */
	public Date getRequestDate();
	
	/** 
	 * Sets the request date.
	 * 
	 * @param requestDate request date
	 */
	public void setRequestDate(Date requestDate);
	
	/** 
	 * Gets the expected completion date.
	 * 
	 * @return expected completion date
	 */
	public Date getExpectedCompletionDate();
	
	/** 
	 * Sets the expected completion date.
	 * 
	 * @param expectedCompletionDate expected completion date
	 */
	public void setExpectedCompletionDate(Date expectedCompletionDate);

	/** 
	 * Gets the assigned user.
	 * 
	 * @return assigned user
	 */
	public UserAccount getAssignedUser();
	
	/** 
	 * Sets the assigned user.
	 * 
	 * @param assignedUser assigned user
	 */
	public void setAssignedUser(UserAccount assignedUser);
	
	/** 
	 * Gets the sentence date.
	 * 
	 * @return sentence date
	 */
	public Date getSentenceDate();
	
	/**
	 * Sets the sentence date.
	 * 
	 * @param sentenceDate sentence date
	 */
	public void setSentenceDate(Date sentenceDate);
	
	/** 
	 * Gets the submission date.
	 * 
	 * @return submission date
	 */
	public Date getSubmissionDate();
	
	/** 
	 * Sets the submission date.
	 * 
	 * @param submissionDate submission date
	 */
	public void setSubmissionDate(Date submissionDate);
	
	/**
	 * Returns the category for the presentence investigation request.
	 * 
	 * @return presentence investigation request category
	 */
	public PresentenceInvestigationCategory getCategory();
	
	/**
	 * Sets the category for the presentence investigation request.
	 * 
	 * @param category presentence investigation request category
	 */
	public void setCategory(PresentenceInvestigationCategory category);
	
	/**
	 * Returns the person.
	 * 
	 * @return person
	 */
	public Person getPerson();
	
	/**
	 * Sets the person.
	 * 
	 * @param person person
	 */
	public void setPerson(Person person);
}