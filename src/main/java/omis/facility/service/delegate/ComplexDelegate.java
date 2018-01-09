package omis.facility.service.delegate;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.facility.dao.ComplexDao;
import omis.facility.domain.Complex;
import omis.facility.domain.Facility;
import omis.instance.factory.InstanceFactory;

/**
 * Complex delegate.
 * 
 * @author Joel Norris
 * @author Josh Divine
 * @version 0.1.1 (Aug 3, 2017)
 * @since OMIS 3.0
 */
public class ComplexDelegate {
	
	/* Data access objects. */
	
	private ComplexDao complexDao;
	
	/* Instance Factories. */
	
	private InstanceFactory<Complex> complexInstanceFactory;
	
	/* Exception messages. */
	
	private final String DUPLICATE_COMPLEX_FOUND_MESSAGE
		= "Duplicate complex found";
	
	/* Constructor. */
	
	/**
	 * Instantiates a complex delegate with the specified data access object
	 * and instance factory.
	 * 
	 * @param complexDao complex data access object
	 * @param complexInstanceFactory complex instance factory
	 */
	public ComplexDelegate(final ComplexDao complexDao,
			final InstanceFactory<Complex> complexInstanceFactory) {
		this.complexDao = complexDao;
		this.complexInstanceFactory = complexInstanceFactory;
	}
	
	/* Delegate Methods. */
	
	/**
	 * Creates a complex with the specified values.
	 * 
	 * @param name name
	 * @param abbreviation abbreviation
	 * @param active whether active applies
	 * @param facility facility
	 * @return newly created complex
	 * @throws DuplicateEntityFoundException thrown when a duplicate complex is
	 * found
	 */
	public Complex create(final String name, final String abbreviation,
			final Boolean active, final Facility facility)
		throws DuplicateEntityFoundException {
		if (this.complexDao.find(name, facility) != null) {
			throw new DuplicateEntityFoundException(
					DUPLICATE_COMPLEX_FOUND_MESSAGE);
		}
		Complex complex = this.complexInstanceFactory.createInstance();
		complex.setName(name);
		complex.setAbbreviation(abbreviation);
		complex.setActive(active);
		complex.setFacility(facility);
		return this.complexDao.makePersistent(complex);
	}
	
	/**
	 * Updates the specified complex with the specified name, abbreviation,
	 * and active value.
	 * 
	 * @param name name
	 * @param abbreviation abbreviation
	 * @param active whether active applies
	 * @param complex complex to update
	 * @return updated complex
	 * @throws DuplicateEntityFoundException thrown when a duplicate complex
	 * is found, excluding the specified complex
	 */
	public Complex update(final String name, final String abbreviation,
			final Boolean active, final Complex complex)
		throws DuplicateEntityFoundException {
		if (this.complexDao.findExcluding(name, complex.getFacility(), complex)
				!= null) {
			throw new DuplicateEntityFoundException(
					DUPLICATE_COMPLEX_FOUND_MESSAGE);
		}
		complex.setName(name);
		complex.setAbbreviation(abbreviation);
		complex.setActive(active);
		return this.complexDao.makePersistent(complex);
	}
	
	/**
	 * Removes the specified complex.
	 * 
	 * @param complex complex to remove
	 */
	public void remove(final Complex complex) {
		this.complexDao.makeTransient(complex);
	}
	
	/**
	 * Finds all complexes by the specified facility.
	 * 
	 * @param facility facility
	 * @return list of complexes
	 */
	public List<Complex> findByFacility(final Facility facility) {
		return this.complexDao.findByFacility(facility);
	}
}