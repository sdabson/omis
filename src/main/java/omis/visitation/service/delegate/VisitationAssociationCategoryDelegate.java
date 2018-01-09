package omis.visitation.service.delegate;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.visitation.dao.VisitationAssociationCategoryDao;
import omis.visitation.domain.VisitationAssociationCategory;

/**
 * Visitation association category delegate.
 * 
 * @author Yidong Li
 * @version 0.1.0 (July 13, 2017)
 * @since OMIS 3.0
 */
public class VisitationAssociationCategoryDelegate {
	/* Data access objects. */
	private VisitationAssociationCategoryDao visitationAssociationCategoryDao;
	
	/* Instance factories. */
	private InstanceFactory<VisitationAssociationCategory> 
	visitationAssociationCategoryInstanceFactory;
	
	/**
	 * Instantiates a visitation association category service implementation 
	 * delegate.
	 * 
	 * @param visitationAssociationCategoryDao
	 * @param visitationAssociationCategoryInstanceFactory 
	 */
	public VisitationAssociationCategoryDelegate(
		final VisitationAssociationCategoryDao visitationAssociationCategoryDao,
		final InstanceFactory<VisitationAssociationCategory> 
		visitationAssociationCategoryInstanceFactory) {
		this.visitationAssociationCategoryDao = visitationAssociationCategoryDao;
		this.visitationAssociationCategoryInstanceFactory 
			= visitationAssociationCategoryInstanceFactory;
	}
	
	/* Management methods. */
	
	/**
	 * Creates a new visitation association category.
	 * 
	 * @param name name
	 * @param sortOrder sort order
	 * @param valid valid
	 * @return newly created visitation association category
	 * @throws DuplicateEntityFoundException thrown when a duplicate visitation
	 * association category is found
	 */
	public VisitationAssociationCategory create(final String name,
			final Short sortOrder, final Boolean valid)
		throws DuplicateEntityFoundException {
		if (this.visitationAssociationCategoryDao.find(name)
				!= null) {
			throw new DuplicateEntityFoundException(
					"Duplicate visitation association category found.");
		}
		
		VisitationAssociationCategory category = 
			this.visitationAssociationCategoryInstanceFactory.createInstance();
		category.setName(name);
		category.setSortOrder(sortOrder);
		category.setValid(valid);
		return this.visitationAssociationCategoryDao.makePersistent(category);
	}
	
	/**
	 * Returns a list of all the visitation association categories.
	 * 
	 * @return visitation association categories
	 */
	public List<VisitationAssociationCategory> findAll() {
		return this.visitationAssociationCategoryDao.findAll();
	}
}