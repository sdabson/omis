package omis.stg.service.delegate;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.stg.dao.SecurityThreatGroupRankDao;
import omis.stg.domain.SecurityThreatGroup;
import omis.stg.domain.SecurityThreatGroupRank;

/**
 * Delegate for security threat group ranks.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Dec 07, 2016)
 * @since OMIS 3.0
 */
public class SecurityThreatGroupRankDelegate {

	private final InstanceFactory<SecurityThreatGroupRank>
		securityThreatGroupRankInstanceFactory;

	private final SecurityThreatGroupRankDao securityThreatGroupRankDao;

	/**
	 * Constructor
	 * @param securityThreatGroupRankInstanceFactory
	 * @param securityThreatGroupRankDao
	 */
	public SecurityThreatGroupRankDelegate(
			final InstanceFactory<SecurityThreatGroupRank> 
				securityThreatGroupRankInstanceFactory,
			final SecurityThreatGroupRankDao securityThreatGroupRankDao) {
		this.securityThreatGroupRankInstanceFactory 
			= securityThreatGroupRankInstanceFactory;
		this.securityThreatGroupRankDao = securityThreatGroupRankDao;
	}
	
	/**
	 * Creates a new security threat group rank with the specified name
	 * @param name security threat group rank name
	 * @return security threat group rank
	 * @throws DuplicateEntityFoundException
	 */
	public SecurityThreatGroupRank create(String name, 
			SecurityThreatGroup securityThreatGroup) 
			throws DuplicateEntityFoundException {
		if (this.securityThreatGroupRankDao.find(name, securityThreatGroup) 
				!= null) {
			throw new DuplicateEntityFoundException("Duplicate entity found");
		}
		SecurityThreatGroupRank rank 
			= this.securityThreatGroupRankInstanceFactory.createInstance();
		rank.setName(name);
		rank.setGroup(securityThreatGroup);
		rank.setValid(true);
		return this.securityThreatGroupRankDao.makePersistent(rank);
	}
	
	/**
	 * Returns all security threat group ranks
	 * @return list of security threat group ranks
	 */
	public List<SecurityThreatGroupRank> findAll() {
		return this.securityThreatGroupRankDao.findAll();
	}
	
	/**
	 * Returns security threat group ranks from the specified group
	 * @param group security threat group
	 * @return security threat group rank
	 */
	public List<SecurityThreatGroupRank> findRanksByGroup(
			SecurityThreatGroup securityThreatGroup) {
		return this.securityThreatGroupRankDao.findRanksByGroup(
				securityThreatGroup);
	}
}
