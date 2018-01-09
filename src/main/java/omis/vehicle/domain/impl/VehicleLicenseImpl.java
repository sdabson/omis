package omis.vehicle.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.region.domain.State;
import omis.vehicle.domain.VehicleLicense;
import omis.vehicle.domain.VehicleAssociation;

/** Implements vehicle owner association.
 * @author Yidong Li
 * @version 0.1.0 (July 24, 2014)
 * @since OMIS 3.0 */

public class VehicleLicenseImpl implements VehicleLicense {
	private static final long serialVersionUID = 1L;
	private Long id;
	private State state;
	private String plateNumber;
	private VehicleAssociation vehicleAssociation;
	private UpdateSignature updateSignature;
	private CreationSignature creationSignature;

	/** Constructor. */
	public VehicleLicenseImpl() {
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
	public State getState() {
		return this.state;
	}

	/** {@inheritDoc} */
	@Override
	public void setState(final State state) {
		this.state = state;		
	}
	
	/** {@inheritDoc} */
	@Override
	public String getPlateNumber() {
		return this.plateNumber;
	}

	/** {@inheritDoc} */
	@Override
	public void setPlateNumber(final String plateNumber) {
		this.plateNumber = plateNumber;		
	}
	
	/** {@inheritDoc} */
	@Override
	public VehicleAssociation getVehicleAssociation() {
		return this.vehicleAssociation;
	}

	/** {@inheritDoc} */
	@Override
	public void setVehicleAssociation(final VehicleAssociation 
		vehicleAssociation) {
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
		
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof VehicleLicense)) {
			return false;
		}
		
		VehicleLicense that = (VehicleLicense) obj;
		
		if (this.getState() == null) {
			throw new IllegalStateException("State required.");
		}
		if(!this.getState().equals(that.getState())) {
			return false;
		}
		if (this.getVehicleAssociation() == null) {
			throw new IllegalStateException("Vehicle association required.");
		}
		if(!this.getVehicleAssociation().equals(that.getVehicleAssociation())) {
			return false;
		}
		if (this.getPlateNumber() != null) {
			if (!this.getPlateNumber().equals(that.getPlateNumber())) {
				return false;
			}
		} else if (that.getPlateNumber() != null) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getState() == null) {
			throw new IllegalStateException("Name required.");
		}
		if (this.getVehicleAssociation() == null) {
			throw new IllegalStateException("Vehicle association required.");
		}		
		int hashCode = 14;
		
		hashCode += 29 * this.getState().hashCode();
		
		return hashCode;
	}
}
