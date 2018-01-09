package omis.tierdesignation.service.delegate;

import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;
import omis.tierdesignation.dao.OffenderTierDesignationDao;
import omis.tierdesignation.domain.OffenderTierDesignation;
import omis.tierdesignation.domain.TierChangeReason;
import omis.tierdesignation.domain.TierLevel;
import omis.tierdesignation.domain.TierSource;

/**
 * Delegate for offender tier designations.
 *
 * @author Josh Divine
 * @version 0.0.1 (Jul 19, 2017)
 * @since OMIS 3.0
 */
public class OffenderTierDesignationDelegate {

	/* Instance factories. */
	
	private final InstanceFactory<OffenderTierDesignation>
	offenderTierDesignationInstanceFactory;

	/* Data access objects. */
	
	private final OffenderTierDesignationDao offenderTierDesignationDao;
	
	/* Audit component retriever. */
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/* Constructors. */
	
	/**
	 * Instantiates delegate for offender tier designations.
	 * 
	 * @param offenderTierDesignationInstanceFactory instance factory for
	 * offender tier designations
	 * @param offenderTierDesignationDao data access object for offender tier 
	 * designations
	 * @param auditComponentRetriever audit component retriever
	 */
	public OffenderTierDesignationDelegate(
			final InstanceFactory<OffenderTierDesignation>
			offenderTierDesignationInstanceFactory,
			final OffenderTierDesignationDao offenderTierDesignationDao,
			final AuditComponentRetriever auditComponentRetriever) {
		this.offenderTierDesignationDao = offenderTierDesignationDao;
		this.offenderTierDesignationInstanceFactory = 
				offenderTierDesignationInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/* Methods. */
	
	/**
	 * Finds tier designations for the offender.
	 * 
	 * @param offender offender
	 * @return tier designations for offender
	 */
	public List<OffenderTierDesignation> findByOffender(
			final Offender offender) {
		return this.offenderTierDesignationDao.findByOffender(offender);
	}

	/**
	 * Creates an offender tier designation.
	 * 
	 * @param offender offender
	 * @param level level
	 * @param source source
	 * @param changeReason change reason
	 * @param dateRange date range
	 * @param comment comment
	 * @return saved offender tier designation
	 * @throws DuplicateEntityFoundException if tier designation already exists
	 */
	public OffenderTierDesignation create(final Offender offender,
			final TierLevel level, final TierSource source,
			final TierChangeReason changeReason,
			final DateRange dateRange, final String comment)
					 throws DuplicateEntityFoundException {
		if (this.offenderTierDesignationDao
				.find(offender, level, source, changeReason, dateRange)
					!= null) {
			throw new DuplicateEntityFoundException("Duplicate entity found");
		}
		OffenderTierDesignation offenderTierDesignation
			= this.offenderTierDesignationInstanceFactory.createInstance();
		offenderTierDesignation.setOffender(offender);
		offenderTierDesignation.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		this.populateTierDesignation(offenderTierDesignation, level, source,
				changeReason, dateRange, comment);
		return this.offenderTierDesignationDao
				.makePersistent(offenderTierDesignation);
	}
	
	/**
	 * Updates an offender tier designation.
	 * 
	 * @param offenderTierDesignation offender tier designation
	 * @param level level
	 * @param source source
	 * @param changeReason change reason
	 * @param dateRange date range
	 * @param comment comment
	 * @return updated offender tier designation
	 * @throws DuplicateEntityFoundException if tier designation already exists
	 */
	public OffenderTierDesignation update(
			final OffenderTierDesignation offenderTierDesignation,
			final TierLevel level, final TierSource source,
			final TierChangeReason changeReason,
			final DateRange dateRange, final String comment)
					 throws DuplicateEntityFoundException {
		if (this.offenderTierDesignationDao
				.findExcluding(offenderTierDesignation.getOffender(), level,
						source, changeReason, dateRange,
						offenderTierDesignation) != null) {
			throw new DuplicateEntityFoundException("Duplicate entity found");
		}
		this.populateTierDesignation(offenderTierDesignation, level, source,
				changeReason, dateRange, comment);
		return this.offenderTierDesignationDao
				.makePersistent(offenderTierDesignation);
	}
	
	/**
	 * Removes the offender tier designation.
	 * 
	 * @param offenderTierDesignation offender tier designation to remove
	 */
	public void remove(
			final OffenderTierDesignation offenderTierDesignation) {
		this.offenderTierDesignationDao.makeTransient(offenderTierDesignation);
	}
	
	// Populates offender tier designation
	private void populateTierDesignation(
			final OffenderTierDesignation offenderTierDesignation,
			final TierLevel level, final TierSource source,
			final TierChangeReason changeReason,
			final DateRange dateRange, final String comment) {
		offenderTierDesignation.setLevel(level);
		offenderTierDesignation.setSource(source);
		offenderTierDesignation.setChangeReason(changeReason);
		offenderTierDesignation.setDateRange(DateRange.deepCopy(dateRange));
		offenderTierDesignation.setComment(comment);
		offenderTierDesignation.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
	}
}
