package omis.vehicle.service.impl;

import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;
import omis.region.dao.StateDao;
import omis.region.domain.State;
import omis.vehicle.dao.VehicleAssociationDao;
import omis.vehicle.dao.VehicleColorDao;
import omis.vehicle.dao.VehicleLicenseDao;
import omis.vehicle.dao.VehicleMakeDao;
import omis.vehicle.dao.VehicleModelDao;
import omis.vehicle.dao.VehicleOwnerAssociationDao;
import omis.vehicle.domain.VehicleAssociation;
import omis.vehicle.domain.VehicleColor;
import omis.vehicle.domain.VehicleLicense;
import omis.vehicle.domain.VehicleMake;
import omis.vehicle.domain.VehicleModel;
import omis.vehicle.domain.VehicleOwnerAssociation;
import omis.vehicle.service.OffenderVehicleManager;

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
	 * @param vehicleColorDao vehicle color data access object
	 * @param vehicleMakeDao vehicle make data access object
	 * @param vehicleModelDao vehicle model data access object
	 * @param stateDao state data access object
	 * @param vehicleAssociationInstanceFactory vehicle association instance;
	 * @param vehicleOwnerAssociationInstanceFactory vehicle owner association 
	 * 	instance
	 * @param vehicleLicenseInstanceFactory vehicle license instance
	 * @param auditComponentRetriever audit component retriever
	 */
	private final VehicleAssociationDao vehicleAssociationDao;
	private final VehicleOwnerAssociationDao vehicleOwnerAssociationDao;
	private final VehicleColorDao vehicleColorDao;
	private final VehicleMakeDao vehicleMakeDao;
	private final VehicleModelDao vehicleModelDao;
	private final VehicleLicenseDao vehicleLicenseDao;
	private final StateDao stateDao;
	private final InstanceFactory<VehicleAssociation>	
		vehicleAssociationInstanceFactory;
	private final InstanceFactory<VehicleOwnerAssociation> 
		vehicleOwnerAssociationInstanceFactory;
	private final InstanceFactory<VehicleLicense> vehicleLicenseInstanceFactory;
	private final AuditComponentRetriever auditComponentRetriever;

	public OffenderVehicleManagerImpl(
		VehicleAssociationDao vehicleAssociationDao,
		VehicleColorDao vehicleColorDao, 
		VehicleMakeDao vehicleMakeDao,
		VehicleModelDao vehicleModelDao,
		VehicleLicenseDao vehicleLicenseDao,
		VehicleOwnerAssociationDao vehicleOwnerAssociationDao,
		StateDao stateDao,
		InstanceFactory<VehicleAssociation> vehicleAssociationInstanceFactory,
		InstanceFactory<VehicleOwnerAssociation> 
			vehicleOwnerAssociationInstanceFactory,
		InstanceFactory<VehicleLicense> vehicleLicenseInstanceFactory,
		InstanceFactory<VehicleMake> vehicleMakeInstanceFactory,
		InstanceFactory<VehicleModel> vehicleModelInstanceFactory,
		InstanceFactory<VehicleColor> vehicleColorInstanceFactory,
		AuditComponentRetriever auditComponentRetriever) {
			this.vehicleAssociationDao = vehicleAssociationDao;
			this.vehicleOwnerAssociationDao = vehicleOwnerAssociationDao;
			this.vehicleColorDao = vehicleColorDao;
			this.vehicleMakeDao = vehicleMakeDao;
			this.vehicleModelDao = vehicleModelDao;
			this.vehicleLicenseDao = vehicleLicenseDao;
			this.stateDao = stateDao;
			this.vehicleAssociationInstanceFactory 
				= vehicleAssociationInstanceFactory;
			this.vehicleOwnerAssociationInstanceFactory 
				= vehicleOwnerAssociationInstanceFactory;
			this.vehicleLicenseInstanceFactory = vehicleLicenseInstanceFactory;
			this.auditComponentRetriever = auditComponentRetriever;
	}	

	/** {@inheritDoc} */
	@Override
	public VehicleAssociation associateVehicle(final Offender offender, 
		final VehicleModel vehicleModel, final VehicleColor vehicleColor, 
		final Integer year, final String ownerDescription,final DateRange	dateRange, 
		final String comments, final String plateNumber, final State state,
		final VehicleMake vehicleMake)	
		throws DuplicateEntityFoundException {
		if (this.vehicleAssociationDao.findExcludingSave(vehicleModel, 
			vehicleColor,year,dateRange,comments,vehicleMake, offender)!=null) {
				throw new DuplicateEntityFoundException("Vehicle Association"
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
		vehicleAssociation.setCreationSignature(this.newCreationSignature());
		vehicleAssociation.setUpdateSignature(this.newUpdateSignature());
		vehicleAssociationDao.makePersistent(vehicleAssociation);
		if(ownerDescription != null){
			VehicleOwnerAssociation vehicleOwnerAssociation 
				= this.vehicleOwnerAssociationInstanceFactory.createInstance();
			vehicleOwnerAssociation=this.assignOwner(vehicleAssociation, 
					ownerDescription);
			this.vehicleOwnerAssociationDao
				.makePersistent(vehicleOwnerAssociation);
		}

		VehicleLicense vehicleLicense = this.vehicleLicenseInstanceFactory
			.createInstance();
		vehicleLicense = this.assignLicense(vehicleAssociation, plateNumber,
				state);
		vehicleLicenseDao.makePersistent(vehicleLicense);
		
		return vehicleAssociation;
	}	
	
	/** {@inheritDoc} */
	@Override
	public VehicleAssociation updateVehicle(final VehicleAssociation 
		vehicleAssociation, final VehicleModel vehicleModel, final VehicleColor 
		vehicleColor, final Integer year, final DateRange dateRange, 
		final String comments, final VehicleMake vehicleMake, final State state, 
		final String plateNumber, final String ownerDescription, final Offender offender)
			throws DuplicateEntityFoundException {
		if (this.vehicleAssociationDao.findExcluding(vehicleModel, vehicleColor, 
			year, dateRange, comments, vehicleAssociation,vehicleMake, offender 
			)!=null) {
			throw new DuplicateEntityFoundException("Vehicle Association Already"
				+ " Exist.");
		}
		
		this.populateVehicleAssociation(vehicleAssociation, dateRange, year, 
			comments, vehicleModel, vehicleColor, vehicleMake, state, 
			plateNumber, ownerDescription);
		return  this.vehicleAssociationDao.makePersistent(vehicleAssociation);
	}
	
	/** {@inheritDoc} */
	@Override	
	public VehicleOwnerAssociation assignOwner(final VehicleAssociation 
		vehicleAssociation, final String ownerDescription){
			VehicleOwnerAssociation vehicleOwnerAssociation 
				= this.vehicleOwnerAssociationInstanceFactory.createInstance();
			vehicleOwnerAssociation.setVehicleAssociation(vehicleAssociation);
			vehicleOwnerAssociation.setOwnerDescription(ownerDescription);
			vehicleOwnerAssociation.setCreationSignature(
					this.newCreationSignature());
			vehicleOwnerAssociation
				.setUpdateSignature(this.newUpdateSignature());
			return this.vehicleOwnerAssociationDao
				.makePersistent(vehicleOwnerAssociation);
	}
	
	/** {@inheritDoc} */
	@Override
	public VehicleLicense assignLicense(final VehicleAssociation 
		vehicleAssociation, final String plateNumber, final State state){
		VehicleLicense vehicleLicense = this.vehicleLicenseInstanceFactory
			.createInstance();
		vehicleLicense.setVehicleAssociation(vehicleAssociation);
		vehicleLicense.setPlateNumber(plateNumber);
		vehicleLicense.setState(state);
		vehicleLicense.setCreationSignature(this.newCreationSignature());
		vehicleLicense.setUpdateSignature(this.newUpdateSignature());
		return this.vehicleLicenseDao.makePersistent(vehicleLicense);		
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
		return this.vehicleLicenseDao
			.findLicenseByVehicleAssociation(vehicleAssociation);
	}
	
	/** {@inheritDoc} */
	@Override
	public void removeLicense(final VehicleLicense vehicleLicense){
		this.vehicleLicenseDao.makeTransient(vehicleLicense);
	}
	
	/** {@inheritDoc} */
	@Override
	public void remove(final VehicleAssociation vehicleAssociation){
		if(this.findLicenseByVehicleAssociation(vehicleAssociation)!=null){
			this.vehicleLicenseDao
				.makeTransient(findLicenseByVehicleAssociation(vehicleAssociation));
		}
		if(findOwnerByVehicleAssociation(vehicleAssociation)!=null){
			this.vehicleOwnerAssociationDao
				.makeTransient(findOwnerByVehicleAssociation(vehicleAssociation));
		}
		this.vehicleAssociationDao.makeTransient(vehicleAssociation);
	}
	
	/** {@inheritDoc} */
	@Override
	public void removeOwner(final VehicleOwnerAssociation 
		vehicleOwnerAssociation){
		this.vehicleOwnerAssociationDao.makeTransient(vehicleOwnerAssociation);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<VehicleColor> findColors(){
		return this.vehicleColorDao.findColors();
	}
	
	/** {@inheritDoc} */
	@Override
	public List<VehicleMake> findVehicleMake(){
		return this.vehicleMakeDao.findVehicleMake();
	}
	
	/** {@inheritDoc} */
	@Override
	public List<VehicleModel> findVehicleModelsByMake(final VehicleMake 
		vehicleMake){
			return this.vehicleModelDao.findVehicleMoldelByMake(vehicleMake);
	}
	
	/*
	 * Populates the specified vehicle association with the specified properties 
	 * and an update signature.
	 */

	private VehicleAssociation populateVehicleAssociation(
		final VehicleAssociation vehicleAssociation, final DateRange dateRange, 
		final Integer year,	final String comments, 
		final VehicleModel vehicleModel, final VehicleColor vehicleColor, 
		final VehicleMake vehicleMake,	final State state, 
		final String plateNumber, final String ownerDescription) {
		 	vehicleAssociation.setComments(comments);
			vehicleAssociation.setVehicleModel(vehicleModel);
			vehicleAssociation.setVehicleColor(vehicleColor);
			vehicleAssociation.setYear(year);
			vehicleAssociation.setDateRange(dateRange);
			vehicleAssociation.setVehicleMake(vehicleMake);
						
			if(findLicenseByVehicleAssociation(vehicleAssociation)!=null){
				VehicleLicense vehicleLicenseFound 
					= findLicenseByVehicleAssociation(vehicleAssociation);
				vehicleLicenseFound.setState(state);
				vehicleLicenseFound.setPlateNumber(plateNumber);
			}
			else {
				VehicleLicense vehicleLicenseNew 
					= this.vehicleLicenseInstanceFactory
					.createInstance();
				vehicleLicenseNew.setState(state);
				vehicleLicenseNew.setPlateNumber(plateNumber);
				vehicleLicenseNew.setVehicleAssociation(vehicleAssociation);
				vehicleLicenseNew.setCreationSignature(
					this.newCreationSignature());
				vehicleLicenseNew
					.setUpdateSignature(this.newUpdateSignature());
				vehicleLicenseDao
					.makePersistent(vehicleLicenseNew);
			}
			
			if(ownerDescription != null){
				if(findOwnerByVehicleAssociation(vehicleAssociation)!=null){
					VehicleOwnerAssociation vehicleOwnerAssociation 
						= findOwnerByVehicleAssociation(vehicleAssociation);
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
							this.newCreationSignature());
					vehicleOwnerAssociation
						.setUpdateSignature(this.newUpdateSignature());
					vehicleOwnerAssociationDao
						.makePersistent(vehicleOwnerAssociation);
				}
			}
			else {
				if(findOwnerByVehicleAssociation(vehicleAssociation)!=null){
					VehicleOwnerAssociation vehicleOwnerAssociation 
					= findOwnerByVehicleAssociation(vehicleAssociation);
					this.removeOwner(vehicleOwnerAssociation);
				}
			}
			return vehicleAssociation;
	}
	
	/*
	 * Returns a new update signature with current date and user.
	 * 
	 * @return new update signature
	 */
	private UpdateSignature newUpdateSignature() {
		return new UpdateSignature(this.auditComponentRetriever
				.retrieveUserAccount(), this.auditComponentRetriever
				.retrieveDate());
	}
	
	/*
	 * Returns a new creation signature with the current date and user.
	 * 
	 * @return new creation signature
	 */
	private CreationSignature newCreationSignature() {
		return new CreationSignature(this.auditComponentRetriever
				.retrieveUserAccount(), this.auditComponentRetriever
				.retrieveDate());
	}
	
	/** {@inheritDoc} */
	@Override
	public List<State> findStates( ) {
		List<State> vehicleStates = this.stateDao.findAll();
		return vehicleStates;
	}
}

