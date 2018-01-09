package omis.financial.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import omis.financial.web.form.FinancialAssetItem;
import omis.financial.web.form.FinancialDocumentItem;
import omis.financial.web.form.FinancialLiabilityItem;
import omis.financial.web.form.FinancialProfileForm;
import omis.financial.web.form.ItemOperation;
import omis.financial.web.form.RecurringDeductionItem;

/**
 * Financial Profile Form Validator.
 * 
 *@author Josh Divine 
 *@author Yidong Li
 *@version 0.1.0 (November 21, 2016)
 *@since OMIS 3.0
 *
 */
public class FinancialProfileFormValidator implements Validator {

	/* Message Keys. */
	private static final String ASSET_AMOUNT_REQUIRED_MESSAGE_KEY 
		= "financialAssetAmount.empty";
	private static final String ASSET_AMOUNT_INVALID_MESSAGE_KEY
		= "financialAssetAmount.value.invalid";
	private static final String ASSET_CATEGORY_REQUIRED_MESSAGE_KEY 
		= "financialAssetCategory.empty";
	private static final String ASSET_DESCRIPTION_REQUIRED_MESSAGE_KEY 
		= "financialAssetDescription.empty";
	private static final String ASSET_REPORTED_DATE_REQUIRED_MESSAGE_KEY 
		= "financialAssetReportedDate.empty";
	private static final String LIABILITY_AMOUNT_REQUIRED_MESSAGE_KEY 
		= "financialLiabilityAmount.empty";
	private static final String LIABILITY_AMOUNT_INVALID_MESSAGE_KEY
		= "financialLiabilityAmount.value.invalid";
	private static final String LIABILITY_CATEGORY_REQUIRED_MESSAGE_KEY 
		= "financialLiabilityCategory.empty";
	private static final String LIABILITY_DESCRIPTION_REQUIRED_MESSAGE_KEY 
		= "financialLiabilityDescription.empty";
	private static final String LIABILITY_REPORTED_DATE_REQUIRED_MESSAGE_KEY 
		= "financialLiabilityReportedDate.empty";
	private static final String RECURRING_DEDUCTION_AMOUNT_REQUIRED_MESSAGE_KEY
		= "recurringDeductionAmount.empty";
	private static final String RECURRING_DEDUCTION_AMOUNT_INVALID_MESSAGE_KEY
		= "recurringDeductionAmount.value.invalid";
	private static final String RECURRING_DEDUCTION_REQUIRED_MESSAGE_KEY
		= "recurringDeductionCategory.empty";
	private static final String RECURRING_DEDUCTION_DESCRIPTION_REQUIRED_MESSAGE_KEY
		= "recurringDeductionDescription.empty";
	private static final String RECURRING_DEDUCTION_REPORTED_DATE_REQUIRED_MESSAGE_KEY
		="recurringDeductionReportedDate.empty";
	private static final String RECURRING_DEDUCTION_FREQUENCY_REQUIRED_MESSAGE_KEY
		="recurringDeductionFrequency.empty";
	private static final String FINANCIAL_DOCUMENT_DATE_REQUIRED_MESSAGE_KEY
		= "financialDocumentDate.empty";
	private static final String FINANCIAL_DOCUMENT_TITLE_REQUIRED_MESSAGE_KEY
		= "financialDocumentTitle.empty";
	private static final String FINANCIAL_DOCUMENT_DATA_REQUIRED_MESSAGE_KEY
		= "financialDocumentData.empty";

	public FinancialProfileFormValidator() {
		//Default instantiation
	}

	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return FinancialProfileForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		FinancialProfileForm form = (FinancialProfileForm) target;
		String regex = "^\\$?([1-9]{1}[0-9]{0,2}(\\,\\d{3})*(\\.\\d{0,2})?|"
				+ "[1-9]{1}\\d{0,}(\\.\\d{0,2})?|"
				+ "0(\\.\\d{0,2})?|(\\.\\d{1,2}))$";
		if (form.getFinancialAssetItems() != null) {
			for (int itemIndex = 0; itemIndex < form.getFinancialAssetItems()
					.size(); itemIndex++) {
				FinancialAssetItem asset = form.getFinancialAssetItems()
						.get(itemIndex);
				if (asset.getOperation() != null && 
						!ItemOperation.REMOVE.equals(asset.getOperation())) {
					if (asset.getAmount() == null) {
						errors.rejectValue("financialAssetItems[" + itemIndex 
								+ "].amount", 
								ASSET_AMOUNT_REQUIRED_MESSAGE_KEY);
					} else {
						if (!asset.getAmount().matches(regex))
						{
							errors.rejectValue("financialAssetItems[" 
									+ itemIndex + "].amount", 
									ASSET_AMOUNT_INVALID_MESSAGE_KEY);
						}
					}
					if (asset.getCategory() == null) {
						errors.rejectValue("financialAssetItems[" + itemIndex 
								+ "].category", 
								ASSET_CATEGORY_REQUIRED_MESSAGE_KEY);
					}
					if (asset.getDescription() == null
							|| asset.getDescription().isEmpty()) {
						errors.rejectValue("financialAssetItems[" + itemIndex 
								+ "].description", 
								ASSET_DESCRIPTION_REQUIRED_MESSAGE_KEY);
					}
					if (asset.getReportedDate() == null) {
						errors.rejectValue("financialAssetItems[" + itemIndex 
								+ "].reportedDate", 
								ASSET_REPORTED_DATE_REQUIRED_MESSAGE_KEY);
					}
				}
			}
		}
		if (form.getFinancialLiabilityItems() != null) {
			for (int itemIndex = 0; itemIndex 
					< form.getFinancialLiabilityItems().size(); itemIndex++) {
				FinancialLiabilityItem liability = form
					.getFinancialLiabilityItems().get(itemIndex); 
				if (liability.getOperation() != null 
						&& !ItemOperation.REMOVE.equals(liability
								.getOperation())) {
					if (liability.getAmount() == null) {
						errors.rejectValue("financialLiabilityItems[" 
								+ itemIndex + "].amount", 
								LIABILITY_AMOUNT_REQUIRED_MESSAGE_KEY);
					} else {
						if (!liability.getAmount().matches(regex)) {
							errors.rejectValue("financialLiabilityItems[" 
									+ itemIndex + "].amount", 
									LIABILITY_AMOUNT_INVALID_MESSAGE_KEY);
						}
					}
					if (liability.getCategory() == null) {
						errors.rejectValue("financialLiabilityItems[" 
								+ itemIndex + "].category", 
								LIABILITY_CATEGORY_REQUIRED_MESSAGE_KEY);
					}
					if (liability.getDescription() == null
							|| liability.getDescription().isEmpty()) {
						errors.rejectValue("financialLiabilityItems[" 
								+ itemIndex + "].description", 
								LIABILITY_DESCRIPTION_REQUIRED_MESSAGE_KEY);
					}
					if (liability.getReportedDate() == null) {
						errors.rejectValue("financialLiabilityItems[" 
								+ itemIndex + "].reportedDate", 
								LIABILITY_REPORTED_DATE_REQUIRED_MESSAGE_KEY);
					}
				}
			}
		}
		if (form.getRecurringDeductionItems() != null) {
			for (int itemIndex = 0; itemIndex 
					< form.getRecurringDeductionItems().size(); itemIndex++) {
				RecurringDeductionItem recurringDeductionItem = form
					.getRecurringDeductionItems().get(itemIndex); 
				if (recurringDeductionItem.getOperation() != null 
						&& !ItemOperation.REMOVE.equals(recurringDeductionItem
								.getOperation())) {
					if (recurringDeductionItem.getAmount() == null) {
						errors.rejectValue("recurringDeductionItems[" 
							+ itemIndex + "].amount", 
							RECURRING_DEDUCTION_AMOUNT_REQUIRED_MESSAGE_KEY);
					} else {
						if (!recurringDeductionItem.getAmount().matches(regex)) {
							errors.rejectValue("recurringDeductionItems[" 
								+ itemIndex + "].amount", 
								RECURRING_DEDUCTION_AMOUNT_INVALID_MESSAGE_KEY);
						}
					}
					if (recurringDeductionItem.getCategory() == null) {
						errors.rejectValue("recurringDeductionItems[" 
								+ itemIndex + "].category", 
								RECURRING_DEDUCTION_REQUIRED_MESSAGE_KEY);
					}
					if (recurringDeductionItem.getDescription() == null
							|| recurringDeductionItem.getDescription().isEmpty()) {
						errors.rejectValue("recurringDeductionItems[" 
							+ itemIndex + "].description", 
							RECURRING_DEDUCTION_DESCRIPTION_REQUIRED_MESSAGE_KEY);
					}
					if (recurringDeductionItem.getReportedDate() == null) {
						errors.rejectValue("recurringDeductionItems[" 
							+ itemIndex + "].reportedDate", 
							RECURRING_DEDUCTION_REPORTED_DATE_REQUIRED_MESSAGE_KEY);
					}
					if (recurringDeductionItem.getFrequency() == null) {
						errors.rejectValue("recurringDeductionItems[" 
							+ itemIndex + "].frequency", 
							RECURRING_DEDUCTION_FREQUENCY_REQUIRED_MESSAGE_KEY);
					}
				}
			}
		}
		if (form.getFinancialDocumentAssociationItems() != null) {
			for (int itemIndex = 0; itemIndex 
					< form.getFinancialDocumentAssociationItems().size(); 
					itemIndex++) {
				FinancialDocumentItem financialDocumentItem = form
						.getFinancialDocumentAssociationItems().get(itemIndex);
				if (financialDocumentItem.getOperation() != null 
						&& !ItemOperation.REMOVE.equals(financialDocumentItem
								.getOperation())) {
					if (financialDocumentItem.getDate() == null) {
						errors.rejectValue("financialDocumentAssociationItems[" 
								+ itemIndex + "].date", 
								FINANCIAL_DOCUMENT_DATE_REQUIRED_MESSAGE_KEY);
					}
					if (financialDocumentItem.getTitle() == null 
							|| financialDocumentItem.getTitle().isEmpty()) {
						errors.rejectValue("financialDocumentAssociationItems["
								+ itemIndex + "].title", 
								FINANCIAL_DOCUMENT_TITLE_REQUIRED_MESSAGE_KEY);
					}
					if (financialDocumentItem.getData() == null 
							|| financialDocumentItem.getData().length == 0) {
						errors.rejectValue("financialDocumentAssociationItems["
								+ itemIndex + "].data", 
								FINANCIAL_DOCUMENT_DATA_REQUIRED_MESSAGE_KEY);
					}
				}
			}
		}
	}
}
