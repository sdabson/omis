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
import omis.financial.service.delegate.RecurringDeductionDelegate;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.util.PropertyValueAsserter;

/**
 * Tests "create" of financial profile recurring deduction
 *
 * @author Yidong Li
 * @author Josh Divine
 * @version 0.0.2
 * @since OMIS 3.0
 */
@Test(groups = {"financial"})
public class FinancialProfileServiceDeductionCreationTest
		extends AbstractHibernateTransactionalTestNGSpringContextTests {
	
	/* Delegates. */
	
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	@Qualifier("recurringDeductionCategoryDelegate")
	private RecurringDeductionCategoryDelegate 
		recurringDeductionCategoryDelegate;
	
	@Autowired
	@Qualifier("recurringDeductionDelegate")
	private RecurringDeductionDelegate recurringDeductionDelegate;
	
	/* Service to test. */
	
	@Autowired
	@Qualifier("financialProfileService")
	private FinancialProfileService financialProfileService;
	
	/* Property editors. */
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory datePropertyEditorFactory;
	
	/**
	 * Tests the creation of recurring deduction.
	 */
	@Test
	public void testCreateRecurringDeduction() 
		throws DuplicateEntityFoundException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Resse",
			"Johns", "Charlie", "Mr.");
		RecurringDeductionCategory category = this
				.recurringDeductionCategoryDelegate.create("Category", 
						(short) 1, true);
		Date reportedDate = this.convertDate("02/11/2017");
		BigDecimal amount = new BigDecimal(363);
		String description = "description";
		RecurringDeductionFrequency frequency = 
				RecurringDeductionFrequency.WEEKLY;
		// Action
		RecurringDeduction recurringDeduction = this.financialProfileService
				.createRecurringDeduction(offender, category, reportedDate, 
				description, amount, frequency); 
		
		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("offender", offender)
			.addExpectedValue("category", category)
			.addExpectedValue("reportedDate", reportedDate)
			.addExpectedValue("amount", amount)
			.addExpectedValue("description", description)
			.addExpectedValue("frequency", frequency)
			.performAssertions(recurringDeduction);
	}
	
	/**
	 * Tests {@code DuplicateEntityFoundException} is thrown.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate deduction exists
	 */
	@Test(expectedExceptions = {DuplicateEntityFoundException.class})
	public void testDuplicateDeductionCreate() 
		throws DuplicateEntityFoundException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Resse",
			"Johns", "Charlie", "Mr.");
		RecurringDeductionCategory category = this
				.recurringDeductionCategoryDelegate.create("Category", 
						(short) 1, true);
		Date reportedDate = this.convertDate("02/11/2017");
		BigDecimal amount = new BigDecimal(363);
		String description = "description";
		RecurringDeductionFrequency frequency = 
				RecurringDeductionFrequency.WEEKLY;
		this.recurringDeductionDelegate.create(offender, category, reportedDate,
				description, amount, frequency);
		
		// Action
		this.financialProfileService.createRecurringDeduction(offender, 
				category, reportedDate, description, amount, frequency); 
		
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