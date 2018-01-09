package omis.separationneed.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.separationneed.domain.SeparationNeed;
import omis.separationneed.domain.SeparationNeedReason;
import omis.separationneed.domain.SeparationNeedReasonAssociation;

/**
 * Separation need reason association data access object.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Aug 20, 2015)
 * @since OMIS 3.0
 */
public interface SeparationNeedReasonAssociationDao 
	extends GenericDao<SeparationNeedReasonAssociation> {

	/**
	 * Returns a list of separation need reason associations with the specified
	 * separation need.
	 * 
	 * @param separationNeed separation need
	 * @return list of separation need reason associations
	 */
	public List<SeparationNeedReasonAssociation> findBySeparationNeed(
			SeparationNeed separationNeed);

	/**
	 * Returns the separation need reason association with the specified
	 * separation need and separation need reason.
	 * 
	 * @param separationNeed separation need
	 * @param reason separation need reason
	 * @return separation need reason association
	 */
	public SeparationNeedReasonAssociation find(SeparationNeed separationNeed,
			SeparationNeedReason reason);

	/**
	 * Returns the separation need reason association with the specified
	 * separation need and separation need reason, excluding the specified
	 * separation need reason association.
	 * 
	 * @param separationNeed separation need
	 * @param reason separation need reason
	 * @param association separation need reason association
	 * @return separation need reason association
	 */
	public SeparationNeedReasonAssociation findExcluding(
			SeparationNeed separationNeed,
			SeparationNeedReason reason,
			SeparationNeedReasonAssociation association);
}