package omis.offenderphoto.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.offender.domain.Offender;
import omis.offenderphoto.domain.OffenderPhotoAssociation;

/**
 * Data access object for offender photo associations.
 * 
 * @author Stephen Abson
 * @author Ryan Johns
 * @version 0.1.1 (Mar 15, 2016)
 * @since OMIS 3.0
 */
public interface OffenderPhotoAssociationDao
		extends GenericDao<OffenderPhotoAssociation> {

	/**
	 * Returns profile photo association for offender.
	 * 
	 * @param offender offender
	 * @return profile association for offender
	 */
	OffenderPhotoAssociation findProfilePhotoAssociation(Offender offender);
	
	/**
	 * Returns photo associations for offender.
	 * 
	 * @param offender offender
	 * @return photo associations for offender
	 */
	List<OffenderPhotoAssociation> findByOffender(Offender offender);
}