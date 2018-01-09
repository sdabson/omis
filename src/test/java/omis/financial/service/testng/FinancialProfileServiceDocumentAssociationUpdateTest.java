package omis.financial.service.testng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.document.domain.Document;
import omis.document.service.delegate.DocumentDelegate;
import omis.exception.DuplicateEntityFoundException;
import omis.financial.domain.FinancialDocumentAssociation;
import omis.financial.service.FinancialProfileService;
import omis.financial.service.delegate.FinancialDocumentAssociationDelegate;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.util.PropertyValueAsserter;

/**
 * Tests method to update financial profile service document association.
 *
 * @author Trevor Isles
 * @version 0.0.1 (August 2, 2017)
 * @since OMIS 3.0
 */
public class FinancialProfileServiceDocumentAssociationUpdateTest 
	extends AbstractHibernateTransactionalTestNGSpringContextTests {
	
	/* Service Delegates */
	
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
		
	@Autowired
	@Qualifier("documentDelegate")
	private DocumentDelegate documentDelegate;
	
	@Autowired
	@Qualifier("financialDocumentAssociationDelegate")
	private FinancialDocumentAssociationDelegate 
		financialDocumentAssociationDelegate;
	
	/* Service to test. */
	
	@Autowired
	@Qualifier("financialProfileService")
	private FinancialProfileService financialProfileService;
	
	/* Test methods. */
	
	/**
	 * Tests update of a financial profile document association.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateFinancialDocumentAssociation()
			throws DuplicateEntityFoundException {
		
		// Arrangements		
		Date date = this.parseDateText("01/01/2017");
		
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith", 
				"Joseph", "No", "Mr");
		
		Document document = this.documentDelegate.create(date, "Test", 
				".jpg", "This is a test");
		
		FinancialDocumentAssociation financialDocumentAssociation
		= this.financialDocumentAssociationDelegate.createDocument(offender, 
				document);
		
		// Action
		financialDocumentAssociation = this.financialProfileService
				.updateFinancialDocument(financialDocumentAssociation, 
						document);
		
		// Assertions
		PropertyValueAsserter.create()
		.addExpectedValue("offender", offender)
		.addExpectedValue("document", document)
		.performAssertions(financialDocumentAssociation);
		
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
		Date date = this.parseDateText("01/01/2017");
		
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith", 
				"Joseph", "No", "Mr");
		
		Document document = this.documentDelegate.create(date, "Test", 
				".jpg", "This is a test");
		
		Document document2 = this.documentDelegate.create(date, "Test2", 
				".jpg", "This is a test");
		
		this.financialDocumentAssociationDelegate.createDocument(offender, 
				document);
		
		FinancialDocumentAssociation financialDocumentAssociation
		= this.financialDocumentAssociationDelegate.createDocument(offender, 
				document2);
		
		// Action
		this.financialProfileService.updateFinancialDocument(
				financialDocumentAssociation, document);
		
	}
	
	// Parses date text
	
		private Date parseDateText(final String text) {
			try {
				return new SimpleDateFormat("MM/dd/yyyy").parse(text);
			} catch (ParseException e) {
				throw new RuntimeException("Parse error", e);
			}
		} 

}
