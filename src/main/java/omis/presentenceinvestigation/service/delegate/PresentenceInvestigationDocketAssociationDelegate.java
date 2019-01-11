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
package omis.presentenceinvestigation.service.delegate;

import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.docket.domain.Docket;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.presentenceinvestigation.dao.PresentenceInvestigationDocketAssociationDao;
import omis.presentenceinvestigation.domain.PresentenceInvestigationDocketAssociation;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;

/**
 * Presentence investigation docket association delegate.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Oct 25, 2018)
 * @since OMIS 3.0
 */
public class PresentenceInvestigationDocketAssociationDelegate {

/* Data access objects. */
	
	private final PresentenceInvestigationDocketAssociationDao 
		presentenceInvestigationDocketAssociationDao;
	
	/* Instance factories. */
	
	private final InstanceFactory<PresentenceInvestigationDocketAssociation> 
		presentenceInvestigationDocketAssociationInstanceFactory;
	
	/* Component Retrievers. */
	
	private final AuditComponentRetriever auditComponenetRetriever;
	
	/** 
	 * Constructor.
	 *  
	 * @param presentenceInvestigationDocketAssociationDao presentence 
	 * investigation docket association data access object
	 * @param presentenceInvestigationDocketAssociationInstanceFactory 
	 * presentence investigation docket association instance factory
	 * @param auditComponentRetriever audit component retriever
	 */
	public PresentenceInvestigationDocketAssociationDelegate(
			final PresentenceInvestigationDocketAssociationDao 
				presentenceInvestigationDocketAssociationDao,
			final InstanceFactory<PresentenceInvestigationDocketAssociation>
				presentenceInvestigationDocketAssociationInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.presentenceInvestigationDocketAssociationDao 
			= presentenceInvestigationDocketAssociationDao;
		this.presentenceInvestigationDocketAssociationInstanceFactory 
			= presentenceInvestigationDocketAssociationInstanceFactory;
		this.auditComponenetRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a presentence investigation docket association.
	 * 
	 * @param presentenceInvestigationRequest presentence investigation request
	 * @param docket
	 * @return presentence investigation docket association
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public PresentenceInvestigationDocketAssociation 
			create(
					final PresentenceInvestigationRequest 
						presentenceInvestigationRequest, 
					final Docket docket) throws DuplicateEntityFoundException {
		if (this.presentenceInvestigationDocketAssociationDao.find(
				presentenceInvestigationRequest, docket) != null) {
			throw new DuplicateEntityFoundException();
		}
		PresentenceInvestigationDocketAssociation association = 
				this.presentenceInvestigationDocketAssociationInstanceFactory
				.createInstance();
		association.setDocket(docket);
		association.setPresentenceInvestigationRequest(
				presentenceInvestigationRequest);
		association.setCreationSignature(new CreationSignature(
				this.auditComponenetRetriever.retrieveUserAccount(),
				this.auditComponenetRetriever.retrieveDate()));
		association.setUpdateSignature(new UpdateSignature(
				this.auditComponenetRetriever.retrieveUserAccount(), 
				this.auditComponenetRetriever.retrieveDate()));
		return this.presentenceInvestigationDocketAssociationDao
				.makePersistent(association);
	}
	
	/**
	 * Removes the specified presentence investigation docket association.
	 * 
	 * @param presentenceInvestigationDocketAssociation presentence 
	 * investigation docket association
	 */
	public void removePresentenceInvestigationDocketAssociation(
			final PresentenceInvestigationDocketAssociation 
				presentenceInvestigationDocketAssociation) {
		this.presentenceInvestigationDocketAssociationDao.makeTransient(
				presentenceInvestigationDocketAssociation);
	}
	
	/**
	 * Returns a list of presentence investigation docket associations for the 
	 * specified presentence investigation request.
	 * 
	 * @param presentenceInvestigationRequest presentence investigation request
	 * @return list of presentence investigation docket associations
	 */
	public List<PresentenceInvestigationDocketAssociation> 
			findByPresentenceInvestigationRequest(
					final PresentenceInvestigationRequest 
						presentenceInvestigationRequest) {
		return this.presentenceInvestigationDocketAssociationDao
				.findByPresentenceInvestigationRequest(
						presentenceInvestigationRequest);
	}
}