package omis.citation.service.delegate;

import java.util.List;

import omis.citation.dao.MisdemeanorOffenseDao;
import omis.citation.domain.MisdemeanorOffense;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;

/**
 * Misdemeanor offense delegate.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (Aug 5, 2016)
 * @since OMIS 3.0
 */

public class MisdemeanorOffenseDelegate {

	/* Instance factories. */
	private final InstanceFactory<MisdemeanorOffense> 
		misdemeanorOffenseInstanceFactory;

	/* DAOs. */
	private MisdemeanorOffenseDao misdemeanorOffenseDao;

	/* Constructor. */
	
	/**
	 * Instantiates delegate for offenses.
	 * 
	 * @param misdemeanorOffenseInstanceFactory instance factory for
	 * misdemeanor citations.
	 * @param misdemeanorOffenseDao data access object for citations.
	 */
	public MisdemeanorOffenseDelegate (
			final MisdemeanorOffenseDao misdemeanorOffenseDao,
			final InstanceFactory <MisdemeanorOffense>
				misdemeanorOffenseInstanceFactory){
		this.misdemeanorOffenseDao = misdemeanorOffenseDao;
		this.misdemeanorOffenseInstanceFactory = 
				misdemeanorOffenseInstanceFactory;
	}

	/** Creates a misdemeanor offense.
	 * @param Name - name
	 * @param Valid - valid
	 * @param  - .
	 * @return misdemeanor offense. 
	 * @throws DuplicateEntityFoundException - when offense already exists. */
	public MisdemeanorOffense create(
			final String name,
			final Boolean valid) 
				throws DuplicateEntityFoundException {
					if (this.misdemeanorOffenseDao.find(name) 
						!= null) {
					throw new DuplicateEntityFoundException(
						"Duplicate misdemeanor offense found");
		}
		
			MisdemeanorOffense offense = this.misdemeanorOffenseInstanceFactory
					.createInstance(); offense.setName(name); 
					offense.setValid(valid);
				return this.misdemeanorOffenseDao.makePersistent(offense);
		}
	
	public List<MisdemeanorOffense> findAll() {
		return this.misdemeanorOffenseDao.findAll();
	}
	
	/**
	 * Returns offense.
	 * 
	 * <p>If misdemeanor offense does not exist, creates and returns a new
	 * misdemeanor offense.
	 * 
	 * @param name name of misdemeanor offense
	 * @return misdemeanor offense
	 */
	public MisdemeanorOffense findOrCreateNewOffense(
			final String name, final Boolean valid) {
		MisdemeanorOffense newOffense = this.misdemeanorOffenseDao.find(name);
		if (newOffense != null) {
			return newOffense;
		} else {
			return this.createOffense(name, valid);
		}
	}
	
	// Creates misdemeanor offense
	private MisdemeanorOffense createOffense(final String name, final Boolean valid) {
		MisdemeanorOffense newOffense = this.misdemeanorOffenseInstanceFactory.createInstance();
		newOffense.setName(name);
		newOffense.setValid(valid);
		return this.misdemeanorOffenseDao.makePersistent(newOffense);
	}
}
