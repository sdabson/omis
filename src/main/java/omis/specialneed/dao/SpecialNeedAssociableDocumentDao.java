package omis.specialneed.dao;

import omis.dao.GenericDao;
import omis.specialneed.domain.SpecialNeed;
import omis.specialneed.domain.SpecialNeedAssociableDocument;

/**
 * Data access object for special need associable document.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Nov 1, 2017)
 * @since OMIS 3.0
 */
public interface SpecialNeedAssociableDocumentDao 
	extends GenericDao<SpecialNeedAssociableDocument> {
	
	/**
	 * Returns the special need associable document.
	 *
	 * @param specialNeed special need
	 * @return special need
	 */
	SpecialNeedAssociableDocument find(SpecialNeed specialNeed);
	
	/**
	 * Returns the special need associable document excluding the one in view.
	 *
	 * @param specialNeed special need
	 * @return special need associable document
	 */
	SpecialNeedAssociableDocument findExcluding(SpecialNeed specialNeed, 
			SpecialNeedAssociableDocument excludedDocument);
	
	/**
	 * Returns the special need associable document associated with this special 
	 * need.
	 *
	 * @param specialNeed special need
	 * @return special need associable document
	 */
	SpecialNeedAssociableDocument findBySpecialNeed(SpecialNeed specialNeed);
}