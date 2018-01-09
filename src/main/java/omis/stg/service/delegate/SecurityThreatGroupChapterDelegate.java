package omis.stg.service.delegate;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.stg.dao.SecurityThreatGroupChapterDao;
import omis.stg.domain.SecurityThreatGroup;
import omis.stg.domain.SecurityThreatGroupChapter;

/**
 * Delegate for security threat group chapters.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Dec 07, 2016)
 * @since OMIS 3.0
 */
public class SecurityThreatGroupChapterDelegate {

	private final InstanceFactory<SecurityThreatGroupChapter>
		securityThreatGroupChapterInstanceFactory;

	private final SecurityThreatGroupChapterDao securityThreatGroupChapterDao;

	/**
	 * Constructor
	 * @param securityThreatGroupChapterInstanceFactory
	 * @param securityThreatGroupChapterDao
	 */
	public SecurityThreatGroupChapterDelegate (
			final InstanceFactory<SecurityThreatGroupChapter> 
				securityThreatGroupChapterInstanceFactory, 
			final SecurityThreatGroupChapterDao securityThreatGroupChapterDao) {
		this.securityThreatGroupChapterInstanceFactory 
			= securityThreatGroupChapterInstanceFactory;
		this.securityThreatGroupChapterDao = securityThreatGroupChapterDao;
	}

	/**
	 * Creates a new security threat group chapter with the specified name and 
	 * group
	 * @param name security threat group chapter name
	 * @param securityThreatGroup security threat group
	 * @return security threat group chapter
	 */
	public SecurityThreatGroupChapter create(String name, 
			SecurityThreatGroup securityThreatGroup) 
					throws DuplicateEntityFoundException {
		if (this.securityThreatGroupChapterDao.find(name, securityThreatGroup) 
				!= null) {
			throw new DuplicateEntityFoundException("Duplicate entity found");
		}
		SecurityThreatGroupChapter chapter 
			= this.securityThreatGroupChapterInstanceFactory.createInstance();
		chapter.setGroup(securityThreatGroup);
		chapter.setName(name);
		chapter.setValid(true);
		return this.securityThreatGroupChapterDao.makePersistent(chapter);
	}
	
	/**
	 * Returns security threat group chapters.
	 * 
	 * @return security threat group chapters
	 */
	public List<SecurityThreatGroupChapter> findAll() {
		return this.securityThreatGroupChapterDao.findAll();
	}
	
	/**
	 * Returns security threat group chapters from the specified group
	 * @param group security threat group
	 * @return list of security threat group chapters
	 */
	public List<SecurityThreatGroupChapter> findChaptersByGroup(
			SecurityThreatGroup group) {
		return this.securityThreatGroupChapterDao.findChaptersByGroup(group);
	}
}
