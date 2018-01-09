package omis.hearing.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.hearing.dao.HearingDao;
import omis.hearing.domain.Hearing;
import omis.hearing.domain.HearingCategory;
import omis.hearing.domain.component.Subject;
import omis.instance.factory.InstanceFactory;
import omis.location.domain.Location;
import omis.offender.domain.Offender;
import omis.staff.domain.StaffAssignment;

/**
 * HearingDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.1 (Apr 17, 2017)
 *@since OMIS 3.0
 *
 */
public class HearingDelegate {
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"Hearing exists with specified offender, location, date, and officer.";
	
	private final HearingDao hearingDao;
	
	private final InstanceFactory<Hearing> 
		hearingInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for HearingDelegate
	 * @param hearingDao
	 * @param hearingInstanceFactory
	 * @param auditComponentRetriever
	 */
	public HearingDelegate(
			final HearingDao hearingDao,
			final InstanceFactory<Hearing> 
				hearingInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.hearingDao = hearingDao;
		this.hearingInstanceFactory = hearingInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a hearing with the specified parameters
	 * @param location
	 * @param offender
	 * @param inAttendance - Boolean
	 * @param date
	 * @param category - HearingCategory
	 * @param status - HearingStatusCategory
	 * @param officer - StaffAssignment
	 * @return Newly Created Hearing
	 * @throws DuplicateEntityFoundException - when Hearing exists with
	 * specified offender, location, date, and officer
	 */
	public Hearing create(final Location location, final Offender offender,
			final Boolean inAttendance, final Date date,
			final HearingCategory category,
			final StaffAssignment officer)
			throws DuplicateEntityFoundException{
		if(this.hearingDao
				.find(location, offender, date, officer, category) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		Hearing hearing = 
				this.hearingInstanceFactory.createInstance();
		
		Subject subject = new Subject();
		subject.setInAttendance(inAttendance);
		subject.setOffender(offender);
		
		hearing.setLocation(location);
		hearing.setSubject(subject);
		hearing.setCategory(category);
		hearing.setDate(date);
		hearing.setOfficer(officer);
		hearing.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		hearing.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.hearingDao.makePersistent(hearing);
	}
	
	/**
	 * Updates a hearing with the specified parameters
	 * @param hearing - Hearing to update
	 * @param location
	 * @param offender
	 * @param inAttendance - Boolean
	 * @param date
	 * @param category - HearingCategory
	 * @param officer - StaffAssignment
	 * @return Updated Hearing
	 * @throws DuplicateEntityFoundException - when Hearing exists with
	 * specified offender, location, date, and officer
	 */
	public Hearing update(final Hearing hearing, final Location location,
			final Boolean inAttendance, final Date date,
			final HearingCategory category,
			final StaffAssignment officer)
			throws DuplicateEntityFoundException{
		if(this.hearingDao.findExcluding(
				location, hearing.getSubject().getOffender(), date, officer,
				category, hearing) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		 
		Subject subject = new Subject();
		subject.setInAttendance(inAttendance);
		subject.setOffender(hearing.getSubject().getOffender());
		
		hearing.setLocation(location);
		hearing.setSubject(subject);
		hearing.setCategory(category);
		hearing.setDate(date);
		hearing.setOfficer(officer);
		hearing.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.hearingDao.makePersistent(hearing);
	}
	
	/**
	 * Removes a hearing
	 * @param hearing - Hearing to remove
	 */
	public void remove(final Hearing hearing){
		this.hearingDao.makeTransient(hearing);
	}
	
	/**
	 * Returns a list of Hearings with specified offender
	 * @param offender
	 * @return List of Hearings with specified offender
	 */
	public List<Hearing> findByOffender(final Offender offender){
		return this.hearingDao.findByOffender(offender);
	}
	
	
}
