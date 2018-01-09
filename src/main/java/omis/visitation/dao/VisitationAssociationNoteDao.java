package omis.visitation.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.visitation.domain.VisitationAssociation;
import omis.visitation.domain.VisitationAssociationNote;

/**
 * Data access object for visitation association note.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Feb 10, 2017)
 * @since OMIS 3.0
 */
public interface VisitationAssociationNoteDao
	extends GenericDao<VisitationAssociationNote> {

	/**
	 * Returns all visitation association notes for the specified visitation
	 * association.
	 * 
	 * @param association association
	 * @return list of visitation association notes
	 */
	List<VisitationAssociationNote> findByAssociation(
			VisitationAssociation association);
	
	/**
	 * Returns the visitation association note with the matching specified
	 * values.
	 * 
	 * @param association visitation association
	 * @param date date
	 * @param value value
	 * @return matching visitation association note
	 */
	VisitationAssociationNote find(VisitationAssociation association, Date date,
			String value);
	
	/**
	 * Return the visitation association note with the matching specified
	 * values, excluding the specified visitation association note.
	 * 
	 * @param association visitation association
	 * @param date date
	 * @param value value
	 * @param note visitation association note to exclude
	 * @return matching visitation association note
	 */
	VisitationAssociationNote findExcluding(VisitationAssociation association,
			Date date, String value, VisitationAssociationNote note);
}