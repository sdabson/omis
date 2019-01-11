/*
 * OMIS - Offender Management Information System
 * Copyright (C) 2011 - 2017 State of Montana
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
 * @version 0.0.1 (Jan 8, 2019)
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
			final Boolean active) throws ZipCodeExistsException {
		ZipCode existingZipCode = this.zipCodeDao.find(city, value, extension);
		if (existingZipCode != null) {
			throw new ZipCodeExistsException(existingZipCode);
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
			final Boolean active) throws ZipCodeExistsException {
		ZipCode existingZipCode = this.zipCodeDao.findExcluding(city, value,
			extension, zipCode);
		if (existingZipCode != null) {
			throw new ZipCodeExistsException(existingZipCode);
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