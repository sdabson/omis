package omis.violationevent.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.violationevent.domain.ViolationEvent;
import omis.violationevent.domain.ViolationEventNote;

/**
 * ViolationEventNoteDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jan 17, 2017)
 *@since OMIS 3.0
 *
 */
public interface ViolationEventNoteDao extends GenericDao<ViolationEventNote> {
	
	/**
	 * Finds and returns a ViolationEventNote with specified properties
	 * @param date - Date
	 * @param description - String
	 * @param violationEvent - ViolationEvent
	 * @return ViolationEventNote with specified properties
	 */
	ViolationEventNote find(Date date, String description,
			ViolationEvent violationEvent);
	
	/**
	 * Finds and returns a ViolationEventNote with specified properties excluding
	 * specified ViolationEventNote 
	 * @param excludedViolationEventNote - ViolationEventNote to exclude
	 * @param date - Date 
	 * @param description - String
	 * @param violationEvent - ViolationEvent
	 * @return ViolationEventNote with specified properties excluding
	 * specified ViolationEventNote
	 */
	ViolationEventNote findExcluding(
			ViolationEventNote excludedViolationEventNote, Date date,
			String description, ViolationEvent violationEvent);
	
	/**
	 * Finds and returns a list of all ViolationEventNotes by ViolationEvent
	 * @param violationEvent - ViolationEvent
	 * @return List of violationEventNotes by specified ViolationEvent
	 */
	List<ViolationEventNote> findByViolationEvent(ViolationEvent violationEvent);
	
}
