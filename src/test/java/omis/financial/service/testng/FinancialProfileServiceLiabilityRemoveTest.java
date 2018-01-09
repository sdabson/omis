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
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;

/**
 * Tests financial profile liability removal
 * 
 *@author Yidong Li 
 *@version 0.1.0 (May 25, 2017)
 *@since OMIS 3.0
 *
 */
@Test(groups = {"financial"})
public class FinancialProfileServiceLiabilityRemoveTest
	extends AbstractHibernateTransactionalTestNGSpringContextTests {
	/* Delegate */
	@Autowired
	@Qualifier("financialLiabilityCategoryDelegate")
	private FinancialLiabilityCategoryDelegate financialLiabilityCategoryDelegate;
	
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	/* Service */
	@Autowired
	@Qualifier("financialProfileService")
	private FinancialProfileService financialProfileService;
	
	/* Property editors. */
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory datePropertyEditorFactory;
	
	@Test
	public void testLiabilityRemove() throws DuplicateEntityFoundException{
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Trump",
			"Johns", "Jeff", "Mr.");
		FinancialLiabilityCategory category;
		try {
			category 
				= this.financialLiabilityCategoryDelegate.create("Debt", 
				(short)3433, true);
		} catch (DuplicateEntityFoundException e) {
			throw new RuntimeException("Financial liability category already "
					+ "exists",	e);
		}
		Date reportedDate = this.convertDate("11/11/2017");
		
		// Action
		BigDecimal amount = new BigDecimal(4523);
		FinancialLiability liability;
		try {
			liability = this.financialProfileService.createLiability(offender, 
			category, reportedDate, "Test liability removal", amount); 
		} catch (DuplicateEntityFoundException e) {
			throw new RuntimeException("Financial liability unexpectedly exists", 
				e);
		}
		
		// Action
		this.financialProfileService.removeLiability(liability); 
		
		// Assertion
		assert !this.financialProfileService.findLiabilities(offender)
			.contains(liability): "Liability was not removed!";
	}
	
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
