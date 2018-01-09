package omis.military.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.datatype.DateRange;
import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.military.dao.MilitaryServiceTermDao;
import omis.military.domain.MilitaryBranch;
import omis.military.domain.MilitaryDischargeStatus;
import omis.military.domain.MilitaryServiceTerm;
import omis.offender.domain.Offender;

/**
 * Military service term delegate.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (Jul 8, 2016)
 * @since OMIS 3.0
 */

public class MilitaryServiceTermDelegate {

	/* Instance factories. */
	
	private final InstanceFactory<MilitaryServiceTerm>
	militaryServiceTermInstanceFactory;
	
	/* DAOs. */
	
	private final MilitaryServiceTermDao militaryServiceTermDao;
	
	/* Audit component retriever. */
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/* Constructor. */
	
	/**
	 * Instantiates delegate for service terms.
	 * 
	 * @param militaryServiceTermInstanceFactory instance factory for
	 * service terms.
	 * @param militaryServiceTermDao data access object for service terms.
	 */
	public MilitaryServiceTermDelegate(
			final InstanceFactory<MilitaryServiceTerm>
				militaryServiceTermInstanceFactory,
			final MilitaryServiceTermDao militaryServiceTermDao,
			final AuditComponentRetriever auditComponentRetriever) {
		this.militaryServiceTermInstanceFactory
			= militaryServiceTermInstanceFactory;
		this.militaryServiceTermDao = militaryServiceTermDao;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	
	/** Creates a military service term.
	 * @param MilitaryBranch - branch.
	 * @param MilitaryDischargeStatus - dischargeStatus.
	 * @param Date - startDate.
	 * @param Date - endDate.
	 * @param  - .
	 * @return MilitaryServiceTerm. 
	 * @throws DuplicateEntityFoundException - when service term already exists.
	 *  */
	public MilitaryServiceTerm create(final Offender offender, 
			final MilitaryBranch branch,
			final MilitaryDischargeStatus dischargeStatus, 
			final Date startDate, final Date endDate) 
		throws DuplicateEntityFoundException, DateConflictException {
		if (this.militaryServiceTermDao.find(offender, startDate, branch) 
				!= null) {
			throw new DuplicateEntityFoundException(
					"Duplicate military service term found");
		}
		if (this.militaryServiceTermDao
				.findWithinDateRange(offender, startDate, endDate).size() > 0) {
			throw new DateConflictException(
					"Conflicting military service term date range found");
		}
		MilitaryServiceTerm serviceTerm = 
				this.militaryServiceTermInstanceFactory.createInstance();
		serviceTerm.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		serviceTerm.setOffender(offender);
		this.populateMilitaryServiceTerm(serviceTerm, branch, dischargeStatus,
				startDate, endDate);
		return this.militaryServiceTermDao.makePersistent(serviceTerm);
	}
	 
	/** Updates a military service term.
	 * @param MilitaryBranch - branch.
	 * @param MilitaryDischargeStatus - dischargeStatus.
	 * @param Date - startDate.
	 * @param Date - endDate.
	 * @param  - .
	 * @return Updates MilitaryServiceTerm.
	 * @throws DuplicateEntityFoundException - when service term already exists.
	 *  */
	public MilitaryServiceTerm update(final MilitaryServiceTerm militaryServiceTerm,
			final MilitaryBranch branch,
			final MilitaryDischargeStatus dischargeStatus,
			final Date startDate, final Date endDate) 
		throws DuplicateEntityFoundException, DateConflictException {
		if (this.militaryServiceTermDao.findExcluding(militaryServiceTerm, 
				militaryServiceTerm.getOffender(),
				startDate, branch) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate military service term found");
		}
		if (this.militaryServiceTermDao
				.findWithinDateRangeExcluding(militaryServiceTerm.getOffender(), 
						startDate, endDate, militaryServiceTerm)
				.size() > 0) {
			throw new DateConflictException(
					"Conflicting military service term date range found");
		}
		this.populateMilitaryServiceTerm(militaryServiceTerm, branch, dischargeStatus,
				startDate, endDate);
		return this.militaryServiceTermDao.makePersistent(militaryServiceTerm);
	}
	
	/** Removes a military service term.
	 * @param MilitaryBranch - branch.
	 * @param MilitaryDischargeStatus - dischargeStatus.
	 * @param Date - startDate.
	 * @param Date - endDate.
	 * @param  - .
	 * @return Removes MilitaryServiceTerm.
	 * @throws DuplicateEntityFoundException - when service term already exists. 
	 * */
	public void remove(final MilitaryServiceTerm militaryServiceTerm) {
		this.militaryServiceTermDao.makeTransient(militaryServiceTerm);
	
	}
		/*
		 * Populates the specified military service term.
		 * 
		 * @param serviceTerm military service term
		 * @param branch military branch
		 * @param dischargeStatus military discharge status
		 * @param startDate start date
		 * @param endDate end date
		 * @return populated military service term
		 */
		private MilitaryServiceTerm populateMilitaryServiceTerm(
				final MilitaryServiceTerm militaryServiceTerm, final MilitaryBranch branch, 
				final MilitaryDischargeStatus dischargeStatus, final Date startDate,
				final Date endDate) {
			militaryServiceTerm.setBranch(branch);
			militaryServiceTerm.setDischargeStatus(dischargeStatus);
			militaryServiceTerm.setDateRange(new DateRange(startDate, endDate));
			militaryServiceTerm.setUpdateSignature(new UpdateSignature(
					this.auditComponentRetriever.retrieveUserAccount(),
					this.auditComponentRetriever.retrieveDate()));
			return militaryServiceTerm;
		}
		
		public List<MilitaryServiceTerm> findByOffender(Offender offender) {
			return this.militaryServiceTermDao.findByOffender(offender);
			
		}
	 
}
	