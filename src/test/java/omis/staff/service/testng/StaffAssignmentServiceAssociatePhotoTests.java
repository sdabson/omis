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
public class StaffAssignmentServiceAssociatePhotoTests
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
	 * Tests the association of photo.
	 * @throws StaffPhotoAssociationExistsException
	 * @throws PhotoExistsException
	 * 
	 */
	public void testPhotoAssociation()
		throws StaffPhotoAssociationExistsException,
		PhotoExistsException{
		// Arrangements
		Person staffMember = this.personDelegate
			.create("Smith", "John", "Ryan", "Mr.");
		Date photoDate = new Date(20000000);
		String fileName = "photo filename";

		// Action
		StaffPhotoAssociation staffPhotoAssociation
			= this.staffAssignmentService.associatePhoto(staffMember,
			photoDate, fileName);
		
		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("person", staffMember)
			.addExpectedValue("photo.date", photoDate)
			.addExpectedValue("photo.filename", fileName)
			.performAssertions(staffPhotoAssociation);
	}
	
	/**
	 * Tests that {@code StaffPhotoAssociationExistsException} is thrown.
	 * 
	 * @throws StaffPhotoAssociationExistsException
	 * @throws PhotoExistsException 
	 */
	@Test(expectedExceptions = {StaffPhotoAssociationExistsException.class})
	public void testStaffPhotoAssociationExistsException() 
		throws StaffPhotoAssociationExistsException,
		PhotoExistsException  {
		// Arrangements
		Person staffMember = this.personDelegate
			.create("Smith", "John", "Ryan", "Mr.");
		Date firstPhotoDate = new Date(20000000);
		String firstFileName = "First photo file name";
		Date secondPhotoDate = new Date(80000000);
		String secondFileName = "Second photo file name";
		Photo firstPhoto = this.photoDelegate.create(firstFileName,
			firstPhotoDate);
		this.staffPhotoAssociationDelegate.create(staffMember,
			firstPhoto);
		
		// Action
		this.staffAssignmentService.associatePhoto(
		staffMember, secondPhotoDate, secondFileName);
	}
	
	/**
	 * Tests that {@code PhotoExistsException} is thrown.
	 * 
	 * @throws StaffPhotoAssociationExistsException
	 * @throws PhotoExistsException 
	 */
	@Test(expectedExceptions = {PhotoExistsException.class})
	public void testPhotoExistsException() 
		throws StaffPhotoAssociationExistsException,
		PhotoExistsException  {
		// Arrangements
		Person staffMember = this.personDelegate
			.create("Smith", "John", "Ryan", "Mr.");
		Date photoDate = new Date(20000000);
		String fileName = "photo file name";
		Photo photo = this.photoDelegate.create(fileName, photoDate);
		this.staffPhotoAssociationDelegate.create(staffMember, photo);
		
		// Action
		this.staffAssignmentService.associatePhoto(
		staffMember, new Date(20000000), fileName);
	}
}