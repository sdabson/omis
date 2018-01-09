package omis.financial.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import omis.document.domain.Document;
import omis.document.domain.DocumentTag;
import omis.document.service.delegate.DocumentDelegate;
import omis.document.service.delegate.DocumentTagDelegate;
import omis.exception.DuplicateEntityFoundException;
import omis.financial.domain.FinancialAsset;
import omis.financial.domain.FinancialAssetCategory;
import omis.financial.domain.FinancialDocumentAssociation;
import omis.financial.domain.FinancialLiability;
import omis.financial.domain.FinancialLiabilityCategory;
import omis.financial.domain.RecurringDeduction;
import omis.financial.domain.RecurringDeductionCategory;
import omis.financial.domain.RecurringDeductionFrequency;
import omis.financial.service.FinancialProfileService;
import omis.financial.service.delegate.FinancialAssetCategoryDelegate;
import omis.financial.service.delegate.FinancialAssetDelegate;
import omis.financial.service.delegate.FinancialDocumentAssociationDelegate;
import omis.financial.service.delegate.FinancialLiabilityCategoryDelegate;
import omis.financial.service.delegate.FinancialLiabilityDelegate;
import omis.financial.service.delegate.RecurringDeductionCategoryDelegate;
import omis.financial.service.delegate.RecurringDeductionDelegate;
import omis.offender.domain.Offender;

/**
 * Financial Profile Service Impl.
 * 
 *@author Josh Divine 
 *@author Yidong Li
 *@author Trevor Isles
 *@version 0.1.1 (November 21, 2016)
 *@since OMIS 3.0
 *
 */
public class FinancialProfileServiceImpl implements FinancialProfileService {

	private final FinancialAssetDelegate financialAssetDelegate;
	private final FinancialLiabilityDelegate financialLiabilityDelegate;
	private final FinancialAssetCategoryDelegate financialAssetCategoryDelegate;
	private final FinancialLiabilityCategoryDelegate 
		financialLiabilityCategoryDelegate;
	private final RecurringDeductionDelegate recurringDeductionDelegate;
	private final RecurringDeductionCategoryDelegate 
		recurringDeductionCategoryDelegate;
	private final FinancialDocumentAssociationDelegate 
		financialDocumentAssociationDelegate;
	private final DocumentDelegate documentDelegate;
	private final DocumentTagDelegate documentTagDelegate;
	
	/**
	 * Constructor
	 * @param financialAssetDelegate
	 * @param financialLiabilityDelegate
	 * @param financialAssetCategoryDelegate
	 * @param financialLiabilityCategoryDelegate
	 * @param recurringDeductionDelegate
	 * @param recurringDeductionCategoryDelegate
	 * @param recurringDeductionDelegate
	 * @param financialDocumentAssociationDelegate
	 * @param documentTagDelegate
	 */
	public FinancialProfileServiceImpl(final FinancialAssetDelegate 
			financialAssetDelegate,
			final FinancialLiabilityDelegate financialLiabilityDelegate,
			final FinancialAssetCategoryDelegate financialAssetCategoryDelegate,
			final FinancialLiabilityCategoryDelegate 
			financialLiabilityCategoryDelegate,
			final RecurringDeductionDelegate recurringDeductionDelegate,
			final RecurringDeductionCategoryDelegate 
				recurringDeductionCategoryDelegate,
			final FinancialDocumentAssociationDelegate 
				financialDocumentAssociationDelegate,
			final DocumentDelegate documentDelegate,
			final DocumentTagDelegate documentTagDelegate) {
		this.financialAssetDelegate = financialAssetDelegate;
		this.financialLiabilityDelegate = financialLiabilityDelegate;
		this.financialAssetCategoryDelegate = financialAssetCategoryDelegate;
		this.financialLiabilityCategoryDelegate 
			= financialLiabilityCategoryDelegate;
		this.recurringDeductionDelegate = recurringDeductionDelegate;
		this.recurringDeductionCategoryDelegate 
			= recurringDeductionCategoryDelegate;
		this.financialDocumentAssociationDelegate 
			= financialDocumentAssociationDelegate;
		this.documentDelegate = documentDelegate;
		this.documentTagDelegate = documentTagDelegate;
	}
	
	/** {@inheritDoc} */
	@Override
	public FinancialAsset createAsset(final Offender offender, 
			final FinancialAssetCategory category, final Date reportedDate,
			final String description, final BigDecimal amount) 
					throws DuplicateEntityFoundException {
			return this.financialAssetDelegate.create(offender, category, 
				description, amount, reportedDate);
	}

	/** {@inheritDoc} */
	@Override
	public FinancialAsset updateAsset(final FinancialAsset financialAsset, 
			final Offender offender, final FinancialAssetCategory category, 
			final Date reportedDate, final String description, 
			final BigDecimal amount) 
					throws DuplicateEntityFoundException {
		return this.financialAssetDelegate.update(financialAsset, offender, 
				category, description, amount, reportedDate);
	}

	/** {@inheritDoc} */
	@Override
	public FinancialLiability createLiability(final Offender offender, 
			final FinancialLiabilityCategory category, final Date reportedDate,
			final String description, final BigDecimal amount) 
					throws DuplicateEntityFoundException {
		return this.financialLiabilityDelegate.create(offender, category, 
				description, amount, reportedDate);
	}

	/** {@inheritDoc} */
	@Override
	public FinancialLiability updateLiability(
			final FinancialLiability financialLiability, 
			final Offender offender, final FinancialLiabilityCategory category, 
			final Date reportedDate, final String description, 
			final BigDecimal amount) 
					throws DuplicateEntityFoundException {
		return this.financialLiabilityDelegate.update(financialLiability, 
				offender, category, description, amount, reportedDate);
	}

	/** {@inheritDoc} */
	@Override
	public List<FinancialAssetCategory> findAssetCategories() {
		return this.financialAssetCategoryDelegate.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public List<FinancialLiabilityCategory> findLiabilityCategories() {
		return this.financialLiabilityCategoryDelegate.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public void removeLiability(final FinancialLiability liability) {
		this.financialLiabilityDelegate.remove(liability);
	}

	/** {@inheritDoc} */
	@Override
	public void removeAsset(final FinancialAsset asset) {
		this.financialAssetDelegate.remove(asset);
	}

	/** {@inheritDoc} */
	@Override
	public BigDecimal calculateAssets(final Offender offender) {
		BigDecimal assetsTotal = this.financialAssetDelegate
				.findAssetSumByOffender(offender);
		if (assetsTotal == null) {
			assetsTotal = new BigDecimal(0);
		}
		return assetsTotal;
	}

	/** {@inheritDoc} */
	@Override
	public BigDecimal calculateLiabilities(final Offender offender) {
		BigDecimal liabilitiesTotal = this.financialLiabilityDelegate
				.findLiabilitySumByOffender(offender);
		if (liabilitiesTotal == null) {
			liabilitiesTotal = new BigDecimal(0);
		}
		return liabilitiesTotal;
	}

	/** {@inheritDoc} */
	@Override
	public BigDecimal calculateEquity(final Offender offender) {
		BigDecimal assetsTotal = this.calculateAssets(offender);
		BigDecimal liabilitiesTotal = this.calculateLiabilities(offender);
		return assetsTotal.subtract(liabilitiesTotal);
	}

	/** {@inheritDoc} */
	@Override
	public List<FinancialAsset> findAssets(final Offender offender) {
		return this.financialAssetDelegate.findByOffender(offender);
	}

	/** {@inheritDoc} */
	@Override
	public List<FinancialLiability> findLiabilities(final Offender offender) {
		return this.financialLiabilityDelegate.findByOffender(offender);
	}
	
	/** {@inheritDoc} */
	@Override
	public RecurringDeduction createRecurringDeduction(final Offender offender, 
		final RecurringDeductionCategory category, final Date reportedDate, 
		final String description, final BigDecimal amount, 
		final RecurringDeductionFrequency frequency) 
			throws DuplicateEntityFoundException {
		return this.recurringDeductionDelegate.create(offender, category, 
			reportedDate, description, amount, frequency);
	}

	/** {@inheritDoc} */
	@Override
	public RecurringDeduction updateRecurringDeduction(
		final RecurringDeduction recurringDeduction, 
		final RecurringDeductionCategory category, 
		final Date reportedDate, final String description, 
		final BigDecimal amount, final RecurringDeductionFrequency frequency)
			throws DuplicateEntityFoundException {
		return this.recurringDeductionDelegate.update(category, reportedDate, 
			description, amount, frequency, recurringDeduction);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<RecurringDeductionCategory> findRecurringDeductionCategories(){
		return this.recurringDeductionCategoryDelegate.findAll();
	};
	
	/** {@inheritDoc} */
	@Override
	public List<RecurringDeduction> findRecurringDeductions(final Offender 
		offender){
		return this.recurringDeductionDelegate.findAll(offender);
	};
	
	/** {@inheritDoc} */
	@Override
	public void removeRecurringDeduction(final RecurringDeduction 
		recurringDeduction){
		this.recurringDeductionDelegate.remove(recurringDeduction);
	};
	
	/**{@inheritDoc} */
	@Override
	public FinancialDocumentAssociation createFinancialDocument(final Offender offender,
			final Document document)
			throws DuplicateEntityFoundException {
		return this.financialDocumentAssociationDelegate.createDocument(
				offender, document);
	}
	
	/**{@inheritDoc} */
	@Override
	public FinancialDocumentAssociation updateFinancialDocument(
			final FinancialDocumentAssociation financialAssociableDocument,
			final Document document)
					throws DuplicateEntityFoundException {
		return this.financialDocumentAssociationDelegate.updateDocument(
				financialAssociableDocument, document);
	}
	
	/**{@inheritDoc} */
	@Override
	public void removeFinancialDocument(
			final FinancialDocumentAssociation financialAssociableDocument) {
		this.financialDocumentAssociationDelegate.removeDocument(
				financialAssociableDocument);
	}

	/**{@inheritDoc} */
	@Override
	public DocumentTag createDocumentTag(final Document document,
			final String name) throws DuplicateEntityFoundException {
		return this.documentTagDelegate.tagDocument(document, name);
	}

	/**{@inheritDoc} */
	@Override
	public DocumentTag updateDocumentTag(final DocumentTag documentTag,
			final String name) throws DuplicateEntityFoundException {
		return this.documentTagDelegate.update(documentTag, name);
	}

	/**{@inheritDoc} */
	@Override
	public void removeDocumentTag(final DocumentTag documentTag) {
		this.documentTagDelegate.removeTag(documentTag);
	}

	/**{@inheritDoc} */
	@Override
	public List<DocumentTag> findDocumentTagsByDocument(
			final Document document) {
		return this.documentTagDelegate.findByDocument(document);
	}

	@Override
	public Document createDocument(final Date documentDate,
			final String filename, final String fileExtension, 
			final String title)
			throws DuplicateEntityFoundException {
		return this.documentDelegate.create(documentDate, filename, 
				fileExtension, title);
	}

	@Override
	public Document updateDocument(final Document document, 
			final Date documentDate, final String title)
			throws DuplicateEntityFoundException {
		return this.documentDelegate.update(document, title, documentDate);
	}

	@Override
	public void removeDocument(final Document document) {
		this.documentDelegate.remove(document);
	}

	@Override
	public List<FinancialDocumentAssociation> 
		findFinancialDocumentAssociationsByOffender(final Offender offender) {
		return this.financialDocumentAssociationDelegate
				.findFinancialDocumentAssociationsByOffender(offender);
	}
	
}
