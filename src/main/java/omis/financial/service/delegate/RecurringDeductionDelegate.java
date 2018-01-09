package omis.financial.service.delegate;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.financial.dao.RecurringDeductionDao;
import omis.financial.domain.RecurringDeduction;
import omis.financial.domain.RecurringDeductionCategory;
import omis.financial.domain.RecurringDeductionFrequency;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;

public class RecurringDeductionDelegate {
	private RecurringDeductionDao recurringDeductionDao;
	private InstanceFactory<RecurringDeduction> 
		recurringDeductionInstanceFactory;
	private AuditComponentRetriever auditComponentRetriever;
	
	/**
	 * Constructor
	 * @param recurringDeductionDao recurringDeductionDao
	 * @param recurringDeductionInstanceFactory recurringDeductionInstanceFactory
	 * @param auditComponentRetriever auditComponentRetriever
	 */
	public RecurringDeductionDelegate(
	final InstanceFactory<RecurringDeduction> recurringDeductionInstanceFactory,
	final RecurringDeductionDao recurringDeductionDao,
	final AuditComponentRetriever auditComponentRetriever) {
		this.recurringDeductionDao = recurringDeductionDao;
		this.recurringDeductionInstanceFactory=recurringDeductionInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Create a recurring deduction
	 * @param offender offender
	 * @param category recurring deduction category
	 * @param reportedDate reported date
	 * @param description description
	 * @param amount amount
	 * @param frequency frequency
	 * @return the created recurring deduction
	 */
	public RecurringDeduction create(final Offender offender, 
		final RecurringDeductionCategory category, final Date reportedDate, 
		final String description, final BigDecimal amount, 
		final RecurringDeductionFrequency frequency) 
		throws DuplicateEntityFoundException{
		if (this.recurringDeductionDao.find(offender, category, description, 
				reportedDate, amount, frequency) != null) {
			throw new DuplicateEntityFoundException("Recurring deduction already"
					+ " exists");
		}
		RecurringDeduction recurringDeduction 
			= this.recurringDeductionInstanceFactory.createInstance();
		recurringDeduction.setAmount(amount);
		recurringDeduction.setCategory(category);
		recurringDeduction.setCreationSignature(
			new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
		recurringDeduction.setUpdateSignature(
			new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
		recurringDeduction.setDescription(description);
		recurringDeduction.setFrequency(frequency);
		recurringDeduction.setOffender(offender);
		recurringDeduction.setReportedDate(reportedDate);
		return this.recurringDeductionDao.makePersistent(recurringDeduction);
	}
	
	/**
	 * Update a recurring deduction
	 * @param category recurring deduction category
	 * @param reportedDate reported date
	 * @param description description
	 * @param amount amount
	 * @param frequency frequency
	 * @return the updated recurring deduction
	 */
	public RecurringDeduction update(final RecurringDeductionCategory category, 
		final Date reportedDate, final String description, 
		final BigDecimal amount, final RecurringDeductionFrequency frequency, 
		final RecurringDeduction recurringDeduction) 
		throws DuplicateEntityFoundException{
		if (this.recurringDeductionDao.findExcluding(category, description, 
				reportedDate, amount, frequency, recurringDeduction) != null) {
			throw new DuplicateEntityFoundException("Recurring deduction already"
					+ " exists");
		}
		recurringDeduction.setAmount(amount);
		recurringDeduction.setCategory(category);
		recurringDeduction.setUpdateSignature(
			new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
		recurringDeduction.setDescription(description);
		recurringDeduction.setFrequency(frequency);
		recurringDeduction.setOffender(recurringDeduction.getOffender());
		recurringDeduction.setReportedDate(reportedDate);
		return this.recurringDeductionDao.makePersistent(recurringDeduction);
	}
	
	public List<RecurringDeduction> findAll(final Offender offender) {
		return this.recurringDeductionDao.findAll(offender);
	}
	
	/**
	 * Removes the specified recurring deduction
	 * @param recurringDeduction recurring deduction
	 */
	public void remove(final RecurringDeduction recurringDeduction) {
		this.recurringDeductionDao.makeTransient(recurringDeduction);
	}
}