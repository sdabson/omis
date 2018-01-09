package omis.military.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.military.domain.MilitaryServiceTerm;
import omis.military.domain.MilitaryServiceTermNote;

/**
 * Military service term note data access object.
 * 
 * @author Joel Norris
 * @version 0.1.0 (May 13, 2015)
 * @since OMIS 3.0
 */
public interface MilitaryServiceTermNoteDao 
extends GenericDao<MilitaryServiceTermNote> {
	
	/**
	 * Returns the matching military service term note.
	 * 
	 * @param serviceTerm military service term
	 * @param note note
	 * @param date date
	 * @return matching military service term note
	 */
	MilitaryServiceTermNote find(MilitaryServiceTerm serviceTerm, 
			String note, Date date);
	
	/**
	 * Returns the military service term with matching properties, except
	 * the specified military service term note.
	 * 
	 * @param serviceTermNote military service term note
	 * @param serviceTerm military service term
	 * @param note note
	 * @param date date
	 * @return matching military service term note
	 */
	MilitaryServiceTermNote findExcluding(
			MilitaryServiceTermNote serviceTermNote,
			MilitaryServiceTerm serviceTerm,
			String note, Date date);

	/**
	 * Returns a list of military service term notes for the specified military
	 * service term.
	 * 
	 * @param serviceTerm military service term
	 * @return list of military service term notes
	 */
	List<MilitaryServiceTermNote> findByServiceTerm(
			MilitaryServiceTerm serviceTerm);
}