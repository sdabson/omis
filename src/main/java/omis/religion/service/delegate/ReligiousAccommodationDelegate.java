package omis.religion.service.delegate;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.religion.dao.ReligiousAccommodationDao;
import omis.religion.domain.ReligiousAccommodation;

/**
 * Delegate for religious accommodations.
 *
 * @author Josh Divine
 * @version 0.1.0 (Jul 26, 2017)
 * @since OMIS 3.0
 *
 */
public class ReligiousAccommodationDelegate {

	/* Data access objects. */
	
	private final ReligiousAccommodationDao religiousAccommodationDao;

	/* Instance factories. */
	
	private final InstanceFactory<ReligiousAccommodation>
			religiousAccommodationInstanceFactory;
	
	/**
	 * Constructor for religious accommodations.
	 * 
	 * @param religiousAccommodationDao religious accommodation data access 
	 * object
	 * @param religiousAccommodationInstanceFactory religious accommodation 
	 * instance factory
	 */
	public ReligiousAccommodationDelegate(
			final ReligiousAccommodationDao religiousAccommodationDao,
			final InstanceFactory<ReligiousAccommodation>
				religiousAccommodationInstanceFactory) {
		this.religiousAccommodationDao = religiousAccommodationDao;
		this.religiousAccommodationInstanceFactory = 
				religiousAccommodationInstanceFactory;
	}
	
	/**
	 * Creates a religious accommodation.
	 * 
	 * @param name name
	 * @param sortOrder sort order
	 * @param valid valid
	 * @return religious accommodation
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public ReligiousAccommodation create(final String name, 
			final Short sortOrder, final Boolean valid) 
					throws DuplicateEntityFoundException {
		if (this.religiousAccommodationDao.find(name) != null) {
			throw new DuplicateEntityFoundException();
		}
		ReligiousAccommodation accommodation = 
				this.religiousAccommodationInstanceFactory.createInstance();
		populateReligiousAccommodation(accommodation, name, sortOrder, valid);
		return this.religiousAccommodationDao.makePersistent(accommodation);
	}
	
	/**
	 * Updates a religious accommodation.
	 * 
	 * @param accommodation religious accommodation
	 * @param name name
	 * @param sortOrder sort order
	 * @param valid valid
	 * @return religious accommodation
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public ReligiousAccommodation update(
			final ReligiousAccommodation accommodation, final String name, 
			final Short sortOrder, final Boolean valid) 
					throws DuplicateEntityFoundException {
		if (this.religiousAccommodationDao.findExcluding(name, accommodation) != 
				null) {
			throw new DuplicateEntityFoundException();
		}
		populateReligiousAccommodation(accommodation, name, sortOrder, valid);
		return this.religiousAccommodationDao.makePersistent(accommodation);
	}
	
	/**
	 * Removes a religious accommodation.
	 * 
	 * @param accommodation religious accommodation
	 */
	public void remove(final ReligiousAccommodation accommodation) {
		this.religiousAccommodationDao.makeTransient(accommodation);
	}

	// Populates a religious accommodation
	private void populateReligiousAccommodation(
			final ReligiousAccommodation accommodation, final String name,
			final Short sortOrder, final Boolean valid) {
		accommodation.setName(name);
		accommodation.setSortOrder(sortOrder);
		accommodation.setValid(valid);
	}

	/**
	 * Returns a list of religious accommodations.
	 * 
	 * @return list of religious accommodations
	 */
	public List<ReligiousAccommodation> findAll() {
		return this.religiousAccommodationDao.findAll();
	}
}
