package omis.offender.service.testng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.country.domain.Country;
import omis.country.service.delegate.CountryDelegate;
import omis.demographics.domain.Sex;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.offender.service.CreateOffenderService;
import omis.region.domain.City;
import omis.region.domain.State;
import omis.region.service.delegate.StateDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;

/**
 * Tests service to create offenders.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Jun 16, 2016)
 * @since OMIS 3.0
 */
@Test(groups = "offender")
public class CreateOffenderServiceTests
		extends AbstractHibernateTransactionalTestNGSpringContextTests {

	@Autowired
	@Qualifier("createOffenderService")
	private CreateOffenderService createOffenderService;
	
	@Autowired
	@Qualifier("countryDelegate")
	private CountryDelegate countryDelegate;
	
	@Autowired
	@Qualifier("stateDelegate")
	private StateDelegate stateDelegate;
	
	/**
	 * Tests creation of offender.
	 * 
	 * @throws DuplicateEntityFoundException if offender exists
	 * @throws ParseException if birth date cannot be parsed 
	 */
	public void createOffenderTest()
			throws DuplicateEntityFoundException, ParseException {
		final String lastName = "Blofeldt";
		final String firstName = "Ernst";
		final String middleName = "Stavro";
		final String suffix = "Jr";
		final Integer socialSecurityNumber = 123456789;
		final String stateIdNumber = "12345";
		final String birthPlaceName = "Bozeman";
		final Country birthCountry = this.countryDelegate
				.create("United Status", "US", true);
		final State birthState = this.stateDelegate
				.create("Montana", "MT", birthCountry, true, true);
		final City birthPlace = this.createOffenderService
				.createCity(birthPlaceName, birthState, birthCountry);
		final Date birthDate = new SimpleDateFormat("MM/dd/yyyy")
			.parse("02/17/1978");
		final Sex sex = Sex.MALE;
		Offender offender = this.createOffenderService.create(
				lastName, firstName, middleName, suffix, socialSecurityNumber,
				stateIdNumber, birthDate, birthCountry, birthPlace, sex);
		assert lastName.equals(offender.getName().getLastName())
			: String.format("Wrong last name: %s found, expecting %s",
					offender.getName().getLastName(), lastName);
		assert firstName.equals(offender.getName().getFirstName())
			: String.format("Wrong first name: %s found, expecting %s",
					offender.getName().getFirstName(), firstName);
		assert middleName.equals(offender.getName().getMiddleName())
			: String.format("Wrong middle name: %s found, expecting %s",
					offender.getName().getMiddleName(), middleName);
		assert suffix.equals(offender.getName().getSuffix())
			: String.format("Wrong suffix: %s found, expecting %s",
					offender.getName().getSuffix(), suffix);
		assert socialSecurityNumber.equals(offender.getIdentity()
				.getSocialSecurityNumber())
			: String.format("Wrong SSN: %d found, expecting %s",
					offender.getIdentity().getSocialSecurityNumber(),
					socialSecurityNumber);
		assert stateIdNumber.equals(offender.getIdentity()
				.getStateIdNumber())
			: String.format("Wrong SID: %s found, expecting %s",
					offender.getIdentity().getStateIdNumber(),
					stateIdNumber);
		assert birthPlace.equals(offender.getIdentity().getBirthPlace())
			: String.format("Wrong birth place: %s found, expecting %s",
					offender.getIdentity().getBirthPlace().getName(),
					birthPlace.getName());
		assert birthState.equals(offender.getIdentity().getBirthState())
			: String.format("Wrong birth State: %s found, expecting %s",
					offender.getIdentity().getBirthState().getName(),
					birthState.getName());
		assert birthCountry.equals(offender.getIdentity().getBirthCountry())
			: String.format("Wrong birth country: %s found, expecting %s",
					offender.getIdentity().getBirthCountry().getName(),
					birthCountry.getName());
		assert birthDate.equals(offender.getIdentity().getBirthDate())
			: String.format("Wrong birth date: %s found, expecting %s",
					offender.getIdentity().getBirthDate(), birthDate);
		assert sex.equals(offender.getIdentity().getSex())
			: String.format("Wrong sex: %s found, expecting %s",
					offender.getIdentity().getSex(), sex);
	}
}
