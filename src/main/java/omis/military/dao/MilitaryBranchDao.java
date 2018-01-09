package omis.military.dao;

import omis.dao.GenericDao;
import omis.military.domain.MilitaryBranch;

/**
 * Military branch data access object.
 * 
 * @author Joel Norris
 * @version 0.1.0 (May 13, 2015)
 * @since OMIS 3.0
 */
public interface MilitaryBranchDao extends GenericDao<MilitaryBranch> {

	MilitaryBranch find(String name, String shortName, Boolean valid);
}