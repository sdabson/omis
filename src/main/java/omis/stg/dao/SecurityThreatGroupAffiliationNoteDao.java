package omis.stg.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.stg.domain.SecurityThreatGroupAffiliation;
import omis.stg.domain.SecurityThreatGroupAffiliationNote;

/**
 * Security threat group affiliation note.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (October 20, 2016)
 * @since OMIS 3.0
 */

public interface SecurityThreatGroupAffiliationNoteDao
	extends GenericDao<SecurityThreatGroupAffiliationNote> {

	
	/**
	 * Finds a security threat group affiliation note.
	 * @param date - date
	 * @param note - note
	 * @return matching security threat group note.
	 */
	List <SecurityThreatGroupAffiliationNote> 
		findNotes(SecurityThreatGroupAffiliation affiliation);

	SecurityThreatGroupAffiliationNote findExcluding(
			SecurityThreatGroupAffiliationNote affiliationNote,	
			SecurityThreatGroupAffiliation affiliation,
			Date date, String note);
	
	SecurityThreatGroupAffiliationNote findAffiliationNote(
			SecurityThreatGroupAffiliation affiliation,
			Date date, String note);
}
