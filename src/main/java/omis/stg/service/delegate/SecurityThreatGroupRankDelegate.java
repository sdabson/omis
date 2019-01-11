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
import omis.stg.dao.SecurityThreatGroupRankDao;
import omis.stg.domain.SecurityThreatGroup;
import omis.stg.domain.SecurityThreatGroupRank;
import omis.stg.exception.SecurityThreatGroupRankExistsException;

/**
 * Delegate for security threat group ranks.
 * 
 * @author Josh Divine
 * @author Sheronda Vaughn
 * @version 0.1.0 (Dec 07, 2016)
 * @since OMIS 3.0
 */
public class SecurityThreatGroupRankDelegate {

	private final InstanceFactory<SecurityThreatGroupRank>
		securityThreatGroupRankInstanceFactory;

	private final SecurityThreatGroupRankDao securityThreatGroupRankDao;

	/**
	 * Constructor
	 * @param securityThreatGroupRankInstanceFactory
	 * @param securityThreatGroupRankDao
	 */
	public SecurityThreatGroupRankDelegate(
			final InstanceFactory<SecurityThreatGroupRank> 
				securityThreatGroupRankInstanceFactory,
			final SecurityThreatGroupRankDao securityThreatGroupRankDao) {
		this.securityThreatGroupRankInstanceFactory 
			= securityThreatGroupRankInstanceFactory;
		this.securityThreatGroupRankDao = securityThreatGroupRankDao;
	}
	
	/**
	 * Creates a new security threat group rank with the specified name
	 * @param name security threat group rank name
	 * @return security threat group rank
	 * @throws SecurityThreatGroupRankExistsException
	 */
	public SecurityThreatGroupRank create(String name, 
			SecurityThreatGroup securityThreatGroup) 
			throws SecurityThreatGroupRankExistsException {
		if (this.securityThreatGroupRankDao.find(name, securityThreatGroup) 
				!= null) {
			throw new SecurityThreatGroupRankExistsException("Duplicate entity found");
		}
		SecurityThreatGroupRank rank 
			= this.securityThreatGroupRankInstanceFactory.createInstance();
		rank.setName(name);
		rank.setGroup(securityThreatGroup);
		rank.setValid(true);
		return this.securityThreatGroupRankDao.makePersistent(rank);
	}
	
	/**
	 * Returns all security threat group ranks
	 * @return list of security threat group ranks
	 */
	public List<SecurityThreatGroupRank> findAll() {
		return this.securityThreatGroupRankDao.findAll();
	}
	
	/**
	 * Returns security threat group ranks from the specified group
	 * @param group security threat group
	 * @return security threat group rank
	 */
	public List<SecurityThreatGroupRank> findRanksByGroup(
			SecurityThreatGroup securityThreatGroup) {
		return this.securityThreatGroupRankDao.findRanksByGroup(
				securityThreatGroup);
	}
}
