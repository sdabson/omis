
package omis.hearing.service.delegate;

import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.hearing.dao.StaffAttendanceDao;
import omis.hearing.domain.Hearing;
import omis.hearing.domain.StaffAttendance;
import omis.instance.factory.InstanceFactory;
import omis.staff.domain.StaffAssignment;

/**
 * StaffAttendanceDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.1 (Apr 17, 2017)
 *@since OMIS 3.0
 *
 */
public class StaffAttendanceDelegate {
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"Staff Attendance exists for specified hearing with given"
			+ " staff assignment";
	
	private final StaffAttendanceDao staffAttendanceDao;
	
	private final InstanceFactory<StaffAttendance> 
		staffAttendanceInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for StaffAttendanceDelegate
	 * @param staffAttendanceDao
	 * @param staffAttendanceInstanceFactory
	 * @param auditComponentRetriever
	 */
	public StaffAttendanceDelegate(
			final StaffAttendanceDao staffAttendanceDao,
			final InstanceFactory<StaffAttendance> 
				staffAttendanceInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.staffAttendanceDao = staffAttendanceDao;
		this.staffAttendanceInstanceFactory = staffAttendanceInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	
	/**
	 * Creates a staffAttendance with specified properties
	 * @param hearing
	 * @param staff - StaffAssignment
	 * @return newly created StaffAttendance
	 * @throws DuplicateEntityFoundException - when Staff Attendance exists for
	 * specified hearing with given staff assignment
	 */
	public StaffAttendance create(final Hearing hearing,
			final StaffAssignment staff)throws DuplicateEntityFoundException{
		if(this.staffAttendanceDao.find(hearing, staff) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		StaffAttendance staffAttendance = 
				this.staffAttendanceInstanceFactory.createInstance();
		
		staffAttendance.setHearing(hearing);
		staffAttendance.setStaff(staff);
		staffAttendance.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		staffAttendance.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.staffAttendanceDao.makePersistent(staffAttendance);
	}
	
	/**
	 * Updates a staffAttendance with specified properties
	 * @param staffAttendance - staffAttendance to update
	 * @param staff - StaffAssignment
	 * @return updated StaffAttendance
	 * @throws DuplicateEntityFoundException - when Staff Attendance exists for
	 * specified hearing with given staff assignment
	 */
	public StaffAttendance update(final StaffAttendance staffAttendance,
			final StaffAssignment staff)
			throws DuplicateEntityFoundException{
		if(this.staffAttendanceDao
				.findExcluding(staffAttendance.getHearing(), staff,
						staffAttendance) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		staffAttendance.setStaff(staff);
		staffAttendance.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.staffAttendanceDao.makePersistent(staffAttendance);
	}
	
	/**
	 * Removes a staffAttendance
	 * @param staffAttendance - StaffAttendance to remove
	 */
	public void remove(final StaffAttendance staffAttendance){
		this.staffAttendanceDao.makeTransient(staffAttendance);
	}
	
	/**
	 * Returns a list of StaffAttendance by specified Hearing
	 * @param hearing - Hearing
	 * @return list of StaffAttendance by specified Hearing
	 */
	public List<StaffAttendance> findAllByHearing(final Hearing hearing){
		return this.staffAttendanceDao.findByHearing(hearing);
	}
	
}
