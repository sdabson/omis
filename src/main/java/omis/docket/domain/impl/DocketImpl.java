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
package omis.docket.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.court.domain.Court;
import omis.docket.domain.Docket;
import omis.person.domain.Person;

/**
 * Implementation of docket.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class DocketImpl
		implements Docket {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	private Person person;
	
	private Court court;
	
	private String value;

	/** Instantiates implementation of docket. */
	public DocketImpl() {
		// Default instantiation
	}
	
	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(
			final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setUpdateSignature(
			final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setPerson(final Person person) {
		this.person = person;
	}

	/** {@inheritDoc} */
	@Override
	public Person getPerson() {
		return this.person;
	}

	/** {@inheritDoc} */
	@Override
	public void setCourt(final Court court) {
		this.court = court;
	}

	/** {@inheritDoc} */
	@Override
	public Court getCourt() {
		return this.court;
	}

	/** {@inheritDoc} */
	@Override
	public void setValue(final String value) {
		this.value = value;
	}

	/** {@inheritDoc} */
	@Override
	public String getValue() {
		return this.value;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Docket)) {
			return false;
		}
		Docket that = (Docket) obj;
		if (this.getPerson() == null) {
			throw new IllegalStateException("Person required");
		}
		if (!this.getPerson().equals(that.getPerson())) {
			return false;
		}
		if (this.getCourt() == null) {
			throw new IllegalStateException("Court required");
		}
		if (!this.getCourt().equals(that.getCourt())) {
			return false;
		}
		if (this.getValue() == null) {
			throw new IllegalStateException("Value required");
		}
		if (!this.getValue().equals(that.getValue())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getPerson() == null) {
			throw new IllegalStateException("Person required");
		}
		if (this.getCourt() == null) {
			throw new IllegalStateException("Court required");
		}
		if (this.getValue() == null) {
			throw new IllegalStateException("Value required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getPerson().hashCode();
		hashCode = 29 * hashCode + this.getCourt().hashCode();
		hashCode = 29 * hashCode + this.getValue().hashCode();
		return hashCode;
	}
	
	/**
	 * Returns string representation of {@code this} including person,
	 * court and value.
	 * 
	 * <p>See {@link Object#toString()}.
	 * 
	 * @return string representation of {@code this} including person,
	 * court and value
	 */
	@Override
	public String toString() {
		return String.format("#%d: [%s], [%s], %s",
			this.getId(), this.getPerson(), this.getCourt(), this.getValue());
	}
}