package omis.vehicle.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.datatype.DateRange;
import omis.offender.domain.Offender;
import omis.vehicle.domain.VehicleAssociation;
import omis.vehicle.domain.VehicleColor;
import omis.vehicle.domain.VehicleMake;
import omis.vehicle.domain.VehicleModel;

/** Implementation of vehicle association.
 * @author Ryan Johns
 * @author Yidong Li
 * @version 0.1.0 (July 22, 2014)
 * @since OMIS 3.0 */
public class VehicleAssociationImpl implements VehicleAssociation {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Offender offender;
	private DateRange dateRange;
	private Integer year;
	private String comments;
	private VehicleModel vehicleModel;
	private VehicleMake vehicleMake;
	private VehicleColor vehicleColor;
	private UpdateSignature updateSignature;
	private CreationSignature creationSignature;
	
	/** Constructor. */
	public VehicleAssociationImpl() {
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
	public Offender getOffender() {
		return this.offender;
	}

	/** {@inheritDoc} */
	@Override
	public void setOffender(final Offender offender) {
		this.offender = offender;
	}

	/** {@inheritDoc} */
	@Override
	public DateRange getDateRange() {
		return this.dateRange;
	}

	/** {@inheritDoc} */
	@Override
	public void setDateRange(final DateRange dateRange) {
		this.dateRange = dateRange;
	}

	/** {@inheritDoc} */
	@Override
	public Integer getYear() {
		return this.year;
	}

	/** {@inheritDoc} */
	@Override
	public void setYear(final Integer year) {
		this.year = year;
	}

	/** {@inheritDoc} */
	@Override
	public String getComments() {
		return this.comments;
	}

	/** {@inheritDoc} */
	@Override
	public void setComments(final String comments) {
		this.comments = comments;
	}

	/** {@inheritDoc} */
	@Override
	public void setVehicleModel(final VehicleModel vehicleModel) {
		this.vehicleModel = vehicleModel;
	}

	/** {@inheritDoc} */
	@Override
	public VehicleModel getVehicleModel() {
		return this.vehicleModel;
	}

	/** {@inheritDoc} */
	@Override
	public void setVehicleColor(final VehicleColor vehicleColor) {
		this.vehicleColor = vehicleColor;
	}

	/** {@inheritDoc} */
	@Override
	public VehicleColor getVehicleColor() {
		return this.vehicleColor;
	}

	/** {@inheritDoc} */
	@Override
	public void setVehicleMake(final VehicleMake vehicleMake) {
		this.vehicleMake = vehicleMake;
	}

	/** {@inheritDoc} */
	@Override
	public VehicleMake getVehicleMake() {
		return this.vehicleMake;
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
		if (!(obj instanceof VehicleAssociation)) {
			return false;
		}
		VehicleAssociation that = (VehicleAssociation) obj;
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required");
		}
		if (!this.getOffender().equals(that.getOffender())) {
			return false;
		}
		if (this.getDateRange() == null) {
			throw new IllegalStateException("Daterange required");
		}		
		if(!this.getDateRange().equals(that.getDateRange())){
			return false;
		}
		if (this.getVehicleModel() != null) {
			if (!this.getVehicleModel().equals(that.getVehicleModel())) {
				return false;
			}
		} else if (that.getVehicleModel() != null) {
			return false;
		}
		if (this.getVehicleMake() != null) {
			if (!this.getVehicleMake().equals(that.getVehicleMake())) {
				return false;
			}
		} else if (that.getVehicleMake() != null) {
			return false;
		}
		if (this.getYear() != null) {
			if (!this.getYear().equals(that.getYear())) {
				return false;
			}
		} else if (that.getYear() != null) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		int hashCode = 7;
		
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required");
		}
		if (this.getDateRange() == null) {
			throw new IllegalStateException("Daterange required");
		}
		hashCode += 29 * this.getOffender().hashCode();
		hashCode += 31 * this.getDateRange().hashCode();
		return hashCode;
	}
}
