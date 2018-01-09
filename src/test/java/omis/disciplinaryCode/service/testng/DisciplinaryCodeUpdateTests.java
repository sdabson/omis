package omis.disciplinaryCode.service.testng;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.disciplinaryCode.domain.DisciplinaryCode;
import omis.disciplinaryCode.service.DisciplinaryCodeService;
import omis.exception.DuplicateEntityFoundException;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;

/**
 * DisciplinaryCodeUpdateTests.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Aug 16, 2016)
 *@since OMIS 3.0
 *
 */
@Test(groups={"disciplinaryCode"})
public class DisciplinaryCodeUpdateTests 
	extends AbstractHibernateTransactionalTestNGSpringContextTests{
	
	@Autowired
	@Qualifier("disciplinaryCodeService")
	private DisciplinaryCodeService disciplinaryCodeService;
	
	@Test
	public void testUpdate() throws DuplicateEntityFoundException{
		
		final String value = "testValue";
		final String description = "testDescription";
		final String extendedDescription = "testExtendedDescription";
		final String valueUpdated = "testValueUpdated";
		final String descriptionUpdated = "testDescriptionUpdated";
		final String extendedDescriptionUpdated = "testExtendedDescriptionUpdated";
		
		DisciplinaryCode disciplinaryCode = this.disciplinaryCodeService
				.createDisciplinaryCode(value, description, extendedDescription);
		
		disciplinaryCode = this.disciplinaryCodeService
				.updateDisciplinaryCode(disciplinaryCode, valueUpdated, 
						descriptionUpdated, extendedDescriptionUpdated);
		
		assert valueUpdated.equals(disciplinaryCode.getValue())
		: String.format("Wrong value found: %s found, %s expected.", 
				disciplinaryCode.getValue(), valueUpdated);
		
		assert descriptionUpdated.equals(disciplinaryCode.getDescription())
		: String.format("Wrong description found: %s found, %s expected.", 
				disciplinaryCode.getDescription(), descriptionUpdated);
		
		assert extendedDescriptionUpdated.equals(disciplinaryCode.getExtendedDescription())
		: String.format("Wrong extended description found: %s found, %s expected.", 
				disciplinaryCode.getExtendedDescription(), extendedDescriptionUpdated);
	}
	
}
