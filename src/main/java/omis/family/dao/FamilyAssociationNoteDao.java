package omis.family.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.family.domain.FamilyAssociation;
import omis.family.domain.FamilyAssociationNote;

/**
 * Data access object for family association note.
 * 
 * @author Yidong Li
 * @version 0.1.0 (June 3, 2015)
 * @since OMIS 3.0
 */
public interface FamilyAssociationNoteDao
	extends GenericDao<FamilyAssociationNote> {

	/**
	 * Returns the existing family association note.
	 *  
	 * @param familyAssociation family association
	 * @param date date
	 * 
	 * @return family association note
	 */
	FamilyAssociationNote find(FamilyAssociation familyAssociation, 
		Date date);
	
	/**
	 * Returns the existing family association note excluding the passed one.
	 *  
	 * @param familyAssociation family association
	 * @param date date
	 * @param note family association note
	 * 
	 * @return family association note
	 */
	FamilyAssociationNote findExcluding(FamilyAssociation familyAssociation, 
		Date date, FamilyAssociationNote note);
	/**
	 * Returns the existing family association note.
	 *  
	 * @param familyAssociation family association
	 * 
	 * @return a list of family association notes
	 */
	List<FamilyAssociationNote> findByAssociation(
		FamilyAssociation familyAssociation);
	
	/**
	 * Returns all existing family association notes.
	 * 
	 * @return a list of family association notes
	 */
	List<FamilyAssociationNote> findAll();
}