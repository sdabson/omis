package omis.vehicle.domain.impl;

import omis.vehicle.domain.VehicleMake;
import omis.vehicle.domain.VehicleModel;

/**
 * Implementation of vehicle model.
 * @author Ryan JOhns
 * @author Yidong Li
 * @version 0.1.0 (July 22, 2014)
 * @since OMIS 3.0 
 */
public class VehicleModelImpl implements VehicleModel {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private VehicleMake vehicleMake;
	private Boolean valid;
	
	/** Constructor. */
	public VehicleModelImpl() {
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
	public String getName() {
		return this.name;
	}

	/** {@inheritDoc} */
	@Override
	public void setName(final String name) {
		this.name = name;
	}

	/** {@inheritDoc} */
	@Override
	public VehicleMake getVehicleMake() {
		return this.vehicleMake;
	}

	/** {@inheritDoc} */
	@Override
	public void setVehicleMake(final VehicleMake vehicleMake) {
		this.vehicleMake = vehicleMake;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getValid() {
		return this.valid;
	}

	/** {@inheritDoc} */
	@Override
	public void setValid(final Boolean valid) {
		this.valid = valid;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(Object obj) {
		
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof VehicleModel)) {
			return false;
		}
		
		VehicleModel that = (VehicleModel) obj;
		
		if (this.getName() == null) {
			throw new IllegalStateException("Name required.");
		}
		if (this.getVehicleMake() == null) {
			throw new IllegalStateException("Vehicle make required.");
		}
		if ((this.getName().equals(that.getName()))&&(this.getVehicleMake().equals(that.getVehicleMake()))) {
			return true;
		}
		return false;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getName() == null) {
			throw new IllegalStateException("Name required.");
		}
		if (this.getVehicleMake() == null) {
			throw new IllegalStateException("Vehicle make required.");
		}
		
		int hashCode = 14;
		
		hashCode += 29 * this.getName().hashCode();
		hashCode += 29 * this.getVehicleMake().hashCode();
		
		return hashCode;
	}
}