package omis.facility.service.delegate;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.facility.dao.BedDao;
import omis.facility.domain.Bed;
import omis.facility.domain.Room;
import omis.instance.factory.InstanceFactory;

/**
 * Bed delegate.
 *
 * @author Josh Divine
 * @version 0.1.0 (Aug 2, 2017)
 * @since OMIS 3.0
 */
public class BedDelegate {

	private final InstanceFactory<Bed> bedInstanceFactory;

	private final BedDao bedDao;
	
	/**
	 * Constructor for bed delegate.
	 * 
	 * @param bedDao bed data access object
	 * @param bedInstanceFactory bed instance factory
	 */
	public BedDelegate(final BedDao bedDao, 
			final InstanceFactory<Bed> bedInstanceFactory) {
		this.bedDao = bedDao;
		this.bedInstanceFactory = bedInstanceFactory;
	}
	
	/**
	 * Returns a list of beds according to a specified room.
	 * @param room room
	 * @return list of beds
	 */
	public List<Bed> findByRoom(final Room room) {
		return this.bedDao.findByRoom(room);
	}
	
	/**
	 * Creates a new bed.
	 * 
	 * @param room room
	 * @param number number
	 * @param active active
	 * @return bed
	 * @throws DuplicateEntityFoundException if entity already exists
	 */
	public Bed create(final Room room, final Integer number, 
			final Boolean active) throws DuplicateEntityFoundException {
		if (this.bedDao.find(room, number) != null) {
			throw new DuplicateEntityFoundException("The bed already exists.");
		}
		Bed bed = this.bedInstanceFactory.createInstance();
		populateBed(bed, room, number, active);
		return this.bedDao.makePersistent(bed);
	}
	
	/**
	 * Updates an existing bed.
	 * 
	 * @param bed bed
	 * @param room room
	 * @param number number
	 * @param active active
	 * @return bed
	 * @throws DuplicateEntityFoundException if entity already exists
	 */
	public Bed update(final Bed bed, final Room room, final Integer number, 
			final Boolean active) throws DuplicateEntityFoundException {
		if (this.bedDao.findExcluding(room, number, bed) != null) {
			throw new DuplicateEntityFoundException("The bed already exists.");
		}
		populateBed(bed, room, number, active);
		return this.bedDao.makePersistent(bed);
	}

	/**
	 * Removes the specified bed.
	 * 
	 * @param bed bed
	 */
	public void remove(final Bed bed) {
		this.bedDao.makeTransient(bed);
	}
	
	// Populates a bed
	private void populateBed(final Bed bed, final Room room, 
			final Integer number, final Boolean active) {
		bed.setActive(active);
		bed.setNumber(number);
		bed.setRoom(room);
	}
}
