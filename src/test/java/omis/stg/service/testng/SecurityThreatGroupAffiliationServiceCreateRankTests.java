package omis.stg.service.testng;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.country.domain.Country;
import omis.country.service.delegate.CountryDelegate;
import omis.exception.DuplicateEntityFoundException;
import omis.region.domain.State;
import omis.region.service.delegate.StateDelegate;
import omis.stg.domain.SecurityThreatGroup;
import omis.stg.domain.SecurityThreatGroupRank;
import omis.stg.service.SecurityThreatGroupAffiliationService;
import omis.stg.service.delegate.SecurityThreatGroupDelegate;
import omis.stg.service.delegate.SecurityThreatGroupRankDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.util.PropertyValueAsserter;

/**
 * Tests method to create security threat group ranks.
 *
 * @author Josh Divine
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"stg", "service"})
public class SecurityThreatGroupAffiliationServiceCreateRankTests
	extends AbstractHibernateTransactionalTestNGSpringContextTests {
	
	/* Delegates. */

	@Autowired
	@Qualifier("securityThreatGroupDelegate")
	private SecurityThreatGroupDelegate securityThreatGroupDelegate;
	
	@Autowired
	@Qualifier("stateDelegate")
	private StateDelegate stateDelegate;
	
	@Autowired
	@Qualifier("countryDelegate")
	private CountryDelegate countryDelegate;
	
	@Autowired
	@Qualifier("securityThreatGroupRankDelegate")
	private SecurityThreatGroupRankDelegate securityThreatGroupRankDelegate;
	
	/* Services. */
	@Autowired
	@Qualifier("securityThreatGroupAffiliationService")
	private SecurityThreatGroupAffiliationService 
		securityThreatGroupAffiliationService;

	/* Test methods. */
	
	/**
	 * Tests creation of a security threat group rank.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testCreateRank() throws DuplicateEntityFoundException {
		// Arrangements
		String name = "Rank";
		Country country = this.countryDelegate.create("Country", "C", true);
		State state = this.stateDelegate.create("State", "ST", country, true, 
				true);
		SecurityThreatGroup securityThreatGroup = 
				this.securityThreatGroupDelegate.create("Group", state);

		// Action
		SecurityThreatGroupRank securityThreatGroupRank = 
				this.securityThreatGroupAffiliationService.createRank(name, 
						securityThreatGroup);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("name", name)
			.addExpectedValue("group", securityThreatGroup)
			.performAssertions(securityThreatGroupRank);
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
				String name = "Rank";
				Country country = this.countryDelegate.create("Country", "C", true);
				State state = this.stateDelegate.create("State", "ST", country, true, 
						true);
				SecurityThreatGroup securityThreatGroup = 
						this.securityThreatGroupDelegate.create("Group", state);
				this.securityThreatGroupRankDelegate.create(name, 
						securityThreatGroup);
				
				// Action
				this.securityThreatGroupAffiliationService.createRank(name, 
								securityThreatGroup);

	}

}