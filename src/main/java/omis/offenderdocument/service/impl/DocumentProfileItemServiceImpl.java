package omis.offenderdocument.service.impl;

import omis.courtdocument.service.delegate.CourtCaseDocumentAssociationDelegate;
import omis.offender.domain.Offender;
import omis.offenderdocument.service.DocumentProfileItemService;

/** Implementation of document profile item service.
 * @author Ryan Johns
 * @version 0.1.0 (Mar 15, 2016)
 * @since OMIS 3.0 */
public class DocumentProfileItemServiceImpl 
	implements DocumentProfileItemService {
	private final CourtCaseDocumentAssociationDelegate 
		courtCaseDocumentAssociationDelegate;
	
	/** Constructor.
	 * @param courtCaseDocumentDelegate - court case document delegate. */
	public DocumentProfileItemServiceImpl(
			final CourtCaseDocumentAssociationDelegate 
				courtCaseDocumentAssociationDelegate) {
		this.courtCaseDocumentAssociationDelegate 
			= courtCaseDocumentAssociationDelegate;
	}
	
	/** {@inheritDoc} */
	@Override
	public Integer findDocumentCountByOffender(final Offender offender) {
		return this.courtCaseDocumentAssociationDelegate
				.findCourtCaseDocumentAssociationCountByOffender(offender);
	}
}
