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
package omis.offenderdocument.service.impl;

import omis.courtdocument.service.delegate.CourtDocumentAssociationDelegate;
import omis.offender.domain.Offender;
import omis.offenderdocument.service.DocumentProfileItemService;

/** 
 * Implementation of document profile item service.
 * 
 * @author Ryan Johns
 * @author Josh Divine
 * @version 0.1.1 (Aug 9, 2018)
 * @since OMIS 3.0
 */
public class DocumentProfileItemServiceImpl 
	implements DocumentProfileItemService {
	private final CourtDocumentAssociationDelegate 
		courtDocumentAssociationDelegate;
	
	/** 
	 * Constructor.
	 * 
	 * @param courtDocumentDelegate court document delegate
	 */
	public DocumentProfileItemServiceImpl(
			final CourtDocumentAssociationDelegate 
				courtDocumentAssociationDelegate) {
		this.courtDocumentAssociationDelegate = courtDocumentAssociationDelegate;
	}
	
	/** {@inheritDoc} */
	@Override
	public Integer findDocumentCountByOffender(final Offender offender) {
		return this.courtDocumentAssociationDelegate
				.findCourtDocumentAssociationCountByOffender(offender);
	}
}