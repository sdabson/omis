package omis.violationevent.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.document.domain.Document;
import omis.violationevent.domain.ViolationEvent;
import omis.violationevent.domain.ViolationEventDocument;

/**
 * ViolationEventDocumentDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jan 17, 2017)
 *@since OMIS 3.0
 *
 */
public interface ViolationEventDocumentDao
	extends GenericDao<ViolationEventDocument> {
	
	/**
	 * Finds and returns a ViolationEventDocument with specified properties
	 * @param document - document
	 * @param violationEvent - violationEvent
	 * @return ViolationEventDocument with specified properties
	 */
	ViolationEventDocument find(Document document,
			ViolationEvent violationEvent);
	
	/**
	 * Finds and returns a ViolationEventDocument with specified properties
	 * excluding specified ViolationEventDocument
	 * @param excludedViolationEventDocument - ViolationEventDocument to exclude
	 * @param document - document
	 * @param violationEvent - violationEvent
	 * @return ViolationEventDocument with specified properties
	 * excluding specified ViolationEventDocument
	 */
	ViolationEventDocument findExcluding(
			ViolationEventDocument excludedViolationEventDocument,
			Document document, ViolationEvent violationEvent);
	
	/**
	 * Finds and returns a list of ViolationEventDocuments found by specified
	 * ViolationEvent
	 * @param violationEvent - ViolationEvent
	 * @return list of ViolationEventDocuments found by specified
	 * ViolationEvent
	 */
	List<ViolationEventDocument> findByViolationEvent(
			ViolationEvent violationEvent);
	
	
	
}
