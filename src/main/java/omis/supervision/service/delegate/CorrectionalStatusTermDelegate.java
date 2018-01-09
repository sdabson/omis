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
package omis.supervision.service.delegate;

import java.util.Date;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;
import omis.supervision.dao.CorrectionalStatusTermDao;
import omis.supervision.domain.CorrectionalStatus;
import omis.supervision.domain.CorrectionalStatusTerm;

/**
 * Delegate for correctional status terms.
 *
 * @author Stephen Abson
 * @version 0.0.1 (May 9, 2016)
 * @since OMIS 3.0
 */
public class CorrectionalStatusTermDelegate {
	
	/* Instance factories. */
	
	private final InstanceFactory<CorrectionalStatusTerm>
	correctionalStatusTermInstanceFactory;

	/* Data access objects. */
	
	private final CorrectionalStatusTermDao correctionalStatusTermDao;
	
	/* Audit component retriever. */
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/* Constructors. */
	
	/**
	 * Instantiates delegate for correctional status terms.
	 * 
	 * @param correctionalStatusTermInstanceFactory instance factory for
	 * correctional status terms
	 * @param correctionalStatusTermDao data access object for correctional
	 * status terms
	 * @param auditComponentRetriever audit component retriever
	 */
	public CorrectionalStatusTermDelegate(
			final InstanceFactory<CorrectionalStatusTerm>
			correctionalStatusTermInstanceFactory,
			final CorrectionalStatusTermDao correctionalStatusTermDao,
			final AuditComponentRetriever auditComponentRetriever) {
		this.correctionalStatusTermInstanceFactory
			= correctionalStatusTermInstanceFactory;
		this.correctionalStatusTermDao = correctionalStatusTermDao;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/* Methods. */
	
	/**
	 * Returns correctional status term for offender on date.
	 * 
	 * @param offender offender
	 * @param date date
	 * @return correctional status term for offender on date
	 */
	public CorrectionalStatusTerm findForOffenderOnDate(
			final Offender offender, final Date date) {
		return this.correctionalStatusTermDao.findForOffenderOnDate(
				offender, date);
	}
	
	/**
	 * Creates correctional status term.
	 * 
	 * @param offender offender
	 * @param dateRange date range
	 * @param correctionalStatus correctional status
	 * @return newly created correctional status term
	 * @throws DuplicateEntityFoundException if correctional status term exists
	 */
	public CorrectionalStatusTerm create(
			final Offender offender,
			final DateRange dateRange,
			final CorrectionalStatus correctionalStatus)
				 throws DuplicateEntityFoundException {
		if (this.correctionalStatusTermDao.find(
				offender,
				correctionalStatus,
				DateRange.getStartDate(dateRange),
				DateRange.getEndDate(dateRange)) != null) {
			throw new DuplicateEntityFoundException(
					"Correctional status term exists");
		}
		CorrectionalStatusTerm correctionalStatusTerm
			= this.correctionalStatusTermInstanceFactory.createInstance();
		correctionalStatusTerm.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		correctionalStatusTerm.setOffender(offender);
		this.populateCorrectionalStatusTerm(
				correctionalStatusTerm, dateRange, correctionalStatus);
		return this.correctionalStatusTermDao.makePersistent(
				correctionalStatusTerm);
	}
	
	/**
	 * Updates correctional status term.
	 * 
	 * @param correctionalStatusTerm correctional status term to update
	 * @param dateRange date range
	 * @param correctionalStatus correctional status
	 * @return updated correctional status term
	 * @throws DuplicateEntityFoundException if correctional status term exists
	 */
	public CorrectionalStatusTerm update(
			final CorrectionalStatusTerm correctionalStatusTerm,
			final DateRange dateRange,
			final CorrectionalStatus correctionalStatus)
				throws DuplicateEntityFoundException {
		if (this.correctionalStatusTermDao.findExcluding(
				correctionalStatusTerm.getOffender(),
				correctionalStatus,
				DateRange.getStartDate(dateRange),
				DateRange.getEndDate(dateRange),
				correctionalStatusTerm) != null) {
			throw new DuplicateEntityFoundException(
					"Correctional status term exists");
		}
		this.populateCorrectionalStatusTerm(
				correctionalStatusTerm, dateRange, correctionalStatus);
		return this.correctionalStatusTermDao.makePersistent(
				correctionalStatusTerm);
	}
	
	/**
	 * Removes correctional status term.
	 * 
	 * @param correctionalStatusTerm correctional status term to remove
	 */
	public void remove(final CorrectionalStatusTerm correctionalStatusTerm) {
		this.correctionalStatusTermDao.makeTransient(correctionalStatusTerm);
	}
	
	/* Helper methods. */
	
	// Populates correctional status term
	private void populateCorrectionalStatusTerm(
			final CorrectionalStatusTerm correctionalStatusTerm,
			final DateRange dateRange,
			final CorrectionalStatus correctionalStatus) {
		correctionalStatusTerm.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		correctionalStatusTerm.setCorrectionalStatus(correctionalStatus);
		correctionalStatusTerm.setDateRange(dateRange);
	}
}