package omis.detainernotification.service.delegate;

import java.util.List;

import omis.detainernotification.dao.DetainerActivityCategoryDao;
import omis.detainernotification.domain.DetainerActivityCategory;
import omis.detainernotification.domain.InterstateAgreementInitiatedByCategory;

/**
 * DetainerActivityCategoryDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 21, 2017)
 *@since OMIS 3.0
 *
 */
public class DetainerActivityCategoryDelegate {
	
	public final DetainerActivityCategoryDao detainerActivityCategoryDao;

	/**
	 * Contructor for DetainerActivityCategoryDelegate
	 * @param detainerActivityCategoryDao
	 */
	public DetainerActivityCategoryDelegate(
			final DetainerActivityCategoryDao detainerActivityCategoryDao) {
		this.detainerActivityCategoryDao = detainerActivityCategoryDao;
	}
	
	/**
	 * Returns a list of all DetainerActivityCategorys
	 * @return List of all DetainerActivityCategorys
	 */
	public List<DetainerActivityCategory> findAll() {
		return this.detainerActivityCategoryDao.findAll();
	}
	
	/**
	 * Returns a list of DetainerActivityCategories found by
	 * InterstateAgreementInitiatedByCategory
	 * @param initiatedBy - InterstateAgreementInitiatedByCategory
	 * @return List of DetainerActivityCategories found by
	 * InterstateAgreementInitiatedByCategory
	 */
	public List<DetainerActivityCategory> findByInitiatedBy(
			final InterstateAgreementInitiatedByCategory initiatedBy) {
		return this.detainerActivityCategoryDao.findByInitiatedBy(initiatedBy);
	}
	
	
}
