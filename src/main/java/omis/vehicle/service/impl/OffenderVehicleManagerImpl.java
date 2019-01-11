/*
 * OMIS - Offender Management Information System
 * Copyright (C) 2011 - 2017 State of Montana
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package omis.vehicle.service.impl;

import java.util.List;

import omis.datatype.DateRange;
import omis.offender.domain.Offender;
import omis.region.domain.State;
import omis.region.service.delegate.StateDelegate;
import omis.vehicle.dao.VehicleAssociationDao;
import omis.vehicle.dao.VehicleOwnerAssociationDao;
import omis.vehicle.domain.VehicleAssociation;
import omis.vehicle.domain.VehicleColor;
import omis.vehicle.domain.VehicleLicense;
import omis.vehicle.domain.VehicleMake;
import omis.vehicle.domain.VehicleModel;
import omis.vehicle.domain.VehicleOwnerAssociation;
import omis.vehicle.exception.VehicleAssociationExistsException;
import omis.vehicle.service.OffenderVehicleManager;
import omis.vehicle.service.delegate.VehicleAssociationDelegate;
import omis.vehicle.service.delegate.VehicleColorDelegate;
import omis.vehicle.service.delegate.VehicleLicenseDelegate;
import omis.vehicle.service.delegate.VehicleMakeDelegate;
import omis.vehicle.service.delegate.VehicleModelDelegate;
import omis.vehicle.service.delegate.VehicleOwnerAssociationDelegate;

/**
 * Implementation of Vehicle association manager service.
 * 
 * @author: Yidong Li
 * @version: 0.1.0 (Aug 1, 2014)
 * @since: OMIS 3.0
 */

public class OffenderVehicleManagerImpl implements OffenderVehicleManager {
	/**
	 * Instantiates an instance of vehicle association manager service 
	 * implementation.
	 * @param vehicleAssociationDao vehicle association data access object
	 * @param vehicleOwnerAssociationDao vehicle owner association data access 
	 * 	object
	 * @param vehicleOwnerAssociationDelegate vehicle owner association delegate
	 * @param vehicleLicenseDelegate vehicle license delegate
	 * @param vehicleAssociationDelegate vehicle association delegate
	 * @param vehicleColorDelegate vehicle color delegate
	 * @param vehicleMakeDelegate vehicle make delegate
	 * @param stateDelegate state delegate
	 */
	private final VehicleOwnerAssociationDao vehicleOwnerAssociationDao;
	private final VehicleOwnerAssociationDelegate vehicleOwnerAssociationDelegate;
	private final VehicleLicenseDelegate vehicleLicenseDelegate;
	private final VehicleAssociationDelegate vehicleAssociationDelegate;
	private final VehicleColorDelegate vehicleColorDelegate;
	private final VehicleModelDelegate vehicleModelDelegate;
	private final VehicleMakeDelegate vehicleMakeDelegate;
	private final StateDelegate stateDelegate;

	public OffenderVehicleManagerImpl(
		final VehicleAssociationDao vehicleAssociationDao,
		final VehicleOwnerAssociationDao vehicleOwnerAssociationDao,
		final VehicleOwnerAssociationDelegate vehicleOwnerAssociationDelegate,
		final VehicleLicenseDelegate vehicleLicenseDelegate,
		final VehicleAssociationDelegate vehicleAssociationDelegate,
		final VehicleColorDelegate vehicleColorDelegate,
		final VehicleModelDelegate vehicleModelDelegate,
		final VehicleMakeDelegate vehicleMakeDelegate,
		final StateDelegate stateDelegate) {
		this.vehicleOwnerAssociationDao = vehicleOwnerAssociationDao;
		this.vehicleLicenseDelegate = vehicleLicenseDelegate;
		this.vehicleAssociationDelegate = vehicleAssociationDelegate;
		this.vehicleOwnerAssociationDelegate = vehicleOwnerAssociationDelegate;
		this.vehicleColorDelegate = vehicleColorDelegate;
		this.vehicleModelDelegate = vehicleModelDelegate;
		this.vehicleMakeDelegate = vehicleMakeDelegate;
		this.stateDelegate = stateDelegate;
	}	

	/** {@inheritDoc} */
	@Override
	public VehicleAssociation associateVehicle(final Offender offender, 
		final VehicleModel vehicleModel, final VehicleColor vehicleColor, 
		final Integer year, final String ownerDescription,final DateRange dateRange, 
		final String comments, final String plateNumber, final State state,
		final VehicleMake vehicleMake)	
		throws VehicleAssociationExistsException {
		return this.vehicleAssociationDelegate.create(offender, vehicleModel,
			vehicleColor, year, ownerDescription, dateRange, comments,
			plateNumber, state, vehicleMake);
	}	
	
	/** {@inheritDoc} */
	@Override
	public VehicleAssociation updateVehicle(final VehicleAssociation 
		vehicleAssociation, final VehicleModel vehicleModel, final VehicleColor 
		vehicleColor, final Integer year, final DateRange dateRange, 
		final String comments, final VehicleMake vehicleMake, final State state, 
		final String plateNumber, final String ownerDescription,
		final Offender offender) throws VehicleAssociationExistsException {
		return this.vehicleAssociationDelegate.update(vehicleAssociation,
			vehicleModel, vehicleColor, year, dateRange, comments, vehicleMake,
			state, plateNumber, ownerDescription, offender);
	}
	
	/** {@inheritDoc} */
	@Override	
	public VehicleOwnerAssociation assignOwner(final VehicleAssociation 
		vehicleAssociation, final String ownerDescription){
		return this.vehicleOwnerAssociationDelegate.create(vehicleAssociation,
		ownerDescription);
	}
	
	/** {@inheritDoc} */
	@Override
	public VehicleLicense assignLicense(final VehicleAssociation 
		vehicleAssociation, final String plateNumber, final State state){
		return this.vehicleLicenseDelegate.create(vehicleAssociation,
		plateNumber, state);
	}
	
	
	/** {@inheritDoc} */
	@Override	
	public VehicleOwnerAssociation findOwnerByVehicleAssociation(
		final VehicleAssociation vehicleAssociation){
		return this.vehicleOwnerAssociationDao
			.findOwnerByVehicleAssociation(vehicleAssociation);
	}
	
	/** {@inheritDoc} */
	@Override	
	public VehicleLicense findLicenseByVehicleAssociation(
		final VehicleAssociation vehicleAssociation){
		return this.vehicleLicenseDelegate.findLicenseByVehicleAssociation(
			vehicleAssociation);
	}
	
	/** {@inheritDoc} */
	@Override
	public void removeLicense(final VehicleLicense vehicleLicense){
		this.vehicleLicenseDelegate.remove(vehicleLicense);
	}
	
	/** {@inheritDoc} */
	@Override
	public void remove(final VehicleAssociation vehicleAssociation){
		this.vehicleAssociationDelegate.remove(vehicleAssociation);
	}
	
	/** {@inheritDoc} */
	@Override
	public void removeOwner(final VehicleOwnerAssociation 
		vehicleOwnerAssociation){
		this.vehicleOwnerAssociationDelegate.remove(vehicleOwnerAssociation);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<VehicleColor> findColors(){
		return this.vehicleColorDelegate.findColors();
	}
	
	/** {@inheritDoc} */
	@Override
	public List<VehicleMake> findVehicleMake(){
		return this.vehicleMakeDelegate.findVehicleMakes();
	}
	
	/** {@inheritDoc} */
	@Override
	public List<VehicleModel> findVehicleModelsByMake(final VehicleMake 
		vehicleMake){
		return this.vehicleModelDelegate.findVehicleMoldelByMake(vehicleMake);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<State> findStates( ) {
		return this.stateDelegate.findAll();
	}
}

