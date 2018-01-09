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
package omis.caution.service.impl;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.caution.dao.CautionDescriptionDao;
import omis.caution.dao.CautionSourceDao;
import omis.caution.dao.OffenderCautionDao;
import omis.caution.domain.CautionDescription;
import omis.caution.domain.CautionSource;
import omis.caution.domain.OffenderCaution;
import omis.caution.service.OffenderCautionService;
import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;

/**
 * Implementation of service for offender cautions.
 * 
 * @author Stephen Abson
 * @version 0.1.3 (June 19, 2013)
 * @since OMIS 3.0
 */
public class OffenderCautionServiceImpl
		implements OffenderCautionService {

	private final OffenderCautionDao offenderCautionDao;
	
	private final InstanceFactory<OffenderCaution>
	offenderCautionInstanceFactory;
	
	private final CautionSourceDao cautionSourceDao;
	
	private final CautionDescriptionDao cautionDescriptionDao;
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/**
	 * Instantiates an implementation of service for offender cautions with
	 * the specified data access object.
	 * 
	 * @param offenderCautionDao data access object for offender cautions
	 * @param offenderCautionInstanceFactory instance factory for offender
	 * cautions
	 * @param cautionSourceDao data access object for caution sources
	 * @param cautionDescriptionDao data access object for caution descriptions
	 * @param auditComponentRetriever retriever for audit components
	 */
	public OffenderCautionServiceImpl(
			final OffenderCautionDao offenderCautionDao,
			final InstanceFactory<OffenderCaution>
				offenderCautionInstanceFactory,
			final CautionSourceDao cautionSourceDao,
			final CautionDescriptionDao cautionDescriptionDao,
			final AuditComponentRetriever auditComponentRetriever) {
		this.offenderCautionDao = offenderCautionDao;
		this.offenderCautionInstanceFactory = offenderCautionInstanceFactory;
		this.cautionSourceDao = cautionSourceDao;
		this.cautionDescriptionDao = cautionDescriptionDao;
		this.auditComponentRetriever = auditComponentRetriever;
	}

	/** {@inheritDoc} */
	@Override
	public OffenderCaution save(final Offender offender,
			final CautionDescription description, final CautionSource source,
			final DateRange dateRange, final String comment,
			final String sourceComment)
				throws DuplicateEntityFoundException {
		if (this.offenderCautionDao.find(offender, dateRange, source,
				description) != null) {
			throw new DuplicateEntityFoundException("Duplicate entity found");
		}
		OffenderCaution caution = this.offenderCautionInstanceFactory
				.createInstance();
		caution.setOffender(offender);
		caution.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		this.populateCaution(caution, description, source,
				DateRange.deepCopy(dateRange), comment, sourceComment);
		caution.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		return this.offenderCautionDao.makePersistent(caution);
	}

	/** {@inheritDoc} */
	@Override
	public OffenderCaution update(final OffenderCaution caution,
			final CautionDescription description, final CautionSource source,
			final DateRange dateRange, final String comment,
			final String sourceComment)
				throws DuplicateEntityFoundException {
		if (this.offenderCautionDao.findExcluding(caution.getOffender(),
				dateRange, source, description, caution) != null) {
			throw new DuplicateEntityFoundException("Duplicate entity found");
		}
		this.populateCaution(caution, description, source,
				DateRange.deepCopy(dateRange), comment, sourceComment);
		caution.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		return this.offenderCautionDao.makePersistent(caution);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<OffenderCaution> findByOffender(final Offender offender) {
		return this.offenderCautionDao.findByOffender(offender);
	}

	/** {@inheritDoc} */
	@Override
	public void remove(final OffenderCaution caution) {
		this.offenderCautionDao.makeTransient(caution);
	}

	/** {@inheritDoc} */
	@Override
	public List<OffenderCaution> findByOffenderOnDate(
			final Offender offender, final Date date) {
		return this.offenderCautionDao.findByOffenderOnDate(offender, date);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<CautionSource> findSources() {
		return this.cautionSourceDao.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public List<CautionDescription> findDescriptions() {
		return this.cautionDescriptionDao.findAll();
	}
	
	/* Helper methods. */
	
	// Populate caution
	private void populateCaution(final OffenderCaution caution,
			final CautionDescription description, final CautionSource source,
			final DateRange dateRange, final String comment,
			final String sourceComment) {
		caution.setDescription(description);
		caution.setSource(source);
		caution.setDateRange(dateRange);
		caution.setComment(comment);
		caution.setSourceComment(sourceComment);
	}
}