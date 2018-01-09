package omis.staff.service.delegate;

import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.location.domain.Location;
import omis.organization.domain.OrganizationDivision;
import omis.person.domain.Person;
import omis.staff.dao.StaffAssignmentDao;
import omis.staff.domain.StaffAssignment;
import omis.staff.domain.StaffTitle;
import omis.supervision.domain.SupervisoryOrganization;

/**
 * Staff assignment delegate.
 *
 * @author Josh Divine
 * @version 0.1.0 (Nov 9, 2017)
 * @since OMIS 3.0
 */
public class StaffAssignmentDelegate {

	/* Data access objects. */
	
	private final StaffAssignmentDao staffAssignmentDao;
	
	/* Instance factories. */
	
	private final InstanceFactory<StaffAssignment> 
			staffAssignmentInstanceFactory;
	
	/**
	 * Instantiates an implementation of staff assignment delegate with the 
	 * specified data access object and instance factory.
	 * 
	 * @param staffAssignmentDao staff assignment data access object
	 * @param staffAssignmentInstanceFactory staff assignment instance factory
	 */
	public StaffAssignmentDelegate(final StaffAssignmentDao staffAssignmentDao,
			final InstanceFactory<StaffAssignment> 
					staffAssignmentInstanceFactory) {
		this.staffAssignmentDao = staffAssignmentDao;
		this.staffAssignmentInstanceFactory = staffAssignmentInstanceFactory;
		
	}

	/**
	 * Creates a new staff assignment.
	 * 
	 * @param staffMember staff member
	 * @param supervisoryOrganization supervisory organization
	 * @param location location
	 * @param dateRange date range
	 * @param title title
	 * @param supervisory supervisory
	 * @param organizationDivision organization division
	 * @param staffId staff id
	 * @param emailAddress email address
	 * @param telephoneNumber telephone number
	 * @param telephoneExtension telephone extension
	 * @return staff assignment
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public StaffAssignment create(final Person staffMember, 
			final SupervisoryOrganization supervisoryOrganization, 
			final Location location, final DateRange dateRange, 
			final StaffTitle title, final Boolean supervisory, 
			final OrganizationDivision organizationDivision, 
			final String staffId, final String emailAddress, 
			final Long telephoneNumber, final Integer telephoneExtension) 
					throws DuplicateEntityFoundException {
		if (this.staffAssignmentDao.find(staffMember, supervisoryOrganization, 
				location, DateRange.getStartDate(dateRange), 
				DateRange.getEndDate(dateRange)) != null) {
			throw new DuplicateEntityFoundException(
					"Staff assignment already exists.");
		}
		StaffAssignment staffAssignment = this.staffAssignmentInstanceFactory
				.createInstance();
		populateStaffAssignment(staffAssignment, staffMember, 
				supervisoryOrganization, location, dateRange, title, 
				supervisory, organizationDivision, staffId, emailAddress, 
				telephoneNumber, telephoneExtension);
		return this.staffAssignmentDao.makePersistent(staffAssignment);
	}
	
	/**
	 * Updates an existing staff assignment.
	 * 
	 * @param staffAssignment staff assignment
	 * @param staffMember staff member
	 * @param supervisoryOrganization supervisory organization
	 * @param location location
	 * @param dateRange date range
	 * @param title title
	 * @param supervisory supervisory
	 * @param organizationDivision organization division
	 * @param staffId staff id
	 * @param emailAddress email address
	 * @param telephoneNumber telephone number
	 * @param telephoneExtension telephone extension
	 * @return staff assignment
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public StaffAssignment update(final StaffAssignment staffAssignment,
			final Person staffMember, 
			final SupervisoryOrganization supervisoryOrganization, 
			final Location location, final DateRange dateRange, 
			final StaffTitle title, final Boolean supervisory, 
			final OrganizationDivision organizationDivision, 
			final String staffId, final String emailAddress, 
			final Long telephoneNumber, final Integer telephoneExtension) 
					throws DuplicateEntityFoundException {
		if (this.staffAssignmentDao.findExcluding(staffMember, 
				supervisoryOrganization, location, 
				DateRange.getStartDate(dateRange), 
				DateRange.getEndDate(dateRange), staffAssignment) != null) {
			throw new DuplicateEntityFoundException(
					"Staff assignment already exists.");
		}
		populateStaffAssignment(staffAssignment, staffMember, 
				supervisoryOrganization, location, dateRange, title, 
				supervisory, organizationDivision, staffId, emailAddress, 
				telephoneNumber, telephoneExtension);
		return this.staffAssignmentDao.makePersistent(staffAssignment);
	}
	
	/**
	 * Removes the specified staff assignment.
	 * 
	 * @param staffAssignment staff assignment
	 */
	public void remove(final StaffAssignment staffAssignment) {
		this.staffAssignmentDao.makeTransient(staffAssignment);
	}
	
	// Populates a staff assignment
	private void populateStaffAssignment(final StaffAssignment staffAssignment, 
			final Person staffMember,
			final SupervisoryOrganization supervisoryOrganization, 
			final Location location, final DateRange dateRange,
			final StaffTitle title, final Boolean supervisory, 
			final OrganizationDivision organizationDivision,
			final String staffId, final String emailAddress, 
			final Long telephoneNumber, final Integer telephoneExtension) {
		staffAssignment.setDateRange(dateRange);
		staffAssignment.setEmailAddress(emailAddress);
		staffAssignment.setLocation(location);
		staffAssignment.setOrganizationDivision(organizationDivision);
		staffAssignment.setStaffId(staffId);
		staffAssignment.setStaffMember(staffMember);
		staffAssignment.setSupervisory(supervisory);
		staffAssignment.setSupervisoryOrganization(supervisoryOrganization);
		staffAssignment.setTelephoneNumber(telephoneNumber);
		staffAssignment.setTelephoneExtension(telephoneExtension);
		staffAssignment.setTitle(title);
	}
}
