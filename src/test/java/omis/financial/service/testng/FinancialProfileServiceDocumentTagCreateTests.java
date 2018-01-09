package omis.financial.service.testng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.document.domain.Document;
import omis.document.domain.DocumentTag;
import omis.document.service.delegate.DocumentDelegate;
import omis.exception.DuplicateEntityFoundException;
import omis.financial.service.FinancialProfileService;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.util.PropertyValueAsserter;

/**
 * Tests method to create financial profile service document tag.
 *
 * @author Trevor Isles
 * @version 0.0.1 (August 3, 2017)
 * @since OMIS 3.0
 */
public class FinancialProfileServiceDocumentTagCreateTests 
	extends AbstractHibernateTransactionalTestNGSpringContextTests {

	/* Delegates. */
	
	@Autowired
	@Qualifier("documentDelegate")
	private DocumentDelegate documentDelegate;

	/* Services. */

	@Autowired
	@Qualifier("financialProfileService")
	private FinancialProfileService financialProfileService;

	/* Test methods. */

	/**
	 * Tests creation of a financial profile document tag.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testCreateDocumentTag() throws DuplicateEntityFoundException {
		
		// Arrangements
		Date date = this.parseDateText("01/01/2017");
		
		Document document = this.documentDelegate.create(date, "Test", 
				".jpg", "This is a test");
		
		String name = "Something";

		// Action
		DocumentTag documentTag = this.financialProfileService
				.createDocumentTag(document, name);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("document", document)
			.addExpectedValue("name", name)
			.performAssertions(documentTag);
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
		
		Date date2 = this.parseDateText("01/01/2017");
		
		this.documentDelegate.create(date2, "Test", ".jpg", "This is a test");
		
		Document document = this.documentDelegate.create(date, "Test", 
				".jpg", "This is a test");
		
		String name = "Something";
		
		// Action
		this.financialProfileService.createDocumentTag(document, name);
		
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
