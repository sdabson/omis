package omis.separationneed.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;
import omis.person.domain.Person;
import omis.relationship.domain.Relationship;
import omis.separationneed.dao.SeparationNeedDao;
import omis.separationneed.domain.SeparationNeed;
import omis.separationneed.domain.SeparationNeedRemoval;
import omis.separationneed.domain.SeparationNeedRemovalReason;

public class SeparationNeedDelegate {

	private final SeparationNeedDao separationNeedDao;
	
	private final InstanceFactory<SeparationNeed> separationNeedInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	public SeparationNeedDelegate(final SeparationNeedDao separationNeedDao,
			final InstanceFactory<SeparationNeed> separationNeedInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.separationNeedDao = separationNeedDao;
		this.separationNeedInstanceFactory = separationNeedInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Returns all separation needs.
	 * 
	 * @return list of separation needs
	 */
	public List<SeparationNeed> findAll() {
		return this.separationNeedDao.findAll();
	}
	
	/**
	 * Returns a list of separation needs with the specified offender.
	 * @param offender offender
	 * @return list of separation needs
	 */
	public List<SeparationNeed> findByOffender(final Offender offender) {
		return this.separationNeedDao.findByOffender(offender);
	}

	/**
	 * Returns a list of separation needs with the specified target offender.
	 * @param offender offender
	 * @return list of separation needs
	 */
	public List<SeparationNeed> findByTargetOffender(final Offender offender) {
		return this.separationNeedDao.findByTargetOffender(offender);
	}

	/**
	 * Creates a new separation need.
	 * 
	 * @param relationship relationship
	 * @param date date
	 * @param creationComment creation comment
	 * @param reportingStaff reporting staff
	 * @param removalDate removal date
	 * @param removalReason separation need removal reason
	 * @param removal comment
	 * @return newly created separation need
	 * @throws DuplicateEntityFoundException duplicate entity found exception
	 * @throws DateConflictException date conflict exception
	 */
	public SeparationNeed create(final Relationship relationship,
			final Date date, final String creationComment, 
			final Person reportingStaff, final Date removalDate,
			final SeparationNeedRemovalReason removalReason, 
			final String removalComment) throws DuplicateEntityFoundException, 
			DateConflictException {
		if (this.separationNeedDao.find(relationship, date) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate separation need found");
		}
		if (this.separationNeedDao.findInDateRange(
				relationship, date, removalDate).size() > 0) {
			throw new DateConflictException(
			"Conflicting separation need within date and removal date found");
		}
		SeparationNeed separationNeed = 
				this.separationNeedInstanceFactory.createInstance();
		separationNeed.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
		populateSeparationNeed(separationNeed, relationship, date, 
				creationComment, reportingStaff, removalDate, removalReason, 
				removalComment);
		return this.separationNeedDao.makePersistent(separationNeed);
	}
	
	/**
	 * Updates the specified separation need.
	 * 
	 * @param separationNeed separation need
	 * @param relationship relationship
	 * @param date date
	 * @param creationComment creation comment
	 * @param reportingStaff reporting staff
	 * @param removalDate removal date
	 * @param removalReason removal reason
	 * @param removalComment removal comment
	 * @return updated separation need
	 * @throws DuplicateEntityFoundException thrown when a duplicate separation
	 * need is found
	 * @throws DateConflictException thrown when a separation need, with the
	 * same relationship, exists in a conflicting range of dates
	 */
	public SeparationNeed update(final SeparationNeed separationNeed,
			final Relationship relationship, final Date date,
			final String creationComment, final Person reportingStaff,
			final Date removalDate, 
			final SeparationNeedRemovalReason removalReason,
			final String removalComment) throws DuplicateEntityFoundException, 
			DateConflictException {
		if (this.separationNeedDao.findExcluding(
				separationNeed.getRelationship(), date,
				separationNeed) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate separation need found");
		}
		if (this.separationNeedDao.findInDateRangeExcluding(
				relationship, date, removalDate, separationNeed).size() > 0) {
			throw new DateConflictException("Conflicting separation need "
					+ "within date and removal date found");
		}
		populateSeparationNeed(separationNeed, relationship, date, 
				creationComment, reportingStaff, removalDate, removalReason, 
				removalComment);
		return this.separationNeedDao.makePersistent(separationNeed);
	}

	/**
	 * Removes the specified separation need.
	 * 
	 * @param separationNeed separation need
	 */
	public void remove(final SeparationNeed separationNeed) {
		this.separationNeedDao.makeTransient(separationNeed);
	}
	
	/**
	 * Associates a new relationship to a separation need.
	 * 
	 * @param separationNeed separation need
	 * @param relationship relationship
	 * @return separation need
	 */
	public SeparationNeed associate(final SeparationNeed separationNeed, 
			final Relationship relationship) {
		separationNeed.setRelationship(relationship);
		return this.separationNeedDao.makePersistent(separationNeed);
	}
	
	// Populates a separation need
	private void populateSeparationNeed(final SeparationNeed separationNeed, 
			final Relationship relationship, final Date date, 
			final String creationComment, final Person reportingStaff, 
			final Date removalDate, 
			final SeparationNeedRemovalReason removalReason, 
			final String removalComment) {
		separationNeed.setCreationComment(creationComment);
		separationNeed.setDate(date);
		separationNeed.setRelationship(relationship);
		separationNeed.setReportingStaff(reportingStaff);
		SeparationNeedRemoval removal = new SeparationNeedRemoval(removalDate,
				removalComment, removalReason);
		separationNeed.setRemoval(removal);
		separationNeed.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
	}
}
