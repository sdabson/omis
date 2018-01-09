package omis.adaaccommodation.service.impl;

import java.util.List;

import omis.adaaccommodation.dao.DisabilityClassificationCategoryDao;
import omis.adaaccommodation.dao.DisabilityDao;
import omis.adaaccommodation.domain.Disability;
import omis.adaaccommodation.domain.DisabilityClassificationCategory;
import omis.adaaccommodation.service.DisabilityService;
import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;

/**
 * Disability service implementation.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 20, 2015)
 * @since OMIS 3.0
 */
public class DisabilityServiceImpl implements DisabilityService {
	
	/* Data access objects. */
	private final DisabilityDao disabilityDao;
	private final DisabilityClassificationCategoryDao 
		disabilityClassificationCategoryDao;
	
	/* Instance factories. */	
	private final InstanceFactory<Disability> disabilityInstanceFactory;

	/* Component retrievers. */
	private final AuditComponentRetriever auditComponentRetriever;
	
	public DisabilityServiceImpl(
			final DisabilityDao disabilityDao,
			final DisabilityClassificationCategoryDao
			disabilityClassificationCategoryDao, 
			final InstanceFactory<Disability> disabilityInstanceFactory, 
			final AuditComponentRetriever auditComponentRetriever) {
			
			this.disabilityDao = disabilityDao;
			this.disabilityClassificationCategoryDao 
				= disabilityClassificationCategoryDao;
			this.disabilityInstanceFactory 
				= disabilityInstanceFactory;
			this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/** {@inheritDoc} */
	@Override
	public Disability create(Offender offender, String description,
			DisabilityClassificationCategory disabilityClassificationCategory)
			throws DuplicateEntityFoundException {
		Disability disability = this.disabilityInstanceFactory.createInstance();
		if(this.disabilityDao.find(offender, description, 
				disabilityClassificationCategory) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate disability found");
		}
		disability.setOffender(offender);
		disability.setDescription(description);
		disability.setDisabilityClassification(
				disabilityClassificationCategory);
		disability.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		disability.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		
		return this.disabilityDao.makePersistent(disability);
	}

	/** {@inheritDoc} */
	@Override
	public Disability update(Disability disability, String description,
			DisabilityClassificationCategory disabilityClassificationCategory)
			throws DuplicateEntityFoundException {
		if(this.disabilityDao.findExcluding(disability.getOffender(), 
				description, disabilityClassificationCategory, 
				disability) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate disability found");
		}
		disability.setDescription(description);
		disability.setDisabilityClassification(
				disabilityClassificationCategory);
		disability.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		
		return this.disabilityDao.makePersistent(disability);
	}

	/** {@inheritDoc} */
	@Override
	public void remove(Disability disability) {
		this.disabilityDao.makeTransient(disability);
	}

	/** {@inheritDoc} */
	@Override
	public List<DisabilityClassificationCategory> 
			findAllDisabilityClassificationCategories() {
		return this.disabilityClassificationCategoryDao.findCategories();
	}
}