package omis.specialneed.service.delegate;

import java.util.Date;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;
import omis.specialneed.dao.SpecialNeedDao;
import omis.specialneed.domain.SpecialNeed;
import omis.specialneed.domain.SpecialNeedCategory;
import omis.specialneed.domain.SpecialNeedClassification;
import omis.specialneed.domain.SpecialNeedSource;

/**
 * Special need service implementation delegate.
 *
 * @author Sheronda Vaughn
 * @author Josh Divine
 * @version 0.1.1 (Jul 20, 2017)
 * @since OMIS 3.0
 */
public class SpecialNeedDelegate {

	/* Data access objects. */
	
	private SpecialNeedDao specialNeedDao;
	
	/* Instance factories. */
	
	private InstanceFactory<SpecialNeed> specialNeedInstanceFactory;
	
	/* Component Retrievers. */
	
	private AuditComponentRetriever auditComponentRetriever;
	
	/** Instantiates an implementation of special need delegate with 
	 * the specified date access object and instance factory.
	 * 
	 * @param specialNeedDao special need data access object
	 * @param auditComponentRetriever audit component retriever
	 * @param specialNeedInstanceFactory special need instance factory
	 */
	public SpecialNeedDelegate(final SpecialNeedDao specialNeedDao,
			final AuditComponentRetriever auditComponentRetriever,
			final InstanceFactory<SpecialNeed> specialNeedInstanceFactory) {
		this.specialNeedDao = specialNeedDao;
		this.auditComponentRetriever = auditComponentRetriever;
		this.specialNeedInstanceFactory = specialNeedInstanceFactory; 
	}
	
	/**
	 * Creates a new special need.
	 *
	 *
	 * @param comment comment
	 * @param startDate start date
	 * @param endDate end date
	 * @param classification classification
	 * @param category category
	 * @param source source
	 * @param sourceComment source comment
	 * @param offender offender
	 * @return new special need
	 * @throws DuplicateEntityFoundException
	 */
	public SpecialNeed create(final String comment, final Date startDate,
			final Date endDate, final SpecialNeedClassification classification, 
			final SpecialNeedCategory category, final SpecialNeedSource source, 
			final String sourceComment, final Offender offender)
		throws DuplicateEntityFoundException {
		if (this.specialNeedDao.find(startDate, classification, category, 
				source, offender) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate special need found");
		}
		SpecialNeed specialNeed = this.specialNeedInstanceFactory
				.createInstance();
		specialNeed.setOffender(offender);
		specialNeed.setClassification(classification);
		specialNeed.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		this.populateSpecialNeed(specialNeed, comment,
				new DateRange(startDate, endDate), category, source, 
				sourceComment);
		return this.specialNeedDao.makePersistent(specialNeed);
	}
	
	/**
	 * Updates a specified special need.
	 *
	 *
	 * @param specialNeed special need
	 * @param comment comment
	 * @param startDate start date
	 * @param endDate end date
	 * @param category category
	 * @param source source
	 * @param sourceComment source comment
	 * @return updated special need
	 * @throws DuplicateEntityFoundException
	 */
	public SpecialNeed update(final SpecialNeed specialNeed,
			final String comment, final Date startDate, final Date endDate,
			final SpecialNeedCategory category,
			final SpecialNeedSource source, final String sourceComment)
			throws DuplicateEntityFoundException {
		if (this.specialNeedDao.findExcluding(
				specialNeed, startDate, category, specialNeed.getClassification(),
				source, specialNeed.getOffender()) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate special need found");
		}
		this.populateSpecialNeed(specialNeed, comment,
				new DateRange(startDate, endDate), category, source,
				sourceComment);
		return this.specialNeedDao.makePersistent(specialNeed);
	}
	
	/**
	 * Removes a specified special need.
	 *
	 *
	 * @param specialNeed special need
	 */
	public void remove(final SpecialNeed specialNeed) {
		this.specialNeedDao.makeTransient(specialNeed);
	}

	/* Helper methods. */
	
	/*
	 * Populates the specified special need.
	 * 
	 * @param specialNeed
	 * @param comment
	 * @param dateRange
	 * @param category
	 * @param source
	 * @return populated special need
	 */
	private SpecialNeed populateSpecialNeed(final SpecialNeed specialNeed,
			final String comment, final DateRange dateRange,
			final SpecialNeedCategory category,
			final SpecialNeedSource source,
			final String sourceComment) {
		specialNeed.setCategory(category);
		specialNeed.setComment(comment);
		specialNeed.setDateRange(dateRange);
		specialNeed.setSource(source);
		specialNeed.setSourceComment(sourceComment);
		specialNeed.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		return specialNeed;
	}		
}