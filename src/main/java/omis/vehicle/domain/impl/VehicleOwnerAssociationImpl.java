package omis.vehicle.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.vehicle.domain.VehicleAssociation;
import omis.vehicle.domain.VehicleOwnerAssociation;

/** Implements vehicle owner association.
 * @author Yidong Li
 * @author Ryan Johns
 * @version 0.1.1 (Jun 1, 2016)
 * @since OMIS 3.0 */

public class VehicleOwnerAssociationImpl implements VehicleOwnerAssociation {
	private static final long serialVersionUID = 1L;
	private static final String OWNER_DESCRIPTION_REQUIRED_MSG 
		= "Owner description required";
	private static final String VEHICLE_ASSOCIATION_REQUIRED_MSG
		= "Vehicle association required";
	
	private Long id;
	private String ownerDescription;
	private VehicleAssociation vehicleAssociation;
	private UpdateSignature updateSignature;
	private CreationSignature creationSignature;

	/** Constructor. */
	public VehicleOwnerAssociationImpl() {
	}
	
	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}
	
	/** {@inheritDoc} */
	@Override
	public String getOwnerDescription() {
		return this.ownerDescription;
	}

	/** {@inheritDoc} */
	@Override
	public void setOwnerDescription(String ownerDescription) {
		this.ownerDescription = ownerDescription;		
	}
	
	/** {@inheritDoc} */
	@Override
	public VehicleAssociation getVehicleAssociation() {
		return this.vehicleAssociation;
	}

	/** {@inheritDoc} */
	@Override
	public void setVehicleAssociation(
		final VehicleAssociation vehicleAssociation) {
		this.vehicleAssociation = vehicleAssociation;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setUpdateSignature(final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(
			final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}	

	/** {@inheritDoc} */
	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if (this == obj) {
			result = true;
		} else if (obj instanceof VehicleOwnerAssociation) {
			VehicleOwnerAssociation that = (VehicleOwnerAssociation) obj;
			if (this.getOwnerDescription().equals(that.getOwnerDescription())
					&& this.getVehicleAssociation().equals(
							that.getVehicleAssociation())) {
			result = true;
			}	
		}
		return result;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		this.checkState();
		
		int hashCode = 14;
		
		hashCode += 29 * this.getOwnerDescription().hashCode();
		hashCode += 29 * this.getVehicleAssociation().hashCode();
		return hashCode;
	}
	
	/* Checks state. */
	public void checkState() {
		if (this.getOwnerDescription() == null) {
			throw new IllegalStateException(OWNER_DESCRIPTION_REQUIRED_MSG);
		}
		if (this.getVehicleAssociation() == null) {
			throw new IllegalStateException(VEHICLE_ASSOCIATION_REQUIRED_MSG);
		}
	}
}
