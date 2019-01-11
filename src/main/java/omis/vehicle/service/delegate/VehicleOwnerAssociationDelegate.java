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
import omis.vehicle.dao.VehicleOwnerAssociationDao;
import omis.vehicle.domain.VehicleAssociation;
import omis.vehicle.domain.VehicleOwnerAssociation;

/**
 * Delegate for vehicle owner association.
 * 
 * @author Yidong Li
 * @version 0.1.0 (Dec 21, 2018)
 * @since OMIS 3.0
 */
public class VehicleOwnerAssociationDelegate {
	private final InstanceFactory<VehicleOwnerAssociation>
	vehicleOwnerAssociationInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	private final VehicleOwnerAssociationDao vehicleOwnerAssociationDao;
	
	/**
	 * Instantiates a delegate for vehicle owner association.
	 * 
	 * @param vehicleOwnerAssociationInstanceFactory
	 * instance factory for vehicle owner association
	 * @param vehicleOwnerAssociationDao vehicle owner association data access
	 * object
	 * @param auditComponentRetriever audit component retriever
	 *
	 */
	public VehicleOwnerAssociationDelegate(
		final InstanceFactory<VehicleOwnerAssociation>
		vehicleOwnerAssociationInstanceFactory,
		final AuditComponentRetriever auditComponentRetriever,
		final VehicleOwnerAssociationDao vehicleOwnerAssociationDao) {
		this.vehicleOwnerAssociationInstanceFactory
		= vehicleOwnerAssociationInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
		this.vehicleOwnerAssociationDao = vehicleOwnerAssociationDao;
	}

	/**
	 * Creates and persists a vehicle owner association
	 * 
	 * @param vehicleAssociation vehicle association
	 * @param ownerDescription owner description
	 * @return vehicle owner description
	 */
	public VehicleOwnerAssociation create(
		final VehicleAssociation vehicleAssociation, 
		final String ownerDescription) {
		VehicleOwnerAssociation vehicleOwnerAssociation 
			= this.vehicleOwnerAssociationInstanceFactory.createInstance();
		vehicleOwnerAssociation.setVehicleAssociation(vehicleAssociation);
		vehicleOwnerAssociation.setOwnerDescription(ownerDescription);
		CreationSignature creationSignature = new CreationSignature(
			this.auditComponentRetriever.retrieveUserAccount(), 
			this.auditComponentRetriever.retrieveDate());
		UpdateSignature updateSignature = new UpdateSignature(
			this.auditComponentRetriever.retrieveUserAccount(),
			this.auditComponentRetriever.retrieveDate());
		vehicleOwnerAssociation.setCreationSignature(creationSignature);
		vehicleOwnerAssociation.setUpdateSignature(updateSignature);
		return this.vehicleOwnerAssociationDao
			.makePersistent(vehicleOwnerAssociation);
	}
	
	/**
	 * Removes a vehicle owner association
	 * 
	 * @param vehicleAssociation vehicle association
	 */
	public void remove(	final VehicleOwnerAssociation vehicleOwnerAssociation) {
		this.vehicleOwnerAssociationDao.makeTransient(vehicleOwnerAssociation);
	}
	
	/**
	 * Finds a vehicle owner association by vehicle association
	 * 
	 * @param vehicleAssociation vehicle association
	 */
	public VehicleOwnerAssociation findOwnerByVehicleAssociation(
		final VehicleAssociation vehicleAssociation) {
		return this.vehicleOwnerAssociationDao.findOwnerByVehicleAssociation(
		vehicleAssociation);
	}
}