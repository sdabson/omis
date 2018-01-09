package omis.financial.service.delegate;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.financial.dao.FinancialAssetDao;
import omis.financial.domain.FinancialAsset;
import omis.financial.domain.FinancialAssetCategory;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;

/**
 * Financial asset delegate.
 * 
 *@author Josh Divine 
 *@version 0.1.0 (November 21, 2016)
 *@since OMIS 3.0
 *
 */
public class FinancialAssetDelegate {

	private static final String DUPLICATE_ENTITY_FOUND_MSG = "Financial asset "
			+ "already exists for this offender.";
	
	private FinancialAssetDao financialAssetDao;
	private InstanceFactory<FinancialAsset> financialAssetInstanceFactory;
	private AuditComponentRetriever auditComponentRetriever;
	
	/**
	 * Constructor
	 * @param financialAssetInstanceFactory
	 * @param financialAssetDao
	 * @param auditComponentRetriever
	 */
	public FinancialAssetDelegate(final InstanceFactory<FinancialAsset> 
			financialAssetInstanceFactory,
			final FinancialAssetDao financialAssetDao,
			final AuditComponentRetriever auditComponentRetriever) {
		this.financialAssetInstanceFactory = financialAssetInstanceFactory;
		this.financialAssetDao = financialAssetDao;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a new financial asset
	 * @param offender offender
	 * @param category financial asset category
	 * @param description description
	 * @param amount amount
	 * @param reportedDate reported date
	 * @return Financial asset
	 * @throws DuplicateEntityFoundException
	 */
	public FinancialAsset create(final Offender offender, 
			final FinancialAssetCategory category, final String description, 
			final BigDecimal amount, final Date reportedDate) 
					throws DuplicateEntityFoundException {
		if (this.financialAssetDao.find(offender, category, description, 
				reportedDate, amount) != null) {
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		FinancialAsset financialAsset = this.financialAssetInstanceFactory
				.createInstance();
		financialAsset.setAmount(amount);
		financialAsset.setCategory(category);
		financialAsset.setDescription(description);
		financialAsset.setOffender(offender);
		financialAsset.setReportedDate(reportedDate);
		financialAsset.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		financialAsset.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		
		return this.financialAssetDao.makePersistent(financialAsset);
	}
	
	/**
	 * Updates an existing financial asset
	 * @param financialAsset financial asset
	 * @param offender offender
	 * @param category financial asset category
	 * @param description description
	 * @param amount amount
	 * @param reportedDate reported date
	 * @return Financial asset
	 * @throws DuplicateEntityFoundException
	 */
	public FinancialAsset update(final FinancialAsset financialAsset, 
			final Offender offender, final FinancialAssetCategory category, 
			final String description, final BigDecimal amount, 
			final Date reportedDate) 
					throws DuplicateEntityFoundException {
		if (this.financialAssetDao.findExcluding(offender, category, 
				description, reportedDate, amount, financialAsset) != null) {
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		financialAsset.setAmount(amount);
		financialAsset.setCategory(category);
		financialAsset.setDescription(description);
		financialAsset.setOffender(offender);
		financialAsset.setReportedDate(reportedDate);
		financialAsset.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		
		return this.financialAssetDao.makePersistent(financialAsset);
	}
	
	/**
	 * Removes the specified financial asset
	 * @param financialAsset financial asset
	 */
	public void remove(final FinancialAsset financialAsset) {
		this.financialAssetDao.makeTransient(financialAsset);
	}
	
	/**
	 * Finds all financial assets for the specified offender
	 * @param offender offender
	 * @return List of financial assets
	 */
	public List<FinancialAsset> findByOffender(final Offender offender) {
		return this.financialAssetDao.findByOffender(offender);
	}
	
	/**
	 * Finds the total asset amount for the specified offender
	 * @param offender offender
	 * @return total asset amount
	 */
	public BigDecimal findAssetSumByOffender(final Offender offender) {
		return this.financialAssetDao.findAssetSumByOffender(offender);
	}
}
