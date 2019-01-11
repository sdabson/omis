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
package omis.presentenceinvestigation.web.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import omis.person.domain.Person;
import omis.presentenceinvestigation.domain.PresentenceInvestigationCategory;
import omis.user.domain.UserAccount;

/** 
 * Form for presentence investigation requests.
 * 
 * @author Ryan Johns
 * @author Annie Wahl
 * @author Josh Divine
 * @version 0.1.6 (Aug 15, 2018)
 * @since OMIS 3.0
 */
public class PresentenceInvestigationRequestForm {
	
	private UserAccount assignedUserAccount;
	
	private Date requestDate;
	
	private Date sentenceDate;
	
	private Boolean createPerson;
	
	private Person person;
	
	private String lastName;
	
	private String firstName;
	
	private String middleName;
	
	private String suffix;
	
	private Date submissionDate;
	
	private PresentenceInvestigationCategory category;
	
	private List<PresentenceInvestigationDocketAssociationItem> 
			presentenceInvestigationDocketAssociationItems = new ArrayList<>();
	
	private List<PresentenceInvestigationRequestNoteItem>
		presentenceInvestigationRequestNoteItems =
			new ArrayList<PresentenceInvestigationRequestNoteItem>();
	
	private List<PresentenceInvestigationDelayItem>
			presentenceInvestigationDelayItems = new ArrayList<>();
	
	/**
	 * 
	 */
	public PresentenceInvestigationRequestForm() {
	}

	/**
	 * Returns the assignedUser
	 * @return assignedUserAccount - UserAccount
	 */
	public UserAccount getAssignedUserAccount() {
		return assignedUserAccount;
	}

	/**
	 * Sets the assignedUser
	 * @param assignedUserAccount - UserAccount
	 */
	public void setAssignedUserAccount(final UserAccount assignedUserAccount) {
		this.assignedUserAccount = assignedUserAccount;
	}

	/**
	 * Returns the requestDate
	 * @return requestDate - Date
	 */
	public Date getRequestDate() {
		return requestDate;
	}

	/**
	 * Sets the requestDate
	 * @param requestDate - Date
	 */
	public void setRequestDate(final Date requestDate) {
		this.requestDate = requestDate;
	}

	/**
	 * Returns the sentenceDate
	 * @return sentenceDate - Date
	 */
	public Date getSentenceDate() {
		return sentenceDate;
	}

	/**
	 * Sets the sentenceDate
	 * @param sentenceDate - Date
	 */
	public void setSentenceDate(final Date sentenceDate) {
		this.sentenceDate = sentenceDate;
	}

	/**
	 * Returns the createPerson
	 * @return createPerson - Boolean
	 */
	public Boolean getCreatePerson() {
		return createPerson;
	}

	/**
	 * Sets the createPerson
	 * @param createPerson - Boolean
	 */
	public void setCreatePerson(final Boolean createPerson) {
		this.createPerson = createPerson;
	}

	/**
	 * Returns the person
	 * @return person - Person
	 */
	public Person getPerson() {
		return person;
	}

	/**
	 * Sets the person
	 * @param person - Person
	 */
	public void setPerson(final Person person) {
		this.person = person;
	}

	/**
	 * Returns the lastName
	 * @return lastName - String
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the lastName
	 * @param lastName - String
	 */
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Returns the firstName
	 * @return firstName - String
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the firstName
	 * @param firstName - String
	 */
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Returns the middleName
	 * @return middleName - String
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * Sets the middleName
	 * @param middleName - String
	 */
	public void setMiddleName(final String middleName) {
		this.middleName = middleName;
	}

	/**
	 * Returns the suffix
	 * @return suffix - String
	 */
	public String getSuffix() {
		return suffix;
	}

	/**
	 * Sets the suffix
	 * @param suffix - String
	 */
	public void setSuffix(final String suffix) {
		this.suffix = suffix;
	}
	
	/**
	 * Returns the category
	 * @return category - PresentenceInvestigationCategory
	 */
	public PresentenceInvestigationCategory getCategory() {
		return category;
	}

	/**
	 * Sets the category
	 * @param category - PresentenceInvestigationCategory
	 */
	public void setCategory(final PresentenceInvestigationCategory category) {
		this.category = category;
	}
	
	/** Sets submission date.
	 * @param submissionDate - submission date.
	 */
	public void setSubmissionDate(final Date submissionDate) {
		this.submissionDate = submissionDate;
	}
	
	/** Gets submission date.
	 * @return submission date. 
	 */
	public Date getSubmissionDate() {
		return this.submissionDate;
	}

	/**
	 * Returns the presentence investigation docket association items.
	 *
	 * @return presentence investigation docket association items
	 */
	public List<PresentenceInvestigationDocketAssociationItem> 
			getPresentenceInvestigationDocketAssociationItems() {
		return presentenceInvestigationDocketAssociationItems;
	}

	/**
	 * Sets the presentence investigation docket association items.
	 *
	 * @param presentenceInvestigationDocketAssociationItems presentence 
	 * investigation docket association items
	 */
	public void setPresentenceInvestigationDocketAssociationItems(
			final List<PresentenceInvestigationDocketAssociationItem> 
				presentenceInvestigationDocketAssociationItems) {
		this.presentenceInvestigationDocketAssociationItems = 
				presentenceInvestigationDocketAssociationItems;
	}

	/**
	 * Returns the presentence investigation request note items.
	 * 
	 * @return presentence investigation request note items
	 */
	public List<PresentenceInvestigationRequestNoteItem>
		getPresentenceInvestigationRequestNoteItems() {
		return presentenceInvestigationRequestNoteItems;
	}

	/**
	 * Sets the presentence investigation request note items.
	 * 
	 * @param presentenceInvestigationRequestNoteItems presentence investigation 
	 * request note items
	 */
	public void setPresentenceInvestigationRequestNoteItems(
			final List<PresentenceInvestigationRequestNoteItem>
				presentenceInvestigationRequestNoteItems) {
		this.presentenceInvestigationRequestNoteItems =
				presentenceInvestigationRequestNoteItems;
	}

	/**
	 * Returns the presentence investigation delay items.
	 *
	 * @return presentence investigation delay items
	 */
	public List<PresentenceInvestigationDelayItem> 
			getPresentenceInvestigationDelayItems() {
		return presentenceInvestigationDelayItems;
	}

	/**
	 * Sets the presentence investigation delay items.
	 *
	 * @param presentenceInvestigationDelayItems presentence investigation delay 
	 * items
	 */
	public void setPresentenceInvestigationDelayItems(
			final List<PresentenceInvestigationDelayItem> 
				presentenceInvestigationDelayItems) {
		this.presentenceInvestigationDelayItems = 
				presentenceInvestigationDelayItems;
	}
}