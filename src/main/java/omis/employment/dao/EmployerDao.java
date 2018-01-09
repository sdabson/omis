package omis.employment.dao;

import omis.address.domain.Address;
import omis.dao.GenericDao;
import omis.employment.domain.Employer;

/**
 * Employer data access object.
 * 
 * @author Yidong Li
 * @version 0.1.0 (Aug 4, 2015)
 * @since OMIS 3.0
 */

public interface EmployerDao 
	extends GenericDao<Employer> {
	/**
	 * Search an employer.
	 * @param name employer name
	 * @param address employer address
	 * @return employer
	 */
	Employer findEmployer(String name, Address address);
}
