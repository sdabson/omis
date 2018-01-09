package omis.conviction.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.conviction.dao.ConvictionDao;
import omis.conviction.domain.Conviction;
import omis.conviction.domain.OffenseSeverity;
import omis.conviction.domain.component.ConvictionFlags;
import omis.conviction.exception.ConvictionExistsException;
import omis.courtcase.domain.CourtCase;
import omis.instance.factory.InstanceFactory;
import omis.offense.domain.Offense;

/**
 * Delegate to manage convictions.
 *
 * @author Stephen Abson
 * @author Josh Divine
 * @version 0.1.2 (May 2, 2017)
 * @since OMIS 3.0
 */
public class ConvictionDelegate {
	
	private final ConvictionDao convictionDao;
	
	private final InstanceFactory<Conviction> convictionInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/**
	 * Instantiates delegate to manage convictions.
	 * 
	 * @param convictionDao data access object for convictions
	 * @param convictionInstanceFactory instance factory for convictions
	 * @param auditComponentRetriever audit component retriever
	 */
	public ConvictionDelegate(
			final ConvictionDao convictionDao,
			final InstanceFactory<Conviction> convictionInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.convictionDao = convictionDao;
		this.convictionInstanceFactory = convictionInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Convicts an offense for a court case.
	 * 
	 * @param courtCase court case
	 * @param offense offense
	 * @param severity severity
	 * @param date date
	 * @param counts counts
	 * @param flags conviction flags
	 * @return conviction
	 * @throws ConvictionExistsException thrown if conviction exists
	 */
	public Conviction convict(final CourtCase courtCase, final Offense offense,
			final OffenseSeverity severity, final Date date, 
			final Integer counts, final ConvictionFlags flags) 
					throws ConvictionExistsException {
		return this.create(courtCase, severity, offense, date, counts, flags);
	}
	
	/**
	 * Returns all convictions for the specified court case.
	 * 
	 * @param courtCase court case
	 * @return convictions for court case
	 */
	public List<Conviction> findByCourtCase(CourtCase courtCase) {
		return this.convictionDao.findByCourtCase(courtCase);
	}
	
	/**
	 * Returns whether the court case has existing convictions.
	 * 
	 * @param courtCase court case.
	 * @return court case
	 */
	public boolean hasConvictions(final CourtCase courtCase) {
		return this.convictionDao.findByCourtCase(courtCase).size() > 0;
	}
	
	/**
	 * Creates a new conviction.
	 * 
	 * @param courtCase court case
	 * @param severity offense severity
	 * @param offense offense
	 * @param date date
	 * @param counts counts
	 * @param flags conviction flags
	 * @return conviction
	 * @throws ConvictionExistsException if conviction exists
	 */
	public Conviction create(final CourtCase courtCase,
			final OffenseSeverity severity, final Offense offense, 
			final Date date, final Integer counts, 
			final ConvictionFlags flags) 
					throws ConvictionExistsException {
		final Conviction conviction = this.convictionInstanceFactory.createInstance();
		conviction.setCourtCase(courtCase);
		conviction.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		this.populateConviction(conviction, severity, offense, date, counts, 
				flags);
		conviction.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		return this.convictionDao.makePersistent(conviction);
	}
	
	/**
	 * Updates a conviction.
	 * 
	 * @param conviction conviction
	 * @param charge charge
	 * @param severity offense severity
	 * @param offense offense
	 * @param date date
	 * @param counts counts
	 * @param flags conviction flags
	 * @return conviction
	 * @throws ConvictionExistsException if conviction exists
	 */
	public Conviction update(final Conviction conviction, 
			final OffenseSeverity severity, final Offense offense, 
			final Date date, final Integer counts,
			final ConvictionFlags flags) 
					throws ConvictionExistsException {
		this.populateConviction(conviction, severity, offense, date, counts, 
				flags);
		conviction.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		return this.convictionDao.makePersistent(conviction);
	}

	/**
	 * Removes an existing conviction.
	 * 
	 * @param conviction conviction
	 */
	public void remove(Conviction conviction) {
		this.convictionDao.makeTransient(conviction);
	}
	
	/* Helper methods. */
	
	// Populates conviction
	private Conviction populateConviction(final Conviction conviction, 
			final OffenseSeverity severity, final Offense offense, 
			final Date date, final Integer counts, 
			final ConvictionFlags flags) {
		conviction.setSeverity(severity);
		conviction.setOffense(offense);
		conviction.setDate(date);
		conviction.setCounts(counts);
		conviction.setFlags(flags);
		return conviction;
	}
}