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
import omis.stg.dao.SecurityThreatGroupChapterDao;
import omis.stg.domain.SecurityThreatGroup;
import omis.stg.domain.SecurityThreatGroupChapter;
import omis.stg.exception.SecurityThreatGroupChapterExistsException;

/**
 * Delegate for security threat group chapters.
 * 
 * @author Josh Divine
 * @author Sheronda Vaughn
 * @version 0.1.0 (Dec 07, 2016)
 * @since OMIS 3.0
 */
public class SecurityThreatGroupChapterDelegate {

	private final InstanceFactory<SecurityThreatGroupChapter>
		securityThreatGroupChapterInstanceFactory;

	private final SecurityThreatGroupChapterDao securityThreatGroupChapterDao;

	/**
	 * Constructor
	 * @param securityThreatGroupChapterInstanceFactory
	 * @param securityThreatGroupChapterDao
	 */
	public SecurityThreatGroupChapterDelegate (
			final InstanceFactory<SecurityThreatGroupChapter> 
				securityThreatGroupChapterInstanceFactory, 
			final SecurityThreatGroupChapterDao securityThreatGroupChapterDao) {
		this.securityThreatGroupChapterInstanceFactory 
			= securityThreatGroupChapterInstanceFactory;
		this.securityThreatGroupChapterDao = securityThreatGroupChapterDao;
	}

	/**
	 * Creates a new security threat group chapter with the specified name and 
	 * group
	 * @param name security threat group chapter name
	 * @param securityThreatGroup security threat group
	 * @return security threat group chapter
	 */
	public SecurityThreatGroupChapter create(String name, 
			SecurityThreatGroup securityThreatGroup) 
					throws SecurityThreatGroupChapterExistsException {
		if (this.securityThreatGroupChapterDao.find(name, securityThreatGroup) 
				!= null) {
			throw new SecurityThreatGroupChapterExistsException("Duplicate entity found");
		}
		SecurityThreatGroupChapter chapter 
			= this.securityThreatGroupChapterInstanceFactory.createInstance();
		chapter.setGroup(securityThreatGroup);
		chapter.setName(name);
		chapter.setValid(true);
		return this.securityThreatGroupChapterDao.makePersistent(chapter);
	}
	
	/**
	 * Returns security threat group chapters.
	 * 
	 * @return security threat group chapters
	 */
	public List<SecurityThreatGroupChapter> findAll() {
		return this.securityThreatGroupChapterDao.findAll();
	}
	
	/**
	 * Returns security threat group chapters from the specified group
	 * @param group security threat group
	 * @return list of security threat group chapters
	 */
	public List<SecurityThreatGroupChapter> findChaptersByGroup(
			SecurityThreatGroup group) {
		return this.securityThreatGroupChapterDao.findChaptersByGroup(group);
	}
}