package omis.detainernotification.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.detainernotification.domain.InterstateAgreementDetainer;
import omis.detainernotification.domain.InterstateDetainerActivity;
import omis.document.domain.Document;

/**
 * InterstateDetainerActivityDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 21, 2017)
 *@since OMIS 3.0
 *
 */
public interface InterstateDetainerActivityDao
		extends GenericDao<InterstateDetainerActivity> {
	
	/**
	 * Finds and returns an InterstateDetainerActivity with the specified 
	 * properties
	 * @param interstateAgreementDetainer- InterstateAgreementDetainer
	 * @param activityDate - Date
	 * @param document - Document
	 * @return InterstateDetainerActivity found with specified properties
	 */
	InterstateDetainerActivity find(
			InterstateAgreementDetainer interstateAgreementDetainer,
			Date activityDate, Document document);
	
	/**
	 * Finds and returns an InterstateDetainerActivity with the specified 
	 * properties excluding specified InterstateDetainerActivity
	 * @param interstateAgreementDetainer- InterstateAgreementDetainer
	 * @param activityDate - Date
	 * @param document - Document
	 * @param interstateDetainerActivityExcluded - InterstateDetainerActivity
	 * to exclude from search
	 * @return InterstateDetainerActivity found with specified properties
	 * excluding specified InterstateDetainerActivity
	 */
	InterstateDetainerActivity findExcluding(
			InterstateAgreementDetainer interstateAgreementDetainer,
			Date activityDate, Document document,
			InterstateDetainerActivity interstateDetainerActivityExcluded);
	
	/**
	 * Returns a list of InterstateDetainerActivities found by specified
	 * InterstateAgreementDetainer
	 * @param interstateAgreementDetainer - InterstateAgreementDetainer
	 * @return List of InterstateDetainerActivities found by specified
	 * Detainer
	 */
	List<InterstateDetainerActivity> findByInterstateAgreementDetainer(
			InterstateAgreementDetainer interstateAgreementDetainer);
}
