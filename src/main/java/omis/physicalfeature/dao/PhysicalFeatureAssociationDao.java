package omis.physicalfeature.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.offender.domain.Offender;
import omis.physicalfeature.domain.PhysicalFeature;
import omis.physicalfeature.domain.PhysicalFeatureAssociation;

/**
 * Data access object for offender physical feature.
 * @author Joel Norris
 * @version 0.1.0 (Nov 15, 2013)
 * @since OMIS 3.0
 */
public interface PhysicalFeatureAssociationDao 
	extends GenericDao<PhysicalFeatureAssociation> {

	/**
	 * Returns a list of all physical features associations with
	 * the specified offender.
	 * 
	 * @param offender offender
	 * @return list of physical feature associations
	 */
	List<PhysicalFeatureAssociation> findPhysicalFeatureAssociationsByOffender(
			Offender offender);

	/**
	 * Returns the physical feature association with the specified natural key
	 * properties. If no physical feature association is found, return
	 * {@code null}.
	 * 
	 * @param description description
	 * @param feature physical feature
	 * @param offender offender
	 * @return physical feature association
	 */
	PhysicalFeatureAssociation find(String description, PhysicalFeature feature,
			Offender offender);

	/**
	 * Returns the physical feature association with the specified natural key
	 * properties excluding the specified physical feature association. If no
	 * physical feature association is found, return {@code null}.
	 * 
	 * @param description description
	 * @param feature physical feature
	 * @param offender offender
	 * @param physicalFeatureAssociation physical feature association
	 * @return physical feature association
	 */
	PhysicalFeatureAssociation findExcluding(String description, 
			PhysicalFeature feature, Offender offender,
			PhysicalFeatureAssociation physicalFeatureAssociation);
}