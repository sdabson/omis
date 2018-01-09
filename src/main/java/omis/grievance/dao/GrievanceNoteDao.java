package omis.grievance.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.grievance.domain.Grievance;
import omis.grievance.domain.GrievanceNote;

/**
 * Data access object for grievance notes.
 *
 * @author Stephen Abson
 * @version 0.0.1 (May 8, 2015)
 * @since OMIS 3.0
 */
public interface GrievanceNoteDao
		extends GenericDao<GrievanceNote> {

	/**
	 * Finds notes for grievance.
	 * 
	 * @param grievance grievance for which to return notes
	 * @return notes for grievance
	 */
	List<GrievanceNote> findByGrievance(Grievance grievance);
	
	/**
	 * Finds grievance note.
	 * 
	 * @param grievance grievance
	 * @param date date
	 * @param value value
	 * @return grievance note
	 */
	GrievanceNote find(Grievance grievance, Date date, String value);
	
	/**
	 * Finds grievance note excluding specified notes.
	 * 
	 * @param grievance grievance
	 * @param date date
	 * @param value value
	 * @param excludedNotes notes to exclude
	 * @return grievance note
	 */
	GrievanceNote findExcluding(Grievance grievance, Date date, String value,
			GrievanceNote... excludedNotes);
}