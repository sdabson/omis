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

import omis.contact.domain.OnlineAccount;
import omis.contact.web.form.OnlineAccountFields;

/**
 * Offender contact online account item.
 *
 * @author Josh Divine
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class OffenderContactOnlineAccountItem
		implements Serializable {

	private static final long serialVersionUID = 1;
	
	private OnlineAccountFields fields;
	
	private OffenderContactOnlineAccountOperation operation;
	
	private OnlineAccount onlineAccount;
	
	/** Instantiates victim online account item. */
	public OffenderContactOnlineAccountItem() {
		// Default instantiation
	}
	
	/**
	 * Sets fields.
	 * 
	 * @param fields fields
	 */
	public void setFields(final OnlineAccountFields fields) {
		this.fields = fields;
	}
	
	/**
	 * Returns fields.
	 * 
	 * @return fields
	 */
	public OnlineAccountFields getFields() {
		return this.fields;
	}
	
	/**
	 * Sets operation.
	 * 
	 * @param operation operation
	 */
	public void setOperation(final OffenderContactOnlineAccountOperation operation) {
		this.operation = operation;
	}
	
	/**
	 * Returns operation.
	 * 
	 * @return operation
	 */
	public OffenderContactOnlineAccountOperation getOperation() {
		return this.operation;
	}
	
	/**
	 * Sets online account.
	 * 
	 * @param onlineAccount online account
	 */
	public void setOnlineAccount(final OnlineAccount onlineAccount) {
		this.onlineAccount = onlineAccount;
	}
	
	/**
	 * Returns online account.
	 * 
	 * @return online account
	 */
	public OnlineAccount getOnlineAccount() {
		return this.onlineAccount;
	}
}