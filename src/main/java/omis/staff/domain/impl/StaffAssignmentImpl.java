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
package omis.staff.domain.impl;

import omis.datatype.DateRange;
import omis.location.domain.Location;
import omis.organization.domain.OrganizationDivision;
import omis.person.domain.Person;
import omis.staff.domain.StaffAssignment;
import omis.staff.domain.StaffTitle;
import omis.supervision.domain.SupervisoryOrganization;

/**
 * Implementation of staff assignment.
 * 
 * @author Stephen Abson
 * @version 0.1.1 (March 5, 2014)
 * @since OMIS 3.0
 */
public class StaffAssignmentImpl
		implements StaffAssignment {

	private static final long serialVersionUID = 1L;
	
	private Long id;

	private Person staffMember;

	private SupervisoryOrganization supervisoryOrganization;
	
	private Location location;

	private DateRange dateRange;
	
	private StaffTitle title;

	private Boolean supervisory;
	
	private String staffId;
	
	private Long telephoneNumber;
	
	private Integer telephoneExtension;
	
	private String emailAddress;
	
	private OrganizationDivision organizationDivision;
	
	/** Instantiates a default implementation of staff assignment. */
	public StaffAssignmentImpl() {
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
	public void setStaffMember(final Person staffMember) {
		this.staffMember = staffMember;
	}

	/** {@inheritDoc} */
	@Override
	public Person getStaffMember() {
		return this.staffMember;
	}

	/** {@inheritDoc} */
	@Override
	public void setSupervisoryOrganization(
			final SupervisoryOrganization supervisoryOrganization) {
		this.supervisoryOrganization = supervisoryOrganization;
	}

	/** {@inheritDoc} */
	@Override
	public SupervisoryOrganization getSupervisoryOrganization() {
		return this.supervisoryOrganization;
	}

	/** {@inheritDoc} */
	@Override
	public void setLocation(final Location location) {
		this.location = location;
	}

	/** {@inheritDoc} */
	@Override
	public Location getLocation() {
		return this.location;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setDateRange(final DateRange dateRange) {
		this.dateRange = dateRange;
	}

	/** {@inheritDoc} */
	@Override
	public DateRange getDateRange() {
		return this.dateRange;
	}

	/** {@inheritDoc} */
	@Override
	public void setTitle(final StaffTitle title) {
		this.title = title;
	}

	/** {@inheritDoc} */
	@Override
	public StaffTitle getTitle() {
		return this.title;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setSupervisory(final Boolean supervisory) {
		this.supervisory = supervisory;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getSupervisory() {
		return this.supervisory;
	}
	
	/** {@inheritDoc} */
	@Override
	public String getStaffId() {
		return this.staffId;
	}

	/** {@inheritDoc} */
	@Override
	public Long getTelephoneNumber() {
		return this.telephoneNumber;
	}

	/** {@inheritDoc} */
	@Override
	public Integer getTelephoneExtension() {
		return this.telephoneExtension;
	}

	/** {@inheritDoc} */
	@Override
	public String getEmailAddress() {
		return this.emailAddress;
	}

	/** {@inheritDoc} */
	@Override
	public OrganizationDivision getOrganizationDivision() {
		return this.organizationDivision;
	}

	/** {@inheritDoc} */
	@Override
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	/** {@inheritDoc} */
	@Override
	public void setTelephoneNumber(Long telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	/** {@inheritDoc} */
	@Override
	public void setTelephoneExtension(Integer telephoneExtension) {
		this.telephoneExtension = telephoneExtension;
	}

	/** {@inheritDoc} */
	@Override
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/** {@inheritDoc} */
	@Override
	public void setOrganizationDivision(OrganizationDivision organizationDivision) {
		this.organizationDivision = organizationDivision;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof StaffAssignment)) {
			return false;
		}
		StaffAssignment that = (StaffAssignment) obj;
		if (this.getStaffMember() == null) {
			throw new IllegalStateException("Staff member required");
		}
		if (!this.getStaffMember().equals(that.getStaffMember())) {
			return false;
		}
		if (this.getSupervisoryOrganization() == null) {
			throw new IllegalStateException(
					"Supervisory organization required");
		}
		if (!this.getSupervisoryOrganization().equals(
				that.getSupervisoryOrganization())) {
			return false;
		}
		if (this.getTitle() == null) {
			throw new IllegalStateException("Staff required");
		}
		if (!this.getTitle().equals(that.getTitle())) {
			return false;
		}
		if (this.getLocation() != null) {
			if (!this.getLocation().equals(that.getLocation())) {
				return false;
			}
		} else if (that.getLocation() != null) {
			return false;
		}
		if (this.getDateRange() != null) {
			if (!this.getDateRange().equals(that.getDateRange())) {
				return false;
			}
		} else if (that.getDateRange() != null) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getStaffMember() == null) {
			throw new IllegalStateException("Staff member required");
		}
		if (this.getSupervisoryOrganization() == null) {
			throw new IllegalStateException(
					"Supervisory organization required");
		}
		if (this.getTitle() == null) {
			throw new IllegalStateException("Staff required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getStaffMember().hashCode();
		hashCode = 29 * hashCode + this.getSupervisoryOrganization().hashCode();
		if (this.getLocation() != null) {
			hashCode = 29 * hashCode + this.getLocation().hashCode();
		}
		hashCode = 29 * hashCode + this.getTitle().hashCode();
		if (this.getDateRange() != null) {
			hashCode = 29 * hashCode + this.getDateRange().hashCode();
		}
		return hashCode;
	}
}