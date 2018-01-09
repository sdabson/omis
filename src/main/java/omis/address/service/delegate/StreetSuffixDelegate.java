package omis.address.service.delegate;

import java.util.List;

import omis.address.dao.StreetSuffixDao;
import omis.address.domain.StreetSuffix;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;

/**
 * Delegate for street suffixes.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Sep 14, 2015)
 * @since OMIS 3.0
 */
public class StreetSuffixDelegate {

	/* Instance factory. */
	
	private final InstanceFactory<StreetSuffix> streetSuffixInstanceFactory;
	
	/* DAOs. */
	
	private final StreetSuffixDao streetSuffixDao;
	
	/* Constructors. */
	
	/**
	 * Instantiates delegate for street suffixes.
	 * 
	 * @param streetSuffixInstanceFactory instance factory for street suffixes
	 * @param streetSuffixDao data access object for street suffixes
	 */
	public StreetSuffixDelegate(
			final InstanceFactory<StreetSuffix> streetSuffixInstanceFactory,
			final StreetSuffixDao streetSuffixDao) {
		this.streetSuffixInstanceFactory = streetSuffixInstanceFactory;
		this.streetSuffixDao = streetSuffixDao;
	}
	
	/**
	 * Returns street suffixes.
	 * 
	 * @return street suffixes
	 */
	public List<StreetSuffix> findAll() {
		return this.streetSuffixDao.findAll();
	}

	/**
	 * Creates street suffix.
	 * 
	 * @param name name
	 * @param abbreviation abbreviation
	 * @param sortOrder sort order
	 * @param valid whether valid
	 * @return creates street suffix
	 * @throws DuplicateEntityFoundException if street suffix exists
	 */
	public StreetSuffix create(final String name, final String abbreviation,
			final Short sortOrder, final Boolean valid)
					throws DuplicateEntityFoundException {
		if (this.streetSuffixDao.find(name) != null) {
			throw new DuplicateEntityFoundException("Street suffix exists");
		}
		StreetSuffix streetSuffix = this.streetSuffixInstanceFactory
				.createInstance();
		streetSuffix.setName(name);
		streetSuffix.setAbbreviation(abbreviation);
		streetSuffix.setSortOrder(sortOrder);
		streetSuffix.setValid(valid);
		return this.streetSuffixDao.makePersistent(streetSuffix);
	}
}