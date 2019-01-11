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
package omis.presentenceinvestigation.service;

import java.util.Date;
import java.util.List;

import omis.court.domain.Court;
import omis.docket.domain.Docket;
import omis.docket.exception.DocketExistsException;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.person.domain.Person;
import omis.person.domain.Suffix;
import omis.presentenceinvestigation.domain.PresentenceInvestigationCategory;
import omis.presentenceinvestigation.domain.PresentenceInvestigationDelay;
import omis.presentenceinvestigation.domain.PresentenceInvestigationDelayCategory;
import omis.presentenceinvestigation.domain.PresentenceInvestigationDocketAssociation;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequestNote;
import omis.user.domain.UserAccount;

/** 
 * Service for presentence investigation request related operations.
 * 
 * @author Ryan Johns
 * @author Annie Wahl
 * @author Josh Divine
 * @version 0.1.4 (Aug 15, 2018)
 * @since OMIS 3.0
 */
public interface PresentenceInvestigationRequestService {
	
	 /** 
	  * Creates a presentence investigation request.
	  * 
	  * @param assignedUser assigned user
	  * @param requestDate request date
	  * @param expectedCompletionDate expected completion date
	  * @param person person
	  * @param sentenceDate sentence date
	  * @param category presentence investigation category
	  * @param submissionDate submission date
	  * @return presentence investigation request 
	  * @throws DuplicateEntityFoundException when presentence investigation
	  * request exists with given docket
	  */
	 public PresentenceInvestigationRequest create(
			 UserAccount assignedUser, Date requestDate, 
			 Date expectedCompletionDate, Person person, 
			 Date sentenceDate, PresentenceInvestigationCategory category, 
			 Date submissionDate) throws DuplicateEntityFoundException;
	 
	/** 
	 * Updates an existing presentence investigation request.
	 * 
	 * @param presentenceInvestigationRequest presentence investigation request
	 * @param assignedUser assigned user
	 * @param requestDate request date
	 * @param expectedCompletionDate expected completion date
	 * @param completionDate completion date
	 * @param person person
	 * @param sentenceDate sentence date
	 * @param category presentence investigation category
	 * @param submissionDate submission date
	 * @return presentence investigation request
	 * @throws DuplicateEntityFoundException when presentence investigation
	 * request exists for given docket
	 */
	public PresentenceInvestigationRequest update(
			PresentenceInvestigationRequest 
				presentenceInvestigationRequest,
			UserAccount assignedUser, Date requestDate, 
			Date expectedCompletionDate, Person person, Date sentenceDate, 
			PresentenceInvestigationCategory category, Date submissionDate) 
					throws DuplicateEntityFoundException;
	
	/** 
	 * Removes a presentence investigation request.
	 * 
	 * @param presentenceInvestigationRequest presentence investigation request
	 */
	public void remove(PresentenceInvestigationRequest 
			presentenceInvestigationRequest);
	
	/**
	 * Creates a person with the specified properties.
	 * 
	 * @param lastName last name
	 * @param firstName first name
	 * @param middleName middle name
	 * @param suffix suffix
	 * @return person
	 * @throws DuplicateEntityFoundException when a person already exists with
	 * the same name and identity
	 */
	public Person createPerson(String lastName, String firstName,
			String middleName, String suffix)
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates a person with the specified properties.
	 * 
	 * @param person person to update
	 * @param lastName last name
	 * @param firstName first name
	 * @param middleName middle name
	 * @param suffix suffix
	 * @return person
	 * @throws DuplicateEntityFoundException when a person already exists with
	 * the same name and identity
	 */
	public Person updatePerson(Person person, String lastName, String firstName,
			String middleName, String suffix)
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes a Person
	 * @param person - Person to remove
	 */
	public void removePerson(Person person);
	
	/**
	 * Creates a docket with the specified properties.
	 * 
	 * @param person person
	 * @param court court
	 * @param value value
	 * @return docket
	 * @throws DocketExistsException When a docket already exists with the 
	 * specified properties
	 */
	public Docket createDocket(Person person, Court court, String value)
					throws DocketExistsException;
	
	/**
	 * Updates a docket with the specified properties.
	 * 
	 * @param docket docket to update
	 * @param court court
	 * @param value value
	 * @return docket
	 * @throws DocketExistsException when a docket already exists with the 
	 * specified properties
	 */
	public Docket updateDocket(Docket docket, Court court, String value)
					throws DocketExistsException;
	
	/**
	 * Removes a docket.
	 * 
	 * @param docket docket to remove
	 */
	public void removeDocket(Docket docket);
	
	/**
	 * Creates a presentence investigation request note with the specified 
	 * properties.
	 * 
	 * @param presentenceInvestigationRequest presentence investigation request
	 * @param description description
	 * @param date date
	 * @return presentence investigation request note
	 * @throws DuplicateEntityFoundException when a presentence investigation 
	 * request note already exists with the given date and description for the 
	 * specified presentence investigation request
	 */
	public PresentenceInvestigationRequestNote
		createPresentenceInvestigationRequestNote(
			PresentenceInvestigationRequest presentenceInvestigationRequest,
			String description, Date date)
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates a presentence investigation request note with the specified 
	 * properties.
	 * 
	 * @param presentenceInvestigationRequestNote presentence investigation 
	 * request note
	 * @param description description
	 * @param date date
	 * @return presentence investigation request note
	 * @throws DuplicateEntityFoundException when a presentence investigation 
	 * request note already exists with the given date and description for the 
	 * specified presentence investigation request
	 */
	public PresentenceInvestigationRequestNote
		updatePresentenceInvestigationRequestNote(
			PresentenceInvestigationRequestNote
				presentenceInvestigationRequestNote,
			String description, Date date)
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes a presentence investigation request note.
	 * 
	 * @param presentenceInvestigationRequestNote presentence investigation 
	 * request note
	 */
	public void removePresentenceInvestigationRequestNote(
			PresentenceInvestigationRequestNote
				presentenceInvestigationRequestNote);
	
	/**
	 * Returns a list of presentence investigation request notes found with the
	 * specified presentence investigation request.
	 * 
	 * @param presentenceInvestigationRequest presentence investigation request
	 * @return list of presentence investigation request notes
	 */
	public List<PresentenceInvestigationRequestNote>
		findPresentenceInvestigationRequestNotesByPresentenceInvestigationRequest(
			PresentenceInvestigationRequest presentenceInvestigationRequest);
	
	/**
	 * Returns a list of all suffixes.
	 * 
	 * @return list of suffixes
	 */
	public List<Suffix> findSuffixes();
	
	/**
	 * Returns a list of all courts.
	 * 
	 * @return list of courts
	 */
	public List<Court> findCourts();
	
	/**
	 * Returns whether person is an offender.
	 * 
	 * @param person person
	 * @return whether person is an offender
	 */
	public Boolean isOffender(Person person);
	
	/**
	 * Finds user account by user name.
	 *
	 * @param userName user name
	 * @return user account name
	 */
	UserAccount findUserAccountByUsername(String username);
	
	/**
	 * Returns a list of all presentence investigation categories.
	 * 
	 * @return list of all presentence investigation categories
	 */
	public List<PresentenceInvestigationCategory>
			findAllPresentenceInvestigationCategories();

	/**
	 * Creates a new presentence investigation delay.
	 * 
	 * @param presentenceInvestigationRequest presentence investigation request
	 * @param date date
	 * @param reason presentence investigation delay category
	 * @return presentence investigation delay
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	PresentenceInvestigationDelay createPresentenceInvestigationDelay(
			PresentenceInvestigationRequest presentenceInvestigationRequest, 
			Date date, PresentenceInvestigationDelayCategory reason) 
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates an existing presentence investigation delay.
	 * 
	 * @param presentenceInvestigationDelay presentence investigation delay
	 * @param date date
	 * @param reason presentence investigation delay category
	 * @return presentence investigation delay
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	PresentenceInvestigationDelay updatePresentenceInvestigationDelay(
			PresentenceInvestigationDelay presentenceInvestigationDelay, 
			Date date, PresentenceInvestigationDelayCategory reason) 
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes the specified presentence investigation delay.
	 * 
	 * @param presentenceInvestigationDelay presentence investigation delay
	 */
	void removePresentenceInvestigationDelay(
			PresentenceInvestigationDelay presentenceInvestigationDelay);
	
	/**
	 * Returns a list of presentence investigation delay categories.
	 *  
	 * @return list of presentence investigation delay categories
	 */
	List<PresentenceInvestigationDelayCategory> 
			findPresentenceInvestigationDelayCategories();
	
	/**
	 * Returns a list of presentence investigation delays for the specified 
	 * presentence investigation request.
	 * 
	 * @param presentenceInvestigationRequest presentence investigation request
	 * @return list of presentence investigation delays
	 */
	List<PresentenceInvestigationDelay> findPresentenceInvestigationDelays(
			PresentenceInvestigationRequest presentenceInvestigationRequest);
	
	/**
	 * Returns a list of dockets for the specified offender.
	 * 
	 * @param offender offender
	 * @return list of dockets
	 */
	List<Docket> findDocketsByOffender(Offender offender);
	
	/**
	 * Returns a list of dockets for the specified value.
	 * 
	 * @param value value
	 * @return list of dockets
	 */
	List<Docket> findDocketsByValue(String value);
	
	/**
	 * Creates a presentence investigation docket association.
	 * 
	 * @param presentenceInvestigationRequest presentence investigation request
	 * @param docket
	 * @return presentence investigation docket association
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	PresentenceInvestigationDocketAssociation 
			createPresentenceInvestigationDocketAssociation(
					PresentenceInvestigationRequest 
						presentenceInvestigationRequest, 
					Docket docket) throws DuplicateEntityFoundException;
	
	/**
	 * Removes the specified presentence investigation docket association.
	 * 
	 * @param presentenceInvestigationDocketAssociation presentence 
	 * investigation docket association
	 */
	void removePresentenceInvestigationDocketAssociation(
			PresentenceInvestigationDocketAssociation 
				presentenceInvestigationDocketAssociation);
	
	/**
	 * Returns a list of presentence investigation docket associations for the 
	 * specified presentence investigation request.
	 * 
	 * @param presentenceInvestigationRequest presentence investigation request
	 * @return list of presentence investigation docket associations
	 */
	List<PresentenceInvestigationDocketAssociation> 
			findPresentenceInvestigationDocketAssociationsByPresentenceInvestigationRequest(
					final PresentenceInvestigationRequest 
						presentenceInvestigationRequest);
}