package omis.financial.service.delegate;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.financial.dao.FinancialLiabilityDao;
import omis.financial.domain.FinancialLiability;
import omis.financial.domain.FinancialLiabilityCategory;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;

/**
 * Financial Liability Delegate.
 * 
 *@author Josh Divine 
 *@version 0.1.0 (November 21, 2016)
 *@since OMIS 3.0
 *
 */
public class FinancialLiabilityDelegate {
	private static final String DUPLICATE_ENTITY_FOUND_MSG 
		= "Financial liability already exists for this offender.";
	
	private FinancialLiabilityDao financialLiabilityDao;
	private InstanceFactory<FinancialLiability> 
		financialLiabilityInstanceFactory;
	private AuditComponentRetriever auditComponentRetriever;
	
	/**
	 * Constructor
	 * @param financialLiabilityInstanceFactory
	 * @param financialLiabilityDao
	 * @param auditComponentRetriever
	 */
	public FinancialLiabilityDelegate(final InstanceFactory<FinancialLiability> 
			financialLiabilityInstanceFactory,
			final FinancialLiabilityDao financialLiabilityDao,
			final AuditComponentRetriever auditComponentRetriever) {
		this.financialLiabilityInstanceFactory 
			= financialLiabilityInstanceFactory;
		this.financialLiabilityDao = financialLiabilityDao;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a new financial liability
	 * @param offender offender
	 * @param category financial liability category
	 * @param description description
	 * @param amount amount
	 * @param reportedDate reported date
	 * @return Financial liability
	 * @throws DuplicateEntityFoundException
	 */
	public FinancialLiability create(final Offender offender, 
			final FinancialLiabilityCategory category, final String description, 
			final BigDecimal amount, final Date reportedDate) 
					throws DuplicateEntityFoundException {
		if (this.financialLiabilityDao.find(offender, category, description, 
				reportedDate, amount) != null) {
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		FinancialLiability financialLiability 
			= this.financialLiabilityInstanceFactory.createInstance();
		financialLiability.setAmount(amount);
		financialLiability.setCategory(category);
		financialLiability.setDescription(description);
		financialLiability.setOffender(offender);
		financialLiability.setReportedDate(reportedDate);
		financialLiability.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		financialLiability.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		
		return this.financialLiabilityDao.makePersistent(financialLiability);
	}
	
	/**
	 * Updates and existing financial liability
	 * @param financialLiability financial liability
	 * @param offender offender
	 * @param category financial liability category
	 * @param description description
	 * @param amount amount
	 * @param reportedDate reported date
	 * @return Financial liability
	 * @throws DuplicateEntityFoundException
	 */
	public FinancialLiability update(final FinancialLiability 
			financialLiability, final Offender offender, 
			final FinancialLiabilityCategory category, final String description, 
			final BigDecimal amount, final Date reportedDate) 
					throws DuplicateEntityFoundException {
		if (this.financialLiabilityDao.findExcluding(offender, category, 
				description, reportedDate, amount, financialLiability) != null) 
		{
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		financialLiability.setAmount(amount);
		financialLiability.setCategory(category);
		financialLiability.setDescription(description);
		financialLiability.setOffender(offender);
		financialLiability.setReportedDate(reportedDate);
		financialLiability.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		
		return this.financialLiabilityDao.makePersistent(financialLiability);
	}
	
	/**
	 * Removes the specified financial liability
	 * @param financialLiability financial liability
	 */
	public void remove(final FinancialLiability financialLiability) {
		this.financialLiabilityDao.makeTransient(financialLiability);
	}
	
	/**
	 * Finds all financial liabilities for the specified offender
	 * @param offender offender
	 * @return List of financial liabilities
	 */
	public List<FinancialLiability> findByOffender(final Offender offender) {
		return this.financialLiabilityDao.findByOffender(offender);
	}
	
	/**
	 * Finds the total liabilities amount for an offender
	 * @param offender offender
	 * @return total liabilities amount
	 */
	public BigDecimal findLiabilitySumByOffender(final Offender offender) {
		return this.financialLiabilityDao.findLiabilitySumByOffender(offender);
	}
}
