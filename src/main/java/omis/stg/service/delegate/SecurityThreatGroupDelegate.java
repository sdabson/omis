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
import omis.region.domain.State;
import omis.stg.dao.SecurityThreatGroupDao;
import omis.stg.domain.SecurityThreatGroup;
import omis.stg.exception.SecurityThreatGroupExistsException;

/**
 * Delegate for security threat groups.
 * 
 * @author Josh Divine
 * @author Sheronda Vaughn 
 * @version 0.1.0 (Dec 07, 2016)
 * @since OMIS 3.0
 */
public class SecurityThreatGroupDelegate {

	private final InstanceFactory<SecurityThreatGroup>
		securityThreatGroupInstanceFactory;
	
	private final SecurityThreatGroupDao securityThreatGroupDao;
	
	/**
	 * Constructor
	 * @param securityThreatGroupInstanceFactory
	 * @param securityThreatGroupDao
	 */
	public SecurityThreatGroupDelegate (
			final InstanceFactory<SecurityThreatGroup> 
				securityThreatGroupInstanceFactory, 
				final SecurityThreatGroupDao securityThreatGroupDao) {
		this.securityThreatGroupInstanceFactory = securityThreatGroupInstanceFactory;
		this.securityThreatGroupDao = securityThreatGroupDao;
	}
	
	/**
	 * Creates a new security threat group
	 * @param name security threat group name
	 * @param state state
	 * @return security threat group
	 * @throws SecurityThreatGroupExistsException
	 */
	public SecurityThreatGroup create(String name, State state) 
			throws SecurityThreatGroupExistsException {
		if (this.securityThreatGroupDao.find(name, state) != null) {
			throw new SecurityThreatGroupExistsException("Duplicate entity found");
		}
		SecurityThreatGroup group 
			= this.securityThreatGroupInstanceFactory.createInstance();
		group.setName(name);
		group.setState(state);
		group.setValid(true);
		return this.securityThreatGroupDao.makePersistent(group);
		
	}
	
	/**
	 * Returns security threat groups.
	 * 
	 * @return security threat groups
	 */
	public List<SecurityThreatGroup> findGroups() {
		return this.securityThreatGroupDao.findAll();
	}
}