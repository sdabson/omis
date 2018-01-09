package omis.condition.dao;

import java.util.Date;
import java.util.List;

import omis.condition.domain.Agreement;
import omis.condition.domain.AgreementNote;
import omis.dao.GenericDao;

/**
 * Agreement Note Dao.
 * 
 * @author Jonny Santy
 * @author Trevor Isles
 * @author Annie Jacques
 * @version 0.1.1 (May 21, 2017)
 * @since OMIS 3.0
 */
public interface AgreementNoteDao extends GenericDao<AgreementNote>{

	/**
	 * Returns an AgreementNote with the specified properties
	 * @param date - Date
	 * @param description - String
	 * @param agreement - Agreement
	 * @return AgreementNote with the specified properties
	 */
	AgreementNote find(Date date, String description, Agreement agreement);
	
	/**
	 * Returns an AgreementNote with the specified properties excluding the
	 * specified AgreementNote
	 * @param agreementNote - AgreementNote to exclude from search
	 * @param date - Date
	 * @param description - String
	 * @param agreement - Agreement
	 * @return AgreementNote with the specified properties excluding the
	 * specified AgreementNote
	 */
	AgreementNote findExcluding(AgreementNote agreementNote, Date date, 
			String description, Agreement agreement);
	
	/**
	 * Returns a List of AgreementNotes with the specified Agreement
	 * @param agreement - Agreement
	 * @return List of AgreementNotes with the specified Agreement
	 */
	List<AgreementNote> findByAgreement(Agreement agreement);
	
	
}
