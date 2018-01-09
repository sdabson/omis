package omis.treatment.service.delegate;

import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.location.domain.Location;
import omis.treatment.dao.TreatmentCenterDao;
import omis.treatment.domain.TreatmentCenter;

/**
 * Delegate for treatment center.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 17, 2017)
 * @since OMIS 3.0
 */
public class TreatmentCenterDelegate {

	/* Resources. */
	
	private final TreatmentCenterDao treatmentCenterDao;
	
	private final InstanceFactory<TreatmentCenter> 
		treatmentCenterInstanceFactory;
	
	/* Constructors. */
	
	/**
	 * Instantiates delegate for managing treatment center.
	 * 
	 * @param treatmentCenterDao data access object for treatment center
	 * @param treatmentCenterInstanceFactory instance factory for treatment 
	 * center
	 */
	public TreatmentCenterDelegate(
			final TreatmentCenterDao treatmentCenterDao,
			final InstanceFactory<TreatmentCenter> 
				treatmentCenterInstanceFactory) {
		this.treatmentCenterDao = treatmentCenterDao;
		this.treatmentCenterInstanceFactory = treatmentCenterInstanceFactory;
	}

	/**
	 * Creates a new treatment center.
	 * 
	 * @param location location
	 * @param name name
	 * @param telephoneNumber telephone number
	 * @return created treatment center
	 * @throws DuplicateEntityFoundException thrown when a duplicate treatment 
	 * center is found
	 */
	public TreatmentCenter create(Location location, String name, 
			Long telephoneNumber) throws DuplicateEntityFoundException {
		if (this.treatmentCenterDao.find(location, name, telephoneNumber) 
				!= null) {
			throw new DuplicateEntityFoundException("Duplicate treatment center"
					+ " found");
		}
		TreatmentCenter treatmentCenter 
			= this.treatmentCenterInstanceFactory.createInstance();
		treatmentCenter.setLocation(location);
		treatmentCenter.setName(name);
		treatmentCenter.setTelephoneNumber(telephoneNumber);
		return this.treatmentCenterDao.makePersistent(treatmentCenter);
	}
	
	/**
	 * Updates the specified treatment center.
	 * 
	 * @param treatmentCenter treatment center
	 * @param location location
	 * @param name name
	 * @param telephoneNumber telephone number
	 * @return updated treatment center
	 * @throws DuplicateEntityFoundException thrown when a duplicate treatment 
	 * center is found
	 */
	public TreatmentCenter update(TreatmentCenter treatmentCenter, 
			Location location, String name, Long telephoneNumber) 
				throws DuplicateEntityFoundException {
		if (this.treatmentCenterDao.findExcluding(location, 
				name, telephoneNumber, treatmentCenter) 
				!= null) {
			throw new DuplicateEntityFoundException("Duplicate treatment center "
					+ "found");
		}
		treatmentCenter.setLocation(location);
		treatmentCenter.setName(name);
		treatmentCenter.setTelephoneNumber(telephoneNumber);
		return this.treatmentCenterDao.makePersistent(treatmentCenter);
	}
	
	/**
	 * Removes the specified treatment center.
	 * 
	 * @param treatmentCenter treatment center
	 */
	public void remove(TreatmentCenter treatmentCenter) {
		this.treatmentCenterDao.makeTransient(treatmentCenter);
	}
}
