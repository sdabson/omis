package omis.address.service.delegate;

import java.util.List;

import omis.address.dao.AddressUnitDesignatorDao;
import omis.address.domain.AddressUnitDesignator;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;

/**
 * Delegate for address unit designators.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Sep 14, 2015)
 * @since OMIS 3.0
 */
public class AddressUnitDesignatorDelegate {
	
	/* Instance factory. */
	
	private final InstanceFactory<AddressUnitDesignator>
	addressUnitDesignatorInstanceFactory;

	/* DAOs. */
	
	private final AddressUnitDesignatorDao addressUnitDesignatorDao;
	
	/* Constructors. */
	
	/**
	 * Instantiates delegate for address unit designators.
	 * 
	 * @param addressUnitDesignatorInstanceFactory instance factory for address
	 * unit designators
	 * @param addressUnitDesignatorDao data access object for address unit
	 * designators
	 */
	public AddressUnitDesignatorDelegate(
			final InstanceFactory<AddressUnitDesignator>
				addressUnitDesignatorInstanceFactory,
			final AddressUnitDesignatorDao addressUnitDesignatorDao) {
		this.addressUnitDesignatorInstanceFactory
			= addressUnitDesignatorInstanceFactory;
		this.addressUnitDesignatorDao = addressUnitDesignatorDao;
	}
	
	/* Methods. */
	
	/**
	 * Returns address unit designators.
	 * 
	 * @return address unit designators
	 */
	public List<AddressUnitDesignator> findAll() {
		return this.addressUnitDesignatorDao.findAll();
	}

	/**
	 * Creates address unit designator.
	 * 
	 * @param name name
	 * @param abbreviation abbreviation
	 * @param sortOrder sort order
	 * @param valid whether valid
	 * @return creates address unit designator
	 * @throws DuplicateEntityFoundException if address unit designator exists
	 */
	public AddressUnitDesignator create(
			final String name, final String abbreviation,
			final Short sortOrder, final Boolean valid)
				throws DuplicateEntityFoundException {
		if (this.addressUnitDesignatorDao.find(name) != null) {
			throw new DuplicateEntityFoundException(
					"Address unit designator exists");
		}
		AddressUnitDesignator unitDesignator
			= this.addressUnitDesignatorInstanceFactory.createInstance();
		unitDesignator.setName(name);
		unitDesignator.setAbbreviation(abbreviation);
		unitDesignator.setSortOrder(sortOrder);
		unitDesignator.setValid(valid);
		return this.addressUnitDesignatorDao.makePersistent(unitDesignator);
	}
}