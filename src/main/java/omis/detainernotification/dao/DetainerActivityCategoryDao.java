package omis.detainernotification.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.detainernotification.domain.DetainerActivityCategory;
import omis.detainernotification.domain.InterstateAgreementInitiatedByCategory;

/**
 * DetainerActivityCategoryDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 21, 2017)
 *@since OMIS 3.0
 *
 */
public interface DetainerActivityCategoryDao
		extends GenericDao<DetainerActivityCategory> {
	
	
	/**
	 * Returns a list of DetainerActivityCategories found by
	 * InterstateAgreementInitiatedByCategory
	 * @param initiatedBy - InterstateAgreementInitiatedByCategory
	 * @return List of DetainerActivityCategories found by
	 * InterstateAgreementInitiatedByCategory
	 */
	List<DetainerActivityCategory> findByInitiatedBy(
			InterstateAgreementInitiatedByCategory initiatedBy);
	
}
