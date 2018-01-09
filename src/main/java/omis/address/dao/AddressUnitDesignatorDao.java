package omis.address.dao;

import omis.address.domain.AddressUnitDesignator;
import omis.dao.GenericDao;

/**
 * Data access object for address unit designators.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 8, 2014)
 * @since OMIS 3.0
 */
public interface AddressUnitDesignatorDao
		extends GenericDao<AddressUnitDesignator> {

	/**
	 * Returns address unit designator.
	 * 
	 * @param name name
	 * @return address unit designator
	 */
	AddressUnitDesignator find(String name);
}