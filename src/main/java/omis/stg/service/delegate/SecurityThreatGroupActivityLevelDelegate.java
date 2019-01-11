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
package omis.stg.service.delegate;

import java.util.List;

import omis.instance.factory.InstanceFactory;
import omis.stg.dao.SecurityThreatGroupActivityLevelDao;
import omis.stg.domain.SecurityThreatGroupActivityLevel;
import omis.stg.exception.SecurityThreatGroupActivityLevelExistsException;

/**
 * Delegate for security threat group activity levels.
 * 
 * @author Josh Divine
 * @author Sheronda Vaughn
 * @version 0.1.0 (Dec 07, 2016)
 * @since OMIS 3.0
 */
public class SecurityThreatGroupActivityLevelDelegate {

	private final InstanceFactory<SecurityThreatGroupActivityLevel>
		securityThreatGroupActivityLevelInstanceFactory;
	
	private final SecurityThreatGroupActivityLevelDao 
		securityThreatGroupActivityLevelDao;
	
	/**
	 * Constructor
	 * @param securityThreatGroupActivityLevelInstanceFactory
	 * @param securityThreatGroupActivityLevelDao
	 */
	public SecurityThreatGroupActivityLevelDelegate (
			final InstanceFactory<SecurityThreatGroupActivityLevel> 
				securityThreatGroupActivityLevelInstanceFactory, 
			final SecurityThreatGroupActivityLevelDao 
				securityThreatGroupActivityLevelDao) {
		this.securityThreatGroupActivityLevelInstanceFactory = securityThreatGroupActivityLevelInstanceFactory;
		this.securityThreatGroupActivityLevelDao = securityThreatGroupActivityLevelDao;
	}
	
	/**
	 * Creates a new security threat group activity level
	 * @param name security threat group activity level name
	 * @return security threat group activity level
	 * @throws SecurityThreatGroupActivityLevelExistsException
	 */
	public SecurityThreatGroupActivityLevel create(final String name) 
			throws SecurityThreatGroupActivityLevelExistsException{
		if (this.securityThreatGroupActivityLevelDao.find(name) != null) {
			throw new SecurityThreatGroupActivityLevelExistsException("Duplicate entity found");
		}
		SecurityThreatGroupActivityLevel activityLevel 
			= this.securityThreatGroupActivityLevelInstanceFactory
			.createInstance();
		activityLevel.setName(name);
		activityLevel.setValid(true);
		return this.securityThreatGroupActivityLevelDao
				.makePersistent(activityLevel);
	}

	/**
	 * Returns security threat group activity levels.
	 * 
	 * @return security threat group activity levels
	 */
	public List<SecurityThreatGroupActivityLevel> findAll() {
		return this.securityThreatGroupActivityLevelDao.findAll();
	}
}
