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
package omis.offendercontact.web.form;

import java.io.Serializable;

import omis.contact.domain.TelephoneNumber;
import omis.contact.web.form.TelephoneNumberFields;

/**
 * Offender contact telephone number item.
 *
 * @author Josh Divine
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class OffenderContactTelephoneNumberItem
		implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private TelephoneNumberFields fields;
	
	private OffenderContactTelephoneNumberOperation operation;
	
	private TelephoneNumber telephoneNumber;
	
	/** Instantiates victim telephone number item. */
	public OffenderContactTelephoneNumberItem() {
		// Default instantiation
	}
	
	/**
	 * Sets telephone number fields.
	 * 
	 * @param fields telephone number fields
	 */
	public void setFields(final TelephoneNumberFields fields) {
		this.fields = fields;
	}
	
	/**
	 * Returns telephone number fields.
	 * 
	 * @return telephone number fields
	 */
	public TelephoneNumberFields getFields() {
		return this.fields;
	}
	
	/**
	 * Sets operation.
	 * 
	 * @param operation operation
	 */
	public void setOperation(final OffenderContactTelephoneNumberOperation operation) {
		this.operation = operation;
	}
	
	/**
	 * Returns operation.
	 * 
	 * @return operation
	 */
	public OffenderContactTelephoneNumberOperation getOperation() {
		return this.operation;
	}
	
	/**
	 * Sets telephone number.
	 * 
	 * @param telephoneNumber telephone number
	 */
	public void setTelephoneNumber(final TelephoneNumber telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	
	/**
	 * Returns telephone number.
	 * 
	 * @return telephone number
	 */
	public TelephoneNumber getTelephoneNumber() {
		return this.telephoneNumber;
	}
}