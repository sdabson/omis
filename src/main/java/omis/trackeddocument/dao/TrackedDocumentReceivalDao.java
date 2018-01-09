package omis.trackeddocument.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.docket.domain.Docket;
import omis.trackeddocument.domain.TrackedDocumentCategory;
import omis.trackeddocument.domain.TrackedDocumentReceival;

/**
 * Data access object for tracked document receival.
 * 
 * @author Yidong Li
 * @version 0.1.0 (Dec 11, 2017)
 * @since OMIS 3.0
 */
public interface TrackedDocumentReceivalDao
		extends GenericDao<TrackedDocumentReceival> {
	/**
	 * Finds existing receivals.
	 * 
	 * @param docket docket
	 * @param category tracked document category
	 * @param receivedDate received date
	 * @return TrackedDocumentReceival existing tracked document receival
	 */
	TrackedDocumentReceival findExistingReceival(Docket docket, 
		TrackedDocumentCategory category, Date receivedDate);

	/**
	 * Returns receivals.
	 * 
	 * @param docket docket
	 * @return a list of tracked document receival
	 */
	List<TrackedDocumentReceival> findReceivalsByDocket(Docket docket);
}