package omis.separationneed.service.testng;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.relationship.domain.Relationship;
import omis.relationship.exception.ReflexiveRelationshipException;
import omis.separationneed.service.SeparationNeedService;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.util.PropertyValueAsserter;

/**
 * Tests method to create relationships.
 *
 * @author Josh Divine
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class SeparationNeedServiceCreateRelationshipTests
	extends AbstractHibernateTransactionalTestNGSpringContextTests {

	/* Delegates. */

	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	/* Services. */

	@Autowired
	@Qualifier("separationNeedService")
	private SeparationNeedService separationNeedService;

	/* Test methods. */

	/**
	 * Tests the creation of a relationship.
	 * 
	 * @throws ReflexiveRelationshipException 
	 */
	@Test
	public void testCreateRelationship() throws ReflexiveRelationshipException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith", 
				"John", "Jay", null);
		Offender targetOffender = this.offenderDelegate.createWithoutIdentity(
				"Smith", "Jane", "June", null);

		// Action
		Relationship relationship = this.separationNeedService
				.createRelationship(offender, targetOffender);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("firstPerson", offender)
			.addExpectedValue("secondPerson", targetOffender)
			.performAssertions(relationship);
	}

}