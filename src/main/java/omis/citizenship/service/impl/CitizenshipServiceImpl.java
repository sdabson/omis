package omis.citizenship.service.impl;

import java.util.Date;
import java.util.List;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.citizenship.dao.CitizenshipDao;
import omis.citizenship.domain.Citizenship;
import omis.citizenship.service.CitizenshipService;
import omis.country.dao.CountryDao;
import omis.country.domain.Country;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;
import omis.user.domain.UserAccount;

/**
 * Implementation of service for citizenship.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 24, 2014)
 * @since OMIS 3.0
 */
public class CitizenshipServiceImpl
		implements CitizenshipService {

	private final CitizenshipDao citizenshipDao;
	
	private final InstanceFactory<Citizenship> citizenshipInstanceFactory;

	private final CountryDao countryDao;
	
	/**
	 * Instantiates an implementation of citizenship service.
	 * 
	 * @param citizenshipDao data access object for citizenships
	 * @param citizenshipInstanceFactory instance factory for citizenships
	 * @param countryDao data access object for countries
	 */
	public CitizenshipServiceImpl(
			final CitizenshipDao citizenshipDao,
			final InstanceFactory<Citizenship> citizenshipInstanceFactory,
			final CountryDao countryDao) {
		this.citizenshipDao = citizenshipDao;
		this.citizenshipInstanceFactory = citizenshipInstanceFactory;
		this.countryDao = countryDao;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setCountryOfCitizenship(final Offender offender,
			final Country country, final UserAccount userAccount,
			final Date date) {
		Citizenship citizenship = this.citizenshipDao.findByOffender(offender);
		if (citizenship == null) {
			citizenship = this.citizenshipInstanceFactory
					.createInstance();
			citizenship.setCreationSignature(
					new CreationSignature(userAccount, date));
			citizenship.setOffender(offender);
		}
		citizenship.setUpdateSignature(new UpdateSignature(userAccount, date));
		citizenship.setCountry(country);
		this.citizenshipDao.makePersistent(citizenship);
	}

	/** {@inheritDoc} */
	@Override
	public Country getCountryOfCitizenship(final Offender offender) {
		Citizenship citizenship = this.citizenshipDao.findByOffender(offender);
		if (citizenship != null) {
			return citizenship.getCountry();
		} else {
			return null;
		}
	}

	/** {@inheritDoc} */
	@Override
	public void removeCountryOfCitizenship(final Offender offender) {
		Citizenship citizenship = this.citizenshipDao.findByOffender(offender);
		if (citizenship != null) {
			this.citizenshipDao.makeTransient(citizenship);
		}
	}

	/** {@inheritDoc} */
	@Override
	public List<Country> findCountries() {
		return this.countryDao.findAll();
	}
}