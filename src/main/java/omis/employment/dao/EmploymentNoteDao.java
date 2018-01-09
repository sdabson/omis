package omis.employment.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.employment.domain.EmploymentTerm;
import omis.employment.domain.EmploymentNote;

/**
 * Data access object for employment note.
 * 
 * @author Yidong Li
 * @author Josh Divine
 * @version 0.1.1 (Dec 14, 2017)
 * @since OMIS 3.0
 */
public interface EmploymentNoteDao
	extends GenericDao<EmploymentNote> {

	/**
	 * Find employment note.
	 * @param term employment term
	 * @return list of existing matched employment notes
	 */
	List<EmploymentNote> findNotes(EmploymentTerm term);

	/**
	 * Finds employment note matching the specified term, date and value.
	 * 
	 * @param term term
	 * @param date date
	 * @param value value
	 * @return employment note
	 */
	EmploymentNote find(EmploymentTerm term, Date date, String value);
	
	/**
	 * Finds employment note matching the specified term, date,  and value 
	 * excluding the specified note.
	 * 
	 * @param term term
	 * @param date date
	 * @param value value
	 * @param excludedNote excluded employment note
	 * @return employment note
	 */
	EmploymentNote findExcluding(EmploymentTerm term, Date date, String value, 
			EmploymentNote excludedNote);
}