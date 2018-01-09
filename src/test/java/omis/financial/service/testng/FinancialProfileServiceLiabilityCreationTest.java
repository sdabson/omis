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
 * Tests financial profile liability creation 
 *
 * @author Yidong Li
 * @author Josh Divine
 * @version 0.0.2
 * @since OMIS 3.0
 */
@Test(groups = {"financial"})
public class FinancialProfileServiceLiabilityCreationTest
		extends AbstractHibernateTransactionalTestNGSpringContextTests {
	
	/* Delegates. */
	
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	@Qualifier("financialLiabilityCategoryDelegate")
	private FinancialLiabilityCategoryDelegate 
		financialLiabilityCategoryDelegate;
	
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
	 * Tests the creation of a liability.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testCreateLiability() throws DuplicateEntityFoundException{
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Noel",
			"Johns", "Jeff", "Mr.");
		FinancialLiabilityCategory category = this
				.financialLiabilityCategoryDelegate.create("Loan", (short) 1, 
						true);
		Date reportedDate = this.convertDate("05/11/2017");
		String description = "Description";
		BigDecimal amount = new BigDecimal(34);
		
		// Action
		FinancialLiability liability = this.financialProfileService
				.createLiability(offender, category, reportedDate, description, 
						amount); 
		
		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("offender", offender)
			.addExpectedValue("category", category)
			.addExpectedValue("reportedDate", reportedDate)
			.addExpectedValue("description", description)
			.addExpectedValue("amount", amount)
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
		
		// Action
		this.financialProfileService.createLiability(offender, category, 
				reportedDate, description, amount); 
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