package omis.identificationnumber.service.testng;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.exception.DuplicateEntityFoundException;
import omis.identificationnumber.domain.IdentificationNumberCategory;
import omis.identificationnumber.domain.Multitude;
import omis.identificationnumber.service.IdentificationNumberCategoryService;
import omis.identificationnumber.service.delegate.IdentificationNumberCategoryDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;

/**
 * IdentificationNumberCategoryServiceRemoveTests.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Aug 2, 2017)
 *@since OMIS 3.0
 *
 */
public class IdentificationNumberCategoryServiceRemoveTests
		extends AbstractHibernateTransactionalTestNGSpringContextTests {
	
	@Autowired
	@Qualifier("identificationNumberCategoryDelegate")
	private IdentificationNumberCategoryDelegate
						identificationNumberCategoryDelegate;
	
	@Autowired
	@Qualifier("identificationNumberCategoryService")
	private IdentificationNumberCategoryService
						identificationNumberCategoryService;
	
	@Test
	public void testRemove() throws DuplicateEntityFoundException {
		final String name = "CategoryName";
		final Boolean valid = true;
		final Multitude multitude = Multitude.MULTIPLE;
		
		final IdentificationNumberCategory category =
				this.identificationNumberCategoryService.create(
						name, multitude, valid);
		
		this.identificationNumberCategoryService.remove(category);
		
		assert !this.identificationNumberCategoryDelegate.findAll()
		.contains(category) : "IdentificationNumberCategory was not deleted.";
	}
	
}
