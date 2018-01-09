package omis.identificationnumber.service.testng;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.exception.DuplicateEntityFoundException;
import omis.identificationnumber.domain.IdentificationNumberCategory;
import omis.identificationnumber.domain.Multitude;
import omis.identificationnumber.service.IdentificationNumberCategoryService;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;

/**
 * IdentificationNumberCategoryServiceUpdatetTests.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Aug 2, 2017)
 *@since OMIS 3.0
 *
 */
public class IdentificationNumberCategoryServiceUpdatetTests
		extends AbstractHibernateTransactionalTestNGSpringContextTests {
	
	@Autowired
	@Qualifier("identificationNumberCategoryService")
	private IdentificationNumberCategoryService
						identificationNumberCategoryService;
	
	@Test
	public void testUpdate() throws DuplicateEntityFoundException {
		final String name = "CategoryName";
		final Boolean valid = true;
		final Multitude multitude = Multitude.SINGLE;
		IdentificationNumberCategory category =
				this.identificationNumberCategoryService.create("OldName",
						Multitude.MULTIPLE, false);
		
		category = this.identificationNumberCategoryService.update(
				category, name, multitude, valid);
		
		assert name.equals(category.getName())
		: String.format("Wrong name for category: %s found; %s expected",
				category.getName(), name);
		assert multitude.equals(category.getMultitude())
		: String.format("Wrong multitude for category: %s found; %s expected",
				category.getMultitude(), multitude);
		assert valid.equals(category.getValid())
		: String.format("Wrong valid for category: %s found; %s expected",
				category.getValid(), valid);
	}
	
}
