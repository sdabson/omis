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
 * Tests "update" of financial profile recurring deduction
 *
 * @author Yidong Li
 * @author Josh Divine
 * @version 0.0.2
 * @since OMIS 3.0
 */
@Test(groups = {"financial"})
public class FinancialProfileServiceDeductionUpdateTest
		extends AbstractHibernateTransactionalTestNGSpringContextTests {
	
	/* Delegates. */
	
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	@Qualifier("recurringDeductionCategoryDelegate")
	private RecurringDeductionCategoryDelegate recurringDeductionCategoryDelegate;
	
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
	 * Tests the update of the category for a recurring deduction.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateRecurringDeductionCategory() 
			throws DuplicateEntityFoundException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Resse",
			"Johns", "Charlie", "Mr.");
		RecurringDeductionCategory category = this
				.recurringDeductionCategoryDelegate.create("Category", 
						(short) 1, true);
		Date reportedDate = this.convertDate("02/11/2017");
		BigDecimal amount = new BigDecimal(363);
		String description = "Description";
		RecurringDeductionFrequency frequency = 
				RecurringDeductionFrequency.WEEKLY;
		RecurringDeduction recurringDeduction = this.recurringDeductionDelegate
				.create(offender, category, reportedDate, description, amount, 
						frequency);		
		RecurringDeductionCategory newCategory = this
				.recurringDeductionCategoryDelegate.create("New Category", 
						(short) 2, true);
		
		// Action
		recurringDeduction = this.financialProfileService
				.updateRecurringDeduction(recurringDeduction, newCategory, 
						reportedDate, description, amount, frequency);
		
		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("offender", offender)
			.addExpectedValue("category", newCategory)
			.addExpectedValue("reportedDate", reportedDate)
			.addExpectedValue("amount", amount)
			.addExpectedValue("Description", description)
			.addExpectedValue("frequency", frequency)
			.performAssertions(recurringDeduction);
	}
	
	/**
	 * Tests the update of the reported date for a recurring deduction.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateRecurringDeductionReportedDate() 
			throws DuplicateEntityFoundException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Resse",
			"Johns", "Charlie", "Mr.");
		RecurringDeductionCategory category = this
				.recurringDeductionCategoryDelegate.create("Category", 
						(short) 1, true);
		Date reportedDate = this.convertDate("02/11/2017");
		BigDecimal amount = new BigDecimal(363);
		String description = "Description";
		RecurringDeductionFrequency frequency = 
				RecurringDeductionFrequency.WEEKLY;
		RecurringDeduction recurringDeduction = this.recurringDeductionDelegate
				.create(offender, category, reportedDate, description, amount, 
						frequency);		
		Date newReportedDate = this.convertDate("02/12/2017");
		
		// Action
		recurringDeduction = this.financialProfileService
				.updateRecurringDeduction(recurringDeduction, category, 
						newReportedDate, description, amount, frequency);
		
		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("offender", offender)
			.addExpectedValue("category", category)
			.addExpectedValue("reportedDate", newReportedDate)
			.addExpectedValue("amount", amount)
			.addExpectedValue("Description", description)
			.addExpectedValue("frequency", frequency)
			.performAssertions(recurringDeduction);
	}
	/**
	 * Tests the update of the amount for a recurring deduction.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateRecurringDeductionAmount() 
			throws DuplicateEntityFoundException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Resse",
			"Johns", "Charlie", "Mr.");
		RecurringDeductionCategory category = this
				.recurringDeductionCategoryDelegate.create("Category", 
						(short) 1, true);
		Date reportedDate = this.convertDate("02/11/2017");
		BigDecimal amount = new BigDecimal(363);
		String description = "Description";
		RecurringDeductionFrequency frequency = 
				RecurringDeductionFrequency.WEEKLY;
		RecurringDeduction recurringDeduction = this.recurringDeductionDelegate
				.create(offender, category, reportedDate, description, amount, 
						frequency);		
		BigDecimal newAmount = new BigDecimal(36);
		
		// Action
		recurringDeduction = this.financialProfileService
				.updateRecurringDeduction(recurringDeduction, category, 
						reportedDate, description, newAmount, frequency);
		
		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("offender", offender)
			.addExpectedValue("category", category)
			.addExpectedValue("reportedDate", reportedDate)
			.addExpectedValue("amount", newAmount)
			.addExpectedValue("Description", description)
			.addExpectedValue("frequency", frequency)
			.performAssertions(recurringDeduction);
	}
	/**
	 * Tests the update of the description for a recurring deduction.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateRecurringDeductionDescription() 
			throws DuplicateEntityFoundException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Resse",
			"Johns", "Charlie", "Mr.");
		RecurringDeductionCategory category = this
				.recurringDeductionCategoryDelegate.create("Category", 
						(short) 1, true);
		Date reportedDate = this.convertDate("02/11/2017");
		BigDecimal amount = new BigDecimal(363);
		String description = "Description";
		RecurringDeductionFrequency frequency = 
				RecurringDeductionFrequency.WEEKLY;
		RecurringDeduction recurringDeduction = this.recurringDeductionDelegate
				.create(offender, category, reportedDate, description, amount, 
						frequency);		
		String newDescription = "New description";
		
		// Action
		recurringDeduction = this.financialProfileService
				.updateRecurringDeduction(recurringDeduction, category, 
						reportedDate, newDescription, amount, frequency);
		
		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("offender", offender)
			.addExpectedValue("category", category)
			.addExpectedValue("reportedDate", reportedDate)
			.addExpectedValue("amount", amount)
			.addExpectedValue("Description", newDescription)
			.addExpectedValue("frequency", frequency)
			.performAssertions(recurringDeduction);
	}
	/**
	 * Tests the update of the frequency for a recurring deduction.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateRecurringDeductionFrequency() 
			throws DuplicateEntityFoundException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Resse",
			"Johns", "Charlie", "Mr.");
		RecurringDeductionCategory category = this
				.recurringDeductionCategoryDelegate.create("Category", 
						(short) 1, true);
		Date reportedDate = this.convertDate("02/11/2017");
		BigDecimal amount = new BigDecimal(363);
		String description = "Description";
		RecurringDeductionFrequency frequency = 
				RecurringDeductionFrequency.WEEKLY;
		RecurringDeduction recurringDeduction = this.recurringDeductionDelegate
				.create(offender, category, reportedDate, description, amount, 
						frequency);		
		RecurringDeductionFrequency newFrequency = 
				RecurringDeductionFrequency.MONTHLY;
		
		// Action
		recurringDeduction = this.financialProfileService
				.updateRecurringDeduction(recurringDeduction, category, 
						reportedDate, description, amount, newFrequency);
		
		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("offender", offender)
			.addExpectedValue("category", category)
			.addExpectedValue("reportedDate", reportedDate)
			.addExpectedValue("amount", amount)
			.addExpectedValue("Description", description)
			.addExpectedValue("frequency", newFrequency)
			.performAssertions(recurringDeduction);
	}
	
	/**
	 * Tests {@code DuplicateEntityFoundException} is thrown.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate deduction exists
	 */
	@Test(expectedExceptions = {DuplicateEntityFoundException.class})
	public void testDuplicateEntityFoundException() 
		throws DuplicateEntityFoundException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Resse",
			"Johns", "Charlie", "Mr.");
		RecurringDeductionCategory category = this
				.recurringDeductionCategoryDelegate.create("Category", 
						(short) 1, true);
		Date reportedDate = this.convertDate("02/11/2017");
		BigDecimal amount = new BigDecimal(363);
		String description = "Description";
		RecurringDeductionFrequency frequency = 
				RecurringDeductionFrequency.WEEKLY;
		this.recurringDeductionDelegate.create(offender, category, reportedDate, 
				description, amount, frequency);
		Date secondReportedDate = this.convertDate("02/10/2017");
		RecurringDeduction recurringDeduction = this.recurringDeductionDelegate
				.create(offender, category, secondReportedDate, description, 
						amount, frequency);		
		
		// Action
		recurringDeduction = this.financialProfileService
				.updateRecurringDeduction(recurringDeduction, category, 
						reportedDate, description, amount, frequency);
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