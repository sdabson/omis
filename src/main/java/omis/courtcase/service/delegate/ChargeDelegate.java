package omis.courtcase.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.courtcase.dao.ChargeDao;
import omis.courtcase.domain.Charge;
import omis.courtcase.domain.CourtCase;
import omis.courtcase.exception.ChargeExistsException;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.offense.domain.Offense;

/**
 * Delegate for managing charges. 
 *
 * @author Stephen Abson
 * @author Ryan Johns
 * @author Josh Divine
 * @version 0.0.4 (Aug 11, 2017)
 * @since OMIS 3.0
 */
public class ChargeDelegate {

	private final ChargeDao chargeDao;
	
	private final InstanceFactory<Charge> chargeInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/**
	 * Instantiates a delegate for managing charges.
	 * 
	 * @param chargeDao data access object for charges
	 * @param chargeInstanceFactory instance factory for charges
	 * @param offenseDao data access object for charges
	 * @param auditComponentRetriever audit component retriever
	 */
	public ChargeDelegate(
			final ChargeDao chargeDao,
			final InstanceFactory<Charge> chargeInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.chargeDao = chargeDao;
		this.chargeInstanceFactory = chargeInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a charge for court case.
	 * 
	 * @param courtCase court case
	 * @param offense offense of charge
	 * @param date date
	 * @param fileDate file date
	 * @param counts count
	 * @return charge
	 * @throws DuplicateEntityFoundException if the charge exists
	 */
	public Charge create(
			final CourtCase courtCase, final Offense offense, final Date date,
			final Date fileDate, final Integer counts)
					throws ChargeExistsException {
		if (this.chargeDao.find(courtCase, offense, counts) != null) {
			throw new ChargeExistsException("Charge exists");
		}
		Charge charge = this.chargeInstanceFactory.createInstance();
		charge.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		charge.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		charge.setCourtCase(courtCase);
		charge.setOffense(offense);
		charge.setDate(date);
		charge.setFileDate(fileDate);
		charge.setCounts(counts);
		return this.chargeDao.makePersistent(charge);
	}

	/** 
	 * Updates charge.
	 * 
	 * @param charge charge
	 * @param offense offense
	 * @param date date
	 * @param fileDate file date
	 * @param counts counts 
	 * @throws ChargeExistsException if charge exists
	 */
	public Charge update(final Charge charge, final Offense offense,
			final Date date, final Date fileDate, final Integer counts) 
					throws ChargeExistsException {
		if (this.chargeDao.findExcluding(charge.getCourtCase(), offense, 
				counts, charge) != null) {
			throw new ChargeExistsException("Charge exists");
		}
		charge.setOffense(offense);
		charge.setDate(date);
		charge.setFileDate(fileDate);
		charge.setCounts(counts);
		return this.chargeDao.makePersistent(charge);
	}
	
	/**
	 * Removes charge.
	 * 
	 * @param charge charge
	 */
	public void remove(final Charge charge) {
		this.chargeDao.makeTransient(charge);
	}

	/**
	 * Returns charges by court case.
	 * 
	 * @param courtCase court case
	 * @return charges by court case
	 */
	public List<Charge> findByCourtCase(final CourtCase courtCase) {
		return this.chargeDao.findByCourtCase(courtCase);
	}
}