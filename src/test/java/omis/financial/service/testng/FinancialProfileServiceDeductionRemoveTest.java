package omis.financial.service.testng;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.testng.annotations.Test;

import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.exception.DuplicateEntityFoundException;
import omis.financial.domain.RecurringDeduction;
import omis.financial.domain.RecurringDeductionCategory;
import omis.financial.domain.RecurringDeductionFrequency;
import omis.financial.service.FinancialProfileService;
import omis.financial.service.delegate.RecurringDeductionCategoryDelegate;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;

/**
 * Tests financial profile deduction removal 
 *
 * @author Yidong Li
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"financial"})
public class FinancialProfileServiceDeductionRemoveTest
		extends AbstractHibernateTransactionalTestNGSpringContextTests {
	
	/* Delegates. */
	
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	@Qualifier("recurringDeductionCategoryDelegate")
	private RecurringDeductionCategoryDelegate recurringDeductionCategoryDelegate;
	
	/* Service to test. */
	
	@Autowired
	@Qualifier("financialProfileService")
	private FinancialProfileService financialProfileService;
	
	/* Property editors. */
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory datePropertyEditorFactory;
	
	/**
	 * Tests the removal of deduction.
	 */
	@Test
	public void testDeductionRemove() {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Pence",
			"Johns", "Charlie", "Mr.");
		RecurringDeductionCategory category;
		try {
			category 
				= this.recurringDeductionCategoryDelegate.create("aSGR", 
				(short)2345, true);
		} catch (DuplicateEntityFoundException e) {
			throw new RuntimeException("Recurring deduction category already "
					+ "exists",	e);
		}
		Date reportedDate = this.convertDate("02/30/2017");
		
		BigDecimal amount = new BigDecimal(55555);
		RecurringDeduction recurringDeduction;
		try {
			recurringDeduction = this.financialProfileService
				.createRecurringDeduction(offender, category, reportedDate, 
				"testRecurringDeductionCreation", amount, 
				RecurringDeductionFrequency.WEEKLY); 
		} catch (DuplicateEntityFoundException e) {
			throw new RuntimeException("Recurring deduction unexpectedly exists", 
				e);
		}
		
		//Action
		this.financialProfileService.removeRecurringDeduction(recurringDeduction);
				
		// Assertions
		assert !this.financialProfileService.findRecurringDeductions(offender)
		.contains(recurringDeduction): "Deduction was not removed!";
	}
	
	// Converts date - null safe
	private Date convertDate(final String value) {
		if (value != null) {
			CustomDateEditor dateEditor = this.datePropertyEditorFactory
					.createCustomDateOnlyEditor(false);
			dateEditor.setAsText(value);
			return (Date) dateEditor.getValue();
		} else {
			return null;
		}
	}
}