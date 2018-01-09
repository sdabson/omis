package omis.courtcase.dao;

import java.util.Date;
import java.util.List;

import omis.courtcase.domain.CourtCase;
import omis.courtcase.domain.CourtCaseNote;
import omis.dao.GenericDao;

public interface CourtCaseNoteDao extends GenericDao<CourtCaseNote> {

	/**
	 * Returns all notes for the specified court case.
	 * 
	 * @param courtCase court case
	 * @return list of court case notes
	 */
	List<CourtCaseNote> findByCourtCase(CourtCase courtCase);
	
	/**
	 * Returns the court case note matching the specified court case, date and 
	 * value.
	 * 
	 * @param courtCase court case
	 * @param date date
	 * @param value value
	 * @return court case note
	 */
	CourtCaseNote findCourtCaseNote(CourtCase courtCase, Date date, 
			String value);
	
	/**
	 * Returns the court case note matching the specified court case, date and 
	 * value, excluding the specified note.
	 * 
	 * @param courtCase court case
	 * @param date date
	 * @param value value
	 * @param excludedNote excluded court case note
	 * @return court case note
	 */
	CourtCaseNote findCourtCaseNoteExcluding(CourtCase courtCase, Date date, 
			String value, CourtCaseNote excludedNote);
}
