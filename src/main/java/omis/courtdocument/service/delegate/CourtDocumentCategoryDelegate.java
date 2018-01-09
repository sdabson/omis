package omis.courtdocument.service.delegate;

import java.util.List;

import omis.courtdocument.dao.CourtDocumentCategoryDao;
import omis.courtdocument.domain.CourtDocumentCategory;

/** Delegate for court document category service related operations.
 * @author Ryan Johns
 * @version 0.1.0 (Nov 18, 2015)
 * @since OMIS 3.0 */
public class CourtDocumentCategoryDelegate {
	private CourtDocumentCategoryDao courtDocumentCategoryDao;
	
	/** Constructor.
	 * @param courtDocumentCategoryDao - court document category dao. */
	public CourtDocumentCategoryDelegate(
			final CourtDocumentCategoryDao courtDocumentCategoryDao) {
		this.courtDocumentCategoryDao = courtDocumentCategoryDao;
	}
	
	/** finds all court document categories.
	 * @return list of court document categories. */
	public List<CourtDocumentCategory> findAll() {
		return this.courtDocumentCategoryDao.findAll();
	}

}
