package omis.family.service.delegate;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.family.dao.FamilyAssociationCategoryDao;
import omis.family.domain.FamilyAssociationCategory;
import omis.family.domain.FamilyAssociationCategoryClassification;
import omis.instance.factory.InstanceFactory;

/**
 * Family association category delegate.
 * 
 * @author Yidong Li
 * @author Sheronda Vaughn
 * @version 0.1.1 (Oct 6, 2015)
 * @since OMIS 3.0
 */
public class FamilyAssociationCategoryDelegate {

	/* Data access objects. */
	private FamilyAssociationCategoryDao familyAssociationCategoryDao;
	
	/* Instance factories. */
	private InstanceFactory<FamilyAssociationCategory> 
		familyAssociationCategoryInstanceFactory;

	/* Constructor. */

	/**
	 * Instantiates a family association category delegate with
	 * the specified data access object and instance factory.
	 * 
	 * @param familyAssociationCategoryDao family association category data 
	 * access object
	 * @param familyAssociationCategoryInstanceFactory family association 
	 * category instance factory
	 */
	public FamilyAssociationCategoryDelegate(
			final FamilyAssociationCategoryDao familyAssociationCategoryDao,
			final InstanceFactory<FamilyAssociationCategory> 
			familyAssociationCategoryInstanceFactory) {
		this.familyAssociationCategoryDao = familyAssociationCategoryDao;
		this.familyAssociationCategoryInstanceFactory 
			= familyAssociationCategoryInstanceFactory;
	}
	
	/* Management methods. */
	
	/**
	 * Find all family association categories.
	 * 
	 * @return created family association
	 */
	public List<FamilyAssociationCategory> findAll() {
		return this.familyAssociationCategoryDao.findAll();
	}
	
	/**
	 * Create a family association category.
	 * @param name name
	 * @param valid valid
	 * @param sortOrder sort order
	 * @param classification famil association classification
	 * @return created family association category
	 * @throws DuplicateEntityFoundException  
	 */
	public FamilyAssociationCategory create(final String name, 
		final Boolean valid, final Short sortOrder, 
		final FamilyAssociationCategoryClassification classification) 
		throws DuplicateEntityFoundException {
		if (this.familyAssociationCategoryDao.find(name) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate family association category found");
		}
		FamilyAssociationCategory category 
			= this.familyAssociationCategoryInstanceFactory.createInstance();
		category.setClassification(classification);
		category.setName(name);
		category.setSortOrder(sortOrder);
		category.setValid(valid);
		return this.familyAssociationCategoryDao.makePersistent(category);
	}
	
	/**
	 * Remove a family association category.
	 * @param category family association category
	 * @throws DuplicateEntityFoundException duplicate entity found exception
	 */
	public void remove(
		final FamilyAssociationCategory category) 
		throws DuplicateEntityFoundException {
		this.familyAssociationCategoryDao.makeTransient(category);
	}
}