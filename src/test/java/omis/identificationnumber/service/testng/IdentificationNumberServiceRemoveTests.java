package omis.identificationnumber.service.testng;

import java.beans.PropertyEditor;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
import omis.identificationnumber.domain.IdentificationNumber;
import omis.identificationnumber.domain.IdentificationNumberCategory;
import omis.identificationnumber.domain.IdentificationNumberIssuer;
import omis.identificationnumber.service.IdentificationNumberService;
import omis.identificationnumber.service.delegate.IdentificationNumberCategoryDelegate;
import omis.identificationnumber.service.delegate.IdentificationNumberDelegate;
import omis.identificationnumber.service.delegate.IdentificationNumberIssuerDelegate;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;

/**
 * Removal of identification number tests.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"identificationNumber", "service", "remove"})
public class IdentificationNumberServiceRemoveTests
		extends AbstractHibernateTransactionalTestNGSpringContextTests {
	
	/* Service delegates. */
	
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	@Qualifier("identificationNumberIssuerDelegate")
	private IdentificationNumberIssuerDelegate
	identificationNumberIssuerDelegate;
	
	@Autowired
	@Qualifier("identificationNumberCategoryDelegate")
	private IdentificationNumberCategoryDelegate
	identificationNumberCategoryDelegate;
	
	@Autowired
	@Qualifier("identificationNumberDelegate")
	private IdentificationNumberDelegate identificationNumberDelegate;
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;
	
	/* Services. */
	
	@Autowired
	@Qualifier("identificationNumberService")
	private IdentificationNumberService identificationNumberService;

	/* Tests. */
	
	/** Tests removal of identification numbers. 
	 * @throws DateConflictException */
	public void testRemoval() throws DateConflictException {
		
		// Arrangements
		Offender scaramanga = this.offenderDelegate
				.createWithoutIdentity("Scaramanga", "Fransisco", null, null);
		IdentificationNumberIssuer uscis;
		try {
			uscis = this.identificationNumberIssuerDelegate
					.create("USCIS", true);
		} catch (DuplicateEntityFoundException e) {
			throw new AssertionError("Issuer exists", e);
		}
		IdentificationNumberCategory alienNumber;
		try {
			alienNumber = this.identificationNumberCategoryDelegate
					.create("Alien #", null, true);
		} catch (DuplicateEntityFoundException e) {
			throw new AssertionError("Category exists", e);
		}
		String scaramangaAlienNumberValue = "6273486";
		Date scaramangeIssueDate = null;
		Date scaramangaExpireDate = this.parseDateText("05/12/2018");
		IdentificationNumber scaramangaAlienNumber;
		try {
			scaramangaAlienNumber = this.identificationNumberDelegate
				.create(scaramanga, uscis, alienNumber,
						scaramangaAlienNumberValue, scaramangeIssueDate,
						scaramangaExpireDate);
		} catch (DuplicateEntityFoundException e) {
			throw new AssertionError("Identification number exists", e);
		}
		Offender blofeld = this.offenderDelegate
				.createWithoutIdentity("Blofeld", "Ernst", null, null);
		String blofeldAlienNumberValue = "51367321";
		Date blofeldIssueDate = this.parseDateText("07/22/2001");
		Date blofeldExpireDate = null;
		IdentificationNumber blofeldAlienNumber;
		try {
			blofeldAlienNumber = this.identificationNumberDelegate
					.create(blofeld, uscis, alienNumber,
							blofeldAlienNumberValue, blofeldIssueDate,
							blofeldExpireDate);
		} catch (DuplicateEntityFoundException e) {
			throw new AssertionError("Identification number exists", e);
		}
		
		// Action
		this.identificationNumberService.remove(blofeldAlienNumber);
		
		// Assertions
		assert !this.identificationNumberDelegate.findByOffender(blofeld)
				.contains(blofeldAlienNumber)
			: "Identification was not removed";
		assert this.identificationNumberDelegate.findByOffender(scaramanga)
				.contains(scaramangaAlienNumber)
			: "Wrong identification number was removed";
	}
	
	/* Helper methods. */
	
	// Parses date text
	private Date parseDateText(final String dateText) {
		PropertyEditor propertyEditor = this.customDateEditorFactory
				.createCustomDateOnlyEditor(true);
		propertyEditor.setAsText(dateText);
		return (Date) propertyEditor.getValue();
	}
}