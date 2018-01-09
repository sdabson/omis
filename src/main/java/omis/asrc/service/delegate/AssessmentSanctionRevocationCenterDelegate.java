package omis.asrc.service.delegate;

import omis.asrc.dao.AssessmentSanctionRevocationCenterDao;
import omis.asrc.domain.AssessmentSanctionRevocationCenter;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.location.domain.Location;

/**
 * Delegate for assessment sanction revocation center.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 17, 2017)
 * @since OMIS 3.0
 */
public class AssessmentSanctionRevocationCenterDelegate {

	/* Resources. */
	
	private final AssessmentSanctionRevocationCenterDao 
		assessmentSanctionRevocationCenterDao;
	
	private final InstanceFactory<AssessmentSanctionRevocationCenter> 
		assessmentSanctionRevocationCenterInstanceFactory;
	
	/* Constructors. */
	
	/**
	 * Instantiates delegate for managing assessment sanction revocation 
	 * centers.
	 * 
	 * @param assessmentSanctionRevocationCenterDao data access object for 
	 * assessment sanction revocation centers
	 * @param assessmentSanctionRevocationCenterInstanceFactory instance factory
	 *  for assessment sanction revocation centers
	 */
	public AssessmentSanctionRevocationCenterDelegate(
			final AssessmentSanctionRevocationCenterDao 
				assessmentSanctionRevocationCenterDao,
			final InstanceFactory<AssessmentSanctionRevocationCenter> 
				assessmentSanctionRevocationCenterInstanceFactory) {
		this.assessmentSanctionRevocationCenterDao 
			= assessmentSanctionRevocationCenterDao;
		this.assessmentSanctionRevocationCenterInstanceFactory 
			= assessmentSanctionRevocationCenterInstanceFactory;
	}
	
	/**
	 * Creates a new assessment sanction revocation center.
	 * 
	 * @param location location
	 * @param name name
	 * @param telephoneNumber telephone number
	 * @return created assessment sanction revocation center
	 * @throws DuplicateEntityFoundException thrown when a duplicate assessment
	 * sanction revocation center is found
	 */
	public AssessmentSanctionRevocationCenter create(Location location, 
			String name, Long telephoneNumber) 
					throws DuplicateEntityFoundException {
		if (this.assessmentSanctionRevocationCenterDao.find(location, name, 
				telephoneNumber) != null) {
			throw new DuplicateEntityFoundException("Duplicate assessment "
					+ "sanction revocation center found");
		}
		AssessmentSanctionRevocationCenter assessmentSanctionRevocationCenter 
			= this.assessmentSanctionRevocationCenterInstanceFactory
			.createInstance();
		assessmentSanctionRevocationCenter.setLocation(location);
		assessmentSanctionRevocationCenter.setName(name);
		assessmentSanctionRevocationCenter.setTelephoneNumber(telephoneNumber);
		return this.assessmentSanctionRevocationCenterDao.makePersistent(
				assessmentSanctionRevocationCenter);
	}
	
	/**
	 * Updates the specified assessment sanction revocation center.
	 * 
	 * @param assessmentSanctionRevocationCenter assessment sanction revocation
	 * center
	 * @param location location
	 * @param name name
	 * @param telephoneNumber telephone number
	 * @return updated assessment sanction revocation center
	 * @throws DuplicateEntityFoundException thrown when a duplicate assessment
	 * sanction revocation center is found
	 */
	public AssessmentSanctionRevocationCenter update(
		AssessmentSanctionRevocationCenter assessmentSanctionRevocationCenter,
		Location location, String name, Long telephoneNumber) 
				throws DuplicateEntityFoundException {
		if (this.assessmentSanctionRevocationCenterDao.findExcluding(location, 
				name, telephoneNumber, assessmentSanctionRevocationCenter) 
				!= null) {
			throw new DuplicateEntityFoundException("Duplicate assessment "
					+ "sanction revocation center found");
		}
		assessmentSanctionRevocationCenter.setLocation(location);
		assessmentSanctionRevocationCenter.setName(name);
		assessmentSanctionRevocationCenter.setTelephoneNumber(telephoneNumber);
		return this.assessmentSanctionRevocationCenterDao.makePersistent(
				assessmentSanctionRevocationCenter);
	}
	
	/**
	 * Removes the specified assessment sanction revocation center
	 * 
	 * @param assessmentSanctionRevocationCenter assessment sanction revocation
	 * center
	 */
	public void remove(AssessmentSanctionRevocationCenter 
			assessmentSanctionRevocationCenter) {
		this.assessmentSanctionRevocationCenterDao.makeTransient(
				assessmentSanctionRevocationCenter);
	}
}
