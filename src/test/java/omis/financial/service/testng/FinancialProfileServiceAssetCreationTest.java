package omis.financial.service.testng;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.testng.annotations.Test;

import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.exception.DuplicateEntityFoundException;
import omis.financial.domain.FinancialAsset;
import omis.financial.domain.FinancialAssetCategory;
import omis.financial.service.FinancialProfileService;
import omis.financial.service.delegate.FinancialAssetCategoryDelegate;
import omis.financial.service.delegate.FinancialAssetDelegate;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.util.PropertyValueAsserter;

/**
 * Tests financial profile asset creation 
 *
 * @author Yidong Li
 * @author Josh Divine
 * @version 0.0.2
 * @since OMIS 3.0
 */
@Test(groups = {"financial"})
public class FinancialProfileServiceAssetCreationTest
		extends AbstractHibernateTransactionalTestNGSpringContextTests {
	
	/* Delegates. */
	
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	@Qualifier("financialAssetCategoryDelegate")
	private FinancialAssetCategoryDelegate financialAssetCategoryDelegate;
	
	@Autowired
	@Qualifier("financialAssetDelegate")
	private FinancialAssetDelegate financialAssetDelegate;
	
	/* Service to test. */
	
	@Autowired
	@Qualifier("financialProfileService")
	private FinancialProfileService financialProfileService;
	
	/* Property editors. */
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory datePropertyEditorFactory;
	
	/**
	 * Tests the creation of asset.
	 */
	@Test
	public void testCreateAsset() throws DuplicateEntityFoundException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Obama",
			"Kevin", "Johns", "Mr.");
		FinancialAssetCategory category = this.financialAssetCategoryDelegate
				.create("Gold", (short)1343, 
					true);
		Date reportedDate = this.convertDate("05/23/2017");
		BigDecimal amount = new BigDecimal(123);
		String description = "TestCreate";
		
		// Action
		FinancialAsset asset = this.financialProfileService.createAsset(offender, 
				category, reportedDate, description, amount);
		
		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("offender", offender)
			.addExpectedValue("category", category)
			.addExpectedValue("reportedDate", reportedDate)
			.addExpectedValue("description", description)
			.addExpectedValue("amount", amount)
			.performAssertions(asset);
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
	
	/**
	 * Tests {@code DuplicateEntityFoundException} is thrown.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate asset exists
	 */
	@Test(expectedExceptions = {DuplicateEntityFoundException.class})
	public void testDuplicateEntityFoundException() 
			throws DuplicateEntityFoundException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Obama",
			"Kevin", "Johns", "Mr.");
		FinancialAssetCategory category = this.financialAssetCategoryDelegate
				.create("Gold", (short)1343, 
					true);
		Date reportedDate = this.convertDate("05/23/2017");
		BigDecimal amount = new BigDecimal(123);
		String description = "TestCreate";
		this.financialAssetDelegate.create(offender, category, description, 
				amount, reportedDate);
		
		// Action
		this.financialProfileService.createAsset(offender, category, 
				reportedDate, description, amount);
}
}