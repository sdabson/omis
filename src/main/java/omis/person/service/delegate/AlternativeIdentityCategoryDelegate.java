package omis.person.service.delegate;

import java.util.List;

import omis.person.dao.AlternativeIdentityCategoryDao;
import omis.person.domain.AlternativeIdentityCategory;

/**
 * AlternativeIdentityCategoryDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Nov 9, 2016)
 *@since OMIS 3.0
 *
 */
public class AlternativeIdentityCategoryDelegate {
	
	private final AlternativeIdentityCategoryDao alternativeIdentityCategoryDao;

	/**
	 * Constructor for AlternativeIdentityCategoryDelegate
	 * @param alternativeIdentityCategoryDao
	 */
	public AlternativeIdentityCategoryDelegate(AlternativeIdentityCategoryDao 
			alternativeIdentityCategoryDao) {
		this.alternativeIdentityCategoryDao = alternativeIdentityCategoryDao;
	}
	
	/**
	 * Returns a list of all AlternativeIdentityCategories
	 * @return list of all AlternativeIdentityCategories
	 */
	public List<AlternativeIdentityCategory> findAll() {
		return this.alternativeIdentityCategoryDao.findAll();
	}
	
	
}
