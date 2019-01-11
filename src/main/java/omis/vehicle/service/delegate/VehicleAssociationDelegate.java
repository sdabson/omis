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
import omis.datatype.DateRange;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;
import omis.region.domain.State;
import omis.vehicle.dao.VehicleAssociationDao;
import omis.vehicle.dao.VehicleLicenseDao;
import omis.vehicle.dao.VehicleOwnerAssociationDao;
import omis.vehicle.domain.VehicleAssociation;
import omis.vehicle.domain.VehicleColor;
import omis.vehicle.domain.VehicleLicense;
import omis.vehicle.domain.VehicleMake;
import omis.vehicle.domain.VehicleModel;
import omis.vehicle.domain.VehicleOwnerAssociation;
import omis.vehicle.exception.VehicleAssociationExistsException;

/**
 * Delegate for vehicle association.
 * 
 * @author Yidong Li
 * @version 0.1.0 (Dec 19, 2018)
 * @since OMIS 3.0
 */
public class VehicleAssociationDelegate {
	
	private final InstanceFactory<VehicleAssociation>
	vehicleAssociationInstanceFactory;
	
	private final InstanceFactory<VehicleOwnerAssociation>
	vehicleOwnerAssociationInstanceFactory;
	
	private final VehicleAssociationDao vehicleAssociationDao;
	
	private final VehicleLicenseDao vehicleLicenseDao;
	
	private final VehicleOwnerAssociationDao vehicleOwnerAssociationDao;
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	private final VehicleOwnerAssociationDelegate vehicleOwnerAssociationDelegate;
	
	private final VehicleLicenseDelegate vehicleLicenseDelegate;
	
	/**
	 * Instantiates a delegate for vehicle association.
	 * 
	 * @param vehicleAssociationInstanceFactory
	 * instance factory for vehicle association
	 * @param vehicleAssociationDao vehicle association for external referral
	 * association
	 * @param auditComponentRetriever audit component retriever
	 * @param vehicleOwnerAssociationDelegate vehicle owner association delegate
	 * @param vehicleLicenseDelegate vehicle license delegate
	 * @param vehicleLicenseDao vehicle license dao
	 * @param vehicleOwnerAssociationDao vvehicle owner association dao
	 * @param vehicleOwnerAssociationInstanceFactory vehicle owner association
	 * instance factory
	 */
	public VehicleAssociationDelegate(
		final InstanceFactory<VehicleAssociation>
		vehicleAssociationInstanceFactory,
		final VehicleAssociationDao vehicleAssociationDao,
		final AuditComponentRetriever auditComponentRetriever,
		final VehicleOwnerAssociationDelegate vehicleOwnerAssociationDelegate,
		final VehicleLicenseDelegate vehicleLicenseDelegate,
		final VehicleLicenseDao vehicleLicenseDao,
		final VehicleOwnerAssociationDao vehicleOwnerAssociationDao,
		final InstanceFactory<VehicleOwnerAssociation>
		vehicleOwnerAssociationInstanceFactory) {
		this.vehicleAssociationInstanceFactory
			= vehicleAssociationInstanceFactory;
		this.vehicleAssociationDao = vehicleAssociationDao;
		this.auditComponentRetriever = auditComponentRetriever;
		this.vehicleOwnerAssociationDelegate = vehicleOwnerAssociationDelegate;
		this.vehicleLicenseDelegate = vehicleLicenseDelegate;
		this.vehicleLicenseDao = vehicleLicenseDao;
		this.vehicleOwnerAssociationDao = vehicleOwnerAssociationDao;
		this.vehicleOwnerAssociationInstanceFactory
			= vehicleOwnerAssociationInstanceFactory;
	}

	/**
	 * Creates and persists a vehicle association
	 * 
	 * @param offender offender
	 * @param vehicleModel vehicle model
	 * @param vehicelColor vehicle color
	 * @param year year
	 * @param ownerDescription owner description
	 * @param dateRange date range
	 * @param comments comments
	 * @param plateNumber plate number
	 * @param state state
	 * @param vehicleMake vehicle make 
	 * @return vehcileAssociation vehicle association
	 * @throws VehicleAssociationExistsException if vehicle association already
	 * exists
	 */
	public VehicleAssociation create(
		final Offender offender, 
		final VehicleModel vehicleModel, final VehicleColor vehicleColor, 
		final Integer year, final String ownerDescription,final DateRange dateRange, 
		final String comments, final String plateNumber, final State state,
		final VehicleMake vehicleMake)
		throws VehicleAssociationExistsException {
		if (this.vehicleAssociationDao.findExcludingSave(vehicleModel, 
			vehicleColor,year,dateRange,comments,vehicleMake, offender) != null) {
				throw new VehicleAssociationExistsException("Vehicle Association"
					+ " Already Exist.");
		}
		VehicleAssociation vehicleAssociation 
			= this.vehicleAssociationInstanceFactory.createInstance();
		
		vehicleAssociation.setOffender(offender);
	 	vehicleAssociation.setComments(comments);
	 	vehicleAssociation.setVehicleModel(vehicleModel);
	 	vehicleAssociation.setVehicleColor(vehicleColor);
	 	vehicleAssociation.setVehicleMake(vehicleMake);
		vehicleAssociation.setYear(year);
		vehicleAssociation.setDateRange(dateRange);
		vehicleAssociation.setCreationSignature(new CreationSignature(
			this.auditComponentRetriever.retrieveUserAccount(), 
			this.auditComponentRetriever.retrieveDate()));
		vehicleAssociation.setUpdateSignature(new UpdateSignature(
			this.auditComponentRetriever.retrieveUserAccount(),
			this.auditComponentRetriever.retrieveDate()));
		vehicleAssociationDao.makePersistent(vehicleAssociation);
		
		if (ownerDescription != null) {
			this.vehicleOwnerAssociationDelegate.create(vehicleAssociation,
			ownerDescription);
		}
		
		this.vehicleLicenseDelegate.create(vehicleAssociation, plateNumber,
			state);
		
		return vehicleAssociation;
	}
	
	/**
	 * Updates and persists a vehicle association
	 * 
	 * @param offender offender
	 * @param vehicleModel vehicle model
	 * @param vehicelColor vehicle color
	 * @param year year
	 * @param ownerDescription owner description
	 * @param dateRange date range
	 * @param comments comments
	 * @param plateNumber plate number
	 * @param state state
	 * @param vehicleMake vehicle make 
	 * @return vehcileAssociation vehicle association
	 * @throws VehicleAssociationExistsException if vehicle association already
	 * exists
	 */
	public VehicleAssociation update(
		final VehicleAssociation vehicleAssociation, 
		final VehicleModel vehicleModel, final VehicleColor vehicleColor, 
		final Integer year, final DateRange dateRange, 
		final String comments, final VehicleMake vehicleMake, final State state,
		final String plateNumber, final String ownerDescription,
		final Offender offender) throws VehicleAssociationExistsException {
		if (this.vehicleAssociationDao.findExcluding(vehicleModel, vehicleColor, 
			year, dateRange, comments, vehicleAssociation,vehicleMake, offender 
			)!=null) {
			throw new VehicleAssociationExistsException("Vehicle Association Already"
				+ " Exist.");			
		}
		
		vehicleAssociation.setComments(comments);
		vehicleAssociation.setVehicleModel(vehicleModel);
		vehicleAssociation.setVehicleColor(vehicleColor);
		vehicleAssociation.setYear(year);
		vehicleAssociation.setDateRange(dateRange);
		vehicleAssociation.setVehicleMake(vehicleMake);
					
		if (this.vehicleLicenseDelegate.findLicenseByVehicleAssociation(
			vehicleAssociation)!=null) {
			VehicleLicense vehicleLicenseFound 
				= this.vehicleLicenseDelegate.findLicenseByVehicleAssociation(
				vehicleAssociation);
			vehicleLicenseFound.setState(state);
			vehicleLicenseFound.setPlateNumber(plateNumber);
		}
		else {
			this.vehicleLicenseDelegate.create(vehicleAssociation, plateNumber,
			state);
		}
		
		if (ownerDescription != null) {
			if (this.vehicleOwnerAssociationDao.findOwnerByVehicleAssociation(
				vehicleAssociation)!=null) {
				VehicleOwnerAssociation vehicleOwnerAssociation 
					= this.vehicleOwnerAssociationDao
					.findOwnerByVehicleAssociation(vehicleAssociation);
				vehicleOwnerAssociation.setOwnerDescription(
						ownerDescription);
			}
			else {
				VehicleOwnerAssociation vehicleOwnerAssociation 
					= this.vehicleOwnerAssociationInstanceFactory
					.createInstance();
				vehicleOwnerAssociation.setOwnerDescription(
						ownerDescription);
				vehicleOwnerAssociation
					.setVehicleAssociation(vehicleAssociation);
				vehicleOwnerAssociation.setCreationSignature(
						new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
				vehicleOwnerAssociation
					.setUpdateSignature(new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
				vehicleOwnerAssociationDao
					.makePersistent(vehicleOwnerAssociation);
			}
		}
		else {
			if (this.vehicleOwnerAssociationDao.findOwnerByVehicleAssociation(
				vehicleAssociation)!=null) {
				VehicleOwnerAssociation vehicleOwnerAssociation 
				= this.vehicleOwnerAssociationDao.findOwnerByVehicleAssociation(
				vehicleAssociation);
				this.vehicleOwnerAssociationDelegate.remove(vehicleOwnerAssociation);
			}
		}
		return  this.vehicleAssociationDao.makePersistent(vehicleAssociation);
	}
	
	
	/**
	 * Removes a vehicle association
	 * 
	 * @param vehicleAssociation vehicle association
	 */
	public void remove(	final VehicleAssociation vehicleAssociation) {
		if (this.vehicleLicenseDelegate.findLicenseByVehicleAssociation(
			vehicleAssociation) != null) {
			this.vehicleLicenseDelegate.remove(this.vehicleLicenseDelegate
			.findLicenseByVehicleAssociation(vehicleAssociation));
		}
		if (this.vehicleOwnerAssociationDelegate.findOwnerByVehicleAssociation(
			vehicleAssociation) != null) {
			VehicleOwnerAssociation owner = this.vehicleOwnerAssociationDelegate
			.findOwnerByVehicleAssociation(vehicleAssociation);
			this.vehicleOwnerAssociationDelegate.remove(owner);
		}
		this.vehicleAssociationDao.makeTransient(vehicleAssociation);
	}
}