/*
 *  OMIS - Offender Management Information System
 *  Copyright (C) 2011 - 2017 State of Montana
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package omis.travelpermit.domain.impl;

import omis.travelpermit.domain.TravelMethod;

/**
 * Implementation of travel method.
 * @author Yidong Li
 * @author Sheronda Vaughn
 * @version 0.1.0 (May 17, 2018)
 * @since OMIS 3.0 
 */
public class TravelMethodImpl implements TravelMethod {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private Boolean descriptionRequired;
	private String descriptionName;
	private String numberName;
	private Short sortOrder;
	
	/** Constructor. */
	public TravelMethodImpl() {
	}
	
	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}
	
	/** {@inheritDoc} */
	@Override
	public Boolean getDescriptionRequired() {
		return this.descriptionRequired;
	}

	/** {@inheritDoc} */
	@Override
	public void setDescriptionRequired(final Boolean descriptionRequired) {
		this.descriptionRequired = descriptionRequired;
	}
	
	/** {@inheritDoc} */
	@Override
	public String getDescriptionName() {
		return this.descriptionName;
	}

	/** {@inheritDoc} */
	@Override
	public void setDescriptionName(final String descriptionName) {
		this.descriptionName = descriptionName;
	}

	/** {@inheritDoc} */
	@Override
	public String getName() {
		return this.name;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setName(final String name) {
		this.name=name;
	}

	@Override
	public void setNumberName(final String numberName) {
		this.numberName = numberName;
		
	}

	@Override
	public String getNumberName() {
		return this.numberName;
	}

	@Override
	public void setSortOrder(final Short sortOrder) {
		this.sortOrder = sortOrder;
	}

	@Override
	public Short getSortOrder() {
		return this.sortOrder;
	}
		
	/** {@inheritDoc} */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TravelMethod)) {
			return false;
		}
		
		TravelMethod that = (TravelMethod) obj;
		
		if (this.getName() == null) {
			throw new IllegalStateException("Name required.");
		}
		if (this.getName().equals(that.getName())) {
			return true;
		}
		return false;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getName() == null) {
			throw new IllegalStateException("Name required.");
		}
		
		int hashCode = 14;
		
		hashCode += 29 * this.getName().hashCode();
		return hashCode;
	}
}