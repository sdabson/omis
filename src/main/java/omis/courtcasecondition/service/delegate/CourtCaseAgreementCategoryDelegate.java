package omis.courtcasecondition.service.delegate;

import java.util.List;

import omis.courtcasecondition.dao.CourtCaseAgreementCategoryDao;
import omis.courtcasecondition.domain.CourtCaseAgreementCategory;
import omis.instance.factory.InstanceFactory;

/**
 * CourtCaseAgreementCategoryDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 15, 2017)
 *@since OMIS 3.0
 *
 */
public class CourtCaseAgreementCategoryDelegate {
	
	public final CourtCaseAgreementCategoryDao courtCaseAgreementCategoryDao;
	
	public final InstanceFactory<CourtCaseAgreementCategory>
		courtCaseAgreementCategoryInstanceFactory;

	/**
	 * Contructor for CourtCaseAgreementCategoryDelegate
	 * @param courtCaseAgreementCategoryDao
	 */
	public CourtCaseAgreementCategoryDelegate(
			final CourtCaseAgreementCategoryDao courtCaseAgreementCategoryDao,
			final InstanceFactory<CourtCaseAgreementCategory>
				courtCaseAgreementCategoryInstanceFactory) {
		this.courtCaseAgreementCategoryDao = courtCaseAgreementCategoryDao;
		this.courtCaseAgreementCategoryInstanceFactory =
				courtCaseAgreementCategoryInstanceFactory;
	}
	
	/**
	 * Creates a CourtCaseAgreementCategory (for unit testing)
	 * @param name - String
	 * @return Created CourtCaseAgreementCategory
	 */
	public CourtCaseAgreementCategory create(final String name){
		CourtCaseAgreementCategory category = this
				.courtCaseAgreementCategoryInstanceFactory.createInstance();
		
		category.setName(name);
		
		return this.courtCaseAgreementCategoryDao.makePersistent(category);
	}
	
	/**
	 * Returns a list of all CourtCaseAgreementCategories
	 * @return List of all CourtCaseAgreementCategories
	 */
	public List<CourtCaseAgreementCategory> findAll() {
		return this.courtCaseAgreementCategoryDao.findAll();
	}
	
}
