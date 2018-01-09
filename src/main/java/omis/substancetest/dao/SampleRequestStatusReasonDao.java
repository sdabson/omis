package omis.substancetest.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.substancetest.domain.SampleRequestStatusReason;

/**
 * Sample status reason data access object.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Apr 29, 2015)
 * @since OMIS 3.0
 */
public interface SampleRequestStatusReasonDao extends GenericDao<SampleRequestStatusReason>{

	/**
	 * Returns a list of status reasons with the specified taken value.
	 * 
	 * @return list of sample status reasons
	 */
	List<SampleRequestStatusReason> findStatusReasons();
}