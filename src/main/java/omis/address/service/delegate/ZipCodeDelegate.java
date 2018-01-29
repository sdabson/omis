package omis.address.service.delegate;

import java.util.List;

import omis.address.dao.ZipCodeDao;
import omis.address.domain.ZipCode;
import omis.address.exception.ZipCodeExistsException;
import omis.instance.factory.InstanceFactory;
import omis.region.domain.City;
import omis.region.domain.State;

/**
 * Delegate for ZIP codes.
 *
 * @author Stephen Abson
 * @author Yidong Li
 * @version 0.0.1 (Sep 14, 2015)
 * @since OMIS 3.0
 */
public class ZipCodeDelegate {

	/* DAOs. */
	
	private final ZipCodeDao zipCodeDao;
	
	/* Instance Factories. */
	
	private final InstanceFactory<ZipCode> zipCodeInstanceFactory;
	
	/* Constructors. */
	
	/**
	 * Instantiates delegate for ZIP codes.
	 * 
	 * @param zipCodeDao data access object for ZIP codes
	 * @param zipCodeInstanceFactory instance factory for ZIP codes
	 */
	public ZipCodeDelegate(
			final ZipCodeDao zipCodeDao,
			final InstanceFactory<ZipCode> zipCodeInstanceFactory) {
		this.zipCodeDao = zipCodeDao;
		this.zipCodeInstanceFactory = zipCodeInstanceFactory;
	}
	
	/* Methods. */
	
	/**
	 * Creates ZIP code.
	 * 
	 * @param city city
	 * @param value value
	 * @param extension extension
	 * @param active active
	 * @return new ZIP code
	 * @throws ZipCodeExistsException if ZIP code exists
	 */
	public ZipCode create(
			final City city,
			final String value,
			final String extension,
			final Boolean active)
					throws ZipCodeExistsException {
		if (this.zipCodeDao.find(city, value, extension) != null) {
			throw new ZipCodeExistsException("ZIP code exists");
		}
		ZipCode zipCode = this.zipCodeInstanceFactory.createInstance();
		zipCode.setCity(city);
		zipCode.setValue(value);
		zipCode.setExtension(extension);
		zipCode.setValid(active);
		return this.zipCodeDao.makePersistent(zipCode);
	}
	
	/**
	 * Updates ZIP code.
	 * 
	 * @param zipCode ZIP code
	 * @param city city
	 * @param value value
	 * @param extension extension
	 * @param active active
	 * @return updated ZIP code
	 * @throws ZipCodeExistsException ZIP code exists
	 */
	public ZipCode update(
			final ZipCode zipCode,
			final City city,
			final String value,
			final String extension,
			final Boolean active)
					throws ZipCodeExistsException {
		if (this.zipCodeDao.findExcluding(
				city, value, extension, zipCode) != null) {
			throw new ZipCodeExistsException("ZIP code exists");
		}
		zipCode.setCity(city);
		zipCode.setValue(value);
		zipCode.setExtension(extension);
		zipCode.setValid(active);
		return this.zipCodeDao.makePersistent(zipCode);
	}
	
	/**
	 * Removes ZIP code.
	 * 
	 * @param zipCode ZIP code
	 */
	public void remove(final ZipCode zipCode) {
		this.zipCodeDao.makeTransient(zipCode);
	}
	
	/**
	 * Returns ZIP codes by city.
	 * 
	 * @param city city
	 * @return ZIP codes by city
	 */
	public List<ZipCode> findByCity(final City city) {
		return this.zipCodeDao.findInCity(city);
	}
	
	/**
	 * Returns available zip codes in a state.
	 * 
	 * @param state state
	 * @return zip code
	 */
	public List<ZipCode> findInStates(final State state) {
		return this.zipCodeDao.findInStates(state);
	}

}