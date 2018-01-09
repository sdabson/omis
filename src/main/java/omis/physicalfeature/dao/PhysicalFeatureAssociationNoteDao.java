package omis.physicalfeature.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.physicalfeature.domain.PhysicalFeatureAssociation;
import omis.physicalfeature.domain.PhysicalFeatureAssociationNote;

/**
 * Physical feature association note data access object.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Aug 6, 2015)
 * @since OMIS 3.0
 */
public interface PhysicalFeatureAssociationNoteDao
	extends GenericDao<PhysicalFeatureAssociationNote> {
	
	/**
	 * Returns a list of physical feature association notes for the specified
	 * physical feature association.
	 * 
	 * @param physicalFeatureAssociation physical feature association
	 * @return list of physical feature association notes
	 */
	List<PhysicalFeatureAssociationNote> findNotesByPhysicalFeatureAssociation(
			PhysicalFeatureAssociation physicalFeatureAssociation);
}