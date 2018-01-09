package omis.presentenceinvestigation.service.delegate;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.presentenceinvestigation.dao.PresentenceInvestigationCategoryDao;
import omis.presentenceinvestigation.domain.PresentenceInvestigationCategory;

/**
 * PresentenceInvestigationCategoryDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jun 23, 2017)
 *@since OMIS 3.0
 *
 */
public class PresentenceInvestigationCategoryDelegate {
	
	private final PresentenceInvestigationCategoryDao
			presentenceInvestigationCategoryDao;
	
	private final InstanceFactory<PresentenceInvestigationCategory>
		presentenceInvestigationCategoryInstanceFactory;
	
	/**
	 * Contructor for PresentenceInvestigationCategoryDelegate
	 * @param presentenceInvestigationCategoryDao
	 */
	public PresentenceInvestigationCategoryDelegate(
			final PresentenceInvestigationCategoryDao
				presentenceInvestigationCategoryDao,
			final InstanceFactory<PresentenceInvestigationCategory>
				presentenceInvestigationCategoryInstanceFactory) {
		this.presentenceInvestigationCategoryDao =
				presentenceInvestigationCategoryDao;
		this.presentenceInvestigationCategoryInstanceFactory =
				presentenceInvestigationCategoryInstanceFactory;
	}
	
	/**
	 * Returns a list of all PresentenceInvestigationCategories
	 * @return List of all PresentenceInvestigationCategories
	 */
	public List<PresentenceInvestigationCategory> findAll() {
		return this.presentenceInvestigationCategoryDao.findAll();
	}
	
	/**
	 * Creates a PresentenceInvestigationCategory with the specified properties
	 * (For use in testing)
	 * @param name - String
	 * @param valid - Boolean
	 * @return Newly created PresentenceInvestigationCategory
	 * @throws DuplicateEntityFoundException - when a
	 * PresentenceInvestigationCategory already exists with specified name
	 */
	public PresentenceInvestigationCategory create(
			final String name, final Boolean valid) throws DuplicateEntityFoundException {
		PresentenceInvestigationCategory category =
				this.presentenceInvestigationCategoryInstanceFactory
				.createInstance();
		if(this.presentenceInvestigationCategoryDao.find(name) != null) {
			throw new DuplicateEntityFoundException(
					"PresentenceInvestigationCategory already exists with " +
					"specified name.");
		}
		category.setName(name);
		category.setValid(valid);
		
		return this.presentenceInvestigationCategoryDao.makePersistent(category);
	}
}
