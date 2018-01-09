package omis.court.service.delegate;

import java.util.List;

import omis.court.dao.CourtDao;
import omis.court.domain.Court;
import omis.court.domain.CourtCategory;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.location.domain.Location;

/** Service delegate for court.
 * @author Ryan Johns
 * @version 0.1.0 (Dec 29, 2015)
 * @since OMIS 3.0 */
public class CourtDelegate {
	
	/* Data access objects. */
	private final CourtDao courtDao;
	
	/* Instance factories. */
	private final InstanceFactory<Court> courtInstanceFactory;
	
	/** Constructor.
	 * @param courtDao - court dao. */
	public CourtDelegate(final CourtDao courtDao,
			final InstanceFactory<Court> courtInstanceFactory) {
		this.courtDao = courtDao;
		this.courtInstanceFactory = courtInstanceFactory;
	}
	
	/** Find all Courts.
	 * @return list of courts. */
	public List<Court> findAllCourts() {
		return this.courtDao.findAll();
	}
	
	/**
	 * Creates a new court.
	 * 
	 * @param name name
	 * @param category court category
	 * @param location location
	 * @return court
	 * @throws DuplicateEntityFoundException thrown when entity already exists
	 */
	public Court create(final String name, final CourtCategory category, 
			final Location location) throws DuplicateEntityFoundException {
		if (this.courtDao.find(name, category, location) != null) {
			throw new DuplicateEntityFoundException("Court already exists.");
		}
		Court court = this.courtInstanceFactory.createInstance();
		court.setCategory(category);
		court.setLocation(location);
		court.setName(name);
		return this.courtDao.makePersistent(court);
	}
	
	/**
	 * Creates a new court.
	 * 
	 * @param name name
	 * @param category court category
	 * @param location location
	 * @return court
	 * @throws DuplicateEntityFoundException thrown when entity already exists
	 */
	public Court update(final Court court, final String name, 
			final CourtCategory category, final Location location) 
					throws DuplicateEntityFoundException {
		if (this.courtDao.findExcluding(name, category, location, 
				court) != null) {
			throw new DuplicateEntityFoundException("Court already exists.");
		}
		court.setCategory(category);
		court.setLocation(location);
		court.setName(name);
		return this.courtDao.makePersistent(court);
	}
}
