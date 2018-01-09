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
package omis.grievance.service.delegate;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.grievance.dao.GrievanceComplaintCategoryDao;
import omis.grievance.domain.GrievanceComplaintCategory;
import omis.grievance.domain.GrievanceSubject;
import omis.instance.factory.InstanceFactory;

/**
 * Delegate for grievance complaint categories.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Feb 25, 2016)
 * @since OMIS 3.0
 */
public class GrievanceComplaintCategoryDelegate {
	
	/* Instance factories. */
	
	private final InstanceFactory<GrievanceComplaintCategory>
	grievanceComplaintCategoryInstanceFactory;
	
	/* Data access objects. */
	
	private final GrievanceComplaintCategoryDao grievanceComplaintCategoryDao;

	/* Constructors. */
	
	/**
	 * Instantiates delegate for grievance complaint categories.
	 * 
	 * @param grievanceComplaintCategoryInstanceFactory instance factory for
	 * grievance complaint categories
	 * @param grievanceComplaintCategoryDao data access object for grievance
	 * complaint categories
	 */
	public GrievanceComplaintCategoryDelegate(
			final InstanceFactory<GrievanceComplaintCategory>
				grievanceComplaintCategoryInstanceFactory,
			final GrievanceComplaintCategoryDao grievanceComplaintCategoryDao) {
		this.grievanceComplaintCategoryInstanceFactory
			= grievanceComplaintCategoryInstanceFactory;
		this.grievanceComplaintCategoryDao = grievanceComplaintCategoryDao;
	}
	
	/* Methods. */
	
	/**
	 * Returns grievance complaint categories by subject.
	 * 
	 * @param subject subject
	 * @return grievance complaint categories by subject
	 */
	public List<GrievanceComplaintCategory> findBySubject(
			final GrievanceSubject subject) {
		return this.grievanceComplaintCategoryDao.findBySubject(subject);
	}
	
	/**
	 * Creates grievance complaint category.
	 * 
	 * @param subject subject
	 * @param name name
	 * @param valid whether valid
	 * @return created grievance complaint category
	 * @throws DuplicateEntityFoundException if complaint category exists
	 */
	public GrievanceComplaintCategory create(
			final GrievanceSubject subject, final String name,
			final Boolean valid)
				throws DuplicateEntityFoundException {
		if (this.grievanceComplaintCategoryDao.find(name) != null) {
			throw new DuplicateEntityFoundException(
					"Grievance complaitn category exists");
		}
		GrievanceComplaintCategory complaintCategory
			= this.grievanceComplaintCategoryInstanceFactory
				.createInstance();
		complaintCategory.setName(name);
		complaintCategory.setSubject(subject);
		complaintCategory.setValid(valid);
		return this.grievanceComplaintCategoryDao
				.makePersistent(complaintCategory);
	}
}