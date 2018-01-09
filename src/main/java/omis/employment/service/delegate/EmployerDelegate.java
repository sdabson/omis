package omis.employment.service.delegate;

import omis.address.domain.Address;
import omis.employment.dao.EmployerDao;
import omis.employment.domain.Employer;
import omis.instance.factory.InstanceFactory;
import omis.location.domain.Location;

/**
 * Delegate for managing employers.
 *
 * @author Yidong
 * @author Josh Divine
 * @version 0.0.2 (Dec 13, 2017)
 * @since OMIS 3.0
 */
public class EmployerDelegate {
	/* Resources. */
	private final EmployerDao employerDao;
	
	private final InstanceFactory<Employer> employerInstanceFactory;
	
	/* Constructors. */
	
	/**
	 * Instantiates delegate for managing employers.
	 * 
	 * @param employerDao data access object for employer
	 * @param employerInstanceFactory instance factory for employers.
	 */
	public EmployerDelegate(
		final EmployerDao employerDao,
		final InstanceFactory<Employer> employerInstanceFactory) {
		this.employerDao = employerDao;
		this.employerInstanceFactory = employerInstanceFactory;
	}

	/* Methods. */
	
	/**
	 * Creates employer.
	 * 
	 * @param location employer location
	 * @param telephoneNumber telephone number
	 * @return employer
	 */
	public Employer create(final Location location, 
			final Long telephoneNumber) {
		Employer employer = this.employerInstanceFactory.createInstance();
		populateEmployer(employer, location, telephoneNumber);
		return employerDao.makePersistent(employer);
	}
	
	/**
	 * Updates an existing employer.
	 * 
	 * @param employer employer
	 * @param location employer location
	 * @param telephoneNumber telephone number
	 * @return employer
	 */
	public Employer update(final Employer employer, final Location location, 
			final Long telephoneNumber) {
		populateEmployer(employer, location, telephoneNumber);
		return employerDao.makePersistent(employer);
	}
	
	/**
	 * Removes an employer.
	 * 
	 * @param employer employer
	 */
	public void remove(final Employer employer) {
		this.employerDao.makeTransient(employer);
	}

	/**
	 * Finds the employer with the specified name and address.
	 * 
	 * @param name name
	 * @param address address
	 * @return employer
	 */
	public Employer findByNameAndAddress(final String name, 
			final Address address) {
		return this.employerDao.findEmployer(name, address);
	}
	
	// Populates an employer
	private void populateEmployer(final Employer employer, 
			final Location location, final Long telephoneNumber) {
		employer.setLocation(location);
		employer.setTelephoneNumber(telephoneNumber);
	}
}