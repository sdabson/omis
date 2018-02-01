package omis.victim.service;

import java.util.Date;
import java.util.List;

import omis.docket.domain.Docket;
import omis.document.domain.Document;
import omis.document.domain.DocumentTag;
import omis.document.exception.DocumentExistsException;
import omis.document.exception.DocumentTagExistsException;
import omis.person.domain.Person;
import omis.victim.domain.VictimDocumentAssociation;
import omis.victim.exception.VictimDocumentAssociationExistsException;

/*
 *  OMIS - Offender Management Information System
 *  Copyright (C) 2011 - 2017 State of Montana
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * Victim document service.
 * 
 * @author Joel Norris
 * @author Sheronda Vaughn
 * @version 0.1.0 (Aug 22, 2017)
 * @since OMIS 3.0
 */
public interface VictimDocumentAssociationService {

	/**
	 * Creates a victim document association for the specified victim and 
	 * document.
	 * 
	 * @param victim victim
	 * @param document document
	 * @param docket docket
	 * @return newly created victim document association
	 * @throws VictimDocumentAssociationExistsException thrown when a 
	 * duplicate victim 
	 * document association is found
	 */
	VictimDocumentAssociation create(Person victim, Document document, 
			Docket docket)
			throws VictimDocumentAssociationExistsException;
	
	/**
	 * Creates a document tag for the specified document.
	 * 
	 * @param document document
	 * @param name name
	 * @return newly created document tag
	 * @throws DocumentTagExistsException thrown when a duplicate 
	 * document tag is found
	 */
	DocumentTag createDocumentTag(Document document, String name) 
			throws DocumentTagExistsException;
	
	/**
	 * Updates the specified document tag.
	 * 
	 * @param documentTag document tag
	 * @param name name
	 * @return updated document tag
	 * @throws DocumentTagExistsException thrown when a duplicate document 
	 * tag is found
	 */
	DocumentTag updateDocumentTag(DocumentTag documentTag, String name) 
			throws DocumentTagExistsException;
	
	/**
	 * Removes the specified document tag.
	 * 
	 * @param documentTag document tag
	 */
	void removeDocumentTag(DocumentTag documentTag);
	
	/**
	 * Returns all victim document associations for the specified victim.
	 * 
	 * @param victim person
	 * @return list of victim document associaitons
	 */
	List<VictimDocumentAssociation> findByVictim(Person victim);
	
	/**
	 * Returns all victim document associations for the specified docket.
	 * 
	 * @param docket docket
	 * @return list of victim document associations
	 */
	List<VictimDocumentAssociation> findByDocket(Docket docket);
	
	/**
	 * Creates a document with the specified date, file name, file extension, 
	 * and title.
	 * 
	 * @param date date
	 * @param fileName file name
	 * @param fileExtension file extension
	 * @param title title
	 * @return newly created document
	 * @throws DocumentExistsException Thrown when a duplicate 
	 * document is found. 
	 */
	Document createDocument(Date date, String fileName, String fileExtension, 
			String title)
			throws DocumentExistsException;
	
	/**
	 * Removes the specified document.
	 * 
	 * @param document document
	 */
	void removeDocument(Document document);

	/**
	 * Returns all victim document associations for the specified victim and 
	 * docket.
	 * 
	 * @param docket docket
	 * @param victim victim
	 * @return victim document associations
	 */
	List<VictimDocumentAssociation> findByDocketAndVictim(
			Docket docket, Person victim);

	/**
	 * Returns all document tags for the specified document.
	 * 
	 * @param document document
	 * @return document tags
	 */
	List<DocumentTag> findTagsByDocument(Document document);

	/**
	 * Removes the specified victim document association.
	 * 
	 * @param association victim document association to remove
	 */
	void remove(VictimDocumentAssociation association);
	
	/**
	 * Returns a list of dockets for the specified victim.
	 * 
	 * @param victim person
	 * @return list of dockets
	 */
	List<Docket> findDocketsByVictim(Person victim);

	/**
	 * Updates the specified victim document association.
	 * 
	 * @param victimDocumentAssociation victim document association 
	 * @param title title
	 * @param date date
	 * @param docket docket
	 * @return updated victim document association
	 * @throws VictimDocumentAssociationExistsException thrown when a 
	 * duplicate victim document association is found
	 */
	VictimDocumentAssociation update(
			VictimDocumentAssociation victimDocumentAssociation, String title,
			Date date, Docket docket) 
					throws VictimDocumentAssociationExistsException;
}