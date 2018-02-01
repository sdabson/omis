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
package omis.unitmanagerreview.service.delegate;

import java.util.Date;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;
import omis.staff.domain.StaffAssignment;
import omis.unitmanagerreview.dao.UnitManagerReviewDao;
import omis.unitmanagerreview.domain.UnitManagerReview;

/**
 * Unit manager review delegate.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 29, 2018)
 * @since OMIS 3.0
 */
public class UnitManagerReviewDelegate {

	/* Data access objects. */
	
	private final UnitManagerReviewDao unitManagerReviewDao;

	/* Instance factories. */
	
	private final InstanceFactory<UnitManagerReview> 
			unitManagerReviewInstanceFactory;
	
	/* Component Retrievers. */
	
	private final AuditComponentRetriever auditComponentRetriever;

	/** Instantiates an implementation of unit manager review delegate with the 
	 * specified date access object and instance factory.
	 * 
	 * @param unitManagerReviewDao unit manager review data access object
	 * @param unitManagerReviewInstanceFactory unit manager review instance 
	 * factory
	 * @param auditComponentRetriever audit component retriever
	 */
	public UnitManagerReviewDelegate(
			final UnitManagerReviewDao unitManagerReviewDao,
			final InstanceFactory<UnitManagerReview> 
					unitManagerReviewInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.unitManagerReviewDao = unitManagerReviewDao;
		this.unitManagerReviewInstanceFactory = 
				unitManagerReviewInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}

	/**
	 * Creates a new unit manager review with the specified parameters.
	 * 
	 * @param staffAssignment staff assignment
	 * @param date date
	 * @param text text
	 * @param offender offender
	 * @return unit manager review
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public UnitManagerReview create(final StaffAssignment staffAssignment, 
			final Date date, final String text, final Offender offender) 
					throws DuplicateEntityFoundException {
		if (this.unitManagerReviewDao.find(staffAssignment, offender) != null) {
			throw new DuplicateEntityFoundException(
					"Unit manager review already exists.");
		}
		UnitManagerReview unitManagerReview = this
				.unitManagerReviewInstanceFactory.createInstance();
		populateUnitManagerReview(unitManagerReview, staffAssignment, date, 
				text, offender);
		unitManagerReview.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
		return this.unitManagerReviewDao.makePersistent(unitManagerReview);
	}

	/**
	 * Updates an existing unit manager review with the specified parameters.
	 * 
	 * @param unitManagerReview unit manager review
	 * @param staffAssignment staff assignment
	 * @param date date
	 * @param text text
	 * @param offender offender
	 * @return unit manager review
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public UnitManagerReview update(final UnitManagerReview unitManagerReview, 
			final StaffAssignment staffAssignment, final Date date,
			final String text, final Offender offender) 
					throws DuplicateEntityFoundException {
		if (this.unitManagerReviewDao.findExcluding(staffAssignment, offender, 
				unitManagerReview) != null) {
			throw new DuplicateEntityFoundException(
					"Unit manager review already exists.");
		}
		populateUnitManagerReview(unitManagerReview, staffAssignment, date, 
				text, offender);
		return this.unitManagerReviewDao.makePersistent(unitManagerReview);
	}
	
	/**
	 * Removes the specified unit manager review.
	 * 
	 * @param unitManagerReview unit manager review
	 */
	public void remove(final UnitManagerReview unitManagerReview) {
		this.unitManagerReviewDao.makeTransient(unitManagerReview);
	}
	
	// Populates a unit manager review
	private void populateUnitManagerReview(
			final UnitManagerReview unitManagerReview,
			final StaffAssignment staffAssignment, final Date date, 
			final String text, final Offender offender) {
		unitManagerReview.setStaffAssignment(staffAssignment);
		unitManagerReview.setDate(date);
		unitManagerReview.setText(text);
		unitManagerReview.setOffender(offender);
		unitManagerReview.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
	}
}