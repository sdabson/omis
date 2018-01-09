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
 * Update of identification number tests.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"identificationNumber", "service", "update"})
public class IdentificationNumberServiceUpdateTests
		extends AbstractHibernateTransactionalTestNGSpringContextTests {

	/* Property editor factories. */
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;
	
	/* Service delegates. */
	
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
	
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	/* Services. */
	
	@Autowired
	@Qualifier("identificationNumberService")
	private IdentificationNumberService identificationNumberService;
	
	/* Tests. */
	
	/**
	 * Tests update of identification number.
	 * 
	 * @throws DuplicateEntityFoundException if identification number exists
	 * @throws DateConflictException 
	 */
	public void testUpdate() throws DuplicateEntityFoundException, DateConflictException {
		Offender offender = this.offenderDelegate
				.createWithoutIdentity("Blofeld", "Ernst", null, null);
		IdentificationNumberIssuer gallatinCounty;
		try {
			gallatinCounty = this.identificationNumberIssuerDelegate
					.create("Gallatin County", true);
		} catch (DuplicateEntityFoundException e) {
			throw new AssertionError("Issuer exists", e);
		}
		IdentificationNumberCategory courtNumberCategory;
		try {
			courtNumberCategory = this.identificationNumberCategoryDelegate
					.create("Court #", null, true);
		} catch (DuplicateEntityFoundException e) {
			throw new AssertionError("Court #", e);
		}
		String courtNumberValue = "DK-1233-1";
		Date courtNumberIssueDate = this.parseDateText("11/02/1993");
		Date courtNumberExpireDate = this.parseDateText("05/23/2003");
		IdentificationNumber identificationNumber
			= this.identificationNumberDelegate.create(offender, gallatinCounty,
					courtNumberCategory, courtNumberValue, courtNumberIssueDate,
					courtNumberExpireDate);
		IdentificationNumberIssuer dmv;
		try {
			dmv = this.identificationNumberIssuerDelegate
					.create("DMV", true);
		} catch (DuplicateEntityFoundException e) {
			throw new AssertionError("Blaine County", e);
		}
		IdentificationNumberCategory dlNumberCategory;
		try {
			dlNumberCategory = this.identificationNumberCategoryDelegate
					.create("DL #", null, true);
		} catch (DuplicateEntityFoundException e) {
			throw new DuplicateEntityFoundException("Category exists", e);
		}
		String dlNumber = "123245363564";
		Date dlNumberIssueDate = this.parseDateText("03/23/1990");
		Date dlNumberExpireDate = this.parseDateText("06/23/1999");
		
		// Action
		identificationNumber = this.identificationNumberService.update(
				identificationNumber, dmv, dlNumberCategory, dlNumber,
				dlNumberIssueDate, dlNumberExpireDate);
		
		// Assertions
		assert offender.equals(identificationNumber.getOffender())
			: String.format("Wrong offender: %s expected; %s found",
					offender, identificationNumber.getOffender());
		assert dmv.equals(identificationNumber.getIssuer())
			: String.format("Wrong issuer: %s expected; %s found",
					dmv, identificationNumber.getIssuer());
		assert dlNumberCategory.equals(identificationNumber.getCategory())
			: String.format("Wrong category: %s expected; %s found",
					dlNumberCategory, identificationNumber.getCategory());
		assert dlNumber.equals(identificationNumber.getValue())
			: String.format("Wrong value: %s expected; %s found",
					dlNumber, identificationNumber.getValue());
		assert dlNumberIssueDate.equals(identificationNumber.getIssueDate())
			: String.format("Wrong issue date: %s expected; %s found",
					dlNumberIssueDate, identificationNumber.getIssueDate());
		assert dlNumberExpireDate.equals(identificationNumber.getExpireDate())
			: String.format("Wrong issue date: %s expected; %s found",
					dlNumberExpireDate, identificationNumber.getExpireDate());
	}
	
	/**
	 * Tests duplicate identification check on update.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate identification number
	 * exists (asserted)
	 * @throws DateConflictException 
	 */
	@Test(expectedExceptions = {DuplicateEntityFoundException.class})
	public void testDuplicateCheck()
			throws DuplicateEntityFoundException, DateConflictException {
		
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity(
				"Blofeld", "Ernst", null, null);
		IdentificationNumberIssuer dmv;
		try {
			dmv = this.identificationNumberIssuerDelegate.create("DMV", true);
		} catch (DuplicateEntityFoundException e) {
			throw new AssertionError("Isser exists", e);
		}
		IdentificationNumberCategory dlNumber;
		try {
			dlNumber = this.identificationNumberCategoryDelegate
					.create("DL Number", null, true);
		} catch (DuplicateEntityFoundException e) {
			throw new AssertionError("Category exists", e);
		}
		String dlNumberValue = "1234567";
		Date dlNumberIssueDate = this.parseDateText("05/12/2001");
		Date dlNumberExpireDate = this.parseDateText("12/06/2010");
		IdentificationNumber identificationNumber;
		try {
			identificationNumber = this.identificationNumberDelegate.create(
					offender, dmv, dlNumber, dlNumberValue,
					dlNumberIssueDate, dlNumberExpireDate);
		} catch (DuplicateEntityFoundException e) {
			throw new AssertionError("Identification number exists", e);
		}
		String otherDlNumberValue = "987654321";
		try {
			this.identificationNumberDelegate.create(offender, dmv, dlNumber,
					otherDlNumberValue, dlNumberIssueDate, dlNumberExpireDate);
		} catch (DuplicateEntityFoundException e) {
			throw new AssertionError("Other identification number exists", e);
		}
		
		// Action
		this.identificationNumberService.update(identificationNumber, dmv,
				dlNumber, otherDlNumberValue, dlNumberIssueDate,
				dlNumberExpireDate);
	}
	
	@Test(expectedExceptions = {DateConflictException.class})
	public void testDateConflictCheck()
			throws DuplicateEntityFoundException, DateConflictException {
		
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity(
				"Blofeld", "Ernst", null, null);
		IdentificationNumberIssuer dmv;
		try {
			dmv = this.identificationNumberIssuerDelegate.create("DMV", true);
		} catch (DuplicateEntityFoundException e) {
			throw new AssertionError("Isser exists", e);
		}
		IdentificationNumberCategory dlNumber;
		try {
			dlNumber = this.identificationNumberCategoryDelegate
					.create("DL Number", Multitude.SINGLE, true);
		} catch (DuplicateEntityFoundException e) {
			throw new AssertionError("Category exists", e);
		}
		String dlNumberValue = "1234567";
		Date dlNumberIssueDate0 = this.parseDateText("05/12/1989");
		Date dlNumberExpireDate0 = this.parseDateText("11/24/1998");
		Date dlNumberIssueDate1 = this.parseDateText("05/12/2001");
		Date dlNumberExpireDate1 = this.parseDateText("12/06/2010");
		Date dlNumberIssueDate2 = this.parseDateText("11/25/1998");
		Date dlNumberExpireDate2 = this.parseDateText("05/17/2007");
		IdentificationNumber identificationNumber;
		try {
			identificationNumber = this.identificationNumberDelegate.create(
					offender, dmv, dlNumber, dlNumberValue,
					dlNumberIssueDate0, dlNumberExpireDate0);
		} catch (DuplicateEntityFoundException e) {
			throw new AssertionError("Identification number exists", e);
		}
		String otherDlNumberValue = "987654321";
		try {
			this.identificationNumberDelegate.create(offender, dmv, dlNumber,
					otherDlNumberValue, dlNumberIssueDate1, dlNumberExpireDate1);
		} catch (DuplicateEntityFoundException e) {
			throw new AssertionError("Other identification number exists", e);
		}
		
		// Action
		this.identificationNumberService.update(identificationNumber, dmv,
				dlNumber, dlNumberValue, dlNumberIssueDate2,
				dlNumberExpireDate2);
	}
	
	/**
	 * Tests that target of duplicate identification number update is excluded
	 * from duplicate check.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate identification number
	 * exists (test fails)
	 * @throws DateConflictException 
	 */
	public void testUpdateDuplicateCheckExcludesSelf()
			throws DuplicateEntityFoundException, DateConflictException {
		
		// Arrangements
		Offender offender = this.offenderDelegate
				.createWithoutIdentity("Blofeld", "Ernst", null, null);
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
		String alienNumberValue = "999-999-999";
		Date alienNumberIssueDate = this.parseDateText("12/31/2009");
		Date alienNumberExpireDate = null;
		IdentificationNumber identificationNumber;
		try {
			identificationNumber = this.identificationNumberDelegate
					.create(offender, uscis, alienNumber, alienNumberValue,
							alienNumberIssueDate, alienNumberExpireDate);
		} catch (DuplicateEntityFoundException e) {
			throw new AssertionError("Identification number exists", e);
		}
		
		// Action
		this.identificationNumberService.update(identificationNumber, uscis,
				alienNumber, alienNumberValue, alienNumberIssueDate,
				alienNumberExpireDate);
	}
	
	public void testUpdateDateConflictCheckExcludesSelf()
			throws DuplicateEntityFoundException, DateConflictException {
		
		// Arrangements
		Offender offender = this.offenderDelegate
				.createWithoutIdentity("Blofeld", "Ernst", null, null);
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
					.create("Alien #", Multitude.SINGLE, true);
		} catch (DuplicateEntityFoundException e) {
			throw new AssertionError("Category exists", e);
		}
		String alienNumberValue = "999-999-999";
		Date alienNumberIssueDate = this.parseDateText("12/31/2009");
		Date alienNumberExpireDate = null;
		Date alienNumberIssueDate2 = this.parseDateText("12/31/2010");
		Date alienNumberExpireDate2 = this.parseDateText("01/21/2021");
		IdentificationNumber identificationNumber;
		try {
			identificationNumber = this.identificationNumberDelegate
					.create(offender, uscis, alienNumber, alienNumberValue,
							alienNumberIssueDate, alienNumberExpireDate);
		} catch (DuplicateEntityFoundException e) {
			throw new AssertionError("Identification number exists", e);
		}
		
		// Action
		this.identificationNumberService.update(identificationNumber, uscis,
				alienNumber, alienNumberValue, alienNumberIssueDate2,
				alienNumberExpireDate2);
	}
	
	/* Helper methods. */
	
	// Parses date
	private Date parseDateText(final String dateText) {
		PropertyEditor propertyEditor = this.customDateEditorFactory
				.createCustomDateOnlyEditor(true);
		propertyEditor.setAsText(dateText);
		return (Date) propertyEditor.getValue();
	}
}