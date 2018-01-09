package omis.disciplinaryCode.service.testng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.datatype.DateRange;
import omis.disciplinaryCode.domain.DisciplinaryCode;
import omis.disciplinaryCode.domain.SupervisoryOrganizationCode;
import omis.disciplinaryCode.service.DisciplinaryCodeService;
import omis.disciplinaryCode.service.delegate.SupervisoryOrganizationCodeDelegate;
import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
import omis.supervision.domain.SupervisoryOrganization;
import omis.supervision.service.delegate.SupervisoryOrganizationDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;

/**
 * SupervisoryOrganizationCodeRemoveTests.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Aug 16, 2016)
 *@since OMIS 3.0
 *
 */
@Test(groups={"disciplinaryCode"})
public class SupervisoryOrganizationCodeRemoveTests 
	extends AbstractHibernateTransactionalTestNGSpringContextTests {
	
	@Autowired
	@Qualifier("supervisoryOrganizationDelegate")
	private SupervisoryOrganizationDelegate supervisoryOrganizationDelegate;
	
	@Autowired
	@Qualifier("supervisoryOrganizationCodeDelegate")
	private SupervisoryOrganizationCodeDelegate supervisoryOrganizationCodeDelegate;
	
	@Autowired
	@Qualifier("disciplinaryCodeService")
	private DisciplinaryCodeService disciplinaryCodeService;
	
	public void testRemove()
			throws DuplicateEntityFoundException, DateConflictException{
		final Date startDate = this.parseDateText("08/01/2016");
		final Date endDate = this.parseDateText("08/31/2016");
		final DateRange dateRange = new DateRange(startDate, endDate);
		
		final SupervisoryOrganization supervisoryOrganization 
			= this.supervisoryOrganizationDelegate
				.create("testOrganization", "testAlias", null);
		
		final DisciplinaryCode disciplinaryCode = this.disciplinaryCodeService
				.createDisciplinaryCode("testValue", "testDescription",
						"Extended Description");
		
		final SupervisoryOrganizationCode supervisoryOrganizationCode =
				this.disciplinaryCodeService.createSupervisoryOrganizationCode(
						supervisoryOrganization, dateRange, disciplinaryCode);
		
		this.disciplinaryCodeService
			.removeSupervisoryOrganizationCode(supervisoryOrganizationCode);
		
		assert !this.supervisoryOrganizationCodeDelegate.findAll()
		.contains(supervisoryOrganizationCode)
		: "Supervisory organization code was not removed.";
		
	}
	
	private Date parseDateText(final String dateText) {
		try {
			return new SimpleDateFormat("MM/dd/yyyy").parse(dateText);
		} catch (ParseException e) {
			throw new RuntimeException("Error parsing date", e);
		}
	}
}
