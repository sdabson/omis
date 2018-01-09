package omis.vehicle.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.datatype.DateRange;
import omis.offender.domain.Offender;

/**
 * Vehicle association.
 * 
 * @author: Ryan Johns
 * @author: Joel Norris
 * @author: Yidong Li
 * @version: 0.1.0 (Jul 22, 2014)
 * @since: OMIS 3.0
 */
public interface VehicleAssociation extends Updatable, Creatable {

	/**
	 * Returns the id.
	 * 
	 * @return id
	 */
	Long getId();
	
	/**
	 * Sets the id
	 * 
	 * @param id id
	 */
	void setId(Long id);
	
	/**
	 * Returns the offender.
	 * 
	 * @return offender
	 */
	Offender getOffender();
	
	/**
	 * Sets the offender.
	 * 
	 * @param offender offender
	 */
	void setOffender(Offender offender);

	/**
	 * Sets the vehicle model.
	 * 
	 * @param VehicleModel vehicle model
	 */
	void setVehicleModel(VehicleModel vehicleModel);

	/**
	 * Gets the vehicle model.
	 * 
	 * @return vehicleModel
	 */
	VehicleModel getVehicleModel();

	/**
	 * Sets the vehicle color.
	 * 
	 * @param VehicleColor vehicle color
	 */
	void setVehicleColor(VehicleColor vehicleColor);

	/**
	 * Gets the vehicle color.
	 * 
	 * @return vehicleColor
	 */
	VehicleColor getVehicleColor();
	
	/**
	 * Returns the date range.
	 * 
	 * @return dateRange
	 */
	DateRange getDateRange();
	
	/**
	 * Sets the date range.
	 * 
	 * @param dateRange date range
	 */
	void setDateRange(DateRange dateRange);
	
	/**
	 * Returns the year.
	 * 
	 * @return year
	 */
	Integer getYear();
	
	/**
	 * Sets the year.
	 * 
	 * @param year year
	 */
	void setYear(Integer year);
	
	/**
	 * Returns the comments.
	 * 
	 * @return comments
	 */
	String getComments();
	
	/**
	 * Sets the comments.
	 * 
	 * @param String comments
	 */
	void setComments(String comments);

	/**
	 * Sets the vehicle make.
	 * 
	 * @param VehicleMake vehicle make
	 */
	void setVehicleMake(VehicleMake vehicleMake);

	/**
	 * Gets the vehicle make.
	 * 
	 * @return vehicleMake
	 */
	VehicleMake getVehicleMake();
	
	/**
	 * Compares {@code this} and {@code obj} for equality.
	 * 
	 * <p>Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * 
	 * @param obj reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code obj} are equal;
	 * {@code false} otherwise
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the comparison is {@code null} 
	 */
	@Override
	boolean equals(Object obj);
	
	/**
	 * Returns a hash code for {@code this}.
	 * 
	 * <p>Any mandatory property of {@code this} may be used in the hash code.
	 * If a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * 
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null}
	 */
	@Override
	int hashCode();

}