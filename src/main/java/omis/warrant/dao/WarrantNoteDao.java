package omis.warrant.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.warrant.domain.Warrant;
import omis.warrant.domain.WarrantNote;

/**
 * WarrantNoteDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 8, 2017)
 *@since OMIS 3.0
 *
 */
public interface WarrantNoteDao extends GenericDao<WarrantNote> {
	
	/**
	 * Finds and returns a WarrantNote with the specified parameters
	 * @param note - String
	 * @param date - Date
	 * @param warrant - Warrant
	 * @return WarrantNote with the specified parameters
	 */
	public WarrantNote find(String note, Date date, Warrant warrant);
	
	/**
	 * Finds and returns a WarrantNote with the specified parameters excluding
	 * specified WarrantNote
	 * @param note - String
	 * @param date - Date
	 * @param warrant - Warrant
	 * @param warrantNoteExcluding - WarrantNote to exclude from search
	 * @return WarrantNote with the specified parameters excluding
	 * specified WarrantNote
	 */
	public WarrantNote findExcluding(String note, Date date, Warrant warrant,
			WarrantNote warrantNoteExcluding);
	
	/**
	 * Returns a list of all WarrantNotes with specified Warrant
	 * @param warrant - Warrant
	 * @return List of all WarrantNotes with specified Warrant
	 */
	public List<WarrantNote> findByWarrant(Warrant warrant);
}
