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
package omis.grievance.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.grievance.dao.GrievanceDao;
import omis.grievance.domain.Grievance;
import omis.grievance.domain.GrievanceComplaintCategory;
import omis.grievance.domain.GrievanceLocation;
import omis.grievance.domain.GrievanceSubject;
import omis.grievance.domain.GrievanceUnit;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;

/**
 * Delegate for grievances.
 *
 * @author Stephen Abson
 * @version 0.0.2 (Oct 2, 2015)
 * @since OMIS 3.0
 */
public class GrievanceDelegate {

	/* Resources. */
	
	private final GrievanceDao grievanceDao;
	
	private final InstanceFactory<Grievance> grievanceInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/* Constructors. */
	
	/**
	 * Instantiates grievance delegate.
	 * 
	 * @param grievanceDao data access object for grievances
	 * @param grievanceInstanceFactory instance factory for grievances
	 * @param auditComponentRetriever audit component retriever
	 */
	public GrievanceDelegate(final GrievanceDao grievanceDao,
			final InstanceFactory<Grievance> grievanceInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.grievanceDao = grievanceDao;
		this.grievanceInstanceFactory = grievanceInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/* Grievance management methods. */
	
	/**
	 * Creates grievance.
	 * 
	 * @param offender offender
	 * @param location location
	 * @param unit unit
	 * @param subject subject
	 * @param complaintCategory complaint category 
	 * @param grievanceNumber grievance number
	 * @param openedDate opened date
	 * @param informalFileDate informal file date
	 * @param description description
	 * @param initialComment initial comment
	 * @param closedDate closed date
	 * @return new grievance
	 * @throws DuplicateEntityFoundException if grievance exists
	 */
	public Grievance create(final Offender offender,
			final GrievanceLocation location, final GrievanceUnit unit,
			final GrievanceSubject subject,
			final GrievanceComplaintCategory complaintCategory,
			final Integer grievanceNumber,
			final Date openedDate, final Date informalFileDate,
			final String description, final String initialComment,
			final Integer transactionOrder,
			final Date closedDate) throws DuplicateEntityFoundException {
		if (this.grievanceDao.find(
				offender, location, unit, openedDate, subject, grievanceNumber)
					!= null) {
			throw new DuplicateEntityFoundException("Grievance exists");
		}
		Grievance grievance = this.grievanceInstanceFactory.createInstance();
		grievance.setOffender(offender);
		grievance.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		this.populateGrievance(grievance, location, unit, subject,
				complaintCategory, grievanceNumber, openedDate,
				informalFileDate, description, initialComment, closedDate);
		return this.grievanceDao.makePersistent(grievance);
	}
	
	/**
	 * Updates grievance.
	 * 
	 * @param grievance grievance to update
	 * @param location location
	 * @param unit unit
	 * @param subject subject
	 * @param complaintCategory complaint category
	 * @param grievanceNumber grievance number
	 * @param openedDate opened date
	 * @param informalFileDate informal file date
	 * @param description description
	 * @param initialComment initial comment
	 * @param closedDate closed date
	 * @return update grievance
	 * @throws DuplicateEntityFoundException if grievance exists
	 */
	public Grievance update(final Grievance grievance,
			final GrievanceLocation location, final GrievanceUnit unit,
			final GrievanceSubject subject,
			final GrievanceComplaintCategory complaintCategory,
			final Integer grievanceNumber,
			final Date openedDate, final Date informalFileDate,
			final String description, final String initialComment,
			final Date closedDate) throws DuplicateEntityFoundException {
		if (this.grievanceDao.findExcluding(grievance.getOffender(), location,
				unit, openedDate, subject, grievanceNumber,
				grievance) != null) {
			throw new DuplicateEntityFoundException("Grievance exists");
		}
		this.populateGrievance(grievance, location, unit, subject,
				complaintCategory, grievanceNumber, openedDate,
				informalFileDate, description, initialComment, closedDate);
		return this.grievanceDao.makePersistent(grievance);
	}
	
	/**
	 * Removes grievance.
	 * 
	 * @param grievance grievance to remove
	 */
	public void remove(final Grievance grievance) {
		this.grievanceDao.makeTransient(grievance);
	}
	
	/**
	 * Returns grievances.
	 * 
	 * @param offender offender
	 * @return grievances by offender
	 */
	public List<Grievance> findByOffender(final Offender offender) {
		return this.grievanceDao.findByOffender(offender);
	}
	
	/**
	 * Returns highest grievance number.
	 * 
	 * <p>Returns zero if no grievances exist.
	 *  
	 * @return highest grievance number; zero if no grievances exist
	 */
	public int findMaxGrievanceNumber() {
		return this.grievanceDao.findMaxGrievanceNumber();
	}
	
	/* Helpers. */
	
	// Populates grievance properties
	private void populateGrievance(final Grievance grievance,
			final GrievanceLocation location, final GrievanceUnit unit,
			final GrievanceSubject subject,
			final GrievanceComplaintCategory complaintCategory,
			final Integer grievanceNumber,
			final Date openedDate, final Date informalFileDate,
			final String description, final String initialComment,
			final Date closedDate) {
		grievance.setLocation(location);
		grievance.setUnit(unit);
		grievance.setSubject(subject);
		grievance.setComplaintCategory(complaintCategory);
		grievance.setGrievanceNumber(grievanceNumber);
		grievance.setOpenedDate(openedDate);
		grievance.setInformalFileDate(informalFileDate);
		grievance.setDescription(description);
		grievance.setInitialComment(initialComment);
		grievance.setClosedDate(closedDate);
		grievance.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
	}

	/**
	 * Closes grievance.
	 * 
	 * @param grievance grievance
	 * @param closedDate closed date
	 * @return closed grievance
	 */
	public Grievance close(final Grievance grievance, final Date closedDate) {
		if (closedDate == null) {
			throw new IllegalArgumentException("Closed date required");
		}
		grievance.setClosedDate(closedDate);
		return this.grievanceDao.makePersistent(grievance);
	}
	
	/**
	 * Reopens grievance.
	 * 
	 * @param grievance grievance
	 * @return reopened grievance
	 */
	public Grievance reopen(final Grievance grievance) {
		grievance.setClosedDate(null);
		return this.grievanceDao.makePersistent(grievance);
	}
}