package omis.workrestriction.service.testng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.audit.domain.AuthorizationSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.person.domain.Person;
import omis.person.service.delegate.PersonDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.user.domain.UserAccount;
import omis.user.service.delegate.UserAccountDelegate;
import omis.workrestriction.domain.WorkRestriction;
import omis.workrestriction.domain.WorkRestrictionCategory;
import omis.workrestriction.service.WorkRestrictionService;
import omis.workrestriction.service.delegate.WorkRestrictionCategoryDelegate;

/**
 * WorkRestrictionUpdateTests.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Aug 18, 2016)
 *@since OMIS 3.0
 *
 */
@Test(groups={"workRestriction"})
public class WorkRestrictionUpdateTests 
	extends AbstractHibernateTransactionalTestNGSpringContextTests {
	
	@Autowired
	@Qualifier("workRestrictionService")
	private WorkRestrictionService workRestrictionService;
	
	@Autowired
	@Qualifier("userAccountDelegate")
	private UserAccountDelegate userAccountDelegate;
	
	@Autowired
	@Qualifier("personDelegate")
	private PersonDelegate personDelegate;
	
	@Autowired
	@Qualifier("workRestrictionCategoryDelegate")
	private WorkRestrictionCategoryDelegate categoryDelegate;
	
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	@Test
	public void testUpdate() throws DuplicateEntityFoundException{
		
		final Person pers = this.personDelegate.create("bip", "bop", 
				"boop", null);
		final UserAccount user = this.userAccountDelegate
				.create(pers, "ooserName", "pass", 
						this.parseDateText("01/01/1901"), 
						(Integer) 2, true);
		final Offender offender = this.offenderDelegate.createWithoutIdentity(
				"lastNameOffender", "firstNameOffender", "middleNameOffender", 
				null);
		
		WorkRestriction workRestriction 
			= this.workRestrictionService.create(offender, 
					this.categoryDelegate.create("cat", true),
					new AuthorizationSignature(user, new Date()), "blub");
		
		final Person person = this.personDelegate.create("Last", "First", 
				"Middle", null);
		final UserAccount authorizedByUser = this.userAccountDelegate
				.create(person, "USERNAME", "password1", new Date(), 
						(Integer) 0, true);
		final Date authorizationDate = this.parseDateText("08/01/2016");
		final AuthorizationSignature authorizationSignature 
			= new AuthorizationSignature(authorizedByUser, authorizationDate);
		final WorkRestrictionCategory category 
			= this.categoryDelegate.create("categoryTest", true);
		
		final String notes = "testNotes";
		
		workRestriction = this.workRestrictionService.update(
				workRestriction, category, authorizationSignature, notes);
		
		assert offender.equals(workRestriction.getOffender())
		: String.format("Wrong offender found. #%d found; #%d expected.", 
				workRestriction.getOffender().getOffenderNumber(), 
				offender.getOffenderNumber());
		assert category.equals(workRestriction.getCategory())
		: String.format("Wrong category found. %s found, %s expected.", 
				workRestriction.getCategory().getName(), category.getName());
		assert authorizationSignature.equals(workRestriction
				.getAuthorizationSignature())
		: String.format("Wrong authorization signature found. %s - %s found, "
				+ "%s - %s expected.",
				workRestriction.getAuthorizationSignature().getUserAccount()
					.getUsername(), 
				workRestriction.getAuthorizationSignature().getDate(),
				authorizationSignature.getUserAccount().getUsername(), 
				authorizationSignature.getDate());
		assert notes.equals(workRestriction.getNotes())
		: String.format("Wrong notes found. %s found, %s expected.", 
				workRestriction.getNotes(), notes);
	}
	
	private Date parseDateText(final String dateText) {
		try {
			return new SimpleDateFormat("MM/dd/yyyy").parse(dateText);
		} catch (ParseException e) {
			throw new RuntimeException("Error parsing date", e);
		}
	}
	
	
}
