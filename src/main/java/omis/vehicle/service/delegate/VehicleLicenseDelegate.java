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

package omis.vehicle.service.delegate;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.instance.factory.InstanceFactory;
import omis.region.domain.State;
import omis.vehicle.dao.VehicleLicenseDao;
import omis.vehicle.domain.VehicleAssociation;
import omis.vehicle.domain.VehicleLicense;

/**
 * Delegate for vehicle license.
 * 
 * @author Yidong Li
 * @version 0.1.0 (Dec 21, 2018)
 * @since OMIS 3.0
 */
public class VehicleLicenseDelegate {
	private final InstanceFactory<VehicleLicense>
	vehicleLicenseInstanceFactory;
	private final AuditComponentRetriever auditComponentRetriever;
	private final VehicleLicenseDao vehicleLicenseDao;
	
	/**
	 * Instantiates a delegate for vehicle license.
	 * 
	 * @param vehicleLicenseInstanceFactory instance factory for vehicle license
	 * @param vehicleLicenseDao vehicle license data access object
	 * @param auditComponentRetriever audit component retriever
	 *
	 */
	public VehicleLicenseDelegate(
		final InstanceFactory<VehicleLicense> vehicleLicenseInstanceFactory,
		final AuditComponentRetriever auditComponentRetriever,
		final VehicleLicenseDao vehicleLicenseDao) {
		this.vehicleLicenseInstanceFactory
		= vehicleLicenseInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
		this.vehicleLicenseDao = vehicleLicenseDao;
	}

	/**
	 * Creates and persists a vehicle license
	 * 
	 * @param vehicleAssociation vehicle association
	 * @param plateNumber plate number
	 * @param state state
	 * @return vehicle owner description
	 */
	public VehicleLicense create(
		final VehicleAssociation vehicleAssociation, 
		final String plateNumber, final State state) {
		VehicleLicense vehicleLicense 
		= this.vehicleLicenseInstanceFactory.createInstance();
		CreationSignature creationSignature = new CreationSignature(
			this.auditComponentRetriever.retrieveUserAccount(), 
			this.auditComponentRetriever.retrieveDate());
		UpdateSignature updateSignature = new UpdateSignature(
			this.auditComponentRetriever.retrieveUserAccount(),
			this.auditComponentRetriever.retrieveDate());
		vehicleLicense.setCreationSignature(creationSignature);
		vehicleLicense.setPlateNumber(plateNumber);
		vehicleLicense.setState(state);
		vehicleLicense.setUpdateSignature(updateSignature);
		vehicleLicense.setVehicleAssociation(vehicleAssociation);
		
		return this.vehicleLicenseDao.makePersistent(vehicleLicense);
	}
	
	/**
	 * Removes a vehicle license
	 * 
	 * @param vehicleLicense vehicle license
	 *
	 */
	public void remove(	final VehicleLicense vehicleLicense) {
		this.vehicleLicenseDao.makeTransient(vehicleLicense);
	}
	
	/**
	 * Finds a vehicle license by vehicle association
	 * 
	 * @param vehicleAssociation vehicle association
	 *
	 */
	public VehicleLicense findLicenseByVehicleAssociation(
		final VehicleAssociation vehicleAssociation){
		return this.vehicleLicenseDao
		.findLicenseByVehicleAssociation(vehicleAssociation);
	}
}