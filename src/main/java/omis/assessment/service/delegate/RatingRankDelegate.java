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
package omis.assessment.service.delegate;

import omis.assessment.dao.RatingRankDao;
import omis.assessment.domain.RatingRank;
import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;

/**
 * Rating rank delegate.
 * 
 * @author Josh Divine
 * @author Annie Wahl
 * @version 0.1.1 (Aug 15, 2018)
 * @since OMIS 3.0
 */
public class RatingRankDelegate {

	/* Data access objects. */
	
	private final RatingRankDao ratingRankDao;

	/* Instance factories. */
	
	private final InstanceFactory<RatingRank> ratingRankInstanceFactory;
	
	/* Component Retrievers. */
	
	private final AuditComponentRetriever auditComponentRetriever;

	/** 
	 * Instantiates an implementation of rating rank delegate 
	 * with the specified date access object and instance factory.
	 * 
	 * @param ratingRankDao rating rank data access object
	 * @param ratingRankInstanceFactory rating rank instance factory
	 * @param auditComponentRetriever audit component retriever
	 */
	public RatingRankDelegate(
			final RatingRankDao ratingRankDao,
			final InstanceFactory<RatingRank>
					ratingRankInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.ratingRankDao = ratingRankDao;
		this.ratingRankInstanceFactory = 
				ratingRankInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a new rating rank.
	 * 
	 * @param name name
	 * @param valid valid
	 * @return rating rank
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public RatingRank create(final String name, final Boolean valid)
					throws DuplicateEntityFoundException {
		if (this.ratingRankDao.find(name) != null) {
			throw new DuplicateEntityFoundException(
					"Rating rank already exists.");
		}
		RatingRank ratingRank = this.ratingRankInstanceFactory.createInstance();
		populateRatingRank(ratingRank, name, valid);
		ratingRank.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
		return this.ratingRankDao.makePersistent(ratingRank);
	}
	
	/**
	 * Updates an existing rating rank.
	 * 
	 * @param ratingRank rating rank
	 * @param name name
	 * @param valid valid
	 * @return rating rank
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public RatingRank update(final RatingRank ratingRank, final String name,
			final Boolean valid) throws DuplicateEntityFoundException {
		if (this.ratingRankDao.find(name) != null) {
			throw new DuplicateEntityFoundException(
					"Rating rank already exists.");
		}
		populateRatingRank(ratingRank, name, valid);
		return this.ratingRankDao.makePersistent(ratingRank);
	}
	
	/**
	 * Removes the specified rating rank.
	 * 
	 * @param ratingRank rating rank
	 */
	public void remove(final RatingRank ratingRank) {
		this.ratingRankDao.makeTransient(ratingRank);
	}

	// Populates a rating rank
	private void populateRatingRank(final RatingRank ratingRank,
			final String name, final Boolean valid) {
		ratingRank.setName(name);
		ratingRank.setValid(valid);
		ratingRank.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
	}
}