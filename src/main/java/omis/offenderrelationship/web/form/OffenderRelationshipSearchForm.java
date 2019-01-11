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
package omis.offenderrelationship.web.form;

import java.io.Serializable;
import java.util.Date;

/**
 * Form for searching for offender relationships.
 *
 * @author Stephen Abson
 * @author Yidong Li
 * @version 0.0.1 (Jun 24, 2015)
 * @since OMIS 3.0
 */
public class OffenderRelationshipSearchForm
		implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private OffenderRelationshipSearchType type;
	
	private String lastName;
	
	private String firstName;
	
	private Integer offenderNumber;
	
	private Date birthDate;
	
	private String socialSecurityNumber;
	
	private String addressValue;
	
	private String cityName;
	
	private String stateName;
	
	private String zipCodeValue;
	
	private Long telephoneNumberValue;
	
	private String onlineAccountName;	

	/** Instantiates a default form for searching for offender relationships. */
	public OffenderRelationshipSearchForm() {
		// Default instantiation
	}

	/**
	 * Returns type.
	 * 
	 * @return type
	 */
	public OffenderRelationshipSearchType getType() {
		return this.type;
	}

	/**
	 * Sets type.
	 * 
	 * @param type type
	 */
	public void setType(final OffenderRelationshipSearchType type) {
		this.type = type;
	}
	
	/**
	 * Returns last name.
	 * 
	 * @return last name
	 */
	public String getLastName() {
		return this.lastName;
	}
	
	/**
	 * Sets last name.
	 * 
	 * @param lastName last name
	 */
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * Returns first name.
	 * 
	 * @return first name
	 */
	public String getFirstName() {
		return this.firstName;
	}
	
	/**
	 * Sets first name.
	 * 
	 * @param firstName first name
	 */
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * Returns offender number.
	 * 
	 * @return offender number
	 */
	public Integer getOffenderNumber() {
		return this.offenderNumber;
	}
	
	/**
	 * Sets offender number.
	 * 
	 * @param offenderNumber offender number
	 */
	public void setOffenderNumber(final Integer offenderNumber) {
		this.offenderNumber = offenderNumber;
	}
	
	/**
	 * Returns birth date.
	 * 
	 * @return birth date
	 */
	public Date getBirthDate() {
		return this.birthDate;
	}
	
	/**
	 * Sets birth date.
	 * 
	 * @param birthDate birth date
	 */
	public void setBirthDate(final Date birthDate) {
		this.birthDate = birthDate;
	}
	
	/**
	 * Returns social security number.
	 * 
	 * @return social security number
	 */
	public String getSocialSecurityNumber() {
		return this.socialSecurityNumber;
	}
	
	/**
	 * Sets social security number.
	 * 
	 * @param socialSecurityNumber social security number
	 */
	public void setSocialSecurityNumber(final String socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}
	
	/**
	 * Returns address value.
	 * 
	 * @return address value
	 */
	public String getAddressValue() {
		return this.addressValue;
	}
	
	/**
	 * Sets address value.
	 * 
	 * @param addressValue address value
	 */
	public void setAddressValue(final String addressValue) {
		this.addressValue = addressValue;
	}
	
	public String getCityName() {
		return this.cityName;
	}

	public void setCityName(final String cityName) {
		this.cityName = cityName;
	}

	public String getStateName() {
		return this.stateName;
	}

	public void setStateName(final String stateName) {
		this.stateName = stateName;
	}

	public String getZipCodeValue() {
		return this.zipCodeValue;
	}

	public void setZipCodeValue(final String zipCodeValue) {
		this.zipCodeValue = zipCodeValue;
	}

	/**
	 * Returns telephone number value.
	 * 
	 * @return telephone number value
	 */
	public Long getTelephoneNumberValue() {
		return this.telephoneNumberValue;
	}
	
	/**
	 * Sets telephone number value.
	 * 
	 * @param telephoneNumberValue telephone number value
	 */
	public void setTelephoneNumberValue(final Long telephoneNumberValue) {
		this.telephoneNumberValue = telephoneNumberValue;
	}
	
	/**
	 * Returns online account name.
	 * 
	 * @return online account name
	 */
	public String getOnlineAccountName() {
		return this.onlineAccountName;
	}
	
	/**
	 * Sets online account name.
	 * 
	 * @param onlineAccountName online account name
	 */
	public void setOnlineAccountName(final String onlineAccountName) {
		this.onlineAccountName = onlineAccountName;
	}
}