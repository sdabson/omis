package omis.separationneed.service.delegate;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.separationneed.dao.SeparationNeedReasonAssociationDao;
import omis.separationneed.domain.SeparationNeed;
import omis.separationneed.domain.SeparationNeedReason;
import omis.separationneed.domain.SeparationNeedReasonAssociation;

/**
 * Delegate for separation need reason associations.
 *
 * @author Josh Divine
 * @version 0.0.1 (Aug 8, 2017)
 * @since OMIS 3.0
 */
public class SeparationNeedReasonAssociationDelegate {

	private final SeparationNeedReasonAssociationDao
		separationNeedReasonAssociationDao;
	
	private final InstanceFactory<SeparationNeedReasonAssociation> 
	separationNeedReasonAssociationInstanceFactory;
	
	public SeparationNeedReasonAssociationDelegate(
			final SeparationNeedReasonAssociationDao
				separationNeedReasonAssociationDao,
			final InstanceFactory<SeparationNeedReasonAssociation> 
				separationNeedReasonAssociationInstanceFactory) {
		this.separationNeedReasonAssociationDao = 
				separationNeedReasonAssociationDao;
		this.separationNeedReasonAssociationInstanceFactory = 
				separationNeedReasonAssociationInstanceFactory;
	}
	
	/**
	 * Creates a separation need reason association for the specified reason and
	 * separation need.
	 * 
	 * @param separationNeed separation need
	 * @param reason separation need reason
	 * @return separation need reason association
	 * @throws DuplicateEntityFoundException thrown when a duplicate separation
	 * need reason association is found
	 */
	public SeparationNeedReasonAssociation create(
			final SeparationNeed separationNeed, 
			final SeparationNeedReason reason)
		throws DuplicateEntityFoundException {
		if (this.separationNeedReasonAssociationDao.find(
				separationNeed, reason) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate separation need reason association found");
		}
		SeparationNeedReasonAssociation association
			= this.separationNeedReasonAssociationInstanceFactory
			.createInstance();
		association.setSeparationNeed(separationNeed);
		this.populateSeparationNeedReasonAssociation(association, reason);
		return this.separationNeedReasonAssociationDao
				.makePersistent(association);
	}

	/**
	 * Updates the specified separation need reason association.
	 * 
	 * @param association separation need reason association
	 * @param reason separation need reason
	 * @return updated separation need reason association
	 * @throws DuplicateEntityFoundException thrown when a duplicate separation
	 * need reason association is found
	 */
	public SeparationNeedReasonAssociation update(
			final SeparationNeedReasonAssociation association,
			final SeparationNeedReason reason)
			throws DuplicateEntityFoundException {
		if (this.separationNeedReasonAssociationDao.findExcluding(
				association.getSeparationNeed(), reason, association) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate separation need reason association found");
		}
		this.populateSeparationNeedReasonAssociation(association, reason);
		return this.separationNeedReasonAssociationDao.makePersistent(
				association);
	}

	/**
	 * Removes the specified separation need reason association.
	 * 
	 * @param association separation need reason association
	 */
	public void remove(final SeparationNeedReasonAssociation association) {
		this.separationNeedReasonAssociationDao.makeTransient(
				association);
	}
	
	/**
	 * Returns a list of separation need reason associations for the specified
	 * separation need.
	 * 
	 * @param separationNeed separation need
	 * @return list of separation need reason associations
	 */
	public List<SeparationNeedReasonAssociation> findBySeparationNeed(
			final SeparationNeed separationNeed) {
		return this.separationNeedReasonAssociationDao.findBySeparationNeed(
				separationNeed);
	}
	
	// Populates a separation need reason association
	private SeparationNeedReasonAssociation 
		populateSeparationNeedReasonAssociation(
				final SeparationNeedReasonAssociation association,
				final SeparationNeedReason reason) {
	association.setReason(reason);
	return association;
}
}
