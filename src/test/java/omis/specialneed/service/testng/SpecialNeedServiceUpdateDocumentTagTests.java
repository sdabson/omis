package omis.specialneed.service.testng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.document.domain.Document;
import omis.document.domain.DocumentTag;
import omis.document.service.delegate.DocumentDelegate;
import omis.document.service.delegate.DocumentTagDelegate;
import omis.exception.DuplicateEntityFoundException;
import omis.specialneed.service.SpecialNeedService;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.util.PropertyValueAsserter;

/**
 * Tests method to update document tags.
 *
 * @author Josh Divine
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class SpecialNeedServiceUpdateDocumentTagTests
	extends AbstractHibernateTransactionalTestNGSpringContextTests {

	/* Delegates. */

	@Autowired
	@Qualifier("documentDelegate")
	private DocumentDelegate documentDelegate;

	@Autowired
	@Qualifier("documentTagDelegate")
	private DocumentTagDelegate documentTagDelegate;
	
	/* Services. */

	@Autowired
	@Qualifier("specialNeedService")
	private SpecialNeedService specialNeedService;

	/* Test methods. */

	/**
	 * Tests the update of the name for a document tag.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateDocumentTagName() 
			throws DuplicateEntityFoundException {
		// Arrangements
		String name = "Name";
		Date date = this.parseDateText("01/01/2017");
		String title = "Title";
		String filename = "filename";
		String fileExtension = "pdf";
		Document document = this.documentDelegate.create(date, filename, 
				fileExtension, title);
		DocumentTag documentTag = this.documentTagDelegate.tagDocument(document, 
				name);
		String newName = "New name";

		// Action
		documentTag = this.specialNeedService.updateDocumentTag(documentTag, 
				newName);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("name", newName)
			.performAssertions(documentTag);
	}

	/**
	 * Tests the update of the title for a document.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test(expectedExceptions = {DuplicateEntityFoundException.class})
	public void testDuplicateEntityFoundException() 
			throws DuplicateEntityFoundException {
		// Arrangements
		String name = "Name";
		Date date = this.parseDateText("01/01/2017");
		String title = "Title";
		String filename = "filename";
		String fileExtension = "pdf";
		Document document = this.documentDelegate.create(date, filename, 
				fileExtension, title);
		this.documentTagDelegate.tagDocument(document, name);
		String secondName = "Second name";
		DocumentTag documentTag = this.documentTagDelegate.tagDocument(document, 
				secondName);
		

		// Action
		documentTag = this.specialNeedService.updateDocumentTag(documentTag, 
				name);
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