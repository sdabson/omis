package omis.courtcasecondition.dao;

import java.util.List;

import omis.courtcasecondition.domain.CourtCaseAgreementCategory;
import omis.dao.GenericDao;

/**
 * Data access object for CourtCaseAgreementCategory.
 * 
 * @author Jonny Santy
 * @version 0.1.0 (Jul 18, 2016)
 * @since OMIS 3.0
 */
public interface CourtCaseAgreementCategoryDao
	extends GenericDao<CourtCaseAgreementCategory> {
	
	/**
	 * Returns a list of all CourtCaseAgreementCategories.  
	 * @return List of all CourtCaseAgreementCategories
	 */
	@Override
	List<CourtCaseAgreementCategory> findAll();
}
