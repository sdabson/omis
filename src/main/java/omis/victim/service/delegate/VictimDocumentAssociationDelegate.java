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
package omis.victim.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.docket.domain.Docket;
import omis.document.domain.Document;
import omis.instance.factory.InstanceFactory;
import omis.person.domain.Person;
import omis.victim.dao.VictimDocumentAssociationDao;
import omis.victim.domain.VictimDocumentAssociation;
import omis.victim.exception.VictimDocumentAssociationExistsException;

/**
 * Victim document association delegate.
 * 
 * @author Joel Norris
 * @author Sheronda Vaughn
 * @version 0.1.0 (Aug 22, 2017)
 * @since OMIS 3.0
 */
public class VictimDocumentAssociationDelegate {

	/* Resources. */
	private VictimDocumentAssociationDao victimDocumentAssociationDao;
	private final InstanceFactory<VictimDocumentAssociation> 
		victimDocumentAssociationInstanceFactory;
	private final AuditComponentRetriever auditComponentRetriever;
	
	/* Exception Messages */
	private final String DUPLICATE_ASSOCIATION_FOUND_EXCEPTION_MESSAGE 
		= "Duplicate victim document association found";
	
	/* Constructor. */
	
	/**
	 * Instantiates an instance of victim document association delegate.
	 * 
	 * @param victimDocumentAssociationDao victim document association data 
	 * access object
	 * @param victimDocumentAssociationInstanceFactory victim document 
	 * association instance factory
	 * @param auditComponentRetriever audit component retriever
	 */
	public VictimDocumentAssociationDelegate(
			final VictimDocumentAssociationDao victimDocumentAssociationDao,
			final InstanceFactory<VictimDocumentAssociation> 
				victimDocumentAssociationInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.victimDocumentAssociationDao = victimDocumentAssociationDao;
		this.victimDocumentAssociationInstanceFactory 
			= victimDocumentAssociationInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/* Delegate service methods. */
	
	/**
	 * Creates a victim document association for the specified victim and 
	 * document.
	 * 
	 * @param victim person
	 * @param document document
	 * @param docket docket
	 * @return newly created victim document association
	 * @throws VictimDocumentAssociationExistsException thrown when a duplicate 
	 * victim document association is found
	 */
	public VictimDocumentAssociation create(final Person victim, 
			final Document document, final Docket docket)
			throws VictimDocumentAssociationExistsException {
		if (this.victimDocumentAssociationDao.find(victim, document) != null) {
			throw new VictimDocumentAssociationExistsException(
					DUPLICATE_ASSOCIATION_FOUND_EXCEPTION_MESSAGE);
		}
		VictimDocumentAssociation association 
			= this.victimDocumentAssociationInstanceFactory.createInstance();
		association.setVictim(victim);
		association.setDocument(document);
		association.setDocket(docket);
		association.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		return this.victimDocumentAssociationDao.makePersistent(association);
	}

	/**
	 * Updates the specified victim document association.
	 *  
	 * @param association victim document association
	 * @param title title
	 * @param date date
	 * @param docket docket
	 * @return updated victim document association
	 * @throws VictimDocumentAssociationExistsException thrown when a duplicate 
	 * victim document association is found
	 */
	public VictimDocumentAssociation update(
			final VictimDocumentAssociation association,
			final String title, final Date date, final Docket docket) 
					throws VictimDocumentAssociationExistsException {
		if (this.victimDocumentAssociationDao.findExcluding(
				association.getVictim(), association.getDocument(),
				association) != null) {
			throw new VictimDocumentAssociationExistsException(
					DUPLICATE_ASSOCIATION_FOUND_EXCEPTION_MESSAGE);
		}
		association.getDocument().setTitle(title);
		association.getDocument().setDate(date);
		association.setDocket(docket);
		return this.victimDocumentAssociationDao.makePersistent(association);
	}
	
	/**
	 * Removes the specified victim document association.
	 * 
	 * @param association victim document association
	 */
	public void remove(final VictimDocumentAssociation association) {
		this.victimDocumentAssociationDao.makeTransient(association);
	}
	
	/**
	 * Returns victim document associations for the specified document.
	 * 
	 * @param docket docket
	 * @return list of victim document associations
	 */
	public List<VictimDocumentAssociation> findByDocket(final Docket docket) {
		return this.victimDocumentAssociationDao.findByDocket(docket);
	}
	
	/**
	 * Returns victim document associations for the specified victim.
	 *  
	 * @param victim victim
	 * @return list of victim document associations
	 */
	public List<VictimDocumentAssociation> findByVictim(final Person victim) {
		return this.victimDocumentAssociationDao.findByVictim(victim);
	}

	/**
	 * Returns victim document associations for the specified victim and docket.
	 * 
	 * @param docket docket
	 * @param victim victim
	 * @return list of victim document associations
	 */
	public List<VictimDocumentAssociation> findByDocketAndVictim(
			final Docket docket, final Person victim) {
		return this.victimDocumentAssociationDao.findByDocketAndVictim(docket, 
				victim);
	}
}	