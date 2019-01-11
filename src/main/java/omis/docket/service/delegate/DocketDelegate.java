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
package omis.docket.service.delegate;

import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.court.domain.Court;
import omis.docket.dao.DocketDao;
import omis.docket.domain.Docket;
import omis.docket.exception.DocketExistsException;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.person.domain.Person;

/**
 * Delegate for dockets.
 *
 * @author Stephen Abson
 * @author Josh Divine
 * @version 0.1.1 (Aug 15, 2018)
 * @since OMIS 3.0
 */
public class DocketDelegate {

	private final InstanceFactory<Docket> docketInstanceFactory;
	
	private final DocketDao docketDao;
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/**
	 * Instantiates delegate for dockets.
	 * 
	 * @param docketInstanceFactory instance factory for dockets
	 * @param docketDao data access object for dockets
	 * @param auditComponentRetriever audit component retriever
	 */
	public DocketDelegate(
			final InstanceFactory<Docket> docketInstanceFactory,
			final DocketDao docketDao,
			final AuditComponentRetriever auditComponentRetriever) {
		this.docketInstanceFactory = docketInstanceFactory;
		this.docketDao = docketDao;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates docket.
	 * 
	 * @param person person
	 * @param court court
	 * @param value value
	 * @return created docket
	 * @throws DocketExistsException if docket exists
	 */
	public Docket create(
			final Person person, final Court court, final String value)
				throws DocketExistsException {
		if (this.docketDao.find(person, court, value) != null) {
			throw new DocketExistsException("Docket exists");
		}
		Docket docket = this.docketInstanceFactory.createInstance();
		docket.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		populateDocket(docket, person, court, value);
		return this.docketDao.makePersistent(docket);
	}

	/**
	 * Updates docket.
	 * 
	 * @param docket docket to update
	 * @param court court
	 * @param value value
	 * @return updated docket
	 * @throws DuplicateEntityFoundException if docket exists
	 */
	public Docket update(
			final Docket docket, final Court court, final String value)
					throws DocketExistsException {
		if (this.docketDao.findExcluding(
				docket.getPerson(), court, value, docket) != null) {
			throw new DocketExistsException("Docket exists");
		}
		populateDocket(docket, docket.getPerson(), court, value);
		return this.docketDao.makePersistent(docket);
	}
	
	/**
	 * Removes docket.
	 * 
	 * @param docket docket to remove
	 */
	public void remove(final Docket docket) {
		this.docketDao.makeTransient(docket);
	}
	
	/**
	 * Returns a list of dockets for the specified person.
	 * 
	 * @param person person
	 * @return list of dockets
	 */
	public List<Docket> findByPerson(final Person person){
		return this.docketDao.findByPerson(person);
	}

	/**
	 * Returns a list of dockets for the specified value.
	 * 
	 * @param value value
	 * @return list of dockets
	 */
	public List<Docket> findByValue(final String value) {
		return this.docketDao.findByValue(value);
	}
	
	// Populates a docket
		private void populateDocket(final Docket docket, final Person person,
				final Court court, final String value) {
			docket.setPerson(person);
			docket.setCourt(court);
			docket.setValue(value);
			docket.setUpdateSignature(new UpdateSignature(
					this.auditComponentRetriever.retrieveUserAccount(),
					this.auditComponentRetriever.retrieveDate()));
		}
}