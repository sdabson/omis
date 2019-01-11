package omis.staff.service.testng;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.media.domain.Photo;
import omis.media.exception.PhotoExistsException;
import omis.media.service.delegate.PhotoDelegate;
import omis.person.domain.Person;
import omis.person.service.delegate.PersonDelegate;
import omis.staff.domain.StaffPhotoAssociation;
import omis.staff.exception.StaffPhotoAssociationExistsException;
import omis.staff.service.StaffAssignmentService;
import omis.staff.service.delegate.StaffPhotoAssociationDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.util.PropertyValueAsserter;

/**
 * Tests method to associate photo.
 *
 * @author Yidong Li
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"staff", "service"})
public class StaffAssignmentServiceUpdatePhotoTests
	extends AbstractHibernateTransactionalTestNGSpringContextTests {
	/* Delegate */
	@Autowired
	@Qualifier("personDelegate")
	private PersonDelegate personDelegate;
	
	@Autowired
	@Qualifier("staffPhotoAssociationDelegate")
	private StaffPhotoAssociationDelegate staffPhotoAssociationDelegate;
	
	@Autowired
	@Qualifier("photoDelegate")
	private PhotoDelegate photoDelegate;

	/* Service */
	@Autowired
	@Qualifier("staffAssignmentService")
	private StaffAssignmentService staffAssignmentService;
	
	/* Test methods. */

	/**
	 * Tests the photo update.
	 * @throws PhotoExistsException 
	 * 
	 */
	public void testPhotoUpdate() throws PhotoExistsException {
		// Arrangements
		Date photoDate = new Date(20000000);
		String fileName = "photo filename";
		Photo photo	= this.photoDelegate.create(fileName, photoDate);
		Date newPhotoDate = new Date(120000000);

		// Action
		this.staffAssignmentService.updatePhoto(photo, newPhotoDate);
		
		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("date", newPhotoDate)
			.addExpectedValue("filename", fileName)
			.performAssertions(photo);
	}
}