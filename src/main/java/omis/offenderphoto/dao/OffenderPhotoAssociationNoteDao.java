package omis.offenderphoto.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.offenderphoto.domain.OffenderPhotoAssociation;
import omis.offenderphoto.domain.OffenderPhotoAssociationNote;

/**
 * Offender photo association note data access object.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Dec 15, 2016)
 * @since OMIS 3.0
 */
public interface OffenderPhotoAssociationNoteDao 
	extends GenericDao<OffenderPhotoAssociationNote> {

	/**
	 * Returns offender photo association notes for the specified offender
	 * photo association.
	 * 
	 * @param association offender photo association
	 * @return list of offender photo association notes
	 */
	List<OffenderPhotoAssociationNote> findByAssociation(
			OffenderPhotoAssociation association);

	/**
	 * Returns the offender photo association note with the specified value,
	 * date and offender photo association.
	 * 
	 * @param value value
	 * @param date date
	 * @param association offender photo association
	 * @return offender photo association note
	 */
	OffenderPhotoAssociationNote find(String value, Date date,
			OffenderPhotoAssociation association);

	/**
	 * Returns the offender photo association note with the specified value and
	 * date, excluding the specified offender photo association note.
	 * 
	 * @param value value
	 * @param date date
	 * @param association offender photo association
	 * @param note offender photo association note
	 * @return offender photo association note
	 */
	OffenderPhotoAssociationNote findExcluding(String value, Date date,
			OffenderPhotoAssociation association,
			OffenderPhotoAssociationNote note);
}