package omis.financial.service.testng;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.testng.annotations.Test;

import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.exception.DuplicateEntityFoundException;
import omis.financial.domain.FinancialLiability;
import omis.financial.domain.FinancialLiabilityCategory;
import omis.financial.service.FinancialProfileService;
import omis.financial.service.delegate.FinancialLiabilityCategoryDelegate;
import omis.financial.service.delegate.FinancialLiabilityDelegate;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.util.PropertyValueAsserter;

/**
 * Tests "update" of financial profile liability
 *
 * @author Yidong Li
 * @author Josh Divine
 * @version 0.0.2
 * @since OMIS 3.0
 */
@Test(groups = {"financial"})
public class FinancialProfileServiceLiabilityUpdateTest
		extends AbstractHibernateTransactionalTestNGSpringContextTests {
	
	/* Delegates. */
	
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	@Qualifier("financialLiabilityCategoryDelegate")
	private FinancialLiabilityCategoryDelegate financialLiabilityCategoryDelegate;
	
	@Autowired
	@Qualifier("financialLiabilityDelegate")
	private FinancialLiabilityDelegate financialLiabilityDelegate;
	
	/* Service to test. */
	
	@Autowired
	@Qualifier("financialProfileService")
	private FinancialProfileService financialProfileService;
	
	/* Property editors. */
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory datePropertyEditorFactory;
	
	/**
	 * Tests the update of the category for a liability.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateLiabilityCategory() 
			throws DuplicateEntityFoundException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Noel",
			"Johns", "Jeff", "Mr.");
		FinancialLiabilityCategory category = this
				.financialLiabilityCategoryDelegate.create("Loan", (short) 1, 
						true);
		Date reportedDate = this.convertDate("05/11/2017");
		String description = "Description";
		BigDecimal amount = new BigDecimal(34);
		FinancialLiability liability = this.financialLiabilityDelegate.create(
				offender, category, description, amount, reportedDate);
		FinancialLiabilityCategory newCategory = this
				.financialLiabilityCategoryDelegate.create("Category", 
						(short) 2, true);
		
		// Action
		this.financialProfileService.updateLiability(liability, offender, 
				newCategory, reportedDate, description, amount);
		
		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("offender", offender)
			.addExpectedValue("category", newCategory)
			.addExpectedValue("reportedDate", reportedDate)
			.addExpectedValue("description", description)
			.addExpectedValue("amount", amount)
			.performAssertions(liability);
	}
	
	/**
	 * Tests the update of the reported date for a liability.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateLiabilityReportedDate() 
			throws DuplicateEntityFoundException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Noel",
			"Johns", "Jeff", "Mr.");
		FinancialLiabilityCategory category = this
				.financialLiabilityCategoryDelegate.create("Loan", (short) 1, 
						true);
		Date reportedDate = this.convertDate("05/11/2017");
		String description = "Description";
		BigDecimal amount = new BigDecimal(34);
		FinancialLiability liability = this.financialLiabilityDelegate.create(
				offender, category, description, amount, reportedDate);
		Date newReportedDate = this.convertDate("05/12/2017");
		
		// Action
		this.financialProfileService.updateLiability(liability, offender, 
				category, newReportedDate, description, amount);
		
		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("offender", offender)
			.addExpectedValue("category", category)
			.addExpectedValue("reportedDate", newReportedDate)
			.addExpectedValue("description", description)
			.addExpectedValue("amount", amount)
			.performAssertions(liability);
	}
	
	/**
	 * Tests the update of the description for a liability.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateLiabilityDescription() 
			throws DuplicateEntityFoundException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Noel",
			"Johns", "Jeff", "Mr.");
		FinancialLiabilityCategory category = this
				.financialLiabilityCategoryDelegate.create("Loan", (short) 1, 
						true);
		Date reportedDate = this.convertDate("05/11/2017");
		String description = "Description";
		BigDecimal amount = new BigDecimal(34);
		FinancialLiability liability = this.financialLiabilityDelegate.create(
				offender, category, description, amount, reportedDate);
		String newDescription = "New description";
		
		// Action
		this.financialProfileService.updateLiability(liability, offender, 
				category, reportedDate, newDescription, amount);
		
		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("offender", offender)
			.addExpectedValue("category", category)
			.addExpectedValue("reportedDate", reportedDate)
			.addExpectedValue("description", newDescription)
			.addExpectedValue("amount", amount)
			.performAssertions(liability);
	}
	
	/**
	 * Tests the update of the amount for a liability.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateLiabilityAmount() 
			throws DuplicateEntityFoundException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Noel",
			"Johns", "Jeff", "Mr.");
		FinancialLiabilityCategory category = this
				.financialLiabilityCategoryDelegate.create("Loan", (short) 1, 
						true);
		Date reportedDate = this.convertDate("05/11/2017");
		String description = "Description";
		BigDecimal amount = new BigDecimal(34);
		FinancialLiability liability = this.financialLiabilityDelegate.create(
				offender, category, description, amount, reportedDate);
		BigDecimal newAmount = new BigDecimal(343);
		
		// Action
		this.financialProfileService.updateLiability(liability, offender, 
				category, reportedDate, description, newAmount);
		
		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("offender", offender)
			.addExpectedValue("category", category)
			.addExpectedValue("reportedDate", reportedDate)
			.addExpectedValue("description", description)
			.addExpectedValue("amount", newAmount)
			.performAssertions(liability);
	}
	
	/**
	 * Tests {@code DuplicateEntityFoundException} is thrown.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test(expectedExceptions = {DuplicateEntityFoundException.class})
	public void testDuplicateEntityFoundException()
		throws DuplicateEntityFoundException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Noel",
			"Johns", "Jeff", "Mr.");
		FinancialLiabilityCategory category = this
				.financialLiabilityCategoryDelegate.create("Loan", (short) 1, 
						true);
		Date reportedDate = this.convertDate("05/11/2017");
		String description = "Description";
		BigDecimal amount = new BigDecimal(34);
		this.financialLiabilityDelegate.create(offender, category, description, 
				amount, reportedDate);
		Date secondReportedDate = this.convertDate("05/12/2017");
		FinancialLiability liability = this.financialLiabilityDelegate.create(
				offender, category, description, amount, secondReportedDate);
		
		// Action
		this.financialProfileService.updateLiability(liability, offender, 
				category, reportedDate, description, amount);
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