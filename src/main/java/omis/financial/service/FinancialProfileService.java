package omis.financial.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import omis.document.domain.Document;
import omis.document.domain.DocumentTag;
import omis.exception.DuplicateEntityFoundException;
import omis.financial.domain.FinancialAsset;
import omis.financial.domain.FinancialAssetCategory;
import omis.financial.domain.FinancialDocumentAssociation;
import omis.financial.domain.FinancialLiability;
import omis.financial.domain.FinancialLiabilityCategory;
import omis.financial.domain.RecurringDeduction;
import omis.financial.domain.RecurringDeductionCategory;
import omis.financial.domain.RecurringDeductionFrequency;
import omis.offender.domain.Offender;

/**
 * Financial Profile Service.
 * 
 *@author Josh Divine 
 *@author Yidong Li
 *@author Trevor Isles
 *@version 0.1.1 (November 21, 2016)
 *@since OMIS 3.0
 *
 */
public interface FinancialProfileService {

	/**
	 * Creates an asset
	 * @param offender offender
	 * @param category financial asset category
	 * @param reportedDate reported date
	 * @param description description
	 * @param amount amount
	 * @return financial asset
	 * @throws DuplicateEntityFoundException
	 */
	public FinancialAsset createAsset(Offender offender, 
			FinancialAssetCategory category, Date reportedDate, 
			String description, BigDecimal amount) 
					throws DuplicateEntityFoundException;

	/**
	 * Updates an asset
	 * @param financialAsset financial asset
	 * @param offender offender
	 * @param category financial asset category
	 * @param reportedDate reported date
	 * @param description description
	 * @param amount amount
	 * @return financial asset
	 * @throws DuplicateEntityFoundException
	 */
	public FinancialAsset updateAsset(FinancialAsset financialAsset, 
			Offender offender, FinancialAssetCategory category,
			Date reportedDate, String description, BigDecimal amount) 
					throws DuplicateEntityFoundException;
	
	/**
	 * Creates a liability
	 * @param offender offender
	 * @param category financial asset category
	 * @param reportedDate reported date
	 * @param description description
	 * @param amount amount
	 * @return financial liability
	 * @throws DuplicateEntityFoundException
	 */
	public FinancialLiability createLiability(Offender offender, 
			FinancialLiabilityCategory category, Date reportedDate, 
			String description, BigDecimal amount) 
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates a liability
	 * @param financialLiability financial liability
	 * @param offender offender
	 * @param category financial asset category
	 * @param reportedDate reported date
	 * @param description description
	 * @param amount amount
	 * @return financial liability
	 * @throws DuplicateEntityFoundException
	 */
	public FinancialLiability updateLiability(
			FinancialLiability financialLiability, Offender offender, 
			FinancialLiabilityCategory category, Date reportedDate, 
			String description, BigDecimal amount) 
					throws DuplicateEntityFoundException;
	
	/**
	 * Finds all asset categories
	 * @return List of asset categories
	 */
	public List<FinancialAssetCategory> findAssetCategories();
	
	/**
	 * Finds all liability categories
	 * @return List of liability categories
	 */
	public List<FinancialLiabilityCategory> findLiabilityCategories();
	
	/**
	 * Removes a liability
	 * @param liability financial liability
	 */
	public void removeLiability(FinancialLiability liability);
	
	/**
	 * Removes an asset
	 * @param asset financial asset
	 */
	public void removeAsset(FinancialAsset asset);
	
	/**
	 * Calculates total assets for an offender
	 * @param offender offender
	 * @return Total asset amount
	 */
	public BigDecimal calculateAssets(Offender offender);
	
	/**
	 * Calculates total liabilities for an offender
	 * @param offender offender
	 * @return Total liability amount
	 */
	public BigDecimal calculateLiabilities(Offender offender);
	
	/**
	 * Calculates total equity for an offender
	 * @param offender offender
	 * @return Total equity amount
	 */
	public BigDecimal calculateEquity(Offender offender);
	
	/**
	 * Finds all assets for an offender
	 * @param offender offender
	 * @return List of financial assets
	 */
	public List<FinancialAsset> findAssets(Offender offender);
	
	/**
	 * Finds all liabilities for an offender
	 * @param offender offender
	 * @return List of financial liabilities
	 */
	public List<FinancialLiability> findLiabilities(Offender offender);
	
	/**
	 * Create a recurring deduction
	 * @param offender offender
	 * @param category recurring deduction category
	 * @param reportedDate reported date
	 * @param description description
	 * @param amount amount
	 * @param frrequency recurring deduction frequency
	 * @return Recurring deduction
	 * @throws DuplicateEntityFoundException 
	 */
	public RecurringDeduction createRecurringDeduction(Offender offender, 
		RecurringDeductionCategory category, Date reportedDate, 
		String description, BigDecimal amount, 
		RecurringDeductionFrequency frequency) 
		throws DuplicateEntityFoundException;
		
	/**
	 * Update a recurring deduction
	 * @param recurringDeduction recurring deduction
	 * @param category recurring deduction category
	 * @param reportedDate reported date
	 * @param description description
	 * @param amount amount
	 * @param frrequency recurring deduction frequency
	 * @return Recurring deduction
	 */
	public RecurringDeduction updateRecurringDeduction(
		RecurringDeduction recurringDeduction, 
		RecurringDeductionCategory category, Date reportedDate, 
		String description, BigDecimal amount, 
		RecurringDeductionFrequency frequency) 
		throws DuplicateEntityFoundException;
	
	/**
	 * Find all recurring deduction categories
	 * @return List of recurring deduction category
	 */
	public List<RecurringDeductionCategory> findRecurringDeductionCategories();
	
	/**
	 * Find all of a offender's recurring deduction
	 * @return List of recurring deduction category
	 */
	public List<RecurringDeduction> findRecurringDeductions(Offender offender);
	
	/**
	 * Remove the specified recurring deduction
	 * @param recurringDeduction specified recurring deduction
	 */
	public void removeRecurringDeduction(RecurringDeduction recurringDeduction);
	
	/**
	 * Creates an FinancialAssociableDocument with the specified properties
	 * @param offender - Offender
	 * @param document - Document
	 * @return Newly created FinancialAssociableDocument
	 * @throws DuplicateEntityFoundException - when a FinancialAssociableDocument
	 * already exists with given Offender and Document
	 */
	public FinancialDocumentAssociation createFinancialDocument(Offender offender,
			Document document)
			throws DuplicateEntityFoundException;
	
	/**
	 * Updates an FinancialAssociableDocument with the specified properties
	 * @param financialAssociableDocument - FinancialAssociableDocument to update
	 * @param document - Document
	 * @return Updated FinancialDocumentAssociation
	 * @throws DuplicateEntityFoundException - when a FinancialDocumentAssociation
	 * already exists with given Offender and Document
	 */
	public FinancialDocumentAssociation updateFinancialDocument(
			FinancialDocumentAssociation financialDocumentAssociaiton,
			Document document)
			throws DuplicateEntityFoundException;
	
	/**
	 * Removes a financialDocumentAssociation
	 * @param financialDocumentAssociation - FinancialDocumentAssociation to 
	 * remove
	 */
	public void removeFinancialDocument(
			final FinancialDocumentAssociation financialDocumentAssociation);
	
	
	/** Create document.
	 * @param documentDate - date of document.
	 * @param filename - file name.
	 * @param fileExtension - file extension. 
	 * @param title - title. 
	 * @return document entity.
	 * @throws DuplicateEntityFoundException - when document with existing file
	 * name exists. */
	public Document createDocument(Date documentDate,
			String filename, String fileExtension, String title)
					throws DuplicateEntityFoundException;
	
	/** Updates document.
	 * @param document - document.
	 * @param title - title. 
	 * @param date - date. 
	 * @throws DuplicateEntityFoundException */
	public Document updateDocument(Document document, Date documentDate,
			String title)
					throws DuplicateEntityFoundException;

	/**
	 * Removes a specified document
	 * @param document - Document to remove
	 */
	public void removeDocument(Document document);
	
	/** Tag document.
	 * @param document - document.
	 * @param name - tag name. 
	 * @return document tag. 
	 * @throws DuplicateEntityFoundException - when document is already tagged
	 * with given name. */
	public DocumentTag createDocumentTag(Document document, String name)
			throws DuplicateEntityFoundException;

	/** Update tag.
	 * @param documentTag - document tag.
	 * @param name - name. 
	 * @throws DuplicateEntityFoundException - when document is already tagged
	 * with given name. */
	public DocumentTag updateDocumentTag(DocumentTag documentTag, String name)
			throws DuplicateEntityFoundException;

	/** Remove tag.
	 * @param documentTag - document tag. */
	public void removeDocumentTag(DocumentTag documentTag);
	

	/** Finds document tags by document.
	 * @param document - document.
	 * @return list of document tags. */
	public List<DocumentTag> findDocumentTagsByDocument(Document document);
	
	/** Finds financial document associations by offender
	 * @param offender
	 * @return list of financial document associations
	 */
	public List<FinancialDocumentAssociation> 
		findFinancialDocumentAssociationsByOffender(Offender offender);
	
}
