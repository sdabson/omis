package omis.offender.service.delegate.testng;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.offender.exception.OffenderExistsException;
import omis.offender.service.delegate.OffenderDelegate;
import omis.person.domain.Person;
import omis.person.service.delegate.PersonDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;

/**
 * Tests conversion of person to offender in delegate.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"offender", "delegate"})
public class OffenderDelegateConversionTests
		extends AbstractHibernateTransactionalTestNGSpringContextTests {
	
	@Autowired
	@Qualifier("personDelegate")
	private PersonDelegate personDelegate;
	
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;

	/**
	 * Tests conversion of person to offender.
	 * 
	 * <p>See {@link OffenderDelegate#convertPerson(Person)}
	 * 
	 * @throws OffenderExistsException if person is an offender (not
	 * expected)
	 */
	public void testConvertPerson()
			throws OffenderExistsException {
		
		// Arrangements
		Person person = this.personDelegate.create(
				"Carver", "Elliot", null, null);
		
		// Action
		this.offenderDelegate.convertPerson(person);
		
		// Assertion
		assert this.offenderDelegate.isOffender(person)
			: "Person not converted to offender";
	}
	
	/**
	 * Tests that conversion of existing offender fails.
	 * 
	 * <p>See {@link OffenderDelegate#convertPerson(Person)}
	 * 
	 * @throws OffenderExistsException thrown if conversion is of existing
	 * offender (asserted)
	 */
	@Test(expectedExceptions = {OffenderExistsException.class})
	public void testConvertOffenderFails()
			throws OffenderExistsException {
		
		// Arrangements
		Person offender = this.offenderDelegate.createWithoutIdentity(
				"Carver", "Elliot", null, null);
		
		// Action
		this.offenderDelegate.convertPerson(offender);
	}
}