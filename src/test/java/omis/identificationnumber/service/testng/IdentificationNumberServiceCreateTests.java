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
import omis.identificationnumber.domain.Multitude;
import omis.identificationnumber.service.IdentificationNumberService;
import omis.identificationnumber.service.delegate.IdentificationNumberCategoryDelegate;
import omis.identificationnumber.service.delegate.IdentificationNumberDelegate;
import omis.identificationnumber.service.delegate.IdentificationNumberIssuerDelegate;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;

/**
 * Creation of identification numbers tests.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"identificationNumber", "service", "create"})
public class IdentificationNumberServiceCreateTests
		extends AbstractHibernateTransactionalTestNGSpringContextTests {
	
	/* Property editor factories */
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;
	
	/* Service delegates */
	
	@Autowired
	@Qualifier("identificationNumberIssuerDelegate")
	private IdentificationNumberIssuerDelegate
	identificationNumberIssuerDelegate;
	
	@Autowired
	@Qualifier("identificationNumberCategoryDelegate")
	private IdentificationNumberCategoryDelegate
	identificationNumberCateoryDelegate;
	
	@Autowired
	@Qualifier("identificationNumberDelegate")
	private IdentificationNumberDelegate identificationNumberDelegate;
	
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	/* Services */
	
	@Autowired
	@Qualifier("identificationNumberService")
	private IdentificationNumberService identificationNumberService;
	
	/* Tests */

	/**
	 * Tests creation of identification number.
	 * 
	 * @throws DuplicateEntityFoundException if identification number exists
	 * @throws DateConflictException 
	 */
	public void testCreation()
			throws DuplicateEntityFoundException, DateConflictException {
		
		// Arrangements
		Offender offender = this.offenderDelegate
				.createWithoutIdentity("Klebb", "Rosa", null, null);
		IdentificationNumberIssuer issuer;
		try {
			issuer = this.identificationNumberIssuerDelegate
					.create("USCIS", true);
		} catch (DuplicateEntityFoundException e) {
			throw new AssertionError("Issuer exists", e);
		}
		IdentificationNumberCategory category;
		try {
			category = this.identificationNumberCateoryDelegate
					.create("Alien Number", null, true);
		} catch (DuplicateEntityFoundException e) {
			throw new AssertionError("Category exists", e);
		}
		String value = "999-999-999";
		Date issueDate = this.parseDateText("09/21/2001");
		Date expireDate = this.parseDateText("12/11/2015");
		
		// Action
		IdentificationNumber identificationNumber
			= this.identificationNumberService.create(
					offender, issuer, category, value, issueDate, expireDate);
		
		// Assertions
		assert offender.equals(identificationNumber.getOffender())
			: String.format("Wrong offender - %s expected; %s found",
					offender, identificationNumber.getOffender());
		assert issuer.equals(identificationNumber.getIssuer())
			: String.format("Wrong issuer - %s expected; %s found",
					issuer, identificationNumber.getIssuer());
		assert category.equals(identificationNumber.getCategory())
			: String.format("Wrong category - %s expected; %s found",
					category, identificationNumber.getCategory());
		assert value.equals(identificationNumber.getValue())
			: String.format("Wrong value - %s expected; %s found",
					value, identificationNumber.getValue());
		assert issueDate.equals(identificationNumber.getIssueDate())
			: String.format("Wrong issue date - %s expected; %s found",
					issueDate, identificationNumber.getIssueDate());
		assert expireDate.equals(identificationNumber.getExpireDate())
			: String.format("Wrong expire date - %s expected; %s found",
					expireDate, identificationNumber.getExpireDate());
	}
	
	/**
	 * Tests duplicate identification number check on creation.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate identification
	 * exists (asserted)
	 * @throws DateConflictException 
	 */
	@Test(expectedExceptions = {DuplicateEntityFoundException.class})
	public void testDuplicateCheck()
			throws DuplicateEntityFoundException, DateConflictException {
		
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity(
				"No", "Julius", null, null);
		IdentificationNumberIssuer issuer;	
		try {
			issuer = this.identificationNumberIssuerDelegate
					.create("MT DMV", true);
		} catch (DuplicateEntityFoundException e) {
			throw new AssertionError("Issuer exists", e);
		}
		IdentificationNumberCategory category;
		try {
			category = this.identificationNumberCateoryDelegate
					.create("DL #", null, true);
		} catch (DuplicateEntityFoundException e) {
			throw new AssertionError("Category exists", e);
		}
		String value = "I12344672";
		Date issueDate = this.parseDateText("12/19/2001");
		Date expireDate = this.parseDateText("06/24/2011");
		this.identificationNumberDelegate.create(
				offender, issuer, category, value, issueDate, expireDate);
		
		// Action
		this.identificationNumberService.create(
				offender, issuer, category, value, issueDate, expireDate);
	}
	
	
	@Test(expectedExceptions = {DateConflictException.class})
	public void testDateConflictCheck()
			throws DuplicateEntityFoundException, DateConflictException {
		
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity(
				"No", "Julius", null, null);
		IdentificationNumberIssuer issuer;	
		try {
			issuer = this.identificationNumberIssuerDelegate
					.create("MT DMV", true);
		} catch (DuplicateEntityFoundException e) {
			throw new AssertionError("Issuer exists", e);
		}
		IdentificationNumberCategory category;
		try {
			category = this.identificationNumberCateoryDelegate
					.create("DL #", Multitude.SINGLE, true);
		} catch (DuplicateEntityFoundException e) {
			throw new AssertionError("Category exists", e);
		}
		String value = "I12344672";
		Date issueDate = this.parseDateText("12/19/2001");
		Date expireDate = this.parseDateText("06/24/2011");
		String value2 = "12345678";
		Date issueDate2 = this.parseDateText("10/31/2001");
		Date expireDate2 = this.parseDateText("11/31/2004");
		this.identificationNumberDelegate.create(
				offender, issuer, category, value, issueDate, expireDate);
		
		// Action
		this.identificationNumberService.create(
				offender, issuer, category, value2, issueDate2, expireDate2);
	}
	
	// Helper methods
	private Date parseDateText(final String dateText) {
		PropertyEditor propertyEditor = this.customDateEditorFactory
				.createCustomDateOnlyEditor(true);
		propertyEditor.setAsText(dateText);
		return (Date) propertyEditor.getValue();
	}
}