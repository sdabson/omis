package omis.offenderdocument.service;

import omis.offender.domain.Offender;

/** Service for document profile item.
 * @author Ryan Johns
 * @version 0.1.0 (Mar 15, 2016)
 * @since OMIS 3.0 */
public interface DocumentProfileItemService {
	/** Finds document count by Offender.
	 * @param offender - offender.
	 * @return count. */
	Integer findDocumentCountByOffender(Offender offender);
}
