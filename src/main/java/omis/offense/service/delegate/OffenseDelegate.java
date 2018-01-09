package omis.offense.service.delegate;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.offense.dao.OffenseDao;
import omis.offense.domain.Offense;
import omis.offense.domain.OffenseClassification;

/**
 * Delegate for offenses.
 *
 * @author Stephen Abson
 * @author Josh Divine
 * @version 0.0.2 (Sept 15, 2017)
 * @since OMIS 3.0
 */
public class OffenseDelegate {

	/* Data access objects. */
	
	private final OffenseDao offenseDao;
	
	/* Instance factories. */
	
	private final InstanceFactory<Offense> offenseInstanceFactory;
	
	/* Constructors. */
	
	/**
	 * Instantiates delegate of offenses.
	 * 
	 * @param offenseDao data access object for offenses
	 */
	public OffenseDelegate(
			final OffenseDao offenseDao,
			final InstanceFactory<Offense> offenseInstanceFactory) {
		this.offenseDao = offenseDao;
		this.offenseInstanceFactory = offenseInstanceFactory;
	}
	
	/* Methods. */
	
	/**
	 * Returns offenses.
	 * 
	 * @return offenses
	 */
	public List<Offense> findAll() {
		return this.offenseDao.findAll();
	}
	
	/**
	 * Creates a new offense.
	 * 
	 * @param name name
	 * @param shortName short name
	 * @param url url
	 * @param classification offense classification
	 * @param violationCode violation code
	 * @param valid valid
	 * @return offense
	 * @throws DuplicateEntityFoundException thrown if entity already exists
	 */
	public Offense create(final String name, final String shortName,
			final String url, final OffenseClassification classification, 
			final String violationCode, final Boolean valid) 
					throws DuplicateEntityFoundException {
		if (this.offenseDao.find(name, shortName, classification, violationCode)
				!= null) {
			throw new DuplicateEntityFoundException("Offense already exists.");
		}
		Offense offense = this.offenseInstanceFactory.createInstance();
		offense.setClassification(classification);
		offense.setName(name);
		offense.setShortName(shortName);
		offense.setUrl(url);
		offense.setViolationCode(violationCode);
		offense.setValid(valid);
		return this.offenseDao.makePersistent(offense);
	}
	
	/**
	 * Updates an existing offense.
	 *
	 * @param offense offense
	 * @param name name
	 * @param shortName short name
	 * @param url url
	 * @param classification offense classification
	 * @param violationCode violation code
	 * @param valid valid
	 * @return offense
	 * @throws DuplicateEntityFoundException thrown if entity already exists
	 */
	public Offense update(final Offense offense, final String name, 
			final String shortName, final String url,
			final OffenseClassification classification, 
			final String violationCode, final Boolean valid) 
					throws DuplicateEntityFoundException {
		if (this.offenseDao.findExcluding(name, shortName, classification, 
				violationCode, offense) != null) {
			throw new DuplicateEntityFoundException("Offense already exists.");
		}
		offense.setClassification(classification);
		offense.setName(name);
		offense.setShortName(shortName);
		offense.setUrl(url);
		offense.setViolationCode(violationCode);
		offense.setValid(valid);
		return this.offenseDao.makePersistent(offense);
	}
	
	/**
	 * Returns the offenses that match the specified query.
	 * 
	 * @param query query
	 * @return offenses
	 * 
	 * @deprecated Use {@code findByQuery(String, int)} as this method has been 
	 * hard code limited to a maximum of 25 results.
	 */
	@Deprecated
	public List<Offense> findByQuery(final String query) {
		return this.offenseDao.findByQuery(query);
	}
	
	/**
	 * Returns the offenses that match the specified query.
	 * 
	 * @param query query
	 * @return offenses
	 */
	public List<Offense> findByQuery(final String query, final int maxResults) {
		return this.offenseDao.findByQuery(query, maxResults);
	}
}