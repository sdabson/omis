package omis.disciplinaryCode.service.testng;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.disciplinaryCode.domain.DisciplinaryCode;
import omis.disciplinaryCode.service.DisciplinaryCodeService;
import omis.exception.DuplicateEntityFoundException;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;

/**
 * DisciplinaryCodeRemoveTests.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Aug 16, 2016)
 *@since OMIS 3.0
 *
 */
@Test(groups={"disciplinaryCode"})
public class DisciplinaryCodeRemoveTests 
	extends AbstractHibernateTransactionalTestNGSpringContextTests{
	
	
	@Autowired
	@Qualifier("disciplinaryCodeService")
	private DisciplinaryCodeService disciplinaryCodeService;
	
	@Test
	public void testRemove() throws DuplicateEntityFoundException{
		
		final String value = "testValue";
		final String description = "testDescription";
		final String extendedDescription = "testExtendedDescription";
		
		final DisciplinaryCode disciplinaryCode = this.disciplinaryCodeService
				.createDisciplinaryCode(value, description, extendedDescription);
		
		this.disciplinaryCodeService.removeDisciplinaryCode(disciplinaryCode);
		
		assert !this.disciplinaryCodeService
			.findDisciplinaryCodeByValue(value).contains(disciplinaryCode)
			: "Disciplinary code was not removed.";
	}
	
}
