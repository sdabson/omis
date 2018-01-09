package omis.victim.service.delegate;

import java.util.List;

import omis.victim.dao.VictimNoteCategoryDao;
import omis.victim.domain.VictimNoteCategory;

/**
 * Delegate for categories of victim note.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Sep 1, 2015)
 * @since OMIS 3.0
 */
public class VictimNoteCategoryDelegate {

	/* DAOs. */
	
	private final VictimNoteCategoryDao victimNoteCategoryDao;
	
	/* Constructors. */
	
	/**
	 * Instantiates delegate for victim note categories.
	 * 
	 * @param victimNoteCategoryDao data access object for victim notes
	 */
	public VictimNoteCategoryDelegate(
			final VictimNoteCategoryDao victimNoteCategoryDao) {
		this.victimNoteCategoryDao = victimNoteCategoryDao;
	}
	
	/**
	 * Returns victim note categories.
	 * 
	 * @return victim note categories
	 */
	public List<VictimNoteCategory> findAll() {
		return this.victimNoteCategoryDao.findAll();
	}
}