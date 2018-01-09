package omis.dna.service.delegate;

import java.util.Date;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.dna.dao.DnaSampleExemptionDao;
import omis.dna.domain.DnaSampleExemption;
import omis.dna.domain.DnaSampleExemptionReason;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;

/**
 * Delegate for DNA sample exemptions.
 *
 * @author Stephen Abson
 * @author Josh Divine
 * @version 0.0.2 (Dec 12, 2017)
 * @since OMIS 3.0
 */
public class DnaSampleExemptionDelegate {

	/* Instance factories. */
	
	private final InstanceFactory<DnaSampleExemption>
	dnaSampleExemptionInstanceFactory;
	
	/* Data access objects. */
	
	private final DnaSampleExemptionDao dnaSampleExemptionDao;
	
	/* Audit component retriever. */
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/* Constructors. */
	
	/**
	 * Delegate for DNA sample exemptions.
	 * 
	 * @param dnaSampleExemptionInstanceFactory instance factory for DNA sample
	 * exemptions
	 * @param dnaSampleExemptionDao data access object for DNA sample exemptions
	 * @param auditComponentRetriever audit component retriever
	 */
	public DnaSampleExemptionDelegate(
			final InstanceFactory<DnaSampleExemption>
				dnaSampleExemptionInstanceFactory,
			final DnaSampleExemptionDao dnaSampleExemptionDao,
			final AuditComponentRetriever auditComponentRetriever) {
		this.dnaSampleExemptionInstanceFactory
			= dnaSampleExemptionInstanceFactory;
		this.dnaSampleExemptionDao = dnaSampleExemptionDao;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/* Methods. */
	
	/**
	 * Creates DNA sample exemption.
	 * 
	 * @param offender offender
	 * @param reason reason
	 * @param effectiveDate effective date
	 * @return new DNA sample exemption
	 * @throws DuplicateEntityFoundException if DNA sample exemption exists
	 */
	public DnaSampleExemption create(final Offender offender,
			final DnaSampleExemptionReason reason, final Date effectiveDate)
				throws DuplicateEntityFoundException {
		if (this.dnaSampleExemptionDao.find(offender) != null) {
			throw new DuplicateEntityFoundException(
					"DNA sample exemption exists");
		}
		DnaSampleExemption exemption = this.dnaSampleExemptionInstanceFactory
				.createInstance();
		exemption.setOffender(offender);
		exemption.setEffectiveDate(effectiveDate);
		exemption.setReason(reason);
		exemption.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		exemption.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		return this.dnaSampleExemptionDao.makePersistent(exemption);
	}
	
	/**
	 * Updates DNA sample exemption.
	 * 
	 * @param exemption exemption to update
	 * @param reason reason
	 * @param effectiveDate effective date
	 * @return update DNA sample exemption
	 */
	public DnaSampleExemption update(final DnaSampleExemption exemption,
			final DnaSampleExemptionReason reason, final Date effectiveDate) {
		exemption.setEffectiveDate(effectiveDate);
		exemption.setReason(reason);
		exemption.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		return this.dnaSampleExemptionDao.makePersistent(exemption);
	}
	
	/**
	 * Removes DNA sample exemption.
	 * 
	 * @param exemption DNA sample exemption
	 */
	public void remove(final DnaSampleExemption exemption) {
		this.dnaSampleExemptionDao.makeTransient(exemption);
	}
	
	/**
	 * Returns DNA sample exemption.
	 * 
	 * @param offender offender for which to return exemption
	 * @return DNA sample exemption
	 */
	public DnaSampleExemption find(final Offender offender) {
		return this.dnaSampleExemptionDao.find(offender);
	}
}