package omis.staff.domain;

import java.io.Serializable;

import omis.datatype.DateRange;
import omis.location.domain.Location;
import omis.organization.domain.OrganizationDivision;
import omis.person.domain.Person;
import omis.supervision.domain.SupervisoryOrganization;

/**
 * Assignment of a staff member to an organization providing correctional
 * supervision.
 * 
 * Whether the staff member can provide correctional supervision to offenders
 * supervised by the organization is indicated by the {@code supervisory}
 * property.
 * 
 * @author Stephen Abson
 * @author Sheronda Vaughn
 * @version 0.1.0 (Oct 21, 2013)
 * @since OMIS 3.0
 */
public interface StaffAssignment
		extends Serializable {

	/**
	 * Sets the ID.
	 * 
	 * @param id ID
	 */
	void setId(Long id);
	
	/**
	 * Returns the ID.
	 * 
	 * @return ID
	 */
	Long getId();
	
	/**
	 * Sets the staff member.
	 * 
	 * @param staffMember staff member
	 */
	void setStaffMember(Person staffMember);
	
	/**
	 * Returns the staff member.
	 * 
	 * @return staff member
	 */
	Person getStaffMember();
	
	/**
	 * Sets the supervisory organization.
	 * 
	 * @param supervisoryOrganization supervisory organization
	 */
	void setSupervisoryOrganization(
			SupervisoryOrganization supervisoryOrganization);
	
	/**
	 * Returns the supervisory organization.
	 * 
	 * @return supervisory organization
	 */
	SupervisoryOrganization getSupervisoryOrganization();
	
	/**
	 * Sets the location.
	 * 
	 * @param location location
	 */
	void setLocation(Location location);
	
	/**
	 * Returns the location.
	 * 
	 * @return location
	 */
	Location getLocation();
	
	/**
	 * Sets the date range.
	 * 
	 * @param dateRange date range
	 */
	void setDateRange(DateRange dateRange);
	
	/**
	 * Returns the date range.
	 * 
	 * @return date range
	 */
	DateRange getDateRange();
	
	/**
	 * Sets the title.
	 * 
	 * @param title title
	 */
	void setTitle(StaffTitle title);
	
	/**
	 * Returns the title.
	 * 
	 * @return title
	 */
	StaffTitle getTitle();
	
	/**
	 * Sets whether the assignment is supervisory.
	 * 
	 * @param supervisory whether assignment is supervisory
	 */
	void setSupervisory(Boolean supervisory);
	
	/**
	 * Returns whether the assignment is supervisory.
	 * 
	 * @return whether assignment is supervisory
	 */
	Boolean getSupervisory();
	
	/**
	 * Sets staff ID.
	 * 
	 * @param staffId staff ID
	 */
	void setStaffId(String staffId);
	/**
	 * Returns the staff ID.
	 * 
	 * @return staffId
	 */
	String getStaffId();
	
	/**
	 * Sets the staff assignment telephone number.
	 * 
	 * @param telephoneNumber telephone number
	 */
	void setTelephoneNumber(Long telephoneNumber);
	/**
	 * Returns the staff assignment telephone number.
	 * 
	 * @return telephone number
	 */
	Long getTelephoneNumber();
	
	/**
	 * Sets staff assignment telephone extension.
	 * 
	 * @param telephoneExtension telephone extension
	 */
	void setTelephoneExtension(Integer telephoneExtension);
	/**
	 * Returns the staff assignment telephone number extension.
	 * 
	 * @return 
	 */
	Integer getTelephoneExtension();
	
	/**
	 * Sets the staff assignment email address.
	 * 
	 * @param emailAddress email address
	 */
	void setEmailAddress(String emailAddress);
	/**
	 * Returns the staff assignment email address.
	 * 
	 * @return email address
	 */
	String getEmailAddress();
	
	/**
	 * Sets the staff assignment organization division.
	 * 
	 * @param organizationDivision organization division
	 */
	void setOrganizationDivision(OrganizationDivision organizationDivision);
	/**
	 * Returns the staff assignment organization division.
	 * 
	 * @return organization division
	 */
	OrganizationDivision getOrganizationDivision();
	
	/**
	 * Compares {@code this} and {@code obj} for equality.
	 * 
	 * <p>Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * 
	 * @param obj reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code obj} are equal;
	 * {@code false} otherwise
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the comparison is {@code null} 
	 */
	@Override
	boolean equals(Object obj);
	
	/**
	 * Returns a hash code for {@code this}.
	 * 
	 * <p>Any mandatory property of {@code this} may be used in the hash code.
	 * If a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * 
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null}
	 */
	@Override
	int hashCode();
}