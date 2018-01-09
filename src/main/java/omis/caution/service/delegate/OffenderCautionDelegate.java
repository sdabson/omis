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
package omis.caution.service.delegate;

import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.caution.dao.OffenderCautionDao;
import omis.caution.domain.CautionDescription;
import omis.caution.domain.CautionSource;
import omis.caution.domain.OffenderCaution;
import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;

/**
 * Delegate for offender cautions.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Jun 22, 2016)
 * @since OMIS 3.0
 */
public class OffenderCautionDelegate {

	/* Instance factories. */
	
	private final InstanceFactory<OffenderCaution>
	offenderCautionInstanceFactory;
	
	/* DAOs. */
	
	private final OffenderCautionDao offenderCautionDao;

	/* Audit component retriever. */
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/* Constructors. */
	
	/**
	 * Delegate for offender cautions.
	 * 
	 * @param offenderCautionInstanceFactory instance factory for offender
	 * cautions
	 * @param offenderCautionDao data access object for offender cautions
	 * @param auditComponentRetriever audit component retriever
	 */
	public OffenderCautionDelegate(
			final InstanceFactory<OffenderCaution>
				offenderCautionInstanceFactory,
			final OffenderCautionDao offenderCautionDao,
			final AuditComponentRetriever auditComponentRetriever) {
		this.offenderCautionInstanceFactory = offenderCautionInstanceFactory;
		this.offenderCautionDao = offenderCautionDao;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/* Methods. */
	
	/**
	 * Creates caution.
	 * 
	 * @param offender offender
	 * @param dateRange date range
	 * @param source source
	 * @param description description
	 * @param comment comment
	 * @param sourceComment source comment
	 * @return caution
	 * @throws DuplicateEntityFoundException if caution exists
	 */
	public OffenderCaution create(
			final Offender offender, final DateRange dateRange,
			final CautionSource source, final CautionDescription description,
			final String comment, final String sourceComment)
					throws DuplicateEntityFoundException {
		if (this.offenderCautionDao.find(
				offender, dateRange, source, description) != null) {
			throw new DuplicateEntityFoundException("Caution exists");
		}
		OffenderCaution caution = this.offenderCautionInstanceFactory
				.createInstance();
		caution.setOffender(offender);
		caution.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		caution.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		caution.setDateRange(dateRange);
		caution.setDescription(description);
		caution.setSource(source);
		caution.setComment(comment);
		caution.setSourceComment(sourceComment);
		return this.offenderCautionDao.makePersistent(caution);
	}

	/**
	 * Returns cautions by offender.
	 * 
	 * @param offender offender
	 * @return cautions by offender
	 */
	public List<OffenderCaution> findByOffender(final Offender offender) {
		return this.offenderCautionDao.findByOffender(offender);
	}
}