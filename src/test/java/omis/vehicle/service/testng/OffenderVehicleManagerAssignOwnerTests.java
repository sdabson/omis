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

package omis.vehicle.service.testng;

import java.beans.PropertyEditor;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.datatype.DateRange;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.util.PropertyValueAsserter;
import omis.vehicle.dao.VehicleAssociationDao;
import omis.vehicle.domain.VehicleAssociation;
import omis.vehicle.domain.VehicleOwnerAssociation;
import omis.vehicle.exception.VehicleAssociationExistsException;
import omis.vehicle.service.OffenderVehicleManager;

/**
 * Test assigning vehicle owner association
 *
 * @author Yidong Li
 * @version 0.0.1 (Dec. 20, 2018)
 * @since OMIS 3.0
 */
@Test(groups = {"vehicle", "service"})
public class OffenderVehicleManagerAssignOwnerTests 
	extends AbstractHibernateTransactionalTestNGSpringContextTests {
	/* Instance factory */
	@Autowired
	@Qualifier("vehicleAssociationInstanceFactory")
	private InstanceFactory<VehicleAssociation> vehicleAssociationInstanceFactory;
	
	/* Delegates. */
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	@Qualifier("auditComponentRetriever")
	private AuditComponentRetriever auditComponentRetriever;
	
	/* Service to test. */
	@Autowired
	@Qualifier("offenderVehicleManager")
	private OffenderVehicleManager offenderVehicleManager;
	
	/* Property editor factories. */
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;
	
	/* Data access object. */
	@Autowired
	@Qualifier("vehicleAssociationDao")
	private VehicleAssociationDao vehicleAssociationDao;
	
	/* Test methods. */
	
	/**
	 * Test assigning vehicle owner association
	 * @throws VehicleAssociationExistsException 
	 */
	@Test
	public void testAssignOwner()
		throws VehicleAssociationExistsException {
		// Arrangements
		VehicleAssociation vehicleAssociation
		= this.vehicleAssociationInstanceFactory.createInstance();
		Offender offender = this.offenderDelegate.createWithoutIdentity(
			"lastName", "firstName", "middleName", "Mr.");
		DateRange dateRange = new DateRange(this.parseDateText("01/01/2018"),
			null);
		CreationSignature creationSignature = new CreationSignature(
			this.auditComponentRetriever.retrieveUserAccount(), 
			this.auditComponentRetriever.retrieveDate());
		UpdateSignature	updateSignature = new UpdateSignature(
			this.auditComponentRetriever.retrieveUserAccount(),
			this.auditComponentRetriever.retrieveDate());
		
		vehicleAssociation.setOffender(offender);
		vehicleAssociation.setCreationSignature(creationSignature);
		vehicleAssociation.setDateRange(dateRange);
		vehicleAssociation.setUpdateSignature(updateSignature);
		vehicleAssociation.setYear(2018);
		
		this.vehicleAssociationDao.makePersistent(vehicleAssociation);
		
		// Action
		VehicleOwnerAssociation vehicleOwnerAssociation
			= this.offenderVehicleManager.assignOwner(vehicleAssociation,
			"ownerDescription");
		
		// Assertion
		PropertyValueAsserter.create()
		.addExpectedValue("vehicleAssociation",
			vehicleOwnerAssociation.getVehicleAssociation())
		.addExpectedValue("OwnerDescription",
			vehicleOwnerAssociation.getOwnerDescription())
		.performAssertions(vehicleOwnerAssociation);
	}
	
	// Parse date text
	private Date parseDateText(final String dateText) {
		PropertyEditor propertyEditor = this.customDateEditorFactory
				.createCustomDateOnlyEditor(true);
		propertyEditor.setAsText(dateText);
		return (Date) propertyEditor.getValue();
	}
}