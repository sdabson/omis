package omis.disciplinaryCode.service.testng;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.disciplinaryCode.domain.DisciplinaryCode;
import omis.disciplinaryCode.service.DisciplinaryCodeService;
import omis.exception.DuplicateEntityFoundException;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;

/**
 * DisciplinaryCodeCreateTests.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Aug 16, 2016)
 *@since OMIS 3.0
 *
 */
@Test(groups={"disciplinaryCode"})
public class DisciplinaryCodeCreateTests 
	extends AbstractHibernateTransactionalTestNGSpringContextTests{
	
	@Autowired
	@Qualifier("disciplinaryCodeService")
	private DisciplinaryCodeService disciplinaryCodeService;
	
	@Test
	public void testCreate() throws DuplicateEntityFoundException{
		
		final String value = "testValue";
		final String description = "testDescription";
		final String extendedDescription = "testExtendedDescription";
		final DisciplinaryCode disciplinaryCode = this.disciplinaryCodeService
				.createDisciplinaryCode(value, description, extendedDescription);
		
		
		assert value.equals(disciplinaryCode.getValue())
		: String.format("Wrong value found: %s found, %s expected.", 
				disciplinaryCode.getValue(), value);
		
		assert description.equals(disciplinaryCode.getDescription())
		: String.format("Wrong description found: %s found, %s expected.", 
				disciplinaryCode.getDescription(), description);
		
		assert extendedDescription.equals(disciplinaryCode.getExtendedDescription())
		: String.format("Wrong extended description found: %s found, %s expected.", 
				disciplinaryCode.getExtendedDescription(), extendedDescription);
	}
	
}
