package omis.victim.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import omis.docket.domain.Docket;
import omis.docket.service.delegate.DocketDelegate;
import omis.document.domain.Document;
import omis.document.domain.DocumentTag;
import omis.document.service.delegate.DocumentDelegate;
import omis.document.service.delegate.DocumentTagDelegate;
import omis.exception.DuplicateEntityFoundException;
import omis.person.domain.Person;
import omis.victim.domain.VictimAssociation;
import omis.victim.domain.VictimDocumentAssociation;
import omis.victim.service.VictimDocumentAssociationService;
import omis.victim.service.delegate.VictimAssociationDelegate;
import omis.victim.service.delegate.VictimDocumentAssociationDelegate;

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
 * Victim document association service.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Aug 30, 2017)
 * @since OMIS 3.0
 */
public class VictimDocumentAssociationServiceImpl implements VictimDocumentAssociationService {

	/* Service delegates. */
	private VictimDocumentAssociationDelegate victimDocumentAssociationDelegate;
	private DocumentDelegate documentDelegate;
	private DocumentTagDelegate documentTagDelegate;
	private VictimAssociationDelegate victimAssociationDelegate;
	private DocketDelegate docketDelegate;
	
	/* Constructor. */
	
	/**
	 * Instantiates an instance of victim document association service.
	 *  
	 * @param victimDocumentAssociationDelegate victim document association delegate
	 * @param documentDelegate document delegate
	 * @param documentTagDelegate document tag delegate
	 * @param victimAssociationDelegate victim association delegate
	 */
	public VictimDocumentAssociationServiceImpl(
			final VictimDocumentAssociationDelegate victimDocumentAssociationDelegate,
			final DocumentDelegate documentDelegate,
			final DocumentTagDelegate documentTagDelegate,
			final VictimAssociationDelegate victimAssociationDelegate,
			final DocketDelegate docketDelegate) {
		this.victimDocumentAssociationDelegate = victimDocumentAssociationDelegate;
		this.documentDelegate = documentDelegate;
		this.documentTagDelegate = documentTagDelegate;
		this.victimAssociationDelegate = victimAssociationDelegate;
		this.docketDelegate = docketDelegate;
	}
	
	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public VictimDocumentAssociation create(final Person victim, final Document document, 
			final Docket docket)
		throws DuplicateEntityFoundException {
		return this.victimDocumentAssociationDelegate.create(victim, document, docket);
	}

	/** {@inheritDoc} */
	@Override
	public DocumentTag createDocumentTag(final Document document, final String name)
		throws DuplicateEntityFoundException {
		return this.documentTagDelegate.tagDocument(document, name);
	}

	/** {@inheritDoc} */
	@Override
	public DocumentTag updateDocumentTag(final DocumentTag documentTag, final String name)
		throws DuplicateEntityFoundException {
		return this.documentTagDelegate.update(documentTag, name);
	}

	/** {@inheritDoc} */
	@Override
	public void removeDocumentTag(final DocumentTag documentTag) {
		this.documentTagDelegate.removeTag(documentTag);
	}

	/** {@inheritDoc} */
	@Override
	public List<VictimDocumentAssociation> findByVictim(final Person victim) {
		return this.victimDocumentAssociationDelegate.findByVictim(victim);
	}

	/** {@inheritDoc} */
	@Override
	public List<VictimDocumentAssociation> findByDocket(final Docket docket) {
		return this.victimDocumentAssociationDelegate.findByDocket(docket);
	}

	/** {@inheritDoc} */
	@Override
	public Document createDocument(final Date date, final String fileName, final String fileExtension,
		final String title)
		throws DuplicateEntityFoundException {
		return this.documentDelegate.create(date, fileName, fileExtension, title);
	}

	/** {@inheritDoc} */
	@Override
	public void removeDocument(final Document document) {
		this.documentDelegate.remove(document);
	}

	/** {@inheritDoc} */
	@Override
	public List<VictimDocumentAssociation> findByDocketAndVictim(final Docket docket, final Person victim) {
		return this.victimDocumentAssociationDelegate.findByDocketAndVictim(docket, victim);
	}

	/** {@inheritDoc} */
	@Override
	public List<DocumentTag> findTagsByDocument(final Document document) {
		return this.documentTagDelegate.findByDocument(document);
	}

	/** {@inheritDoc} */
	@Override
	public void remove(final VictimDocumentAssociation association) {
		this.victimDocumentAssociationDelegate.remove(association);
	}

	/** {@inheritDoc} */
	@Override
	public List<Docket> findDocketsByVictim(final Person victim) {
		List<Docket> dockets = new ArrayList<Docket>();
		for (VictimAssociation association : this.victimAssociationDelegate.findByVictim(victim)) {
			dockets.addAll(this.docketDelegate.findByPerson(association.getRelationship().getFirstPerson()));
		}
		return dockets;
	}

	/** {@inheritDoc} */
	@Override
	public VictimDocumentAssociation update(VictimDocumentAssociation victimDocumentAssociation, String title,
			Date date, Docket docket) throws DuplicateEntityFoundException {
		return this.victimDocumentAssociationDelegate.update(victimDocumentAssociation, title, date, docket);
	}
}
