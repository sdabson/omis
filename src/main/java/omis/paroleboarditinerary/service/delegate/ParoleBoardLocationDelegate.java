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
package omis.paroleboarditinerary.service.delegate;

import java.util.List;
import omis.instance.factory.InstanceFactory;
import omis.location.domain.Location;
import omis.paroleboarditinerary.dao.ParoleBoardLocationDao;
import omis.paroleboarditinerary.domain.ParoleBoardLocation;

/**
 * Parole Board Location Delegate.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Jan 23, 2018)
 *@since OMIS 3.0
 *
 */
public class ParoleBoardLocationDelegate {
	
	private final ParoleBoardLocationDao paroleBoardLocationDao;
	
	private final InstanceFactory<ParoleBoardLocation>
			paroleBoardLocationInstanceFactory;
	
	/**
	 * Contructor for ParoleBoardLocationDelegate.
	 * @param paroleBoardLocationDao - Parole Board Location DAO
	 * @param paroleBoardLocationInstanceFactory - Parole Board Location
	 * Instance Factory
	 */
	public ParoleBoardLocationDelegate(
			final ParoleBoardLocationDao paroleBoardLocationDao,
			final InstanceFactory<ParoleBoardLocation>
				paroleBoardLocationInstanceFactory) {
		this.paroleBoardLocationDao = paroleBoardLocationDao;
		this.paroleBoardLocationInstanceFactory =
				paroleBoardLocationInstanceFactory;
	}
	
	/**
	 * Returns a list of all ParoleBoardLocations.
	 * @return List of all ParoleBoardLocations
	 */
	public List<ParoleBoardLocation> findAll() {
		return this.paroleBoardLocationDao.findAll();
	}
	
	/**
	 * Creates a Parole Board Location for the purposes of unit testing.
	 * 
	 * @param location - Location
	 * @param videoConferenceCapable - Video Conference Capability
	 * @return Newly created Parole Board Location
	 */
	public ParoleBoardLocation create(final Location location,
			final Boolean videoConferenceCapable) {
		ParoleBoardLocation paroleBoardLocation =
				this.paroleBoardLocationInstanceFactory.createInstance();
		paroleBoardLocation.setLocation(location);
		paroleBoardLocation.setVideoConferenceCapable(videoConferenceCapable);
		
		return this.paroleBoardLocationDao.makePersistent(paroleBoardLocation);
	}
}
