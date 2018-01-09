package omis.stg.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.stg.domain.SecurityThreatGroup;
import omis.stg.domain.SecurityThreatGroupChapter;

/**
 * Data access object for security threat group chapters.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 17, 2014)
 * @since OMIS 3.0
 */
public interface SecurityThreatGroupChapterDao
		extends GenericDao<SecurityThreatGroupChapter> {

	/**
	 * Returns security threat group chapters from the specified group
	 * @param group security threat group
	 * @return list of security threat group chapters
	 */
	List<SecurityThreatGroupChapter> findChaptersByGroup(
			SecurityThreatGroup group);
	
	/**
	 * Returns the security threat group chapter with the specified name and 
	 * group
	 * @param name security threat group chapter name
	 * @param securityThreatGroup security threat group
	 * @return security threat group chapter
	 */
	SecurityThreatGroupChapter find(String name, 
			SecurityThreatGroup securityThreatGroup);
	
	/**
	 * Returns the security threat group chapter with the specified name and 
	 * group
	 * @param name security threat group chapter name
	 * @param securityThreatGroup security threat group
	 * @param excludedChapter security threat group chapter to exclude
	 * @return security threat group chapter
	 */
	SecurityThreatGroupChapter findExcluding(String name, 
			SecurityThreatGroup securityThreatGroup, 
			SecurityThreatGroupChapter excludedChapter);
}