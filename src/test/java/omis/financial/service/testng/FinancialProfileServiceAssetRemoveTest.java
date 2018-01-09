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

/**
 * Tests financial profile asset removal
 * 
 *@author Yidong Li 
 *@version 0.1.0 (May 24, 2017)
 *@since OMIS 3.0
 *
 */
@Test(groups = {"financial"})
public class FinancialProfileServiceAssetRemoveTest
	extends AbstractHibernateTransactionalTestNGSpringContextTests {
	/* Delegate */
	@Autowired
	@Qualifier("financialAssetCategoryDelegate")
	private FinancialAssetCategoryDelegate financialAssetCategoryDelegate;
	
	@Autowired
	@Qualifier("financialAssetDelegate")
	private FinancialAssetDelegate financialAssetDelegate;
	
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
	public void testAssetRemove() throws DuplicateEntityFoundException{
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Li",
			"Kevin", "asga", "Mr.");
		FinancialAssetCategory category;
		try {
			category 	= this.financialAssetCategoryDelegate.create("Cash", 
				(short)2341, true);
		} catch (DuplicateEntityFoundException e) {
			throw new RuntimeException("Financial asset category already "
					+ "exists",	e);
		}
		Date reportedDate = this.convertDate("09/23/2017");
		
		
		BigDecimal amount = new BigDecimal(1232345);
		FinancialAsset asset;
		try {
			asset = this.financialProfileService.createAsset(offender, 
				category, reportedDate, "Test asset removal", amount);
		} catch (DuplicateEntityFoundException e) {
			throw new RuntimeException("Asset unexpectedly exists", e);
		}
		
		// Action
		this.financialProfileService.removeAsset(asset); 
		
		// Assertion
		assert !this.financialProfileService.findAssets(offender)
			.contains(asset): "Asset was not removed!";
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
